package com.hi_depok.hi_depok.Fokopok;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.Adapter_Komentar;
import com.hi_depok.hi_depok.Akses;
import com.hi_depok.hi_depok.Komentar;
import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class detail_post_fokopok extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener{
    TextView namaKomunitas, timeTxt, title, jml_like, jml_com;
    ImageView gambar_komunitas, gambar_event, likeimg, comimg, btnKirim, statimg, shareimg;
    EditText etKomentar;
    SessionManager session;
    List<Komentar> mList = new ArrayList<Komentar>();
    Adapter_Komentar adapter;
    SwipeRefreshLayout swipe;
    String id_artikel;
    String detail_url = "http://hidepok.id/android/fokopok/fokopok_artikel_detail.php";
    String url = "http://hidepok.id/android/fokopok/fokopok_komentar.php?id=";
    String insert = "http://hidepok.id/android/fokopok/fokopok_insert.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_komentar);
        super.onCreateDrawer();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        session = new SessionManager(this);
        HashMap<String, String> user = session.getUserDetails();
        final String id_user_komentar = user.get(SessionManager.KEY_ID_USER);

        //komentar
        ListView listView = (ListView) findViewById(R.id.listKomentar);
        adapter = new Adapter_Komentar(this, R.layout.lapok_isi_komentar, mList);
        listView.setAdapter(adapter);

        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout_komentar);
        swipe.setOnRefreshListener(this);

        //inisialisasi View
        btnKirim = (ImageView) findViewById(R.id.btnKirim);
        etKomentar = (EditText) findViewById(R.id.isiKomentar);
        namaKomunitas = (TextView) findViewById(R.id.nameTxtdetail);
        timeTxt = (TextView) findViewById(R.id.timeTxtdetail);
        title = (TextView) findViewById(R.id.card_text_detail);
        likeimg = (ImageView) findViewById(R.id.like_detail);
        jml_like = (TextView) findViewById(R.id.jumlah_like_detail);
        jml_com = (TextView) findViewById(R.id.jumlah_comment_detail);
        gambar_komunitas = (ImageView) findViewById(R.id.imageArtist_detail);
        gambar_event = (ImageView) findViewById(R.id.card_image_detail);
        statimg = (ImageView) findViewById(R.id.status_post_detail);
        shareimg = (ImageView) findViewById(R.id.share_button_detail);
        comimg = (ImageView) findViewById(R.id.comment_button_detail);

        statimg.setVisibility(View.GONE);
        shareimg.setVisibility(View.GONE);

        Intent intent = this.getIntent();
        id_artikel = (String) intent.getExtras().get("id_artikel");

        detail_artikel(id_artikel);

        likeimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest likeRequest = new StringRequest(Request.Method.POST, insert,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.equals("belum")){
                                    likeimg.setImageResource(R.drawable.favorite);
                                    Toast.makeText(detail_post_fokopok.this, "Anda menyukai konten ini",
                                            Toast.LENGTH_SHORT).show();
                                }else
                                    Toast.makeText(detail_post_fokopok.this, "Anda telah menyukai konten ini",
                                            Toast.LENGTH_SHORT).show();

                                detail_artikel(id_artikel);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put("id_artikel", id_artikel);
                        params.put("id_user", id_user_komentar);
                        params.put("id", "2");
                        return params;
                    }
                };
                Akses.getInstance(detail_post_fokopok.this).addtoRequestQueue(likeRequest);
            }
        });

        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String komentar_user = etKomentar.getText().toString();
                if (!komentar_user.equals("")) {

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, insert,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(detail_post_fokopok.this, response, Toast.LENGTH_SHORT).show();
                                    detail_artikel(id_artikel);
                                    etKomentar.setText("");
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                                Toast.makeText(detail_post_fokopok.this, "Percobaan koneksi gagal! Cek koneksi anda!",
                                        Toast.LENGTH_SHORT).show();
                            } else if (error instanceof AuthFailureError) {
                                Toast.makeText(detail_post_fokopok.this, "Gagal terhubung! Cek koneksi anda!",
                                        Toast.LENGTH_SHORT).show();
                            } else if (error instanceof ServerError) {
                                Toast.makeText(detail_post_fokopok.this, "Gagal terhubung dengan server!",
                                        Toast.LENGTH_SHORT).show();
                            } else if (error instanceof NetworkError) {
                                Toast.makeText(detail_post_fokopok.this, "Gagal terhubung! Cek koneksi anda!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> params = new HashMap<String, String>();
                            params.put("id_artikel", id_artikel);
                            params.put("id_user", id_user_komentar);
                            params.put("isi_komentar", komentar_user);
                            params.put("id", "1");
                            return params;
                        }
                    };
                    Akses.getInstance(detail_post_fokopok.this).addtoRequestQueue(stringRequest);
                } else
                    etKomentar.setError("Anda belum memberikan komentar anda");
            }
        });
    }

    //panggil artikel
    private void detail_artikel(final String id_artikel) {
        swipe.setRefreshing(true);
        String komentar_url = url + id_artikel;
        HashMap<String, String> user = session.getUserDetails();
        final String id_user_suka = user.get(SessionManager.KEY_ID_USER);
        StringRequest getArtikel = new StringRequest(Request.Method.POST, detail_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject json = jsonArray.getJSONObject(0);
                            namaKomunitas.setText(json.getString("nama_komunitas"));
                            title.setText(json.getString("isi"));
                            timeTxt.setText(json.getString("waktu_artikel"));
                            jml_like.setText(json.getString("suka"));
                            jml_com.setText(json.getString("komentar"));

                            if (!json.getString("user_like").equals("null")) {
                                String[] array = json.getString("user_like").split(", ");
                                for (int j = 0; j < array.length; j++) {
                                    if (id_user_suka.equals(array[j])) {
                                        likeimg.setImageResource(R.drawable.favorite);
                                    } else {
                                        likeimg.setImageResource(R.drawable.like);
                                    }
                                }
                            } else {
                                likeimg.setImageResource(R.drawable.like);
                            }

                            Glide.with(detail_post_fokopok.this).load("http://hidepok.id/assets/images/photos/fokopok/" +
                                    json.getString("foto_artikel"))
                                    .placeholder(R.drawable.image_placeholder).thumbnail(0.3f).into(gambar_event);
                            Glide.with(detail_post_fokopok.this).load("http://hidepok.id/assets/images/photos/fokopok/" +
                                    json.getString("foto_komunitas"))
                                    .placeholder(R.drawable.image_placeholder).thumbnail(0.3f).into(gambar_komunitas);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("id_artikel", id_artikel);
                return param;
            }
        };

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, komentar_url, "",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject obj_komentar = response.getJSONObject(i);
                                Komentar komentar = new Komentar();
                                komentar.setNama(obj_komentar.getString("nama_user"));
                                komentar.setIsi_komentar(obj_komentar.getString("isi_komentar_artikel"));
                                komentar.setProfpict(obj_komentar.getString("foto_user"));
                                mList.add(komentar);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        adapter.notifyDataSetChanged();
                        swipe.setRefreshing(false);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(detail_post_fokopok.this, "Percobaan koneksi gagal! Cek koneksi anda!",
                            Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(detail_post_fokopok.this, "Gagal terhubung! Cek koneksi anda!",
                            Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(detail_post_fokopok.this, "Gagal terhubung dengan server!",
                            Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    Toast.makeText(detail_post_fokopok.this, "Gagal terhubung! Cek koneksi anda!",
                            Toast.LENGTH_SHORT).show();
                }
                swipe.setRefreshing(false);
            }
        });
        Akses.getInstance(detail_post_fokopok.this).addtoRequestQueue(getArtikel);
        Akses.getInstance(detail_post_fokopok.this).addtoRequestQueue(jsonArrayRequest);
    }

    @Override
    public void onRefresh() {
        detail_artikel(id_artikel);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.action_new_msg:
//                // todo: goto back activity from here
//
//                startActivity(new Intent(getBaseContext(), message.class));
//                return true;
            case android.R.id.home:
                // todo: goto back activity from here

                detail_post_fokopok.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
