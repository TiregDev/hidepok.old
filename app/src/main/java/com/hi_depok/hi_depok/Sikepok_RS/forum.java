package com.hi_depok.hi_depok.Sikepok_RS;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
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

import java.util.ArrayList;
import java.util.List;


public class forum extends BaseActivity {
    private SearchView searchView;

    String GET_JSON_DATA_HTTP_URL;
    String GET_JSON_DATA_HTTP_URL2;
    String JSON_ID_POST = "id_post";
    String JSON_ID_MODUL = "id_modul";
    String JSON_ID_USER = "id_user";
    String JSON_JUDUL_POST = "judul_post";
    String JSON_ISI_POST = "isi_post";
    String JSON_TANGGAL_POST = "tanggal_post";
    String JSON_WAKTU_POST = "waktu_post";
    String JSON_KATEGORI_POST = "kategori_post";
    String JSON_FOTO_POST = "foto_post";
    String JSON_DESKRIPSI_POST = "deskripsi_post";
    String JSON_LOKASI_POST = "lokasi_post";
    String JSON_NO_IDENTITAS_POST = "no_identitas_post";
    String JSON_STATUS_POST = "status_post";
    String JSON_RATING_POST = "rating_post";

    JsonArrayRequest jsonArrayRequest ;
    List<GetDataAdapter> dataAdapter;
    RequestQueue requestQueue ;
    RecyclerView.Adapter recyclerViewadapter;
    RecyclerView rViewPOST;
    RecyclerView rViewSAYA;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sikepokrs_forum);
        super.onCreateDrawer();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar3));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //recycler view
        rViewPOST = (RecyclerView) findViewById(R.id.recyclerviewpost1);
        rViewPOST.setLayoutManager(new LinearLayoutManager(this));

        rViewSAYA = (RecyclerView) findViewById(R.id.recyclerviewpost2);
        rViewSAYA.setLayoutManager(new LinearLayoutManager(this));

        dataAdapter = new ArrayList<>();
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading");
        dialog.show();
        dialog.setCancelable(false);

        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/android/sikepok/1.2/sikepokrs_forum_json.php?id=2";
        GET_JSON_DATA_HTTP_URL2 = "http://hidepok.id/android/sikepok/1.2/sikepokrs_forum_json.php?id_saya=6";

        JSON_DATA_WEB_CALL();
        JSON_DATA_WEB_CALL2();
    }

    //r view
    public void JSON_DATA_WEB_CALL(){
        jsonArrayRequest = new JsonArrayRequest(GET_JSON_DATA_HTTP_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        dialog.dismiss();
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
            GetDataAdapter dataFromJSON = new GetDataAdapter();
            JSONObject json = null;
            try {
                json = array.getJSONObject(i);

                dataFromJSON.setId_post(json.getString(JSON_ID_POST));
                dataFromJSON.setId_modul(json.getString(JSON_ID_MODUL));
                dataFromJSON.setId_user(json.getString(JSON_ID_USER));
                dataFromJSON.setJudul_post(json.getString(JSON_JUDUL_POST));
                dataFromJSON.setIsi_post(json.getString(JSON_ISI_POST));
                dataFromJSON.setTanggal_post(json.getString(JSON_TANGGAL_POST));
                dataFromJSON.setWaktu_post(json.getString(JSON_WAKTU_POST));
                dataFromJSON.setKategori_post(json.getString(JSON_KATEGORI_POST));
                dataFromJSON.setFoto_post(json.getString(JSON_FOTO_POST));
                dataFromJSON.setDeskripsi_post(json.getString(JSON_DESKRIPSI_POST));
                dataFromJSON.setLokasi_post(json.getString(JSON_LOKASI_POST));
                dataFromJSON.setNo_identitas_post(json.getString(JSON_NO_IDENTITAS_POST));
                dataFromJSON.setStatus_post(json.getString(JSON_STATUS_POST));
                dataFromJSON.setRating_post(json.getString(JSON_RATING_POST));

            } catch (JSONException e) {

                e.printStackTrace();
            }
            dataAdapter.add(dataFromJSON);
        }

        recyclerViewadapter = new RecyclerViewAdapterPOST(dataAdapter, this);
        rViewPOST.setAdapter(recyclerViewadapter);
    }

    //r view 2
    public void JSON_DATA_WEB_CALL2(){
        jsonArrayRequest = new JsonArrayRequest(GET_JSON_DATA_HTTP_URL2,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        dialog.dismiss();
                        JSON_PARSE_DATA_AFTER_WEBCALL2(response);
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

    public void JSON_PARSE_DATA_AFTER_WEBCALL2(JSONArray array){
        for(int i = 0; i<array.length(); i++) {
            GetDataAdapter dataFromJSON = new GetDataAdapter();
            JSONObject json = null;
            try {
                json = array.getJSONObject(i);

                dataFromJSON.setId_post(json.getString(JSON_ID_POST));
                dataFromJSON.setId_modul(json.getString(JSON_ID_MODUL));
                dataFromJSON.setId_user(json.getString(JSON_ID_USER));
                dataFromJSON.setJudul_post(json.getString(JSON_JUDUL_POST));
                dataFromJSON.setIsi_post(json.getString(JSON_ISI_POST));
                dataFromJSON.setTanggal_post(json.getString(JSON_TANGGAL_POST));
                dataFromJSON.setWaktu_post(json.getString(JSON_WAKTU_POST));
                dataFromJSON.setKategori_post(json.getString(JSON_KATEGORI_POST));
                dataFromJSON.setFoto_post(json.getString(JSON_FOTO_POST));
                dataFromJSON.setDeskripsi_post(json.getString(JSON_DESKRIPSI_POST));
                dataFromJSON.setLokasi_post(json.getString(JSON_LOKASI_POST));
                dataFromJSON.setNo_identitas_post(json.getString(JSON_NO_IDENTITAS_POST));
                dataFromJSON.setStatus_post(json.getString(JSON_STATUS_POST));
                dataFromJSON.setRating_post(json.getString(JSON_RATING_POST));

            } catch (JSONException e) {

                e.printStackTrace();
            }
            dataAdapter.add(dataFromJSON);
        }
        recyclerViewadapter = new RecyclerViewAdapterPOSTSAYA(dataAdapter, this);
        rViewSAYA.setAdapter(recyclerViewadapter);

    }

    //search
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        // Retrieve the SearchView and plug it into SearchManager
        final MenuItem searchItem = menu.findItem(R.id.action_search);

        if (searchItem != null) {
            searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
            searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                @Override
                public boolean onClose() {
                    //some operation
                    return false;
                }
            });
            searchView.setOnSearchClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //some operation
                }
            });
            EditText searchPlate = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
            searchPlate.setHint("Search");
            View searchPlateView = searchView.findViewById(android.support.v7.appcompat.R.id.search_plate);
            searchPlateView.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
            // use this method for search process
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    // use this method when query submitted
                    Toast.makeText(forum.this, query, Toast.LENGTH_SHORT).show();
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    // use this method for auto complete search process
                    return false;
                }
            });
            SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        }
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                forum.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    public void ke_detail_post (View view){
//        Intent next = new Intent(forum.this, detail_post.class);
//        startActivity(next);
//    }
//
//    public void ke_detail_post_saya (View view){
//        Intent next = new Intent(forum.this, detail_post_saya.class);
//        startActivity(next);
//    }

    public void ke_post_saya (View view){
        Intent next = new Intent(forum.this, post_saya.class);
        startActivity(next);
    }

    public void ke_post_terbaru (View view){
        Intent next = new Intent(forum.this, list_diskusi.class);
        startActivity(next);
    }
}
