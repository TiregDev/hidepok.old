package com.hi_depok.hi_depok.Kadepok_Donasi;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.hi_depok.hi_depok.Kadepok_Cherish.kadepok_cherish;
import com.hi_depok.hi_depok.Kadepok_Volunteer.kadepok_volunteer;
import com.hi_depok.hi_depok.R;

public class kadepok_donasi_panti extends AppCompatActivity {
    public Button btn_donate;
    private ImageView btn_cherish, btn_volunteer;
    private ImageButton btn_kadepok_notifikasi;
    private PopupWindow popup_notifikasi;
    public Button close;

    public void gotocherish() {
        btn_cherish = (ImageView)findViewById(R.id.btn_cherish);
        btn_cherish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent cherish = new Intent(kadepok_donasi_panti.this, kadepok_cherish.class);
                startActivity(cherish);
            }
        });
    }

    public void gotovolunteer() {
        btn_volunteer = (ImageView)findViewById(R.id.btn_volunteer);
        btn_volunteer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent cherish = new Intent(kadepok_donasi_panti.this, kadepok_volunteer.class);
                startActivity(cherish);
            }
        });
    }

    private void initiatepopup() {
        try {
            LayoutInflater layoutInflater = (LayoutInflater)kadepok_donasi_panti.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = layoutInflater.inflate(R.layout.kadepok_notifikasi, (ViewGroup)findViewById(R.id.kadepok_notifikasi));

            popup_notifikasi = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
            popup_notifikasi.showAtLocation(layout, Gravity.CENTER, 0, 0);

            close = (Button)layout.findViewById(R.id.close);
            close.setOnClickListener(cancel_button_click_listener);

            Button notif = (Button)layout.findViewById(R.id.notif);
            notif.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent notif = new Intent(kadepok_donasi_panti.this, kadepok_donasi_upload.class);
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

    public void init() {
        btn_donate = (Button)findViewById(R.id.btn_donate);
        btn_donate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent donate = new Intent(kadepok_donasi_panti.this, kadepok_donasi_form.class);
                startActivity(donate);
            }
        });
    }

    public void back_profile(View view) {
        Intent intent = new Intent(kadepok_donasi_panti.this, kadepok_donasi.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kadepok_donasi_panti);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
        gotocherish();
        gotovolunteer();

        btn_kadepok_notifikasi = (ImageButton)findViewById(R.id.btn_kadepok_notifikasi);
        btn_kadepok_notifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initiatepopup();
            }
        });
    }
}
