package com.pasalsewa.pasalsewa.helper.viewholders;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.pasalsewa.pasalsewa.BillParticulars;
import com.pasalsewa.pasalsewa.DatabaseHelper;
import com.pasalsewa.pasalsewa.R;
import com.pasalsewa.pasalsewa.TransactionListViewAdapter;
import com.pasalsewa.pasalsewa.helper.fragment.BillParticularListFragment;
import com.pasalsewa.pasalsewa.helper.viewholders.adapters.BillParticularsRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by Aezirus on 11/20/2017.
 */

public class BillViewHolder extends ChildViewHolder {

    DatabaseHelper databaseHelper;
   // ViewPager viewPager;
    TextView customer_name, bill_id, bill_amount, bill_paid, bill_due, status, status_title, bill_paid_title, bill_amount_title, bill_due_title;
    Context context;
    RecyclerView bpRecyclerView;
//    int billIdForVP;
//    FragmentManager fragmentManager;
     ArrayList<BillParticulars> list;
     BillParticularsRecyclerViewAdapter billParticularsRecyclerViewAdapter;

    public BillViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
//        list = new ArrayList<>();
        databaseHelper = new DatabaseHelper(context);
//        viewPager = itemView.findViewById(R.id.view_pager);
//        fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
//
//        viewPager.setAdapter(new ViewPagerAdapter(fragmentManager));
//        viewPager.getAdapter().notifyDataSetChanged();
        bpRecyclerView = itemView.findViewById(R.id.bp_recycler_view);
        bill_amount_title = itemView.findViewById(R.id.bill_amount_title);
        bill_paid_title = itemView.findViewById(R.id.bill_paid_title);
        bill_due_title = itemView.findViewById(R.id.bill_due_title);
        customer_name = (TextView) itemView.findViewById(R.id.customer_name);
        bill_id = (TextView) itemView.findViewById(R.id.bill_id);
        bill_amount = (TextView) itemView.findViewById(R.id.bill_amount);
        bill_paid = (TextView) itemView.findViewById(R.id.bill_paid);
        bill_due = (TextView) itemView.findViewById(R.id.bill_due);
        status = (TextView) itemView.findViewById(R.id.status);
        status_title = itemView.findViewById(R.id.status_title);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ResourceAsColor")
    public void setBillAttributes(int customerId, int billId, int billAmount, int billPaid, int billRem) {

        list = databaseHelper.getBillParticulars(billId);
        billParticularsRecyclerViewAdapter = new BillParticularsRecyclerViewAdapter(list,context);
        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        bpRecyclerView.setLayoutManager(mLayoutManager);
        bpRecyclerView.setAdapter(billParticularsRecyclerViewAdapter);
        bill_amount_title.setBackgroundColor(R.color.colorBackground);
        bill_paid_title.setBackgroundColor(R.color.colorBackground);
        bill_due_title.setBackgroundColor(R.color.colorBackground);
        status_title.setBackgroundColor(R.color.colorBackground);
        customer_name.setBackgroundColor(R.color.colorBackground);
        bill_id.setBackgroundColor(R.color.colorBackground);
        bill_paid.setBackgroundColor(R.color.colorBackground);
        bill_amount.setBackgroundColor(R.color.colorBackground);
        status.setBackgroundColor(R.color.colorBackground);
        customer_name.setText(databaseHelper.getCustomerName(customerId));
        bill_id.setText(String.valueOf(billId));
        bill_amount.setText(String.valueOf(billAmount));
        bill_paid.setText(String.valueOf(billPaid));
        bill_due.setText(String.valueOf(billRem));


    }

//
//    public class ViewPagerAdapter extends FragmentPagerAdapter {
//        public ViewPagerAdapter(FragmentManager fm) {
//
//            super(fm);
//        }
//
//
//        private boolean dataChangedNotify = false;
//        @Override
//        public Fragment getItem(int position) {
//
//            if (dataChangedNotify){
//
//            }
//            BillParticularListFragment billParticularListFragment = new BillParticularListFragment();
//            Bundle bundle = new Bundle();
//            bundle.putInt("bpId", 1);
//            billParticularListFragment.setArguments(bundle);
//            return billParticularListFragment;
//        }



//        @Override
//        public int getCount() {
//
//            return list.size();
//        }
//    }
}
