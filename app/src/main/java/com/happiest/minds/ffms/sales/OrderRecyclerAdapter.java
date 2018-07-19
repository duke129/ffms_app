package com.happiest.minds.ffms.sales;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.happiest.minds.ffms.CommonUtility;
import com.happiest.minds.ffms.Constant;
import com.happiest.minds.ffms.R;
import com.happiest.minds.ffms.sales.pojo.OrderVo;
import com.happiest.minds.ffms.sales.pojo.ProductDTO;

import java.util.ArrayList;

public class OrderRecyclerAdapter extends RecyclerView.Adapter<OrderRecyclerAdapter.ProductRecyclerAdapterViewHolder> {

    private static final String TAG = OrderRecyclerAdapter.class.getSimpleName();

    ArrayList<ProductDTO> productDtoArrayList;

    ArrayList<OrderVo> orderVoArrayList;

    public static int[] productCount;

    Context context;

    String ticketId ;

    public OrderRecyclerAdapter(Context contextCons, ArrayList<ProductDTO> productDtoArrayListArg) {

        this.context = SalesTicketDetailsFragment.context;

        this.productDtoArrayList = productDtoArrayListArg;

        productCount = new int[productDtoArrayList.size()];

        for (int i = 0; i < productCount.length; i++) {

            productCount[i] = 1;
        }
    }

    public OrderRecyclerAdapter(Context contextCons, ArrayList<OrderVo> orderVoArrayListArg, String ticketIdArg) {

        this.context = SalesTicketDetailsFragment.context;

        this.orderVoArrayList = orderVoArrayListArg;

        this.ticketId = ticketIdArg;

        productCount = new int[orderVoArrayListArg.size()];

        for (int i = 0; i < productCount.length; i++) {

            productCount[i] = orderVoArrayListArg.get(i).getQuantity();
        }
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
    public void onBindViewHolder(@NonNull final ProductRecyclerAdapterViewHolder holder, final int position) {

        holder.setIsRecyclable(false);


        if (SalesTicketDetailsFragment.orderStatus == Constant.ACTIVITY_COMPLETED) {

            holder.order_model_id_CV_TV.setText("" + orderVoArrayList.get(position).getProductId());
            holder.order_name_CV_TV.setText("" + orderVoArrayList.get(position).getProductName());
            holder.order_price_CV_TV.setText("" + orderVoArrayList.get(position).getPrice());
            holder.order_model_specification_value_CV_TV.setText("Color : Black, Door : Multidoor, Warranty : Yes");

            long productCount = orderVoArrayList.get(position).getQuantity();
            long price = Integer.parseInt(orderVoArrayList.get(position).getPrice());

            long amount = (productCount * price);

            holder.count_TV.setText(""+productCount);

            holder.amount_TV.setText("Amount : " + amount);

            holder.decrease_LL.setEnabled(false);
            holder.increase_LL.setEnabled(false);
            holder.remove_IV.setEnabled(false);



        } else {

            holder.order_model_id_CV_TV.setText("" + productDtoArrayList.get(position).getIdProduct());
            holder.order_name_CV_TV.setText("" + productDtoArrayList.get(position).getName());
            holder.order_price_CV_TV.setText("" + productDtoArrayList.get(position).getPrice());
            holder.order_model_specification_value_CV_TV.setText("Color : Black, Door : Multidoor, Warranty : Yes");
            holder.amount_TV.setText("Amount : " + productDtoArrayList.get(position).getPrice());
        }

        holder.count_TV.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                int productPrice = Integer.parseInt(productDtoArrayList.get(position).getPrice());

                int count = Integer.parseInt(holder.count_TV.getText().toString());

                productCount[position] = count;

                int amount = (productPrice * count);

                holder.amount_TV.setText("Amount : " + amount);

            }
        });

        holder.decrease_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int currentValue = Integer.parseInt(holder.count_TV.getText().toString());

                if (currentValue != 1) {

                    currentValue = currentValue - 1;
                    holder.count_TV.setText("" + currentValue);

                } else {

                    CommonUtility.showToastMessage(context, "Only positive count allow");
                }


            }
        });

        holder.increase_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int currentValue = Integer.parseInt(holder.count_TV.getText().toString());

                currentValue = currentValue + 1;
                holder.count_TV.setText("" + currentValue);

            }
        });


        holder.remove_IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                holder.remove_IV.setOnClickListener(null);

                SalesActivityDetailsViewHolder.removeFromList(position);

                SalesActivityDetailsViewHolder.initOrderRecyclerAdapter(SalesActivityDetailsViewHolder.getOrderedProductDTOArrayList());

            }
        });

    }

    @Override
    public int getItemCount() {

        if(SalesTicketDetailsFragment.orderStatus == Constant.ACTIVITY_COMPLETED){

            if (orderVoArrayList != null) {

                return orderVoArrayList.size();
            } else {

                return 0;
            }

        }else {

            if (productDtoArrayList != null) {

                return productDtoArrayList.size();
            } else {

                return 0;
            }

        }

    }


    public class ProductRecyclerAdapterViewHolder extends RecyclerView.ViewHolder {

        CardView order_card_view;
        TextView order_model_id_CV_TV, order_name_CV_TV, order_price_CV_TV,
                order_model_specification_CV_TV, order_model_specification_value_CV_TV, count_TV, amount_TV;

        LinearLayout decrease_LL, increase_LL;
        ImageView remove_IV;


        public ProductRecyclerAdapterViewHolder(View itemView) {
            super(itemView);

            order_card_view = (CardView) itemView.findViewById(R.id.order_card_view);
            order_model_id_CV_TV = (TextView) itemView.findViewById(R.id.order_model_id_CV_TV);
            order_name_CV_TV = (TextView) itemView.findViewById(R.id.order_name_CV_TV);
            order_price_CV_TV = (TextView) itemView.findViewById(R.id.order_price_CV_TV);
            order_model_specification_CV_TV = (TextView) itemView.findViewById(R.id.order_model_specification_CV_TV);
            order_model_specification_value_CV_TV = (TextView) itemView.findViewById(R.id.order_model_specification_value_CV_TV);
            decrease_LL = (LinearLayout) itemView.findViewById(R.id.decrease_LL);
            increase_LL = (LinearLayout) itemView.findViewById(R.id.increase_LL);
            remove_IV = (ImageView) itemView.findViewById(R.id.remove_IV);
            count_TV = (TextView) itemView.findViewById(R.id.count_TV);
            amount_TV = (TextView) itemView.findViewById(R.id.amount_TV);

        }

    }

}
