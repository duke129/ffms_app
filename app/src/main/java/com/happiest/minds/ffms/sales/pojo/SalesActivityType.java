package com.happiest.minds.ffms.sales.pojo;

import android.content.Context;


import com.happiest.minds.ffms.Constant;
import com.happiest.minds.ffms.R;

import java.util.ArrayList;
import java.util.List;

public class  SalesActivityType {
	private static SalesActivityType salesActivityType;

	private ArrayList<SalesActivityName> salesActivityNames;

	final String[] salesActivityTitles = {Constant.SALES_ACTIVITY_DEMO,
			Constant.SALES_ACTIVITY_ORDER};

	final int[] activityStatusIcons = {R.drawable.gray, R.drawable.gray };

	public static SalesActivityType get(Context context) {
		if (salesActivityType == null) {
			salesActivityType = new SalesActivityType(context);
		}
		return salesActivityType;
	}

	private SalesActivityType(Context context) {
		salesActivityNames = new ArrayList<>();
		for (int i = 0; i < salesActivityTitles.length; i++) {
			SalesActivityName salesActivityName = new SalesActivityName();

			salesActivityName.setName(salesActivityTitles[i]);
			salesActivityName.setThumbnail(activityStatusIcons[i]);
			salesActivityNames.add(salesActivityName);
		}
	}

	public List<SalesActivityName> getTicketNames() {
		return salesActivityNames;
	}

}
