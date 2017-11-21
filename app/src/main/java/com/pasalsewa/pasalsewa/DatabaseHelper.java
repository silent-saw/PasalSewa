package com.pasalsewa.pasalsewa;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by lazyboy on 11/10/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    static String name = "pasalsewadb";
    static int version = 1;

    String createCategoryTableSql = "CREATE TABLE if not exists `Category` (\n" +
            "\t`cat_id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`cat_name`\tTEXT NOT NULL,\n" +
            "\t`cat_img`\tBLOB\n" +
            ");";

    String createItemTableSql = "CREATE TABLE if not exists `Item` (\n" +
            "\t`item_id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`cat_id`\tINTEGER,\n" +
            "\t`item_name`\tTEXT NOT NULL,\n" +
            "\t`item_price`\tINTEGER NOT NULL,\n" +
            "\t`item_qty`\tINTEGER NOT NULL,\n" +
            "\t`item_img`\tBLOB,\n" +
            "\tFOREIGN KEY(`cat_id`) REFERENCES Category\n" +
            ");";

    String createCustomerTableSql = "CREATE TABLE if not exists `Customer` (\n" +
            "\t`customer_id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`customer_name`\tTEXT NOT NULL,\n" +
            "\t`customer_no`\tTEXT,\n" +
            "\t`customer_addr`\tTEXT,\n" +
            "\t`customer_img`\tBLOB\n" +
            ");";
    String createBillTableSql = "CREATE TABLE if not exists `Bill` (\n" +
            "\t`bill_id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`customer_id`\tINTEGER,\n" +
            "\t`bill_amt`\tINTEGER NOT NULL,\n" +
            "\t`bill_paid`\tINTEGER,\n" +
            "\t`bill_rem`\tINTEGER,\n" +
            "\t`bill_time`\tTEXT,\n" +
            "\tFOREIGN KEY(`customer_id`) REFERENCES Customer\n" +
            ");";

    String createBillParticularsTableSql = "CREATE TABLE if not exists `BillParticulars` (\n" +
            "\t`bp_id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`bill_id`\tINTEGER,\n" +
            "\t`item_id`\tINTEGER NOT NULL,\n" +
            "\t`item_qty`\tINTEGER NOT NULL,\n" +
            "\t`item_price`\tINTEGER NOT NULL,\n" +
            "\tFOREIGN KEY(`bill_id`) REFERENCES Bill\n" +
            ");";




    String createAddToCartTableSql = "CREATE TABLE if not exists `AddToCart` (\n" +


            "\t`item_id`\tINTEGER,\n" +
            "\t`item_price`\tINTEGER,\n" +
            "\t`item_name`\tTEXT,\n" +

            "\t`cart_id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`item_quantity`\tINTEGER NOT NULL,\n" +
            "\t`item_image`\tBLOB\n" +
            ");";


    public DatabaseHelper(Context context) {
        super(context, name, null, version);
        getWritableDatabase().execSQL(createCategoryTableSql);
        getWritableDatabase().execSQL(createItemTableSql);
        getWritableDatabase().execSQL(createCustomerTableSql);
        getWritableDatabase().execSQL(createBillTableSql);
        getWritableDatabase().execSQL(createBillParticularsTableSql);
        getWritableDatabase().execSQL(createAddToCartTableSql);
    }

    public void insertCategory(ContentValues cv) {

        getWritableDatabase().insert("Category", "", cv);
    }

    public void insertItem(ContentValues cv) {
        getWritableDatabase().insert("Item", "", cv);
    }

    public void insertCustomer(ContentValues cv) {
        getWritableDatabase().insert("Customer", "", cv);
    }

    public void insertBill(ContentValues cv) {
        getWritableDatabase().insert("Bill", "", cv);
    }

    public void insertBillParticulars(ContentValues cv){getWritableDatabase().insert("BillParticulars","",cv);}
    public void insertToCart(ContentValues cv){
        getWritableDatabase().insert("AddToCart","",cv);
    }







    public ArrayList<AddToCart> getCartList() {
        ArrayList<AddToCart> list = new ArrayList<AddToCart>();
        String getCartListSql = "SELECT * FROM `AddToCart`";
        Cursor c = getReadableDatabase().rawQuery(getCartListSql, null);
        Integer TotalCostPerItem,TotalCost=0;

        while (c.moveToNext()) {
            AddToCart AddToCart = new AddToCart();
            AddToCart.item_id = Integer.parseInt(c.getString(c.getColumnIndex("item_id")));
            AddToCart.item_name = c.getString(c.getColumnIndex("item_name"));
            AddToCart.item_price = Integer.parseInt(c.getString(c.getColumnIndex("item_price")));
            AddToCart.item_quantity= Integer.parseInt(c.getString(c.getColumnIndex("item_quantity")));
            list.add(AddToCart);
//            FOR CALCULATION
            TotalCostPerItem=AddToCart.item_price * AddToCart.item_quantity;
            TotalCost=TotalCost+TotalCostPerItem;
        }
        c.close();
        return list;


    }

    public ArrayList<Category> getCategoryList() {
        ArrayList<Category> list = new ArrayList<Category>();
        String getCategoryListSql = "SELECT * FROM `Category`";
        Cursor c = getReadableDatabase().rawQuery(getCategoryListSql, null);
        while (c.moveToNext()) {

            Category category = new Category();
            category.cat_id = Integer.parseInt(c.getString(c.getColumnIndex("cat_id")));
            category.cat_name = c.getString(c.getColumnIndex("cat_name"));
            category.cat_img = c.getBlob(c.getColumnIndex("cat_img"));
            list.add(category);
        }
        c.close();
        return list;
    }

    public ArrayList<Item> getItemList() {
        ArrayList<Item> list = new ArrayList<Item>();
        String getItemListSql = "SELECT * FROM `Item`";
        Cursor c = getReadableDatabase().rawQuery(getItemListSql, null);
        while (c.moveToNext()) {

            Item item = new Item();
            item.item_id = Integer.parseInt(c.getString(c.getColumnIndex("item_id")));
            item.cat_id = Integer.parseInt(c.getString(c.getColumnIndex("cat_id")));
            item.item_qty = Integer.parseInt(c.getString(c.getColumnIndex("item_qty")));
            item.item_price = Integer.parseInt(c.getString(c.getColumnIndex("item_price")));
            item.item_name = c.getString(c.getColumnIndex("item_name"));
            item.item_img = c.getBlob(c.getColumnIndex("item_img"));
            list.add(item);
        }
        c.close();
        return list;
    }

    public ArrayList<Customer> getCustomerList() {
        ArrayList<Customer> list = new ArrayList<Customer>();
        String getCustomerListSql = "SELECT * FROM `Customer`";
        Cursor c = getReadableDatabase().rawQuery(getCustomerListSql, null);
        while (c.moveToNext()) {

            Customer customer = new Customer();
            customer.customer_id = Integer.parseInt(c.getString(c.getColumnIndex("customer_id")));
            customer.customer_no = c.getString(c.getColumnIndex("customer_no"));
            customer.customer_name = c.getString(c.getColumnIndex("customer_name"));
            customer.customer_addr = c.getString(c.getColumnIndex("customer_addr"));
            customer.customer_img = c.getBlob(c.getColumnIndex("customer_img"));
            list.add(customer);
        }
        c.close();
        return list;
    }

    public ArrayList<Item> getCategoryItem(int id) {
        ArrayList<Item> list = new ArrayList<Item>();
        String getCategoryItemSql = "SELECT * FROM `Item` WHERE cat_id=" + id;
        Cursor c = getReadableDatabase().rawQuery(getCategoryItemSql, null);
        while (c.moveToNext()) {
            Item item = new Item();
            item.item_id = Integer.parseInt(c.getString(c.getColumnIndex("item_id")));
            item.cat_id = Integer.parseInt(c.getString(c.getColumnIndex("cat_id")));
            item.item_qty = Integer.parseInt(c.getString(c.getColumnIndex("item_qty")));
            item.item_price = Integer.parseInt(c.getString(c.getColumnIndex("item_price")));
            item.item_name = c.getString(c.getColumnIndex("item_name"));
            item.item_img = c.getBlob(c.getColumnIndex("item_img"));
            list.add(item);
        }
        c.close();
        return list;

    }

    public Customer getCustomerInfo(int id) {
        Customer customer = new Customer();
        String sql = "SELECT * FROM `Customer` WHERE customer_id=" + id;
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        while (cursor.moveToNext()) {
            customer.customer_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("customer_id")));
            customer.customer_name = cursor.getString(cursor.getColumnIndex("customer_name"));
            customer.customer_addr = cursor.getString(cursor.getColumnIndex("customer_addr"));
            customer.customer_no = cursor.getString(cursor.getColumnIndex("customer_no"));
        }
        cursor.close();
        return customer;

    }


    public Item getIteminfo(int item_id){
        Item item=new Item();
        String sql="SELECT * FROM `Item` WHERE item_id="+item_id;
        Cursor cursor=getReadableDatabase().rawQuery(sql,null);
        while(cursor.moveToNext()){
            item.item_id=Integer.parseInt(cursor.getString(cursor.getColumnIndex("item_id")));
            item.item_name=cursor.getString(cursor.getColumnIndex("item_name"));
            item.item_price=Integer.parseInt(cursor.getString(cursor.getColumnIndex("item_price")));
            item.item_img= cursor.getBlob(cursor.getColumnIndex("item_img"));
            item.item_qty =Integer.parseInt(cursor.getString(cursor.getColumnIndex("item_qty")));
        }
        cursor.close();
        return item;

    }

    public ArrayList<String> getUsernameList() {
        String sql = "select * from Customer";

        Cursor cursor = getReadableDatabase().rawQuery(sql, null);


        ArrayList<String> list = new ArrayList<String>();
        while (cursor.moveToNext()) {
            list.add(cursor.getString(cursor.getColumnIndex("customer_name")));
        }
        cursor.close();
        return list;
    }

    public ArrayList<AddToCart> getCartItems(){
        String sql="SELECT * FROM  `AddToCart`";
        Cursor cursor=getReadableDatabase().rawQuery(sql,null);
        ArrayList<AddToCart> list=new ArrayList<AddToCart>();
        while (cursor.moveToNext()){
            AddToCart addToCart=new AddToCart();
            addToCart.cart_id=Integer.parseInt(cursor.getString(cursor.getColumnIndex("cart_id")));
            addToCart.item_id=Integer.parseInt(cursor.getString(cursor.getColumnIndex("item_id")));
            addToCart.item_price=Integer.parseInt(cursor.getString(cursor.getColumnIndex("item_price")));
            addToCart.item_quantity=Integer.parseInt(cursor.getString(cursor.getColumnIndex("item_quantity")));
            addToCart.item_name=cursor.getString(cursor.getColumnIndex("item_name"));
            list.add(addToCart);
        }
        cursor.close();
        return list;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }




}
