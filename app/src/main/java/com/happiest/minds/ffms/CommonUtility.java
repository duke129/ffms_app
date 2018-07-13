package com.happiest.minds.ffms;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Gravity;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class CommonUtility {

    private static final String TAG = CommonUtility.class.getName();
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    public static ProgressDialog progressDialog;

    public static void showToastMessage(Context context, String message) {

        Toast toast = Toast
                .makeText(context, message, Toast.LENGTH_SHORT);

        toast.setGravity(Gravity.CENTER, Gravity.CENTER, Gravity.CENTER);

        toast.show();
    }

    public static void saveUserName(Context context,
                                    String username) {

        sharedPreferences = context.getSharedPreferences(
                Constant.FWMP_PREFERENCE, Context.MODE_PRIVATE);

        editor = sharedPreferences.edit();

        editor.putString("username", username);

        editor.commit();

    }

    public static String getUserName(Context context) {

        sharedPreferences = context.getSharedPreferences(
                Constant.FWMP_PREFERENCE, Context.MODE_PRIVATE);

        String username = sharedPreferences.getString("username", "");


        return username;

    }

    public static void savePassword(Context context,
                                    String password) {

        sharedPreferences = context.getSharedPreferences(
                Constant.FWMP_PREFERENCE, Context.MODE_PRIVATE);

        editor = sharedPreferences.edit();

        editor.putString("password", password);

        editor.commit();

    }

    public static String getPassword(Context context) {

        sharedPreferences = context.getSharedPreferences(
                Constant.FWMP_PREFERENCE, Context.MODE_PRIVATE);

        String password = sharedPreferences.getString("password", "");


        return password;

    }

    public static void saveSeButtonClickedValue(Context context,
                                                int clickedValue) {

        sharedPreferences = context.getSharedPreferences(
                Constant.FWMP_PREFERENCE, Context.MODE_PRIVATE);

        editor = sharedPreferences.edit();

        editor.putInt("clickedValue", clickedValue);

        editor.commit();

        Log.i(TAG,
                " SE Button clicked value has been saved to shared prefrence : "
                        + clickedValue);

    }

    public static int getSeButtonClickedValue(Context context) {

        sharedPreferences = context.getSharedPreferences(
                Constant.FWMP_PREFERENCE, Context.MODE_PRIVATE);

        int clickedValue = sharedPreferences.getInt("clickedValue", 0);

        Log.i(TAG, "Button clicked has been returned from  SharedPreference : "
                + clickedValue);

        return clickedValue;

    }

    public static void showServerResponseMessage(final Context context,
                                                 String message) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        alertDialogBuilder.setMessage(message);

        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }

                });

        AlertDialog alertDailog = alertDialogBuilder.create();
        alertDailog.show();
    }

    public static Date addHours(Date date, int amount) {
        return add(date, 11, amount);
    }

    public static Date add(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }

    public static void showProgressDailog(Context context, String message) {

        if (context != null) {

            Activity activity = (Activity) context;

            if (!activity.isFinishing()) {

                progressDialog = new ProgressDialog(context);

                progressDialog.setMessage(message);

                progressDialog.setCancelable(false);

                progressDialog.show();

            }
        }
    }

    public static void cancelProgressDailog(Context context) {

        if (progressDialog != null && progressDialog.isShowing()) {

            progressDialog.cancel();
        }
    }


    public static void saveSelectedProductId(Context context,
                                                String modelId) {

        sharedPreferences = context.getSharedPreferences(
                Constant.FWMP_PREFERENCE, Context.MODE_PRIVATE);

        editor = sharedPreferences.edit();

        editor.putString("modelId", modelId);

        editor.commit();

        Log.i(TAG,
                "Selected model id has been saved to shared prefrence : "
                        + modelId);

    }

    public static String getSelectedProductId(Context context) {

        sharedPreferences = context.getSharedPreferences(
                Constant.FWMP_PREFERENCE, Context.MODE_PRIVATE);

        String modelId = sharedPreferences.getString("modelId", null);

        Log.i(TAG, "Selected model id returned from  SharedPreference : "
                + modelId);

        return modelId;

    }

    public static void saveTicketId(Context context,
                                             String ticketId) {

        sharedPreferences = context.getSharedPreferences(
                Constant.FWMP_PREFERENCE, Context.MODE_PRIVATE);

        editor = sharedPreferences.edit();

        editor.putString("ticketId", ticketId);

        editor.commit();

        Log.i(TAG,
                "Selected ticketId id has been saved to shared prefrence : "
                        + ticketId);

    }

    public static String getTicketId(Context context) {

        sharedPreferences = context.getSharedPreferences(
                Constant.FWMP_PREFERENCE, Context.MODE_PRIVATE);

        String ticketId = sharedPreferences.getString("ticketId", null);

        Log.i(TAG, "Selected ticketId  returned from  SharedPreference : "
                + ticketId);

        return ticketId;

    }

    public static void saveOrderStatus(Context context,
                                    String ticketId) {

        sharedPreferences = context.getSharedPreferences(
                Constant.FWMP_PREFERENCE, Context.MODE_PRIVATE);

        editor = sharedPreferences.edit();

        editor.putInt(ticketId, Constant.COMPLETED);

        editor.commit();

        Log.i(TAG,
                "Order Status for ticketId "+ticketId+" saved" );

    }

    public static int getOrderStatus(Context context,String ticketId) {

        sharedPreferences = context.getSharedPreferences(
                Constant.FWMP_PREFERENCE, Context.MODE_PRIVATE);

        int orderStatus = sharedPreferences.getInt(ticketId, 0);

        Log.i(TAG, "Order Status for ticketId : "+ticketId+" orderStatus : "+orderStatus);

        return orderStatus;

    }
}
