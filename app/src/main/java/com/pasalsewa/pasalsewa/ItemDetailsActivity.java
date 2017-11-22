package com.pasalsewa.pasalsewa;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ItemDetailsActivity extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextView;
    DatabaseHelper databaseHelper;
    int item_id;
    TextView price,itemname;
    ImageView image;
    EditText quantity;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cart_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_cart) {
            startActivity(new Intent(ItemDetailsActivity.this, CartActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
        Button addToCart, cancel, checkout;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_item_details);
            addToCart = (Button) findViewById(R.id.addtocart);
            cancel = (Button) findViewById(R.id.cancel);
            checkout = (Button) findViewById(R.id.addtocredit);
            autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autocompletetextview);
            databaseHelper = new DatabaseHelper(this);
             price = (TextView) findViewById(R.id.priceset);
             quantity = (EditText) findViewById(R.id.quantity);
              image = (ImageView) findViewById(R.id.image);
             itemname = (TextView) findViewById(R.id.itemname);

            item_id =getIntent().getIntExtra("item_id",0);
            FillItemDetail();


            addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(ItemDetailsActivity.this, CategoriesActivity.class));
                    Toast.makeText(ItemDetailsActivity.this, "Added to AddToCart", Toast.LENGTH_SHORT).show();


                    int pricevalue = Integer.valueOf(price.getText().toString());
                    int quantityvalue = Integer.valueOf(quantity.getText().toString());
                    String itemnamevalue = itemname.getText().toString();

                    ContentValues contentValues = new ContentValues();
                    contentValues.put("item_price", pricevalue);
                    contentValues.put("item_quantity", quantityvalue);
                    contentValues.put("item_name", itemnamevalue);
                    contentValues.put("item_id",item_id);

                    databaseHelper.insertToCart(contentValues);

                }
            });


            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(ItemDetailsActivity.this, CategoriesActivity.class));

                }
            });
            checkout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ItemDetailsActivity.this, CartActivity.class);


                    startActivity(intent);
                }
            });



            autoCompleteTextView.setAdapter(new AutoCompleteAdapter(ItemDetailsActivity.this, databaseHelper.getUsernameList()));



        }

        public void FillItemDetail(){
            Item item = databaseHelper.getIteminfo(item_id);

            itemname.setText(item.item_name);
            price.setText(String.valueOf(item.item_price));


        }
}
