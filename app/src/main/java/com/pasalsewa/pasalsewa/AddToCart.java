package com.pasalsewa.pasalsewa;

import java.util.ArrayList;

/**
 * Created by wicked_sick on 11/18/2017.
 */

public class AddToCart {

    int item_id, item_price, item_quantity,cart_id;
    String item_name;
    byte[] item_img;


    public int calculateTotal(ArrayList<AddToCart>list){
        int sum=0;
        for (int i=0;i<list.size();i++){
            AddToCart addToCart = list.get(i);
            int subtotal = addToCart.item_price*addToCart.item_quantity;
            sum=sum+subtotal;
        }
        return sum;
    }
}
