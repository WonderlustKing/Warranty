package com.papaya.massive.warranty.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.papaya.massive.warranty.Adapters.PageAdapter;
import com.papaya.massive.warranty.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yugy.github.reveallayout.RevealLayout;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.pager)
    ViewPager viewPager;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.reveal)
    RevealLayout reveal;

    @BindView(R.id.reveal_view)
    View reaveal_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        tabLayout.addTab(tabLayout.newTab().setText("Valid"));
        tabLayout.addTab(tabLayout.newTab().setText("Expired"));
        tabLayout.addTab(tabLayout.newTab().setText("All"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final PageAdapter adapter = new PageAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] location = new int[2];
                fab.getLocationOnScreen(location);
                location[0] += fab.getWidth() / 2;
                location[1] += fab.getHeight() / 2;

                reaveal_view.setVisibility(View.VISIBLE);
                reveal.setVisibility(View.VISIBLE);

                final Intent intent = new Intent(MainActivity.this, NewEntryActivity.class);

                reveal.show(location[0], location[1]); // Expand from center of FAB. Actually, it just plays reveal animation.
                fab.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(intent);
                        /**
                         * Without using R.anim.hold, the screen will flash because of transition
                         * of Activities.
                         */
                        overridePendingTransition(0, R.anim.hold);
                    }
                }, 600); // 600 is default duration of reveal animation in RevealLayout
                fab.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fab.setClickable(true);
                        reveal.setVisibility(View.INVISIBLE);
                        reaveal_view.setVisibility(View.INVISIBLE);
                    }
                }, 900);


                //newEntryActivity();
            }
        });

    }

    public void newEntryActivity(){
        Intent intent = new Intent(this,NewEntryActivity.class);
        startActivity(intent);
    }
}
