package com.hi_depok.hi_depok.Sikepok_RS;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hi_depok.hi_depok.R;

public class klinik extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klinik);
    }

    public void ke_deskripsi_klinik (View view){
        Intent next = new Intent(klinik.this, deskripsi_klinik.class);
        startActivity(next);
    }
}
