package com.happiest.minds.ffms.sales;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.happiest.minds.ffms.R;
import com.happiest.minds.ffms.sales.pojo.ActivityChild;
import com.happiest.minds.ffms.sales.pojo.SalesActivityName;
import com.happiest.minds.ffms.sales.pojo.SalesActivityType;
import com.happiest.minds.ffms.sales.pojo.SpinnerItems;
import com.happiest.minds.ffms.sales.pojo.TicketDetails;

import java.util.ArrayList;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;


public class SalesTicketDetailsFragment extends Fragment {

    private static final String TAG = SalesTicketDetailsFragment.class.getSimpleName();

    View view;

    private RecyclerView recyclerView;

    private LinearLayoutManager layoutManager;

    SalesActivityExpandableAdapter salesActivityExpandableAdapter;

    List<SalesActivityName> salesActivityNames;

    private static final String ERROR_MSG = "Very very very long error message to get scrolling or multiline animation when the error button is clicked";
    MaterialSpinner title_SP, branch_SP, area_SP;
    EditText ticketNumber_ET, ticketCreatedDate_ET, firstName_ET, middleName_ET, lastName_ET, mobileNo_ET, alternateMobileNo_ET, officeNo_ET, email_ET, aternateEmail_ET, city_ET, current_addressLine1_ET, current_addressLine2_ET, current_addressLandmark_ET, current_addressPincode_ET, billing_addressLine1_ET, billing_addressLine2_ET, billing_addressLandmark_ET, billing_addressPincode_ET;
    private ArrayAdapter<String> title_adapter, branch_adapter, area_adapter;

    private TicketDetails ticketDetails = SalesLeadsCardViewRecyclerAdapter.ticketDetailsArrayList.get(0);


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sales_ticket_details, container, false);

        initGUI();

        initRecycler();

        //initSpinner();

        setDetailsToUI();

        return view;
    }

    public void initGUI() {
        ticketNumber_ET = view.findViewById(R.id.ticketNumber_ET);
        ticketCreatedDate_ET = view.findViewById(R.id.ticketCreatedDate_ET);
        firstName_ET = view.findViewById(R.id.firstName_ET);
        middleName_ET = view.findViewById(R.id.middleName_CN_ET);
        lastName_ET = view.findViewById(R.id.lastName_ET);
        mobileNo_ET = view.findViewById(R.id.mobileNo_ET);
        alternateMobileNo_ET = view.findViewById(R.id.alternateMobileNo_ET);
        officeNo_ET = view.findViewById(R.id.officeNo_ET);
        email_ET = view.findViewById(R.id.email_CN_ET);
        aternateEmail_ET = view.findViewById(R.id.aternateEmail_ET);
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

    }

    public void initRecycler() {


        recyclerView = (RecyclerView) view.findViewById(R.id.activity_recycler_view);

        recyclerView.setHasFixedSize(true);

        salesActivityExpandableAdapter = new SalesActivityExpandableAdapter(
                getActivity(), generateSalesTicketTypes());

        salesActivityExpandableAdapter
                .setExpandCollapseListener(new ExpandableRecyclerAdapter.ExpandCollapseListener() {

                    @Override
                    public void onListItemCollapsed(int position) {
                        // TODO Auto-generated method stub

                        recyclerView
                                .setAdapter(salesActivityExpandableAdapter);

                    }

                    @Override
                    public void onListItemExpanded(int position) {
                        // TODO Auto-generated method stub

                        SalesActivityExpandableAdapter.flag = position;

                        for (int idx = 0; idx < salesActivityNames.size(); idx++) {
                            if (idx != position && idx < salesActivityNames.size()) {
                                salesActivityExpandableAdapter
                                        .collapseParent(idx);
                                // position = t1.indexOf(expandedTicketType);
                            }

                        }
                    }
                });
        recyclerView.setAdapter(salesActivityExpandableAdapter);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setVisibility(View.VISIBLE);
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


    /*public void activateError(View view) {
        if (!shown) {
            spinner1.setError(ERROR_MSG);

        } else {
            spinner1.setError(null);

        }
        shown = !shown;

    }*/


}
