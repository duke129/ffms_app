package com.happiest.minds.ffms.sales;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.happiest.minds.ffms.CommonUtility;
import com.happiest.minds.ffms.R;
import com.happiest.minds.ffms.sales.pojo.ProductDTO;

import java.util.ArrayList;

import static com.happiest.minds.ffms.sales.SalesActivityDetailsViewHolder.orderedProductDTOArrayList;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ProductRecyclerAdapterViewHolder> {

    private static final String TAG = ProductRecyclerAdapter.class.getSimpleName();

    ArrayList<ProductDTO> productDtoArrayList;

    Context context;

    public ProductRecyclerAdapter(Context contextCons, ArrayList<ProductDTO> productDtoArrayListArg) {

        this.context = SalesTicketDetailsFragment.context;

        this.productDtoArrayList = productDtoArrayListArg;
    }

    @NonNull
    @Override
    public ProductRecyclerAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(
                        R.layout.product_view,
                        parent, false);
        ProductRecyclerAdapterViewHolder viewHolder = new ProductRecyclerAdapterViewHolder(
                v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductRecyclerAdapterViewHolder holder, int position) {

        holder.setIsRecyclable(false);

        int firstObjectPosition = (2 * position);
        int secondObjectPosition = (2 * position) + 1;

        if ( firstObjectPosition >= productDtoArrayList.size()) {

            holder.cardView.setVisibility(View.GONE);
            holder.product_LL.setVisibility(View.GONE);

        }else{


            holder.first_product_model_name_TV.setText("Model Name : " + productDtoArrayList.get(firstObjectPosition).getName());
            holder.first_product_model_id_TV.setText("Model Id : " + productDtoArrayList.get(firstObjectPosition).getIdProduct());
            holder.first_product_price_TV.setText("Price : " + productDtoArrayList.get(firstObjectPosition).getPrice());

            byte[] decodedStringFirst = Base64.decode(productDtoArrayList.get(firstObjectPosition).getImage(), Base64.DEFAULT);
            Bitmap decodedByteFirst = BitmapFactory.decodeByteArray(decodedStringFirst, 0, decodedStringFirst.length);
            holder.first_product_IV.setImageBitmap(decodedByteFirst);
        }

        if ( secondObjectPosition >= productDtoArrayList.size()) {

            holder.second_product_LL.setVisibility(View.GONE);


        } else {

            holder.second_product_model_name_TV.setText("Model Name : " + productDtoArrayList.get(secondObjectPosition).getName());
            holder.second_product_model_id_TV.setText("Model Id : " + productDtoArrayList.get(secondObjectPosition).getIdProduct());
            holder.second_product_price_TV.setText("Price : " + productDtoArrayList.get(secondObjectPosition).getPrice());

            byte[] decodedStringSecond = Base64.decode(productDtoArrayList.get(secondObjectPosition).getImage(), Base64.DEFAULT);
            Bitmap decodedByteSecond = BitmapFactory.decodeByteArray(decodedStringSecond, 0,decodedStringSecond.length);
            holder.second_product_IV.setImageBitmap(decodedByteSecond);
        }

    }

    @Override
    public int getItemCount() {
        return productDtoArrayList.size();
    }

    public class ProductRecyclerAdapterViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        LinearLayout product_LL,first_product_LL, second_product_LL,firstAddToOrderList_LL, secondAddToOrderList_LL;
        ImageView first_product_IV, second_product_IV;
        TextView first_product_model_name_TV, first_product_model_id_TV, first_product_price_TV,
                second_product_model_name_TV, second_product_model_id_TV, second_product_price_TV,firstToOrderList_TV,secondToOrderList_TV;


        public ProductRecyclerAdapterViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.product_cardview);
            product_LL = (LinearLayout) itemView.findViewById(R.id.product_LL);
            first_product_LL = (LinearLayout) itemView.findViewById(R.id.first_product_LL);
            firstAddToOrderList_LL = (LinearLayout) itemView.findViewById(R.id.firstAddToOrderList_LL);
            secondAddToOrderList_LL = (LinearLayout) itemView.findViewById(R.id.secondAddToOrderList_LL);
            second_product_LL = (LinearLayout) itemView.findViewById(R.id.second_product_LL);
            first_product_IV = (ImageView) itemView.findViewById(R.id.first_product_IV);
            second_product_IV = (ImageView) itemView.findViewById(R.id.second_product_IV);
            first_product_model_name_TV = (TextView) itemView.findViewById(R.id.first_product_model_name_TV);
            first_product_model_id_TV = (TextView) itemView.findViewById(R.id.first_product_model_id_TV);
            first_product_price_TV = (TextView) itemView.findViewById(R.id.first_product_price_TV);
            second_product_model_name_TV = (TextView) itemView.findViewById(R.id.second_product_model_name_TV);
            second_product_model_id_TV = (TextView) itemView.findViewById(R.id.second_product_model_id_TV);
            second_product_price_TV = (TextView) itemView.findViewById(R.id.second_product_price_TV);
            firstToOrderList_TV = (TextView) itemView.findViewById(R.id.firstToOrderList_TV);
            secondToOrderList_TV = (TextView) itemView.findViewById(R.id.secondToOrderList_TV);

            first_product_LL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String modelId = String
                            .valueOf(productDtoArrayList.get(
                                    getAdapterPosition()).getIdProduct());

                    CommonUtility.saveSelectedProductId(SalesTicketDetailsFragment.context, modelId);

                    Intent productDetailsActivity = new Intent(context, ProductDetailsActivity.class);
                    context.startActivity(productDetailsActivity);

                }
            });

            second_product_LL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String modelId = String
                            .valueOf(productDtoArrayList.get(
                                    getAdapterPosition()).getIdProduct());

                    CommonUtility.saveSelectedProductId(SalesTicketDetailsFragment.context, modelId);

                    Intent productDetailsActivity = new Intent(context, ProductDetailsActivity.class);
                    context.startActivity(productDetailsActivity);
                }
            });


            firstAddToOrderList_LL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String modelId = first_product_model_id_TV.getText().toString().trim();

                   CommonUtility.showToastMessage(context, "Added product Id : "+modelId);

                    orderedProductDTOArrayList.add(productDtoArrayList.get(
                            getAdapterPosition()));

                    firstToOrderList_TV.setText("Added List");

                    firstAddToOrderList_LL.setEnabled(false);
                }
            });

            secondAddToOrderList_LL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String modelId = second_product_model_id_TV.getText().toString().trim();

                    CommonUtility.showToastMessage(context, "Added product Id : "+modelId);

                    orderedProductDTOArrayList.add(productDtoArrayList.get(
                            getAdapterPosition()));

                    secondToOrderList_TV.setText("Added List");

                    secondAddToOrderList_LL.setEnabled(false);
                }
            });

        }

    }

}
