package com.happiest.minds.ffms.sales;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.happiest.minds.ffms.R;

public class ProductImagePageAdapter extends PagerAdapter {

	private static final String TAG = ProductImagePageAdapter.class.getSimpleName();
	Context mContext;
	LayoutInflater mLayoutInflater;

	/*
	 * int[] mResources = { R.drawable.act_banner_first,
	 * R.drawable.act_banner_second, R.drawable.act_banner_third };
	 */

	String[] imageBase64ArrayString;

	public ProductImagePageAdapter(Context context, String[] imageBase64ArrayStringArg) {

		Log.i(TAG, " base64ImageArrayString.size() "+imageBase64ArrayStringArg.length);
		mContext = context;
		mLayoutInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageBase64ArrayString = imageBase64ArrayStringArg;
	}

	@Override
	public int getCount() {
		return imageBase64ArrayString.length;
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

		byte[] decodedString = Base64.decode(imageBase64ArrayString[position], Base64.DEFAULT);
		Bitmap decodedByteSecond = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
		imageView.setImageBitmap(decodedByteSecond);

		container.addView(itemView);
		return itemView;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((LinearLayout) object);
	}
}
