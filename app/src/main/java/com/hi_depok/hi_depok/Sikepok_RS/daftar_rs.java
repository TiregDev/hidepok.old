package com.hi_depok.hi_depok.Sikepok_RS;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class daftar_rs extends BaseActivity {
    private SearchView searchView;

    public static final String PATOKAN = "patokan";
    String GET_JSON_DATA_HTTP_URL;
    String JSON_ID = "id_rs";
    String JSON_ALAMAT = "alamat_rs";
    String JSON_NAME = "nama_rs";
    String JSON_DESKRIPSI = "deskripsi_rs";
    String JSON_NOTLP = "no_telp_rs";
    String JSON_FOTO = "foto_rs";
    String JSON_KECAMATAN = "kecamatan_rs";
    String JSON_LAT = "koordinat_latitude_rs";
    String JSON_LONG ="koordinat_longitude_rs";
    String JSON_WEB ="website_rs";
    String JSON_EMAIL = "email_rs";
    String JSON_ID_PARTNER = "id_partner";

    JsonArrayRequest jsonArrayRequest ;
    List<GetDataAdapter> dataAdapter;
    RequestQueue requestQueue ;
    RecyclerView.Adapter recyclerViewadapter;
    RecyclerView rView;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sikepokrs_daftar_rs);
        super.onCreateDrawer();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar3));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rView = (RecyclerView) findViewById(R.id.recyclerview);

        rView.setLayoutManager(new LinearLayoutManager(this));

        dataAdapter = new ArrayList<>();
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading");
        dialog.show();
        dialog.setCancelable(true);

        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/android/sikepok/1.2/sikepokrs_menurs_json.php";

        JSON_DATA_WEB_CALL();
    }

    //dll
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                daftar_rs.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //search
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);

        if (searchItem != null) {
            searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
            searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                @Override
                public boolean onClose() {
                    //some operation
                    dataAdapter.clear();
                    GET_JSON_DATA_HTTP_URL = "http://hidepok.id/android/sikepok/1.2/sikepokrs_menurs_json.php";
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
                    GET_JSON_DATA_HTTP_URL = "http://hidepok.id/android/sikepok/1.2/sikepokrs_menurs_json.php?cari="+query;
                    JSON_DATA_WEB_CALL();
                    Toast.makeText(getBaseContext(), "Hasil pencarian untuk: " + query, Toast.LENGTH_SHORT).show();
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    dataAdapter.clear();
                    GET_JSON_DATA_HTTP_URL = "http://hidepok.id/android/sikepok/1.2/sikepokrs_menurs_json.php?cari="+newText;
                    JSON_DATA_WEB_CALL();
                    return false;
                }
            });
            SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        }

        return super.onCreateOptionsMenu(menu);
    }

    //recycler
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
            GetDataAdapter dataFromJSON = new GetDataAdapter();;
            JSONObject json = null;
            try {
                json = array.getJSONObject(i);
                dataFromJSON = new GetDataAdapter();

                dataFromJSON.setName(json.getString(JSON_NAME));
                dataFromJSON.setDeskripsi(json.getString(JSON_DESKRIPSI));
                dataFromJSON.setNoTelp(json.getString(JSON_NOTLP));
                dataFromJSON.setAlamat(json.getString(JSON_ALAMAT));
                dataFromJSON.setFoto(json.getString(JSON_FOTO));
                dataFromJSON.setId(json.getString(JSON_ID));
                dataFromJSON.setKecamatan(json.getString(JSON_KECAMATAN));
                dataFromJSON.setKordinatLat(json.getString(JSON_LAT));
                dataFromJSON.setKordinatLong(json.getString(JSON_LONG));
                dataFromJSON.setWebsite(json.getString(JSON_WEB));
                dataFromJSON.setEmail(json.getString(JSON_EMAIL));
                dataFromJSON.setId_partner(json.getString(JSON_ID_PARTNER));

                double latitudeA = Double.parseDouble(json.getString(JSON_LAT));
                double longitudeA = Double.parseDouble(json.getString(JSON_LONG));

                double latitudeB = getIntent().getExtras().getDouble("getLat");
                double longitudeB = getIntent().getExtras().getDouble("getLong");

                Double jarak = BigDecimal.valueOf(CalculationByDistance(latitudeB, longitudeB, latitudeA, longitudeA))
                        .setScale(1, RoundingMode.HALF_UP)
                        .doubleValue();
                dataFromJSON.setJarak(String.valueOf(jarak));

            } catch (JSONException e) {

                e.printStackTrace();
            }
            dataAdapter.add(dataFromJSON);
        }
        Collections.sort(dataAdapter, new Comparator<GetDataAdapter>() {
            @Override
            public int compare(GetDataAdapter data1, GetDataAdapter data2) {
                Double f1 = Double.parseDouble(data1.getJarak());
                Double f2 = Double.parseDouble(data2.getJarak());
                return f1.compareTo(f2);
            }
        });

        recyclerViewadapter = new RecyclerViewAdapterJSON(dataAdapter, this);
        rView.setAdapter(recyclerViewadapter);
    }

    //distance
    public double CalculationByDistance(double lat1, double lon1, double lat2, double lon2) {
        int Radius = 6371;// radius of earth in Km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);

        return Radius * c;
    }

//    public void ke_daftar_rs(View view){
//        Intent next = new Intent(daftar_rs.this, daftar_rs.class);
//        startActivity(next);
//    }
//
//    public void ke_menu_rs(View view){
//        Intent next = new Intent(daftar_rs.this, menu_rs.class);
//        startActivity(next);
//    }


}