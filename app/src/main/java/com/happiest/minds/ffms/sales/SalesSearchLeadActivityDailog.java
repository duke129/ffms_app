package com.happiest.minds.ffms.sales;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.happiest.minds.ffms.CommonUtility;
import com.happiest.minds.ffms.FFMSRequestQueue;
import com.happiest.minds.ffms.R;
import com.happiest.minds.ffms.Webserver;
import com.happiest.minds.ffms.sales.pojo.APIResponse;
import com.happiest.minds.ffms.sales.pojo.TicketCardViewData;
import com.happiest.minds.ffms.sales.pojo.TicketFilter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class SalesSearchLeadActivityDailog extends FragmentActivity implements View.OnClickListener {

    private static final String TAG = SalesSearchLeadActivityDailog.class.getSimpleName();
    Context context;

    FFMSRequestQueue ffmsRequestQueue;
    ObjectMapper objectMapper;
    TicketFilter ticketFilter;
    APIResponse apiResponse;

    EditText ticketNumber_ET, mobileNumber_ET;
    LinearLayout search_LL,searchCancel_LL;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_sales_search_lead);

        context = this;


        ffmsRequestQueue = FFMSRequestQueue.getInstance(context);
        objectMapper = new ObjectMapper();

        initGUI();

    }

    private void initGUI(){

        ticketNumber_ET = (EditText) findViewById(R.id.ticketNumber_ET);
        mobileNumber_ET = (EditText) findViewById(R.id.mobileNumber_ET);
        search_LL = (LinearLayout) findViewById(R.id.search_LL);
        searchCancel_LL = (LinearLayout) findViewById(R.id.searchCancel_LL);
        search_LL.setOnClickListener(this);
        searchCancel_LL.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.search_LL:
                Log.i(TAG, "clicked on search");

                validateInput();

                break;

            case R.id.searchCancel_LL:
                Log.i(TAG, "clicked on cancel");
                finish();
                break;
        }
    }

    private  void validateInput(){

        boolean validIpnut = false;
        String ticketNumber = ticketNumber_ET.getText().toString().trim();
        String mobileNumber = mobileNumber_ET.getText().toString().trim();

        ticketFilter = new TicketFilter();

        if(ticketNumber.isEmpty()){

           if(mobileNumber.isEmpty()){

               CommonUtility.showToastMessage(context, "Please enter at least one field..!");

               validIpnut = false;

               return;

           }else if(mobileNumber.length() < 10){


               CommonUtility.showToastMessage(context, "Please enter valid mobile number..!");

               validIpnut = false;

               return;

           }else{

               Log.i(TAG, "Entered valid mobile number : "+mobileNumber);

               validIpnut = true ;
               Log.i(TAG, "Call search service for mobile number : "+mobileNumber);

               ticketFilter.setMobileNumber(mobileNumber);

           }
        }else{

            Log.i(TAG, " Entered ticket number : "+ticketNumber);

            if(mobileNumber.isEmpty()){

                validIpnut = true;

                Log.i(TAG, "Call search service for ticket number : "+ticketNumber);

                ticketFilter.setTicketNo(ticketNumber);

            }else if(mobileNumber.length() < 10){


                CommonUtility.showToastMessage(context, "Please enter valid mobile number..!");

                validIpnut = false;

                return;

            }else{

                validIpnut = true;

                Log.i(TAG, "Call search service for ticket number : "+ticketNumber+" and mobile number : "+mobileNumber);

                ticketFilter.setTicketNo(ticketNumber);
                ticketFilter.setMobileNumber(mobileNumber);
            }

        }

        if(validIpnut){

            callSearchService();
        }
    }

    private void callSearchService(){

        try {

            String jsonData = objectMapper
                    .writeValueAsString(ticketFilter);

            JSONObject jsonObject = new JSONObject(jsonData);

            String host = Webserver.SERVER_HOST;
            String uri = Webserver.SEARCH_URI;

            String url = host + "" + uri;

            Log.i(TAG, "callSearchService url : " + url
                    + " jsonObject : " + jsonObject);

            if (context != null && !((Activity) context).isFinishing()) {
                CommonUtility.showProgressDailog(context,
                        "Please Wait...");
            }

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST, url, jsonObject,
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {

                            if (context != null
                                    && !((Activity) context).isFinishing()) {
                                CommonUtility.cancelProgressDailog(context);
                            }

                            Log.i(TAG,
                                    "callSearchService response : "
                                            + response);

                            try {

                                apiResponse = objectMapper.readValue(response.toString(), APIResponse.class);

                                if (apiResponse != null) {

                                    Log.i(TAG, "apiResponse :" + apiResponse.toString());

                                    Object data = apiResponse.getData();

                                    if (data != null) {

                                        String dataString = objectMapper.writeValueAsString(data);

                                        Log.i(TAG, "dataString : " + dataString);

                                        SalesHomeActivity.ticketCardViewDataList = objectMapper.readValue(
                                                dataString,
                                                TypeFactory.defaultInstance()
                                                        .constructCollectionType(
                                                                ArrayList.class,
                                                                TicketCardViewData.class));

                                        if(SalesHomeActivity.ticketCardViewDataList.size() >0){

                                            finish();

                                        }else{

                                            CommonUtility.showServerResponseMessage(context, "Result not found for given input..!");
                                        }



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
                            "callSearchService onErrorResponse : "
                                    + error);

                    if (context != null
                            && !((Activity) context).isFinishing()) {
                        CommonUtility.cancelProgressDailog(context);
                    }

                }
            });


            ffmsRequestQueue.addToRequestQueue(jsonObjectRequest);

        } catch (JsonGenerationException e)

        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e)

        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e)

        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        // super.onBackPressed();
    }
}
