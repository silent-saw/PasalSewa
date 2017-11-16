package com.pasalsewa.pasalsewa;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CustomerEntry extends AppCompatActivity {

    EditText name, address, no;
    Button enter, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_entry);

        name= (EditText) findViewById(R.id.customer_name);
        address= (EditText) findViewById(R.id.customer_addr);
        no= (EditText) findViewById(R.id.customer_no);
        enter= (Button) findViewById(R.id.enter);
        cancel= (Button) findViewById(R.id.cancel);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameValue=name.getText().toString();
                String addressValue=address.getText().toString();
                int noValue=Integer.parseInt(no.getText().toString());

                ContentValues contentValues=new ContentValues();
                contentValues.put("customer_name",nameValue);
                contentValues.put ("customer_addr",addressValue);
                contentValues.put("customer_no",noValue);

                DatabaseHelper databaseHelper=new DatabaseHelper(CustomerEntry.this);
                databaseHelper.insertCustomer(contentValues);
                Toast.makeText(CustomerEntry.this,"Customer inserted successfully",Toast.LENGTH_LONG).show();
                finish();

            }
        });
    }
}
