package com.papaya.massive.warranty.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.papaya.massive.warranty.R;

/**
 * Created by christos on 30/6/2016.
 */
public class ExpiredTab extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.expired_tab, container, false);
    }

}
