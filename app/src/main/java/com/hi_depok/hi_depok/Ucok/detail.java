package com.hi_depok.hi_depok.Ucok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hi_depok.hi_depok.R;

public class detail extends AppCompatActivity {

    public static final String EXTRA_POSITION = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ucok_detail_activity);
    }
}
