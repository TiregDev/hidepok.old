package com.hi_depok.hi_depok.Sikepok_RS;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.R;

public class registrasi extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sikepokrs_registrasi);
        super.onCreateDrawer();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, android.R.color.transparent));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void ke_pasien_baru(View view){
        Intent next = new Intent(registrasi.this, regis_pasien_baru.class);
        startActivity(next);
    }

    public void ke_pasien_lama(View view){
        Intent next = new Intent(registrasi.this, regis_pasien_lama.class);
        startActivity(next);
    }

    public void ke_regis_berobat(View view){
        Intent next = new Intent(registrasi.this, regis_berobat.class);
        startActivity(next);
    }

    public void ke_ketentuan(View view){
        Intent next = new Intent(registrasi.this, ketentuan.class);
        startActivity(next);
    }
}
