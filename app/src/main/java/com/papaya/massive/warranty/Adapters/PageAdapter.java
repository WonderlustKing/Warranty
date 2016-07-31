package com.papaya.massive.warranty.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.papaya.massive.warranty.fragments.AllTab;
import com.papaya.massive.warranty.fragments.ExpiredTab;
import com.papaya.massive.warranty.fragments.ValidTab;

/**
 * Created by christos on 30/6/2016.
 */
public class PageAdapter extends FragmentStatePagerAdapter {
    private int mNumOfTabs;

    public PageAdapter(FragmentManager fm, int mNumOfTabs){
        super(fm);
        this.mNumOfTabs = mNumOfTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ValidTab tab1 = new ValidTab();
                return tab1;
            //return ValidTab.newInstance(apiClient);
            case 1:
                ExpiredTab tab2 = new ExpiredTab();
                return tab2;
            case 2:
                AllTab tab3 = new AllTab();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
