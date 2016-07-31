package com.papaya.massive.warranty.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.papaya.massive.warranty.Adapters.ProductAdapter;
import com.papaya.massive.warranty.R;
import com.papaya.massive.warranty.models.ProductInfo;
import com.papaya.massive.warranty.presenters.CardPresenter;
import com.papaya.massive.warranty.presenters.CardPresenterImp;
import com.papaya.massive.warranty.views.CardViewOperation;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by christos on 30/6/2016.
 */
public class ValidTab extends Fragment implements CardViewOperation {

    private CardPresenter cardPresenter;
    private RecyclerView recList;
    private ProductAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.valid_tab, container, false);
        recList = (RecyclerView) rootView.findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cardPresenter = new CardPresenterImp(getContext(),this);
        cardPresenter.getValidProducts();
    }

    @Override
    public void showCards(List<ProductInfo> productInfos) {
        adapter = new ProductAdapter(productInfos);
        recList.setAdapter(adapter);
    }

    @Override
    public void expandCard() {

    }

    @Override
    public void favorite() {

    }

    @Override
    public void unfavorite() {

    }

    @Override
    public void notificationsON() {

    }

    @Override
    public void notificationsOFF() {

    }
}
