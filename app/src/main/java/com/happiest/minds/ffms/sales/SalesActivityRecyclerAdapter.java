package com.happiest.minds.ffms.sales;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.happiest.minds.ffms.R;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class SalesActivityRecyclerAdapter extends ExpandableRecyclerViewAdapter<SalesActivityViewHolder, SalesActivityDetailsViewHolder> {

    private Activity activity;

    public SalesActivityRecyclerAdapter(Activity activity, List<? extends ExpandableGroup> groups) {
        super(groups);
        this.activity = activity;
    }

    @Override
    public SalesActivityViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.sales_activty_view_holder, parent, false);

        return new SalesActivityViewHolder(view);
    }

    @Override
    public SalesActivityDetailsViewHolder onCreateChildViewHolder(ViewGroup parent, final int viewType) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.sales_activity_details_view_holder, parent, false);

        return new SalesActivityDetailsViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(SalesActivityDetailsViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final SalesActivityDetails salesActivityDetails = ((SalesActivity) group).getItems().get(childIndex);
        holder.onBind(salesActivityDetails,group);
    }

    @Override
    public void onBindGroupViewHolder(SalesActivityViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setActivityName(group);
    }
}
