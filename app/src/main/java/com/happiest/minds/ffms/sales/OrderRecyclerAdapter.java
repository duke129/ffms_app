package com.happiest.minds.ffms.sales;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.happiest.minds.ffms.CommonUtility;
import com.happiest.minds.ffms.R;
import com.happiest.minds.ffms.sales.pojo.ProductDTO;

import java.util.ArrayList;

public class OrderRecyclerAdapter extends RecyclerView.Adapter<OrderRecyclerAdapter.ProductRecyclerAdapterViewHolder> {

    private static final String TAG = OrderRecyclerAdapter.class.getSimpleName();

    ArrayList<ProductDTO> productDtoArrayList;

    Context context;

    public OrderRecyclerAdapter(Context contextCons, ArrayList<ProductDTO> productDtoArrayListArg) {

        this.context = SalesTicketDetailsFragment.context;

        this.productDtoArrayList = productDtoArrayListArg;
    }

    @NonNull
    @Override
    public ProductRecyclerAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(
                        R.layout.order_card_view,
                        parent, false);
        ProductRecyclerAdapterViewHolder viewHolder = new ProductRecyclerAdapterViewHolder(
                v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductRecyclerAdapterViewHolder holder, int position) {

        holder.setIsRecyclable(false);

        holder.order_model_id_CV_TV.setText(""+productDtoArrayList.get(position).getIdProduct());
        holder.order_name_CV_TV.setText(""+productDtoArrayList.get(position).getName());
        holder.order_price_CV_TV.setText(""+productDtoArrayList.get(position).getPrice());
        holder.order_model_specification_value_CV_TV.setText("Color : Black, Door : Multidoor, Warranty : Yes");


    }

    @Override
    public int getItemCount() {

        if(productDtoArrayList != null){

            return productDtoArrayList.size();
        }else{

            return 0;
        }


    }

    public class ProductRecyclerAdapterViewHolder extends RecyclerView.ViewHolder {

        CardView order_card_view;
        TextView order_model_id_CV_TV, order_name_CV_TV, order_price_CV_TV,
                order_model_specification_CV_TV, order_model_specification_value_CV_TV;

        RelativeLayout order_remove_RL;

        public ProductRecyclerAdapterViewHolder(View itemView) {
            super(itemView);

            order_card_view = (CardView) itemView.findViewById(R.id.order_card_view);
            order_remove_RL = (RelativeLayout) itemView.findViewById(R.id.order_remove_RL);
            order_model_id_CV_TV = (TextView) itemView.findViewById(R.id.order_model_id_CV_TV);
            order_name_CV_TV = (TextView) itemView.findViewById(R.id.order_name_CV_TV);
            order_price_CV_TV = (TextView) itemView.findViewById(R.id.order_price_CV_TV);
            order_model_specification_CV_TV = (TextView) itemView.findViewById(R.id.order_model_specification_CV_TV);
            order_model_specification_value_CV_TV = (TextView) itemView.findViewById(R.id.order_model_specification_value_CV_TV);

            order_remove_RL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    CommonUtility.showToastMessage(context, "Activity not available");

                }
            });

        }

    }

}
