package com.pasalsewa.pasalsewa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
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

public class CartActivity extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextView;
    DatabaseHelper databaseHelper;
    Button buy, cancel;
    EditText paid;
    TextView total, credit;
    ListView ListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autocompletetextview);
        databaseHelper = new DatabaseHelper(this);
        buy = (Button) findViewById(R.id.buy);
        cancel = (Button) findViewById(R.id.cancel);a
        paid = (EditText) findViewById(R.id.paid);
        total = (TextView) findViewById(R.id.total);
        credit = (TextView) findViewById(R.id.credit);
        ListView = (android.widget.ListView) findViewById(R.id.display_add_to_cart);
        total.setText("Set Total Val");


        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this, HomePageActivity.class));
                Toast.makeText(CartActivity.this, "Transaction Completed", Toast.LENGTH_SHORT).show();
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
                    credit.setText("Set credit Value");
                    return true;
                }
                return false;
            }
        });

    }

    @Override
    protected void onResume() {
        ListView.setAdapter(new AddToCartAdapter(this, databaseHelper.getCartList()));
        super.onResume();
    }
}
