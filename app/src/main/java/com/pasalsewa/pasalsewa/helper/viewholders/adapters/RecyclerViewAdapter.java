package com.pasalsewa.pasalsewa.helper.viewholders.adapters;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pasalsewa.pasalsewa.Bill;
import com.pasalsewa.pasalsewa.R;
import com.pasalsewa.pasalsewa.helper.viewholders.BillViewHolder;
import com.pasalsewa.pasalsewa.helper.viewholders.ParentViewHolder;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by Aezirus on 11/20/2017.
 */

public class RecyclerViewAdapter extends ExpandableRecyclerViewAdapter<ParentViewHolder, BillViewHolder> {

Context context;
    public RecyclerViewAdapter(List<? extends ExpandableGroup> groups, Context context) {
        super(groups);
        this.context=context;
    }

    @Override
    public ParentViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_list_items, parent, false);
        return new ParentViewHolder(view);
    }

    @Override
    public BillViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_list_items_details, parent, false);
        return new BillViewHolder(view,context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindChildViewHolder(BillViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {

        Bill bill = (Bill) group.getItems().get(childIndex);
        holder.setBillAttributes(bill.getCustomer_id() , bill.getBill_id(), bill.getBill_amt(), bill.getBill_paid(), bill.getBill_rem());
//    use holder.setBillId to get an id to send in fragment in bundle;;(Very important)
    }

    @Override
    public void onBindGroupViewHolder(ParentViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setParentAttributes(group.getTitle());
    }
}
