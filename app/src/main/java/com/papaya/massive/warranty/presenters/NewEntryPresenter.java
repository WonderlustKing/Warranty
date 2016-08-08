package com.papaya.massive.warranty.presenters;

import android.widget.EditText;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Date;

/**
 * Created by christos on 1/8/2016.
 */
public interface NewEntryPresenter {
    void editTextFocus(int edittext);
    void addProduct(String title_text, String purchase_text, String expire_text, String store_text, String notes_text, String notification_text, String price_text );
    boolean checkDates(String date1, String date2);
}
