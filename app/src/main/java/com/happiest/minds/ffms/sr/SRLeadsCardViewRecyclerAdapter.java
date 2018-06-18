package com.happiest.minds.ffms.sr;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.happiest.minds.ffms.CommonUtility;
import com.happiest.minds.ffms.FFMSRequestQueue;
import com.happiest.minds.ffms.R;
import com.happiest.minds.ffms.Webserver;
import com.happiest.minds.ffms.sales.pojo.TicketCardViewData;
import com.happiest.minds.ffms.sales.pojo.TicketDetails;
import java.io.IOException;
import java.util.ArrayList;


public class SRLeadsCardViewRecyclerAdapter extends RecyclerView.Adapter<SRLeadsCardViewRecyclerAdapter.SRCardViewHolder> {

    private static final String TAG = SRLeadsCardViewRecyclerAdapter.class.getSimpleName();

    private ArrayList<TicketCardViewData> ticketCardViewDataArrayList;

    Context context;

    FFMSRequestQueue ffmsRequestQueue;
    ObjectMapper objectMapper;
    public static ArrayList<TicketDetails> ticketDetailsArrayList;


    public SRLeadsCardViewRecyclerAdapter(Context contextCons, ArrayList<TicketCardViewData> ticketCardViewDataArrayListCons) {


        context = contextCons;

        ffmsRequestQueue = FFMSRequestQueue.getInstance(context);

        objectMapper = new ObjectMapper();

        ticketCardViewDataArrayList = ticketCardViewDataArrayListCons;

    }

    @Override
    public SRLeadsCardViewRecyclerAdapter.SRCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.sr_leads_ticket_card_view_layout,
                parent, false);

        SRLeadsCardViewRecyclerAdapter.SRCardViewHolder srHeaderButtonTicketListCardViewHolder = new SRLeadsCardViewRecyclerAdapter.SRCardViewHolder(
                v);

        Log.i(TAG, "onCreateViewHolder");

        return srHeaderButtonTicketListCardViewHolder;
    }

    @Override
    public void onBindViewHolder(SRLeadsCardViewRecyclerAdapter.SRCardViewHolder holder, int position) {

        holder.setIsRecyclable(false);

        holder.ticketNo_CV_TV.setText(ticketCardViewDataArrayList.get(
                position).getTicketNumber());

        holder.address_CV_TV.setText(ticketCardViewDataArrayList.get(
                position).getCustomerAddress().getAddress1());

        holder.createdDate_CV_TV.setText("" + ticketCardViewDataArrayList.get(
                position).getTicketCreationDate());

        holder.etr_CV_TV.setText(ticketCardViewDataArrayList.get(
                position).getCommittedETR());

        holder.mobileNo_CV_TV.setText(ticketCardViewDataArrayList.get(
                position).getCustomerMobileNumber());

        holder.customerName_CV_TV.setText(ticketCardViewDataArrayList.get(
                position).getCustomerName());

        holder.update_Status_IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CommonUtility.showToastMessage(context, "Activity not available");

            }
        });

    }

    @Override
    public int getItemCount() {
        return ticketCardViewDataArrayList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class SRCardViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        TextView ticketNo_CV_TV, customerName_CV_TV, mobileNo_CV_TV,
                createdDate_CV_TV, etr_CV_TV, address_CV_TV;

        RelativeLayout demo_demarcation_RL, order_demarcation_RL;

        ImageView lock_Status_IV, update_Status_IV;

        SRCardViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.sr_card_view);

            ticketNo_CV_TV = (TextView) itemView
                    .findViewById(R.id.ticketNo_CV_SR_TV);
            customerName_CV_TV = (TextView) itemView
                    .findViewById(R.id.customerName_CV_SR_TV);
            mobileNo_CV_TV = (TextView) itemView
                    .findViewById(R.id.mobileNo_CV_SR_TV);

            createdDate_CV_TV = (TextView) itemView
                    .findViewById(R.id.createdDate_CV_SR_TV);

            etr_CV_TV = (TextView) itemView.findViewById(R.id.etr_CV_SR_TV);

            address_CV_TV = (TextView) itemView
                    .findViewById(R.id.address_CV_SR_TV);

            // demo_demarcation_RL = (RelativeLayout) itemView.findViewById(R.id.demo_demarcation_SR_RL);
            // order_demarcation_RL = (RelativeLayout) itemView.findViewById(R.id.order_demarcation_SR_RL);
            lock_Status_IV = (ImageView) itemView.findViewById(R.id.lock_Status_IV);
            update_Status_IV = (ImageView) itemView.findViewById(R.id.update_Status_SR_IV);


            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {


                    String ticketId = String
                            .valueOf(ticketCardViewDataArrayList.get(
                                    getAdapterPosition()).getTicketId());

                    callServiceForTicketDetails(ticketId);
                }
            });
        }
    }

    private void callServiceForTicketDetails(String ticketId) {

        String host = Webserver.SERVER_HOST;
        String uri = Webserver.SALES_LEAD_DETAILS;
        String url = host + "" + uri + "/" + ticketId;

        Log.i(TAG, "url : " + url);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.i(TAG,
                                "callServiceForTicketDetails  response : "
                                        + response);

                        try {

                            ticketDetailsArrayList = objectMapper.readValue(
                                    response,
                                    TypeFactory.defaultInstance()
                                            .constructCollectionType(
                                                    ArrayList.class,
                                                    TicketDetails.class));
                            redirectToSRLeadDetailsActivity();

                        } catch (JsonParseException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (JsonMappingException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                if (error != null) {

                    Log.e(TAG,
                            "callServiceForTicketDetails onErrorResponse : "
                                    + error.toString());

                }

                CommonUtility
                        .showServerResponseMessage(
                                context,
                                context.getResources()
                                        .getString(
                                                R.string.invalid_server_response_for_webservice)
                                        + " SALES_LEAD_DETAILS");


            }

        });

        ffmsRequestQueue.addToRequestQueue(stringRequest);

    }

    private void redirectToSRLeadDetailsActivity() {

        ServiceRequestHomeActivity.isOnHome = false;
        ServiceRequestHomeActivity.isOnHome = false;
        ServiceRequestHomeActivity.isOnProfile = false;
        ServiceRequestHomeActivity.isOnLeadsDetails = true;
        ServiceRequestHomeActivity.isOnCardView = false;
        ServiceRequestHomeActivity.welcome_TV.setText(context.getResources().getString(
                R.string.lead_details_text));

        Intent srLeadsDetailsActivity = new Intent(context, SRLeadsDetailsActivity.class);
        context.startActivity(srLeadsDetailsActivity);


    }
}

