package com.example.lab2_22521602_5;

public class Dish {
    private String name;
    private int thumbnail;
    private boolean hasPromotion;

    public Dish(String name, int thumbnail, boolean hasPromotion) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.hasPromotion = hasPromotion;
    }

    public String getName() {
        return name;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public boolean hasPromotion() {
        return hasPromotion;
    }
}
