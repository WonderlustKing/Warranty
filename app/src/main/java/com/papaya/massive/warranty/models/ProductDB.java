package com.papaya.massive.warranty.models;

import java.util.Date;

/**
 * Created by christos on 31/7/2016.
 */
public class ProductDB {
    private String title;
    private int date_buy;
    private int date_expire;
    private String store;
    private String note;
    private int notification_day;
    private int notification;
    private double price;

    public ProductDB(){}

    public ProductDB(String title, int date_buy, int date_expire, String store, String note, int notification_day, int notification, double price) {
        this.title = title;
        this.date_buy = date_buy;
        this.date_expire = date_expire;
        this.store = store;
        this.note = note;
        this.notification_day = notification_day;
        this.notification = notification;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDate_buy() {
        return date_buy;
    }

    public void setDate_buy(int date_buy) {
        this.date_buy = date_buy;
    }

    public int getDate_expire() {
        return date_expire;
    }

    public void setDate_expire(int date_expire) {
        this.date_expire = date_expire;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getNotification_day() {
        return notification_day;
    }

    public void setNotification_day(int notification_day) {
        this.notification_day = notification_day;
    }

    public int isNotification() {
        return notification;
    }

    public void setNotification(int notification) {
        this.notification = notification;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
