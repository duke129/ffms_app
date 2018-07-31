package com.happiest.minds.ffms.sales;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.happiest.minds.ffms.CommonUtility;
import com.happiest.minds.ffms.Constant;
import com.happiest.minds.ffms.CustomPageAdapter;
import com.happiest.minds.ffms.FFMSRequestQueue;
import com.happiest.minds.ffms.R;
import com.happiest.minds.ffms.UserProfileFragment;
import com.happiest.minds.ffms.Webserver;
import com.happiest.minds.ffms.sales.pojo.APIResponse;
import com.happiest.minds.ffms.sales.pojo.DashBoardSummaryCountVo;
import com.happiest.minds.ffms.sales.pojo.TicketCardViewData;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class SalesHomeActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = SalesHomeActivity.class.getName();

    Context context;

    View actionBar_V;

    ImageView profileMenu_IV, notification_IV;

    Switch user_SB;

    static TextView welcome_TV;

    RelativeLayout button_grid_RL, footer_RL, banner_RL;

    private ViewPager viewPager;

    int currentPage = 0;

    int NUM_PAGES = 0;

    Timer timer;

    LinearLayout newLeads_LL, inProgressLeads_LL, completedLeads_LL, searchLeads_LL, createNewLeads_LL, rejectedLeads_LL;
    TextView newLeads_TV, inProgressLeads_TV, completedLeads_TV, searchLeads_TV, createNewLeads_TV, rejectedLeads_TV,
            newLeadsCount_TV, inprogressCount_TV, completedCount_TV, rejectedCount_TV;
    private FrameLayout fragmentContainerFrameLayout;

    private CustomPageAdapter customPagerAdapter;

    SalesLeadsCardView salesCardViewFragment;

    SalesCreateNewLeads salesCreateNewLeads;

    UserProfileFragment userProfileFragment;

    public static boolean flag, isOnHome, isOnProfile, isOnLeadsDetails, isOnCardView, isSearch, isOnCreate;

    FFMSRequestQueue ffmsRequestQueue;
    ObjectMapper objectMapper;
    APIResponse apiResponse;
    public static ArrayList<TicketCardViewData> ticketCardViewDataList;
    ArrayList<DashBoardSummaryCountVo> dashBoardSummaryCountVoArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_home);

        context = this;

        ffmsRequestQueue = FFMSRequestQueue.getInstance(context);
        objectMapper = new ObjectMapper();

        flag = false;
        isOnHome = true;
        isOnProfile = false;
        isOnLeadsDetails = false;
        isOnCardView = false;
        isSearch = false;
        isOnCreate = false;

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        getActionBarTitle();

        loadBanner();

        initGUI();

    }

    private void getActionBarTitle() {
        // TODO Auto-generated method stub
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        /* actionBar variable initialization */

        actionBar_V = mInflater.inflate(R.layout.custom_actionbar_sales_home,
                null);

        profileMenu_IV = (ImageView) actionBar_V
                .findViewById(R.id.profileMenu_IV);
        welcome_TV = (TextView) actionBar_V.findViewById(R.id.welcome_TV);
        welcome_TV.setText(context.getResources().getString(
                R.string.welcome_text)
                + " " + "HM");
        user_SB = (Switch) actionBar_V.findViewById(R.id.user_SB);
        notification_IV = (ImageView) actionBar_V.findViewById(R.id.notification_IV);

        actionBar.setCustomView(actionBar_V);

        actionBar.setDisplayShowCustomEnabled(true);

        /* apply on click listener on actionBar elements */

        profileMenu_IV.setOnClickListener(this);
        user_SB.setOnClickListener(this);
        notification_IV.setOnClickListener(this);

        user_SB.setChecked(true);

    }

    private void loadBanner() {

        int[] banners = {R.drawable.hm_banner_first,
                R.drawable.hm_banner_second, R.drawable.hm_banner_third};

        customPagerAdapter = new CustomPageAdapter(this, banners);

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        viewPager.setAdapter(customPagerAdapter);

        NUM_PAGES = customPagerAdapter.getCount();

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 500, 3000);
    }


    private void initGUI() {

        button_grid_RL = (RelativeLayout) findViewById(R.id.button_grid_RL);
        footer_RL = (RelativeLayout) findViewById(R.id.footer_RL);
        banner_RL = (RelativeLayout) findViewById(R.id.banner_RL);

        fragmentContainerFrameLayout = (FrameLayout) findViewById(R.id.fragmentContainer);

        newLeads_LL = (LinearLayout) findViewById(R.id.newLeads_LL);
        inProgressLeads_LL = (LinearLayout) findViewById(R.id.inProgressLeads_LL);
        completedLeads_LL = (LinearLayout) findViewById(R.id.completedLeads_LL);
        searchLeads_LL = (LinearLayout) findViewById(R.id.searchLeads_LL);
        createNewLeads_LL = (LinearLayout) findViewById(R.id.createNewLeads_LL);
        rejectedLeads_LL = (LinearLayout) findViewById(R.id.rejectedLeads_LL);

        newLeads_TV = (TextView) findViewById(R.id.newLeads_TV);
        inProgressLeads_TV = (TextView) findViewById(R.id.inProgressLeads_TV);
        completedLeads_TV = (TextView) findViewById(R.id.completedLeads_TV);
        searchLeads_TV = (TextView) findViewById(R.id.searchLeads_TV);
        createNewLeads_TV = (TextView) findViewById(R.id.createNewLeads_TV);
        rejectedLeads_TV = (TextView) findViewById(R.id.rejectedLeads_TV);

        newLeadsCount_TV = (TextView) findViewById(R.id.newLeadsCount_TV);
        inprogressCount_TV = (TextView) findViewById(R.id.inprogressCount_TV);
        completedCount_TV = (TextView) findViewById(R.id.completedCount_TV);
        rejectedCount_TV = (TextView) findViewById(R.id.rejectedCount_TV);

        newLeads_LL.setOnClickListener(this);
        inProgressLeads_LL.setOnClickListener(this);
        completedLeads_LL.setOnClickListener(this);
        searchLeads_LL.setOnClickListener(this);
        createNewLeads_LL.setOnClickListener(this);
        rejectedLeads_LL.setOnClickListener(this);

        callCountService();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.profileMenu_IV:

                if (isOnHome) {

                    welcome_TV.setText(context.getResources().getString(
                            R.string.welcome_text)
                            + " " + "HM");

                    user_SB.setVisibility(View.VISIBLE);

                    removeFragmentFromHome();

                    redirectToUserProfileFragment();

                    notification_IV.setVisibility(View.VISIBLE);

                } else if (isOnLeadsDetails) {

                    Log.i(TAG, "isOnLeadsDetails : "+isOnLeadsDetails);

                    if (isSearch) {

                        Log.i(TAG, "isOnLeadsDetails : "+isOnLeadsDetails+" isSearch : "+isSearch);

                        isSearch = false;

                        onHomeButtonClick();

                        welcome_TV.setText(context.getResources().getString(
                                R.string.welcome_text)
                                + " " + "HM");

                        callCountService();

                    } else {

                        Log.i(TAG, "isOnLeadsDetails : "+isOnLeadsDetails+"else isSearch : "+isSearch);

                        notification_IV.setVisibility(View.VISIBLE);

                        user_SB.setVisibility(View.GONE);

                        int clickedBucketId = CommonUtility.getSeButtonClickedValue(context);

                        callServiceForCardView(clickedBucketId);
                    }


                } else if (isOnCreate) {

                    onHomeButtonClick();

                    welcome_TV.setText(context.getResources().getString(
                            R.string.welcome_text)
                            + " " + "HM");

                    callCountService();

                } else if (isSearch) {

                    isSearch = false;

                    onHomeButtonClick();

                    welcome_TV.setText(context.getResources().getString(
                            R.string.welcome_text)
                            + " " + "HM");

                    callCountService();


                } else {

                    onHomeButtonClick();

                    welcome_TV.setText(context.getResources().getString(
                            R.string.welcome_text)
                            + " " + "HM");

                    callCountService();

                }

                break;

            case R.id.newLeads_LL:

                CommonUtility.saveSeButtonClickedValue(context, Constant.NEW);

                callServiceForCardView(Constant.NEW);

                break;

            case R.id.inProgressLeads_LL:

                CommonUtility.saveSeButtonClickedValue(context, Constant.IN_PROGRESS);

                callServiceForCardView(Constant.IN_PROGRESS);

                break;

            case R.id.completedLeads_LL:

                CommonUtility.saveSeButtonClickedValue(context, Constant.COMPLETED);

                callServiceForCardView(Constant.COMPLETED);

                break;

            case R.id.rejectedLeads_LL:

                CommonUtility.saveSeButtonClickedValue(context, Constant.REJECTED);

                callServiceForCardView(Constant.REJECTED);

                break;

            case R.id.createNewLeads_LL:

                notification_IV.setVisibility(View.VISIBLE);

                user_SB.setVisibility(View.GONE);

                removeFragmentFromHome();

                redirectToCreateNewFragment();

                break;

            case R.id.searchLeads_LL:

                isSearch = true;

                CommonUtility.saveSeButtonClickedValue(context, Constant.SEARCH);

                notification_IV.setVisibility(View.VISIBLE);

                user_SB.setVisibility(View.GONE);

                removeFragmentFromHome();

                Intent prospectSearchIntent = new Intent(context,
                        SalesSearchLeadActivityDailog.class);

                startActivity(prospectSearchIntent);

                // redirectToSearchLeadFragment();

                break;
        }


    }

    private void redirectToCardViewFragment() {

        // boolean ticketAvailable = checkTicketCount();

        boolean ticketAvailable = false;

        if (!ticketAvailable) {

            flag = false;
            isOnHome = false;
            isOnProfile = false;
            isOnLeadsDetails = false;
            isOnCardView = true;
            isOnCreate = false;

            if(isSearch){

                isSearch = true;

            }else{

                isSearch = false;
            }

            hide();

            profileMenu_IV.setImageResource(R.drawable.back_button);

            welcome_TV.setText(context.getResources().getString(R.string.lead_card_view_text));

            footer_RL.setVisibility(View.GONE);
            banner_RL.setVisibility(View.GONE);
            button_grid_RL.setVisibility(View.GONE);

            fragmentContainerFrameLayout.setVisibility(View.VISIBLE);

            salesCardViewFragment = new SalesLeadsCardView();

            if (salesCardViewFragment != null) {

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer,
                                salesCardViewFragment)
                        .addToBackStack(null).commitAllowingStateLoss();

            } else {

                Log.i(TAG, " salesExecutiveProspectCardViewFragment is null");
            }

        } else {
            if (context != null) {

                CommonUtility.showToastMessage(context, context
                        .getResources().getString(R.string.data_not_available));
            }

            if (context != null) {
                welcome_TV.setText(context.getResources().getString(
                        R.string.welcome_text)
                        + " " + "HM");
            }

            return;
        }

    }

    private void redirectToCreateNewFragment() {

        // boolean ticketAvailable = checkTicketCount();

        boolean ticketAvailable = false;

        if (!ticketAvailable) {

            flag = false;
            isOnHome = false;
            isOnProfile = false;
            isOnLeadsDetails = false;
            isSearch = false;
            isOnCardView = false;
            isOnCreate = true;

            hide();

            profileMenu_IV.setImageResource(R.drawable.back_button);

            welcome_TV.setText(context.getResources().getString(R.string.create_new_lead_text));

            footer_RL.setVisibility(View.GONE);
            banner_RL.setVisibility(View.GONE);
            button_grid_RL.setVisibility(View.GONE);

            fragmentContainerFrameLayout.setVisibility(View.VISIBLE);

            salesCreateNewLeads = new SalesCreateNewLeads();

            if (salesCreateNewLeads != null) {

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer,
                                salesCreateNewLeads)
                        .addToBackStack(null).commitAllowingStateLoss();

            } else {

                Log.i(TAG, " salesCreateNewLeads is null");
            }

        } else {
            if (context != null) {

                CommonUtility.showToastMessage(context, context
                        .getResources().getString(R.string.somthing_went_wrong));
            }

            if (context != null) {
                welcome_TV.setText(context.getResources().getString(
                        R.string.welcome_text)
                        + " " + "HM");
            }

            return;
        }

    }


    private void hide() {
        // TODO Auto-generated method stub


        if (flag) {

            profileMenu_IV.setImageResource(R.drawable.back_button);
            welcome_TV.setVisibility(View.INVISIBLE);

            footer_RL.setVisibility(View.GONE);

            fragmentContainerFrameLayout.setVisibility(View.VISIBLE);

            isSearch = false;

        } else {
            show();
        }

    }

    private void show() {

    }

    private void removeFragmentFromHome() {

        isOnHome = true;
        isOnProfile = false;
        isOnLeadsDetails = false;
        isOnCardView = false;

        if (isOnCardView) {

            if (salesCardViewFragment != null
                    && getFragmentManager()
                    .findFragmentById(
                            this.salesCardViewFragment
                                    .getId()) != null) {

                fragmentContainerFrameLayout.setVisibility(View.GONE);

                getFragmentManager().beginTransaction()
                        .remove(salesCardViewFragment)
                        .addToBackStack(null).addToBackStack(null)
                        .commitAllowingStateLoss();

                isOnCardView = false;

            } else if (userProfileFragment != null
                    && getFragmentManager()
                    .findFragmentById(
                            this.userProfileFragment
                                    .getId()) != null) {

                fragmentContainerFrameLayout.setVisibility(View.GONE);

                getFragmentManager().beginTransaction()
                        .remove(userProfileFragment)
                        .addToBackStack(null).addToBackStack(null)
                        .commitAllowingStateLoss();

                isOnProfile = false;

            } else {

                Log.i(TAG, "Something went wrong in removeFragmentFromHome");
            }
        }
    }


    private void onHomeButtonClick() {

        removeFragmentFromHome();

        flag = false;

        hide();

        profileMenu_IV.setImageResource(R.drawable.user_profile_icon);

        user_SB.setVisibility(View.GONE);

        notification_IV.setVisibility(View.VISIBLE);

        fragmentContainerFrameLayout.setVisibility(View.GONE);

        footer_RL.setVisibility(View.VISIBLE);
        banner_RL.setVisibility(View.VISIBLE);
        button_grid_RL.setVisibility(View.VISIBLE);

    }

    private void redirectToUserProfileFragment() {

        flag = false;

        isOnHome = false;
        isOnProfile = true;
        isOnLeadsDetails = false;
        isOnCardView = false;

        hide();

        welcome_TV.setText(context.getResources().getString(
                R.string.profile_text));

        profileMenu_IV.setImageResource(R.drawable.back_button);

        footer_RL.setVisibility(View.GONE);
        banner_RL.setVisibility(View.GONE);
        button_grid_RL.setVisibility(View.GONE);

        fragmentContainerFrameLayout.setVisibility(View.VISIBLE);

        userProfileFragment = new UserProfileFragment();

        if (userProfileFragment != null) {

            getFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, userProfileFragment)
                    .addToBackStack(null).commitAllowingStateLoss();
        } else {

            Log.i(TAG, " userProfileFragment is null");
        }

    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        // super.onBackPressed();
    }


    private void callServiceForCardView(int clickedBucketId) {

        String host = Webserver.SERVER_HOST;
        String uri = Webserver.SALES_CARD_VIEW_URI;
        String url = host + "" + uri + "/" + clickedBucketId;


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.i(TAG,
                                "callServiceForCardView  response : "
                                        + response);

                        try {

                            ticketCardViewDataList = objectMapper.readValue(
                                    response,
                                    TypeFactory.defaultInstance()
                                            .constructCollectionType(
                                                    ArrayList.class,
                                                    TicketCardViewData.class));

                            processForCardViewRedirect();

                        } catch (JsonParseException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (JsonMappingException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                if (error != null) {

                    Log.e(TAG,
                            "callServiceForCardView onErrorResponse : "
                                    + error.toString());

                }
                if (!SalesHomeActivity.this.isFinishing()) {

                    if (context != null
                            && !SalesHomeActivity.this
                            .isFinishing()) {
                        CommonUtility
                                .showServerResponseMessage(
                                        context,
                                        context.getResources()
                                                .getString(
                                                        R.string.invalid_server_response_for_webservice)
                                                + " SALES_CARD_VIEW_URI");
                    }
                }


            }

        });

        ffmsRequestQueue.addToRequestQueue(stringRequest);

    }

    private void processForCardViewRedirect() {

        welcome_TV.setText(context.getResources().getString(
                R.string.leads_card_view_text));

        removeFragmentFromHome();

        redirectToCardViewFragment();
    }

    private void callCountService() {

        String host = Webserver.SERVER_HOST;
        String uri = Webserver.SALES_COUNT_SERVICE;

        String url = host + "" + uri+"/1";

        Log.i(TAG, "callCountService url : " + url);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        Log.i(TAG,
                                "callCountService response : "
                                        + response);

                        try {

                            apiResponse = objectMapper.readValue(response.toString(), APIResponse.class);

                            if (apiResponse != null) {

                                Log.i(TAG, "apiResponse :" + apiResponse.toString());

                                Object data = apiResponse.getData();

                                if (data != null) {

                                    String dataString = objectMapper.writeValueAsString(data);

                                    dashBoardSummaryCountVoArrayList = objectMapper.readValue(
                                            dataString,
                                            TypeFactory.defaultInstance()
                                                    .constructCollectionType(
                                                            ArrayList.class,
                                                            DashBoardSummaryCountVo.class));

                                    if (dashBoardSummaryCountVoArrayList != null) {

                                        setCountToUI(dashBoardSummaryCountVoArrayList);

                                    } else {

                                        Log.i(TAG, "dashBoardSummaryCountVoArrayList is null");
                                    }

                                    Log.i(TAG, "dataString : " + dataString);
                                }
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e(TAG,
                        "callCountService onErrorResponse : "
                                + error);
            }
        });


        ffmsRequestQueue.addToRequestQueue(jsonObjectRequest);

    }

    private void setCountToUI(ArrayList<DashBoardSummaryCountVo> dashBoardSummaryCountVoArrayListArgs) {

        for (int i = 0; i < dashBoardSummaryCountVoArrayListArgs.size(); i++) {

            DashBoardSummaryCountVo dashBoardSummaryCountVo = dashBoardSummaryCountVoArrayListArgs.get(i);

            int bucketId = dashBoardSummaryCountVo.getStatusId();

            switch (bucketId) {

                case Constant.NEW:

                    newLeadsCount_TV.setText("" + dashBoardSummaryCountVo.getTotalCounts());

                    break;

                case Constant.IN_PROGRESS:

                    inprogressCount_TV.setText("" + dashBoardSummaryCountVo.getTotalCounts());

                    break;

                case Constant.COMPLETED:

                    completedCount_TV.setText("" + dashBoardSummaryCountVo.getTotalCounts());

                    break;

                case Constant.REJECTED:

                    rejectedCount_TV.setText("" + dashBoardSummaryCountVo.getTotalCounts());

                    break;

                default:

                    break;


            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i(TAG, " onResume ");

        if (isSearch) {

            if (ticketCardViewDataList.size() > 0) {

                processForCardViewRedirect();

            } else {

                Log.i(TAG, "Search canceled");

                isSearch = false;
            }
        }

    }
}
