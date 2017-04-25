package com.hi_depok.hi_depok.Lapok.mData;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
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

    public List_Laporan(Context context) {
        mContext = context;
    }

    public ArrayList<Laporan> getLaporan(final String kategori, final String sortby) {
        final ArrayList<Laporan> laporan = new ArrayList<>();
        final Laporan mLaporan = new Laporan();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "url",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String code = jsonObject.getString("code");
                            if (code.equals("not_available")) {
                                Toast.makeText(mContext, jsonObject.getString("message"),
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                mLaporan.setName(jsonObject.getString("nama"));
                                mLaporan.setImage(jsonObject.getString("avatar"));
                                mLaporan.setTanggal(jsonObject.getString("tanggal"));
                                mLaporan.setWaktu(jsonObject.getString("waktu"));
                                mLaporan.setKejadian(jsonObject.getString("foto"));
                                mLaporan.setJudul(jsonObject.getString("judul"));
                                mLaporan.setComment_imgbtn(R.drawable.comment);
                                mLaporan.setLike_imgbtn(R.drawable.like);
                                mLaporan.setShare_imgbtn(R.drawable.share);
                                mLaporan.setJml_com(jsonObject.getString("komentar"));
                                mLaporan.setJml_like(jsonObject.getString("suka"));

                                mLaporan.setStatus(jsonObject.getString("status"));
                                mLaporan.setId(jsonObject.getString("id_post"));
                                mLaporan.setIsi(jsonObject.getString("isi"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        laporan.add(mLaporan);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("kategori", kategori);
                params.put("urutan", sortby);
                return params;
            }
        };
        Akses.getInstance(mContext).addtoRequestQueue(stringRequest);

        return laporan;
    }
}
