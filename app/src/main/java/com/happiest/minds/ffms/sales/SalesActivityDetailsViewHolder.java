package com.happiest.minds.ffms.sales;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

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
import com.happiest.minds.ffms.Constant;
import com.happiest.minds.ffms.FFMSRequestQueue;
import com.happiest.minds.ffms.R;
import com.happiest.minds.ffms.Webserver;
import com.happiest.minds.ffms.sales.pojo.APIResponse;
import com.happiest.minds.ffms.sales.pojo.ProductCatalog;
import com.happiest.minds.ffms.sales.pojo.ProductDTO;
import com.happiest.minds.ffms.sales.pojo.TypeHeadVo;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import fr.ganfra.materialspinner.MaterialSpinner;

public class SalesActivityDetailsViewHolder extends ChildViewHolder {

    private static final String TAG = SalesActivityDetailsViewHolder.class.getSimpleName();
    Context context;
    private LinearLayout demo_activity_LL, order_activity_LL, product_recyclerView_LL;
    private MaterialSpinner assetCategory_SP;
    private ArrayAdapter assetCategory_adapter, assetCategoryOr_adapter, assetQuantity_adapter, assetModel_adapter;
    private RecyclerView product_recyclerView, order_recyclerView;
    private ProductRecyclerAdapter productRecyclerAdapter;
    private OrderRecyclerAdapter orderRecyclerAdapter;
    FFMSRequestQueue ffmsRequestQueue;
    ObjectMapper objectMapper;
    ArrayList<TypeHeadVo> typeHeadVoArrayList;
    APIResponse apiResponse;
    int MY_SOCKET_TIMEOUT_MS = 30000;
    ArrayList<ProductDTO> productDTOArrayList;
    public static ArrayList<ProductDTO> orderedProductDTOArrayList;

    public SalesActivityDetailsViewHolder(View itemView) {
        super(itemView);

        context = SalesTicketDetailsFragment.context;
        demo_activity_LL = (LinearLayout) itemView.findViewById(R.id.demo_activity_LL);
        order_activity_LL = (LinearLayout) itemView.findViewById(R.id.order_activity_LL);
        assetCategory_SP = (MaterialSpinner) itemView.findViewById(R.id.assetCategory_SP);
        product_recyclerView_LL = (LinearLayout) itemView.findViewById(R.id.product_recyclerView_LL);
        product_recyclerView = (RecyclerView) itemView.findViewById(R.id.product_recyclerView);
        order_recyclerView = (RecyclerView) itemView.findViewById(R.id.order_recyclerView);

        orderedProductDTOArrayList = new ArrayList<>();
    }

    public void onBind(SalesActivityDetails salesActivityDetails, ExpandableGroup group) {

        assetCategory_SP.setHint(R.string.assetCategory_hint);

        if (group.getTitle().equals(Constant.SALES_ACTIVITY_DEMO)) {

            demo_activity_LL.setVisibility(View.VISIBLE);
            order_activity_LL.setVisibility(View.GONE);

            callAssetTypeService();

            //operationOnAssetTypeSelction();

           // initRecyclerAdapter();

        } else if (group.getTitle().equals(Constant.SALES_ACTIVITY_ORDER)) {
            demo_activity_LL.setVisibility(View.GONE);
            order_activity_LL.setVisibility(View.VISIBLE);
            initOrderRecyclerAdapter(orderedProductDTOArrayList);
        } else {
        }

    }

    private void callAssetTypeService() {

        ffmsRequestQueue = FFMSRequestQueue.getInstance(context);
        objectMapper = new ObjectMapper();

        String host = Webserver.SERVER_HOST;
        String uri = Webserver.ASSET_TYPE_URI;
        String url = host + "" + uri;


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG,
                                "callAssetTypeService  response : "
                                        + response);

                        try {

                            apiResponse = objectMapper.readValue(response.toString(), APIResponse.class);

                            if (apiResponse != null) {

                                Log.i(TAG, "apiResponse :" + apiResponse.toString());

                                Object data = apiResponse.getData();

                                if (data != null) {

                                    String dataString = objectMapper.writeValueAsString(data);

                                    typeHeadVoArrayList = objectMapper.readValue(
                                            dataString,
                                            TypeFactory.defaultInstance()
                                                    .constructCollectionType(
                                                            ArrayList.class,
                                                            TypeHeadVo.class));

                                    initAssetTypeSpinner(typeHeadVoArrayList);

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
                            "callAssetTypeService onErrorResponse : "
                                    + error.toString());

                }


                CommonUtility
                        .showServerResponseMessage(
                                context,
                                context.getResources()
                                        .getString(
                                                R.string.invalid_server_response_for_webservice)
                                        + " callAssetTypeService");


            }

        });

        ffmsRequestQueue.addToRequestQueue(jsonObjectRequest);

    }

    private void initAssetTypeSpinner(ArrayList<TypeHeadVo> typeHeadVoArrayListArg) {

        ArrayList<String> assetTypeStringArrayList = new ArrayList<>();

        for(int i= 0; i<typeHeadVoArrayListArg.size(); i++){

            assetTypeStringArrayList.add(typeHeadVoArrayListArg.get(i).getName());
        }

        assetCategory_adapter = new ArrayAdapter<String>(SalesTicketDetailsFragment.context, android.R.layout.simple_spinner_item, assetTypeStringArrayList);
        assetCategory_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        assetCategory_SP.setAdapter(assetCategory_adapter);
        assetCategory_SP.setPaddingSafe(0, 0, 0, 0);

        operationOnAssetTypeSelction();
    }

    private void initRecyclerAdapter(ArrayList<ProductDTO> productDTOArrayListArg) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        product_recyclerView.setLayoutManager(layoutManager);

        productRecyclerAdapter = new ProductRecyclerAdapter(context, productDTOArrayListArg);
        product_recyclerView.setAdapter(productRecyclerAdapter);
    }

    private void initOrderRecyclerAdapter(ArrayList<ProductDTO> productDTOArrayListArg) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        order_recyclerView.setLayoutManager(layoutManager);

        orderRecyclerAdapter = new OrderRecyclerAdapter(context, productDTOArrayListArg);
        order_recyclerView.setAdapter(orderRecyclerAdapter);
    }

    private ArrayList<ProductCatalog> getProductList() {

        ArrayList<ProductCatalog> productCatalogArrayList = new ArrayList<>();

        for (int i = 0; i <= 2; i++) {

            ProductCatalog productCatalog = new ProductCatalog();

            productCatalog.setFirstModelName("ABC");
            productCatalog.setFirstModelId("ABC" + i);
            productCatalog.setFirstPrice("123" + i);
            productCatalog.setSecondModelName("XYZ");
            productCatalog.setSecondModelId("XYZ" + i);
            productCatalog.setSecondPrice("321" + i);
            productCatalogArrayList.add(productCatalog);
        }

        ProductCatalog productCatalog = new ProductCatalog();
        productCatalog.setFirstModelName("ABC");
        productCatalog.setFirstModelId("ABC123");
        productCatalog.setFirstPrice("123");
        productCatalogArrayList.add(productCatalog);

        return productCatalogArrayList;
    }


    private void operationOnAssetTypeSelction(){

        assetCategory_SP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                /*String selectedAssetCategory = "";

                if(assetCategory_SP != null){

                     selectedAssetCategory = assetCategory_SP
                            .getSelectedItem().toString().trim();
                }


                if( selectedAssetCategory != null && !selectedAssetCategory.equals("")){

                    if(selectedAssetCategory.equalsIgnoreCase("Select Asset Category")){

                        CommonUtility.showToastMessage(context, "Please Select Asset Category");
                    }else if(selectedAssetCategory.equalsIgnoreCase("Refrigerator")){


                    }else {

                        CommonUtility.showToastMessage(context, "Data not available for selected category");
                    }
                }*/

                //Log.i(TAG, " selectedAssetCategory : "+selectedAssetCategory);


                callModelDetailsForAssetCategory();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void callModelDetailsForAssetCategory(){

        ffmsRequestQueue = FFMSRequestQueue.getInstance(context);
        objectMapper = new ObjectMapper();

        String host = Webserver.SERVER_HOST;
        String uri = Webserver.MODEL_FOR_ASSET_TYPE_URI;
        String url = host + "" + uri+"/1";


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG,
                                "callModelDetailsForAssetCategory  response : "
                                        + response);

                        try {

                            apiResponse = objectMapper.readValue(response.toString(), APIResponse.class);

                            if (apiResponse != null) {

                                Log.i(TAG, "apiResponse :" + apiResponse.toString());

                                Object data = apiResponse.getData();

                                if (data != null) {

                                    String dataString = objectMapper.writeValueAsString(data);

                                    productDTOArrayList = objectMapper.readValue(
                                            dataString,
                                            TypeFactory.defaultInstance()
                                                    .constructCollectionType(
                                                            ArrayList.class,
                                                            ProductDTO.class));

                                   // CommonUtility.cancelProgressDailog(context);

                                    initRecyclerAdapter(productDTOArrayList);

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
                            "callModelDetailsForAssetCategory onErrorResponse : "
                                    + error.toString());

                }


                CommonUtility
                        .showServerResponseMessage(
                                context,
                                context.getResources()
                                        .getString(
                                                R.string.invalid_server_response_for_webservice)
                                        + " callModelDetailsForAssetCategory");


            }

        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

       // CommonUtility.showProgressDailog(context, "Fetching data...");

        ffmsRequestQueue.addToRequestQueue(jsonObjectRequest);



    }
}
