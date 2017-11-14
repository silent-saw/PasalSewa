package com.pasalsewa.pasalsewa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class HomePageActivity extends AppCompatActivity {

    ImageView categories,itemList,transaction,customerInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        categories= (ImageView) findViewById(R.id.category);
        itemList = (ImageView) findViewById(R.id.itemlist);
        transaction= (ImageView) findViewById(R.id.transaction);
        customerInfo= (ImageView) findViewById(R.id.customer);


        categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageActivity.this,CategoriesActivity.class));
            }
        });

        transaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageActivity.this,TransactionActivity.class));
            }
        });

        itemList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageActivity.this,ItemListActivity.class));
            }
        });

        customerInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageActivity.this,CustomerInfoActivity.class));
            }
        });
    }
}
