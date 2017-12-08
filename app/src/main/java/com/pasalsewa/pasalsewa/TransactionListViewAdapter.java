package com.pasalsewa.pasalsewa;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Aezirus on 11/28/2017.
 */

public class TransactionListViewAdapter extends ArrayAdapter<BillParticulars> {

    Context context;


    public TransactionListViewAdapter(@NonNull Context context, ArrayList<BillParticulars> list) {
        super(context, 0, list);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.bill_item_list_layout, null);

        TextView item_name, item_qty, item_price, item_total_price;
        item_name = view.findViewById(R.id.item_name);
        item_qty = view.findViewById(R.id.item_qty);
        item_price = view.findViewById(R.id.item_price);
        item_total_price = view.findViewById(R.id.item_total_price);


        BillParticulars billParticulars= getItem(position);
        item_qty.setText(billParticulars.item_qty+"");
        item_name.setText(billParticulars.item_id+"");
        item_price.setText(billParticulars.item_price+"");


        int subtotal_item = (billParticulars.item_qty) * (billParticulars.item_price);
        item_total_price.setText(subtotal_item+"");

        return view;


    }
}
