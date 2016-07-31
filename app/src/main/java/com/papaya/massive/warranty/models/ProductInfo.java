package com.papaya.massive.warranty.models;

/**
 * Created by christos on 30/6/2016.
 */
public class ProductInfo {
    private String name;
    private int days_left;
    private int color;

    public ProductInfo(String name, int days_left, int color) {
        this.name = name;
        this.days_left = days_left;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDays_left() {
        return days_left;
    }

    public void setDays_left(int days_left) {
        this.days_left = days_left;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
