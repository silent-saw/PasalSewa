package com.pasalsewa.pasalsewa.helper.viewholders;

import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.pasalsewa.pasalsewa.R;

import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

/**
 * Created by Aezirus on 11/20/2017.
 */

public class ParentViewHolder extends GroupViewHolder {

    TextView bill_time,bill_amount;
    LinearLayout linearLayout;
    public ParentViewHolder(View itemView) {
        super(itemView);
        linearLayout = itemView.findViewById(R.id.linear_layout);
        bill_amount = (TextView) itemView.findViewById(R.id.bill_amount);
        bill_time = (TextView)itemView.findViewById(R.id.bill_time);
    }

    public void setParentAttributes(String billAmount){
        linearLayout.setBackgroundColor(R.color.colorBackground);
        bill_amount.setText(billAmount);
    }

}
