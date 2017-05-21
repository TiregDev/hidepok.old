package com.hi_depok.hi_depok.Ucok.Danus;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class detail_target extends BaseActivity {

    String URL;
    DataModel data;
    CollapsingToolbarLayout collapsingToolbar;
    TextView placeDetail, placeDetail2;
    ImageView placePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ucok_detail_target);
        super.onCreateDrawer();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, android.R.color.transparent));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        /* ----------------------------- TOOLBAR ----------------------------------------------------------*/
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Set Collapsing Toolbar layout to the screen
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        placeDetail = (TextView) findViewById(R.id.detail);
        placeDetail2 = (TextView) findViewById(R.id.detail_jual);

        placePicture = (ImageView) findViewById(R.id.image_target);

        URL = "http://hidepok.id/android/ucok/ucok_riwayat_usaha.php?id=" + getIntent().getExtras().getString("getId");
        getDataFromJSON(URL);
    }


    public void getDataFromJSON(String url){
        JsonArrayRequest req = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++){
                    data = new DataModel();
                    JSONObject json = null;
                    try{

                        json = response.getJSONObject(i);
                        collapsingToolbar.setTitle(json.getString("nama_event"));
                        placeDetail.setText(json.getString("desc_event"));
                        String a = "Untuk mendapatkan uang sebanyak Rp "+json.getString("target_uang")+ " dalam waktu "+json.getString("target_hari")+" hari, Anda di rekomendasikan untuk menjual "+json.getString("barang_jual")+" dengan harga Rp "+json.getString("harga_jual");
                        placeDetail2.setText(a);
                        Glide.with(getBaseContext()).load("http://hidepok.id/assets/images/photos/ucok/" + json.getString("foto_barang")).placeholder(R.drawable.image_placeholder).thumbnail(0.3f).into(placePicture);


                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue reqQueue = Volley.newRequestQueue(this);
        reqQueue.add(req);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                detail_target.this.finish();
                return true;
//            case R.id.action_share:
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
