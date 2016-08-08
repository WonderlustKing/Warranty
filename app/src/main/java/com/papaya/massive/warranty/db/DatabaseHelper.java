package com.papaya.massive.warranty.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.papaya.massive.warranty.models.ProductDB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by christos on 31/7/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String LOG = "DatabaseHelper";
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "warrantydb";

    private static final String PRODUCTS_TABLE = "products";

    private static final String KEY_ID = "id";
    private static final String KEY_TYPE = "type";
    private static final String KEY_NAME = "title";
    private static final String KEY_BUY_DATE = "date_buy";
    private static final String KEY_EXPIRE_DATE = "date_expire";
    private static final String KEY_STORE = "store";
    private static final String KEY_NOTE = "note";
    private static final String KEY_NOTIFICATIONDAY = "notification_day";
    private static final String KEY_NOTIFICATION = "notification";
    private static final String KEY_PRICE = "price";

    private static final String CREATE_TABLE_PRODUCTS = "CREATE TABLE " + PRODUCTS_TABLE
            + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            + KEY_TYPE + " TEXT,"
            + KEY_NAME + " TEXT,"
            + KEY_BUY_DATE +" INTEGER,"
            + KEY_EXPIRE_DATE+" INTEGER,"
            + KEY_STORE+" TEXT,"
            + KEY_NOTE +" TEXT,"
            + KEY_NOTIFICATIONDAY +" INTEGER,"
            + KEY_NOTIFICATION +" INTEGER,"
            + KEY_PRICE + " REAL"
            +")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PRODUCTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS "+PRODUCTS_TABLE);
        onCreate(db);
    }

    public boolean insertProduct(ProductDB product){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TYPE, product.getType());
        values.put(KEY_NAME,product.getTitle());
        values.put(KEY_BUY_DATE, product.getDate_buy());
        values.put(KEY_EXPIRE_DATE, product.getDate_expire());
        values.put(KEY_STORE, product.getStore());
        values.put(KEY_NOTE, product.getNote());
        values.put(KEY_NOTIFICATIONDAY, product.getNotification_day());
        values.put(KEY_NOTIFICATION, product.isNotification());
        values.put(KEY_PRICE, product.getPrice());

        long product_new = db.insert("products", null, values);
        if(product_new == -1) return false;
        return true;
    }

    public List<ProductDB> getAllProducts(){
        List<ProductDB> productsdb = new ArrayList<ProductDB>();
        String query = " SELECT * FROM products WHERE type = 'VALID' ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        if(c.moveToFirst()){
            do{
                ProductDB product = new ProductDB();
                product.setType(c.getString(c.getColumnIndex(KEY_TYPE)));
                product.setTitle(c.getString(c.getColumnIndex(KEY_NAME)));
                product.setDate_buy(c.getInt(c.getColumnIndex(KEY_BUY_DATE)));
                product.setDate_expire(c.getInt(c.getColumnIndex(KEY_EXPIRE_DATE)));
                product.setStore(c.getString(c.getColumnIndex(KEY_STORE)));
                product.setNote(c.getString(c.getColumnIndex(KEY_NOTE)));
                product.setNotification_day(c.getInt(c.getColumnIndex(KEY_NOTIFICATIONDAY)));
                product.setNotification(c.getInt(c.getColumnIndex(KEY_NOTIFICATION)));
                product.setPrice(c.getDouble(c.getColumnIndex(KEY_PRICE)));

                productsdb.add(product);
            }while(c.moveToNext());
        }
        return productsdb;
    }
}
