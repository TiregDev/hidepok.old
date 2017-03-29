package com.hi_depok.hi_depok.fokopok;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hi_depok.hi_depok.Activity_Main.fokopok;
import com.hi_depok.hi_depok.R;

public class fokopok_content extends AppCompatActivity implements View.OnClickListener {
    ViewPager pager;
    FokopokContent adapter;
    View strip;
    ImageView komunitas, home;
    TextView komunitas_text, home_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fokopok_content);
        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pager = (ViewPager) findViewById(R.id.pager);
        komunitas = (ImageView) findViewById(R.id.komunitas);
        home = (ImageView) findViewById(R.id.home);
        strip = findViewById(R.id.strip);
        komunitas_text = (TextView) findViewById(R.id.komunitas_text);
        home_text = (TextView) findViewById(R.id.home_text);

        adapter = new FokopokContent(getSupportFragmentManager());
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

        komunitas.setOnClickListener(this);
        home.setOnClickListener(this);
        komunitas_text.setOnClickListener(this);
        home_text.setOnClickListener(this);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_new_msg:
                // todo: goto back activity from here

                startActivity(new Intent(getBaseContext(), message.class));
                return true;
            case android.R.id.home:
                // todo: goto back activity from here

                fokopok_content.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fokopok, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.komunitas:
                pager.setCurrentItem(0);
                break;
            case R.id.home:
                pager.setCurrentItem(1);
                break;
            case R.id.komunitas_text:
                pager.setCurrentItem(0);
                break;
            case R.id.home_text:
                pager.setCurrentItem(1);
                break;
            default:
                break;
        }
    }

    class FokopokContent extends FragmentPagerAdapter {
        com.hi_depok.hi_depok.fokopok.fragment_content.fragment1 fragment1;
        com.hi_depok.hi_depok.fokopok.fragment_content.fragment2 fragment2;

        public FokopokContent(FragmentManager fm) {
            super(fm);
            fragment1 = com.hi_depok.hi_depok.fokopok.fragment_content.fragment1.newInstance();
            fragment2 = com.hi_depok.hi_depok.fokopok.fragment_content.fragment2.newInstance();
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