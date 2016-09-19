package com.papaya.massive.warranty.presenters;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.papaya.massive.warranty.db.DatabaseHelper;
import com.papaya.massive.warranty.models.ProductDB;
import com.papaya.massive.warranty.models.ProductInfo;
import com.papaya.massive.warranty.views.CardViewOperation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
        List<ProductDB> productDBs = dbHelper.getAllProducts();
        for(ProductDB productDB : productDBs){
            int days_left = getDaysLeft(productDB);
            if(days_left > 0) {
                String expire_date = fromUnixTimeToDate(productDB.getDate_expire());
                int prog = 100 - getProgressPrc(productDB);
                if (prog <= 10) {
                    productInfos.add(new ProductInfo(productDB.getTitle(), days_left, 0xffF04646, getProgressPrc(productDB),productDB.getStore(),expire_date));
                } else if (prog <= 25) {
                    productInfos.add(new ProductInfo(productDB.getTitle(), days_left, 0xffE7F046, getProgressPrc(productDB),productDB.getStore(),expire_date));
                } else
                    productInfos.add(new ProductInfo(productDB.getTitle(), days_left, 0xff46F07B, getProgressPrc(productDB),productDB.getStore(),expire_date));
            }
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

    @Override
    public int getDaysLeft(ProductDB product) {
        int diffInDays = (int)( (product.getDate_expire() - Calendar.getInstance().getTimeInMillis() / 1000)
                / 86400 );
        if(diffInDays == 0) dbHelper.updateProductToExpired(product);
        return diffInDays;
    }

    @Override
    public int getProgressPrc(ProductDB product) {
        double time = (double) ((product.getDate_expire() - product.getDate_buy()) / 86400);
        Log.d("Time overall",""+time);
        Log.d("Days left:",""+getDaysLeft(product));
        double progress = 100 - (getDaysLeft(product) / time * 100) ;
        int pr = (int) progress;
        Log.d("progress",""+progress);
        return pr;
    }

    private String fromUnixTimeToDate(long time){
        Date date = new java.util.Date((long)time*1000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE dd MMM yyyy", Locale.getDefault());
        String formatted = dateFormat.format(date);
        return formatted;
    }
}
