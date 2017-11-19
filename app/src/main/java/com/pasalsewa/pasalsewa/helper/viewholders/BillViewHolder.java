package com.pasalsewa.pasalsewa.helper.viewholders;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import com.pasalsewa.pasalsewa.R;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

/**
 * Created by Aezirus on 11/20/2017.
 */

public class BillViewHolder extends ChildViewHolder {

    TextView customer_name, bill_id, bill_amount, bill_paid, bill_due, status;

    public BillViewHolder(View itemView) {
        super(itemView);
        customer_name = (TextView) itemView.findViewById(R.id.customer_name);
        bill_id = (TextView) itemView.findViewById(R.id.bill_id);
        bill_amount = (TextView) itemView.findViewById(R.id.bill_amount);
        bill_paid = (TextView) itemView.findViewById(R.id.bill_paid);
        bill_due = (TextView) itemView.findViewById(R.id.bill_due);
        status = (TextView) itemView.findViewById(R.id.status);
    }

    @SuppressLint("ResourceAsColor")
    public void setBillAttributes(String customerName, int billId, int billAmount, int billPaid, int billDue, String billStatus) {


        customer_name.setText(customerName);
        bill_id.setText(String.valueOf(billId));
        bill_amount.setText(String.valueOf(billAmount));
        bill_paid.setText(String.valueOf(billPaid));
        bill_due.setText(String.valueOf(billDue));
        status.setText(billStatus);

    }
}
