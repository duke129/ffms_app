package com.happiest.minds.ffms.sales;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.happiest.minds.ffms.R;


public class ProductVideoFragment extends Fragment {

    private static final String TAG = ProductVideoFragment.class.getSimpleName();
    View view;
    public static MediaController mc;
    public static VideoView videoView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_product_video, container, false);

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();

        Log.i(TAG, "onResume");
        mc = new MediaController(getActivity());
        videoView = (VideoView) view.findViewById(R.id.video_view);
        String path = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.demo_1;
        videoView.setVideoURI(Uri.parse(path));
        videoView.setMediaController(mc);
        //videoView.start();

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser && getView() != null);
        if (isVisibleToUser) {
            //fragment visible play video

            if (videoView != null) {
                videoView.start();
            }
        } else {
            //fragment not visible pause video

            if (videoView != null) {
                videoView.pause();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        videoView.stopPlayback();
        Log.i(TAG, " onPause");
    }
}
