package com.pasalsewa.pasalsewa;

import android.content.Context;
import android.content.Intent;
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
 * Created by wa-v-er on 11/18/2017.
 */

public class ItemAdapter extends ArrayAdapter<Item> {
    Context context;
    int item_id;

    public ItemAdapter(@NonNull Context context, ArrayList<Item> list){
        super(context, 0,list);
        this.context = context;

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, null);
        TextView cat_name;
        ImageView cat_img;
        cat_name = (TextView) view.findViewById(R.id.cat_name);
        cat_img = (ImageView) view.findViewById(R.id.cat_img);

        final Item item= getItem(position);
        cat_name.setText(item.item_name);
        cat_img.setImageBitmap(AddItem.getBitmap(item.item_img));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,ItemDetailsActivity.class).putExtra("item_id",item.item_id));
            }
        });

        return view;
    }
}



