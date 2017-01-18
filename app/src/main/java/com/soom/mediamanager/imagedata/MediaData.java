package com.soom.mediamanager.imagedata;

import android.widget.ImageView;

/**
 * Created by kjs on 2017-01-03.
 */

public class MediaData {
    public int type = 0;                // 비디오, 이미지 구분
    public int mediaId = 0;             // 미디어 ID
    public String mediaPath = null;     // 미디어 경로
    public int orientation = 0;         // 미디어 방향
    public ImageView imageView = null;  // 이미지를 표시하기 위한 뷰.
}
