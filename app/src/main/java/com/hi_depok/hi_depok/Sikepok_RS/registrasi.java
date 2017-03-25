package com.hi_depok.hi_depok.Sikepok_RS;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.hi_depok.hi_depok.R;

public class registrasi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sikepokrs_registrasi);
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
