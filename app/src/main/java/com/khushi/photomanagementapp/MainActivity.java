package com.khushi.photomanagementapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private final int GALLERY_REQ_CODE = 1000;
    ImageView imgGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgGallery= findViewById(R.id.imgGallery);
        Button btnGallery = findViewById(R.id.btnGallery);

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
        }
    }
}