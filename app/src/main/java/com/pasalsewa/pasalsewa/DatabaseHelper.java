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

    static String name="pasalsewadb";
    static int version=1;

    String createCategoryTableSql="CREATE TABLE `Category` (\n" +
            "\t`cat_id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`cat_name`\tTEXT NOT NULL,\n" +
            "\t`cat_img`\tBLOB\n" +
            ");";

    String createItemTableSql="CREATE TABLE `Item` (\n" +
            "\t`item_id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`cat_id`\tINTEGER,\n" +
            "\t`item_name`\tTEXT NOT NULL,\n" +
            "\t`item_price`\tINTEGER NOT NULL,\n" +
            "\t`item_qty`\tINTEGER NOT NULL,\n" +
            "\t`item_img`\tBLOB,\n" +
            "\tFOREIGN KEY(`cat_id`) REFERENCES Category\n" +
            ");";

    String createCustomerTableSql="CREATE TABLE `Customer` (\n" +
            "\t`customer_id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`customer_name`\tTEXT NOT NULL,\n" +
            "\t`customer_no`\tINTEGER,\n" +
            "\t`customer_addr`\tTEXT,\n" +
            "\t`customer_img`\tBLOB\n" +
            ");";
    String createBillTableSql="CREATE TABLE `Bill` (\n" +
            "\t`bill_id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`customer_id`\tINTEGER,\n" +
            "\t`bill_amt`\tINTEGER NOT NULL,\n" +
            "\t`bill_paid`\tINTEGER,\n" +
            "\t`bill_rem`\tINTEGER,\n" +
            "\t`bill_time`\tTEXT,\n" +
            "\tFOREIGN KEY(`customer_id`) REFERENCES Customer\n" +
            ");";

    String createBillParticularsTableSql="CREATE TABLE `BillParticulars` (\n" +
            "\t`bp_id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`bill_id`\tINTEGER,\n" +
            "\t`item_id`\tINTEGER NOT NULL,\n" +
            "\t`item_qty`\tINTEGER NOT NULL,\n" +
            "\t`item_price`\tINTEGER NOT NULL,\n" +
            "\tFOREIGN KEY(`bill_id`) REFERENCES Bill\n" +
            ");";



    public DatabaseHelper(Context context) {
        super(context, name, null, version);
        getWritableDatabase().execSQL(createCategoryTableSql);
        getWritableDatabase().execSQL(createItemTableSql);
        getWritableDatabase().execSQL(createCustomerTableSql);
        getWritableDatabase().execSQL(createBillTableSql);
        getWritableDatabase().execSQL(createBillParticularsTableSql);
    }

    public void insertCategory(ContentValues cv){

        getWritableDatabase().insert("Category","",cv);
    }

    public void insertItem(ContentValues cv){
        getWritableDatabase().insert("Item","",cv);
    }
    public void insertCustomer(ContentValues cv){
        getWritableDatabase().insert("Customer","",cv);
    }
    public void insertBill(ContentValues cv){
        getWritableDatabase().insert("Bill","",cv);
    }
    public void insertBillParticulars(ContentValues cv){
        getWritableDatabase().insert("BillParticulars","",cv);
    }

    public ArrayList<Category> getCategoryList(){
        ArrayList<Category>list= new ArrayList<Category>();
        String getCategoryListSql="SELECT * FROM `Category`";
        Cursor c=getReadableDatabase().rawQuery(getCategoryListSql,null);
        while(c.moveToNext()){

            Category category=new Category();
            category.cat_id=Integer.parseInt(c.getString(c.getColumnIndex("cat_id")));
            category.cat_name=c.getString(c.getColumnIndex("cat_name"));
            category.cat_img= c.getBlob(c.getColumnIndex("cat_img"));
            list.add(category);
        }
        c.close();
        return list;
    }

    public ArrayList<Item> getItemList(){
        ArrayList<Item>list= new ArrayList<Item>();
        String getItemListSql="SELECT * FROM `Item`";
        Cursor c=getReadableDatabase().rawQuery(getItemListSql,null);
        while(c.moveToNext()){

            Item item=new Item();
            item.item_id=Integer.parseInt(c.getString(c.getColumnIndex("item_id")));
            item.cat_id=Integer.parseInt(c.getString(c.getColumnIndex("cat_id")));
            item.item_qty=Integer.parseInt(c.getString(c.getColumnIndex("item_qty")));
            item.item_price=Integer.parseInt(c.getString(c.getColumnIndex("item_price")));
            item.item_name=c.getString(c.getColumnIndex("item_name"));
            item.item_img= c.getBlob(c.getColumnIndex("item_img"));
            list.add(item);
        }
        c.close();
        return list;
    }
    public ArrayList<Customer> getCustomerList(){
        ArrayList<Customer>list= new ArrayList<Customer>();
        String getCustomerListSql="SELECT * FROM `Customer`";
        Cursor c=getReadableDatabase().rawQuery(getCustomerListSql,null);
        while(c.moveToNext()){

            Customer customer=new Customer();
            customer.customer_id=Integer.parseInt(c.getString(c.getColumnIndex("customer_id")));
            customer.customer_no=Integer.parseInt(c.getString(c.getColumnIndex("customer_no")));
            customer.customer_name=c.getString(c.getColumnIndex("customer_name"));
            customer.customer_addr=c.getString(c.getColumnIndex("customer_addr"));
            customer.customer_img= c.getBlob(c.getColumnIndex("customer_img"));
            list.add(customer);
        }
        c.close();
        return list;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
