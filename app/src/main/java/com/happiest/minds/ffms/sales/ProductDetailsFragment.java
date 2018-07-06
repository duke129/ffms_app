package com.happiest.minds.ffms.sales;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import com.happiest.minds.ffms.sales.pojo.ProductSpecificationDTO;
import com.happiest.minds.ffms.sales.pojo.TypeHeadVo;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


public class ProductDetailsFragment extends Fragment {

    private static final String TAG = ProductDetailsFragment.class.getSimpleName();

    View view;

    Context context;

    TextView productName_TV,grossCapacity_TV,grossCapacityValue_TV,cyclopentaneInsulation_TV,cyclopentaneInsulationValue_TV,
            worksWithoutStabilizer_TV,worksWithoutStabilizerValue_TV,starRating_TV,starRatingValue_TV,warranty_TV,warrantyValue_TV,
            door_TV,doorValue_TV,width_TV,widthValue_TV,depth_TV,depthValue_TV,height_TV,heightValue_TV;

    FFMSRequestQueue ffmsRequestQueue;
    ObjectMapper objectMapper;
    ArrayList<ProductSpecificationDTO> productSpecificationDTOArrayList;
    APIResponse apiResponse;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_product_details, container, false);

        context = SalesTicketDetailsFragment.context;

        ffmsRequestQueue = FFMSRequestQueue.getInstance(context);
        objectMapper = new ObjectMapper();

        initGUI();

        return view;
    }


    private void initGUI(){

        productName_TV = (TextView) view.findViewById(R.id.productName_TV);
        grossCapacity_TV = (TextView) view.findViewById(R.id.grossCapacity_TV);
        grossCapacityValue_TV = (TextView) view.findViewById(R.id.grossCapacityValue_TV);
        cyclopentaneInsulation_TV = (TextView) view.findViewById(R.id.cyclopentaneInsulation_TV);
        cyclopentaneInsulationValue_TV = (TextView) view.findViewById(R.id.cyclopentaneInsulationValue_TV);
        worksWithoutStabilizer_TV = (TextView) view.findViewById(R.id.worksWithoutStabilizer_TV);
        worksWithoutStabilizerValue_TV = (TextView) view.findViewById(R.id.worksWithoutStabilizerValue_TV);
        starRating_TV = (TextView) view.findViewById(R.id.starRating_TV);
        starRatingValue_TV = (TextView) view.findViewById(R.id.starRatingValue_TV);
        warranty_TV = (TextView) view.findViewById(R.id.warranty_TV);
        warrantyValue_TV = (TextView) view.findViewById(R.id.warrantyValue_TV);
        door_TV = (TextView) view.findViewById(R.id.door_TV);
        doorValue_TV = (TextView) view.findViewById(R.id.doorValue_TV);
        width_TV = (TextView) view.findViewById(R.id.width_TV);
        widthValue_TV = (TextView) view.findViewById(R.id.widthValue_TV);
        depth_TV = (TextView) view.findViewById(R.id.depth_TV);
        depthValue_TV = (TextView) view.findViewById(R.id.depthValue_TV);
        height_TV = (TextView) view.findViewById(R.id.height_TV);
        heightValue_TV = (TextView) view.findViewById(R.id.heightValue_TV);

        callSpecificationService();

    }

    private void callSpecificationService(){

        String productId = CommonUtility.getSelectedProductId(context);

        String host = Webserver.SERVER_HOST;
        String uri = Webserver.MODEL_SPECIFICATION_URI;
        String url = host + "" + uri+"/"+productId;

        Log.i(TAG, "url : "+url);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG,
                                "callSpecificationService  response : "
                                        + response);

                        try {

                            apiResponse = objectMapper.readValue(response.toString(), APIResponse.class);

                            if (apiResponse != null) {

                                Log.i(TAG, "apiResponse :" + apiResponse.toString());

                                Object data = apiResponse.getData();

                                if (data != null) {

                                    String dataString = objectMapper.writeValueAsString(data);

                                    productSpecificationDTOArrayList = objectMapper.readValue(
                                            dataString,
                                            TypeFactory.defaultInstance()
                                                    .constructCollectionType(
                                                            ArrayList.class,
                                                            ProductSpecificationDTO.class));

                                    setDetailsToUI(productSpecificationDTOArrayList);

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
                            "callSpecificationService onErrorResponse : "
                                    + error.toString());

                }


                CommonUtility
                        .showServerResponseMessage(
                                context,
                                context.getResources()
                                        .getString(
                                                R.string.invalid_server_response_for_webservice)
                                        + " callSpecificationService");


            }

        });

        ffmsRequestQueue.addToRequestQueue(jsonObjectRequest);

    }

    private void setDetailsToUI(ArrayList<ProductSpecificationDTO> productSpecificationDTOArrayListArg){

        int objectIndex = 0;
        int listSize = productSpecificationDTOArrayListArg.size();

        //productName_TV.setText(productSpecificationDTOArrayListArg.get(objectIndex).getPropertyName());


        if(objectIndex< (listSize - 1)){

            objectIndex = (objectIndex + 1);

            cyclopentaneInsulation_TV.setText(productSpecificationDTOArrayListArg.get(objectIndex).getPropertyName());
            cyclopentaneInsulationValue_TV.setText(productSpecificationDTOArrayListArg.get(objectIndex).getPropertyValue());
        }

        if(objectIndex< (listSize - 1)){

            objectIndex = (objectIndex + 1);

            worksWithoutStabilizer_TV.setText(productSpecificationDTOArrayListArg.get(objectIndex).getPropertyName());
            worksWithoutStabilizerValue_TV.setText(productSpecificationDTOArrayListArg.get(objectIndex).getPropertyValue());
        }

        if(objectIndex< (listSize - 1)){

            objectIndex = (objectIndex + 1);

            starRating_TV.setText(productSpecificationDTOArrayListArg.get(objectIndex).getPropertyName());
            starRatingValue_TV.setText(productSpecificationDTOArrayListArg.get(objectIndex).getPropertyValue());
        }

        if(objectIndex< (listSize - 1)){

            objectIndex = (objectIndex + 1);

            warranty_TV.setText(productSpecificationDTOArrayListArg.get(objectIndex).getPropertyName());
            warrantyValue_TV.setText(productSpecificationDTOArrayListArg.get(objectIndex).getPropertyValue());
        }

        if(objectIndex< (listSize - 1)){

            objectIndex = (objectIndex + 1);

            door_TV.setText(productSpecificationDTOArrayListArg.get(objectIndex).getPropertyName());
            doorValue_TV.setText(productSpecificationDTOArrayListArg.get(objectIndex).getPropertyValue());
        }

        if(objectIndex< (listSize - 1)){

            objectIndex = (objectIndex + 1);

            width_TV.setText(productSpecificationDTOArrayListArg.get(objectIndex).getPropertyName());
            widthValue_TV.setText(productSpecificationDTOArrayListArg.get(objectIndex).getPropertyValue());
        }

        if(objectIndex< (listSize - 1)){

            objectIndex = (objectIndex + 1);

            depth_TV.setText(productSpecificationDTOArrayListArg.get(objectIndex).getPropertyName());
            depthValue_TV.setText(productSpecificationDTOArrayListArg.get(objectIndex).getPropertyValue());
        }

        if(objectIndex< (listSize - 1)){

            objectIndex = (objectIndex + 1);

            height_TV.setText(productSpecificationDTOArrayListArg.get(objectIndex).getPropertyName());
            heightValue_TV.setText(productSpecificationDTOArrayListArg.get(objectIndex).getPropertyValue());
        }
    }
}
