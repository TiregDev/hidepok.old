package com.hi_depok.hi_depok.Sikepok_Panic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import com.hi_depok.hi_depok.R;

public class admin_userinform extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_userinform);
        TextView tv = (TextView) findViewById(R.id.PesanIsi);
        tv.setMovementMethod(new ScrollingMovementMethod());
    }
}
