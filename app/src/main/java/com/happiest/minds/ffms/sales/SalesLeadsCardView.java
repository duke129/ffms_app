package com.happiest.minds.ffms.sales;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;

import com.happiest.minds.ffms.CommonUtility;
import com.happiest.minds.ffms.R;
import com.happiest.minds.ffms.sales.pojo.SalesCardViewPojo;


public class SalesLeadsCardView extends Fragment {


    private static final String TAG = SalesLeadsCardView.class
            .getSimpleName();

    Context context;

    View view;

    private RecyclerView mRecyclerView;


    SalesLeadsCardViewRecyclerAdapter salesCardViewRecyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        // Inflate the layout for this fragment
        view = inflater.inflate(
                R.layout.fragment_sales_leads_card_view,
                container, false);

        context = getActivity().getApplicationContext();


        // InitGui
        initGUI();

        return view;
    }

    private void initGUI() {
        // TODO Auto-generated method stub

        /* Initialize recyclerview */
        mRecyclerView = (RecyclerView) view
                .findViewById(R.id.se_leads_recycler_view);

        int buttonClickedValue = CommonUtility
                .getSeButtonClickedValue(context);

       /* if (buttonClickedValue == Constant.SEARCH_LEADS) {

           //get serached data

        } else {

            //data from db

        }*/

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<SalesCardViewPojo> salesCardViewPojoArrayList = generateDummyData();

        Log.i(TAG, "cardview count :: " + salesCardViewPojoArrayList.size());

        salesCardViewRecyclerAdapter = new SalesLeadsCardViewRecyclerAdapter(context, salesCardViewPojoArrayList);

        // create an Object for Adapter


        // set the adapter object to the Recyclerview
        mRecyclerView.setAdapter(salesCardViewRecyclerAdapter);

    }

    private ArrayList<SalesCardViewPojo> generateDummyData() {

        ArrayList<SalesCardViewPojo> salesCardViewPojoArrayList = new ArrayList<SalesCardViewPojo>();

        for (int i = 0; i <= 5; i++) {

            SalesCardViewPojo salesCardViewPojo = new SalesCardViewPojo();

            salesCardViewPojo.setAddress("ABC");
            salesCardViewPojo.setCreatedDate("" + new Date());
            salesCardViewPojo.setEtr("" + new Date());
            salesCardViewPojo.setCustomerName("XYZ");
            salesCardViewPojo.setMobileNo("9876543210");
            salesCardViewPojo.setProspectNo("123");

            salesCardViewPojoArrayList.add(salesCardViewPojo);
        }

        return salesCardViewPojoArrayList;
    }


}
