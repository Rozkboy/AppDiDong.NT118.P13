package com.example.lab2_22521602_5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ThumbnailAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ThumbnailItem> thumbnails;

    public ThumbnailAdapter(Context context, ArrayList<ThumbnailItem> thumbnails) {
        this.context = context;
        this.thumbnails = thumbnails;
    }

    @Override
    public int getCount() {
        return thumbnails.size();
    }

    @Override
    public Object getItem(int position) {
        return thumbnails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Hiển thị thumbnail đã chọn trên Spinner
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_selected_thumbail, parent, false);
        }
        ImageView selectedThumbnailImage = convertView.findViewById(R.id.selected_thumbnail_image);
        TextView selectedThumbnailName = convertView.findViewById(R.id.selected_thumbnail_name);
        ThumbnailItem currentItem = thumbnails.get(position);
        selectedThumbnailImage.setImageResource(currentItem.getImageResId());
        selectedThumbnailName.setText(currentItem.getName());

        return convertView;
    }
}
