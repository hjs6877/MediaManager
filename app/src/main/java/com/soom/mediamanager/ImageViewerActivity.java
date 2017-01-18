package com.soom.mediamanager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.soom.mediamanager.imagehandle.ImageUtils;

/**
 * Created by kjs on 2017-01-03.
 */

public class ImageViewerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        Intent intent = getIntent();
        String imagePath = intent.getStringExtra("IMAGE_PATH");
        int orientation = intent.getIntExtra("ORIENTATION", 0);

        showImageView(imagePath, orientation);
    }

    private void showImageView(String imagePath, int orientation) {
        Bitmap bitmap = ImageUtils.getImageBitmap(imagePath, orientation);

        ImageView imageView = (ImageView) findViewById(R.id.showImage);
        imageView.setImageBitmap(bitmap);

    }
}
