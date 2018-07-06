package com.happiest.minds.ffms.sr;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SRTabPagerAdapter extends FragmentPagerAdapter {

	public SRTabPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int arg0) {
		switch (arg0) {
		case 0:
			return new SRLeadDetailsFragment();
		case 1:
			return new SRLeadFlowFragment();
		case 2:
			return new SRLeadActivitiesFragment();
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
