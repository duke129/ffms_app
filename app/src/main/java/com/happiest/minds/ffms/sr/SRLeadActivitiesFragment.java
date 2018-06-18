package com.happiest.minds.ffms.sr;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class SRLeadActivitiesFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		TextView tv = new TextView(getActivity());
		tv.setText("Third Tab");
		tv.setGravity(Gravity.CENTER);
		tv.setTextColor(Color.WHITE);
		tv.setWidth(LayoutParams.MATCH_PARENT);
		tv.setHeight(LayoutParams.MATCH_PARENT);
		tv.setBackgroundColor(Color.CYAN);
		tv.setTextAppearance(getActivity(),
				android.R.style.TextAppearance_Large);
		return tv;
	}

}
