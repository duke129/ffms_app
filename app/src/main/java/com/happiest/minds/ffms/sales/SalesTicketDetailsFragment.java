package com.happiest.minds.ffms.sales;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.happiest.minds.ffms.R;
import com.happiest.minds.ffms.sales.pojo.ActivityChild;
import com.happiest.minds.ffms.sales.pojo.SalesActivityName;
import com.happiest.minds.ffms.sales.pojo.SalesActivityType;
import com.happiest.minds.ffms.sales.pojo.SpinnerItems;

import java.util.ArrayList;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;


public class SalesTicketDetailsFragment extends Fragment {

    View view;

    private RecyclerView recyclerView;

    private LinearLayoutManager layoutManager;

    SalesActivityExpandableAdapter salesActivityExpandableAdapter;

    List<SalesActivityName> salesActivityNames;

    private static final String ERROR_MSG = "Very very very long error message to get scrolling or multiline animation when the error button is clicked";
    MaterialSpinner title_SP, branch_SP, area_SP;
    private ArrayAdapter<String> title_adapter, branch_adapter, area_adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sales_ticket_details, container, false);

        initRecycler();

        initSpinner();

        return view;
    }

    public void initRecycler(){


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

    private void initSpinner(){

        /*title spinner*/
        title_adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, SpinnerItems.TITLE_ITEMS);
        title_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        title_SP = view.findViewById(R.id.title_SP);
        title_SP.setAdapter(title_adapter);
        title_SP.setPaddingSafe(0, 0, 0, 0);

        branch_adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, SpinnerItems.BRANCH_ITEMS);
        branch_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branch_SP = view.findViewById(R.id.branch_SP);
        branch_SP.setAdapter(branch_adapter);
        branch_SP.setPaddingSafe(0, 0, 0, 0);

        area_adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, SpinnerItems.AREA_ITEMS);
        area_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        area_SP = view.findViewById(R.id.area_SP);
        area_SP.setAdapter(area_adapter);
        area_SP.setPaddingSafe(0, 0, 0, 0);


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
