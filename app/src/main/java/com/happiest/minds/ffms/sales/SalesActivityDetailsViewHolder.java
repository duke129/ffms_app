package com.happiest.minds.ffms.sales;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.fasterxml.jackson.core.JsonGenerationException;
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
import com.happiest.minds.ffms.sales.pojo.OrderActivityUpdate;
import com.happiest.minds.ffms.sales.pojo.OrderVo;
import com.happiest.minds.ffms.sales.pojo.ProductCatalog;
import com.happiest.minds.ffms.sales.pojo.ProductDTO;
import com.happiest.minds.ffms.sales.pojo.TypeHeadVo;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class SalesActivityDetailsViewHolder extends ChildViewHolder {

    private static final String TAG = SalesActivityDetailsViewHolder.class.getSimpleName();
    public static Context context;
    private LinearLayout demo_activity_LL, order_activity_LL, product_recyclerView_LL, placeOrder_LL;
    private EditText customerComment_ET;
    private MaterialSpinner assetCategory_SP;
    private ArrayAdapter assetCategory_adapter, assetCategoryOr_adapter, assetQuantity_adapter, assetModel_adapter;
    public static RecyclerView product_recyclerView, order_recyclerView;
    private ProductRecyclerAdapter productRecyclerAdapter;
    public static OrderRecyclerAdapter orderRecyclerAdapter;
    FFMSRequestQueue ffmsRequestQueue;
    ObjectMapper objectMapper;
    ArrayList<TypeHeadVo> typeHeadVoArrayList;
    APIResponse apiResponse;
    int MY_SOCKET_TIMEOUT_MS = 30000;
    ArrayList<ProductDTO> productDTOArrayList;
    private static ArrayList<ProductDTO> orderedProductDTOArrayList = new ArrayList<>();

    OrderActivityUpdate orderActivityUpdate;

    String ticketId;

    ArrayList<OrderVo> orderVoArrayList;

    public static void addToOrderedProductDTOArrayList(ProductDTO productDTO) {
        orderedProductDTOArrayList.add(productDTO);
    }

    public static ArrayList<ProductDTO> getOrderedProductDTOArrayList() {
        return orderedProductDTOArrayList;
    }

    public static void removeFromList(int position) {

        if (position < orderedProductDTOArrayList.size()) {

            orderedProductDTOArrayList.remove(position);
        }


    }


    public SalesActivityDetailsViewHolder(View itemView) {
        super(itemView);

        context = SalesTicketDetailsFragment.context;
        ticketId = CommonUtility.getTicketId(context);
        demo_activity_LL = (LinearLayout) itemView.findViewById(R.id.demo_activity_LL);
        order_activity_LL = (LinearLayout) itemView.findViewById(R.id.order_activity_LL);
        assetCategory_SP = (MaterialSpinner) itemView.findViewById(R.id.assetCategory_SP);
        product_recyclerView_LL = (LinearLayout) itemView.findViewById(R.id.product_recyclerView_LL);
        product_recyclerView = (RecyclerView) itemView.findViewById(R.id.product_recyclerView);
        order_recyclerView = (RecyclerView) itemView.findViewById(R.id.order_recyclerView);
        placeOrder_LL = (LinearLayout) itemView.findViewById(R.id.placeOrder_LL);
        customerComment_ET = (EditText) itemView.findViewById(R.id.customerComment_ET);

        if (SalesTicketDetailsFragment.orderStatus == Constant.ACTIVITY_COMPLETED) {

            demo_activity_LL.setVisibility(View.GONE);
            customerComment_ET.setEnabled(false);
            placeOrder_LL.setVisibility(View.GONE);
            assetCategory_SP.setEnabled(false);

        }else{

            placeOrder_LL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String customerComment = customerComment_ET.getText().toString();

                    int totalProductCount = 0;
                    int totalAmount = 0;

                    orderActivityUpdate = new OrderActivityUpdate();

                    List<OrderVo> orderVoList = new ArrayList<>();


                    for (int i = 0; i < orderedProductDTOArrayList.size(); i++) {

                        OrderVo orderVo = new OrderVo();

                        orderVo.setPrice(orderedProductDTOArrayList.get(i).getPrice());
                        orderVo.setProductId(Long.parseLong(orderedProductDTOArrayList.get(i).getIdProduct()));
                        orderVo.setQuantity(OrderRecyclerAdapter.productCount[i]);
                        orderVoList.add(orderVo);

                        totalProductCount = OrderRecyclerAdapter.productCount[i] + totalProductCount;

                        totalAmount = totalAmount + (OrderRecyclerAdapter.productCount[i] * Integer.parseInt(orderedProductDTOArrayList.get(i).getPrice()));
                    }

                    orderActivityUpdate.setOrdersVo(orderVoList);
                    orderActivityUpdate.setComments(customerComment);
                    orderActivityUpdate.setTicketId(Long.parseLong(CommonUtility.getTicketId(context)));

                    showConfirmationAlertDialog(totalProductCount, totalAmount);


                }
            });
        }




    }


    public void onBind(SalesActivityDetails salesActivityDetails, ExpandableGroup group) {

        assetCategory_SP.setHint(R.string.assetCategory_hint);

        if (group.getTitle().equals(Constant.SALES_ACTIVITY_DEMO)) {

            if(SalesTicketDetailsFragment.orderStatus == Constant.COMPLETED) {

                demo_activity_LL.setVisibility(View.GONE);

            }else{

                demo_activity_LL.setVisibility(View.VISIBLE);
                order_activity_LL.setVisibility(View.GONE);

                callAssetTypeService();
            }

            //operationOnAssetTypeSelction();

            // initRecyclerAdapter();

        } else if (group.getTitle().equals(Constant.SALES_ACTIVITY_ORDER)) {

            if(SalesTicketDetailsFragment.orderStatus == Constant.ACTIVITY_COMPLETED) {

                demo_activity_LL.setVisibility(View.GONE);
                order_activity_LL.setVisibility(View.VISIBLE);
                Log.i(TAG, "orderedProductDTOArrayList : " + orderedProductDTOArrayList.size());

                callOrderSyncService(ticketId);

            }else{

                demo_activity_LL.setVisibility(View.GONE);
                order_activity_LL.setVisibility(View.VISIBLE);
                Log.i(TAG, "orderedProductDTOArrayList : " + orderedProductDTOArrayList.size());
                initOrderRecyclerAdapter(orderedProductDTOArrayList);
            }
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

        assetCategory_adapter = new ArrayAdapter(SalesTicketDetailsFragment.context, android.R.layout.simple_spinner_item, typeHeadVoArrayListArg);
        assetCategory_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        assetCategory_SP.setAdapter(assetCategory_adapter);
        assetCategory_SP.setPaddingSafe(0, 0, 0, 0);

        operationOnAssetTypeSelection(typeHeadVoArrayListArg);
    }

    private void initRecyclerAdapter(ArrayList<ProductDTO> productDTOArrayListArg) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        product_recyclerView.setLayoutManager(layoutManager);

        productRecyclerAdapter = new ProductRecyclerAdapter(context, productDTOArrayListArg);
        product_recyclerView.setAdapter(productRecyclerAdapter);
    }

    public static void initOrderRecyclerAdapter(ArrayList<ProductDTO> productDTOArrayListArg) {

        Log.i(TAG, "productDTOArrayListArg.size() : " + productDTOArrayListArg.size());

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        order_recyclerView.setLayoutManager(layoutManager);

        orderRecyclerAdapter = new OrderRecyclerAdapter(context, productDTOArrayListArg);
        order_recyclerView.setAdapter(orderRecyclerAdapter);
    }

    public static void initOrderRecyclerAdapterForCompletedOrder(ArrayList<OrderVo> orderVoArrayListArg) {

        Log.i(TAG, "orderVoArrayListArg.size() : " + orderVoArrayListArg.size());

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        order_recyclerView.setLayoutManager(layoutManager);
        orderRecyclerAdapter = new OrderRecyclerAdapter(context, orderVoArrayListArg, CommonUtility.getTicketId(context));
        order_recyclerView.setAdapter(orderRecyclerAdapter);
    }

    private void operationOnAssetTypeSelection(final ArrayList<TypeHeadVo> typeHeadVoArrayListArg) {

        assetCategory_SP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                int selectedAssetTypePosition = assetCategory_SP.getSelectedItemPosition();

                if (selectedAssetTypePosition > 0) {

                    TypeHeadVo typeHeadVo = typeHeadVoArrayListArg.get(selectedAssetTypePosition - 1);

                    Long assetTypeId = typeHeadVo.getId();

                    Log.i(TAG, " selectedAssetTypePosition : " + selectedAssetTypePosition + " assetTypeId : " + assetTypeId);

                    callModelDetailsForAssetCategory(assetTypeId);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void callModelDetailsForAssetCategory(Long assetTypeId) {

        ffmsRequestQueue = FFMSRequestQueue.getInstance(context);
        objectMapper = new ObjectMapper();

        String host = Webserver.SERVER_HOST;
        String uri = Webserver.MODEL_FOR_ASSET_TYPE_URI;
        String url = host + "" + uri + "/" + assetTypeId;


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG,
                                "callModelDetailsForAssetCategory  response : "
                                        + response);

                        CommonUtility.cancelProgressDailog(context);

                        try {

                            apiResponse = objectMapper.readValue(response.toString(), APIResponse.class);

                            if (apiResponse != null) {

                                Log.i(TAG, "apiResponse :" + apiResponse.toString());

                                Object data = apiResponse.getData();

                                if (data != null) {

                                    String dataString = objectMapper.writeValueAsString(data);

                                    if (dataString != null && !dataString.isEmpty()) {

                                        productDTOArrayList = objectMapper.readValue(
                                                dataString,
                                                TypeFactory.defaultInstance()
                                                        .constructCollectionType(
                                                                ArrayList.class,
                                                                ProductDTO.class));

                                        initRecyclerAdapter(productDTOArrayList);

                                    } else {

                                        Log.i(TAG, "dataString is null or empty");

                                        CommonUtility.showServerResponseMessage(context, "Data Not Available..!");
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

                CommonUtility.cancelProgressDailog(context);

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

        CommonUtility.showProgressDailog(context, "Fetching data...");

        ffmsRequestQueue.addToRequestQueue(jsonObjectRequest);


    }

    private void showConfirmationAlertDialog(int totalProductCount, int totalAmount) {

        Log.i(TAG, " showConfirmationAlertDialog ");

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        alertDialogBuilder.setMessage("Do you want to place order,  Number of product : " + totalProductCount + " Total Amount : " + totalAmount);
        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.setPositiveButton(
                context.getResources().getString(R.string.alert_button_yes),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        callOrderUpdateService();

                        dialog.cancel();
                    }

                });

        alertDialogBuilder.setNegativeButton(
                context.getResources().getString(R.string.alert_button_no),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDailog = alertDialogBuilder.create();
        alertDailog.show();
    }

    private void callOrderUpdateService() {

        ffmsRequestQueue = FFMSRequestQueue.getInstance(context);
        objectMapper = new ObjectMapper();

        try {

            String jsonData = objectMapper
                    .writeValueAsString(orderActivityUpdate);

            JSONObject jsonObject = new JSONObject(jsonData);

            String host = Webserver.SERVER_HOST;
            String uri = Webserver.ORDER_URI;

            String url = host + "" + uri;

            Log.i(TAG, "callOrderUpdateService url : " + url
                    + " jsonObject : " + jsonObject);

            if (context != null && !((Activity) context).isFinishing()) {
                CommonUtility.showProgressDailog(context,
                        "Please Wait...");
            }

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST, url, jsonObject,
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {

                            if (context != null
                                    && !((Activity) context).isFinishing()) {
                                CommonUtility.cancelProgressDailog(context);
                            }

                            Log.i(TAG,
                                    "callOrderUpdateService response : "
                                            + response);

                            CommonUtility.showToastMessage(context, "Order Placed successfully");

                            placeOrder_LL.setEnabled(false);

                            orderedProductDTOArrayList = new ArrayList<>();

                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {

                    Log.e(TAG,
                            "callCreateLeadService onErrorResponse : "
                                    + error);

                    if (context != null
                            && !((Activity) context).isFinishing()) {
                        CommonUtility.cancelProgressDailog(context);
                    }

                }
            });


            ffmsRequestQueue.addToRequestQueue(jsonObjectRequest);

        } catch (JsonGenerationException e)

        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e)

        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e)

        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void callOrderSyncService(String ticketId) {

        ffmsRequestQueue = FFMSRequestQueue.getInstance(context);
        objectMapper = new ObjectMapper();


        String host = Webserver.SERVER_HOST;
        String uri = Webserver.ORDER_SYNC_URI;

        String url = host + "" + uri+"/"+ticketId;

        Log.i(TAG, "callOrderSyncService url : " + url);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG,
                                "callOrderSyncService  response : "
                                        + response);

                        try {

                            apiResponse = objectMapper.readValue(response.toString(), APIResponse.class);

                            if (apiResponse != null) {

                                Log.i(TAG, "apiResponse :" + apiResponse.toString());

                                Object data = apiResponse.getData();

                                if (data != null) {

                                    String dataString = objectMapper.writeValueAsString(data);

                                    OrderActivityUpdate orderActivityUpdate = objectMapper.readValue(
                                            dataString,OrderActivityUpdate.class);

                                    customerComment_ET.setText(orderActivityUpdate.getComments());

                                    orderVoArrayList = (ArrayList<OrderVo>) orderActivityUpdate.getOrdersVo();

                                    initOrderRecyclerAdapterForCompletedOrder(orderVoArrayList);

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

                Log.e(TAG,
                        "callOrderSyncService onErrorResponse : "
                                + error);


            }
        });


        ffmsRequestQueue.addToRequestQueue(jsonObjectRequest);


    }

}
