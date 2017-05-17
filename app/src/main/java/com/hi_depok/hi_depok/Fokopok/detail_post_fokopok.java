package com.hi_depok.hi_depok.Fokopok;

import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hi_depok.hi_depok.Adapter_Komentar;
import com.hi_depok.hi_depok.Komentar;
import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class detail_post_fokopok extends AppCompatActivity {
    TextView nsmsKomunitas, timeTxt, title, jml_like, jml_com;
    ImageView img, kej, likeimg, comimg, btnKirim, statimg;
    EditText etKomentar;
    EditText comment;
    SessionManager session;
    List<Komentar> mList = new ArrayList<Komentar>();
    SwipeRefreshLayout swipe;
    Adapter_Komentar adapter;
    String detail_url = "http://hidepok.id/android/lapok/lapok_getContent_detail.php";
    String url = "http://hidepok.id/android/lapok/lapok_komentar.php?id=";
    String insert = "http://hidepok.id/android/lapok/lapok_insert.php";
    NotificationCompat.Builder mBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_komentar);

        btnKirim = (ImageView) findViewById(R.id.btnKirim);
        etKomentar = (EditText) findViewById(R.id.isiKomentar);
        nsmsKomunitas = (TextView) findViewById(R.id.nameTxtdetail);
        timeTxt = (TextView) findViewById(R.id.timeTxtdetail);
        title = (TextView) findViewById(R.id.card_text_detail);
        jml_like = (TextView) findViewById(R.id.jumlah_like_detail);
        jml_com = (TextView) findViewById(R.id.jumlah_comment_detail);
        img = (ImageView) findViewById(R.id.imageArtist_detail);
        kej = (ImageView) findViewById(R.id.card_image_detail);
        statimg = (ImageView) findViewById(R.id.status_post_detail);
        statimg.setVisibility(View.GONE);
    }
}
