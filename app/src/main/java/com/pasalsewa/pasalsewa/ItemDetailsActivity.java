package com.pasalsewa.pasalsewa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ItemDetailsActivity extends AppCompatActivity {

    TextView addToCart,cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        addToCart= (TextView) findViewById(R.id.addToCart);
        cancel = (TextView) findViewById(R.id.cancel);

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
    }
}
