package com.happiest.minds.ffms;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

public class CommonUtility {

    private static final String TAG = CommonUtility.class.getName();
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;

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
}
