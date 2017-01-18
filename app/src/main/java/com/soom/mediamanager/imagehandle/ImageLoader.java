package com.soom.mediamanager.imagehandle;

import android.content.Context;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.soom.mediamanager.R;
import com.soom.mediamanager.imagedata.MediaData;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by kjs on 2017-01-17.
 */

public class ImageLoader {
    private final int D_RES_ID = R.drawable.ic_launcher;
    private ExecutorService executorService;
    private HashMap<ImageView, String> imageViewMap;

    public ImageLoader(Context context){
        imageViewMap = new HashMap<>();
        executorService = Executors.newFixedThreadPool(10);
    }

    public void displayImage(MediaData mediaData){
        if(imageViewMap == null)
            return;

        if(mediaData == null)
            return;

        imageViewMap.put(mediaData.imageView, mediaData.mediaPath);
        executorService.submit(new ImageRunnable(mediaData, imageViewMap));
        mediaData.imageView.setImageResource(D_RES_ID);
    }
}
