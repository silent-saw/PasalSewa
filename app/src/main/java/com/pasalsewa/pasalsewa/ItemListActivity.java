package com.pasalsewa.pasalsewa;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class ItemListActivity extends AppCompatActivity {
    ImageView item_img;
    DatabaseHelper databaseHelper;
    GridView gridView;
    int id;
    int cat_id;
    int item_id;
    FloatingActionButton Fab;


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
        cat_id = getIntent().getIntExtra("cat_id",0);
        item_img = (ImageView) findViewById(R.id.item_img);
        gridView = (GridView) findViewById(R.id.gridview);
        id=getIntent().getIntExtra("cat_id",0);
        databaseHelper=new DatabaseHelper(ItemListActivity.this);
        databaseHelper=new DatabaseHelper(this);
        Fab = (FloatingActionButton) findViewById(R.id.fab);

//Items can be only added through specific category cannot be added from itemlist directly
if(cat_id!=0) {
    Fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ItemListActivity.this, AddItem.class);
            intent.putExtra("cat_id", cat_id);
            startActivity(intent);
        }
    });
}
//to notify that item cannot be added from item list
        else {
            Fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(ItemListActivity.this,"Items cannot be added from item list",Toast.LENGTH_LONG).show();
                }
            });


        }

    }
    public void refresh() {
        //to select from specific category function getCategoryItem(cat_id) from database is called
    if (cat_id!=0) {
        gridView.setAdapter(new ItemAdapter(this, databaseHelper.getCategoryItem(cat_id)));
    }
    //to select all item list function getItemList() from database is called
    else{
        gridView.setAdapter(new ItemAdapter(this,databaseHelper.getItemList()));
    }
    }


    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(cat_id!=0) {
            Intent intent = new Intent(ItemListActivity.this, CategoriesActivity.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(ItemListActivity.this, HomePageActivity.class);
            startActivity(intent);
        }
    }

}
