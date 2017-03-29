package com.hi_depok.hi_depok.fokopok;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.Activity_Main.fokopok;
import com.hi_depok.hi_depok.R;

public class fokopok_content extends BaseActivity implements View.OnClickListener {
    ViewPager pager;
    FokopokContent adapter;
    View strip;
    ImageView komunitas, home, profil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fokopok_content);
<<<<<<< HEAD
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
=======
        super.onCreateDrawer();
        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
>>>>>>> origin/master
        }
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pager = (ViewPager) findViewById(R.id.pager);
        komunitas = (ImageView) findViewById(R.id.komunitas);
        home = (ImageView) findViewById(R.id.home);
        profil = (ImageView) findViewById(R.id.profil);
        strip = findViewById(R.id.strip);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        adapter = new FokopokContent(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position) {
                    case 0:
                        strip.setTranslationX(positionOffsetPixels / 3);
                        break;
                    case 1:
                        strip.setTranslationX(strip.getWidth() + positionOffsetPixels / 3);
                        break;
                    case 2:
                        strip.setTranslationX(strip.getWidth() * 2 + positionOffsetPixels / 3);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        komunitas.setOnClickListener(this);
        home.setOnClickListener(this);
        profil.setOnClickListener(this);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_new_msg:
                // todo: goto back activity from here

                startActivity(new Intent(getBaseContext(), message.class));
                return true;
            case android.R.id.home:
                // todo: goto back activity from here

                fokopok_content.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fokopok, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.komunitas:
                pager.setCurrentItem(0);
                break;
            case R.id.home:
                pager.setCurrentItem(1);
                break;
            case R.id.profil:
                pager.setCurrentItem(2);
                break;
            default:
                break;
        }
    }

    class FokopokContent extends FragmentPagerAdapter {
        com.hi_depok.hi_depok.fokopok.fragment_content.fragment1 fragment1;
        com.hi_depok.hi_depok.fokopok.fragment_content.fragment2 fragment2;
        com.hi_depok.hi_depok.fokopok.fragment_content.fragment3 fragment3;

        public FokopokContent(FragmentManager fm) {
            super(fm);
            fragment1 = com.hi_depok.hi_depok.fokopok.fragment_content.fragment1.newInstance();
            fragment2 = com.hi_depok.hi_depok.fokopok.fragment_content.fragment2.newInstance();
            fragment3 = com.hi_depok.hi_depok.fokopok.fragment_content.fragment3.newInstance();
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return fragment1;
                case 1:
                    return fragment2;
                case 2:
                    return fragment3;
                default:
                    return fragment1;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    public void link_message(View v){
        Intent intent = new Intent(fokopok_content.this, message.class);
        startActivity(intent);
    }

    public void fokopok_activity(View v){
        Intent intent = new Intent(fokopok_content.this, fokopok.class);
        startActivity(intent);
    }

}