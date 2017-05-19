package com.hi_depok.hi_depok.Sikepok_RS;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.Akses;
import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.SessionManager;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class detail_post extends BaseActivity {
    private SearchView searchView;

    //inisialisasi simpan komentar
    EditText komen;
    Button kirimKomen;
    Calendar dateAndTime = Calendar.getInstance();
    SimpleDateFormat sdf_tanggal = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdf_waktu = new SimpleDateFormat("HH:mm:ss");
    String Komen, Tanggal, Waktu;
    String STRTanggal, STRWaktu;

    String KIRIM_KOMENTAR_URL = "http://hidepok.id/android/sikepok/1.2/sikepokrs_savekomen_json.php";

    String SUKA_URL = "http://hidepok.id/android/sikepok/1.2/sikepokrs_savesuka_json.php";

    //inisialisasi post
    String GET_JSON_DATA_HTTP_URL;
    String JSON_JUDUL_POST = "judul_post";
    String JSON_ISI_POST = "isi_post";
    String JSON_ANGKA_KOMEN = "total_komentar";
    String JSON_ANGKA_SUKA = "total_suka";
    String JSON_NAMA_USER = "nama_user";

    String JSON_ID_POST = "id_post";
    String JSON_ID_MODUL = "id_modul";
    String JSON_ID_USER = "id_user";
    String JSON_TANGGAL_POST = "tanggal_post";
    String JSON_WAKTU_POST = "waktu_post";
    String JSON_KATEGORI_POST = "kategori_post";
    String JSON_FOTO_POST = "foto_post";
    String JSON_DESKRIPSI_POST = "deskripsi_post";
    String JSON_LOKASI_POST = "lokasi_post";
    String JSON_NO_IDENTITAS_POST = "no_identitas_post";
    String JSON_STATUS_POST = "status_post";
    String JSON_RATING_POST = "rating_post";

    //inisialisasi tampilkan kometar
    String GET_JSON_DATA_HTTP_KOMENTAR;
    String JSON_ID_KOMENTAR = "id_komentar";
    String JSON_ISI_KOMENTAR = "isi_komentar";
    String JSON_TANGGAL_KOMENTAR = "tanggal_komentar";
    String JSON_WAKTU_KOMENTAR = "waktu_komentar";
    String JSON_NAMA_KOMENTATOR = "nama_user";

    JsonArrayRequest jsonArrayRequest ;
    List<GetDataAdapter> dataAdapter;
    RequestQueue requestQueue ;
    ProgressDialog dialog;
    TextView nama, judul, isi, angka_suka, angka_komen;
    ImageView  gambar_suka;
    SessionManager session;
//    ImageView image;
    String idPost;

    RecyclerView.Adapter recyclerViewadapter;
    RecyclerView rViewKOMEN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sikepokrs_detail_post);
        super.onCreateDrawer();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar3));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //inisialisasi data kirim komen
        komen = (EditText) findViewById(R.id.tulis_komentar);
        kirimKomen = (Button) findViewById(R.id.kirim_komentar);
        updateLabel();

        //inisialisasi adapter
        dataAdapter = new ArrayList<>();

        //inisialisasi tampilan di xml
        nama = (TextView) findViewById(R.id.pengirim_post);
        judul = (TextView) findViewById(R.id.judul_post);
        isi = (TextView) findViewById(R.id.isi_post);
        angka_komen = (TextView) findViewById(R.id.angka_komen);
        angka_suka = (TextView) findViewById(R.id.angka_like);
        gambar_suka = (ImageView) findViewById(R.id.gambar_suka);

//        image = (ImageView) findViewById(R.id.fotoDokter);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading");
        dialog.show();
        dialog.setCancelable(true);

        //recycler view
        rViewKOMEN = (RecyclerView) findViewById(R.id.recyclerviewpostkomen);
        rViewKOMEN.setLayoutManager(new LinearLayoutManager(this));

        dataAdapter = new ArrayList<>();
//        dialog = new ProgressDialog(this);
//        dialog.setMessage("Loading");
//        dialog.show();
//        dialog.setCancelable(true);

        //like
        gambar_suka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session = new SessionManager(detail_post.this);
                HashMap<String, String> user = session.getUserDetails();
                final String id_user = user.get(SessionManager.KEY_ID_USER);
                StringRequest likeRequest = new StringRequest(Request.Method.POST, SUKA_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.equals("belum")){
                                    gambar_suka.setImageResource(R.drawable.icon_thump_up_active);
                                    Toast.makeText(detail_post.this, "Anda menyukai post ini",
                                            Toast.LENGTH_SHORT).show();
                                }else
                                    Toast.makeText(detail_post.this, "Anda telah menyukai post ini",
                                            Toast.LENGTH_SHORT).show();
                                JSON_DATA_WEB_CALL();
//                                callVolley(id_post);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put("id_post", idPost);
                        params.put("id_user", id_user);
                        params.put("id", "1");
                        return params;
                    }
                };
                Akses.getInstance(detail_post.this).addtoRequestQueue(likeRequest);
            }
        });

        //shared preferences
        final SharedPreferences prefsa = PreferenceManager.getDefaultSharedPreferences(this);
        idPost = prefsa.getString("id_post","No data found");

        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/android/sikepok/1.2/sikepokrs_forum_json.php?detail_post=" + idPost;

        JSON_DATA_WEB_CALL();


        //recycler view
        rViewKOMEN = (RecyclerView) findViewById(R.id.recyclerviewpostkomen);
        rViewKOMEN.setLayoutManager(new LinearLayoutManager(this));

        dataAdapter = new ArrayList<>();
//        dialog = new ProgressDialog(this);
//        dialog.setMessage("Loading");
//        dialog.show();
//        dialog.setCancelable(true);

        GET_JSON_DATA_HTTP_KOMENTAR = "http://hidepok.id/android/sikepok/1.2/sikepokrs_forum_json.php?komentar=" + idPost;

        JSON_DATA_WEB_CALL2();

        //kirim.setOnClickListener(this); //Set on click Get
        kirimKomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Komen = komen.getText().toString();
                Tanggal = STRTanggal.toString();
                Waktu = STRWaktu.toString();

                if(Komen.equals("")){
                    isi.setError("Komentar harus diisi terlebih dahulu");
                }else{
                    final ProgressDialog loading = ProgressDialog.show(detail_post.this,
                            "Proses...", "Tunggu sebentar...", false, false);
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, KIRIM_KOMENTAR_URL,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    loading.dismiss();
                                    Toast.makeText(detail_post.this, response, Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(detail_post.this, detail_post.class));
                                    finish();

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(detail_post.this, error.getMessage().toString(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            SessionManager session = new SessionManager(detail_post.this);

                            HashMap<String, String> user = session.getUserDetails();
                            String id = user.get(SessionManager.KEY_ID_USER);

                            HashMap<String, String> params = new HashMap<String, String>();

                            params.put("id_user", id);
                            params.put("isi_komentar", Komen);
                            params.put("tanggal_komentar", Tanggal);
                            params.put("waktu_komentar", Waktu);
                            params.put("id_post", idPost);

                            return params;
                        }
                    };
                    Akses.getInstance(detail_post.this).addtoRequestQueue(stringRequest);
                }
            }
        });
    }

    //Update Label
    private void updateLabel(){
//        Tanggal.setText(
//                sdf_tanggal.format(dateAndTime.getTime())
//        );
//        Waktu.setText(
//                sdf_waktu.format(dateAndTime.getTime())
//        );

        STRTanggal = sdf_tanggal.format(dateAndTime.getTime());
        STRWaktu = sdf_waktu.format(dateAndTime.getTime());
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
        session = new SessionManager(detail_post.this);
        HashMap<String, String> user = session.getUserDetails();
        final String id_user = user.get(SessionManager.KEY_ID_USER);

        for(int i = 0; i<array.length(); i++) {
            JSONObject json = null;
            try {
                json = array.getJSONObject(i);

                nama.setText(json.getString(JSON_NAMA_USER));
                judul.setText(json.getString(JSON_JUDUL_POST));
                isi.setText(json.getString(JSON_ISI_POST));
                angka_komen.setText(json.getString(JSON_ANGKA_KOMEN));
                angka_suka.setText(json.getString(JSON_ANGKA_SUKA));
                gambar_suka.setImageResource(R.drawable.icon_thump_up);

                if (json.getString(JSON_ANGKA_SUKA).equals("0")) {
                    gambar_suka.setImageResource(R.drawable.icon_thump_up);
                } else {
                    gambar_suka.setImageResource(R.drawable.icon_thump_up_active);
                }


//                String encodeUrl = URLEncoder.encode(json.getString(JSON_FOTO_DOKTER));
//
//                String urlPhoto = "http://hidepok.id/assets/images/photos/sikepok/sikepok2/" + encodeUrl;
//
//                Picasso.with(this).load(urlPhoto).into(image);

            } catch (JSONException e) {

                e.printStackTrace();
            }
        }

    }

    //r view 2
    public void JSON_DATA_WEB_CALL2() {
        jsonArrayRequest = new JsonArrayRequest(GET_JSON_DATA_HTTP_KOMENTAR,
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

    public void JSON_PARSE_DATA_AFTER_WEBCALL2(JSONArray array) {
        for (int i = 0; i < array.length(); i++) {
            GetDataAdapter dataFromJSON = new GetDataAdapter();
            JSONObject json = null;
            try {
                json = array.getJSONObject(i);

                dataFromJSON.setId_komentar(json.getString(JSON_ID_KOMENTAR));
                dataFromJSON.setIsi_komentar(json.getString(JSON_ISI_KOMENTAR));
                dataFromJSON.setTanggal_komentar(json.getString(JSON_TANGGAL_KOMENTAR));
                dataFromJSON.setWaktu_komentar(json.getString(JSON_WAKTU_KOMENTAR));
                dataFromJSON.setNama_user(json.getString(JSON_NAMA_KOMENTATOR));

            } catch (JSONException e) {

                e.printStackTrace();
            }
            dataAdapter.add(dataFromJSON);
        }
        recyclerViewadapter = new RecyclerViewAdapterKOMENTAR(dataAdapter, this);
        rViewKOMEN.setAdapter(recyclerViewadapter);

    }

    //ddl
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
                    Toast.makeText(detail_post.this, query, Toast.LENGTH_SHORT).show();
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

                detail_post.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
