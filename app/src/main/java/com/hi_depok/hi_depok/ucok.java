package com.hi_depok.hi_depok;

import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.R.layout;

import com.hi_depok.hi_depok.fragment_sikepok_activity.fragment1;
import com.hi_depok.hi_depok.fragment_sikepok_activity.fragment2;
import com.hi_depok.hi_depok.fragment_sikepok_activity.fragment3;

public class ucok extends AppCompatActivity {

    FragmentPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ucok);

        final ViewPager mViewPager = (ViewPager) findViewById(R.id.viewpager);
        adapterViewPager = new ucok.MyPagerAdapter(getSupportFragmentManager());

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.donut));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.money));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.play));

        mViewPager.setAdapter(adapterViewPager);
//        tabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new
                TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 3;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        Fragment fragment;

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    fragment = new fragment1();
                    return fragment;
                case 1:
                    fragment = new fragment2();
                    return fragment;
                case 2:
                    fragment = new fragment3();
                    return fragment;
                default:
                    return null;
            }
        }
    }
}
