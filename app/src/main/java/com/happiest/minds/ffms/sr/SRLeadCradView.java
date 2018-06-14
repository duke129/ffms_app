package com.happiest.minds.ffms.sr;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.happiest.minds.ffms.CommonUtility;
import com.happiest.minds.ffms.R;


public class SRLeadCradView extends Fragment {

    private static final String TAG = SRLeadCradView.class
            .getSimpleName();

    Context context;

    View view;

    private RecyclerView recyclerView;

    SRLeadsCardViewRecyclerAdapter srLeadsCardViewRecyclerAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        // Inflate the layout for this fragment
        view = inflater.inflate(
                R.layout.fragment_sr_leads_card_view,
                container, false);

        context = getActivity().getApplicationContext();


        // InitGui
        initGUI();

        return view;
    }

    private void initGUI() {
        // TODO Auto-generated method stub

        /* Initialize recyclerview */
        recyclerView = (RecyclerView) view
                .findViewById(R.id.sr_leads_recycler_view);

        int buttonClickedValue = CommonUtility
                .getSeButtonClickedValue(context);

       /* if (buttonClickedValue == Constant.SEARCH_LEADS) {

           //get serached data

        } else {

            //data from db

        }*/

        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        srLeadsCardViewRecyclerAdapter = new SRLeadsCardViewRecyclerAdapter(getActivity(), ServiceRequestHomeActivity.ticketCardViewDataList);

        // create an Object for Adapter


        // set the adapter object to the Recyclerview
        recyclerView.setAdapter(srLeadsCardViewRecyclerAdapter);

    }
}
