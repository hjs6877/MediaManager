package com.soom.mediamanager.imagehandle;

import android.app.Activity;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.soom.mediamanager.imagedata.MediaData;

import java.util.HashMap;

/**
 * Created by kjs on 2017-01-17.
 */

public class ImageRunnable implements Runnable {
    private MediaData mediaData;
    private HashMap<ImageView, String> imageViewMap;

    public ImageRunnable(MediaData mediaData, HashMap<ImageView, String> imageViewMap){
        this.mediaData = mediaData;
        this.imageViewMap = imageViewMap;
    }

    @Override
    public void run() {
        if(!isImageViewValid(mediaData))
            return;

        Bitmap bitmap = ImageUtils.getImageThumbnail(mediaData);

        if(!isImageViewValid(mediaData))
            return;

        ImageViewRunnable imageViewRunnable = new ImageViewRunnable(
                bitmap,
                mediaData,
                imageViewMap
        );

        Activity activity = (Activity) mediaData.imageView.getContext();
        activity.runOnUiThread(imageViewRunnable);
    }

    private boolean isImageViewValid(MediaData mediaData){
        String mediaPath = imageViewMap.get(mediaData.imageView);
        if(mediaPath == null || !mediaPath.equals(mediaData.mediaPath))
            return false;

        return true;
    }
}
