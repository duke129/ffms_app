package com.happiest.minds.ffms;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.happiest.minds.ffms.sales.SalesHomeActivity;
import com.happiest.minds.ffms.sr.ServiceRequestHomeActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = AppCompatActivity.class.getName();
    Context context;
    Toast toast;

    TextView username_ET, password_ET;
    Button login_BT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);*/

        context = this;

        initGUI();
    }

    private void initGUI(){

        username_ET = (EditText) findViewById(R.id.username_ET);
        password_ET =  (EditText) findViewById(R.id.password_ET);
        login_BT = (Button) findViewById(R.id.login_BT);
        login_BT.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.login_BT :

                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                validateLoginCredential();
        }

    }

    public void validateLoginCredential(){

        String username = username_ET.getText().toString().trim();
        String password = password_ET.getText().toString().trim();

        username_ET.setText("");
        password_ET.setText("");

        if (username.equals(""))
        {

            CommonUtility.showToastMessage(context, context.getString(R.string.username_empty));

            Log.i(TAG, ""+ context.getString(R.string.username_empty));

            return;

        } else if (password.equals(""))
        {

            CommonUtility.showToastMessage(context, context.getString(R.string.password_empty));

            Log.i(TAG, ""+ context.getString(R.string.password_empty));

            return;

        }else if(! username.equals(password)){

            CommonUtility.showToastMessage(context, context.getString(R.string.invalid_credential));

            Log.i(TAG, ""+ context.getString(R.string.invalid_credential));

            return;

        }else if(username.equals(context.getString(R.string.se_user))){

            redirectToSalesHomePage();

        }


    }

    private void redirectToSalesHomePage(){


        Intent salesHomeRedirectIntent = new Intent(context, SalesHomeActivity.class);
        startActivity(salesHomeRedirectIntent);

        finish();
    }

    private void redirectToServiceRequestHomePage(){


        Intent serviceRequestHomeRedirectIntent = new Intent(context, ServiceRequestHomeActivity.class);
        startActivity(serviceRequestHomeRedirectIntent);

        finish();
    }


}
