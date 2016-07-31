package com.papaya.massive.warranty.presenters;

import android.content.Context;
import android.widget.Toast;

import com.papaya.massive.warranty.db.DatabaseHelper;
import com.papaya.massive.warranty.models.ProductDB;
import com.papaya.massive.warranty.models.ProductInfo;
import com.papaya.massive.warranty.views.CardViewOperation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by christos on 30/6/2016.
 */
public class CardPresenterImp implements CardPresenter {
    private CardViewOperation cardViewOperation;
    private DatabaseHelper dbHelper;
    private Context context;

    public CardPresenterImp(Context context, CardViewOperation cardViewOperation){
        this.context = context;
        dbHelper = new DatabaseHelper(context);
        this.cardViewOperation = cardViewOperation;
    }

    @Override
    public void getValidProducts() {
        List<ProductInfo> productInfos = new ArrayList<ProductInfo>();
        //productInfos.add(new ProductInfo("Product1",20,0xffff0000));
        //productInfos.add(new ProductInfo("Product2",200,0xff00ff00));
        //productInfos.add(new ProductInfo("Moto G",0,0xff666666));
        List<ProductDB> productDBs = dbHelper.getProducts();
        for(ProductDB productDB : productDBs){
            productInfos.add(new ProductInfo(productDB.getTitle(),20,0xffffaaaa));
        }
        if(productInfos.size() == 0){
            Toast.makeText(context,"No products yet", Toast.LENGTH_LONG).show();
        }else {
            cardViewOperation.showCards(productInfos);
        }
    }

    @Override
    public void getExpiredProducts() {

    }

    @Override
    public void getAllProducts() {

    }

    @Override
    public void addProduct() {

    }

    @Override
    public void favoriteProduct(boolean state) {

    }

    @Override
    public void notification(boolean state) {

    }
}
