package com.hi_depok.hi_depok.Sikepok_RS;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class detail_post_saya extends BaseActivity {
    private SearchView searchView;

    //inisialisasi simpan komentar
    EditText komen;
    Button kirimKomen, hapusPost;
    Calendar dateAndTime = Calendar.getInstance();
    SimpleDateFormat sdf_tanggal = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdf_waktu = new SimpleDateFormat("HH:mm:ss");
    String Komen, Tanggal, Waktu;
    String STRTanggal, STRWaktu;

    String KIRIM_KOMENTAR_URL = "http://hidepok.id/android/sikepok/1.2/sikepokrs_savekomen_json.php";
    String DELETE_URL = "http://hidepok.id/android/sikepok/1.2/sikepokrs_delete_json.php";
    String SUKA_URL = "http://hidepok.id/android/sikepok/1.2/sikepokrs_savesuka_json.php";

    //inisialisasi
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

    //inisialisasi komentar
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
    ImageView gambar_suka;
    SessionManager session;
    //    ImageView image;
    String idPostSaya;

    RecyclerView.Adapter recyclerViewadapter;
    RecyclerView rViewKOMEN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sikepokrs_detail_post_saya);
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
        hapusPost = (Button) findViewById(R.id.hapus_post);
        updateLabel();

        //inisialisasi adapter
        dataAdapter = new ArrayList<>();

        hapusPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePost();
            }
        });

        //inisialisasi tampilan di xml
//        nama = (TextView) findViewById(R.id.pengirim_post);
        judul = (TextView) findViewById(R.id.judul_post);
        isi = (TextView) findViewById(R.id.isi_post);
        angka_komen = (TextView) findViewById(R.id.angka_komen);
        angka_suka = (TextView) findViewById(R.id.angka_like);
        gambar_suka = (ImageView) findViewById(R.id.gambar_suka);
//        image = (ImageView) findViewById(R.id.fotoDokter);
//        dialog = new ProgressDialog(this);
//        dialog.setMessage("Loading");
//        dialog.show();
//        dialog.setCancelable(true);

        //recycler view
        rViewKOMEN = (RecyclerView) findViewById(R.id.recyclerviewpostkomensaya);
        rViewKOMEN.setLayoutManager(new LinearLayoutManager(this));

        dataAdapter = new ArrayList<>();
//        dialog = new ProgressDialog(this);
//        dialog.setMessage("Loading");
//        dialog.show();
//        dialog.setCancelable(true);

        //shared preferences
        final SharedPreferences prefsa = PreferenceManager.getDefaultSharedPreferences(this);
        idPostSaya = prefsa.getString("id_post_saya","No data found");

        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/android/sikepok/1.2/sikepokrs_forum_json.php?detail_post_saya=" + idPostSaya;

        JSON_DATA_WEB_CALL();

        //recycler view
        rViewKOMEN = (RecyclerView) findViewById(R.id.recyclerviewpostkomensaya);
        rViewKOMEN.setLayoutManager(new LinearLayoutManager(this));

        dataAdapter = new ArrayList<>();
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading");
        dialog.show();
        dialog.setCancelable(true);
        GET_JSON_DATA_HTTP_KOMENTAR = "http://hidepok.id/android/sikepok/1.2/sikepokrs_forum_json.php?komentar=" + idPostSaya;

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
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, KIRIM_KOMENTAR_URL,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    dialog.dismiss();
                                    Toast.makeText(detail_post_saya.this, response, Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(detail_post_saya.this, detail_post_saya.class));
                                    finish();

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(detail_post_saya.this, error.getMessage().toString(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            SessionManager session = new SessionManager(detail_post_saya.this);

                            HashMap<String, String> user = session.getUserDetails();
                            String id = user.get(SessionManager.KEY_ID_USER);

                            HashMap<String, String> params = new HashMap<String, String>();

                            params.put("id_user", id);
                            params.put("isi_komentar", Komen);
                            params.put("tanggal_komentar", Tanggal);
                            params.put("waktu_komentar", Waktu);
                            params.put("id_post", idPostSaya);

                            return params;
                        }
                    };
                    Akses.getInstance(detail_post_saya.this).addtoRequestQueue(stringRequest);
                }
            }
        });

        //like
        gambar_suka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session = new SessionManager(detail_post_saya.this);
                HashMap<String, String> user = session.getUserDetails();
                final String id_user = user.get(SessionManager.KEY_ID_USER);

                StringRequest likeRequest = new StringRequest(Request.Method.POST, SUKA_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.equals("belum")){
                                    gambar_suka.setImageResource(R.drawable.icon_thump_up_active);
                                    Toast.makeText(detail_post_saya.this, "Anda menyukai post ini",
                                            Toast.LENGTH_SHORT).show();
                                }else
                                    Toast.makeText(detail_post_saya.this, "Anda telah menyukai post ini",
                                            Toast.LENGTH_SHORT).show();
                                JSON_DATA_WEB_CALL();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put("id_post", idPostSaya);
                        params.put("id_user", id_user);
                        params.put("id", "1");
                        return params;
                    }
                };
                Akses.getInstance(detail_post_saya.this).addtoRequestQueue(likeRequest);
            }
        });
    }

    private void deletePost() {
        StringRequest delete = new StringRequest(Request.Method.POST, DELETE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(detail_post_saya.this, response, Toast.LENGTH_SHORT).show();
                        Intent next = new Intent(detail_post_saya.this, post_saya.class);
                        next.putExtra("yang_dicari","");
                        startActivity(next);
                        finish();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("id_post", idPostSaya);
                return param;
            }
        };
        Akses.getInstance(detail_post_saya.this).addtoRequestQueue(delete);
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
        for(int i = 0; i<array.length(); i++) {
            JSONObject json = null;
            try {
                json = array.getJSONObject(i);

//                nama.setText(json.getString(JSON_NAMA_USER));
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
        recyclerViewadapter = new RecyclerViewAdapterKOMENTARSAYA(dataAdapter, this);
        rViewKOMEN.setAdapter(recyclerViewadapter);

    }

    //On Restart
    @Override
    protected void onRestart() {
        super.onRestart();
        dataAdapter.clear();
        JSON_DATA_WEB_CALL();
        JSON_DATA_WEB_CALL2();
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
//                    dataAdapter.clear();
//                    GET_JSON_DATA_HTTP_URL = "http://hidepok.id/android/sikepok/1.2/sikepokrs_menurs_json.php";
//                    JSON_DATA_WEB_CALL();
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
//                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(forum.this);
//                    SharedPreferences.Editor editor=sharedPreferences.edit();
//                        editor.putString("dicari_diforum",query);
//                    editor.commit();

                    Intent intent = new Intent(detail_post_saya.this, post_saya.class);
                    intent.putExtra("yang_dicari",query);
                    startActivity(intent);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
//                    dataAdapter.clear();
//                    GET_JSON_DATA_HTTP_URL = "http://hidepok.id/android/sikepok/1.2/sikepokrs_menurs_json.php?cari="+newText;
//                    JSON_DATA_WEB_CALL();
                    return false;
                }
            });
            SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        }

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                detail_post_saya.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

