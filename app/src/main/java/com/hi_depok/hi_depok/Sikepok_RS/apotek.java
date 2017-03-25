package com.hi_depok.hi_depok.Sikepok_RS;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.hi_depok.hi_depok.R;

public class apotek extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sikepokrs_apotek);
    }

    public void ke_deskripsi_apotik (View view){
        Intent next = new Intent(apotek.this, deskripsiApotek.class);
        startActivity(next);
    }
}
