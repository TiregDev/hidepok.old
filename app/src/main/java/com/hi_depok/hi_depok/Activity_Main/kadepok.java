package com.hi_depok.hi_depok.Activity_Main;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.hi_depok.hi_depok.Kadepok.activity.kadepok_content;
import com.hi_depok.hi_depok.Kadepok_Volunteer.kadepok_volunteer_form;
import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.fragment_kadepok_activity.fragment1;
import com.hi_depok.hi_depok.fragment_kadepok_activity.fragment3;
import com.hi_depok.hi_depok.fragment_kadepok_activity.fragment4;

public class kadepok extends BaseActivity implements View.OnClickListener{

    ViewPager pager;
    KadepokPager adapter;
    View strip;
    ImageView donate, volunteer, play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kadepok);
        super.onCreateDrawer();
        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, android.R.color.transparent));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        pager = (ViewPager) findViewById(R.id.pager);
        donate = (ImageView) findViewById(R.id.donate);
        volunteer = (ImageView) findViewById(R.id.volunteer);
        play = (ImageView) findViewById(R.id.play);
        strip = findViewById(R.id.strip);

        TextView tv = (TextView) findViewById(R.id.kadepok);
        Typeface tf = Typeface.createFromAsset(getAssets(), "font/Blogger_Sans-Bold.otf");
        tv.setTypeface(tf);

        adapter = new KadepokPager(getSupportFragmentManager());
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

        donate.setOnClickListener(this);
        volunteer.setOnClickListener(this);
        play.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.donate:
                pager.setCurrentItem(0);
                break;
            case R.id.volunteer:
                pager.setCurrentItem(1);
                break;
            case R.id.play:
                pager.setCurrentItem(2);
                break;
            default:
                break;
        }
    }

    class KadepokPager extends FragmentPagerAdapter {
        fragment1 fragment1;
        fragment3 fragment3;
        fragment4 fragment4;

        public KadepokPager(FragmentManager fm) {
            super(fm);
            fragment1 = com.hi_depok.hi_depok.fragment_kadepok_activity.fragment1.newInstance();
            fragment3 = com.hi_depok.hi_depok.fragment_kadepok_activity.fragment3.newInstance();
            fragment4 = com.hi_depok.hi_depok.fragment_kadepok_activity.fragment4.newInstance();
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return fragment1;
                case 1:
                    return fragment3;
                case 2:
                    return fragment4;
                default:
                    return fragment1;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    public void donasi(View v) {
        Intent intent = new Intent(kadepok.this, kadepok_content.class);
        startActivity(intent);
    }

    public void volunteer(View v) {
        Intent intent = new Intent(kadepok.this, kadepok_volunteer_form.class);
        startActivity(intent);
    }
    public void gotToYoutube(View v){
        String id = "TiregDev";
        try {

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://" + id));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        } catch (ActivityNotFoundException e) {

            // youtube is not installed.Will be opened in other available apps

            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com/watch?v=" + id));
            startActivity(i);
        }
    }
}
