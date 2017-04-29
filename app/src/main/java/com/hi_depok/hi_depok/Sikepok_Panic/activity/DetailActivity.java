/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hi_depok.hi_depok.Sikepok_Panic.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import com.bumptech.glide.Glide;
import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.Sikepok_Panic.util.Utils;
import com.hi_depok.hi_depok.Sikepok_Panic.adapter.ViewPagerAdapter;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.viewpagerindicator.CirclePageIndicator;

/**
 * Provides UI for the Detail page with Collapsing Toolbar.
 */
public class DetailActivity extends BaseActivity {
    public static final String EXTRA_POSITION = "position";

    String GET_JSON_DATA_HTTP_URL;
    String JSON_ID = "id_tempat_sehat";
    String JSON_ALAMAT = "alamat_tempat_sehat";
    String JSON_JENIS = "nama_jenis";
    String JSON_KORDINAT = "koordinat_tempat_sehat";
    String JSON_KECAMATAN = "kecamatan_tempat_sehat";
    String JSON_NAME = "nama_tempat_sehat";
    String JSON_FOTO = "foto_tempat_sehat";
    String JSON_KETERANGAN = "keterangan_tempat_sehat";
    String JSON_OPERASIONAL = "operasional_tempat_sehat";
    String JSON_NOTLP = "no_telp_tempat_sehat";
    String urlPhoto, noTelp, kordinat, namaLokasi;
    CollapsingToolbarLayout collapsingToolbar;
    TextView placeOperasional, placeDeskripsi, placeContact, placeLocation;

    ImageView placePicture, iconLokasi, iconKontak;
    ViewPager viewPager;
    PagerAdapter adapter;
    CirclePageIndicator indicator;

    JsonArrayRequest jsonArrayRequest ;
    RequestQueue requestQueue;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sikepokpanic_detail);
        super.onCreateDrawer();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, android.R.color.transparent));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent i = this.getIntent();
        Toast.makeText(getBaseContext(),i.getExtras().getString(EXTRA_POSITION),Toast.LENGTH_SHORT).show();

        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/android/sikepok/1.3/sikepokpanic_json.php?id=" + i.getExtras().getString(EXTRA_POSITION);

        iconLokasi = (ImageView) findViewById(R.id.icon_lokasi);
        iconKontak = (ImageView) findViewById(R.id.icon_kontak);
        // Set Collapsing Toolbar layout to the screen
        collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        placeOperasional =  (TextView) findViewById(R.id.place_operasional);
        placeDeskripsi =  (TextView) findViewById(R.id.place_detail);
        placeContact =  (TextView) findViewById(R.id.place_contact);
        placeLocation =  (TextView) findViewById(R.id.place_location);
        placePicture = (ImageView) findViewById(R.id.image);
        viewPager = (ViewPager) findViewById(R.id.pager);

        indicator = (CirclePageIndicator) findViewById(R.id.indicator);

        JSON_DATA_WEB_CALL();
    }

    public void JSON_DATA_WEB_CALL() {
        jsonArrayRequest = new JsonArrayRequest(GET_JSON_DATA_HTTP_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSON_PARSE_DATA_AFTER_WEBCALL(response);
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

    public void JSON_PARSE_DATA_AFTER_WEBCALL(JSONArray array){
        for(int i = 0; i<array.length(); i++) {
            JSONObject json = null;
            try {

                json = array.getJSONObject(i);
                collapsingToolbar.setTitle(json.getString(JSON_NAME));
                placeOperasional.setText(json.getString(JSON_OPERASIONAL));
                placeDeskripsi.setText(json.getString(JSON_KETERANGAN));
                placeContact.setText(json.getString(JSON_NOTLP));
                placeLocation.setText(json.getString(JSON_ALAMAT));

                noTelp = json.getString(JSON_NOTLP);
                kordinat = json.getString(JSON_KORDINAT);
                namaLokasi = json.getString(JSON_NAME);
                if(!json.getString(JSON_FOTO).equals("null") && json.getString(JSON_FOTO).contains(",")){
                    String[] listFoto = json.getString(JSON_FOTO).split(",");
                    String[] linkFoto = new String[listFoto.length];
                    for(int j=0;j<listFoto.length;j++){
                        linkFoto[j] = "http://hidepok.id/assets/images/photos/sikepok/sikepok3/" + listFoto[j];
                    }
                    adapter = new ViewPagerAdapter(this,linkFoto);
                    viewPager.setAdapter(adapter);
                    indicator.setViewPager(viewPager);
                }
                else if(!json.getString(JSON_FOTO).equals("null")){
                    urlPhoto = "http://hidepok.id/assets/images/photos/sikepok/sikepok3/" + json.getString(JSON_FOTO);
                    Glide.with(this).load(urlPhoto).thumbnail(0.3f).placeholder(R.drawable.image_placeholder).into(placePicture);
                }

                iconKontak.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent dial = new Intent();
                        dial.setAction("android.intent.action.DIAL");
                        dial.setData(Uri.parse("tel:" + noTelp));
                        startActivity(dial);
                    }
                });
                iconLokasi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String urlAddress = "http://maps.google.com/maps?q=" + kordinat + "(" + namaLokasi + ")&iwloc=A&hl=es";
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                DetailActivity.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
