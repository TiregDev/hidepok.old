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
import android.widget.PopupWindow;

import com.hi_depok.hi_depok.Activity_Main.kadepok;
import com.hi_depok.hi_depok.R;

public class kadepok_donasi_verify extends AppCompatActivity {
    private Button verifikasi;
    private PopupWindow popupWindow;
    public Button close;

    private void initiatepopup() {
        try {


            LayoutInflater layoutInflater = (LayoutInflater)kadepok_donasi_verify.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = layoutInflater.inflate(R.layout.kadepok_donasi_popup, (ViewGroup)findViewById(R.id.rl_custom_layout));

            popupWindow = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
            popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);

            close = (Button)layout.findViewById(R.id.close);
            close.setOnClickListener(cancel_button_click_listener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View.OnClickListener cancel_button_click_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            popupWindow.dismiss();
            Intent verify = new Intent(kadepok_donasi_verify.this, kadepok.class);
            startActivity(verify);
        }
    };

/*    public void init() {
        verifikasi = (Button)findViewById(R.id.verifikasi);
        verifikasi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent verify = new Intent(kadepok_donasi_verify.this, kadepok_donasi_upload.class);
                startActivity(verify);
            }
        });
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kadepok_donasi_verify);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        verifikasi = (Button)findViewById(R.id.verifikasi);
        verifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initiatepopup();
            }
        });
    }
}
