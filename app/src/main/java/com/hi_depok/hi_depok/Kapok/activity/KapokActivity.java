package com.hi_depok.hi_depok.Kapok.activity;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.Kapok.adapter.GetDataAdapter;
import com.hi_depok.hi_depok.Kapok.adapter.RecyclerViewAdapterJSON;
import com.hi_depok.hi_depok.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KapokActivity extends BaseActivity implements AdapterView.OnItemSelectedListener  {
    private Button temukan;
    //array recycleview menu sort

    String GET_JSON_DATA_HTTP_URL = "http://hidepok.id/android/kapok/kapok_json.php";
    String JSON_ID = "id_tempat";
    String JSON_ALAMAT = "alamat_tempat";
    String JSON_KATEGORI = "kategori_tempat";
    String JSON_KORDINAT = "koordinat_tempat";
    String JSON_KECAMATAN = "kecamatan_tempat";
    String JSON_NAME = "nama_tempat";
    String JSON_FOTO = "foto_tempat";
    String JSON_DESKIPSI = "deskripsi_tempat";
    String JSON_FAV = "menu_fav_tempat";
    String JSON_FASILITAS = "fasilitas_tempat";
    String JSON_NOTE = "note_tempat";
    String JSON_OPERASIONAL = "jam_operasi_tempat";
    String JSON_NOTLP = "no_telp_tempat";
    String kategori;
    String kecamatan;
    JsonArrayRequest jsonArrayRequest ;
    List<GetDataAdapter> dataAdapter;
    RequestQueue requestQueue ;
    RecyclerView.Adapter recyclerViewadapter;
    RecyclerView rView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kapok_user_kapok);
        super.onCreateDrawer();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Spinner pilihan = (Spinner) findViewById(R.id.pilihan);
        final Spinner camat = (Spinner) findViewById(R.id.camat);
        temukan=(Button)findViewById(R.id.temukan);
        dataAdapter = new ArrayList<>();


        // Spinner click listener
        List<String> pilih = new ArrayList<String>();
        pilih.add("Pilihan");
        pilih.add("Kuliner");
        pilih.add("Wisata");
        pilih.add("Pasar");
        pilih.add("Tempat Ibadah");
        pilih.add("GOR");

        ArrayAdapter<String> pilihAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pilih);
        pilihAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        pilihan.setAdapter(pilihAdapter);
        pilihan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 1:
                        kategori = "kuliner";
                        break;
                    case 2:
                        kategori ="wisata";
                        break;
                    case 3:
                        kategori = "pasar";
                        break;
                    case 4:
                        kategori = "tempat%20ibadah";
                        break;
                    case 5:
                        kategori = "olahraga";
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        List<String> wilayah = new ArrayList<String>();
        wilayah.add("Kecamatan");
        wilayah.add("Beji");
        wilayah.add("Cilodong");
        wilayah.add("Cimanggis");
        wilayah.add("Cinere");
        wilayah.add("Cipayung");
        wilayah.add("Limo");
        wilayah.add("Pancoran Mas");
        wilayah.add("Sawangan");
        wilayah.add("Tapos");
        wilayah.add("Bojongsari");
        wilayah.add("Sukmajaya");

        ArrayAdapter<String> camatAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, wilayah);
        camatAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        camat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 1:
                        kecamatan = "beji";
                        break;
                    case 2:
                        kecamatan = "cilodong";
                        break;
                    case 3:
                        kecamatan = "cimanggis";
                        break;
                    case 4:
                        kecamatan = "cinere";
                        break;
                    case 5:
                        kecamatan = "cipayung";
                        break;
                    case 6:
                        kecamatan = "limo";
                        break;
                    case 7:
                        kecamatan = "pancoran%20mas";
                        break;
                    case 8:
                        kecamatan = "sawangan";
                        break;
                    case 9:
                        kecamatan = "tapos";
                        break;
                    case 10:
                        kecamatan = "bojongsari";
                        break;
                    case 11:
                        kecamatan = "sukmajaya";
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        camat.setAdapter(camatAdapter);


        temukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pilihan.getSelectedItemPosition()==0)
                {
                    Toast.makeText(KapokActivity.this, "Pilih Kategori Terlebih Dahulu!", Toast.LENGTH_LONG).show();
                }
                else if(camat.getSelectedItemPosition()==0)
                {
                    Toast.makeText(KapokActivity.this, "Pilih Kecamatan Terlebih Dahulu!", Toast.LENGTH_LONG).show();
                }
                else if(pilihan.getSelectedItemPosition()==0 && camat.getSelectedItemPosition()==0)
                {
                    Toast.makeText(KapokActivity.this, "Pilih Kecamatan dan Kategori Terlebih Dahulu!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    GET_JSON_DATA_HTTP_URL = "http://hidepok.id/android/kapok/kapok_json.php?kategori=" + kategori + "&kecamatan=" + kecamatan;
                    JSON_DATA_WEB_CALL();
                }


            }
        });

        JSON_DATA_WEB_CALL();

        //recycleview
        rView = (RecyclerView) findViewById(R.id.recyclerView);
        // set a GridLayoutManager with 2 number of columns , horizontal gravity and false value for reverseLayout to show the items from start to end
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        rView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView


}
    public void JSON_DATA_WEB_CALL(){
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
        dataAdapter.clear();
        for(int i = 0; i<array.length(); i++) {
            GetDataAdapter dataFromJSON = new GetDataAdapter();
            JSONObject json = null;
            try {
                json = array.getJSONObject(i);
                dataFromJSON.setName(json.getString(JSON_NAME));
                dataFromJSON.setFoto(json.getString(JSON_FOTO));
                dataFromJSON.setId(json.getString(JSON_ID));

            } catch (JSONException e) {

                e.printStackTrace();
            }
            dataAdapter.add(dataFromJSON);
        }

        recyclerViewadapter = new RecyclerViewAdapterJSON(dataAdapter, this);
        rView.setAdapter(recyclerViewadapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                // todo: goto back activity from here

                KapokActivity.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}