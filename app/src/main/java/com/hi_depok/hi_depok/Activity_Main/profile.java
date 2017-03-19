package com.hi_depok.hi_depok.Activity_Main;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.fragment_profile_activity.myprofile;
import com.hi_depok.hi_depok.fragment_profile_activity.history;

public class profile extends AppCompatActivity implements View.OnClickListener {

    ViewPager pager;
    ProfilePager adapter;
    View strip;
    TextView profile, history;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        pager = (ViewPager) findViewById(R.id.pager);
        profile = (TextView) findViewById(R.id.profile);
        history = (TextView) findViewById(R.id.history);
        strip = findViewById(R.id.strip);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        adapter = new ProfilePager(getSupportFragmentManager());
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

        profile.setOnClickListener(this);
        history.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profile:
                pager.setCurrentItem(0);
                break;
            case R.id.history:
                pager.setCurrentItem(1);
                break;
            default:
                break;
        }
    }

    class ProfilePager extends FragmentPagerAdapter {
        myprofile myprofile;
        history history;

        public ProfilePager(FragmentManager fm) {
            super(fm);
            myprofile = com.hi_depok.hi_depok.fragment_profile_activity.myprofile.newInstance();
            history = com.hi_depok.hi_depok.fragment_profile_activity.history.newInstance();
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return myprofile;
                case 1:
                    return history;
                default:
                    return myprofile;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    public void sett (View view){
        Intent intent = new Intent(profile.this, setprofile.class);
        startActivity(intent);
    }
}
