package com.hi_depok.hi_depok.Activity_Main;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.SessionManager;
import com.mikepenz.crossfadedrawerlayout.view.CrossfadeDrawerLayout;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.MiniDrawer;
import com.mikepenz.materialdrawer.interfaces.ICrossfader;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.util.DrawerUIUtils;
import com.mikepenz.materialize.util.UIUtils;

import java.util.HashMap;

/**
 * Created by User on 29/03/17.
 */

public class BaseActivity extends AppCompatActivity {
    protected Drawer result;
    private static final int TIME_DELAY = 2000;
    private static long back_pressed;
    SessionManager session;
    Activity activity = this;
    public Activity c;


//    public BaseActivity(Activity a) {
//        super(a);
//        // TODO Auto-generated constructor stub
//        this.c = a;
//    }

    protected void onCreateDrawer() {
        // Making notification bar transparent
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        session = new SessionManager(this);

        PrimaryDrawerItem home = new PrimaryDrawerItem()
                .withName("Beranda")
                .withTextColor(Color.WHITE)
                .withSelectedTextColor(Color.parseColor("#4f71de"))
                .withIconColor(Color.rgb(158, 158, 158))
                .withSelectedColor(Color.parseColor("#202020"))
                .withIcon(R.drawable.ic_action_home4)
                .withSelectable(false)
                .withIdentifier(0);
        PrimaryDrawerItem profile = new PrimaryDrawerItem()
                .withName("Profil")
                .withSelectable(false)
                .withTextColor(Color.WHITE)
                .withSelectedTextColor(Color.parseColor("#4f71de"))
                .withIconColor(Color.rgb(158, 158, 158))
                .withSelectedColor(Color.parseColor("#202020"))
                .withIcon(R.drawable.ic_action_person4)
                .withIdentifier(1);
        PrimaryDrawerItem logout = new PrimaryDrawerItem()
                .withName("Keluar")
                .withTextColor(Color.WHITE)
                .withSelectedTextColor(Color.parseColor("#4f71de"))
                .withIconColor(Color.rgb(158, 158, 158))
                .withSelectedColor(Color.parseColor("#202020"))
                .withSelectable(false)
                .withIcon(R.drawable.ic_action_exit_to_app)
                .withIdentifier(2);
        SecondaryDrawerItem sikepok = new SecondaryDrawerItem()
                .withName("Sikepok")
                .withSelectable(false)
                .withTextColor(Color.WHITE)
                .withSelectedTextColor(Color.parseColor("#4f71de"))
                .withIconColor(Color.rgb(158, 158, 158))
                .withSelectedColor(Color.parseColor("#202020"))
                .withIcon(R.drawable.sikepok_bw)
                .withIdentifier(3);
        SecondaryDrawerItem kadepok = new SecondaryDrawerItem()
                .withName("KaDepok")
                .withSelectable(false)
                .withTextColor(Color.WHITE)
                .withSelectedTextColor(Color.parseColor("#4f71de"))
                .withIconColor(Color.rgb(158, 158, 158))
                .withSelectedColor(Color.parseColor("#202020"))
                .withIcon(R.drawable.kadepok_bw)
                .withIdentifier(4);
        SecondaryDrawerItem lapok = new SecondaryDrawerItem()
                .withName("Lapok")
                .withSelectable(false)
                .withTextColor(Color.WHITE)
                .withSelectedTextColor(Color.parseColor("#4f71de"))
                .withIconColor(Color.rgb(158, 158, 158))
                .withSelectedColor(Color.parseColor("#202020"))
                .withIcon(R.drawable.lapok_bw)
                .withIdentifier(5);
        SecondaryDrawerItem kapok = new SecondaryDrawerItem()
                .withName("Kapok")
                .withSelectable(false)
                .withTextColor(Color.WHITE)
                .withSelectedTextColor(Color.parseColor("#4f71de"))
                .withIconColor(Color.rgb(158, 158, 158))
                .withSelectedColor(Color.parseColor("#202020"))
                .withIcon(R.drawable.kapok_bw)
                .withIdentifier(6);
        SecondaryDrawerItem ucok = new SecondaryDrawerItem()
                .withName("Ucok")
                .withSelectable(false)
                .withTextColor(Color.WHITE)
                .withSelectedTextColor(Color.parseColor("#4f71de"))
                .withIconColor(Color.rgb(158, 158, 158))
                .withSelectedColor(Color.parseColor("#202020"))
                .withIcon(R.drawable.ucok_bw)
                .withIdentifier(7);
        SecondaryDrawerItem fokopok = new SecondaryDrawerItem()
                .withName("Fokopok")
                .withSelectable(false)
                .withTextColor(Color.WHITE)
                .withSelectedTextColor(Color.parseColor("#4f71de"))
                .withIconColor(Color.rgb(158, 158, 158))
                .withSelectedColor(Color.parseColor("#202020"))
                .withIcon(R.drawable.fokopok_bw)
                .withIdentifier(8);
        PrimaryDrawerItem about = new PrimaryDrawerItem()
                .withName("Tentang")
                .withSelectable(false)
                .withTextColor(Color.WHITE)
                .withSelectedTextColor(Color.parseColor("#4f71de"))
                .withIconColor(Color.rgb(158, 158, 158))
                .withSelectedColor(Color.parseColor("#202020"))
                .withIcon(R.drawable.ic_action_info4)
                .withIdentifier(9);


        // Create the AccountHeader

        HashMap<String, String> user = session.getUserDetails();
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.bgnav2)
                .addProfiles(
                        new ProfileDrawerItem()
                                .withName(user.get(SessionManager.KEY_NAME))
                                .withEmail(user.get(SessionManager.KEY_EMAIL))
                                .withIcon(getResources().getDrawable(R.drawable.profile))
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
                .withSliderBackgroundColor(Color.rgb(48, 48, 48))
                .withRootView(R.id.drawer_layout)
                .addDrawerItems(
                        home,
                        profile,
                        logout,
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
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(getApplicationContext(), profile.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        session = new SessionManager(getApplicationContext());
                        session.logoutUser();
                        Intent intent2 = new Intent(getApplicationContext(), login.class);
                        finish();
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(getApplicationContext(), sikepok.class);
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent(getApplicationContext(), kadepok.class);
                        startActivity(intent4);
                        break;
                    case 5:
                        Intent intent5 = new Intent(getApplicationContext(), lapok.class);
                        startActivity(intent5);
                        break;
                    case 6:
                        Intent intent6 = new Intent(getApplicationContext(), kapok.class);
                        startActivity(intent6);
                        break;
                    case 7:
                        Intent intent7 = new Intent(getApplicationContext(), ucok.class);
                        startActivity(intent7);
                        break;
                    case 8:
                        Intent intent8 = new Intent(getApplicationContext(), fokopok.class);
                        startActivity(intent8);
                        break;
                    case 9:
                        Intent intent9 = new Intent(getApplicationContext(), about.class);
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
    }

    @Override
    public void onBackPressed() {
            if (result.isDrawerOpen()) {
                result.closeDrawer();
            } else {
                super.onBackPressed();
            }
    }
}