package com.pasalsewa.pasalsewa.helper.viewholders.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pasalsewa.pasalsewa.Bill;
import com.pasalsewa.pasalsewa.BillParticulars;
import com.pasalsewa.pasalsewa.DatabaseHelper;
import com.pasalsewa.pasalsewa.R;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aezirus on 12/2/2017.
 */

public class BillParticularsRecyclerViewAdapter extends RecyclerView.Adapter<BillParticularsRecyclerViewAdapter.BillParticularsViewHolder> {


    Context context;
    public BillParticularsRecyclerViewAdapter(ArrayList<BillParticulars>list,Context context){
        this.list=list;
        this.context = context;
    }

    @Override
    public BillParticularsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_item_list_layout,null);

        return new BillParticularsViewHolder(view,context);
    }

    public ArrayList<BillParticulars>list;
    @Override
    public void onBindViewHolder(BillParticularsViewHolder holder, int position) {
        BillParticulars billParticulars =list.get(position);
        holder.setAttributes(billParticulars.getItem_id(),billParticulars.getItem_price(),billParticulars.getItem_qty());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BillParticularsViewHolder extends RecyclerView.ViewHolder {

        public TextView item_name,item_price,item_qty,subtotal_item_price;
        DatabaseHelper databaseHelper;
        Context context;

        public BillParticularsViewHolder(View itemView,Context context) {
            super(itemView);
            this.context= context;
            databaseHelper = new DatabaseHelper(context);
            item_name = itemView.findViewById(R.id.item_name);
            item_qty = itemView.findViewById(R.id.item_qty);
            item_price = itemView.findViewById(R.id.item_price);
            subtotal_item_price = itemView.findViewById(R.id.item_total_price);

        }

        public void setAttributes(int itemId, int itemQty, int itemPrice){

            item_name.setText(databaseHelper.getItemName(itemId));
            item_qty.setText(""+itemQty);
            item_price.setText(""+itemPrice);
            subtotal_item_price.setText(""+itemQty*itemPrice);

        }
    }
}
