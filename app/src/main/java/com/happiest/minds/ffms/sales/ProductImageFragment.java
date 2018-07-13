package com.happiest.minds.ffms.sales;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.happiest.minds.ffms.CommonUtility;
import com.happiest.minds.ffms.FFMSRequestQueue;
import com.happiest.minds.ffms.R;
import com.happiest.minds.ffms.Webserver;
import com.happiest.minds.ffms.sales.pojo.APIResponse;
import com.happiest.minds.ffms.sales.pojo.ImageDTO;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ProductImageFragment extends Fragment {

    private static final String TAG = ProductImageFragment.class.getSimpleName();

    Context context = SalesTicketDetailsFragment.context;

    View view;
    private ViewPager productImage_viewPager;

    int currentPage = 0;

    int NUM_PAGES = 0;

    Timer timer;

    ProductImagePageAdapter productImagePageAdapter;

    FFMSRequestQueue ffmsRequestQueue;
    ObjectMapper objectMapper;
    APIResponse apiResponse;

    public static ArrayList<ImageDTO> imageDTOArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_product_image, container, false);

        ffmsRequestQueue = FFMSRequestQueue.getInstance(context);
        objectMapper = new ObjectMapper();

        callImageService();

        return view;
    }

    private void callImageService() {

        String clickedProductModelId = CommonUtility.getSelectedProductId(context);

        String host = Webserver.SERVER_HOST;
        String uri = Webserver.IMAGE_URI;
        String url = host + "" + uri + "/" + clickedProductModelId;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        Log.i(TAG,
                                "callImageService response : "
                                        + response);

                        try {

                            apiResponse = objectMapper.readValue(response.toString(), APIResponse.class);

                            if (apiResponse != null) {

                                Object data = apiResponse.getData();

                                if (data != null) {

                                    String dataString = objectMapper.writeValueAsString(data);

                                    imageDTOArrayList = objectMapper.readValue(
                                            dataString,
                                            TypeFactory.defaultInstance()
                                                    .constructCollectionType(
                                                            ArrayList.class,
                                                            ImageDTO.class));

                                    loadBanner(imageDTOArrayList);
                                }
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e(TAG,
                        "callImageService onErrorResponse : "
                                + error);

            }
        });


        ffmsRequestQueue.addToRequestQueue(jsonObjectRequest);

    }


    private void loadBanner(ArrayList<ImageDTO> imageDTOArrayListArg) {

        String[] base64ImageArrayString = new String[imageDTOArrayListArg.size()];

        for (int i = 0; i < imageDTOArrayListArg.size(); i++) {

            base64ImageArrayString[i] = imageDTOArrayListArg.get(i).getImage();

        }

        Log.i(TAG, " imageDTOArrayListArg.size() " + imageDTOArrayListArg.size());
        Log.i(TAG, " base64ImageArrayString.size() " + base64ImageArrayString.length);

        productImagePageAdapter = new ProductImagePageAdapter(SalesTicketDetailsFragment.context, base64ImageArrayString);

        productImage_viewPager = (ViewPager) view.findViewById(R.id.productImage_viewpager);

        productImage_viewPager.setAdapter(productImagePageAdapter);

        NUM_PAGES = productImagePageAdapter.getCount();

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                productImage_viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 1000, 3000);
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.i(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.i(TAG, "onPause ");
    }
}
