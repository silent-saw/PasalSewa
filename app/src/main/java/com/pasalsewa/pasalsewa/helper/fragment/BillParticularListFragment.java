package com.pasalsewa.pasalsewa.helper.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pasalsewa.pasalsewa.BillParticulars;
import com.pasalsewa.pasalsewa.DatabaseHelper;
import com.pasalsewa.pasalsewa.R;

/**
 * Created by Aezirus on 11/30/2017.
 */

public class BillParticularListFragment extends android.support.v4.app.Fragment {


    TextView itemName,itemQty,itemPrice,totalPrice;
    DatabaseHelper databaseHelper;
    int bpId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        databaseHelper = new DatabaseHelper(getActivity());
        bpId = getArguments().getInt("bpId");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.bill_item_list_layout,null);
        itemName = view.findViewById(R.id.item_name);
        itemQty= view.findViewById(R.id.item_qty);
        itemPrice= view.findViewById(R.id.item_price);
        totalPrice = view.findViewById(R.id.item_total_price);
        populateData();
        return view;
    }

    public void populateData(){
        BillParticulars billParticulars = new BillParticulars(0,0,0,0,0);
        billParticulars = databaseHelper.getBillParticularsBybpId(bpId);
        itemName.setText(databaseHelper.getItemName(billParticulars.item_id));
        itemQty.setText(""+billParticulars.item_qty);
        itemPrice.setText(""+billParticulars.item_price);
        totalPrice.setText(""+billParticulars.item_qty*billParticulars.item_price);

    }
}
