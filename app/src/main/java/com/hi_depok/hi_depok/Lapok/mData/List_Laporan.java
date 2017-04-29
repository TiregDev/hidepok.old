package com.hi_depok.hi_depok.Lapok.mData;

import android.content.Context;
import android.util.Log;
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
import com.hi_depok.hi_depok.Akses;
import com.hi_depok.hi_depok.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Azmi Muhammad on 3/17/2017.
 */

public class List_Laporan {
    Context mContext;
    String getContent_url = "http://hidepok.id/android/lapok/lapok_getContent.php";
    ArrayList<Laporan> laporan = new ArrayList<>();

    public List_Laporan(Context context) {
        mContext = context;
    }

    public interface listLaporan_interface {
        public void onGetList(ArrayList<Laporan> laporanArrayList);
    }

    listLaporan_interface mListLaporan_interface;

    public void registerForListener(listLaporan_interface mListLaporan_interface) {
        this.mListLaporan_interface = mListLaporan_interface;
    }

    public void getLaporan(final String kategori, final String status) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, getContent_url,
                "", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    Log.e("Response getLaporan", "" + response);
                    for (int i = 0; i < response.length(); i++) {
                        Laporan mLaporan = new Laporan();
                        JSONObject jsonObject = response.getJSONObject(i);
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
                        laporan.add(mLaporan);
                    }
                    mListLaporan_interface.onGetList(laporan);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(mContext, "Percobaan koneksi gagal! Cek koneksi anda!",
                            Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(mContext, "Gagal terhubung! Cek koneksi anda!",
                            Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(mContext, "Gagal terhubung dengan server!",
                            Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    Toast.makeText(mContext, "Gagal terhubung! Cek koneksi anda!",
                            Toast.LENGTH_SHORT).show();
                }
                Log.e("Response getLaporan", "" + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("kategori", kategori);
                params.put("urutan", status);
                return params;
            }
        };
        Akses.getInstance(mContext).addtoRequestQueue(jsonArrayRequest);

    }
}
