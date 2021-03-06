package com.hi_depok.hi_depok.Sikepok_Diagnosa;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by HP on 4/28/2016.
 */
public class DiagnosaBagian extends BaseActivity {

    String JSON_URL = "http://hidepok.id/android/sikepok/1.1/sikepokdiagnosa_json.php";
    RecyclerView rView;
    List<DataModel> dataAdapter;
    DataModel data;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sikepokdiagnosa_anggota_badan);
        super.onCreateDrawer();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rView = (RecyclerView) findViewById(R.id.list_ensiklopedia);
        rView.setLayoutManager(new LinearLayoutManager(this));
        rView.setHasFixedSize(true);
        dataAdapter = new ArrayList<>();
        JSON_URL = "http://hidepok.id/android/sikepok/1.1/sikepokdiagnosa_json.php?id=" + getIntent().getExtras().getString("getID");
        getDataFromJSON(JSON_URL);
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
                        data.setId(json.getString("id_gejala"));
                        data.setNama(json.getString("nama_gejala"));

                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                    dataAdapter.add(data);
                }
                RecyclerView.Adapter rViewAdapter = new DiagnosaBagianAdapter(dataAdapter, getBaseContext());
                rView.setAdapter(rViewAdapter);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_gejala, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                DiagnosaBagian.this.finish();
                return true;
            case R.id.action_gejala:
                startActivity(new Intent(getBaseContext(), Gejala.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}


