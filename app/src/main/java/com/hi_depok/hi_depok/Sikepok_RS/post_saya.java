package com.hi_depok.hi_depok.Sikepok_RS;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
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
import com.hi_depok.hi_depok.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class post_saya extends BaseActivity {
    private SearchView searchView;

    //inisialisasi post
    String GET_JSON_DATA_HTTP_URL;
    String JSON_ID_POST = "id_post";
//    String JSON_ID_MODUL = "id_modul";
    String JSON_ID_USER = "id_user";
    String JSON_JUDUL_POST = "judul_post";
    String JSON_ISI_POST = "isi_post";
    String JSON_TANGGAL_POST = "tanggal_post";
    String JSON_WAKTU_POST = "waktu_post";
//    String JSON_KATEGORI_POST = "kategori_post";
//    String JSON_FOTO_POST = "foto_post";
//    String JSON_DESKRIPSI_POST = "deskripsi_post";
//    String JSON_LOKASI_POST = "lokasi_post";
//    String JSON_NO_IDENTITAS_POST = "no_identitas_post";
//    String JSON_STATUS_POST = "status_post";
//    String JSON_RATING_POST = "rating_post";
    String JSON_ANGKA_KOMEN = "total_komentar";
    String JSON_ANGKA_SUKA = "total_suka";

    SessionManager session;

    JsonArrayRequest jsonArrayRequest ;
    List<GetDataAdapter> dataAdapter;
    RequestQueue requestQueue ;
    RecyclerView.Adapter recyclerViewadapter;
    RecyclerView rView;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sikepokrs_post_saya);
        super.onCreateDrawer();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar3));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //session login
        session = new SessionManager(this);
        HashMap<String, String> user = session.getUserDetails();
        String id_user = user.get(SessionManager.KEY_ID_USER);

        //recycler view
        rView = (RecyclerView) findViewById(R.id.recyclerviewpost);

        rView.setLayoutManager(new LinearLayoutManager(this));

        dataAdapter = new ArrayList<>();
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading");
        dialog.show();
        dialog.setCancelable(false);

        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/android/sikepok/1.2/sikepokrs_forum_json.php?id_saya_semua=" + id_user;

        JSON_DATA_WEB_CALL();
    }

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
//                dataFromJSON.setId_modul(json.getString(JSON_ID_MODUL));
                dataFromJSON.setId_user(json.getString(JSON_ID_USER));
                dataFromJSON.setJudul_post(json.getString(JSON_JUDUL_POST));
                dataFromJSON.setIsi_post(json.getString(JSON_ISI_POST));
                dataFromJSON.setTanggal_post(json.getString(JSON_TANGGAL_POST));
                dataFromJSON.setWaktu_post(json.getString(JSON_WAKTU_POST));
//                dataFromJSON.setKategori_post(json.getString(JSON_KATEGORI_POST));
//                dataFromJSON.setFoto_post(json.getString(JSON_FOTO_POST));
//                dataFromJSON.setDeskripsi_post(json.getString(JSON_DESKRIPSI_POST));
//                dataFromJSON.setLokasi_post(json.getString(JSON_LOKASI_POST));
//                dataFromJSON.setNo_identitas_post(json.getString(JSON_NO_IDENTITAS_POST));
//                dataFromJSON.setStatus_post(json.getString(JSON_STATUS_POST));
//                dataFromJSON.setRating_post(json.getString(JSON_RATING_POST));
                dataFromJSON.setAngka_komen(json.getString(JSON_ANGKA_KOMEN));
                dataFromJSON.setAngka_suka(json.getString(JSON_ANGKA_SUKA));

            } catch (JSONException e) {

                e.printStackTrace();
            }
            dataAdapter.add(dataFromJSON);
        }

        recyclerViewadapter = new RecyclerViewAdapterPOSTSAYA(dataAdapter, this);
        rView.setAdapter(recyclerViewadapter);
    }

    //On Restart
    @Override
    protected void onRestart() {
        super.onRestart();
        dataAdapter.clear();
        recyclerViewadapter.notifyDataSetChanged();
        JSON_DATA_WEB_CALL();
    }

    //lain2
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                post_saya.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //search
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //session login
        session = new SessionManager(this);
        HashMap<String, String> user = session.getUserDetails();
        final String id_user = user.get(SessionManager.KEY_ID_USER);

        getMenuInflater().inflate(R.menu.menu_main, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);

        if (searchItem != null) {
            searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
            searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                @Override
                public boolean onClose() {
                    //some operation
                    dataAdapter.clear();
                    GET_JSON_DATA_HTTP_URL = "http://hidepok.id/android/sikepok/1.2/sikepokrs_forum_json.php?id_saya_semua=" + id_user;
                    JSON_DATA_WEB_CALL();
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
                    // use this method when query submittet
                    dataAdapter.clear();
                    GET_JSON_DATA_HTTP_URL = "http://hidepok.id/android/sikepok/1.2/sikepokrs_forum_json.php?id_saya_semua=" + id_user + "&cari2=" +query;
                    JSON_DATA_WEB_CALL();
                    Toast.makeText(getBaseContext(), "Hasil pencarian untuk: " + query, Toast.LENGTH_SHORT).show();
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    // use this method when query submittet
                    dataAdapter.clear();
                    GET_JSON_DATA_HTTP_URL = "http://hidepok.id/android/sikepok/1.2/sikepokrs_forum_json.php?id_saya_semua=" + id_user + "&cari2=" +newText;
                    JSON_DATA_WEB_CALL();
                    return false;
                }
            });
            SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        }

        return super.onCreateOptionsMenu(menu);
    }
}
