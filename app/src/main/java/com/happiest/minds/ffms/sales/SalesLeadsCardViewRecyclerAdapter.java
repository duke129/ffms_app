package com.happiest.minds.ffms.sales;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import com.happiest.minds.ffms.R;
import com.happiest.minds.ffms.sales.pojo.SalesCardViewPojo;

public class SalesLeadsCardViewRecyclerAdapter extends RecyclerView.Adapter<SalesLeadsCardViewRecyclerAdapter.SalesViewHolder>  {

    private static final String TAG =  SalesLeadsCardViewRecyclerAdapter.class.getSimpleName();

    private ArrayList<SalesCardViewPojo> salesCardViewPojoArrayList;

    Context context;

    public SalesLeadsCardViewRecyclerAdapter(Context contextCons, ArrayList<SalesCardViewPojo> salesCardViewPojoArrayListCons){


        context = contextCons;

        salesCardViewPojoArrayList = salesCardViewPojoArrayListCons;

        Log.i(TAG, "salesCardViewPojoArrayList :"+salesCardViewPojoArrayList.toString());


    }

    @Override
    public SalesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.sales_leads_ticket_card_view_layout,
                parent, false);

        SalesViewHolder seHeaderButtonTicketListCardViewHolder = new SalesViewHolder(
                v);

        Log.i(TAG, "onCreateViewHolder");

        return seHeaderButtonTicketListCardViewHolder;
    }

    @Override
    public void onBindViewHolder(SalesViewHolder holder, int position) {

        holder.setIsRecyclable(false);

        holder.prospectNo_CV_TV.setText(salesCardViewPojoArrayList.get(
                position).getProspectNo());

        holder.address_CV_TV.setText(salesCardViewPojoArrayList.get(
                position).getAddress());

        holder.createdDate_CV_TV.setText(salesCardViewPojoArrayList.get(
                position).getCreatedDate());

        holder.mobileNo_CV_TV.setText(salesCardViewPojoArrayList.get(
                position).getMobileNo());

        holder.customerName_CV_TV.setText(salesCardViewPojoArrayList.get(
                position).getCustomerName());

    }

    @Override
    public int getItemCount() {
        return salesCardViewPojoArrayList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class SalesViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        TextView prospectNo_CV_TV, customerName_CV_TV, mobileNo_CV_TV,
                createdDate_CV_TV, address_CV_TV;

        SalesViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.se_card_view);

            prospectNo_CV_TV = (TextView) itemView
                    .findViewById(R.id.prospectNo_CV_TV);
            customerName_CV_TV = (TextView) itemView
                    .findViewById(R.id.customerName_CV_TV);
            mobileNo_CV_TV = (TextView) itemView
                    .findViewById(R.id.mobileNo_CV_TV);

            createdDate_CV_TV = (TextView) itemView
                    .findViewById(R.id.createdDate_CV_TV);

            address_CV_TV = (TextView) itemView
                    .findViewById(R.id.address_CV_TV);


            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Log.i(TAG, "Card view clicked");
                }
            });
        }
    }
}
