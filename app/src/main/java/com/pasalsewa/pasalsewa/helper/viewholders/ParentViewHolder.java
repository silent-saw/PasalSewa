package com.pasalsewa.pasalsewa.helper.viewholders;

import android.view.View;
import android.widget.TextView;
import com.pasalsewa.pasalsewa.R;

import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

/**
 * Created by Aezirus on 11/20/2017.
 */

public class ParentViewHolder extends GroupViewHolder {

    TextView bill_time,bill_amount;
    public ParentViewHolder(View itemView) {
        super(itemView);
        bill_amount = (TextView) itemView.findViewById(R.id.bill_amount);
        bill_time = (TextView)itemView.findViewById(R.id.bill_time);
    }

    public void setParentAttributes(String billTime){
        bill_time.setText(billTime);
    }

}
