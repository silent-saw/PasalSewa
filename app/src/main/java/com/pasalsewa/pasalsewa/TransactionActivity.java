package com.pasalsewa.pasalsewa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.pasalsewa.pasalsewa.helper.Bill;
import com.pasalsewa.pasalsewa.helper.Parent;
import com.pasalsewa.pasalsewa.helper.viewholders.adapters.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class TransactionActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    List<Parent> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transactions_list);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        getList();
        recyclerViewAdapter = new RecyclerViewAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(TransactionActivity.this));
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    public void getList(){
        list = new ArrayList<>(4);
        for(int i=0;i<4;i++){
            List<Bill>bills= new ArrayList<>(1);
            bills.add(new Bill("Sachin Maharjan","Credit",i+1,10000,5000,5000));
            list.add(new Parent("2017/7/20 5:30 A.M",bills));
        }

    }
}
