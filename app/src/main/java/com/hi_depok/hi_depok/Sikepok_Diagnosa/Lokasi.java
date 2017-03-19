package com.hi_depok.hi_depok.Sikepok_Diagnosa;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.hi_depok.hi_depok.R;

public class Lokasi extends AppCompatActivity implements View.OnClickListener{
    ViewPager pager;
    LokasiPager adapter;
    View strip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lokasi);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pager = (ViewPager) findViewById(R.id.pager);
        strip = (findViewById(R.id.strip));

        adapter = new LokasiPager(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels){
                switch (position){
                    case 0:
                        strip.setTranslationX(positionOffsetPixels / 3);
                        break;
                    case 1:
                        strip.setTranslationX(strip.getWidth() + positionOffsetPixels / 3 );
                        break;
                    case 2:
                        strip.setTranslationX(strip.getWidth() * 2 + positionOffsetPixels / 3 );
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
    }

    public void onClick(View view){
        switch (view.getId()){
            case(R.id.textView3):
                pager.setCurrentItem(0);
                break;
            case(R.id.textView2):
                pager.setCurrentItem(1);
                break;
            case(R.id.textView1):
                pager.setCurrentItem(2);
                break;
            default:
                break;
        }
    }
    class LokasiPager extends FragmentPagerAdapter{
        Frag1 Fr1;
        Frag2 Fr2;
        Frag3 Fr3;

        public LokasiPager(FragmentManager fm){
            super(fm);
            Fr1 = Frag1.newInstance();
            Fr2 = Frag2.newInstance();
            Fr3 = Frag3.newInstance();
        }
        public Fragment getItem(int position){
            switch (position){
                case 0:
                    return Fr1;
                case 1:
                    return Fr2;
                case 2:
                    return Fr3;
                default:
                    return Fr1;
            }
        }
        public int getCount() {
            return 3;
        }
    }
}