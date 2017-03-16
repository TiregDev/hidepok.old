package com.hi_depok.hi_depok.Activity_Main;

import android.content.Intent;
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

import com.hi_depok.hi_depok.Lapok.lapok_content;
import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.Ucok.ucok_content;
import com.hi_depok.hi_depok.fragment_lapok_activity.fragment1;
import com.hi_depok.hi_depok.fragment_lapok_activity.fragment2;
import com.hi_depok.hi_depok.fragment_lapok_activity.fragment3;

public class lapok extends AppCompatActivity implements View.OnClickListener{

    ViewPager pager;
    LapokPager adapter;
    View strip;
    ImageView camera,forum, play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lapok);
        pager = (ViewPager) findViewById(R.id.pager);
        camera = (ImageView) findViewById(R.id.camera);
        forum = (ImageView) findViewById(R.id.forum);
        play = (ImageView) findViewById(R.id.play);
        strip = findViewById(R.id.strip);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        TextView tv = (TextView) findViewById(R.id.lapok);
        Typeface tf = Typeface.createFromAsset(getAssets(), "font/Blogger_Sans-Bold.otf");
        tv.setTypeface(tf);

        adapter = new LapokPager(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position) {
                    case 0:
                        strip.setTranslationX(positionOffsetPixels / 3);
                        break;
                    case 1:
                        strip.setTranslationX(strip.getWidth() + positionOffsetPixels / 3);
                        break;
                    case 2:
                        strip.setTranslationX(strip.getWidth() * 2 + positionOffsetPixels / 3);
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

        camera.setOnClickListener(this);
        forum.setOnClickListener(this);
        play.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.camera:
                pager.setCurrentItem(0);
                break;
            case R.id.forum:
                pager.setCurrentItem(1);
                break;
            case R.id.play:
                pager.setCurrentItem(2);
                break;
            default:
                break;
        }
    }

    class LapokPager extends FragmentPagerAdapter {
        fragment1 fragment1;
        fragment2 fragment2;
        fragment3 fragment3;

        public LapokPager(FragmentManager fm) {
            super(fm);
            fragment1 = com.hi_depok.hi_depok.fragment_lapok_activity.fragment1.newInstance();
            fragment2 = com.hi_depok.hi_depok.fragment_lapok_activity.fragment2.newInstance();
            fragment3 = com.hi_depok.hi_depok.fragment_lapok_activity.fragment3.newInstance();
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return fragment1;
                case 1:
                    return fragment2;
                case 2:
                    return fragment3;
                default:
                    return fragment1;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    public void linkforum(View v){
        Intent intent = new Intent(lapok.this, lapok_content.class);
        startActivity(intent);
    }
}
