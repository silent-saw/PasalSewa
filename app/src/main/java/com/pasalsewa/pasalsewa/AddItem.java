package com.pasalsewa.pasalsewa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class AddItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        //hiding top bar(ACTION BAR)
        getSupportActionBar().hide();
        //below code shows add user as popup and only occupy certain portion of the dispaly
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        //*.8 refers to 80 percent of the current height and with in integer

        getWindow().setLayout((int) (width * .8), (int) (height * .8));
    }
}
