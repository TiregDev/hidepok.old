package com.hi_depok.hi_depok.Kadepok;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
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
import android.widget.TextView;
import android.widget.Toast;

import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.Kadepok_Volunteer.kadepok_volunteer_form;
import com.hi_depok.hi_depok.R;

import static com.hi_depok.hi_depok.R.id.btn_volunteer;

public class KadepokDetailActivity extends BaseActivity implements View.OnClickListener {
    ViewPager pager;
    Content adapter;
    View strip;
    TextView deskripsi, donasi, cherish;
    private ImageView btn_kadepok_notifikasi;
    private PopupWindow popup_notifikasi;
    public Button close;

//    private void initiatepopup() {
//        try {
//            LayoutInflater layoutInflater = (LayoutInflater)KadepokDetailActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View layout = layoutInflater.inflate(R.layout.kadepok_donasi_popup, (ViewGroup)findViewById(R.id.rl_custom_layout));
//
//            popup_notifikasi = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
//            popup_notifikasi.showAtLocation(layout, Gravity.CENTER, 0, 0);
//
//            close = (Button)layout.findViewById(R.id.close);
//            close.setOnClickListener(cancel_button_click_listener);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

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
        super.onCreateDrawer();
        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pager = (ViewPager) findViewById(R.id.pager);
        deskripsi = (TextView) findViewById(R.id.deskripsi_panti);
        donasi = (TextView) findViewById(R.id.donasi_panti);
        cherish = (TextView) findViewById(R.id.cherish_us);
        strip = findViewById(R.id.strip);
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
            case R.id.action_share:
//                todo: goto back activity from here
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Yuk memberi dampak perubahan positif" +
                        "untuk kota Depok ;) ";
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
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
    public void toMaps(View v){
        Double myLatitude = -6.3656374;
        Double myLongitude = 106.8237375;
        String labelLocation = "Perpus UI";

        String urlAddress = "http://maps.google.com/maps?q=" + myLatitude + "," + myLongitude + "(" + labelLocation + ")&iwloc=A&hl=es";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlAddress));
        startActivity(intent);
    }

    public void toCall(View v){
        String PhoneNo="085695454139";
        Intent dial = new Intent();
        dial.setAction("android.intent.action.DIAL");
        dial.setData(Uri.parse("tel:" + PhoneNo));
        startActivity(dial);
    }

}
