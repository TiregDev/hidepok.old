package com.hi_depok.hi_depok.Sikepok_RS;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.preference.PreferenceManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.SessionManager;
import com.hi_depok.hi_depok.Sikepok_Panic.activity.MenuActivity;
import com.hi_depok.hi_depok.Sikepok_Panic.fragment.PanicButtonFragment;
import com.hi_depok.hi_depok.Sikepok_Panic.fragment.TempatSehatFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class menu_rs extends BaseActivity {

    public static final String EXTRA_ID = "position";
    public  static final String DEFAULT = "N/A";
    private DrawerLayout mDrawerLayout;
    private SearchView searchView;
    String apa_aja;
    String hadi;
//    SessionManager session;
    public String idRs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sikepokrs_menu_rs);
        super.onCreateDrawer();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //shared preferences test
        final SharedPreferences prefsa = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        idRs = prefsa.getString("id_rs","No data found");
//        Toast.makeText(this, idRs, Toast.LENGTH_SHORT).show();

        // Setting ViewPager for each Tabs
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        // Set Tabs inside Toolbar
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
      //  tabs.getTabAt(4).setIcon(R.drawable.call_btn);
//        tabs.getTabAt(1).setIcon(R.drawable.panic_bidan);
//        tabs.getTabAt(2).setIcon(R.drawable.panic_pijat);
//        tabs.getTabAt(3).setIcon(R.drawable.panic_sunat);
//        for (int i = 0; i < tabs.getTabCount(); i++) {
//            tabs.getTabAt(i).setIcon(R.drawable.ic_action_home4);
//        }
//        String a = getIntent().getStringExtra("EXTRA_ID");
//        Toast.makeText(this,a,Toast.LENGTH_SHORT).show();
    }

    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());

        adapter.addFragment(deskripsi_rs.newInstance(idRs), "Deskripsi");
        adapter.addFragment(jadwal_praktek.newInstance(idRs), "Jadwal");
        adapter.addFragment(dokter.newInstance(idRs), "Dokter");
        adapter.addFragment(fasilitas.newInstance(idRs), "Fasilitas");

        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    //lain2
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                menu_rs.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void toMaps(View v){
        Intent intent = new Intent(getBaseContext(), MapsActivity.class);
        startActivity(intent);
    }

    public void toCall(View v){
        String PhoneNo="085695454139";
        Intent dial = new Intent();
        dial.setAction("android.intent.action.DIAL");
        dial.setData(Uri.parse("tel:" + PhoneNo));
        startActivity(dial);
    }
}
