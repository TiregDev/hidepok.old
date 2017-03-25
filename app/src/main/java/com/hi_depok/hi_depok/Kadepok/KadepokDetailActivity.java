package com.hi_depok.hi_depok.Kadepok;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hi_depok.hi_depok.Kadepok_Donasi.kadepok_donasi_upload;
import com.hi_depok.hi_depok.R;

public class KadepokDetailActivity extends AppCompatActivity implements View.OnClickListener {
    ViewPager pager;
    Content adapter;
    View strip;
    TextView deskripsi, donasi, cherish;
    private ImageView btn_kadepok_notifikasi;
    private PopupWindow popup_notifikasi;
    public Button close;

    private void initiatepopup() {
        try {
            LayoutInflater layoutInflater = (LayoutInflater)KadepokDetailActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = layoutInflater.inflate(R.layout.kadepok_notifikasi, (ViewGroup)findViewById(R.id.kadepok_notifikasi));

            popup_notifikasi = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
            popup_notifikasi.showAtLocation(layout, Gravity.CENTER, 0, 0);

            close = (Button)layout.findViewById(R.id.close);
            close.setOnClickListener(cancel_button_click_listener);

            Button notif = (Button)layout.findViewById(R.id.notif);
            notif.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent notif = new Intent(KadepokDetailActivity.this, kadepok_donasi_upload.class);
                    startActivity(notif);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View.OnClickListener cancel_button_click_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            popup_notifikasi.dismiss();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kadepok_detail_panti);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pager = (ViewPager) findViewById(R.id.pager);
        deskripsi = (TextView) findViewById(R.id.deskripsi_panti);
        donasi = (TextView) findViewById(R.id.donasi_panti);
        cherish = (TextView) findViewById(R.id.cherish_us);
        strip = findViewById(R.id.strip);setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        adapter = new Content(getSupportFragmentManager());
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

        deskripsi.setOnClickListener(this);
        donasi.setOnClickListener(this);
        cherish.setOnClickListener(this);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_kadepok, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_notif:
                // todo: goto back activity from here

                initiatepopup();
                return true;

            case android.R.id.home:
                // todo: goto back activity from here

                KadepokDetailActivity.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.deskripsi_panti:
                pager.setCurrentItem(0);
                break;
            case R.id.donasi_panti:
                pager.setCurrentItem(1);
                break;
            case R.id.cherish_us:
                pager.setCurrentItem(2);
                break;
            default:
                break;
        }
    }

    class Content extends FragmentPagerAdapter {
        com.hi_depok.hi_depok.Kadepok.fragment.fragment1 fragment1;
        com.hi_depok.hi_depok.Kadepok.fragment.fragment2 fragment2;
        com.hi_depok.hi_depok.Kadepok.fragment.fragment3 fragment3;

        public Content(FragmentManager fm) {
            super(fm);
            fragment1 = com.hi_depok.hi_depok.Kadepok.fragment.fragment1.newInstance();
            fragment2 = com.hi_depok.hi_depok.Kadepok.fragment.fragment2.newInstance();
            fragment3 = com.hi_depok.hi_depok.Kadepok.fragment.fragment3.newInstance();
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


}
