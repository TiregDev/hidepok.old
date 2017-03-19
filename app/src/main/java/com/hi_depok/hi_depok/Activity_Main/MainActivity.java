package com.hi_depok.hi_depok.Activity_Main;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hi_depok.hi_depok.R;
import com.mikepenz.crossfadedrawerlayout.view.CrossfadeDrawerLayout;
import com.mikepenz.materialdrawer.*;
import com.mikepenz.materialdrawer.interfaces.ICrossfader;
import com.mikepenz.materialdrawer.model.*;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.util.DrawerUIUtils;
import com.mikepenz.materialize.util.UIUtils;

public class MainActivity extends AppCompatActivity {

    private static final int TIME_DELAY = 2000;
    private static long back_pressed;
    private Drawer result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        PrimaryDrawerItem home =   new PrimaryDrawerItem()
                .withName("Home")
                .withTextColor(Color.WHITE)
                .withSelectedTextColor(Color.parseColor("#4f71de"))
                .withIconColor(Color.rgb(158,158,158))
                .withSelectedColor(Color.parseColor("#202020"))
                .withIcon(R.drawable.ic_action_home4)
                .withSelectable(false)
                .withIdentifier(0);
        PrimaryDrawerItem profile =    new PrimaryDrawerItem()
                .withName("Profile")
                .withSelectable(false)
                .withTextColor(Color.WHITE)
                .withSelectedTextColor(Color.parseColor("#4f71de"))
                .withIconColor(Color.rgb(158,158,158))
                .withSelectedColor(Color.parseColor("#202020"))
                .withIcon(R.drawable.ic_action_person4)
                .withIdentifier(1);
        PrimaryDrawerItem login = new PrimaryDrawerItem()
                .withName("Login")
                .withTextColor(Color.WHITE)
                .withSelectedTextColor(Color.parseColor("#4f71de"))
                .withIconColor(Color.rgb(158,158,158))
                .withSelectedColor(Color.parseColor("#202020"))
                .withSelectable(false)
                .withIcon(R.drawable.ic_action_input4)
                .withIdentifier(2);
        SecondaryDrawerItem sikepok =    new SecondaryDrawerItem()
                .withName("Sikepok")
                .withSelectable(false)
                .withTextColor(Color.WHITE)
                .withSelectedTextColor(Color.parseColor("#4f71de"))
                .withIconColor(Color.rgb(158,158,158))
                .withSelectedColor(Color.parseColor("#202020"))
                .withIcon(R.drawable.sikepok_bw)
                .withIdentifier(3);
        SecondaryDrawerItem kadepok =    new SecondaryDrawerItem()
                .withName("KaDepok")
                .withSelectable(false)
                .withTextColor(Color.WHITE)
                .withSelectedTextColor(Color.parseColor("#4f71de"))
                .withIconColor(Color.rgb(158,158,158))
                .withSelectedColor(Color.parseColor("#202020"))
                .withIcon(R.drawable.kadepok_bw)
                .withIdentifier(4);
        SecondaryDrawerItem lapok =    new SecondaryDrawerItem()
                .withName("Lapok")
                .withSelectable(false)
                .withTextColor(Color.WHITE)
                .withSelectedTextColor(Color.parseColor("#4f71de"))
                .withIconColor(Color.rgb(158,158,158))
                .withSelectedColor(Color.parseColor("#202020"))
                .withIcon(R.drawable.lapok_bw)
                .withIdentifier(5);
        SecondaryDrawerItem kapok =    new SecondaryDrawerItem()
                .withName("Kapok")
                .withSelectable(false)
                .withTextColor(Color.WHITE)
                .withSelectedTextColor(Color.parseColor("#4f71de"))
                .withIconColor(Color.rgb(158,158,158))
                .withSelectedColor(Color.parseColor("#202020"))
                .withIcon(R.drawable.kapok_bw)
                .withIdentifier(6);
        SecondaryDrawerItem ucok =    new SecondaryDrawerItem()
                .withName("Ucok")
                .withSelectable(false)
                .withTextColor(Color.WHITE)
                .withSelectedTextColor(Color.parseColor("#4f71de"))
                .withIconColor(Color.rgb(158,158,158))
                .withSelectedColor(Color.parseColor("#202020"))
                .withIcon(R.drawable.ucok_bw)
                .withIdentifier(7);
        SecondaryDrawerItem fokopok =    new SecondaryDrawerItem()
                .withName("Fokopok")
                .withSelectable(false)
                .withTextColor(Color.WHITE)
                .withSelectedTextColor(Color.parseColor("#4f71de"))
                .withIconColor(Color.rgb(158,158,158))
                .withSelectedColor(Color.parseColor("#202020"))
                .withIcon(R.drawable.fokopok_bw)
                .withIdentifier(8);
        PrimaryDrawerItem about =    new PrimaryDrawerItem()
                .withName("About")
                .withSelectable(false)
                .withTextColor(Color.WHITE)
                .withSelectedTextColor(Color.parseColor("#4f71de"))
                .withIconColor(Color.rgb(158,158,158))
                .withSelectedColor(Color.parseColor("#202020"))
                .withIcon(R.drawable.ic_action_info4)
                .withIdentifier(9);


        // Create the AccountHeader
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.bgnav2)
                .addProfiles(
                        new ProfileDrawerItem().withName("Fajar Zakaria").withEmail("JAR@gmail.com").withIcon(getResources().getDrawable(R.drawable.profile))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

        result = new DrawerBuilder()
                .withActivity(this)
                .withHasStableIds(true)
                .withAccountHeader(headerResult)
                .withDrawerLayout(R.layout.crossfade_layout)
                .withDrawerWidthDp(72)
                .withFullscreen(true)
                .withGenerateMiniDrawer(true)
                .withSliderBackgroundColor(Color.rgb(48,48,48))
                .withRootView(R.id.drawer_layout)
                .addDrawerItems(
                        home,
                        profile,
                        login,
                        new SectionDrawerItem().withName(R.string.modul).withTextColor(Color.parseColor("#b7b7b7")),
                        sikepok,
                        kadepok,
                        lapok,
                        kapok,
                        ucok,
                        fokopok,
                        new DividerDrawerItem(),
                        about)
                .withShowDrawerOnFirstLaunch(true)
                .build();
        result.setOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                switch ((int) drawerItem.getIdentifier()) {
                    case 0:
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(MainActivity.this, profile.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(MainActivity.this, login.class);
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(MainActivity.this, sikepok.class);
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent(MainActivity.this, kadepok.class);
                        startActivity(intent4);
                        break;
                    case 5:
                        Intent intent5 = new Intent(MainActivity.this, lapok.class);
                        startActivity(intent5);
                        break;
                    case 6:
                        Intent intent6 = new Intent(MainActivity.this, kapok.class);
                        startActivity(intent6);
                        break;
                    case 7:
                        Intent intent7 = new Intent(MainActivity.this, ucok.class);
                        startActivity(intent7);
                        break;
                    case 8:
                        Intent intent8 = new Intent(MainActivity.this, fokopok.class);
                        startActivity(intent8);
                        break;
                    case 9:
                        Intent intent9 = new Intent(MainActivity.this, about.class);
                        startActivity(intent9);
                        break;
                }
                return true;
            }
        });

        final CrossfadeDrawerLayout crossfadeDrawerLayout = (CrossfadeDrawerLayout) result.getDrawerLayout();
        crossfadeDrawerLayout.setMaxWidthPx(DrawerUIUtils.getOptimalDrawerWidth(this));
        final MiniDrawer miniResult = result.getMiniDrawer();
        View view = miniResult.build(this);
        view.setBackgroundColor(UIUtils.getThemeColorFromAttrOrRes(this, com.mikepenz.materialdrawer.R.attr.material_drawer_background, com.mikepenz.materialdrawer.R.color.background_material_light));
        crossfadeDrawerLayout.getSmallView().addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        miniResult.withCrossFader(new ICrossfader() {
            @Override
            public void crossfade() {
                boolean isFaded = isCrossfaded();
                crossfadeDrawerLayout.crossfade(400);

                //only close the drawer if we were already faded and want to close it now
                if (isFaded) {
                    result.getDrawerLayout().closeDrawer(GravityCompat.START);
                }
            }

            @Override
            public boolean isCrossfaded() {
                return crossfadeDrawerLayout.isCrossfaded();
            }
        });


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

        Button bt6 = (Button)findViewById(R.id.fokopok);
        bt6.setTypeface(tf);

        Button bt7 = (Button)findViewById(R.id.wv);
        bt7.setTypeface(tf);
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

    public void linklapok (View view){
        Intent intent = new Intent(MainActivity.this, lapok.class);
        startActivity(intent);
    }

    public void linkucok (View view){
        Intent intent = new Intent(MainActivity.this, ucok.class);
        startActivity(intent);
    }

    public void linkfokopok (View view){
        Intent intent = new Intent(MainActivity.this, fokopok.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {

        if (result.isDrawerOpen()) {
            result.closeDrawer();
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



}