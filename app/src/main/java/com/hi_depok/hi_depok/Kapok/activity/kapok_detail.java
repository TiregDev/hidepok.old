package com.hi_depok.hi_depok.Kapok.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.Kapok.adapter.CustomAdapterViewUlasan;
import com.hi_depok.hi_depok.Kapok.adapter.ItemObjectViewUlasan;
import com.hi_depok.hi_depok.Kapok.adapter.ViewPagerAdapter;
import com.hi_depok.hi_depok.R;
import com.viewpagerindicator.CirclePageIndicator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 4/7/2017.
 */

public class kapok_detail extends BaseActivity {
    private LinearLayoutManager lLayout;
    public static final String EXTRA_POSITION = "position";
    String GET_JSON_DATA_HTTP_URL, urlPhoto;
    JsonArrayRequest jsonArrayRequest ;
    RequestQueue requestQueue;

    CollapsingToolbarLayout collapsingToolbar;
    TextView placeOperasional, placeDeskripsi, placeContact, placeLocation, placeFasilitas;

    ImageView placePicture, iconLokasi, iconKontak;
    ViewPager viewPager;
    PagerAdapter adapter;
    CirclePageIndicator indicator;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kapok_detail);
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
//        iconLokasi = (ImageView) findViewById(R.id.icon_lokasi);
//        iconKontak = (ImageView) findViewById(R.id.icon_kontak);
        // Set Collapsing Toolbar layout to the screen
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        placeOperasional =  (TextView) findViewById(R.id.hari);
        placeDeskripsi =  (TextView) findViewById(R.id.place_detail);
        placeFasilitas =  (TextView) findViewById(R.id.menu_fasilitas);

//        placeContact =  (TextView) findViewById(R.id.place_contact);
        placeLocation =  (TextView) findViewById(R.id.place_location);
        placePicture = (ImageView) findViewById(R.id.image);
        viewPager = (ViewPager) findViewById(R.id.pager);
        indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        // Set title of Detail page
        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/android/kapok/kapok_json.php?id=" + getIntent().getExtras().getString("getID");


        TextView lengkap = (TextView) findViewById(R.id.ulasan);
        lengkap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(kapok_detail.this, activity_view_ulasan.class));
            }
        });
        //recycle ulasan
        List<ItemObjectViewUlasan> rowListItem = getAllItemList();
        lLayout = new LinearLayoutManager(kapok_detail.this);

        RecyclerView rView = (RecyclerView)findViewById(R.id.recycler_view);
        rView.setLayoutManager(lLayout);

        CustomAdapterViewUlasan rcAdapter = new CustomAdapterViewUlasan(kapok_detail.this, rowListItem);
        rView.setAdapter(rcAdapter);
        JSON_DATA_WEB_CALL();

        //buat popup
        ImageView tambah = (ImageView) findViewById(R.id.tambah);
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initiatepopup();
            }
        });



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
                collapsingToolbar.setTitle(json.getString("nama_tempat"));
                placeDeskripsi.setText(json.getString("deskripsi_tempat"));
                placeOperasional.setText(json.getString("jam_operasi_tempat"));
                placeFasilitas.setText(json.getString("fasilitas_tempat"));
                placeLocation.setText(json.getString("alamat_tempat"));
                if(!json.getString("foto_tempat").equals("null") && json.getString("foto_tempat").contains(",")){
                    String[] listFoto = json.getString("foto_tempat").split(",");
                    String[] linkFoto = new String[listFoto.length];
                    for(int j=0;j<listFoto.length;j++){
                        linkFoto[j] = "http://hidepok.id/assets/images/photos/kapok/" + listFoto[j];
                    }
                    adapter = new ViewPagerAdapter(this,linkFoto);
                    viewPager.setAdapter(adapter);
                    indicator.setViewPager(viewPager);
                }
                else if(!json.getString("foto_tempat").equals("null")){
                    urlPhoto = "http://hidepok.id/assets/images/photos/kapok/" + json.getString("foto_tempat");
                    Glide.with(this).load(urlPhoto).thumbnail(0.3f).placeholder(R.drawable.image_placeholder).into(placePicture);
                }
                else {
                    placePicture.setImageResource(R.drawable.image_placeholder);
                }

            } catch (JSONException e) {

                e.printStackTrace();
            }
        }
    }
    //ulasan
    private List<ItemObjectViewUlasan> getAllItemList(){
        List<ItemObjectViewUlasan> allItems = new ArrayList<>();
        allItems.add(new ItemObjectViewUlasan("Kadek Pandu", "12-01-2016 20:01:56", "Makanannya enak dan murah", R.drawable.profile));
        allItems.add(new ItemObjectViewUlasan("Fajar Zakaria", "12-01-2016 21:01:56", "Tempatnya nyaman dan bersih", R.drawable.profile));
        allItems.add(new ItemObjectViewUlasan("Tegar Hidayat", "13-01-2016 08:02:00", "makannya muuaannntebbb!!!!", R.drawable.profile));
        return allItems;
    }

    @Override
      public boolean onOptionsItemSelected(MenuItem item) {
          switch (item.getItemId()) {
              case android.R.id.home:
                  // todo: goto back activity from here

                  kapok_detail.this.finish();
                  return true;

              default:
                  return super.onOptionsItemSelected(item);
          }
      }
    public void toMaps(View v){
        Intent intent = new Intent(getBaseContext(), MapsActivity.class);
        startActivity(intent);
    }
    //popup
    private PopupWindow pwindo;

    private void initiatepopup() {
        try {
            LayoutInflater inflater = (LayoutInflater) kapok_detail.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final AlertDialog ad = new AlertDialog.Builder(this).create();
            View layout = inflater.inflate(R.layout.kapok_popup_ulasan, (ViewGroup) findViewById(R.id.popup_element));
            ad.setView(layout);
            ad.show();
            ImageView close = (ImageView) layout.findViewById(R.id.close);
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ad.dismiss();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


