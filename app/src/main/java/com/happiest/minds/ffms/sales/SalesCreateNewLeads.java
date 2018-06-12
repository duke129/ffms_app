package com.happiest.minds.ffms.sales;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.happiest.minds.ffms.CommonUtility;
import com.happiest.minds.ffms.Constant;
import com.happiest.minds.ffms.FFMSRequestQueue;
import com.happiest.minds.ffms.R;
import com.happiest.minds.ffms.Webserver;
import com.happiest.minds.ffms.sales.pojo.CustomerVo;
import com.happiest.minds.ffms.sales.pojo.ProspectCreation;
import com.happiest.minds.ffms.sales.pojo.SpinnerItems;
import com.happiest.minds.ffms.sales.pojo.TicketCardViewData;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import fr.ganfra.materialspinner.MaterialSpinner;


public class SalesCreateNewLeads extends Fragment implements View.OnClickListener {

    private static final String TAG = SalesCreateNewLeads.class.getSimpleName();
    View view;
    Context context;
    MaterialSpinner title_CN_SP, branch_CN_SP, area_CN_SP;
    EditText firstName_CN_ET, middleName_CN_ET, lastName_CN_ET,
            mobileNo_CN_ET, alternateMobileNo_ET, officeNo_CN_ET,
            email_CN_ET, alternateEmail_CN_ET, city_CN_ET,
            communication_address_CN_ET, preferredCallTime_CN_ET;
    Button submit_CN_BT;
    ImageButton calendar_IB;
    private int mYear, mMonth, mDay, mHour, mMinute;
    Date dateValueSelected;
    int selectedYear, selectedMonth, selectedDay;
    TimePickerDialog timePickerDialog;
    ArrayAdapter title_adapter, branch_adapter, area_adapter;

    FFMSRequestQueue ffmsRequestQueue;
    ObjectMapper objectMapper;
    ProspectCreation prospectCreation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_sales_create_new_leads, container, false);

        context = getActivity();

        ffmsRequestQueue = FFMSRequestQueue.getInstance(context);
        objectMapper = new ObjectMapper();

        initGUI();

        initSpinner();

        return view;

    }

    private void initGUI(){


         title_CN_SP = view.findViewById(R.id.title_CN_SP);
         branch_CN_SP = view.findViewById(R.id.branch_CN_SP);
         area_CN_SP = view.findViewById(R.id.area_CN_SP);

        firstName_CN_ET = view.findViewById(R.id.firstName_CN_ET);
        middleName_CN_ET = view.findViewById(R.id.middleName_CN_ET);
        lastName_CN_ET = view.findViewById(R.id.lastName_CN_ET);
        mobileNo_CN_ET = view.findViewById(R.id.mobileNo_CN_ET);
        alternateMobileNo_ET = view.findViewById(R.id.alternateMobileNo_ET);
        officeNo_CN_ET = view.findViewById(R.id.officeNo_CN_ET);
        email_CN_ET = view.findViewById(R.id.email_CN_ET);
        alternateEmail_CN_ET = view.findViewById(R.id.alternateEmail_CN_ET);
        city_CN_ET = view.findViewById(R.id.city_CN_ET);
        communication_address_CN_ET = view.findViewById(R.id.communication_address_CN_ET);
        preferredCallTime_CN_ET = view.findViewById(R.id.preferredCallTime_CN_ET);

        calendar_IB = view.findViewById(R.id.calendar_IB);
        submit_CN_BT = view.findViewById(R.id.submit_CN_BT);

        calendar_IB.setOnClickListener(this);
        submit_CN_BT.setOnClickListener(this);



    }

    private void initSpinner() {

        /*title spinner*/
        title_adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, SpinnerItems.TITLE_ITEMS);
        title_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        title_CN_SP.setAdapter(title_adapter);
        title_CN_SP.setPaddingSafe(0, 0, 0, 0);

        branch_adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, SpinnerItems.BRANCH_ITEMS);
        branch_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branch_CN_SP.setAdapter(branch_adapter);
        branch_CN_SP.setPaddingSafe(0, 0, 0, 0);

        area_adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, SpinnerItems.AREA_ITEMS);
        area_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        area_CN_SP.setAdapter(area_adapter);
        area_CN_SP.setPaddingSafe(0, 0, 0, 0);


    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.submit_CN_BT:

                validateInputFields();

                return;

            case R.id.calendar_IB:

                showDatePickerDialog();

                return;
        }

    }

    private void validateInputFields(){

        boolean isInputValid = false;

        String title = title_CN_SP.getSelectedItem().toString();
        String firstName = firstName_CN_ET.getText().toString().trim();
        String middleName = middleName_CN_ET.getText().toString().trim();
        String lastName = lastName_CN_ET.getText().toString().trim();
        String mobileNumber = mobileNo_CN_ET.getText().toString().trim();
        String alternateMobileNumber = alternateMobileNo_ET.getText().toString().trim();
        String officeNumber = officeNo_CN_ET.getText().toString().trim();
        String emailId = email_CN_ET.getText().toString().trim();
        String alternateEmailId = alternateEmail_CN_ET.getText().toString().trim();
        String cityName = city_CN_ET.getText().toString().trim();
        String branchName = branch_CN_SP.getSelectedItem().toString();
        String areaName = area_CN_SP.getSelectedItem().toString();
        String communicationAddress = communication_address_CN_ET.getText().toString().trim();
        String preferredCallTime = preferredCallTime_CN_ET.getText().toString().trim();

        SimpleDateFormat formatter = new SimpleDateFormat(Constant.DATE_FORMATE);
        CustomerVo customerVo = new CustomerVo();
        prospectCreation = new ProspectCreation();


        if (title.equals(context.getResources().getString(
                R.string.title_spinner_hint))) {

            if (context != null) {
                CommonUtility.showToastMessage(context, context
                        .getResources().getString(R.string.select_title));
            }
            isInputValid = false;
            return;

        } else {
            customerVo.setCustomerTittle(title);

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
            customerVo.setCustomerFirstName(firstName);

            isInputValid = true;

        }

        customerVo.setCustomerMiddletName(middleName);

        if (lastName.isEmpty()) {

            if (context != null) {
                CommonUtility.showToastMessage(context, context
                        .getResources().getString(R.string.enter_lastname));
            }
            isInputValid = false;
            return;

        } else {
            customerVo.setCustomerLastName(lastName);

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
            customerVo.setCustomerMobileNumber(mobileNumber);

            isInputValid = true;

        }

        customerVo.setCustomerAternateMobileNumber(alternateMobileNumber);

        customerVo.setCustomerOfficeNumber(officeNumber);

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
            customerVo.setCustomerEmailId(emailId);

            isInputValid = true;

        }


        customerVo.setCustomerAternateEmailId(alternateEmailId);

        customerVo.setCityId(Constant.CITY_BANGALORE_ID);

        if (branchName.equals(context.getResources().getString(
                R.string.branch_spinner_hint))) {

            if (context != null) {
                CommonUtility.showToastMessage(context, context
                        .getResources().getString(R.string.select_branch));
            }
            isInputValid = false;
            return;

        } else {
            customerVo.setBranchId(2L);

            isInputValid = true;

        }

        customerVo.setCityId(Constant.CITY_BANGALORE_ID);

        if (areaName.equals(context.getResources().getString(
                R.string.area_spinner_hint))) {

            if (context != null) {
                CommonUtility.showToastMessage(context, context
                        .getResources().getString(R.string.select_area));
            }
            isInputValid = false;
            return;

        } else {
            customerVo.setBranchId(2L);

            isInputValid = true;

        }

        if (communicationAddress.isEmpty()) {

            if (context != null) {
                CommonUtility.showToastMessage(context, context
                        .getResources().getString(R.string.enter_communication_address));
            }
            isInputValid = false;
            return;

        } else {
            customerVo.setCustomerCommunicationAddress(communicationAddress);

            isInputValid = true;

        }

        if (preferredCallTime.isEmpty()) {

            if (context != null) {
                CommonUtility.showToastMessage(context, context
                        .getResources().getString(R.string.select_preferred_call_time));
            }
            isInputValid = false;
            return;

        } else {
            try {
                prospectCreation.setPrefferdCallTime(formatter.parse(preferredCallTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            isInputValid = true;

        }



        if(isInputValid){

            prospectCreation.setCustomer(customerVo);

            showConfirmationAlertDialog();
        }


    }

    private void callCreateLeadService(){

        try {

            String jsonData = objectMapper
                    .writeValueAsString(prospectCreation);

            JSONObject jsonObject = new JSONObject(jsonData);

            String host = Webserver.SERVER_HOST;
            String uri = Webserver.SALES_LEAD_CREATE;

            String url = host + "" + uri;

            Log.i(TAG, "callCreateLeadService url : " + url
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
                                    "callCreateLeadService response : "
                                            + response);

                            CommonUtility.showToastMessage(context,"Leads Created Successfully");

                            resetForm();


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

    private void resetForm(){

        initSpinner();
        firstName_CN_ET.setText("");
        middleName_CN_ET.setText("");
        lastName_CN_ET.setText("");
        mobileNo_CN_ET.setText("");
        alternateMobileNo_ET.setText("");
        officeNo_CN_ET.setText("");
        email_CN_ET.setText("");
        alternateEmail_CN_ET.setText("");
        city_CN_ET.setText("");
        communication_address_CN_ET.setText("");
        preferredCallTime_CN_ET.setText("");

    }

    private void showConfirmationAlertDialog() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        alertDialogBuilder.setMessage(context.getResources().getString(
                R.string.lead_create_alert_message));
        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.setPositiveButton(
                context.getResources().getString(R.string.alert_button_yes),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        callCreateLeadService();

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

                                preferredCallTime_CN_ET.setText("");

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

                                        preferredCallTime_CN_ET.setText("");

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

                                preferredCallTime_CN_ET.setText(""
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

                            preferredCallTime_CN_ET.setText("" + selectedDay + "/"
                                    + selectedMonth + "/" + selectedYear + " "
                                    + hourOfDay + ":" + minute + ":" + "00");

                        }

                    }
                }, mHour, mMinute, true);

    }
}
