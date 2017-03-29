package com.hi_depok.hi_depok.Lapok;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.Lapok.fragment.DescriptionForm;
import com.hi_depok.hi_depok.R;

import java.io.File;

public class lapok_content extends BaseActivity implements View.OnClickListener {

    ViewPager pager;
    Content adapter;
    View strip;
    ImageView report, forum;
    TextView report_text, forum_text;
    File imageFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lapok_content);
<<<<<<< HEAD
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
=======
        super.onCreateDrawer();
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
>>>>>>> origin/master
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pager = (ViewPager) findViewById(R.id.pager);
        report = (ImageView) findViewById(R.id.report);
        forum = (ImageView) findViewById(R.id.forum);
        strip = findViewById(R.id.strip);
        report_text = (TextView) findViewById(R.id.report_text);
        forum_text = (TextView) findViewById(R.id.forum_text);

        adapter = new Content(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.setCurrentItem(1);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position) {
                    case 0:
                        strip.setTranslationX(positionOffsetPixels / 2);
                        break;
                    case 1:
                        strip.setTranslationX(strip.getWidth() + positionOffsetPixels / 2);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        kekamera();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        report.setOnClickListener(this);
        forum.setOnClickListener(this);
        report_text.setOnClickListener(this);
        forum_text.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.report:
                pager.setCurrentItem(0);
                break;
            case R.id.forum:
                pager.setCurrentItem(1);
                break;
            case R.id.report_text:
                pager.setCurrentItem(0);
                break;
            case R.id.forum_text:
                pager.setCurrentItem(1);
                break;
            default:
                break;
        }
    }

    class Content extends FragmentPagerAdapter {
        com.hi_depok.hi_depok.Lapok.fragment.fragment1 fragment1;
        com.hi_depok.hi_depok.Lapok.fragment.fragment2 fragment2;

        public Content(FragmentManager fm) {
            super(fm);
            fragment1 = com.hi_depok.hi_depok.Lapok.fragment.fragment1.newInstance();
            fragment2 = com.hi_depok.hi_depok.Lapok.fragment.fragment2.newInstance();
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return fragment1;
                case 1:
                    return fragment2;
                default:
                    return fragment2;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    public void kekamera (){
        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        imageFile = new File(Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "test.jpg");
        Uri tempuri = Uri.fromFile(imageFile);
        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, tempuri);
        intentCamera.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);

        startActivityForResult(intentCamera, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    if (imageFile.exists()) {
                        Toast.makeText(getApplicationContext(), "The file was save at " + imageFile.getAbsolutePath(),
                                Toast.LENGTH_LONG).show();
                        Intent intentDeskripsi = new Intent(getApplicationContext(), DescriptionForm.class);
                        startActivity(intentDeskripsi);
                    } else {
                        Toast.makeText(getApplicationContext(), "There was an error ", Toast.LENGTH_LONG).show();
                    }
                    break;
                case Activity.RESULT_CANCELED:
                    break;
                default:
                    break;
            }
        }
    }
}
