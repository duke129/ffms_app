package com.happiest.minds.ffms.sales;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.happiest.minds.ffms.R;
import com.happiest.minds.ffms.sales.pojo.SalesActivityName;

import java.util.List;

public class SalesActivityExpandableAdapter extends ExpandableRecyclerAdapter<SalesActivityExpandableAdapter.ActivityParentViewHolder, SalesActivityExpandableAdapter.ActivityChildViewHolder> {

    private static final String TAG = SalesActivityExpandableAdapter.class.getSimpleName();
    private static final float INITIAL_POSITION = 0.0f;
    private static final float ROTATED_POSITION = 180f;
    private LayoutInflater mInflater;
    ActivityParentViewHolder activityParentViewHolder;
    int position;
    ParentListItem parentListItem;
    Context context;
    public static int flag;
    public SalesActivityName salesActivityName;

    public SalesActivityExpandableAdapter(Context contextCons,@NonNull List<? extends ParentListItem> parentItemList) {
        super(parentItemList);

        this.context = contextCons;

        mInflater = LayoutInflater.from(contextCons);


    }

    @Override
    public ActivityParentViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {

        View parentView = mInflater.inflate(R.layout.activity_accordian_layout,
                parentViewGroup, false);

        activityParentViewHolder = new ActivityParentViewHolder(parentView);

        return activityParentViewHolder;
    }

    @Override
    public ActivityChildViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {

        View childView = mInflater
                .inflate(
                        R.layout.sales_activity_details,
                        childViewGroup, false);

        ActivityChildViewHolder activityChildViewHolder = new ActivityChildViewHolder(
                childView);

        return activityChildViewHolder;

    }


    @Override
    public void onBindParentViewHolder(ActivityParentViewHolder activityParentViewHolderCons, int positionCons, ParentListItem parentListItemCons) {

        activityParentViewHolder = activityParentViewHolderCons;

        position = positionCons;

        parentListItem = parentListItemCons;

        parentListItem = parentListItemCons;

        salesActivityName = (SalesActivityName) parentListItem;

        activityParentViewHolder.tvTicketName.setText(salesActivityName.getName());
    }

    @Override
    public void onBindChildViewHolder(ActivityChildViewHolder activityChildViewHolderCons, int positionCons,
                                      Object childListItemCons) {


        switch (flag) {

            case 0:


                activityChildViewHolderCons.demo_activity_LL.setVisibility(View.VISIBLE);
                activityChildViewHolderCons.order_activity_LL.setVisibility(View.GONE);

                return;

            case 1:

                activityChildViewHolderCons.order_activity_LL.setVisibility(View.VISIBLE);
                activityChildViewHolderCons.demo_activity_LL.setVisibility(View.GONE);

                return;

             default:

                 Log.i(TAG, "no layout");

        }

    }

    public class ActivityParentViewHolder extends ParentViewHolder {

        public TextView tvTicketName;
        public ImageView mArrowExpandImageView, status_Img;

        public LinearLayout headerView_Ll;

        public ActivityParentViewHolder(View itemView) {
            super(itemView);

            tvTicketName = (TextView) itemView.findViewById(R.id.textView1);
            mArrowExpandImageView = (ImageView) itemView
                    .findViewById(R.id.imageUpArrow);
            headerView_Ll = (LinearLayout) itemView
                    .findViewById(R.id.header_Name_LL);
            status_Img = (ImageView) itemView.findViewById(R.id.imageIcon);

        }

        public void onActivityExpand() {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {

                mArrowExpandImageView.setImageResource(R.drawable.up_arrow);
                headerView_Ll.setBackgroundColor(Color.parseColor("#D42E27"));
                tvTicketName.setTextColor(Color.parseColor("#FFFFFF"));

                RotateAnimation rotateAnimation;
                // rotate clockwise
                rotateAnimation = new RotateAnimation(ROTATED_POSITION,
                        INITIAL_POSITION, RotateAnimation.RELATIVE_TO_SELF,
                        0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);

                rotateAnimation.setDuration(200);
                rotateAnimation.setFillAfter(true);
                mArrowExpandImageView.startAnimation(rotateAnimation);
            }

        }

        public void onActivityCompress() {

            mArrowExpandImageView.setImageResource(R.drawable.down_arrow);
            headerView_Ll.setBackgroundColor(Color.parseColor("#3F51B5"));
            RotateAnimation rotateAnimation;
            rotateAnimation = new RotateAnimation(-1 * ROTATED_POSITION,
                    INITIAL_POSITION, RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                    RotateAnimation.RELATIVE_TO_SELF, 0.5f);

            rotateAnimation.setDuration(200);
            rotateAnimation.setFillAfter(true);
            mArrowExpandImageView.startAnimation(rotateAnimation);
            tvTicketName.setTextColor(Color.parseColor("#FFFFFF"));
        }

        @Override
        public void setExpanded(boolean expanded) {
            // TODO Auto-generated method stub
            super.setExpanded(expanded);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                if (expanded) {

                    mArrowExpandImageView.setImageResource(R.drawable.up_arrow);
                    headerView_Ll.setBackgroundColor(Color
                            .parseColor("#D42E27"));
                    tvTicketName.setTextColor(Color.parseColor("#FFFFFF"));

                } else {
                    mArrowExpandImageView
                            .setImageResource(R.drawable.down_arrow);
                    headerView_Ll.setBackgroundColor(Color
                            .parseColor("#3F51B5"));
                    tvTicketName.setTextColor(Color.parseColor("#FFFFFF"));
                }
            }

        }

        @Override
        public void onExpansionToggled(boolean expanded) {
            // TODO Auto-generated method stub
            super.onExpansionToggled(expanded);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                RotateAnimation rotateAnimation;
                if (expanded) { // rotate clockwise
                    rotateAnimation = new RotateAnimation(ROTATED_POSITION,
                            INITIAL_POSITION, RotateAnimation.RELATIVE_TO_SELF,
                            0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                } else { // rotate counterclockwise
                    rotateAnimation = new RotateAnimation(
                            -1 * ROTATED_POSITION, INITIAL_POSITION,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                }

                rotateAnimation.setDuration(200);
                rotateAnimation.setFillAfter(true);
                mArrowExpandImageView.startAnimation(rotateAnimation);
            }
        }
    }

    public class ActivityChildViewHolder extends ChildViewHolder implements
            View.OnClickListener {

        LinearLayout demo_activity_LL, order_activity_LL;


        public ActivityChildViewHolder(View itemView) {
            super(itemView);

            demo_activity_LL = itemView.findViewById(R.id.demo_activity_LL);
            order_activity_LL = itemView.findViewById(R.id.order_activity_LL);

        }


        @Override
        public void onClick(View v) {

        }
    }
}








