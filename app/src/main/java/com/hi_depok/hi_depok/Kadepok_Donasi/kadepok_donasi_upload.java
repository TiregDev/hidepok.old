package com.hi_depok.hi_depok.Kadepok_Donasi;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.hi_depok.hi_depok.R;

public class kadepok_donasi_upload extends AppCompatActivity {
    private Button btn_popup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kadepok_donasi_upload);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btn_popup = (Button)findViewById(R.id.btn_popup);
        btn_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initiatepopup();
            }
        });

    }

    private PopupWindow popupWindow;

    private void initiatepopup() {
        try {
            LayoutInflater inflater = (LayoutInflater) kadepok_donasi_upload.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.kadepok_upload_menu, (ViewGroup) findViewById(R.id.popup_element));

            popupWindow = new PopupWindow(layout, 600, 300, true);
            popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);

            layout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    popupWindow.dismiss();
                    return true;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
