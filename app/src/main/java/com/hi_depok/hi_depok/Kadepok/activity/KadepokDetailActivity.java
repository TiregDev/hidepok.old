package com.hi_depok.hi_depok.Kadepok.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.Kadepok.fragment.*;
import com.hi_depok.hi_depok.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.R.attr.label;

public class KadepokDetailActivity extends BaseActivity implements View.OnClickListener {
    ViewPager pager;
    Content adapter;
    View strip;
    TextView deskripsi, donasi, cherish;
    public Button close;

    String JSON_URL;
    CircleImageView fotoPanti;
    TextView namaPanti;
    ImageView telpPanti, kordinatPanti;
    JsonArrayRequest jsonArrayRequest ;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kadepok_detail_panti);
        super.onCreateDrawer();
        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }

        //---------------- Image Single Popup --------------------------------------------------
        final CircleImageView imageView = (CircleImageView) findViewById(R.id.list_avatar);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog settingsDialog = new Dialog(KadepokDetailActivity.this);

                LayoutInflater inflater = getLayoutInflater();
                View newView = inflater.inflate(R.layout.activity_image, null);

                settingsDialog.setContentView(newView);
                settingsDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                settingsDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.alpha(0)));

                ImageView iv = (ImageView) newView.findViewById(R.id.profile_img_popup);
                Bitmap bm = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                iv.setImageBitmap(bm);
                settingsDialog.show();
            }
        });

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        JSON_URL = "http://hidepok.id/android/kadepok/kadepok_json.php?id=" + getIntent().getExtras().getString("getId");
        namaPanti = (TextView)findViewById(R.id.list_title);
        fotoPanti = (CircleImageView)findViewById(R.id.list_avatar);
        telpPanti = (ImageView) findViewById(R.id.call);
        kordinatPanti = (ImageView) findViewById(R.id.maps);
        JSON_VALIDATE_URL();

        pager = (ViewPager) findViewById(R.id.pager);
        deskripsi = (TextView) findViewById(R.id.deskripsi_panti);
        donasi = (TextView) findViewById(R.id.donasi_panti);
        cherish = (TextView) findViewById(R.id.cherish_us);
        strip = findViewById(R.id.strip);
        adapter = new Content(getSupportFragmentManager());
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

        deskripsi.setOnClickListener(this);
        donasi.setOnClickListener(this);
        cherish.setOnClickListener(this);

    }

    public void JSON_VALIDATE_URL(){
        jsonArrayRequest = new JsonArrayRequest(JSON_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSON_PARSING_DATA(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    public void JSON_PARSING_DATA(JSONArray array){
        for(int i = 0; i<array.length(); i++) {
            JSONObject json = null;
            try {
                json = array.getJSONObject(i);
                namaPanti.setText(json.getString("nama_panti"));
                final String label = json.getString("nama_panti");
                final String PhoneNo = json.getString("telpon_panti");
                final String kordinat = json.getString("koordinat_panti");
                String urlPhoto = "http://hidepok.id/assets/images/photos/kadepok/" + json.getString("foto_profile_panti");
                Picasso.with(this).load(urlPhoto).resize(300, 300).placeholder(R.drawable.image_placeholder).into(fotoPanti);
                telpPanti.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent dial = new Intent();
                        dial.setAction("android.intent.action.DIAL");
                        dial.setData(Uri.parse("tel:" + PhoneNo));
                        startActivity(dial);
                    }
                });
                kordinatPanti.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String urlAddress = "http://maps.google.com/maps?q=" + kordinat + "(" + label + ")&iwloc=A&hl=es";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlAddress));
                        startActivity(intent);
                    }
                });


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_kadepok, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
//                todo: goto back activity from here
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Yuk memberi dampak perubahan positif dengan membantu panti asuhan " + label +
                        "untuk kota Depok ;) ";
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
                return true;

            case android.R.id.home:
                // todo: goto back activity from here

                KadepokDetailActivity.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.deskripsi_panti:
                pager.setCurrentItem(0);
                break;
            case R.id.donasi_panti:
                pager.setCurrentItem(1);
                break;
            case R.id.cherish_us:
                pager.setCurrentItem(2);
                break;
            default:
                break;
        }
    }

    class Content extends FragmentPagerAdapter {
        fragment1 fragment1;
        fragment2 fragment2;
        fragment3 fragment3;

        public Content(FragmentManager fm) {
            super(fm);
            fragment1 = fragment1.newInstance(getIntent().getExtras().getString("getId"));
            fragment2 = fragment2.newInstance(getIntent().getExtras().getString("getId"));
            fragment3 = fragment3.newInstance(getIntent().getExtras().getString("getId"));
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

}
