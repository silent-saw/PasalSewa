package com.pasalsewa.pasalsewa.helper.viewholders.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pasalsewa.pasalsewa.R;
import com.pasalsewa.pasalsewa.helper.Bill;
import com.pasalsewa.pasalsewa.helper.Parent;
import com.pasalsewa.pasalsewa.helper.viewholders.BillViewHolder;
import com.pasalsewa.pasalsewa.helper.viewholders.ParentViewHolder;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by Aezirus on 11/20/2017.
 */

public class RecyclerViewAdapter extends ExpandableRecyclerViewAdapter<ParentViewHolder, BillViewHolder> {


    public RecyclerViewAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public ParentViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_list_items, parent, false);
        return new ParentViewHolder(view);
    }

    @Override
    public BillViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_list_items_details, parent, false);
        return new BillViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(BillViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {

        Bill bill = (Bill) group.getItems().get(childIndex);
        holder.setBillAttributes(bill.getCustomer_name(), bill.getBill_id(), bill.getBill_amount(), bill.getBil_paid(), bill.getBill_due(), bill.getStatus());
    }

    @Override
    public void onBindGroupViewHolder(ParentViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setParentAttributes(group.getTitle());
    }
}
