package com.pasalsewa.pasalsewa;

/**
 * Created by lazyboy on 11/20/17.
 */

public class BillParticulars {

   public int bp_id, bill_id, item_id, item_qty, item_price;

    public BillParticulars(int bp_id, int bill_id, int item_id, int item_qty, int item_price) {
        this.bp_id = bp_id;
        this.bill_id = bill_id;
        this.item_id = item_id;
        this.item_qty = item_qty;
        this.item_price = item_price;
    }

    public int getBp_id() {
      return bp_id;
   }

   public int getBill_id() {
      return bill_id;
   }

   public int getItem_id() {
      return item_id;
   }

   public int getItem_qty() {
      return item_qty;
   }

   public int getItem_price() {
      return item_price;
   }
}
