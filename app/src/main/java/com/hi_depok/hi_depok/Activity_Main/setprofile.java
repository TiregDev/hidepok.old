package com.hi_depok.hi_depok.Activity_Main;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hi_depok.hi_depok.R;

public class setprofile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_profile);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    public void linkpass(View view){
        Intent intent = new Intent(setprofile.this, changepass.class);
        startActivity(intent);
    }

    public void back_profile(View view) {
        Intent intent = new Intent(setprofile.this, profile.class);
        startActivity(intent);
    }
}
