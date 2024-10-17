package com.example.lab2_22521602_5;

public class ThumbnailItem {
    private String name;
    private int imageResId;

    public ThumbnailItem(String name, int imageResId) {
        this.name = name;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }
}
