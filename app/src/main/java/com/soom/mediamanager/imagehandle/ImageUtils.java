package com.soom.mediamanager.imagehandle;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.provider.MediaStore;

import com.soom.mediamanager.imagedata.MediaData;

/**
 * Created by kjs on 2017-01-17.
 */

public class ImageUtils {
    public static Bitmap getImageThumbnail(MediaData mediaData){
        ContentResolver contentResolver = mediaData.imageView.getContext().getContentResolver();
        Bitmap bitmap = MediaStore.Images.Thumbnails.getThumbnail(
                contentResolver,
                mediaData.mediaId,
                MediaStore.Images.Thumbnails.MINI_KIND,
                null);
        return getCenterBitmap(bitmap, mediaData.orientation);
    }

    public static Bitmap getImageBitmap(String imagePath, int orientation){
        try{
            Bitmap bitmap;
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 1;
            bitmap = BitmapFactory.decodeFile(imagePath, options);
            return getRotateBitmap(bitmap, orientation);
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    private static Bitmap getRotateBitmap(Bitmap src, int orientation){
        if(src == null)
            return null;

        int width = src.getWidth();
        int height = src.getHeight();

        Matrix m = new Matrix();

        if(orientation != 0)
            m.setRotate(orientation);

        return Bitmap.createBitmap(
                src,
                0,
                0,
                width,
                height,
                m,
                false
        );
    }

    private static Bitmap getCenterBitmap(Bitmap src, int orientation){
        if(src == null)
            return null;

        Bitmap bitmap = null;
        int width = src.getWidth();
        int height = src.getHeight();

        Matrix m = new Matrix();
        if(orientation != 0)
            m.setRotate(orientation);

        if(width >= height){
            bitmap = Bitmap.createBitmap(
                    src,
                    width/2 - height/2,
                    0,
                    height,
                    height,
                    m,
                    true
            );
        }else{
            bitmap = Bitmap.createBitmap(
                    src,
                    0,
                    height/2-width/2,
                    width,
                    width,
                    m,
                    true
            );
        }

        return bitmap;
    }
}
