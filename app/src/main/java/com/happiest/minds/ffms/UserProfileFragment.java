package com.happiest.minds.ffms;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class UserProfileFragment extends Fragment implements View.OnClickListener {


    private static final String TAG = UserProfileFragment.class.getName();

    View view;

    Context context;

    LinearLayout logoutButton_LL, changePasswordButton_LL,
            changePasswordDetails_LL, changePasswordSubmit_LL,
            changePasswordCancel_LL;

    TextView userFullName_TV, roleValue_TV, branchValue_TV, cityValue_TV;

    EditText oldPassword_ET, newPassword_ET, confirmNewPassword_ET;


    boolean changePassword;

    String oldPassword, newPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        view = inflater.inflate(R.layout.fragment_user_profile, container,
                false);


        changePassword = true;

        initGUI();

        return view;
    }

    private void initGUI() {

        userFullName_TV = (TextView) view.findViewById(R.id.userFullName_TV);

        logoutButton_LL = (LinearLayout) view
                .findViewById(R.id.logoutButton_LL);

        logoutButton_LL.setClickable(true);

        roleValue_TV = (TextView) view.findViewById(R.id.roleValue_TV);

        branchValue_TV = (TextView) view.findViewById(R.id.branchValue_TV);

        cityValue_TV = (TextView) view.findViewById(R.id.cityValue_TV);

        changePasswordButton_LL = (LinearLayout) view
                .findViewById(R.id.changePasswordButton_LL);

        changePasswordDetails_LL = (LinearLayout) view
                .findViewById(R.id.changePasswordDetails_LL);

        oldPassword_ET = (EditText) view.findViewById(R.id.oldPassword_ET);

        newPassword_ET = (EditText) view.findViewById(R.id.newPassword_ET);

        confirmNewPassword_ET = (EditText) view
                .findViewById(R.id.confirmNewPassword_ET);

        changePasswordSubmit_LL = (LinearLayout) view
                .findViewById(R.id.changePasswordSubmit_LL);

        changePasswordCancel_LL = (LinearLayout) view
                .findViewById(R.id.changePasswordCancel_LL);

        changePasswordButton_LL.setOnClickListener(this);

        changePasswordSubmit_LL.setOnClickListener(this);

        changePasswordCancel_LL.setOnClickListener(this);

        logoutButton_LL.setOnClickListener(this);

        setUIValues();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        this.context = activity;
    }

    private void setUIValues() {

        if (context != null) {
            userFullName_TV
                    .setText("HM User");

            roleValue_TV.setText("Sales Executive");

            branchValue_TV.setText("Madiwala");

            cityValue_TV.setText("Bangalore");
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.logoutButton_LL:

                Log.i(TAG, " clicked on logoutButton_LL ");

                redirectToLoginPage();

                break;

            case R.id.changePasswordButton_LL:

                if (changePassword) {
                    changePasswordDetails_LL.setVisibility(View.VISIBLE);

                    changePassword = false;

                } else {
                    oldPassword_ET.setText("");

                    newPassword_ET.setText("");

                    confirmNewPassword_ET.setText("");

                    changePasswordDetails_LL.setVisibility(View.GONE);

                    changePassword = true;

                }

                break;

            case R.id.changePasswordSubmit_LL:


                break;

            case R.id.changePasswordCancel_LL:

                oldPassword_ET.setText("");

                newPassword_ET.setText("");

                confirmNewPassword_ET.setText("");

                changePasswordDetails_LL.setVisibility(View.GONE);

                changePassword = true;

                break;

        }

    }


    private void redirectToLoginPage() {
        logoutButton_LL.setClickable(false);

        if (isAdded() && context != null) {

            /*
             * CommonUtilities.showToastMessage(context, context.getResources()
             * .getString(R.string.logged_out_successfully));
             */

          //  CommonUtilities.removeAutoLogin(context);

            Intent logoutIntent = new Intent(context, LoginActivity.class);

            startActivity(logoutIntent);

            if (getActivity() != null) {

                getActivity().finish();
            }

        } else {
            Log.i(TAG, "Context object is null");
        }
    }






}
