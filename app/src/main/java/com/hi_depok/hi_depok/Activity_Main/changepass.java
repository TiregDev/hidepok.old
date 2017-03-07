package com.hi_depok.hi_depok.Activity_Main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hi_depok.hi_depok.R;

public class changepass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_pass);
    }


    public void backsetprofile(View view){
        Intent intent = new Intent(changepass.this, setprofile.class);
        startActivity(intent);
    }
}
