package com.pasalsewa.pasalsewa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

public class ItemListActivity extends AppCompatActivity {
    ImageView cat_img;
    DatabaseHelper databaseHelper;
    GridView gridView;


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
            startActivity(new Intent(ItemListActivity.this,CartActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        cat_img = (ImageView) findViewById(R.id.cat_img);
        gridView = (GridView) findViewById(R.id.gridview);
        databaseHelper = new DatabaseHelper(this);

        cat_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ItemListActivity.this,AddItem.class);
                startActivity(intent);
            }
        });




    }
    public void refresh() {
        gridView.setAdapter(new CategoryAdapter(this,databaseHelper.getCategoryList()));
    }


    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

}
