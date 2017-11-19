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
 * Created by wa-v-er on 11/18/2017.
 */

public class CategoryAdapter extends ArrayAdapter<Category> {
    Context context;

    public CategoryAdapter(@NonNull Context context, ArrayList<Category> list) {
        super(context, 0, list);
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

        Category category = getItem(position);
        cat_name.setText(category.cat_name);
        cat_img.setImageBitmap(AddCategory.getBitmap(category.cat_img));

        return view;
    }
}
