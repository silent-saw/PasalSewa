package com.pasalsewa.pasalsewa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class SortedItemListActivity extends AppCompatActivity {



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cart_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.action_cart){
            startActivity(new Intent(SortedItemListActivity.this,CartActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    ImageView item1,item2,item3,item4,item5,addItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorted_item_list);
        item1 = (ImageView) findViewById(R.id.item1);
        item2 = (ImageView) findViewById(R.id.item2);
        item3 = (ImageView) findViewById(R.id.item3);
        item4 = (ImageView) findViewById(R.id.item4);
        item5 = (ImageView) findViewById(R.id.item5);
        addItems = (ImageView) findViewById(R.id.addItems);


        item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SortedItemListActivity.this,ItemDetailsActivity.class));
            }
        });

        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SortedItemListActivity.this,ItemDetailsActivity.class));
            }
        });

        item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SortedItemListActivity.this,ItemDetailsActivity.class));
            }
        });

        item4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SortedItemListActivity.this,ItemDetailsActivity.class));
            }
        });

        item5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SortedItemListActivity.this,ItemDetailsActivity.class));
            }
        });

        addItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SortedItemListActivity.this,ItemDetailsActivity.class));
            }
        });
    }
}
