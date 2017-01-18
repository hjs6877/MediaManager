package com.soom.mediamanager;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.soom.mediamanager.imagedata.MediaData;
import com.soom.mediamanager.imagehandle.ImageLoader;

import java.util.ArrayList;

/**
 * Created by kjs on 2017-01-17.
 */

public class ImageAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    private ImageLoader imageLoader;

    private ArrayList<MediaData> mediaDataList;

    public ImageAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(this.context);
        imageLoader = new ImageLoader(context.getApplicationContext());
    }

    public void initData(ArrayList<MediaData> mediaDataList){
        this.mediaDataList = mediaDataList;
    }

    @Override
    public int getCount() {
        if(mediaDataList == null){
            return 0;
        }
        return mediaDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MediaData mediaData;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.thumbnail, null);
            mediaData = new MediaData();
            mediaData.imageView = (ImageView) convertView.findViewById(R.id.thumbnail);

            convertView.setTag(mediaData);
        }else{
            mediaData = (MediaData) convertView.getTag();
        }

        if(mediaDataList == null || mediaDataList.size() == 0)
            return convertView;

        mediaData.type = mediaDataList.get(position).type;
        mediaData.mediaId = mediaDataList.get(position).mediaId;
        mediaData.mediaPath = mediaDataList.get(position).mediaPath;
        mediaData.orientation = mediaDataList.get(position).orientation;

        imageLoader.displayImage(mediaData);

        return convertView;
    }
}
