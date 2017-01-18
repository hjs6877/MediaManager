package com.soom.mediamanager.imagehandle;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.soom.mediamanager.R;
import com.soom.mediamanager.imagedata.MediaData;

import java.util.HashMap;

/**
 * Created by kjs on 2017-01-17.
 */

public class ImageViewRunnable implements Runnable {
    private final int D_RES_ID = R.drawable.ic_launcher;
    private Bitmap bitmap;
    private MediaData mediaData;
    private HashMap<ImageView, String> imageViewMap;

    public ImageViewRunnable(Bitmap bitmap, MediaData mediaData, HashMap<ImageView, String> imageViewMap) {
        this.bitmap = bitmap;
        this.mediaData = mediaData;
        this.imageViewMap = imageViewMap;
    }

    @Override
    public void run() {
        if(!isImageViewValid())
            return;

        if(bitmap != null){
            mediaData.imageView.setImageBitmap(bitmap);
        }else{
            mediaData.imageView.setImageResource(D_RES_ID);
        }
    }

    private boolean isImageViewValid(){
        String mediaPath = imageViewMap.get(mediaData.imageView);
        if(mediaPath == null || !mediaPath.equals(mediaData.mediaPath))
            return false;

        return true;
    }
}
