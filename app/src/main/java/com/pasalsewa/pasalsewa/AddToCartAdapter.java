package com.pasalsewa.pasalsewa;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by agubha on 11/19/2017.
 */

public class AddToCartAdapter extends ArrayAdapter<AddToCart> {
    Context context;


    public AddToCartAdapter(@NonNull Context context, ArrayList<AddToCart> list) {


        super(context, 0, list);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item_layout, null);

        ImageView item_img;
        TextView item_name, item_qty, item_price, item_total_price;
        item_img = view.findViewById(R.id.item_img);
        item_name = view.findViewById(R.id.item_name);
        item_qty = view.findViewById(R.id.item_qty);
        item_price = view.findViewById(R.id.item_price);
        item_total_price = view.findViewById(R.id.item_total_price);


        AddToCart addToAddToCart = getItem(position);
        item_qty.setText(addToAddToCart.item_quantity);
        item_name.setText(addToAddToCart.item_name);
        item_price.setText(addToAddToCart.item_price);


        int subtotal_item = (addToAddToCart.item_quantity) * (addToAddToCart.item_price);
        item_total_price.setText(subtotal_item);
        return view;


    }
}
