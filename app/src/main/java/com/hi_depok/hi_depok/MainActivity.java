package com.hi_depok.hi_depok;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        TextView tv = (TextView)findViewById(R.id.textView);
        Typeface tf = Typeface.createFromAsset(getAssets(), "font/Blogger_Sans-Bold.otf");
        tv.setTypeface(tf);

        TextView tv2 = (TextView)findViewById(R.id.textView2);
        Typeface tf2 = Typeface.createFromAsset(getAssets(), "font/Blogger_Sans-Italic.otf");
        tv2.setTypeface(tf2);

        Button bt = (Button)findViewById(R.id.sikepok);
        bt.setTypeface(tf);

        Button bt2 = (Button)findViewById(R.id.kadepok);
        bt2.setTypeface(tf);

        Button bt3 = (Button)findViewById(R.id.lapok);
        bt3.setTypeface(tf);

        Button bt4 = (Button)findViewById(R.id.kapok);
        bt4.setTypeface(tf);

        Button bt5 = (Button)findViewById(R.id.ucok);
        bt5.setTypeface(tf);

        Button bt6 = (Button)findViewById(R.id.about);
        bt6.setTypeface(tf);

        Button bt7 = (Button)findViewById(R.id.wv);
        bt7.setTypeface(tf);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void linkSikepok(View view){
        Intent intent = new Intent(MainActivity.this, sikepok.class);
        startActivity(intent);
    }

    public void linkkapok (View view){
        Intent intent = new Intent(MainActivity.this, kapok.class);
        startActivity(intent);
    }

    public void linkkadepok (View view){
        Intent intent = new Intent(MainActivity.this, kadepok.class);
        startActivity(intent);
    }

   // private boolean backPressedToExitOnce;

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
            super.onBackPressed();

        }
        else if (back_pressed + TIME_DELAY <= System.currentTimeMillis()){
            Toast.makeText(getBaseContext(),"Prees back again to exit app",
                    Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            // Handle the camera action
        } else if (id == R.id.profile) {

        } else if (id == R.id.login) {

        } else if (id == R.id.sikepok) {

        } else if (id == R.id.kadepok) {

        } else if (id == R.id.lapok) {

        } else if (id == R.id.kapok){

        } else if (id == R.id.ucok){

        } else if (id == R.id.about){

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
