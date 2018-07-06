package com.happiest.minds.ffms.sales;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.happiest.minds.ffms.R;

import java.util.Timer;
import java.util.TimerTask;

public class ProductImageFragment extends Fragment {

    private static final String TAG = ProductImageFragment.class.getSimpleName();

    View view;
    private ViewPager productImage_viewPager;

    int currentPage = 0;

    int NUM_PAGES = 0;

    Timer timer;

    ProductImagePageAdapter productImagePageAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_product_image, container, false);

        loadBanner();

        return view;
    }

    private void loadBanner() {

        int[] productImageBanners = {R.drawable.product_1,
                R.drawable.product_2, R.drawable.product_3};

        productImagePageAdapter = new ProductImagePageAdapter( SalesTicketDetailsFragment.context,productImageBanners);

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
