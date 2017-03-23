package com.hi_depok.hi_depok.Kadepok_Volunteer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import com.hi_depok.hi_depok.Activity_Main.kadepok;
import com.hi_depok.hi_depok.R;

import static java.security.AccessController.getContext;

public class kadepok_volunteer_form extends AppCompatActivity {
    public Button btn_volunteer;
    private PopupWindow popupWindow;
    public Button close;

    public void verify_volunteer() {
        try {

            LayoutInflater layoutInflater = (LayoutInflater)kadepok_volunteer_form.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
            Intent verify = new Intent(kadepok_volunteer_form.this, kadepok.class);
            startActivity(verify);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kadepok_volunteer_form);

        btn_volunteer = (Button)findViewById(R.id.btn_volunteer);
        btn_volunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verify_volunteer();
            }
        });
    }
}
