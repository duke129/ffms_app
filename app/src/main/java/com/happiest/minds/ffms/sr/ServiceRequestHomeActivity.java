package com.happiest.minds.ffms.sr;

import android.content.Context;
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
import com.android.volley.toolbox.StringRequest;
import com.happiest.minds.ffms.CommonUtility;
import com.happiest.minds.ffms.Constant;
import com.happiest.minds.ffms.CustomPageAdapter;
import com.happiest.minds.ffms.FFMSRequestQueue;
import com.happiest.minds.ffms.R;
import com.happiest.minds.ffms.UserProfileFragment;
import com.happiest.minds.ffms.Webserver;
import com.happiest.minds.ffms.sales.SalesCreateNewLeads;
import com.happiest.minds.ffms.sales.SalesLeadsCardView;
import com.happiest.minds.ffms.sales.pojo.TicketCardViewData;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;



public class ServiceRequestHomeActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = ServiceRequestHomeActivity.class.getName();

    Context context;

    View actionBar_V;

    ImageView profileMenu_IV, notification_IV;

    Switch user_SB;

    static TextView welcome_TV;

    RelativeLayout button_grid_RL,footer_RL,banner_RL;

    private ViewPager viewPager;

    int currentPage = 0;

    int NUM_PAGES = 0;

    Timer timer;

    LinearLayout newLeads_LL, inProgressLeads_LL, completedLeads_LL, searchLeads_LL, createNewLeads_LL, rejectedLeads_LL;
    TextView newLeads_TV, inProgressLeads_TV, completedLeads_TV, searchLeads_TV, createNewLeads_TV, rejectedLeads_TV;
    private FrameLayout fragmentContainerFrameLayout;

    private CustomPageAdapter customPagerAdapter;

    SRLeadCradView srLeadCradView;

    UserProfileFragment userProfileFragment;

    public static boolean flag,isOnHome,isOnProfile, isOnLeadsDetails,isOnCardView,isSearch,isOnCreate;

    FFMSRequestQueue ffmsRequestQueue;
    ObjectMapper objectMapper;
    public static ArrayList<TicketCardViewData> ticketCardViewDataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_request_home);

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

        actionBar_V = mInflater.inflate(R.layout.custom_actionbar_sr_home,
                null);

        profileMenu_IV = (ImageView) actionBar_V
                .findViewById(R.id.profileMenu_SR_IV);
        welcome_TV = (TextView) actionBar_V.findViewById(R.id.welcome_SR_TV);
        welcome_TV.setText(context.getResources().getString(
                R.string.welcome_text)
                + " " + "HM");
        user_SB = (Switch) actionBar_V.findViewById(R.id.user_SR_SB);
        notification_IV = (ImageView) actionBar_V.findViewById(R.id.notification_SR_IV);

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

        viewPager = (ViewPager) findViewById(R.id.viewpager_SR);

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

        button_grid_RL = (RelativeLayout) findViewById(R.id.button_grid_SR_RL);
        footer_RL = (RelativeLayout) findViewById(R.id.footer_SR_RL);
        banner_RL = (RelativeLayout) findViewById(R.id.banner_SR_RL);

        fragmentContainerFrameLayout = (FrameLayout) findViewById(R.id.fragmentContainer_SR);

        newLeads_LL = (LinearLayout) findViewById(R.id.newLeads_SR_LL);
        inProgressLeads_LL = (LinearLayout) findViewById(R.id.inProgressLeads_SR_LL);
        completedLeads_LL = (LinearLayout) findViewById(R.id.completedLeads_SR_LL);
        searchLeads_LL = (LinearLayout) findViewById(R.id.searchLeads_SR_LL);
        createNewLeads_LL = (LinearLayout) findViewById(R.id.createNewLeads_SR_LL);
        rejectedLeads_LL = (LinearLayout) findViewById(R.id.rejectedLeads_SR_LL);

        newLeads_TV = (TextView) findViewById(R.id.newLeads_SR_TV);
        inProgressLeads_TV = (TextView) findViewById(R.id.inProgressLeads_SR_TV);
        completedLeads_TV = (TextView) findViewById(R.id.completedLeads_SR_TV);
        searchLeads_TV = (TextView) findViewById(R.id.searchLeads_SR_TV);
        createNewLeads_TV = (TextView) findViewById(R.id.createNewLeads_SR_TV);
        rejectedLeads_TV = (TextView) findViewById(R.id.rejectedLeads_SR_TV);

        newLeads_LL.setOnClickListener(this);
        inProgressLeads_LL.setOnClickListener(this);
        completedLeads_LL.setOnClickListener(this);
        searchLeads_LL.setOnClickListener(this);
        createNewLeads_LL.setOnClickListener(this);
        rejectedLeads_LL.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.profileMenu_SR_IV:

                if (isOnHome) {

                    welcome_TV.setText(context.getResources().getString(
                            R.string.welcome_text)
                            + " " + "HM");

                    user_SB.setVisibility(View.VISIBLE);

                    removeFragmentFromHome();

                    redirectToUserProfileFragment();

                    notification_IV.setVisibility(View.VISIBLE);

                } else if (isOnLeadsDetails) {

                    notification_IV.setVisibility(View.VISIBLE);

                    user_SB.setVisibility(View.GONE);

                    removeFragmentFromHome();

                    redirectToCardViewFragment();


                }else if (isOnCreate) {

                    onHomeButtonClick();

                    welcome_TV.setText(context.getResources().getString(
                            R.string.welcome_text)
                            + " " + "HM");

                } else {

                    onHomeButtonClick();

                    welcome_TV.setText(context.getResources().getString(
                            R.string.welcome_text)
                            + " " + "HM");

                }

                break;

            case R.id.newLeads_SR_LL:

                callServiceForCardView();

                break;

            case R.id.inProgressLeads_SR_LL:

                callServiceForCardView();

                break;

            case R.id.completedLeads_SR_LL:

                callServiceForCardView();

                break;

            case R.id.rejectedLeads_SR_LL:

                callServiceForCardView();

                break;

            case R.id.createNewLeads_SR_LL:

                notification_IV.setVisibility(View.VISIBLE);

                user_SB.setVisibility(View.GONE);

                removeFragmentFromHome();

                redirectToCreateNewFragment();

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
            isSearch = false;
            isOnCardView = true;
            isOnCreate = false;

            hide();

            profileMenu_IV.setImageResource(R.drawable.back_button);

            welcome_TV.setText(context.getResources().getString(R.string.lead_card_view_text));

            footer_RL.setVisibility(View.GONE);
            banner_RL.setVisibility(View.GONE);
            button_grid_RL.setVisibility(View.GONE);

            fragmentContainerFrameLayout.setVisibility(View.VISIBLE);

            srLeadCradView = new SRLeadCradView();

            if (srLeadCradView != null) {

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer_SR,
                                srLeadCradView)
                        .addToBackStack(null).commitAllowingStateLoss();

            } else {

                Log.i(TAG, "Fragment is null");
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

    private void redirectToCreateNewFragment(){

        // boolean ticketAvailable = checkTicketCount();

       /* boolean ticketAvailable = false;

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
                        .replace(R.id.fragmentContainer_SR,
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
        }*/

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
        isSearch = false;

    }

    private void removeFragmentFromHome() {

        isOnHome = true;
        isOnProfile = false;
        isOnLeadsDetails = false;
        isOnCardView = false;

        if (isOnCardView) {

            if (srLeadCradView != null
                    && getFragmentManager()
                    .findFragmentById(
                            this.srLeadCradView
                                    .getId()) != null) {

                fragmentContainerFrameLayout.setVisibility(View.GONE);

                getFragmentManager().beginTransaction()
                        .remove(srLeadCradView)
                        .addToBackStack(null).addToBackStack(null)
                        .commitAllowingStateLoss();

                isOnCardView = false;

            }else if (userProfileFragment != null
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

            } else{

                Log.i(TAG, "Something went wrong in removeFragmentFromHome");
            }
        }
    }


    private void onHomeButtonClick() {

        removeFragmentFromHome();

        flag = false;

        isSearch = false;

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
                    .replace(R.id.fragmentContainer_SR, userProfileFragment)
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


    private void callServiceForCardView(){

        String host = Webserver.SERVER_HOST;
        String uri = Webserver.SALES_CARD_VIEW_URI;
        String url = host + "" + uri;


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
                if (!ServiceRequestHomeActivity.this.isFinishing()) {

                    if (context != null
                            && !ServiceRequestHomeActivity.this
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

    private void processForCardViewRedirect(){

        CommonUtility.saveSeButtonClickedValue(context,
                Constant.NEW_LEADS);

        welcome_TV.setText(context.getResources().getString(
                R.string.newLeads_bucket_text));

        removeFragmentFromHome();

        redirectToCardViewFragment();
    }
}
