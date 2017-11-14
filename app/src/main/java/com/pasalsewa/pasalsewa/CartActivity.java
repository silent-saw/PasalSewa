package com.pasalsewa.pasalsewa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CartActivity extends AppCompatActivity {


    Button buy,cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        buy= (Button) findViewById(R.id.buy);
        cancel = (Button) findViewById(R.id.cancel);

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this,HomePageActivity.class));
                Toast.makeText(CartActivity.this, "Transaction Completed", Toast.LENGTH_SHORT).show();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this,HomePageActivity.class));
                Toast.makeText(CartActivity.this, "Transaction Aborted", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
