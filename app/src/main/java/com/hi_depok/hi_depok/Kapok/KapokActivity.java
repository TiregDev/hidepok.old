package com.hi_depok.hi_depok.Kapok;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.Kadepok.KadepokDetailActivity;
import com.hi_depok.hi_depok.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KapokActivity extends BaseActivity implements AdapterView.OnItemSelectedListener  {
    private Button temukan;
    private RadioGroup sort;
    private RadioButton urut;
    //array recycleview menu sort
    ArrayList<String> namalogo = new ArrayList<>(Arrays.asList(
            "Wisata", "Warung Pancong Pak Kumis & Mang Dadang",
            "Bebek & Ayam Goreng Pak Endut", "Wisata",
            "Talaga Seafood Restaurant", "Kolam Renang Tirta Sari",
            "Waroeng SS Spesial Sambal", "Wisata",
            "Kuliner", "Wisata"));
    ArrayList<Integer> gambarlogo = new ArrayList<>(Arrays.asList(
            R.drawable.wisata, R.drawable.ucok_image_4,
            R.drawable.ucok_image_4, R.drawable.wisata,
            R.drawable.ucok_image_4, R.drawable.wisata,
            R.drawable.ucok_image_4, R.drawable.wisata,
            R.drawable.ucok_image_4, R.drawable.wisata));

    String GET_JSON_DATA_HTTP_URL = "http://hidepok.id/include/jsonData.php";
    String JSON_ID = "id_tempat";
    String JSON_ALAMAT = "alamat_tempat";
    String JSON_NAME = "nama_tempat";
    String JSON_DESKRIPSI = "deskripsi_tempat";
    String JSON_NOTLP = "no_telp_tempat";
    JsonArrayRequest jsonArrayRequest ;
    List<GetDataAdapter> dataAdapter;
    RequestQueue requestQueue ;
    RecyclerView.Adapter recyclerViewadapter;
    ProgressDialog progressDialog;
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

        Spinner pilihan = (Spinner) findViewById(R.id.pilihan);
        Spinner camat = (Spinner) findViewById(R.id.camat);
        sort=(RadioGroup)findViewById(R.id.sort);
        temukan=(Button)findViewById(R.id.temukan);
        dataAdapter = new ArrayList<>();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading ...");
        progressDialog.show();
        JSON_DATA_WEB_CALL();
        // Spinner click listener
        pilihan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 1:
                        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/include/jsonData.php?kategori=kuliner";
                        break;
                    case 2:
                        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/include/jsonData.php?kategori=wisata";
                        break;
                    case 3:
                        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/include/jsonData.php?kategori=pasar";
                        break;
                    case 4:
                        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/include/jsonData.php?kategori=tempat_ibadah";
                        break;
                    case 5:
                        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/include/jsonData.php?kategori=olahraga";
                        break;
                    default:
                        break;
                }
                progressDialog.show();
                dataAdapter.clear();
                JSON_DATA_WEB_CALL();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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

        camat.setOnItemSelectedListener(this);
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

        camat.setAdapter(camatAdapter);


        temukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId= sort.getCheckedRadioButtonId();
                urut=(RadioButton)findViewById(selectedId);
            }
        });

        //join

        //recycleview
        rView = (RecyclerView) findViewById(R.id.recyclerView);
        // set a GridLayoutManager with 2 number of columns , horizontal gravity and false value for reverseLayout to show the items from start to end
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        rView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
//        CustomAdapterMenuSort customAdapter = new CustomAdapterMenuSort(KapokActivity.this, namalogo,gambarlogo);
//        rView.setAdapter(customAdapter); // set the Adapter to RecyclerView
}
    public void JSON_DATA_WEB_CALL(){
        jsonArrayRequest = new JsonArrayRequest(GET_JSON_DATA_HTTP_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        progressDialog.dismiss();
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
                dataFromJSON.setName(json.getString(JSON_NAME));
                dataFromJSON.setFoto(gambarlogo.get(i));

            } catch (JSONException e) {

                e.printStackTrace();
            }
            dataAdapter.add(dataFromJSON);
        }

        recyclerViewadapter = new RecyclerViewAdapterJSON(dataAdapter, this);
        rView.setAdapter(recyclerViewadapter);
    }



//    //popup
//    private PopupWindow pwindo;
//
//    public void initiatepopup() {
//        try {
//            LayoutInflater inflater = (LayoutInflater) KapokActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View layout = inflater.inflate(R.layout.kapok_popup_layout, (ViewGroup) findViewById(R.id.popup_element));
//
//            pwindo = new PopupWindow(layout, 450, 750, true);
//            pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);
//            pwindo.setOutsideTouchable(true);
//            pwindo.setFocusable(true);
//            ImageView maps = (ImageView) layout.findViewById(R.id.mapsIcon);
//            maps.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    startActivity(new Intent(KapokActivity.this, MapsActivity.class));
//                }
//            });
//            Button selengkapnya = (Button) layout.findViewById(R.id.next);
//            selengkapnya.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    startActivity(new Intent(KapokActivity.this, activity_selengkapnya.class));
//                }
//            });
//            ImageView close = (ImageView) layout.findViewById(R.id.close);
//            close.setOnClickListener(cancel_button_click_listener);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    @Override
//    public void onBackPressed() {
//        if (pwindo != null) {
//            if (pwindo.isShowing()) {
//                pwindo.dismiss();
//            }
//        }
//    }
//    private View.OnClickListener cancel_button_click_listener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            pwindo.dismiss();
//        }
//    };


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }
//    //button join
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_kapok, menu);
//
//        return super.onCreateOptionsMenu(menu);
//    }
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