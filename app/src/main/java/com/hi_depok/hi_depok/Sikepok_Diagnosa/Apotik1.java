package com.hi_depok.hi_depok.Sikepok_Diagnosa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.hi_depok.hi_depok.R;

public class Apotik1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apotik1);
    }
    public void back (View view){
        Intent contoh = new Intent(Apotik1.this, Lokasi.class);
        startActivity(contoh);
    }
}
