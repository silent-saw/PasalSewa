package com.pasalsewa.pasalsewa;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lazyboy on 11/16/17.
 */

public class CustomerListAdapter extends ArrayAdapter<Customer> {
    Context context;

    public CustomerListAdapter(@NonNull Context context, ArrayList<Customer> list) {
        super(context, 0,list);
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view= LayoutInflater.from(context).inflate(R.layout.customer_list,null);
        TextView customer;

        customer=(TextView) view.findViewById(R.id.customer);

        final Customer customer1=getItem(position);
        customer.setText(customer1.customer_name);

        return  view;
    }
}
