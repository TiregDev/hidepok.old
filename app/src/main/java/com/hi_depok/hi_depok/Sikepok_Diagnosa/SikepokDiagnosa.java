package com.hi_depok.hi_depok.Sikepok_Diagnosa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.hi_depok.hi_depok.R;

public class SikepokDiagnosa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sikepokdiagnosa);
    }

    public void diagnosa (View view){
        Intent contoh = new Intent(SikepokDiagnosa.this, Diagnosa.class);
        startActivity(contoh);
    }

    public void ensiklopedia (View view){
        Intent contoh = new Intent(SikepokDiagnosa.this, Ensiklopedia.class);
        startActivity(contoh);
    }
    public void lokasi (View view){
        Intent contoh = new Intent(SikepokDiagnosa.this, Lokasi.class);
        startActivity(contoh);
    }
}
