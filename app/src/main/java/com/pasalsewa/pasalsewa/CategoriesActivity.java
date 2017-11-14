package com.pasalsewa.pasalsewa;

import android.content.Intent;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class CategoriesActivity extends AppCompatActivity {

    ImageView cat1,cat2,cat3,cat4,cat5,addCategories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        cat1= (ImageView) findViewById(R.id.cat1);
        cat2 = (ImageView) findViewById(R.id.cat2);
        cat3 = (ImageView) findViewById(R.id.cat3);
        cat4 = (ImageView) findViewById(R.id.cat4);
        cat5 = (ImageView) findViewById(R.id.cat5);
        addCategories = (ImageView) findViewById(R.id.addCategories);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CategoriesActivity.this,CartActivity.class));
            }
        });*/

        cat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CategoriesActivity.this,SortedItemListActivity.class));
            }
        });

        cat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CategoriesActivity.this,SortedItemListActivity.class));
            }
        });

        cat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CategoriesActivity.this,SortedItemListActivity.class));
            }
        });

        cat4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CategoriesActivity.this,SortedItemListActivity.class));
            }
        });

        cat5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CategoriesActivity.this,SortedItemListActivity.class));
            }
        });

        addCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CategoriesActivity.this,SortedItemListActivity.class));
            }
        });



    }
}
