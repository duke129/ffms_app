package com.happiest.minds.ffms.sales;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.happiest.minds.ffms.CommonUtility;
import com.happiest.minds.ffms.Constant;
import com.happiest.minds.ffms.FFMSRequestQueue;
import com.happiest.minds.ffms.R;
import com.happiest.minds.ffms.Webserver;
import com.happiest.minds.ffms.sales.pojo.APIResponse;
import com.happiest.minds.ffms.sales.pojo.ActivityChild;
import com.happiest.minds.ffms.sales.pojo.AddressVo;
import com.happiest.minds.ffms.sales.pojo.BasicInfoUpdate;
import com.happiest.minds.ffms.sales.pojo.CustomerVo;
import com.happiest.minds.ffms.sales.pojo.ProspectCreation;
import com.happiest.minds.ffms.sales.pojo.SalesActivityName;
import com.happiest.minds.ffms.sales.pojo.SalesActivityType;
import com.happiest.minds.ffms.sales.pojo.SpinnerItems;
import com.happiest.minds.ffms.sales.pojo.TicketDetails;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;


public class SalesTicketDetailsFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = SalesTicketDetailsFragment.class.getSimpleName();
    public static Context context;

    View view;


    private LinearLayoutManager layoutManager;

    List<SalesActivityName> salesActivityNames;

    private static final String ERROR_MSG = "Very very very long error message to get scrolling or multiline animation when the error button is clicked";

    MaterialSpinner title_SP, branch_SP, area_SP;

    EditText ticketNumber_ET, ticketCreatedDate_ET, firstName_ET, middleName_ET,
            lastName_ET, mobileNo_ET, alternateMobileNo_ET, officeNo_ET, email_ET,
            alternateEmail_ET, city_ET, current_addressLine1_ET, current_addressLine2_ET,
            current_addressLandmark_ET, current_addressPincode_ET, billing_addressLine1_ET,
            billing_addressLine2_ET, billing_addressLandmark_ET, billing_addressPincode_ET,
            preferredCallTime_ET;

    LinearLayout basicInfo_update_BT_LL, sales_activity_LL;

    ImageButton calendar_IB;

    private int mYear, mMonth, mDay, mHour, mMinute;
    Date dateValueSelected;
    int selectedYear, selectedMonth, selectedDay;
    TimePickerDialog timePickerDialog;

    private ArrayAdapter<String> title_adapter, branch_adapter, area_adapter;

    private TicketDetails ticketDetails = SalesLeadsCardViewRecyclerAdapter.ticketDetailsArrayList.get(0);

    private RecyclerView recyclerView;
    private ArrayList<SalesActivity> salesActivityArrayList;
    private SalesActivityRecyclerAdapter adapter;

    BasicInfoUpdate basicInfoUpdate;
    FFMSRequestQueue ffmsRequestQueue;
    ObjectMapper objectMapper;
    APIResponse apiResponse;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sales_ticket_details, container, false);

        context = getActivity();

        ffmsRequestQueue = FFMSRequestQueue.getInstance(context);
        objectMapper = new ObjectMapper();

        initGUI();

       // initRecycler();

        //initSpinner();

        setDetailsToUI();

        recyclerView = (RecyclerView) view.findViewById(R.id.activity_recycler_view);

        salesActivityArrayList = new ArrayList<>();

        setData();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new SalesActivityRecyclerAdapter(getActivity(), salesActivityArrayList);
        recyclerView.setAdapter(adapter);

        return view;
    }

    public void initGUI() {
        ticketNumber_ET = view.findViewById(R.id.ticketNumber_ET);
        ticketCreatedDate_ET = view.findViewById(R.id.ticketCreatedDate_ET);
        firstName_ET = view.findViewById(R.id.firstName_ET);
        middleName_ET = view.findViewById(R.id.middleName_CN_ET);
        lastName_ET = view.findViewById(R.id.lastName_ET);
        mobileNo_ET = view.findViewById(R.id.mobileNo_ET);
        alternateMobileNo_ET = view.findViewById(R.id.alternateMobileNo_CN_ET);
        officeNo_ET = view.findViewById(R.id.officeNo_ET);
        email_ET = view.findViewById(R.id.email_CN_ET);
        alternateEmail_ET = view.findViewById(R.id.aternateEmail_ET);
        city_ET = view.findViewById(R.id.city_ET);
        current_addressLine1_ET = view.findViewById(R.id.current_addressLine1_ET);
        current_addressLine2_ET = view.findViewById(R.id.current_addressLine2_ET);
        current_addressLandmark_ET = view.findViewById(R.id.current_addressLandmark_ET);
        current_addressPincode_ET = view.findViewById(R.id.current_addressPincode_ET);
        billing_addressLine1_ET = view.findViewById(R.id.billing_addressLine1_ET);
        billing_addressLine2_ET = view.findViewById(R.id.billing_addressLine2_ET);
        billing_addressLandmark_ET = view.findViewById(R.id.billing_addressLandmark_ET);
        billing_addressPincode_ET = view.findViewById(R.id.billing_addressPincode_ET);
        title_SP = view.findViewById(R.id.title_SP);
        branch_SP = view.findViewById(R.id.branch_SP);
        area_SP = view.findViewById(R.id.area_SP);
        preferredCallTime_ET = view.findViewById(R.id.preferredCallTime_ET);
        calendar_IB = view.findViewById(R.id.calendar_IB);
        calendar_IB.setOnClickListener(this);

        basicInfo_update_BT_LL = view.findViewById(R.id.basicInfo_update_BT_LL);
        sales_activity_LL = view.findViewById(R.id.sales_activity_LL);
        basicInfo_update_BT_LL.setOnClickListener(this);
        //sales_activity_LL.setVisibility(View.GONE);

    }


    private List<? extends ParentListItem> generateSalesTicketTypes() {
        // TODO Auto-generated method stub

        SalesActivityType salesActivityType = SalesActivityType.get(getActivity());
        salesActivityNames = salesActivityType.getTicketNames();
        List<ParentListItem> parentListItems = new ArrayList<>();
        for (SalesActivityName t2 : salesActivityNames) {
            List<ActivityChild> activityChildItemList = new ArrayList<>();
            activityChildItemList.add(new ActivityChild(t2.getName()));
            t2.setChildItemList(activityChildItemList);
            parentListItems.add(t2);
        }
        return parentListItems;

    }

    private void initSpinner() {

        /*title spinner*/
        title_adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, SpinnerItems.TITLE_ITEMS);
        title_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        title_SP.setAdapter(title_adapter);
        title_SP.setPaddingSafe(0, 0, 0, 0);

        branch_adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, SpinnerItems.BRANCH_ITEMS);
        branch_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branch_SP.setAdapter(branch_adapter);
        branch_SP.setPaddingSafe(0, 0, 0, 0);

        area_adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, SpinnerItems.AREA_ITEMS);
        area_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        area_SP.setAdapter(area_adapter);
        area_SP.setPaddingSafe(0, 0, 0, 0);


    }

    private void setDetailsToUI() {

        ticketNumber_ET.setText(ticketDetails.getTicketNo());
        ticketCreatedDate_ET.setText("" + ticketDetails.getTicketCreatedTime());
        firstName_ET.setText(ticketDetails.getFirstName());
        middleName_ET.setText(ticketDetails.getMiddleName());
        lastName_ET.setText(ticketDetails.getLastName());
        mobileNo_ET.setText(ticketDetails.getMobileNumber());
        alternateMobileNo_ET.setText(ticketDetails.getAlternateMobileNumber());
        officeNo_ET.setText(ticketDetails.getOfficeNumber());
        email_ET.setText(ticketDetails.getEmailId());
        alternateEmail_ET.setText(ticketDetails.getAlternateEmailId());
        city_ET.setText(ticketDetails.getCityName());
        current_addressLine1_ET.setText(ticketDetails.getCommunicationAddress().getAddress1());
        current_addressLine2_ET.setText(ticketDetails.getCommunicationAddress().getAddress2());
        current_addressLandmark_ET.setText(ticketDetails.getCommunicationAddress().getLandmark());
        current_addressPincode_ET.setText(ticketDetails.getCommunicationAddress().getPincode());
        billing_addressLine1_ET.setText(ticketDetails.getCommunicationAddress().getAddress1());
        billing_addressLine2_ET .setText(ticketDetails.getCommunicationAddress().getAddress2());
        billing_addressLandmark_ET.setText(ticketDetails.getCommunicationAddress().getLandmark());
        billing_addressPincode_ET.setText(ticketDetails.getCommunicationAddress().getPincode());

        ArrayList<String> titleArrayList = new ArrayList<String>();
        titleArrayList.add(ticketDetails.getTitle());
        title_adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, titleArrayList);
        title_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        title_SP.setAdapter(title_adapter);
        title_SP.setPaddingSafe(0, 0, 0, 0);

        int titleSpinnerPosition = title_adapter
                .getPosition(ticketDetails.getTitle());
        Log.i(TAG, "titleSpinnerPosition :"+titleSpinnerPosition);
        title_SP.setSelection(1);

        ArrayList<String> branchArrayList = new ArrayList<String>();
        branchArrayList.add("Indira Nagar");
        branch_adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, branchArrayList);
        branch_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branch_SP.setAdapter(branch_adapter);
        branch_SP.setPaddingSafe(0, 0, 0, 0);
        int branchSpinnerPosition = branch_adapter
                .getPosition("Indira Nagar");
        Log.i(TAG, "branchSpinnerPosition :"+branchSpinnerPosition);
        branch_SP.setSelection(1);

        ArrayList<String> areaArrayList = new ArrayList<String>();
        areaArrayList.add("BTM Layout");
        area_adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, areaArrayList);
        area_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        area_SP.setAdapter(area_adapter);
        area_SP.setPaddingSafe(0, 0, 0, 0);

        int areaSpinnerPosition = area_adapter
                .getPosition("BTM Layout");
        Log.i(TAG, "areaSpinnerPosition :"+areaSpinnerPosition);
        area_SP.setSelection(1);

    }

    private void disableUIDetails() {

        ticketNumber_ET.setEnabled(false);
        ticketCreatedDate_ET.setEnabled(false);
        firstName_ET.setEnabled(false);
        middleName_ET.setEnabled(false);
        lastName_ET.setEnabled(false);
        mobileNo_ET.setEnabled(false);
        alternateMobileNo_ET.setEnabled(false);
        officeNo_ET.setEnabled(false);
        email_ET.setEnabled(false);
        alternateEmail_ET.setEnabled(false);
        city_ET.setEnabled(false);
        current_addressLine1_ET.setEnabled(false);
        current_addressLine2_ET.setEnabled(false);
        current_addressLandmark_ET.setEnabled(false);
        current_addressPincode_ET.setEnabled(false);
        billing_addressLine1_ET.setEnabled(false);
        billing_addressLine2_ET.setEnabled(false);
        billing_addressLandmark_ET.setEnabled(false);
        billing_addressPincode_ET.setEnabled(false);
        title_SP.setEnabled(false);
        branch_SP.setEnabled(false);
        area_SP.setEnabled(false);

    }


    /*public void activateError(View view) {
        if (!shown) {
            spinner1.setError(ERROR_MSG);

        } else {
            spinner1.setError(null);

        }
        shown = !shown;

    }*/



    private void setData() {
        ArrayList<SalesActivityDetails> salesDemoActivityDetails = new ArrayList<>();
        salesDemoActivityDetails.add(new SalesActivityDetails("Demo"));

        ArrayList<SalesActivityDetails> salesOrderActivityDetails = new ArrayList<>();
        salesOrderActivityDetails.add(new SalesActivityDetails("Demo"));

        salesActivityArrayList.add(new SalesActivity(Constant.SALES_ACTIVITY_DEMO, salesDemoActivityDetails));
        salesActivityArrayList.add(new SalesActivity(Constant.SALES_ACTIVITY_ORDER, salesOrderActivityDetails));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.basicInfo_update_BT_LL:

                Log.i(TAG, "onClick basicInfo_update_BT_LL ");

                validateUserInput();

                break;

            case R.id.calendar_IB:

                Log.i(TAG, "onClick calendar_CN_IB ");

                showDatePickerDialog();

                break;

        }
    }

    private void validateUserInput(){

        Log.i(TAG, "validateUserInput");

        boolean isInputValid = false;

        String ticketId = ticketNumber_ET.getText().toString().trim();
        String createdDate = ticketCreatedDate_ET.getText().toString().trim();
        String title = title_SP.getSelectedItem().toString();
        String firstName = firstName_ET.getText().toString().trim();
        String middleName = middleName_ET.getText().toString().trim();
        String lastName = lastName_ET.getText().toString().trim();
        String mobileNumber = mobileNo_ET.getText().toString().trim();
        String alternateMobileNumber = alternateMobileNo_ET.getText().toString().trim();
        String officeNumber = officeNo_ET.getText().toString().trim();
        String emailId = email_ET.getText().toString().trim();
        String alternateEmailId = alternateEmail_ET.getText().toString().trim();
        String cityName = city_ET.getText().toString().trim();
        String branchName = branch_SP.getSelectedItem().toString();
        String areaName = area_SP.getSelectedItem().toString();
        String currentAddressLine1 = current_addressLine1_ET.getText().toString().trim();
        String currentAddressLine2 = current_addressLine2_ET.getText().toString().trim();
        String currentAddressLandmark = current_addressLandmark_ET.getText().toString().trim();
        String currentAddressPincode = current_addressPincode_ET.getText().toString().trim();
        String billingAddressLine1 = billing_addressLine1_ET.getText().toString().trim();
        String billingAddressLine2 = billing_addressLine2_ET.getText().toString().trim();
        String billingAddressLandmark = billing_addressLandmark_ET.getText().toString().trim();
        String billingAddressPincode = billing_addressPincode_ET.getText().toString().trim();
        String preferredCallTime = preferredCallTime_ET.getText().toString().trim();
        SimpleDateFormat formatter = new SimpleDateFormat(Constant.DATE_FORMATE);

        basicInfoUpdate = new BasicInfoUpdate();

        basicInfoUpdate.setTicketId(Long.parseLong(ticketId));
        //basicInfoUpdate.setCreatedDate(createdDate);

        if (title.equals(context.getResources().getString(
                R.string.title_spinner_hint))) {

            if (context != null) {
                CommonUtility.showToastMessage(context, context
                        .getResources().getString(R.string.select_title));
            }
            isInputValid = false;
            return;

        } else {
            basicInfoUpdate.setTitle(title);

            isInputValid = true;

        }

        if (firstName.isEmpty()) {

            if (context != null) {
                CommonUtility.showToastMessage(context, context
                        .getResources().getString(R.string.enter_firstname));
            }
            isInputValid = false;
            return;

        } else {
            basicInfoUpdate.setFirstName(firstName);

            isInputValid = true;

        }

        basicInfoUpdate.setMiddleName(middleName);

        if (lastName.isEmpty()) {

            if (context != null) {
                CommonUtility.showToastMessage(context, context
                        .getResources().getString(R.string.enter_lastname));
            }
            isInputValid = false;
            return;

        } else {
            basicInfoUpdate.setLastName(lastName);

            isInputValid = true;

        }

        if (mobileNumber.isEmpty()) {

            if (context != null) {
                CommonUtility.showToastMessage(context, context
                        .getResources().getString(R.string.enter_lastname));
            }
            isInputValid = false;
            return;

        } else if(mobileNumber.length()<10){

            if (context != null) {
                CommonUtility.showToastMessage(context, context
                        .getResources().getString(R.string.enter_valid_mobile));
            }
            isInputValid = false;
            return;

        } else{
            basicInfoUpdate.setMobileNumber(mobileNumber);

            isInputValid = true;

        }

        basicInfoUpdate.setAlternateMobileNumber(alternateMobileNumber);

        basicInfoUpdate.setOfficeNumber(officeNumber);

        if (emailId.isEmpty()) {

            if (context != null) {
                CommonUtility.showToastMessage(context, context
                        .getResources().getString(R.string.enter_email));
            }
            isInputValid = false;
            return;

        } else if(!emailId.matches(Constant.EMAIL_RE)){

            if (context != null) {
                CommonUtility.showToastMessage(context, context
                        .getResources().getString(R.string.enter_valid_email));
            }
            isInputValid = false;
            return;

        } else{
            basicInfoUpdate.setEmailId(emailId);

            isInputValid = true;

        }


        basicInfoUpdate.setAlternateEmailId(alternateEmailId);

        //basicInfoUpdate.setCityId(Constant.CITY_BANGALORE_ID);

        if (branchName.equals(context.getResources().getString(
                R.string.branch_spinner_hint))) {

            if (context != null) {
                CommonUtility.showToastMessage(context, context
                        .getResources().getString(R.string.select_branch));
            }
            isInputValid = false;
            return;

        } else {
            //basicInfoUpdate.setBranchId(2L);

            isInputValid = true;

        }

       // basicInfoUpdate.setCityId(Constant.CITY_BANGALORE_ID);

        if (areaName.equals(context.getResources().getString(
                R.string.area_spinner_hint))) {

            if (context != null) {
                CommonUtility.showToastMessage(context, context
                        .getResources().getString(R.string.select_area));
            }
            isInputValid = false;
            return;

        } else {
           // basicInfoUpdate.setBranchId(2L);

            isInputValid = true;

        }

        if (currentAddressLine1.isEmpty()) {

            if (context != null) {
                CommonUtility.showToastMessage(context, context
                        .getResources().getString(R.string.enter_current_address_line1));
            }
            isInputValid = false;
            return;

        } else {

            isInputValid = true;

        }

        if (currentAddressLine2.isEmpty()) {

            if (context != null) {
                CommonUtility.showToastMessage(context, context
                        .getResources().getString(R.string.enter_current_address_line2));
            }
            isInputValid = false;
            return;

        } else {

            isInputValid = true;

        }

        if (currentAddressLandmark.isEmpty()) {

            if (context != null) {
                CommonUtility.showToastMessage(context, context
                        .getResources().getString(R.string.enter_current_address_landmark));
            }
            isInputValid = false;
            return;

        } else {

            isInputValid = true;

        }

        if (currentAddressPincode.isEmpty()) {

            if (context != null) {
                CommonUtility.showToastMessage(context, context
                        .getResources().getString(R.string.enter_current_address_pincode));
            }
            isInputValid = false;
            return;

        } else {

            isInputValid = true;

        }

        AddressVo currentAddressVo = new AddressVo();

        currentAddressVo.setAddress1(currentAddressLine1);
        currentAddressVo.setAddress2(currentAddressLine2);
        currentAddressVo.setLandmark(currentAddressLandmark);
        currentAddressVo.setPincode(currentAddressPincode);

        basicInfoUpdate.setCurrentAddress(currentAddressVo);

        if (billingAddressLine1.isEmpty()) {

            if (context != null) {
                CommonUtility.showToastMessage(context, context
                        .getResources().getString(R.string.enter_billing_address_line1));
            }
            isInputValid = false;
            return;

        } else {

            isInputValid = true;

        }

        if (billingAddressLine2.isEmpty()) {

            if (context != null) {
                CommonUtility.showToastMessage(context, context
                        .getResources().getString(R.string.enter_billing_address_line2));
            }
            isInputValid = false;
            return;

        } else {

            isInputValid = true;

        }

        if (billingAddressLandmark.isEmpty()) {

            if (context != null) {
                CommonUtility.showToastMessage(context, context
                        .getResources().getString(R.string.enter_billing_address_landmark));
            }
            isInputValid = false;
            return;

        } else {

            isInputValid = true;

        }

        if (billingAddressPincode.isEmpty()) {

            if (context != null) {
                CommonUtility.showToastMessage(context, context
                        .getResources().getString(R.string.enter_billing_address_pincode));
            }
            isInputValid = false;
            return;

        } else {

            isInputValid = true;

        }

        AddressVo billingAddressVo = new AddressVo();

        billingAddressVo.setAddress1(billingAddressLine1);
        billingAddressVo.setAddress2(billingAddressLine2);
        billingAddressVo.setLandmark(billingAddressLandmark);
        billingAddressVo.setPincode(billingAddressPincode);

        basicInfoUpdate.setCurrentAddress(billingAddressVo);

        if (preferredCallTime.isEmpty()) {

            if (context != null) {
                CommonUtility.showToastMessage(context, context
                        .getResources().getString(R.string.select_preferred_call_time));
            }
            isInputValid = false;
            return;

        } else {
            /*try {
                basicInfoUpdate.setPreferredCallTime(formatter.parse(preferredCallTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }*/

            isInputValid = true;

        }



        if(isInputValid){

            Log.i(TAG, "isInputValid : "+isInputValid);

            showConfirmationAlertDialog();
        }


    }

    private void showConfirmationAlertDialog() {

        Log.i(TAG, " showConfirmationAlertDialog ");

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        alertDialogBuilder.setMessage(context.getResources().getString(
                R.string.basic_info_upadte_alert_message));
        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.setPositiveButton(
                context.getResources().getString(R.string.alert_button_yes),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        callUpdateLeadService();

                        dialog.cancel();
                    }

                });

        alertDialogBuilder.setNegativeButton(
                context.getResources().getString(R.string.alert_button_no),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDailog = alertDialogBuilder.create();
        alertDailog.show();
    }

    private void callUpdateLeadService(){

        try {

            String jsonData = objectMapper
                    .writeValueAsString(basicInfoUpdate);

            JSONObject jsonObject = new JSONObject(jsonData);

            String host = Webserver.SERVER_HOST;
            String uri = Webserver.SALES_LEAD_UPDATE;

            String url = host + "" + uri;

            Log.i(TAG, "callUpdateLeadService url : " + url
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
                                    "callUpdateLeadService response : "
                                            + response);

                            CommonUtility.showToastMessage(context,"Lead details updated successfully");


                            try {

                                apiResponse = objectMapper.readValue(response.toString(), APIResponse.class);

                                if (apiResponse != null) {

                                    Log.i(TAG, "apiResponse :" + apiResponse.toString());

                                    Object data = apiResponse.getData();

                                    if (data != null) {

                                        String dataString = objectMapper.writeValueAsString(data);

                                        Log.i(TAG, "dataString : "+dataString);
                                    }
                                }

                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                            onBasicInfoUpdate();


                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {

                    Log.e(TAG,
                            "callCreateLeadService onErrorResponse : "
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

    private void onBasicInfoUpdate(){

        sales_activity_LL.setVisibility(View.VISIBLE);
        basicInfo_update_BT_LL.setVisibility(View.GONE);
        disableUIDetails();
    }

    private void showDatePickerDialog() {

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        selectedYear = year;

                        selectedMonth = (monthOfYear + 1);

                        selectedDay = dayOfMonth;

                        timePickerDialog.show();

                    }
                }, mYear, mMonth, mDay);

        datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());

        datePickerDialog.show();

        // c.add(Calendar.HOUR_OF_DAY, minHour);

        mHour = c.get(Calendar.HOUR_OF_DAY);

        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog

        timePickerDialog = new TimePickerDialog(context,
                new TimePickerDialog.OnTimeSetListener() {

                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        Calendar currentCalendar = Calendar.getInstance();

                        int currentDay = currentCalendar
                                .get(Calendar.DAY_OF_MONTH);

                        if (selectedDay == currentDay) {

                            if (hourOfDay < mHour) {

                                CommonUtility.showToastMessage(
                                        context,
                                        context.getResources().getString(
                                                R.string.min_preffered_time));

                                preferredCallTime_ET.setText("");

                                return;

                            } else {
                                if (hourOfDay == mHour) {

                                    if (minute < mMinute) {

                                        if (context != null) {
                                            CommonUtility
                                                    .showToastMessage(
                                                            context,
                                                            context.getResources()
                                                                    .getString(
                                                                            R.string.min_preffered_time));
                                        }

                                        preferredCallTime_ET.setText("");

                                        return;

                                    }
                                }

                                Calendar calendar = Calendar.getInstance();

                                SimpleDateFormat formatter = new SimpleDateFormat(
                                        "dd-MM-yyyy hh:mm:ss");

                                String selectedDateString = selectedDay + "-"
                                        + selectedMonth + "-" + selectedYear
                                        + " " + hourOfDay + ":" + minute
                                        + ":00";

                                try {
                                    calendar.setTime(CommonUtility.addHours(
                                            formatter.parse(selectedDateString),
                                            Constant.PREFFERED_DATE_HOURS_DIFFERENCE));

                                    if (context != null) {
                                        CommonUtility
                                                .showToastMessage(
                                                        context,
                                                        context.getResources()
                                                                .getString(
                                                                        R.string.added_time_for_mq));
                                    }

                                } catch (ParseException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }

                                preferredCallTime_ET.setText(""
                                        + calendar.get(Calendar.DAY_OF_MONTH)
                                        + "/"
                                        + (calendar.get(Calendar.MONTH) + 1)
                                        + "/" + calendar.get(Calendar.YEAR)
                                        + " "
                                        + calendar.get(Calendar.HOUR_OF_DAY)
                                        + ":" + calendar.get(Calendar.MINUTE)
                                        + ":" + "00");

                            }
                        } else {

                            preferredCallTime_ET.setText("" + selectedDay + "/"
                                    + selectedMonth + "/" + selectedYear + " "
                                    + hourOfDay + ":" + minute + ":" + "00");

                        }

                    }
                }, mHour, mMinute, true);

    }
}
