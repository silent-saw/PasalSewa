package com.pasalsewa.pasalsewa;

/**
 * Created by lazyboy on 11/20/17.
 */

public class Bill {
    int bill_id,customer_id,bill_amt,bill_paid,bill_rem;
    //String bill_time;

    public Bill(int customer_id, int bill_id, int bill_amt, int bill_paid, int bill_rem) {
        this.customer_id = customer_id;
        this.bill_id = bill_id;
        this.bill_amt = bill_amt;
        this.bill_paid = bill_paid;
        this.bill_rem = bill_rem;
    }


    public int getCustomer_id() {
        return customer_id;
    }


    public int getBill_id() {
        return bill_id;
    }

    public int getBill_amt() {
        return bill_amt;
    }

    public int getBill_paid() {
        return bill_paid;
    }

    public int getBill_rem() {
        return bill_rem;
    }

}
