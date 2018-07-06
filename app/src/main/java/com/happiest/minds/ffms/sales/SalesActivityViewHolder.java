package com.happiest.minds.ffms.sales;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.happiest.minds.ffms.R;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import org.w3c.dom.Text;

public class SalesActivityViewHolder extends GroupViewHolder {

    private ImageView statusIcon_IV, arrow_IV;
    private TextView activityName_TV;
    private LinearLayout header_Name_LL;

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

    public void setActivityName(ExpandableGroup group) {
        activityName_TV.setText(group.getTitle());
    }
}
