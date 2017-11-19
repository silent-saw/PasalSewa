package com.pasalsewa.pasalsewa.helper;

/**
 * Created by Aezirus on 11/19/2017.
 */

public class Bill{

     public String customer_name,status;
     public int bill_id,bill_amount,bil_paid, bill_due;

    public Bill(String customer_name,String status , int bill_id, int bill_amount, int bil_paid, int bill_due) {
        this.customer_name = customer_name;
        this.status = status;
        this.bill_id = bill_id;
        this.bill_amount = bill_amount;
        this.bil_paid = bil_paid;
        this.bill_due = bill_due;
    }

    public String getStatus() {
        return status;
    }

    public String getCustomer_name() {
        return customer_name;
    }


    public int getBill_id() {
        return bill_id;
    }

    public int getBill_amount() {
        return bill_amount;
    }

    public int getBil_paid() {
        return bil_paid;
    }

    public int getBill_due() {
        return bill_due;
    }
}
