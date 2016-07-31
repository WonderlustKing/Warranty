package com.papaya.massive.warranty.views;

import com.papaya.massive.warranty.models.ProductInfo;

import java.util.List;

/**
 * Created by christos on 30/6/2016.
 */
public interface CardViewOperation {
    public void showCards(List<ProductInfo> listProducts);
    public void expandCard();
    public void favorite();
    public void unfavorite();
    public void notificationsON();
    public void notificationsOFF();
}
