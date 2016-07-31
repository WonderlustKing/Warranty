package com.papaya.massive.warranty.presenters;

/**
 * Created by christos on 30/6/2016.
 */
public interface CardPresenter {
    public void getValidProducts();
    public void getExpiredProducts();
    public void getAllProducts();
    public void addProduct();
    public void favoriteProduct(boolean state);
    public void notification(boolean state);
}
