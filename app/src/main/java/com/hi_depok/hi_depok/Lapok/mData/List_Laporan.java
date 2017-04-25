package com.hi_depok.hi_depok.Lapok.mData;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hi_depok.hi_depok.Akses;

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

    public ArrayList<Laporan> getLaporan(final String kategori, final String sortby){
        ArrayList<Laporan> laporan = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "url",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);

                            //String judul = jsonObject.getString("id_post")
                            //mLaporan.setJudul(judul)
                            //etc...
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
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

        /*//ADD DATA TO LIST
        mLaporan = new Laporan();
        mLaporan.setName("Emma Roberts");
        mLaporan.setImage(R.drawable.emma_roberts);
        mLaporan.setWaktu("Feb 8, 2017, at 17.00 WIB");
        mLaporan.setKejadian(R.drawable.kemacetan);
        mLaporan.setJudul("Macet di Gerbatama UI Bikin Bete...");
        mLaporan.setComment_imgbtn(R.drawable.comment);
        mLaporan.setLike_imgbtn(R.drawable.like);
        mLaporan.setShare_imgbtn(R.drawable.share);
        mLaporan.setJml_com("0");
        mLaporan.setJml_like("0");
        mLaporan.setJml_share("0");
        laporan.add(mLaporan);

        mLaporan = new Laporan();
        mLaporan.setName("Lea Seydoux");
        mLaporan.setImage(R.drawable.lea_seydoux);
        mLaporan.setWaktu("Feb 8, 2017, at 17.00 WIB");
        mLaporan.setKejadian(R.drawable.kebakaran);
        mLaporan.setJudul("Tolong!!! Kebakaran nih di Detos");
        mLaporan.setComment_imgbtn(R.drawable.comment);
        mLaporan.setLike_imgbtn(R.drawable.like);
        mLaporan.setShare_imgbtn(R.drawable.share);
        mLaporan.setJml_com("0");
        mLaporan.setJml_like("0");
        mLaporan.setJml_share("0");
        laporan.add(mLaporan);

        mLaporan = new Laporan();
        mLaporan.setName("Margot Robbie");
        mLaporan.setImage(R.drawable.margot_robbie);
        mLaporan.setWaktu("Feb 8, 2017, at 17.00 WIB");
        mLaporan.setKejadian(R.drawable.banjir);
        mLaporan.setJudul("Duh mana banjir, ujan, becek, gaada ojek...");
        mLaporan.setComment_imgbtn(R.drawable.comment);
        mLaporan.setLike_imgbtn(R.drawable.like);
        mLaporan.setShare_imgbtn(R.drawable.share);
        mLaporan.setJml_com("0");
        mLaporan.setJml_like("0");
        mLaporan.setJml_share("0");
        laporan.add(mLaporan);

        mLaporan = new Laporan();
        mLaporan.setName("Oksana Neveselaya");
        mLaporan.setImage(R.drawable.oksana_neveselaya);
        mLaporan.setWaktu("Feb 8, 2017, at 17.00 WIB");
        mLaporan.setKejadian(R.drawable.perampokan);
        mLaporan.setJudul("Huhu kasian abangnya dirampok :'( ...");
        mLaporan.setComment_imgbtn(R.drawable.comment);
        mLaporan.setLike_imgbtn(R.drawable.like);
        mLaporan.setShare_imgbtn(R.drawable.share);
        mLaporan.setJml_com("0");
        mLaporan.setJml_like("0");
        mLaporan.setJml_share("0");
        laporan.add(mLaporan);

        mLaporan = new Laporan();
        mLaporan.setName("Taissa Farmiga");
        mLaporan.setImage(R.drawable.taissa_farmiga);
        mLaporan.setWaktu("Feb 8, 2017, at 17.00 WIB");
        mLaporan.setKejadian(R.drawable.geng_motor);
        mLaporan.setJudul("Ihh apaan banget sih geng motor Depok :P");
        mLaporan.setComment_imgbtn(R.drawable.comment);
        mLaporan.setLike_imgbtn(R.drawable.like);
        mLaporan.setShare_imgbtn(R.drawable.share);
        mLaporan.setJml_com("0");
        mLaporan.setJml_like("0");
        mLaporan.setJml_share("0");
        laporan.add(mLaporan);
*/
    }
}
