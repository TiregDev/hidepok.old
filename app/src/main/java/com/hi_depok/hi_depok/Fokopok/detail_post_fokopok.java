package com.hi_depok.hi_depok.Fokopok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
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

public class detail_post_fokopok extends AppCompatActivity {
    TextView namaKomunitas, timeTxt, title, jml_like, jml_com;
    ImageView gambar_komunitas, gambar_event, likeimg, comimg, btnKirim, statimg, shareimg;
    EditText etKomentar;
    EditText comment;
    SessionManager session;
    List<Komentar> mList = new ArrayList<Komentar>();
    Adapter_Komentar adapter;
    String detail_url = "http://hidepok.id/android/fokopok/fokopok_artikel_detail.php";
    String url = "http://hidepok.id/android/lapok/lapok_komentar.php?id=";
    String insert = "http://hidepok.id/android/lapok/lapok_insert.php";
    NotificationCompat.Builder mBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lapok_detail_komentar);

        btnKirim = (ImageView) findViewById(R.id.btnKirim);
        etKomentar = (EditText) findViewById(R.id.isiKomentar);
        namaKomunitas = (TextView) findViewById(R.id.nameTxtdetail);
        timeTxt = (TextView) findViewById(R.id.timeTxtdetail);
        title = (TextView) findViewById(R.id.card_text_detail);
        jml_like = (TextView) findViewById(R.id.jumlah_like_detail);
        jml_com = (TextView) findViewById(R.id.jumlah_comment_detail);
        gambar_komunitas = (ImageView) findViewById(R.id.imageArtist_detail);
        gambar_event = (ImageView) findViewById(R.id.card_image_detail);
        statimg = (ImageView) findViewById(R.id.status_post_detail);
        shareimg = (ImageView) findViewById(R.id.share_button_detail);

        statimg.setVisibility(View.GONE);
        shareimg.setVisibility(View.GONE);

        Intent intent = this.getIntent();
        String id_artikel = (String) intent.getExtras().get("id_artikel");

        detail_artikel(id_artikel);
    }

    private void detail_artikel(final String id_artikel) {
        session = new SessionManager(detail_post_fokopok.this);
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
        Akses.getInstance(detail_post_fokopok.this).addtoRequestQueue(getArtikel);
    }
}
