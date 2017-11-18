package com.pasalsewa.pasalsewa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ItemDetailsActivity extends AppCompatActivity {

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
            startActivity(new Intent(ItemDetailsActivity.this,CartActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    Button addToCart,cancel,checkout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        addToCart= (Button) findViewById(R.id.addtocart);
        cancel = (Button) findViewById(R.id.cancel);
        checkout = (Button) findViewById(R.id.addtocredit);

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ItemDetailsActivity.this,CategoriesActivity.class));
                Toast.makeText(ItemDetailsActivity.this, "Added to Cart", Toast.LENGTH_SHORT).show();
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ItemDetailsActivity.this,CategoriesActivity.class));

            }
        });
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent =new Intent(ItemDetailsActivity.this,CartActivity.class);

                startActivity(intent);
            }
        });
    }
}
