package com.hi_depok.hi_depok.Kadepok.activity;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.Kadepok.adapter.ItemObject;
import com.hi_depok.hi_depok.Kadepok.adapter.JSONAdapter;
import com.hi_depok.hi_depok.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class kadepok_content extends BaseActivity {
    private Spinner kecamatan;
    public Button close;
    List<ItemObject> dataAdapter;
    RecyclerView.Adapter recyclerViewadapter;
    RecyclerView rView;
    String JSON_URL = "http://hidepok.id/android/kadepok/kadepok_json.php";
    private static final String[]camat = {"Pilih Kecamatan", "Beji", "Bojongsari", "Cilodong", "Cimanggis",
            "Cinere", "Cipayung", "Limo", "Pancoran Mas", "Sawangan", "Sukmajaya", "Tapos"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kadepok_recycler_view);
        super.onCreateDrawer();
        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rView = (RecyclerView)findViewById(R.id.recycler_view);
        rView.setLayoutManager(llm);
        dataAdapter = new ArrayList<>();
        JSON_VALIDATE_URL();

        /*-------------------------- START SPINER CATEGORY --------------------------------------- */
        kecamatan = (Spinner)findViewById(R.id.kecamatan);
        ArrayAdapter<String> adapter_camat = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,camat);

        adapter_camat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kecamatan.setAdapter(adapter_camat);
        kecamatan.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        Toast.makeText(getApplicationContext(), ("Anda telah memilih Beji"),
                                Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Toast.makeText(getApplicationContext(), ("Anda telah memilih Bojongsari"),
                                Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        Toast.makeText(getApplicationContext(), ("Anda telah memilih Cilodong"),
                                Toast.LENGTH_LONG).show();
                        break;
                    case 4:
                        Toast.makeText(getApplicationContext(), ("Anda telah memilih Cimanggis"),
                                Toast.LENGTH_LONG).show();
                        break;
                    case 5:
                        Toast.makeText(getApplicationContext(), ("Anda telah memilih Cinere"),
                                Toast.LENGTH_LONG).show();
                        break;
                    case 6:
                        Toast.makeText(getApplicationContext(), ("Anda telah memilih Cipayung"),
                                Toast.LENGTH_LONG).show();
                        break;
                    case 7:
                        Toast.makeText(getApplicationContext(), ("Anda telah memilih Limo"),
                                Toast.LENGTH_LONG).show();
                        break;
                    case 8:
                        Toast.makeText(getApplicationContext(), ("Anda telah memilih Pancoran Mas"),
                                Toast.LENGTH_LONG).show();
                        break;
                    case 9:
                        Toast.makeText(getApplicationContext(), ("Anda telah memilih Sawangan"),
                                Toast.LENGTH_LONG).show();
                        break;
                    case 10:
                        Toast.makeText(getApplicationContext(), ("Anda telah memilih Sukmajaya"),
                                Toast.LENGTH_LONG).show();
                        break;
                    case 11:
                        Toast.makeText(getApplicationContext(), ("Anda telah memilih Tapos"),
                                Toast.LENGTH_LONG).show();
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
                kadepok_content.this.finish();
                return true;
//            case R.id.action_share:
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    JsonArrayRequest jsonArrayRequest ;
    RequestQueue requestQueue;
    public void JSON_VALIDATE_URL(){
        jsonArrayRequest = new JsonArrayRequest(JSON_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSON_PARSING_DATA(response);
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

    public void JSON_PARSING_DATA(JSONArray array){
        for(int i = 0; i<array.length(); i++) {
            ItemObject data = new ItemObject();
            JSONObject json = null;
            try {
                json = array.getJSONObject(i);
                data.setId(json.getString("id_panti"));
                data.setName(json.getString("nama_panti"));
                data.setAlamat(json.getString("alamat_panti"));
                data.setPhoto(json.getString("foto_profile_panti"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            dataAdapter.add(data);
        }
        recyclerViewadapter = new JSONAdapter(dataAdapter, this);
        rView.setAdapter(recyclerViewadapter);
    }

}
