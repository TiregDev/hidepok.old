package com.hi_depok.hi_depok.Kapok;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import com.hi_depok.hi_depok.R;
public class activity_selengkapnya extends AppCompatActivity {
    ViewPager viewPager;

    Integer[] imageId = {R.drawable.ucok_image_4, R.drawable.ucok_image_2, R.drawable.ucok_image_5};
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;
    final long PERIOD_MS = 3000;
    private int NUM_PAGES;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView lengkap;
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_selengkapnya);


        lengkap = (TextView) findViewById(R.id.ulasan);
        lengkap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_selengkapnya.this, activity_view_ulasan.class));
            }
        });

        //buat popup
        ImageView tambah = (ImageView) findViewById(R.id.tambah);
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initiatepopup();
            }
        });

        //buat viewpager
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        PagerAdapter adapter = new slideshow_foto(activity_selengkapnya.this, imageId);
        viewPager.setAdapter(adapter);


        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES-1) {
                    currentPage = 0;
                }

                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer .schedule(new TimerTask() { // task to be scheduled

            @Override
            public void run() {
                handler.post(Update);
            }
        }, 500, 3000);




    }
    private PopupWindow pwindo;

    private void initiatepopup() {
        try {
            LayoutInflater inflater = (LayoutInflater) activity_selengkapnya.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.popup_ulasan, (ViewGroup) findViewById(R.id.popup_element));
            pwindo = new PopupWindow(layout, 450,300, true);
            pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);


            ImageView close = (ImageView) layout.findViewById(R.id.close);
            close.setOnClickListener(cancel_button_click_listener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View.OnClickListener cancel_button_click_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            pwindo.dismiss();
        }
    };
}
