package com.hi_depok.hi_depok.Fokopok_Admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.hi_depok.hi_depok.R;

public class FokopokLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fokopok_login);
    }
    public void nextTime(View v){
        Intent intent = new Intent(FokopokLogin.this, FokopokHome.class);
        startActivity(intent);
    }
}
