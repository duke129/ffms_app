package com.happiest.minds.ffms;

import java.io.InputStream;
import java.security.KeyStore;


import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.Volley;

import org.apache.http.params.HttpParams;

public class FFMSRequestQueue {

    private static FFMSRequestQueue mInstance;
    private RequestQueue mRequestQueue;
    private Context mCtx;
    private HttpParams params;
    private boolean isHttpsEnabled = false;

    private FFMSRequestQueue(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized FFMSRequestQueue getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new FFMSRequestQueue(context);
        }
        return mInstance;
    }


    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {


            mRequestQueue = Volley.newRequestQueue(
                    mCtx.getApplicationContext());

        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);

    }
}
