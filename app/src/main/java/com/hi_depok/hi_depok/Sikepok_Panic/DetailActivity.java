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

package com.hi_depok.hi_depok.Sikepok_Panic;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides UI for the Detail page with Collapsing Toolbar.
 */
public class DetailActivity extends BaseActivity {

    public static final String EXTRA_POSITION = "position";
    public static final String CATEGORY = "position";
    String GET_JSON_DATA_HTTP_URL;
    String JSON_ID = "id_tempat";
    String JSON_ALAMAT = "alamat_tempat";
    String JSON_NAME = "nama_tempat";
    String JSON_DESKRIPSI = "deskripsi_tempat";
    String JSON_NOTLP = "no_telp_tempat";
    JSONArray array;
    List<GetDataAdapter> dataAdapter;
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
        // Set Collapsing Toolbar layout to the screen
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        // Set title of Detail page
        dataAdapter = new ArrayList<>();
        int postion = getIntent().getIntExtra(EXTRA_POSITION, 0);
        String context = getIntent().getExtras().getString("kategori");
        switch (context){
            case "AmbulanceFragment":
                GET_JSON_DATA_HTTP_URL = "http://hidepok.id/include/jsonData.php?kategori=kuliner";
                break;
            case "BidanFragment":
                GET_JSON_DATA_HTTP_URL = "http://hidepok.id/include/jsonData.php?kategori=kuliner";
                break;
            case "TukangUrutFragment":
                GET_JSON_DATA_HTTP_URL = "http://hidepok.id/include/jsonData.php?kategori=kuliner";
                break;
            case "KhitanFragment":
                GET_JSON_DATA_HTTP_URL = "http://hidepok.id/include/jsonData.php?kategori=kuliner";
                break;
            case "KlinikFragment":
                GET_JSON_DATA_HTTP_URL = "http://hidepok.id/include/jsonData.php?kategori=kuliner";
                break;
            case "ApotekFragment":
                GET_JSON_DATA_HTTP_URL = "http://hidepok.id/include/jsonData.php?kategori=kuliner";
                break;
            case "PuskesmasFragment":
                GET_JSON_DATA_HTTP_URL = "http://hidepok.id/include/jsonData.php?kategori=kuliner";
                break;
            default:
                break;
        }
        for(int i = 0; i<array.length(); i++) {
            GetDataAdapter dataFromJSON = new GetDataAdapter();
            JSONObject json = null;
            try {
                json = array.getJSONObject(i);
                dataFromJSON.setName(json.getString(JSON_NAME));
                dataFromJSON.setDeskripsi(json.getString(JSON_DESKRIPSI));
                dataFromJSON.setAlamat(json.getString(JSON_ALAMAT));
                dataFromJSON.setNoTelp(json.getString(JSON_NOTLP));
                dataFromJSON.setFoto(R.drawable.a_avator);

            } catch (JSONException e) {

                e.printStackTrace();
            }
            dataAdapter.add(dataFromJSON);
        }

        Resources resources = getResources();
        String[] places = resources.getStringArray(R.array.panic_nama);
        collapsingToolbar.setTitle(dataAdapter.get(postion).getName());

        String[] placeDetails = resources.getStringArray(R.array.place_details);
        TextView placeDetail = (TextView) findViewById(R.id.place_detail);
        placeDetail.setText(dataAdapter.get(postion).getDeskripsi());

        String[] placeLocations = resources.getStringArray(R.array.panic_deskripsi);
        TextView placeLocation =  (TextView) findViewById(R.id.place_location);
        placeLocation.setText(dataAdapter.get(postion).getAlamat());

        TextView placeContact =  (TextView) findViewById(R.id.place_contact);
        placeContact.setText(dataAdapter.get(postion).getNoTelp());

        TypedArray placePictures = resources.obtainTypedArray(R.array.places_picture2);
        ImageView placePicture = (ImageView) findViewById(R.id.image);
        placePicture.setImageDrawable(placePictures.getDrawable(postion % placePictures.length()));

        placePictures.recycle();
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
