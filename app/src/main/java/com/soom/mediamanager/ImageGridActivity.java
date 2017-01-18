package com.soom.mediamanager;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.soom.mediamanager.imagedata.MediaData;
import com.soom.mediamanager.imagedata.MediaDataProvider;

import java.util.ArrayList;

/**
 * Created by kjs on 2017-01-03.
 */

public class ImageGridActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ArrayList<MediaData> mediaDataList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imggrid);

        mediaDataList = new ArrayList<>();
        MediaDataProvider.getImageArrayData(this, mediaDataList);
        GridView gridView = (GridView) findViewById(R.id.gridView);
        ImageAdapter imageAdapter = new ImageAdapter(this);
        imageAdapter.initData(mediaDataList);
        gridView.setAdapter(imageAdapter);
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        showOriginalImage(mediaDataList.get(position).mediaPath, mediaDataList.get(position).orientation);
    }

    private void showOriginalImage(String mediaPath, int orientation) {
        Intent intent = new Intent(this, ImageViewerActivity.class);
        intent.putExtra("IMAGE_PATH", mediaPath);
        intent.putExtra("ORIENTATION", orientation);
        startActivity(intent);
    }
}
