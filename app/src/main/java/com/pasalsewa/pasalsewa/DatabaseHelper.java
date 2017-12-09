package com.pasalsewa.pasalsewa;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

        import java.util.ArrayList;
import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
        ArrayList<AddToCart> list = new ArrayList<>();
        String getCartListSql = "SELECT * FROM `AddToCart`";
        Cursor c = getReadableDatabase().rawQuery(getCartListSql, null);

        while (c.moveToNext()) {
            AddToCart AddToCart = new AddToCart();
            AddToCart.item_id = Integer.parseInt(c.getString(c.getColumnIndex("item_id")));
            AddToCart.item_name = c.getString(c.getColumnIndex("item_name"));
            AddToCart.item_price = Integer.parseInt(c.getString(c.getColumnIndex("item_price")));
            AddToCart.item_quantity= Integer.parseInt(c.getString(c.getColumnIndex("item_quantity")));
            list.add(AddToCart);
//
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

    public int getCustomerId(String customername){
        int id=0;
        String sql="SELECT * FROM `Customer` WHERE `customer_name`='"+customername+"'";
        Cursor cursor=getReadableDatabase().rawQuery(sql,null);
        while(cursor.moveToNext()){
            id=Integer.parseInt(cursor.getString(cursor.getColumnIndex("customer_id")));

        }
        cursor.close();
        return  id;
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

    //Aezirus added a quick db code for retrieving category name for Item entry purpose.
    public String getCategoryName(int cat_id){
        String cat_name = null;
        String sql = "select cat_name from Category where cat_id = "+ cat_id;
        Cursor cursor = getReadableDatabase().rawQuery(sql,null);
        while(cursor.moveToNext()){
            cat_name=cursor.getString(cursor.getColumnIndex("cat_name"));
        }
        return cat_name;
    }

    public String getItemName(int item_id){
        String item_name = null;
        String sql = "select item_name from Item where item_id = "+ item_id;
        Cursor cursor = getReadableDatabase().rawQuery(sql,null);
        while(cursor.moveToNext()){
            item_name=cursor.getString(cursor.getColumnIndex("item_name"));
        }
        return item_name;
    }

    public String getCustomerName(int customer_id){
        String customer_name = null;
        String sql = "select customer_name from Customer where customer_id = "+ customer_id;
        Cursor cursor = getReadableDatabase().rawQuery(sql,null);
        while(cursor.moveToNext()){
            customer_name=cursor.getString(cursor.getColumnIndex("customer_name"));
        }
        return customer_name;
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


    public ArrayList<Bill> getBillInfo(){
        String sql= "Select * from Bill";
        Cursor cursor = getReadableDatabase().rawQuery(sql,null);
        ArrayList<Bill>list = new ArrayList<>();
        while(cursor.moveToNext()){
            Bill bill = new Bill(0,0,0,0,0);
            bill.bill_id=cursor.getInt(cursor.getColumnIndex("bill_id"));
            bill.customer_id=cursor.getInt(cursor.getColumnIndex("customer_id"));
            bill.bill_amt=cursor.getInt(cursor.getColumnIndex("bill_amt"));
            bill.bill_paid=cursor.getInt(cursor.getColumnIndex("bill_paid"));
            bill.bill_rem=cursor.getInt(cursor.getColumnIndex("bill_rem"));
            //bill.bill_time=cursor.getString(cursor.getColumnIndex("bill_time"));
            list.add(bill);
        }
        cursor.close();
        return list;
    }

    public ArrayList<Bill>getBillInfoByCustomerId(int customer_id){
        String sql= "Select * from Bill where customer_id="+customer_id;
        Cursor cursor = getReadableDatabase().rawQuery(sql,null);
        ArrayList<Bill>list = new ArrayList<>();
        while(cursor.moveToNext()){
            Bill bill = new Bill(0,0,0,0,0);
            bill.bill_id=cursor.getInt(cursor.getColumnIndex("bill_id"));
            bill.customer_id=cursor.getInt(cursor.getColumnIndex("customer_id"));
            bill.bill_amt=cursor.getInt(cursor.getColumnIndex("bill_amt"));
            bill.bill_paid=cursor.getInt(cursor.getColumnIndex("bill_paid"));
            bill.bill_rem=cursor.getInt(cursor.getColumnIndex("bill_rem"));
           // bill.bill_time=cursor.getString(cursor.getColumnIndex("bill_time"));
            list.add(bill);
        }
        cursor.close();
        return list;
    }

    public ArrayList<BillParticulars>getBillParticulars(int bill_id){
        String sql= "Select * from BillParticulars where bill_id ="+bill_id;
        Cursor cursor = getReadableDatabase().rawQuery(sql,null);
        ArrayList<BillParticulars>list = new ArrayList<>();
        while(cursor.moveToNext()){
            BillParticulars billParticulars = new BillParticulars(0,0,0,0,0);

            billParticulars.bp_id=cursor.getInt(cursor.getColumnIndex("bp_id"));
            billParticulars.item_id=cursor.getInt(cursor.getColumnIndex("item_id"));
            billParticulars.item_price=cursor.getInt(cursor.getColumnIndex("item_price"));
            billParticulars.item_qty=cursor.getInt(cursor.getColumnIndex("item_qty"));
            list.add(billParticulars);
        }
        cursor.close();
        return list;
    }

    public BillParticulars getBillParticularsBybpId(int bp_id){
        BillParticulars billParticulars = new BillParticulars(0,0,0,0,0);
        String sql = "Select * from BillParticulars where bp_id ="+bp_id;
        Cursor cursor = getReadableDatabase().rawQuery(sql,null);
        while(cursor.moveToNext()){
            billParticulars.bill_id=cursor.getInt(cursor.getColumnIndex("bill_id"));
            billParticulars.item_qty=cursor.getInt(cursor.getColumnIndex("item_qty"));
            billParticulars.item_id=cursor.getInt(cursor.getColumnIndex("item_id"));
            billParticulars.item_price=cursor.getInt(cursor.getColumnIndex("item_price"));

    }
    cursor.close();
    return billParticulars;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public int getBillId() {
        int billid=0;
        String sql="SELECT MAX(bill_id) FROM `Bill`";
        Cursor cursor=getReadableDatabase().rawQuery(sql,null);
        while (cursor.moveToNext()){
             billid= Integer.parseInt(cursor.getString(0));
        }
        return  billid;
    }

    /******************DELETE QUERIES*****************************************/

    public void deleteMember(int id){
        getWritableDatabase().delete("Customer","customer_id="+id,null);
    }

    public void deleteCategory(int id){
        getWritableDatabase().delete("Category","cat_id="+id,null);
    }

    public void deleteItem(int id){
        getWritableDatabase().delete("Item","item_id="+id,null);
    }

    public void deleteBill(int id){
        getWritableDatabase().delete("Bill","bill_id="+id,null);
    }

    public void deleteBillParticulars(int id){
        getWritableDatabase().delete("BillParticulars","bp_id="+id,null);
    }

    //Delete all the items or rows from temptable ie.addtocart
    public void clearCart(){
        String sql="DELETE  FROM  `AddToCart`";
        getWritableDatabase().execSQL(sql);
        Log.i("Cart cleared", "clearCart: ");
    }

    /*******************************UPDATE QUERIES***********************************/

    public void updateMember(int id, ContentValues cv){
        getWritableDatabase().update("Customer",cv,"customer_id="+id,null);
    }
    public void updateCategory(int id, ContentValues cv){
        getWritableDatabase().update("Category",cv,"cat_id="+id,null);
    }
    public void updateItem(int id, ContentValues cv){
        getWritableDatabase().update("Item",cv,"item_id="+id,null);
    }
    public void updateBill(int id, ContentValues cv){
        getWritableDatabase().update("Bill",cv,"bill_id="+id,null);
    }
    public void updateBillParticulars(int id, ContentValues cv){
        getWritableDatabase().update("BillParticulars",cv,"bp_id="+id,null);
    }



}
