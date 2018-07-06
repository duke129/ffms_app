package com.happiest.minds.ffms.sales;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.happiest.minds.ffms.R;

public class ProductImagePageAdapter extends PagerAdapter {

	Context mContext;
	LayoutInflater mLayoutInflater;

	/*
	 * int[] mResources = { R.drawable.act_banner_first,
	 * R.drawable.act_banner_second, R.drawable.act_banner_third };
	 */

	int[] mResources;

	public ProductImagePageAdapter(Context context, int[] images) {
		mContext = context;
		mLayoutInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mResources = images;
	}

	@Override
	public int getCount() {
		return mResources.length;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == ((LinearLayout) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View itemView = mLayoutInflater.inflate(R.layout.pager_item, container,
				false);

		ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
		imageView.setImageResource(mResources[position]);

		container.addView(itemView);

		return itemView;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((LinearLayout) object);
	}
}
