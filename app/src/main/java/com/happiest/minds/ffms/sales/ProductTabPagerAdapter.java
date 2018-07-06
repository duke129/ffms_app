package com.happiest.minds.ffms.sales;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ProductTabPagerAdapter extends FragmentPagerAdapter {

    public ProductTabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int arg0) {
        switch (arg0) {
            case 0:

                return new ProductImageFragment();

            case 1:

                return new ProductDetailsFragment();

            case 2:

                return new ProductVideoFragment();

            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

}
