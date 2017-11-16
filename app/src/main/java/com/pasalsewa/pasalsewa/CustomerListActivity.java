package com.pasalsewa.pasalsewa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class CustomerListActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;


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


    TextView createNewCustomer;
    ListView container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);

        databaseHelper=new DatabaseHelper(this);

        container= (ListView) findViewById(R.id.container);
        createNewCustomer= (TextView) findViewById(R.id.createNewCustomer);

        container.setAdapter(new CustomerListAdapter(CustomerListActivity.this,databaseHelper.getCustomerList()));

        /*

        customer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CustomerListActivity.this,CustomerInfoActivity.class));
            }
        });
        */

        createNewCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CustomerListActivity.this,CustomerEntry.class));
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        container.setAdapter(new CustomerListAdapter(CustomerListActivity.this,databaseHelper.getCustomerList()));
    }
}
