package com.hi_depok.hi_depok.Ucok;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muhammad63 on 3/13/2017.
 */

public class ucok_content extends BaseActivity {
    private LinearLayoutManager lLayout;
    private Spinner category, sortby;

    String ktgr = "";
    String kcmtn = "";
    String GET_JSON_DATA_HTTP_URL = "http://hidepok.id/include/ucok_json.php?kategori="+ktgr+"&kecamatan="+ kcmtn;
    String JSON_ID_UKM = "id_ukm";
    String JSON_ID_BARANG = "id_barang";
    String JSON_NAMA_UKM = "nama_ukm";
    String JSON_NAMA_OWNER_UKM = "nama_owner_ukm";
    String JSON_NAMA_BARANG = "daftar_barang";
    String JSON_KATEGORI = "daftar_kategori";
    String JSON_HARGA_BARANG = "daftar_harga";
    String JSON_ALAMAT_UKM = "alamat_ukm";
    String JSON_NO_TLP = "notelp";
    String JSON_KECAMATAN = "kecamatan";
    String JSON_DESC_UKM = "deskripsi_ukm";
    String JSON_KORDINAT_1 = "koordinat1";
    String JSON_KORDINAT_2 = "koordinat2";
    String JSON_FOTO_BARANG = "foto_barang";
    String JSON_FOTO_UKM = "foto_ukm";

    JsonArrayRequest jsonArrayRequest ;
    List<GetDataAdapter_siumkm> dataAdapter;
    RequestQueue requestQueue ;
    RecyclerView.Adapter recyclerViewadapter;
    RecyclerView rView;

    private static final String[]cate = {"Kategori","Makanan", "Minuman",  "Pakaian","Aksesoris","Pajangan","Jasa","Lain-lain"};
    private static final String[]sort = {"Kecamatan","Beji", "Cilodong" , "Cimanggis" ,
            "Cinere" , "Cipayung" , "Limo" , "Pancoran Mas" , "Sawangan" , "Tapos" , "Bojongsari" , "Sukmajaya"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ucok_recycler_view);
        super.onCreateDrawer();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        List<ItemObject> rowListItem = getAllItemList();
        lLayout = new LinearLayoutManager(this);
        dataAdapter = new ArrayList<>();
        rView = (RecyclerView)findViewById(R.id.recycler_view);
        rView.setLayoutManager(lLayout);
        JSON_DATA_WEB_CALL();

        /* ------------------------- FUNGSI CLEAR ------------------------------ */

        /*-------------------------- START SPINER CATEGORY --------------------------------------- */
        category = (Spinner)findViewById(R.id.category);
        ArrayAdapter<String> adapter_cate = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,cate);
        adapter_cate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter_cate);
        category.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        if (ktgr != ""){
                            ktgr = "";
                            GET_JSON_DATA_HTTP_URL = "http://hidepok.id/include/ucok_json.php?kategori="+ktgr+"&kecamatan="+ kcmtn;
                            dataAdapter.clear();
                            recyclerViewadapter.notifyDataSetChanged();
                            JSON_DATA_WEB_CALL();
                            rView.setAdapter(recyclerViewadapter);
                        }
                        break;
                    case 1:
                        ktgr = "Makanan";
                        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/include/ucok_json.php?kategori="+ktgr+"&kecamatan="+ kcmtn;
                        dataAdapter.clear();
                        recyclerViewadapter.notifyDataSetChanged();
                        JSON_DATA_WEB_CALL();
                        rView.setAdapter(recyclerViewadapter);
                        if (dataAdapter.size()==0){
                            Toast.makeText(getBaseContext(),"Tidak ada yang dicari",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 2:
                        ktgr = "Minuman";
                        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/include/ucok_json.php?kategori="+ktgr+"&kecamatan="+ kcmtn;
                        dataAdapter.clear();
                        recyclerViewadapter.notifyDataSetChanged();
                        JSON_DATA_WEB_CALL();
                        rView.setAdapter(recyclerViewadapter);
                        if (dataAdapter.size()==0){
                            Toast.makeText(getBaseContext(),"Tidak ada yang dicari",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 3:
                        ktgr = "Pakaian";
                        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/include/ucok_json.php?kategori="+ktgr+"&kecamatan="+ kcmtn;
                        dataAdapter.clear();
                        recyclerViewadapter.notifyDataSetChanged();
                        JSON_DATA_WEB_CALL();
                        rView.setAdapter(recyclerViewadapter);
                        if (dataAdapter.size()==0){
                            Toast.makeText(getBaseContext(),"Tidak ada yang dicari",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 4:
                        ktgr = "Aksesoris";
                        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/include/ucok_json.php?kategori="+ktgr+"&kecamatan="+ kcmtn;
                        dataAdapter.clear();
                        recyclerViewadapter.notifyDataSetChanged();
                        JSON_DATA_WEB_CALL();
                        rView.setAdapter(recyclerViewadapter);
                        if (dataAdapter.size()==0){
                            Toast.makeText(getBaseContext(),"Tidak ada yang dicari",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 5:
                        ktgr = "Pajangan";
                        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/include/ucok_json.php?kategori="+ktgr+"&kecamatan="+ kcmtn;
                        dataAdapter.clear();
                        recyclerViewadapter.notifyDataSetChanged();
                        JSON_DATA_WEB_CALL();
                        rView.setAdapter(recyclerViewadapter);
                        if (dataAdapter.size()==0){
                            Toast.makeText(getBaseContext(),"Tidak ada yang dicari",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 6:
                        ktgr = "Jasa";
                        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/include/ucok_json.php?kategori="+ktgr+"&kecamatan="+ kcmtn;
                        dataAdapter.clear();
                        recyclerViewadapter.notifyDataSetChanged();
                        JSON_DATA_WEB_CALL();
                        rView.setAdapter(recyclerViewadapter);
                        if (dataAdapter.size()==0){
                            Toast.makeText(getBaseContext(),"Tidak ada yang dicari",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 7:
                        ktgr = "Lain-lain";
                        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/include/ucok_json.php?kategori="+ktgr+"&kecamatan="+ kcmtn;
                        dataAdapter.clear();
                        recyclerViewadapter.notifyDataSetChanged();
                        JSON_DATA_WEB_CALL();
                        rView.setAdapter(recyclerViewadapter);
                        if (dataAdapter.size()==0){
                            Toast.makeText(getBaseContext(),"Tidak ada yang dicari",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //NULL
            }
        });

        /*-------------------------- START SPINER SORTBY --------------------------------------- */
        sortby = (Spinner)findViewById(R.id.sortby);
        ArrayAdapter<String> adapter_sort = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,sort);

        adapter_sort.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortby.setAdapter(adapter_sort);
        sortby.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        if (kcmtn != ""){
                            kcmtn ="";
                            GET_JSON_DATA_HTTP_URL = "http://hidepok.id/include/ucok_json.php?kategori="+ktgr+"&kecamatan="+ kcmtn;
                            dataAdapter.clear();
                            recyclerViewadapter.notifyDataSetChanged();
                            JSON_DATA_WEB_CALL();
                            rView.setAdapter(recyclerViewadapter);
                        }
                        break;
                    case 1:
                        kcmtn = "Beji";
                        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/include/ucok_json.php?kategori="+ktgr+"&kecamatan="+ kcmtn;
                        dataAdapter.clear();
                        recyclerViewadapter.notifyDataSetChanged();
                        JSON_DATA_WEB_CALL();
                        rView.setAdapter(recyclerViewadapter);
                        if (dataAdapter.size()==0){
                            Toast.makeText(getBaseContext(),"Tidak ada yang dicari",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 2:
                        kcmtn = "Cilodong";
                        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/include/ucok_json.php?kategori="+ktgr+"&kecamatan="+ kcmtn;
                        dataAdapter.clear();
                        recyclerViewadapter.notifyDataSetChanged();
                        JSON_DATA_WEB_CALL();
                        rView.setAdapter(recyclerViewadapter);
                        if (dataAdapter.size()==0){
                            Toast.makeText(getBaseContext(),"Tidak ada yang dicari",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 3:
                        kcmtn = "Cimanggis";
                        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/include/ucok_json.php?kategori="+ktgr+"&kecamatan="+ kcmtn;
                        dataAdapter.clear();
                        recyclerViewadapter.notifyDataSetChanged();
                        JSON_DATA_WEB_CALL();
                        rView.setAdapter(recyclerViewadapter);
                        if (dataAdapter.size()==0){
                            Toast.makeText(getBaseContext(),"Tidak ada yang dicari",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 4:
                        kcmtn = "Cinere";
                        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/include/ucok_json.php?kategori="+ktgr+"&kecamatan="+ kcmtn;
                        dataAdapter.clear();
                        recyclerViewadapter.notifyDataSetChanged();
                        JSON_DATA_WEB_CALL();
                        rView.setAdapter(recyclerViewadapter);
                        if (dataAdapter.size()==0){
                            Toast.makeText(getBaseContext(),"Tidak ada yang dicari",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 5:
                        kcmtn = "Cipayung";
                        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/include/ucok_json.php?kategori="+ktgr+"&kecamatan="+ kcmtn;
                        dataAdapter.clear();
                        recyclerViewadapter.notifyDataSetChanged();
                        JSON_DATA_WEB_CALL();
                        rView.setAdapter(recyclerViewadapter);
                        if (dataAdapter.size()==0){
                            Toast.makeText(getBaseContext(),"Tidak ada yang dicari",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 6:
                        kcmtn = "Limo";
                        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/include/ucok_json.php?kategori="+ktgr+"&kecamatan="+ kcmtn;
                        dataAdapter.clear();
                        recyclerViewadapter.notifyDataSetChanged();
                        JSON_DATA_WEB_CALL();
                        rView.setAdapter(recyclerViewadapter);
                        if (dataAdapter.size()==0){
                            Toast.makeText(getBaseContext(),"Tidak ada yang dicari",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 7:
                        kcmtn = "Pancoran%20Mas";
                        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/include/ucok_json.php?kategori="+ktgr+"&kecamatan="+ kcmtn;
                        dataAdapter.clear();
                        recyclerViewadapter.notifyDataSetChanged();
                        JSON_DATA_WEB_CALL();
                        rView.setAdapter(recyclerViewadapter);
                        if (dataAdapter.size()==0){
                            Toast.makeText(getBaseContext(),"Tidak ada yang dicari",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 8:
                        kcmtn = "Sawangan";
                        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/include/ucok_json.php?kategori="+ktgr+"&kecamatan="+ kcmtn;
                        dataAdapter.clear();
                        recyclerViewadapter.notifyDataSetChanged();
                        JSON_DATA_WEB_CALL();
                        rView.setAdapter(recyclerViewadapter);
                        if (dataAdapter.size()==0){
                            Toast.makeText(getBaseContext(),"Tidak ada yang dicari",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 9:
                        kcmtn = "Tapos";
                        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/include/ucok_json.php?kategori="+ktgr+"&kecamatan="+ kcmtn;
                        dataAdapter.clear();
                        recyclerViewadapter.notifyDataSetChanged();
                        JSON_DATA_WEB_CALL();
                        rView.setAdapter(recyclerViewadapter);
                        if (dataAdapter.size()==0){
                            Toast.makeText(getBaseContext(),"Tidak ada yang dicari",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 10:
                        kcmtn = "Bojongsari";
                        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/include/ucok_json.php?kategori="+ktgr+"&kecamatan="+ kcmtn;
                        dataAdapter.clear();
                        recyclerViewadapter.notifyDataSetChanged();
                        JSON_DATA_WEB_CALL();
                        rView.setAdapter(recyclerViewadapter);
                        if (dataAdapter.size()==0){
                            Toast.makeText(getBaseContext(),"Tidak ada yang dicari",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 11:
                        kcmtn = "Sukmajaya";
                        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/include/ucok_json.php?kategori="+ktgr+"&kecamatan="+ kcmtn;
                        dataAdapter.clear();
                        recyclerViewadapter.notifyDataSetChanged();
                        JSON_DATA_WEB_CALL();
                        rView.setAdapter(recyclerViewadapter);
                        if (dataAdapter.size()==0){
                            Toast.makeText(getBaseContext(),"Tidak ada yang dicari",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //NULL
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                ucok_content.this.finish();
                return true;
//            case R.id.action_share:
            default:
                return super.onOptionsItemSelected(item);
        }
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
        for(int i = 0; i<array.length(); i++) {
            GetDataAdapter_siumkm dataFromJSON = new GetDataAdapter_siumkm();
            JSONObject json = null;
            try {
                json = array.getJSONObject(i);
                dataFromJSON.setId_barang(json.getString(JSON_ID_BARANG));
                dataFromJSON.setId_ukm(json.getString(JSON_ID_UKM));
                dataFromJSON.setNama_ukm(json.getString(JSON_NAMA_UKM));
                dataFromJSON.setNama_owner_ukm(json.getString(JSON_NAMA_OWNER_UKM));
                dataFromJSON.setNama_barang(json.getString(JSON_NAMA_BARANG));
                dataFromJSON.setKategori(json.getString(JSON_KATEGORI));
                dataFromJSON.setHarga_barang(json.getString(JSON_HARGA_BARANG));
                dataFromJSON.setAlamat_ukm(json.getString(JSON_ALAMAT_UKM));
                dataFromJSON.setNotelp(json.getString(JSON_NO_TLP));
                dataFromJSON.setKecamatan(json.getString(JSON_KECAMATAN));
                dataFromJSON.setDeskripsi_ukm(json.getString(JSON_DESC_UKM));
                dataFromJSON.setKoordinat_ukm_1(json.getString(JSON_KORDINAT_1));
                dataFromJSON.setKoordinat_ukm_2(json.getString(JSON_KORDINAT_2));
                dataFromJSON.setFoto_barang(json.getString(JSON_FOTO_BARANG));
                dataFromJSON.setFoto_ukm(json.getString(JSON_FOTO_UKM));

            } catch (JSONException e) {

                e.printStackTrace();
            }
            dataAdapter.add(dataFromJSON);
        }
        recyclerViewadapter = new RecyclerViewAdapterJSON_siumkm(dataAdapter, this);
        rView.setAdapter(recyclerViewadapter);
    }
}