package com.hi_depok.hi_depok.Sikepok_Panic.activity;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.Sikepok_Panic.fragment.PanicButtonFragment;
import com.hi_depok.hi_depok.Sikepok_Panic.fragment.TempatSehatFragment;


/**
 * Provides UI for the main screen.
 */
public class MenuActivity extends BaseActivity {

    private DrawerLayout mDrawerLayout;
    private SearchView searchView;

    String urlSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sikepokpanic_menu);
        super.onCreateDrawer();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Adding Toolbar to Main screen
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Setting ViewPager for each Tabs
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        // Set Tabs inside Toolbar
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        tabs.getTabAt(0).setIcon(R.drawable.call_btn);
    }

    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());;

        adapter.addFragment(new PanicButtonFragment(), "");
        adapter.addFragment(TempatSehatFragment.newInstance("Apotek", getIntent().getStringExtra("LatLong")), "Apotek");
        adapter.addFragment(TempatSehatFragment.newInstance("Klinik", getIntent().getStringExtra("LatLong")), "Klinik");
        adapter.addFragment(TempatSehatFragment.newInstance("Puskesmas", getIntent().getStringExtra("LatLong")), "Puskesmas");
        adapter.addFragment(TempatSehatFragment.newInstance("Bidan", getIntent().getStringExtra("LatLong")), "Bidan");
        adapter.addFragment(TempatSehatFragment.newInstance("Tempat%20Pijat", getIntent().getStringExtra("LatLong")), "Tempat Pijat");
        adapter.addFragment(TempatSehatFragment.newInstance("Tempat%20Khitan", getIntent().getStringExtra("LatLong")), "Tempat Khitan");

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


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                MenuActivity.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

