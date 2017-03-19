package com.hi_depok.hi_depok.Kadepok_Cherish;

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
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.hi_depok.hi_depok.Kadepok_Donasi.kadepok_donasi_upload;
import com.hi_depok.hi_depok.R;

public class kadepok_cherish_panti extends AppCompatActivity {
    private Button btn_lokasi, btn_daftar_anak, btn_daftar_event, close;
    private ImageView btn_kadepok_notifikasi;
    private PopupWindow popup_notifikasi, popup_lokasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kadepok_cherish_panti);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btn_kadepok_notifikasi = (ImageView) findViewById(R.id.btn_kadepok_notifikasi);
        btn_kadepok_notifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popnotif();
            }
        });

        btn_daftar_anak = (Button)findViewById(R.id.btn_daftar_anak);
        btn_daftar_anak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent daftar_anak = new Intent(kadepok_cherish_panti.this, kadepok_cherish_detail_anak.class);
                startActivity(daftar_anak);
            }
        });

        btn_daftar_event = (Button)findViewById(R.id.btn_daftar_event);
        btn_daftar_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent daftar_anak = new Intent(kadepok_cherish_panti.this, kadepok_cherish_detail_event.class);
                startActivity(daftar_anak);
            }
        });
    }

    private View.OnClickListener close_notifikasi = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            popup_notifikasi.dismiss();
        }
    };

    private void popnotif() {
        try {
            LayoutInflater layoutInflater = (LayoutInflater)kadepok_cherish_panti.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = layoutInflater.inflate(R.layout.kadepok_notifikasi, (ViewGroup)findViewById(R.id.kadepok_notifikasi));

            popup_notifikasi = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
            popup_notifikasi.showAtLocation(layout, Gravity.CENTER, 0, 0);

            close = (Button)layout.findViewById(R.id.close);
            close.setOnClickListener(close_notifikasi);

            Button notif = (Button)layout.findViewById(R.id.notif);
            notif.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent notif = new Intent(kadepok_cherish_panti.this, kadepok_donasi_upload.class);
                    startActivity(notif);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
