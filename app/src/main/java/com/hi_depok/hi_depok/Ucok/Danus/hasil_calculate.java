package com.hi_depok.hi_depok.Ucok.Danus;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.Ucok.SIUMKM.GetDataAdapter_siumkm;
import com.hi_depok.hi_depok.Ucok.SIUMKM.RecyclerViewAdapterJSON_siumkm;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class hasil_calculate extends BaseActivity {
    private LinearLayoutManager lLayout;

    String JSON_ID_BARANG = "id_barang";
    String JSON_NAMA_UKM = "nama_ukm";
    String JSON_NAMA_BARANG = "nama_barang";
    String JSON_HARGA_BARANG = "harga_barang";
    String JSON_FOTO_BARANG = "foto_barang";
    String JSON_NO_TLP = "notelp";
    String JSON_KORDINAT_1 = "koordinat1";
    String JSON_KORDINAT_2 = "koordinat2";
    String JSON_TARGET_WAKTU= "target_waktu";
    String JSON_TARGET_UANG = "target_harga";
    String JSON_HARGA_JUAL = "harga_jual";
    String JSON_DESC_UKM = "deskripsi_ukm";
    String JSON_ALAMAT_UKM = "alamat_ukm";
    String JSON_OWNER_UKM = "nama_owner_ukm";
    String JSON_KECAMATAN = "kecamatan";

    JsonArrayRequest jsonArrayRequest ;
    List<GetDataAdapter_siumkm> dataAdapter;
    RequestQueue requestQueue ;
    RecyclerView.Adapter recyclerViewadapter;
    RecyclerView rView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ucok_hasil_calculate);
        super.onCreateDrawer();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lLayout = new LinearLayoutManager(this);
        dataAdapter = new ArrayList<>();
        rView = (RecyclerView)findViewById(R.id.rekomen_danus);
        rView.setLayoutManager(lLayout);
        JSON_DATA_WEB_CALL();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                hasil_calculate.this.finish();
                return true;
//            case R.id.action_share:
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void JSON_DATA_WEB_CALL(){
        jsonArrayRequest = new JsonArrayRequest(getIntent().getExtras().getString("urlJSON"),
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
            GetDataAdapter_siumkm dataFromJSON = new GetDataAdapter_siumkm();
            JSONObject json = null;
            try {
                json = array.getJSONObject(i);
                dataFromJSON.setId_barang(json.getString(JSON_ID_BARANG));
                dataFromJSON.setNama_ukm(json.getString(JSON_NAMA_UKM));
                dataFromJSON.setNama_barang(json.getString(JSON_NAMA_BARANG));
                dataFromJSON.setHarga_barang(json.getString(JSON_HARGA_BARANG));
                dataFromJSON.setFoto_barang(json.getString(JSON_FOTO_BARANG));
                dataFromJSON.setNotelp(json.getString(JSON_NO_TLP));
                dataFromJSON.setKoordinat_ukm_1(json.getString(JSON_KORDINAT_1));
                dataFromJSON.setKoordinat_ukm_2(json.getString(JSON_KORDINAT_2));
                dataFromJSON.setTarget_waktu(json.getString(JSON_TARGET_WAKTU));
                dataFromJSON.setTarget_uang(json.getString(JSON_TARGET_UANG));
                dataFromJSON.setHarga_jual(json.getString(JSON_HARGA_JUAL));
                dataFromJSON.setDeskripsi_ukm(json.getString(JSON_DESC_UKM));
                dataFromJSON.setAlamat_ukm(json.getString(JSON_ALAMAT_UKM));
                dataFromJSON.setNama_owner_ukm(json.getString(JSON_OWNER_UKM));
                dataFromJSON.setKecamatan(json.getString(JSON_KECAMATAN));

            } catch (JSONException e) {

                e.printStackTrace();
            }
            dataAdapter.add(dataFromJSON);
        }
        recyclerViewadapter = new KalkulasiAdapter(dataAdapter, this);
        rView.setAdapter(recyclerViewadapter);
    }

}
