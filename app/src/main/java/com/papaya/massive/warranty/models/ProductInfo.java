package com.papaya.massive.warranty.models;

/**
 * Created by christos on 30/6/2016.
 */
public class ProductInfo {
    private String name;
    private int days_left;
    private int color;
    private int progressbarPetcent;
    private String bought_at;
    private String expire_date;

    public ProductInfo(String name, int days_left, int color,int progressnarPercent, String bought_at,String expire_date) {
        this.name = name;
        this.days_left = days_left;
        this.color = color;
        this.progressbarPetcent = progressnarPercent;
        this.bought_at = bought_at;
        this.expire_date = expire_date;
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

    public int getProgressbarPetcent() {
        return progressbarPetcent;
    }

    public void setProgressbarPetcent(int progressbarPetcent) {
        this.progressbarPetcent = progressbarPetcent;
    }

    public String getBought_at() {
        return bought_at;
    }

    public void setBought_at(String bought_at) {
        this.bought_at = bought_at;
    }

    public String getExpire_date() {
        return expire_date;
    }

    public void setExpire_date(String expire_date) {
        this.expire_date = expire_date;
    }
}
