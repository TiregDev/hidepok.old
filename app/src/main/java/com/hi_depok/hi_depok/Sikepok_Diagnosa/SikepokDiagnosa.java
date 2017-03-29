package com.hi_depok.hi_depok.Sikepok_Diagnosa;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.hi_depok.hi_depok.R;

public class SikepokDiagnosa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sikepokdiagnosa_sikepokdiagnosa);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
