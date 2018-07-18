package com.happiest.minds.ffms.sales;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.happiest.minds.ffms.CommonUtility;
import com.happiest.minds.ffms.FFMSRequestQueue;
import com.happiest.minds.ffms.R;
import com.happiest.minds.ffms.Webserver;
import com.happiest.minds.ffms.sales.pojo.APIResponse;
import com.happiest.minds.ffms.sales.pojo.ProductDTO;
import com.happiest.minds.ffms.sales.pojo.TypeHeadVo;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ProductRecyclerAdapterViewHolder> {

    private static final String TAG = ProductRecyclerAdapter.class.getSimpleName();

    ArrayList<ProductDTO> productDtoArrayList;

    Context context;

    FFMSRequestQueue ffmsRequestQueue;
    ObjectMapper objectMapper;
    APIResponse apiResponse;

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

        if (firstObjectPosition >= productDtoArrayList.size()) {

            holder.cardView.setVisibility(View.GONE);
            holder.product_LL.setVisibility(View.GONE);

        } else {


            holder.first_product_model_name_TV.setText("" + productDtoArrayList.get(firstObjectPosition).getName());
            holder.first_product_model_id_TV.setText("" + productDtoArrayList.get(firstObjectPosition).getIdProduct());
            holder.first_product_price_TV.setText("" + productDtoArrayList.get(firstObjectPosition).getPrice());

            callProductImageService(productDtoArrayList.get(firstObjectPosition).getIdProduct(), holder.first_product_IV);

           /* byte[] decodedStringFirst = Base64.decode(productDtoArrayList.get(firstObjectPosition).getImage(), Base64.DEFAULT);
            Bitmap decodedByteFirst = BitmapFactory.decodeByteArray(decodedStringFirst, 0, decodedStringFirst.length);
            holder.first_product_IV.setImageBitmap(decodedByteFirst);*/


        }

        if (secondObjectPosition >= productDtoArrayList.size()) {

            holder.second_product_LL.setVisibility(View.GONE);


        } else {

            holder.second_product_model_name_TV.setText("" + productDtoArrayList.get(secondObjectPosition).getName());
            holder.second_product_model_id_TV.setText("" + productDtoArrayList.get(secondObjectPosition).getIdProduct());
            holder.second_product_price_TV.setText("" + productDtoArrayList.get(secondObjectPosition).getPrice());

            callProductImageService(productDtoArrayList.get(secondObjectPosition).getIdProduct(), holder.second_product_IV);

            /*byte[] decodedStringSecond = Base64.decode(productDtoArrayList.get(secondObjectPosition).getImage(), Base64.DEFAULT);
            Bitmap decodedByteSecond = BitmapFactory.decodeByteArray(decodedStringSecond, 0, decodedStringSecond.length);
            holder.second_product_IV.setImageBitmap(decodedByteSecond);*/
        }

    }

    private void callProductImageService(String productId , final ImageView imageView){

        ffmsRequestQueue = FFMSRequestQueue.getInstance(context);
        objectMapper = new ObjectMapper();

        String host = Webserver.SERVER_HOST;
        String uri = Webserver.IMAGE_DOWNLOAD_URI;
        String url = host + "" + uri + "/" + productId;


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG,
                                "callProductImageService  response : "
                                        + response);

                        try {

                            apiResponse = objectMapper.readValue(response.toString(), APIResponse.class);

                            if (apiResponse != null) {

                                Log.i(TAG, "apiResponse :" + apiResponse.toString());

                                Object data = apiResponse.getData();

                                if (data != null) {

                                    String dataString = objectMapper.writeValueAsString(data);

                                    if (dataString != null && !dataString.isEmpty()) {


                                        byte[] decodedString = Base64.decode(dataString, Base64.DEFAULT);
                                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                                        imageView.setImageBitmap(decodedByte);

                                    } else {

                                        Log.i(TAG, "dataString is null or empty");

                                    }

                                } else {

                                    Log.i(TAG, "data is null");

                                }
                            }

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
                            "callProductImageService onErrorResponse : "
                                    + error.toString());

                }


            }

        });


        ffmsRequestQueue.addToRequestQueue(jsonObjectRequest);


    }

    @Override
    public int getItemCount() {
        return productDtoArrayList.size();
    }

    public class ProductRecyclerAdapterViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        LinearLayout product_LL, first_product_LL, second_product_LL, firstAddToOrderList_LL, secondAddToOrderList_LL;
        ImageView first_product_IV, second_product_IV;
        TextView first_product_model_name_TV, first_product_model_id_TV, first_product_price_TV,
                second_product_model_name_TV, second_product_model_id_TV, second_product_price_TV, firstToOrderList_TV, secondToOrderList_TV;


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

                    int firstObjectPosition = (2 * getAdapterPosition());


                    String modelId = first_product_model_id_TV.getText().toString().trim();

                    CommonUtility.showToastMessage(context, "Added product Id : " + modelId);

                    SalesActivityDetailsViewHolder.addToOrderedProductDTOArrayList(productDtoArrayList.get(firstObjectPosition));

                    SalesActivityDetailsViewHolder.initOrderRecyclerAdapter(SalesActivityDetailsViewHolder.getOrderedProductDTOArrayList());
//
//                    Log.i(TAG, "firstObjectPosition : "+firstObjectPosition+" orderedProductDTOArrayList : "+orderedProductDTOArrayList.size());

                    firstToOrderList_TV.setText("Added List");

                    firstAddToOrderList_LL.setEnabled(false);
                }
            });

            secondAddToOrderList_LL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int secondObjectPosition = (2 * getAdapterPosition()) + 1;

                    String modelId = second_product_model_id_TV.getText().toString().trim();

                    CommonUtility.showToastMessage(context, "Added product Id : " + modelId);

                    SalesActivityDetailsViewHolder.addToOrderedProductDTOArrayList(productDtoArrayList.get(secondObjectPosition));

                    SalesActivityDetailsViewHolder.initOrderRecyclerAdapter(SalesActivityDetailsViewHolder.getOrderedProductDTOArrayList());

//                    Log.i(TAG, "secondObjectPosition : "+secondObjectPosition+" orderedProductDTOArrayList : "+orderedProductDTOArrayList.size());


                    secondToOrderList_TV.setText("Added List");

                    secondAddToOrderList_LL.setEnabled(false);
                }
            });

        }

    }

}
