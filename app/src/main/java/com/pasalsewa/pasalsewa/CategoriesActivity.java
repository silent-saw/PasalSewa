package com.pasalsewa.pasalsewa;

import android.content.Intent;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

public class CategoriesActivity extends AppCompatActivity {

    ImageView cat_img;
    DatabaseHelper databaseHelper;
    GridView gridView;
    FloatingActionButton FAB;

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
            startActivity(new Intent(CategoriesActivity.this,CartActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        cat_img = (ImageView) findViewById(R.id.cat_img);
        gridView = (GridView) findViewById(R.id.gridview);
        databaseHelper = new DatabaseHelper(this);
        FAB = (FloatingActionButton) findViewById(R.id.fab);

        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoriesActivity.this,AddCategory.class);
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
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(CategoriesActivity.this,HomePageActivity.class);
        startActivity(intent);
    }

}
