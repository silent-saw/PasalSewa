package com.pasalsewa.pasalsewa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.pasalsewa.pasalsewa.helper.Parent;
import com.pasalsewa.pasalsewa.helper.viewholders.adapters.RecyclerViewAdapter;

import java.util.ArrayList;

public class CustomerInfoActivity extends AppCompatActivity {
    int id;
    TextView name, no, address;
    RecyclerView recyclerView;
    DatabaseHelper databaseHelper;
    RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_info);
        id = getIntent().getIntExtra("customer_id", 0);

        recyclerView = findViewById(R.id.customer_transaction_history_recycler_view);
        ArrayList<Parent> list1 = new ArrayList<>();
        ArrayList<Parent> list2 =new ArrayList<>();
        list1 = getList(list2, id);
        recyclerViewAdapter = new RecyclerViewAdapter(list1, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);


        databaseHelper = new DatabaseHelper(this);

        name = (TextView) findViewById(R.id.customer_name);
        no = (TextView) findViewById(R.id.customer_no);
        address = (TextView) findViewById(R.id.customer_addr);

        Customer customer = new Customer();
        customer = databaseHelper.getCustomerInfo(id);

        name.setText(customer.customer_name);
        no.setText(customer.customer_no + "");
        address.setText(customer.customer_addr);
    }

    public ArrayList<Parent> getList(ArrayList<Parent> list, int id) {
        databaseHelper = new DatabaseHelper(this);
        ArrayList<Bill> billInfo = databaseHelper.getBillInfoByCustomerId(id);
        for (int i = 0; i < billInfo.size(); i++) {
            ArrayList<Bill> bills = new ArrayList<>(1);
            Bill bill = new Bill(0, 0, 0, 0, 0);
            bill = billInfo.get(i);
            bills.add(bill);
            list.add(new Parent(" " + bill.bill_amt, bills));
        }
        return list;

    }
}
