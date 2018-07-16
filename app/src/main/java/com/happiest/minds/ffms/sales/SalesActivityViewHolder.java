package com.happiest.minds.ffms.sales;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.happiest.minds.ffms.CommonUtility;
import com.happiest.minds.ffms.Constant;
import com.happiest.minds.ffms.R;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import org.w3c.dom.Text;

public class SalesActivityViewHolder extends GroupViewHolder {

    private static final String TAG = SalesActivityViewHolder.class.getSimpleName();
    Context context = SalesTicketDetailsFragment.context;

    private ImageView statusIcon_IV, arrow_IV;
    private TextView activityName_TV;
    private LinearLayout header_Name_LL;
    String ticketId = CommonUtility.getTicketId(context);
    int orderStatus = CommonUtility.getOrderStatus(context,ticketId);


    public SalesActivityViewHolder(View itemView) {
        super(itemView);

        statusIcon_IV = (ImageView) itemView.findViewById(R.id.statusIcon_IV);
        arrow_IV = (ImageView) itemView.findViewById(R.id.arrow_IV);
        activityName_TV = (TextView) itemView.findViewById(R.id.activityName_TV);
        header_Name_LL = (LinearLayout) itemView.findViewById(R.id.header_Name_LL);
    }

    @Override
    public void expand() {
        arrow_IV.setImageResource(R.drawable.down_arrow);
        header_Name_LL.setBackgroundColor(Color.parseColor("#3F51B5"));
        activityName_TV.setTextColor(Color.parseColor("#FFFFFF"));


        Log.i("Adapter", "expand");
    }

    @Override
    public void collapse() {
        arrow_IV.setImageResource(R.drawable.up_arrow);
        header_Name_LL.setBackgroundColor(Color.parseColor("#D42E27"));
        activityName_TV.setTextColor(Color.parseColor("#FFFFFF"));
        Log.i("Adapter", "collapse");

    }

    public void setActivityBand(ExpandableGroup group) {
        activityName_TV.setText(group.getTitle());

        if(orderStatus == Constant.ACTIVITY_COMPLETED){

            statusIcon_IV.setImageResource(R.drawable.greencircle);

            Log.i(TAG, "greencircle");
        }else{

            statusIcon_IV.setImageResource(R.drawable.gray);

            Log.i(TAG, "graycircle");
        }
    }
}
