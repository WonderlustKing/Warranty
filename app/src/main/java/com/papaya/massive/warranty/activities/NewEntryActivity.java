package com.papaya.massive.warranty.activities;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.papaya.massive.warranty.R;
import com.papaya.massive.warranty.db.DatabaseHelper;
import com.papaya.massive.warranty.models.ProductDB;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by christos on 22/7/2016.
 */
public class NewEntryActivity extends AppCompatActivity {
    DatabaseHelper db;
    @BindView(R.id.tb_new_entry)
    Toolbar toolbar;

    @BindView(R.id.new_entry_fab)
    FloatingActionButton fab;

    @BindView(R.id.tv_title)
    MaterialEditText title_tv;

    @BindView(R.id.tv_purchase)
    MaterialEditText purchase_date_tv;

    @BindView(R.id.tv_expires)
    MaterialEditText expores_date_tv;

    @BindView(R.id.tv_shop)
    MaterialEditText store_tv;

    @BindView(R.id.tv_notes)
    MaterialEditText notes_tv;

    @BindView(R.id.tv_notification)
    MaterialEditText notificationday_tv;

    @BindView(R.id.tv_price)
    MaterialEditText price_tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_entry);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        db = new DatabaseHelper(this);

        final Drawable cancel = ContextCompat.getDrawable(this,R.drawable.abc_ic_clear_mtrl_alpha);
        cancel.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(cancel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveIntoDb();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    public void saveIntoDb() {
        ProductDB product = new ProductDB();
        product.setTitle(title_tv.getText().toString());
        product.setDate_buy(Integer.parseInt(purchase_date_tv.getText().toString()));
        product.setDate_expire(Integer.parseInt(expores_date_tv.getText().toString()));
        product.setStore(store_tv.getText().toString());
        product.setNote(notes_tv.getText().toString());
        product.setNotification_day(Integer.parseInt(notificationday_tv.getText().toString()));
        product.setNotification(1);
        product.setPrice(Double.parseDouble(price_tv.getText().toString()));
        boolean flag = db.insertProduct(product);
        if (flag) {
            Toast.makeText(this, "product saved", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Something go wrong...", Toast.LENGTH_SHORT).show();
        }

    }
}
