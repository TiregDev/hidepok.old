package com.hi_depok.hi_depok.Activity_Main;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.SessionManager;
import com.hi_depok.hi_depok.notif.event;
import com.mikepenz.materialdrawer.Drawer;

public class MainActivity extends BaseActivity {
    public Drawer drawer;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, android.R.color.transparent));
        }
        setContentView(R.layout.activity_main);
        super.onCreateDrawer();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        session = new SessionManager(this);
        if(session.checkLogin()){
            finish();
        }

        TextView tv = (TextView) findViewById(R.id.textView);
        Typeface tf = Typeface.createFromAsset(getAssets(), "font/Blogger_Sans-Bold.otf");
        tv.setTypeface(tf);

        TextView tv2 = (TextView) findViewById(R.id.textView2);
        Typeface tf2 = Typeface.createFromAsset(getAssets(), "font/Blogger_Sans-Italic.otf");
        tv2.setTypeface(tf2);

        TextView tv3 = (TextView) findViewById(R.id.textView3);
        Typeface tf3 = Typeface.createFromAsset(getAssets(), "font/Blogger_Sans.otf");
        tv3.setTypeface(tf3);

        Button bt = (Button) findViewById(R.id.sikepok);
        bt.setTypeface(tf);

        Button bt2 = (Button) findViewById(R.id.kadepok);
        bt2.setTypeface(tf);

        Button bt3 = (Button) findViewById(R.id.lapok);
        bt3.setTypeface(tf);

        Button bt4 = (Button) findViewById(R.id.kapok);
        bt4.setTypeface(tf);

        Button bt5 = (Button) findViewById(R.id.ucok);
        bt5.setTypeface(tf);

        Button bt6 = (Button) findViewById(R.id.fokopok);
        bt6.setTypeface(tf);

        Button bt7 = (Button) findViewById(R.id.wv);
        bt7.setTypeface(tf);
    }

    public void linkSikepok(View view) {
        Intent intent = new Intent(MainActivity.this, sikepok.class);
        startActivity(intent);
    }

    public void linkkapok(View view) {
        Intent intent = new Intent(MainActivity.this, kapok.class);
        startActivity(intent);
    }

    public void linkkadepok(View view) {
        Intent intent = new Intent(MainActivity.this, kadepok.class);
        startActivity(intent);
    }

    public void linklapok(View view) {
        Intent intent = new Intent(MainActivity.this, lapok.class);
        startActivity(intent);
    }

    public void linkucok(View view) {
        Intent intent = new Intent(MainActivity.this, ucok.class);
        startActivity(intent);
    }

    public void linkfokopok(View view) {
        Intent intent = new Intent(MainActivity.this, fokopok.class);
        startActivity(intent);
    }

    public void event(View view) {
        Intent intent = new Intent(MainActivity.this, event.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (result.isDrawerOpen()) {
            super.onBackPressed();
        }
        else {
            LayoutInflater factory = LayoutInflater.from(this);
            final View exitDialogView = factory.inflate(R.layout.activity_dialog_exit, null);
            final AlertDialog exitDialog = new AlertDialog.Builder(this).create();
            exitDialog.setView(exitDialogView);
            exitDialogView.findViewById(R.id.btn_yes).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.finish();
                }
            });
            exitDialogView.findViewById(R.id.btn_no).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    exitDialog.dismiss();
                }
            });
            exitDialog.show();
        }
    }

    public void linktoyoutube(View v){
        String id = "Kns24Fqa3gc";
        try {

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://" + id));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        } catch (ActivityNotFoundException e) {

            // youtube is not installed.Will be opened in other available apps

            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com/watch?v=" + id));
            startActivity(i);
        }
    }

}