package com.hi_depok.hi_depok.Sikepok_RS;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class info_dokter extends BaseActivity {

    String GET_JSON_DATA_HTTP_URL;
    String JSON_ID_DOKTER = "id_dokter";
    String JSON_NAMA_DOKTER = "nama_dokter";
    String JSON_ALAMAT_DOKTER = "alamat_dokter";
    String JSON_NO_TELP_DOKTER = "no_telp_dokter";
    String JSON_EMAIL_DOKTER = "email_dokter";
    String JSON_SPESIALISASI = "spesialisasi";
    String JSON_FOTO_DOKTER = "foto_dokter";
    String JSON_DESKRIPSI_DOKTER = "deskripsi_dokter";
    String JSON_ID_RS ="id_rs";

    String JSON_SENIN = "senin_jadwal";
    String JSON_SELASA = "selasa_jadwal";
    String JSON_RABU = "rabu_jadwal";
    String JSON_KAMIS = "kamis_jadwal";
    String JSON_JUMAT = "jumat_jadwal";
    String JSON_SABTU = "sabtu_jadwal";

    JsonArrayRequest jsonArrayRequest ;
    List<GetDataAdapter> dataAdapter;
    RequestQueue requestQueue ;
    ProgressDialog dialog;
    TextView nama, deskripsi, spesialisasi, senin, selasa, rabu, kamis, jumat, sabtu;
    ImageView image;
    String idDokter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sikepokrs_info_dokter);
        super.onCreateDrawer();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar3));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //inisialisasi adapter
        dataAdapter = new ArrayList<>();

        //inisialisasi tampilan di xml
        nama = (TextView) findViewById(R.id.namaDokter);
        deskripsi = (TextView) findViewById(R.id.descDokter);
        senin = (TextView) findViewById(R.id.senin);
        selasa = (TextView) findViewById(R.id.selasa);
        rabu = (TextView) findViewById(R.id.rabu);
        kamis = (TextView) findViewById(R.id.kamis);
        jumat = (TextView) findViewById(R.id.jumat);
        sabtu = (TextView) findViewById(R.id.sabtu);
        spesialisasi = (TextView) findViewById(R.id.spesialisasiDokter);
        image = (ImageView) findViewById(R.id.fotoDokter);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading");
        dialog.show();
        dialog.setCancelable(true);

        //shared preferences
        final SharedPreferences prefsa = PreferenceManager.getDefaultSharedPreferences(this);
        idDokter = prefsa.getString("id_dokter","No data found");

        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/android/sikepok/1.2/sikepokrs_menurs_json.php?id_dokter=" + idDokter;

        JSON_DATA_WEB_CALL();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                info_dokter.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //parsing JSON
    public void JSON_DATA_WEB_CALL(){
        jsonArrayRequest = new JsonArrayRequest(GET_JSON_DATA_HTTP_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.v("JsonResponse", "succses");
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
            JSONObject json = null;
            try {
                json = array.getJSONObject(i);

                nama.setText(json.getString(JSON_NAMA_DOKTER));
                deskripsi.setText(json.getString(JSON_DESKRIPSI_DOKTER));
                spesialisasi.setText(json.getString(JSON_SPESIALISASI));
                senin.setText(json.getString(JSON_SENIN));
                selasa.setText(json.getString(JSON_SELASA));
                rabu.setText(json.getString(JSON_RABU));
                kamis.setText(json.getString(JSON_KAMIS));
                jumat.setText(json.getString(JSON_JUMAT));
                sabtu.setText(json.getString(JSON_SABTU));

                String encodeUrl = URLEncoder.encode(json.getString(JSON_FOTO_DOKTER));

                String urlPhoto = "http://hidepok.id/assets/images/photos/sikepok/sikepok2/" + encodeUrl;

                Picasso.with(this).load(urlPhoto).into(image);

            } catch (JSONException e) {

                e.printStackTrace();
            }
        }


    }
}
