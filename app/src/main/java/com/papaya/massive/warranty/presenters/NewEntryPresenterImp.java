package com.papaya.massive.warranty.presenters;

import android.util.Log;
import android.widget.Toast;

import com.papaya.massive.warranty.db.DatabaseHelper;
import com.papaya.massive.warranty.models.ProductDB;
import com.papaya.massive.warranty.views.NewEntryOperation;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by christos on 1/8/2016.
 */
public class NewEntryPresenterImp implements NewEntryPresenter {

    private NewEntryOperation operation;
    private DatabaseHelper db;

    public NewEntryPresenterImp(NewEntryOperation operation, DatabaseHelper db){
        this.operation = operation;
        this.db = db;
    }

    @Override
    public void editTextFocus(int edittext) {
        operation.showDatePicker(edittext);
    }

    @Override
    public void addProduct(String title_text, String purchase_text, String expire_text, String store_text, String notes_text, String notification_text, String price_text ){
        ProductDB product = new ProductDB();
        product.setType("VALID");
        product.setTitle(title_text);
        product.setDate_buy(dateConvert(purchase_text));
        product.setDate_expire(dateConvert(expire_text));
        product.setStore(store_text);
        product.setNote(notes_text);
        product.setNotification_day(Integer.parseInt(notification_text));
        product.setNotification(1);
        product.setPrice(Double.parseDouble(price_text));
        boolean flag = db.insertProduct(product);
        if (flag) {
            operation.showToast("Product saved");
        }else {
            operation.showToast("Something go wrong");
        }
    }

    @Override
    public boolean checkDates(String date1, String date2) {
        long buy_date = dateConvert(date1);
        long expire_date = dateConvert(date2);

        if(buy_date > expire_date){
            operation.showToast("Expire day cant be before purchase date");
            return false;
        }
        return true;
    }

    public long dateConvert(String date){

        String dateParts[] = date.split("-");
        int day = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);
        return convertDateToInt(year,month,day);
    }


    private long convertDateToInt(int year,int month, int day){
        Calendar c = Calendar.getInstance();
        c.set(year,month-1,day,0,0);
        //c.add(Calendar.DAY_OF_YEAR, 20);
        Date date=new Date();

        long msL = c.getTimeInMillis() / 1000;
       // int ms = (int) date.getTime()/1000;
        Log.d("UNIXtime"," "+msL);
        return msL;
    }

}
