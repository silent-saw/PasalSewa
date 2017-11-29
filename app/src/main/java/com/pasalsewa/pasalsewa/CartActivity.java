package com.pasalsewa.pasalsewa;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {


    AutoCompleteTextView autoCompleteTextView;
    DatabaseHelper databaseHelper;
    Button buy, cancel;
    EditText paid;
    TextView total, credit;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autocompletetextview);
        databaseHelper = new DatabaseHelper(this);
        buy = (Button) findViewById(R.id.buy);
        cancel = (Button) findViewById(R.id.cancel);
        paid = (EditText) findViewById(R.id.paid);
        total = (TextView) findViewById(R.id.total);
        credit = (TextView) findViewById(R.id.credit);
        final ArrayList<AddToCart>list =  databaseHelper.getCartList();
        listView = (ListView) findViewById(R.id.display_add_to_cart);
        listView.setAdapter(new AddToCartAdapter(CartActivity.this, list));
        AddToCart addToCart = new AddToCart();
        final int totalAmount=addToCart.calculateTotal(list);

        total.setText(" "+totalAmount);


        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String customernameValue=autoCompleteTextView.getEditableText().toString();
                Log.i("customername", "on buy: "+customernameValue);
                int customeridValue=databaseHelper.getCustomerId(customernameValue);
                Log.i("customerid", "is: "+customeridValue);
                int totalValue=totalAmount;
                int paidValue=Integer.parseInt(paid.getText().toString());
                int billremValue=totalValue-paidValue;
                //Contentvalue for insertingBill
                ContentValues contentValues=new ContentValues();
                contentValues.put("customer_id",customeridValue);
                contentValues.put("bill_amt",totalValue);
                contentValues.put("bill_paid",paidValue);
                contentValues.put("bill_rem",billremValue);
                databaseHelper.insertBill(contentValues);

                //Contentvalue for inserting BillParticulars
                int billidValue=databaseHelper.getBillId();
                Log.i("billidvalue", "is"+billidValue);

                for(int i=0;i<list.size();i++){
                    AddToCart cart=new AddToCart();
                    cart=list.get(i);
                    ContentValues contentValues1=new ContentValues();
                    contentValues1.put("bill_id",billidValue);
                    contentValues1.put("item_id",cart.item_id);
                    contentValues1.put("item_qty",cart.item_quantity);
                    contentValues1.put("item_price",cart.item_price);
                    databaseHelper.insertBillParticulars(contentValues1);
                }

                databaseHelper.clearCart();



                Toast.makeText(CartActivity.this, "Transaction Completed", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CartActivity.this, HomePageActivity.class));

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this, HomePageActivity.class));
                Toast.makeText(CartActivity.this, "Transaction Aborted", Toast.LENGTH_SHORT).show();
            }
        });
        autoCompleteTextView.setAdapter(new AutoCompleteAdapter(this, databaseHelper.getUsernameList()));

        paid.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    credit.setText(" "+(totalAmount-Integer.valueOf(paid.getText().toString())));
                    return true;
                }
                return false;
            }
        });


    }

    @Override
    protected void onResume() {
        listView.setAdapter(new AddToCartAdapter(CartActivity.this, databaseHelper.getCartList()));


        super.onResume();
    }
}
