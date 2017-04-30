package com.hi_depok.hi_depok.Sikepok_Diagnosa;

import android.app.SearchManager;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Diagnosa extends BaseActivity implements SearchView.OnQueryTextListener {
    String JSON_URL = "http://hidepok.id/android/sikepok/1.1/sikepokdiagnosa_json.php";
    RecyclerView rView;
    List<DataModel> dataAdapter;
    DataModel data;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sikepokdiagnosa_diagnosa);
        super.onCreateDrawer();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rView = (RecyclerView) findViewById(R.id.list_diagnosa);
        rView.setLayoutManager(new LinearLayoutManager(this));
        rView.setHasFixedSize(true);
        dataAdapter = new ArrayList<>();
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
                        data.setId(json.getString("id_bagian_tubuh"));
                        data.setNama(json.getString("nama_bagian_tubuh"));
                        data.setFoto(json.getString("foto_bagian_tubuh"));

                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                    dataAdapter.add(data);
                }
                RecyclerView.Adapter rViewAdapter = new DiagnosaAdapter(dataAdapter, getBaseContext());
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                Diagnosa.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onQueryTextChange(String query) {
        // Here is where we are going to implement the filter logic
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

}



