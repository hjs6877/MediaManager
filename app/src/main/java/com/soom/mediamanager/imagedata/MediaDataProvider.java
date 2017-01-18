package com.soom.mediamanager.imagedata;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.soom.mediamanager.MainActivity;

import java.util.ArrayList;

/**
 * Created by kjs on 2017-01-03.
 */

public class MediaDataProvider {
    public static void getImageArrayData(Context context, ArrayList<MediaData> mediaDataList){
        Cursor imageCursor = null;

        String[] imageColumns = {
                MediaStore.Images.Media._ID,        // 미디어 ID
                MediaStore.Images.Media.DATA,       // 이미지 경로
                MediaStore.Images.Media.ORIENTATION // 이미지 방향
        };

        // 컨텐트 리졸버의 query() 함수와 URI를 이용하여 이미지 정보를 가져온다.
        imageCursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                imageColumns,
                null,
                null,
                null
        );

        // 가져온 이미지 정보는 Cursor를 통해 확인할 수 있다.
        if(imageCursor != null && imageCursor.moveToFirst()){
            int imageId = 0;
            String imagePath = null;
            int orientation = 0;

            int imageIdColumnIndex = imageCursor.getColumnIndex(MediaStore.Images.Media._ID);
            int imagePathColumnIndex = imageCursor.getColumnIndex(MediaStore.Images.Media.DATA);
            int imageOrientationColumnIndex = imageCursor.getColumnIndex(MediaStore.Images.Media.ORIENTATION);

            do{
                imageId = imageCursor.getInt(imageIdColumnIndex);
                imagePath = imageCursor.getString(imagePathColumnIndex);
                orientation = imageCursor.getInt(imageOrientationColumnIndex);

                // 이미지 정보 중에 필요한 미디어 ID, 이미지 경로, 이미지 방향을 Media 클래스와 ArrayList로 관리한다.
                MediaData mediaData = new MediaData();
                mediaData.type = MainActivity.TYPE_IMAGE;
                mediaData.mediaId = imageId;
                mediaData.mediaPath = imagePath;
                mediaData.orientation = orientation;

                mediaDataList.add(mediaData);
            }while (imageCursor.moveToNext());
        }
    }
}
