package com.hi_depok.hi_depok.Activity_Main;

import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.fragment_kapok_activity.fragment1;
import com.hi_depok.hi_depok.fragment_kapok_activity.fragment2;

public class kapok extends AppCompatActivity implements View.OnClickListener{

    ViewPager pager;
    KapokPager adapter;
    View strip;
    ImageView trans, play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kapok);
        pager = (ViewPager) findViewById(R.id.pager);
        trans = (ImageView) findViewById(R.id.trans);
        play = (ImageView) findViewById(R.id.play);
        strip = findViewById(R.id.strip);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        TextView tv = (TextView) findViewById(R.id.kapok);
        Typeface tf = Typeface.createFromAsset(getAssets(), "font/Blogger_Sans-Bold.otf");
        tv.setTypeface(tf);

        adapter = new KapokPager(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position) {
                    case 0:
                        strip.setTranslationX(positionOffsetPixels / 2);
                        break;
                    case 1:
                        strip.setTranslationX(strip.getWidth() + positionOffsetPixels / 2);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        trans.setOnClickListener(this);
        play.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.trans:
                pager.setCurrentItem(0);
                break;
            case R.id.play:
                pager.setCurrentItem(1);
                break;
            default:
                break;
        }
    }

    class KapokPager extends FragmentPagerAdapter {
        fragment1 fragment1;
        fragment2 fragment2;

        public KapokPager(FragmentManager fm) {
            super(fm);
            fragment1 = com.hi_depok.hi_depok.fragment_kapok_activity.fragment1.newInstance();
            fragment2 = com.hi_depok.hi_depok.fragment_kapok_activity.fragment2.newInstance();
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return fragment1;
                case 1:
                    return fragment2;
                default:
                    return fragment1;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
