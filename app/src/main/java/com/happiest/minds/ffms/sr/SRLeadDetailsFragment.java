package com.happiest.minds.ffms.sr;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.happiest.minds.ffms.R;
import com.happiest.minds.ffms.sales.SalesActivityExpandableAdapter;
import com.happiest.minds.ffms.sales.SalesLeadsCardViewRecyclerAdapter;
import com.happiest.minds.ffms.sales.pojo.TicketDetails;

import java.util.ArrayList;

import fr.ganfra.materialspinner.MaterialSpinner;

public class SRLeadDetailsFragment extends Fragment {

	private static final String TAG = SRLeadDetailsFragment.class.getSimpleName();
	View view;

	private static final String ERROR_MSG = "Very very very long error message to get scrolling or multiline animation when the error button is clicked";
	MaterialSpinner title_SP, branch_SP, area_SP;
	EditText ticketNumber_ET, ticketCreatedDate_ET, firstName_ET, middleName_ET, lastName_ET, mobileNo_ET, alternateMobileNo_ET, officeNo_ET, email_ET, aternateEmail_ET, city_ET, current_addressLine1_ET, current_addressLine2_ET, current_addressLandmark_ET, current_addressPincode_ET, billing_addressLine1_ET, billing_addressLine2_ET, billing_addressLandmark_ET, billing_addressPincode_ET;
	private ArrayAdapter<String> title_adapter, branch_adapter, area_adapter;

	private TicketDetails ticketDetails = SRLeadsCardViewRecyclerAdapter.ticketDetailsArrayList.get(0);

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.fragment_sr_lead_details, container, false);

		initGUI();

		setDetailsToUI();

		return view;


	}

	public void initGUI() {
		ticketNumber_ET = view.findViewById(R.id.ticketNumber_SR_ET);
		ticketCreatedDate_ET = view.findViewById(R.id.ticketCreatedDate_SR_ET);
		firstName_ET = view.findViewById(R.id.firstName_SR_ET);
		middleName_ET = view.findViewById(R.id.middleName_CN_SR_ET);
		lastName_ET = view.findViewById(R.id.lastName_SR_ET);
		mobileNo_ET = view.findViewById(R.id.mobileNo_SR_ET);
		alternateMobileNo_ET = view.findViewById(R.id.alternateMobileNo_SR_ET);
		officeNo_ET = view.findViewById(R.id.officeNo_SR_ET);
		email_ET = view.findViewById(R.id.email_CN_SR_ET);
		aternateEmail_ET = view.findViewById(R.id.aternateEmail_SR_ET);
		city_ET = view.findViewById(R.id.city_SR_ET);
		current_addressLine1_ET = view.findViewById(R.id.current_addressLine1_SR_ET);
		current_addressLine2_ET = view.findViewById(R.id.current_addressLine2_SR_ET);
		current_addressLandmark_ET = view.findViewById(R.id.current_addressLandmark_SR_ET);
		current_addressPincode_ET = view.findViewById(R.id.current_addressPincode_SR_ET);
		billing_addressLine1_ET = view.findViewById(R.id.billing_addressLine1_SR_ET);
		billing_addressLine2_ET = view.findViewById(R.id.billing_addressLine2_SR_ET);
		billing_addressLandmark_ET = view.findViewById(R.id.billing_addressLandmark_SR_ET);
		billing_addressPincode_ET = view.findViewById(R.id.billing_addressPincode_SR_ET);
		title_SP = view.findViewById(R.id.title_SR_SP);
		branch_SP = view.findViewById(R.id.branch_SR_SP);
		area_SP = view.findViewById(R.id.area_SR_SP);

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
		aternateEmail_ET.setText(ticketDetails.getAlternateEmailId());
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

		disableUIDetails();
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
		aternateEmail_ET.setEnabled(false);
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

}
