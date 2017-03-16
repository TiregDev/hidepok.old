package com.hi_depok.hi_depok.Ucok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hi_depok.hi_depok.R;

public class UcokDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ucok_detail);
    }

    public void ucok_content_siukm(View v){
        Intent intent = new Intent(UcokDetailActivity.this, ucok_content.class);
        startActivity(intent);
    }
}
