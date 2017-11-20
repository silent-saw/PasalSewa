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
 * Created by agubha on 11/19/2017.
 */

public class AddToCartAdapter extends ArrayAdapter<AddToCart> {
    Context context;

    public AddToCartAdapter(@NonNull Context context, int resource, ArrayList<AddToCart> list) {
        super(context, 0, list);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_layout,null);

        TextView item_id_cart, item_name_cart, item_price_cart, item_quantity_cart;
        item_id_cart = view.findViewById(R.id.item_id_cart);
        item_name_cart = view.findViewById(R.id.item_name_cart);
        item_price_cart = view.findViewById(R.id.item_price_cart);
        item_quantity_cart = view.findViewById(R.id.item_qty_cart);


        AddToCart addToCart = getItem(position);
        item_id_cart.setText(addToCart.item_id);
        item_name_cart.setText(addToCart.item_name);
        item_price_cart.setText(addToCart.item_price);
        item_quantity_cart.setText(addToCart.item_quantity);
        return view;


    }
}
