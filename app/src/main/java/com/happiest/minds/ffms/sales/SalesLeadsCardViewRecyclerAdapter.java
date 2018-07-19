package com.happiest.minds.ffms.sales;

import android.app.Activity;
import android.content.Context;
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
import com.happiest.minds.ffms.Constant;
import com.happiest.minds.ffms.FFMSRequestQueue;
import com.happiest.minds.ffms.R;
import com.happiest.minds.ffms.Webserver;
import com.happiest.minds.ffms.sales.pojo.ActivityVo;
import com.happiest.minds.ffms.sales.pojo.TicketCardViewData;
import com.happiest.minds.ffms.sales.pojo.TicketDetails;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SalesLeadsCardViewRecyclerAdapter extends RecyclerView.Adapter<SalesLeadsCardViewRecyclerAdapter.SalesCardViewHolder> {

    private static final String TAG = SalesLeadsCardViewRecyclerAdapter.class.getSimpleName();

    private ArrayList<TicketCardViewData> ticketCardViewDataArrayList;

    Context context;

    SalesTicketDetailsFragment salesTicketDetailsFragment;

    FFMSRequestQueue ffmsRequestQueue;
    ObjectMapper objectMapper;
    public static ArrayList<TicketDetails> ticketDetailsArrayList;


    public SalesLeadsCardViewRecyclerAdapter(Context contextCons, ArrayList<TicketCardViewData> ticketCardViewDataArrayListCons) {


        context = contextCons;

        ffmsRequestQueue = FFMSRequestQueue.getInstance(context);

        objectMapper = new ObjectMapper();

        ticketCardViewDataArrayList = ticketCardViewDataArrayListCons;

    }

    @Override
    public SalesCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.sales_leads_ticket_card_view_layout,
                parent, false);

        SalesCardViewHolder seHeaderButtonTicketListCardViewHolder = new SalesCardViewHolder(
                v);

        Log.i(TAG, "onCreateViewHolder");

        return seHeaderButtonTicketListCardViewHolder;
    }

    @Override
    public void onBindViewHolder(SalesCardViewHolder holder, int position) {

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

        List<ActivityVo> activityVoList = ticketCardViewDataArrayList.get(
                position).getActivities();

        for (int i = 0; i < activityVoList.size(); i++) {

            ActivityVo activityVo = activityVoList.get(i);

            int activityId = activityVo.getId();

            int status = 0;

            if (activityVo.getStatus() != null) {
                status = activityVo.getStatus();
            }

            switch (activityId) {

                case Constant.BASIC_INFO_UPDATE:


                    if (status == Constant.ACTIVITY_COMPLETED) {

                        holder.biu_demarcation_RL.setBackgroundResource(R.drawable.first_green_arrow);

                    } else if (status == Constant.ACTIVITY_NOT_DONE) {

                        holder.biu_demarcation_RL.setBackgroundResource(R.drawable.first_grey_arrow);
                    }else if (status == Constant.ACTIVITY_REJECTED) {

                        holder.biu_demarcation_RL.setBackgroundResource(R.drawable.first_green_arrow);
                    }

                    break;

                case Constant.DEMO:

                    if (status == Constant.ACTIVITY_COMPLETED) {

                        holder.demo_demarcation_RL.setBackgroundResource(R.drawable.green_arrow);

                    } else if (status == Constant.ACTIVITY_NOT_DONE) {

                        holder.demo_demarcation_RL.setBackgroundResource(R.drawable.grey_arrow);
                    }else if (status == Constant.ACTIVITY_REJECTED) {

                        holder.demo_demarcation_RL.setBackgroundResource(R.drawable.red_arrow);
                    }

                    break;

                case Constant.ORDER:

                    if (status == Constant.ACTIVITY_COMPLETED) {

                        holder.order_demarcation_RL.setBackgroundResource(R.drawable.green_arrow);

                    } else if (status == Constant.ACTIVITY_NOT_DONE) {

                        holder.order_demarcation_RL.setBackgroundResource(R.drawable.grey_arrow);
                    }else if (status == Constant.ACTIVITY_REJECTED) {

                        holder.order_demarcation_RL.setBackgroundResource(R.drawable.red_arrow);
                    }

                    break;


            }


        }

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

    public class SalesCardViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        TextView ticketNo_CV_TV, customerName_CV_TV, mobileNo_CV_TV,
                createdDate_CV_TV, etr_CV_TV, address_CV_TV;

        RelativeLayout biu_demarcation_RL, demo_demarcation_RL, order_demarcation_RL;

        ImageView lock_Status_IV, update_Status_IV;

        SalesCardViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.se_card_view);

            ticketNo_CV_TV = (TextView) itemView
                    .findViewById(R.id.ticketNo_CV_TV);
            customerName_CV_TV = (TextView) itemView
                    .findViewById(R.id.customerName_CV_TV);
            mobileNo_CV_TV = (TextView) itemView
                    .findViewById(R.id.mobileNo_CV_TV);

            createdDate_CV_TV = (TextView) itemView
                    .findViewById(R.id.createdDate_CV_TV);

            etr_CV_TV = (TextView) itemView.findViewById(R.id.etr_CV_TV);

            address_CV_TV = (TextView) itemView
                    .findViewById(R.id.address_CV_TV);

            biu_demarcation_RL = (RelativeLayout) itemView.findViewById(R.id.biu_demarcation_RL);
            demo_demarcation_RL = (RelativeLayout) itemView.findViewById(R.id.demo_demarcation_RL);
            order_demarcation_RL = (RelativeLayout) itemView.findViewById(R.id.order_demarcation_RL);
            //lock_Status_IV = (ImageView) itemView.findViewById(R.id.lock_Status_IV);
            update_Status_IV = (ImageView) itemView.findViewById(R.id.update_Status_IV);


            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {


                    String ticketId = String
                            .valueOf(ticketCardViewDataArrayList.get(
                                    getAdapterPosition()).getTicketId());

                    CommonUtility.saveTicketId(context, ticketId);

                    callServiceForTicketDetails(ticketId);

                    //redirectToSalesTicketDetailsFragment();
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
                            redirectToSalesTicketDetailsFragment();

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

    private void redirectToSalesTicketDetailsFragment() {

        SalesHomeActivity.isOnHome = false;
        SalesHomeActivity.isOnHome = false;
        SalesHomeActivity.isOnProfile = false;
        SalesHomeActivity.isOnLeadsDetails = true;
        SalesHomeActivity.isOnCardView = false;
        SalesHomeActivity.welcome_TV.setText(context.getResources().getString(
                R.string.lead_details_text));

        salesTicketDetailsFragment = new SalesTicketDetailsFragment();

        if (salesTicketDetailsFragment != null
                && context != null && !((Activity) context).isFinishing()) {

            ((Activity) context).getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, salesTicketDetailsFragment)
                    .addToBackStack(null)
                    .commitAllowingStateLoss();
        }

    }
}
