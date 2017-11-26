package com.pasalsewa.pasalsewa;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AddItem extends AppCompatActivity {
    Button save;
    String cat_name;
    int cat_id;
    EditText item_name,category,item_price,item_qty;
    ImageView item_img;
    DatabaseHelper databaseHelper;

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
        item_name= (EditText) findViewById(R.id.item_name);
        item_price = (EditText) findViewById(R.id.item_price);
        item_qty = (EditText) findViewById(R.id.item_qty);
        category = (EditText) findViewById(R.id.category);
        cat_id = getIntent().getIntExtra("cat_id",0);
        save= (Button) findViewById(R.id.save);
        databaseHelper = new DatabaseHelper(this);
        cat_name= databaseHelper.getCategoryName(cat_id);
        category.setText(cat_name);
        item_img = (ImageView) findViewById(R.id.item_img);
        item_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               createcustomdialouge();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String additemname= item_name.getText().toString();
                int additemprice= Integer.parseInt(item_price.getText().toString());
                int additemqty= Integer.parseInt(item_qty.getText().toString());
               // int adddcategory = Integer.parseInt(category.getText().toString());


                ContentValues contentValues = new ContentValues();
                contentValues.put("item_name",additemname);
                contentValues.put("item_price",additemprice);
                contentValues.put("item_qty",additemqty);
                contentValues.put("cat_id",cat_id);
                contentValues.put("item_img",getBlob(bitmap));
                databaseHelper.insertItem(contentValues);
                Toast.makeText(AddItem.this, "Item added to list sucessfully",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AddItem.this,ItemListActivity.class);
                startActivity(intent);


            }
        });
    }
    public void createcustomdialouge(){ //Creation of custom dialouge
        final Dialog dialog = new Dialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.selectsource,null);
        view.findViewById(R.id.camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,101);
                dialog.dismiss();

            }
        });
        view.findViewById(R.id.gallery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,102);
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        dialog.setTitle("Choose to select");
        dialog.show();

    }

    Bitmap bitmap;// Intialization for uploading images

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode== Activity.RESULT_OK) {
            bitmap = (Bitmap) data.getExtras().get("data");
            item_img.setImageBitmap(bitmap);
        }
        else if(requestCode==102 && resultCode == Activity.RESULT_OK) {
            Uri imageUri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(
                        getContentResolver(), imageUri);
                item_img.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    public static byte[] getBlob(Bitmap bitmap) {
        ByteArrayOutputStream bos =new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,bos);
        byte[] bArray =bos.toByteArray();
        return bArray;


    }
    public static Bitmap getBitmap(byte[] byteArray) {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }
}
