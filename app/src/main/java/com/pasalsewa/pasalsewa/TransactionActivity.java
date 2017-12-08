package com.pasalsewa.pasalsewa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.pasalsewa.pasalsewa.helper.Parent;
import com.pasalsewa.pasalsewa.helper.viewholders.adapters.RecyclerViewAdapter;

import java.util.ArrayList;

public class TransactionActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    DatabaseHelper databaseHelper;
    ArrayList<Bill>billInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transactions_list);
        billInfo = new ArrayList<>();
        ArrayList<Parent>list1 = new ArrayList<>();
        list1=getList(list1);
        databaseHelper = new DatabaseHelper(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerViewAdapter = new RecyclerViewAdapter(list1,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(TransactionActivity.this));
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    public ArrayList<Parent> getList(ArrayList<Parent>list){
        databaseHelper = new DatabaseHelper(this);
        billInfo = databaseHelper.getBillInfo();
        for(int i=0;i<billInfo.size();i++){
            ArrayList<Bill> bills = new ArrayList<>(1);
            Bill bill = new Bill(0,0,0,0,0);
            bill = billInfo.get(i);
            bills.add(bill);
            list.add(new Parent(" "+bill.bill_amt,bills));
        }
        return list;

    }
}
