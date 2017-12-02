package com.pasalsewa.pasalsewa;

import android.annotation.SuppressLint;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AddCategory extends AppCompatActivity {
    Button save;
    EditText cat_name;
    ImageView cat_img;
    DatabaseHelper databaseHelper;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        //hiding top bar(ACTION BAR)
        getSupportActionBar().hide();

        //below code shows add user as popup and only occupy certain portion of the display
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        //*.8 refers to 80 percent of the current height and with in integer

        getWindow().setLayout((int) (width * .8), (int) (height * .8));

        cat_name= (EditText) findViewById(R.id.cat_name);
        save= (Button) findViewById(R.id.save);
        databaseHelper = new DatabaseHelper(this);
        cat_img = (ImageView) findViewById(R.id.cat_img);
        cat_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              createcustomdialouge();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String addcategoryvalue= cat_name.getText().toString();

                if (addcategoryvalue.length() > 0 && bitmap!=null ) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("cat_name", addcategoryvalue);
                    contentValues.put("cat_img", getBlob(bitmap));
                    databaseHelper.insertCategory(contentValues);
                    Toast.makeText(AddCategory.this, "Category added to list sucessfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AddCategory.this, CategoriesActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(AddCategory.this, "Empty Field", Toast.LENGTH_SHORT).show();
                }


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
            cat_img.setImageBitmap(bitmap);
        }
        else if(requestCode==102 && resultCode == Activity.RESULT_OK) {
            Uri imageUri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(
                        getContentResolver(), imageUri);
                cat_img.setImageBitmap(bitmap);
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
        bitmap.compress(Bitmap.CompressFormat.PNG,0,bos);
        byte[] bArray =bos.toByteArray();
        return bArray;


    }
    public static Bitmap getBitmap(byte[] byteArray) {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }

}
