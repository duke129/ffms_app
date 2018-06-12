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

    public static void showToastMessage(Context context, String message){

       Toast toast = Toast
                .makeText(context, message, Toast.LENGTH_SHORT);

        toast.setGravity(Gravity.CENTER, Gravity.CENTER, Gravity.CENTER);

        toast.show();
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
}
