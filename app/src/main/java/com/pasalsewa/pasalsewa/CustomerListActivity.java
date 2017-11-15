package com.pasalsewa.pasalsewa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class CustomerListActivity extends AppCompatActivity {


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
            startActivity(new Intent(CustomerListActivity.this,CartActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    TextView customer1,customer2,customer3,customer4,customer5,customer6,createNewCustomer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);
        customer1= (TextView) findViewById(R.id.customer1);
        customer2= (TextView) findViewById(R.id.customer2);
        customer3= (TextView) findViewById(R.id.customer3);
        customer4= (TextView) findViewById(R.id.customer4);
        customer5= (TextView) findViewById(R.id.customer5);
        customer6= (TextView) findViewById(R.id.customer6);
        createNewCustomer= (TextView) findViewById(R.id.createNewCustomer);

        customer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CustomerListActivity.this,CustomerInfoActivity.class));
            }
        });



    }
}
