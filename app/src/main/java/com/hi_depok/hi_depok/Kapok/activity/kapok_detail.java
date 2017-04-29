package com.hi_depok.hi_depok.Kapok.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
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
import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.Kapok.adapter.CustomAdapterViewUlasan;
import com.hi_depok.hi_depok.Kapok.adapter.ItemObjectViewUlasan;
import com.hi_depok.hi_depok.R;;

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
    String GET_JSON_DATA_HTTP_URL;
    JsonArrayRequest jsonArrayRequest ;
    RequestQueue requestQueue;
    CollapsingToolbarLayout collapsingToolbar;
    ImageView placePicture;
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
        collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        // Set title of Detail page
        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/android/kapok/kapok_json.php?id=" + getIntent().getExtras().getString(EXTRA_POSITION);
        int postion = getIntent().getIntExtra(EXTRA_POSITION, 0);
        Resources resources = getResources();
        collapsingToolbar.getExpandedTitleMarginBottom();

        String[] placeDetails = resources.getStringArray(R.array.place_details);
        TextView placeDetail = (TextView) findViewById(R.id.place_detail);
        placeDetail.setText(placeDetails[postion % placeDetails.length]);

        String[] placeLocations = resources.getStringArray(R.array.panic_deskripsi);
        TextView placeLocation = (TextView) findViewById(R.id.place_location);
        placeLocation.setText(placeLocations[postion % placeLocations.length]);

        placePicture = (ImageView) findViewById(R.id.image);


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
                placePicture.setImageResource(R.drawable.logo);

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


