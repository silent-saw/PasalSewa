package com.pasalsewa.pasalsewa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CustomerInfoActivity extends AppCompatActivity {
    int id;
    TextView name,no, address;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_info);

        id=getIntent().getIntExtra("customer_id",0);
        databaseHelper=new DatabaseHelper(CustomerInfoActivity.this);

        name= (TextView) findViewById(R.id.customer_name);
        no= (TextView) findViewById(R.id.customer_no);
        address= (TextView) findViewById(R.id.customer_addr);

        Customer customer=new Customer();
        customer=databaseHelper.getCustomerInfo(id);

        name.setText(customer.customer_name);
        no.setText(customer.customer_no+"");
        address.setText(customer.customer_addr);
        int b=Valid.checkNumber(4);
    }
}
