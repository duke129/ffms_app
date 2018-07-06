package com.happiest.minds.ffms.sr;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.happiest.minds.ffms.R;

public class SRLeadsDetailsActivity extends AppCompatActivity implements ActionBar.TabListener, View.OnClickListener {

    Context context;
    private ViewPager viewPager;
    ActionBar actionBar;
    View actionBar_V;
    ImageView backButton_IV;
    private SRTabPagerAdapter tabPagerAdapter;
    private String[] tabNames = { "Details", "Flow", "Activities" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_srleads_details);
        context = this;
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabPagerAdapter = new SRTabPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabPagerAdapter);
        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        for (int i = 0; i < 3; i++) {
            actionBar.addTab(actionBar.newTab().setText(tabNames[i])
                    .setTabListener(this));
        }

        LayoutInflater mInflater = LayoutInflater.from(this);
        actionBar_V = mInflater.inflate(R.layout.custom_actionbar_sr_lead_home,
                null);
        actionBar.setCustomView(actionBar_V);
        actionBar.setDisplayShowCustomEnabled(true);

        backButton_IV = (ImageView) actionBar_V
                .findViewById(R.id.backButton_IV);

        backButton_IV.setOnClickListener(this);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int postion) {
                actionBar.setSelectedNavigationItem(postion);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.backButton_IV:

                finish();
        }
    }
}
