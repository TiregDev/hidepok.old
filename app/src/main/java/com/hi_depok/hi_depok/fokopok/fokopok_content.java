package com.hi_depok.hi_depok.fokopok;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hi_depok.hi_depok.Activity_Main.fokopok;
import com.hi_depok.hi_depok.Lapok.fragment.fragment2;
import com.hi_depok.hi_depok.Lapok.lapok_content;
import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.Ucok.ItemObject;
import com.hi_depok.hi_depok.Ucok.RecyclerViewHolders;
import com.hi_depok.hi_depok.Ucok.UcokDetailActivity;

import java.util.List;

public class fokopok_content extends AppCompatActivity implements View.OnClickListener {
    ViewPager pager;
    FokopokContent adapter;
    View strip;
    ImageView komunitas, home, profil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fokopok_content);
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