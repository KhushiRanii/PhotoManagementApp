package com.khushi.photomanagementapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class Gallery extends AppCompatActivity {

    private final int GALLERY_REQ_CODE = 100;
    private final int CAMERA_REQ_CODE = 2000;
    ImageView imgGallery;
    String url = "https://pixabay.com/images/search/nature/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        imgGallery= findViewById(R.id.imgGallery);
        Button btnGallery = findViewById(R.id.btnGallery);
        Button btnCamera = findViewById(R.id.btnCamera);
        Button btnPicasso = findViewById(R.id.btnPicasso);


        btnPicasso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Picasso.get()
                        .load(url)
                        .placeholder(R.drawable.baseline_image_24)
                        .into(imgGallery);
            }
        });


        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(iCamera, CAMERA_REQ_CODE);
            }
        });

        Log.e("TAG", "onCreate: ",null );

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery, GALLERY_REQ_CODE);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK)
        {
            if (requestCode == GALLERY_REQ_CODE)
            {
                // for gallery
                imgGallery.setImageURI(data.getData());
            }
            else if (requestCode == CAMERA_REQ_CODE)
            {
                // for Camera
                Bitmap img =(Bitmap)(data.getExtras().get("data"));
                imgGallery.setImageBitmap(img);

            }
        }



    }
}