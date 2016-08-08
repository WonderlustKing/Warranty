package com.papaya.massive.warranty.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.papaya.massive.warranty.R;
import com.papaya.massive.warranty.db.DatabaseHelper;
import com.papaya.massive.warranty.fragments.DatePickerFragment;
import com.papaya.massive.warranty.models.ProductDB;
import com.papaya.massive.warranty.presenters.NewEntryPresenter;
import com.papaya.massive.warranty.presenters.NewEntryPresenterImp;
import com.papaya.massive.warranty.views.NewEntryOperation;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by christos on 22/7/2016.
 */
public class NewEntryActivity extends AppCompatActivity implements NewEntryOperation {
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

    private NewEntryPresenter newEntryPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_entry);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        db = new DatabaseHelper(this);
        newEntryPresenter = new NewEntryPresenterImp(this,db);

        final Drawable cancel = ContextCompat.getDrawable(this, R.drawable.abc_ic_clear_mtrl_alpha);
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
                newEntryPresenter.addProduct(title_tv.getText().toString()
                        ,purchase_date_tv.getText().toString()
                        ,expores_date_tv.getText().toString()
                        ,store_tv.getText().toString()
                        ,notes_tv.getText().toString()
                        ,notificationday_tv.getText().toString()
                        ,price_tv.getText().toString());
            }
        });

        purchase_date_tv.setInputType(InputType.TYPE_NULL);
        purchase_date_tv.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                   newEntryPresenter.editTextFocus(1);
                }
            }
        });
        expores_date_tv.setInputType(InputType.TYPE_NULL);
        expores_date_tv.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    newEntryPresenter.editTextFocus(2);
                }
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

    @Override
    public void showDatePicker(int edittext){
        DatePickerFragment date = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        if(edittext == 1){
            date.setCallBack(ondateBuy);
        }else{
            date.setCallBack(ondateExpire);
        }

        date.show(getSupportFragmentManager(), "Date Picker");
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void returnToMainActivity() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    DatePickerDialog.OnDateSetListener ondateBuy = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
                if(!expores_date_tv.getText().toString().equals("")) {
                    boolean flag = newEntryPresenter.checkDates(String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear + 1)
                            + "-" + String.valueOf(year), expores_date_tv.getText().toString());
                    if (flag) {
                        purchase_date_tv.setText(String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear + 1)
                                + "-" + String.valueOf(year));
                    }
                }else{
                    purchase_date_tv.setText(String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear + 1)
                            + "-" + String.valueOf(year));
                }
        }
    };

    DatePickerDialog.OnDateSetListener ondateExpire = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            if(!purchase_date_tv.getText().toString().equals("")) {
                boolean flag = newEntryPresenter.checkDates(purchase_date_tv.getText().toString(), String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear + 1)
                        + "-" + String.valueOf(year));
                if (flag) {
                    expores_date_tv.setText(String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear + 1)
                            + "-" + String.valueOf(year));
                }
            }else{
                expores_date_tv.setText(String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear + 1)
                        + "-" + String.valueOf(year));
            }
        }
    };


}
