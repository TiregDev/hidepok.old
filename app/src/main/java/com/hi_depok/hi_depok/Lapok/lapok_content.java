package com.hi_depok.hi_depok.Lapok;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
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
import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.Akses;
import com.hi_depok.hi_depok.Lapok.mData.Laporan;
import com.hi_depok.hi_depok.Lapok.mRecyler.Adapter_Laporan;
import com.hi_depok.hi_depok.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class lapok_content extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private Spinner category, sortby;
    String kategori = "Pilih%20Kategori" , status = "Pilih%20Status";
    Adapter_Laporan mAdapter;
    ArrayList<Laporan> mList = new ArrayList<>();
    SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView rv;
    private LinearLayoutManager lLayout;

    private static final String[] cate = {"Pilih Kategori", "Sampah", "Kebakaran", "Kemacetan",
            "Bencana Alam", "Pelanggaran", "Jalan Rusak", "Tindak Kriminal", "Terorisme", "Narkoba"};
    private static final String[] stats = {"Pilih Status", "Menunggu", "Proses", "Selesai"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lapok_content);
        super.onCreateDrawer();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView cameraBtn = (ImageView) findViewById(R.id.cameraBtn);
        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(lapok_content.this, lapok_ambil_kejadian.class));
            }
        });

        lLayout = new LinearLayoutManager(this);
        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(lLayout);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);

        mAdapter = new Adapter_Laporan(this, mList);
        rv.setAdapter(mAdapter);

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.post(new Runnable() {
                                     @Override
                                     public void run() {
                                         kategori = "Pilih%20Kategori";
                                         status = "Pilih%20Status";
                                         mSwipeRefreshLayout.setRefreshing(true);
                                         mList.clear();
                                         mAdapter.notifyDataSetChanged();
                                         callVolley(kategori, status);
                                     }
                                 }
        );


        /*-------------------------- START SPINER CATEGORY --------------------------------------- */
        category = (Spinner) findViewById(R.id.category);
        ArrayAdapter<String> adapter_cate = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, cate);

        adapter_cate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter_cate);
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                kategori = adapterView.getItemAtPosition(position).toString();
                if (!kategori.equals("Pilih Kategori")) {
                    Toast.makeText(lapok_content.this, kategori + " selected", Toast.LENGTH_LONG).show();
                }
                if (kategori.contains(" ") || status.contains(" ")) {
                    String newKategori = kategori.replace(" ", "%20");
                    String newStatus = status.replace(" ", "%20");
                    callVolley(newKategori, newStatus);
                }
                else
                    callVolley(kategori, status);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
         /*-------------------------- END SPINER CATEGORY ---------------------------------------- */

        /*-------------------------- START SPINER SORTBY --------------------------------------- */
        sortby = (Spinner) findViewById(R.id.sortby);
        ArrayAdapter<String> adapter_sort = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, stats);

        adapter_sort.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortby.setAdapter(adapter_sort);


        sortby.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                status = adapterView.getItemAtPosition(position).toString();
                if (!status.equals("Pilih Status")) {
                    Toast.makeText(lapok_content.this, status + " selected", Toast.LENGTH_LONG).show();
                }
                if (kategori.contains(" ") || status.contains(" ")) {
                    String newKategori = kategori.replace(" ", "%20");
                    String newStatus = status.replace(" ", "%20");
                    callVolley(newKategori, newStatus);
                }
                else
                    callVolley(kategori, status);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                lapok_content.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onRefresh() {
        kategori = "Pilih%20Kategori";
        status = "Pilih%20Status";
        mList.clear();
        mAdapter.notifyDataSetChanged();
        callVolley(kategori, status);
        Toast.makeText(lapok_content.this, "Pilih kembali kategori dan status",
                Toast.LENGTH_SHORT).show();
        category.setSelection(0);
        sortby.setSelection(0);
    }

    private void callVolley(final String param1, final String param2) {
        String getContent_url = "http://hidepok.id/android/lapok/lapok_getContent.php?kategori=" + param1 + "&status=" + param2;
        mList.clear();
        mAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(true);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, getContent_url,
                "", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        Laporan mLaporan = new Laporan();
                        mLaporan.setName(jsonObject.getString("nama"));
                        mLaporan.setImage(jsonObject.getString("avatar"));
                        mLaporan.setTanggal(jsonObject.getString("tanggal"));
                        mLaporan.setWaktu(jsonObject.getString("waktu"));
                        mLaporan.setKejadian(jsonObject.getString("foto"));
                        mLaporan.setJudul(jsonObject.getString("judul"));
                        mLaporan.setJml_com(jsonObject.getString("komentar"));
                        mLaporan.setJml_like(jsonObject.getString("suka"));

                        mLaporan.setComment_imgbtn(R.drawable.comment);
                        mLaporan.setLike_imgbtn(R.drawable.like);
                        mLaporan.setShare_imgbtn(R.drawable.share);

                        mLaporan.setStatus(jsonObject.getString("status"));
                        mLaporan.setId(jsonObject.getString("id_post"));
                        mLaporan.setIsi(jsonObject.getString("isi"));
                        mList.add(mLaporan);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                mAdapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(lapok_content.this, "Percobaan koneksi gagal! Cek koneksi anda!",
                            Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(lapok_content.this, "Gagal terhubung! Cek koneksi anda!",
                            Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(lapok_content.this, "Gagal terhubung dengan server!",
                            Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    Toast.makeText(lapok_content.this, "Gagal terhubung! Cek koneksi anda!",
                            Toast.LENGTH_SHORT).show();
                }
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("kategori", param1);
                params.put("urutan", param2);

                return params;
            }
        };
        Akses.getInstance(lapok_content.this).addtoRequestQueue(jsonArrayRequest);
    }
}
