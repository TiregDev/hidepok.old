package com.hi_depok.hi_depok.Lapok.mDetail;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
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
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.Akses;
import com.hi_depok.hi_depok.Lapok.Utility;
import com.hi_depok.hi_depok.Lapok.mAdapter.Adapter_Komentar;
import com.hi_depok.hi_depok.Lapok.mData.Komentar;
import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    TextView nameTxt, timeTxt, title, jml_like, jml_com;
    ImageView img, kej, likeimg, comimg, shaimg, btnKirim;
    EditText etKomentar;
    EditText comment;
    List<Komentar> mList = new ArrayList<Komentar>();
    SwipeRefreshLayout swipe;
    Adapter_Komentar adapter;
    SessionManager session;
    String detail_url = "http://hidepok.id/android/lapok/lapok_getContent_detail.php";
    String url = "http://hidepok.id/android/lapok/lapok_komentar.php?id=";
    NotificationCompat.Builder mBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lapok_detail_komentar);
        super.onCreateDrawer();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        session = new SessionManager(this);
        HashMap<String, String> post_detail = session.getIdPost();
        final String id_post = post_detail.get(SessionManager.KEY_ID_POST);

        ListView listView = (ListView) findViewById(R.id.listKomentar);
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout_komentar);
        adapter = new Adapter_Komentar(this, R.layout.lapok_isi_komentar, mList);

        swipe.setOnRefreshListener(this);

        listView.setAdapter(adapter);

        Utility.setListViewHeightBasedOnChildren(listView);

        swipe.post(new Runnable() {
                       @Override
                       public void run() {
                           swipe.setRefreshing(true);
                           /*mList.clear();
                           adapter.notifyDataSetChanged();*/
                           callVolley(id_post);
                       }
                   }
        );

        //INITIALIZE VIEWS
        btnKirim = (ImageView) findViewById(R.id.btnKirim);
        etKomentar = (EditText) findViewById(R.id.isiKomentar);
        nameTxt = (TextView) findViewById(R.id.nameTxtdetail);
        timeTxt = (TextView) findViewById(R.id.timeTxtdetail);
        title = (TextView) findViewById(R.id.card_text_detail);
        jml_like = (TextView) findViewById(R.id.jumlah_like_detail);
        jml_com = (TextView) findViewById(R.id.jumlah_comment_detail);
        img = (ImageView) findViewById(R.id.imageArtist_detail);
        kej = (ImageView) findViewById(R.id.card_image_detail);

        kej.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog settingsDialog = new Dialog(DetailActivity.this);

                LayoutInflater inflater = getLayoutInflater();
                View newView = inflater.inflate(R.layout.activity_image, null);

                settingsDialog.setContentView(newView);
                settingsDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                settingsDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.alpha(0)));

                ImageView iv = (ImageView) newView.findViewById(R.id.profile_img_popup);
                Bitmap bm = ((GlideBitmapDrawable) kej.getDrawable()).getBitmap();
                iv.setImageBitmap(bm);

                settingsDialog.show();
            }
        });

        likeimg = (ImageView) findViewById(R.id.like_detail);
        comimg = (ImageView) findViewById(R.id.comment_button_detail);
        shaimg = (ImageView) findViewById(R.id.share_button_detail);
        comment = (EditText) findViewById(R.id.isiKomentar);

        likeimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                likeimg.setImageResource(R.drawable.favorite);
                Toast.makeText(view.getContext(), "Anda menyukai konten ini",
                        Toast.LENGTH_SHORT).show();
            }
        });

        shaimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Yuk memberi dampak perubahan positif" +
                        " untuk kota Depok ;) ";
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                view.getContext().startActivity(Intent.createChooser(sharingIntent, "Share via"));

                Toast.makeText(view.getContext(), "Sebarkan berita ini",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*session = new SessionManager(DetailActivity.this);
                //session username untuk diambil dan dimasukkan ke dalam skrip php
                HashMap<String, String> user = session.getUserDetails();
                String nama_user_komentar = user.get(SessionManager.KEY_NAME);

                String komentar_user = etKomentar.getText().toString();
                String text = nama_user_komentar + " mengomentari : " + komentar_user;
                mBuilder = new NotificationCompat.Builder(DetailActivity.this);
                mBuilder
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Hi Depok")
                        .setAutoCancel(true)
                        .setContentText(text);
                buildNotification(DetailActivity.this, id_post, name, time, date, isi, jumlah_suka,
                        jumlah_komentar, image, kejadian, like, comment, share);*/
            }
        });
    }

    private void callVolley(final String id_post_detail) {
        /*mList.clear();
        adapter.notifyDataSetChanged();*/
        swipe.setRefreshing(true);
        StringRequest requestDetail = new StringRequest(Request.Method.POST, detail_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray_detail = new JSONArray(response);
                            JSONObject objDetail = jsonArray_detail.getJSONObject(0);

                            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                            DateFormat df1 = new SimpleDateFormat("HH:mm:ss");
                            Date startDate, startTime;
                            String avatar, kejadian, waktu, tanggal;

                            tanggal = objDetail.getString("tanggal");
                            waktu = objDetail.getString("waktu");
                            avatar = "http://hidepok.id/assets/images/photos/avatar/" + objDetail.getString("avatar");
                            kejadian = "http://hidepok.id/assets/images/photos/lapok/" + objDetail.getString("kejadian");

                            try {
                                startDate = df.parse(tanggal);
                                startTime = df1.parse(waktu);
                                SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("d MMM yyyy");
                                String newDateString = mSimpleDateFormat.format(startDate);
                                String newTimeString = df1.format(startTime);
                                String waktu_kejadian = newDateString + ", pada pukul " + newTimeString + " WIB";
                                timeTxt.setText(waktu_kejadian);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            nameTxt.setText(objDetail.getString("nama_user"));
                            jml_like.setText(objDetail.getString("suka"));
                            jml_com.setText(objDetail.getString("komentar"));
                            title.setText(objDetail.getString("isi"));

                            likeimg.setImageResource(R.drawable.like);
                            shaimg.setImageResource(R.drawable.share);
                            comimg.setImageResource(R.drawable.comment);

                            Glide.with(DetailActivity.this).load(kejadian).thumbnail(0.3f)
                                    .placeholder(R.drawable.image_placeholder).into(kej);
                            Glide.with(DetailActivity.this).load(avatar).thumbnail(0.3f)
                                    .placeholder(R.drawable.image_placeholder).into(img);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(DetailActivity.this, "Percobaan koneksi gagal! Cek koneksi anda!",
                            Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(DetailActivity.this, "Gagal terhubung! Cek koneksi anda!",
                            Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(DetailActivity.this, "Gagal terhubung dengan server!",
                            Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    Toast.makeText(DetailActivity.this, "Gagal terhubung! Cek koneksi anda!",
                            Toast.LENGTH_SHORT).show();
                }
                swipe.setRefreshing(false);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("id_post", id_post_detail);
                return param;
            }
        };
        Akses.getInstance(DetailActivity.this).addtoRequestQueue(requestDetail);
    }


    //------------------------------------BUAT NOTIFIKASI-----------------------------------------//
    /*private void buildNotification(Context ctx, String id_post, String name, String time, String date,
                                   String title, String totlike, String totcom, String image,
                                   String kejadian, int like, int comment, int share) {

        Intent infoNotification = new Intent(ctx, DetailActivity.class);

        //PACK DATA TO SEND
        infoNotification.putExtra("ID_POST_KEY", id_post);
        infoNotification.putExtra("NAME_KEY", name);
        infoNotification.putExtra("TIME_KEY", time);
        infoNotification.putExtra("DATE_KEY", date);
        infoNotification.putExtra("TITLE_KEY", title);
        infoNotification.putExtra("IMAGE_KEY", image);
        infoNotification.putExtra("KEJADIAN_KEY", kejadian);
        infoNotification.putExtra("LIKE_KEY", like);
        infoNotification.putExtra("COMMENT_KEY", comment);
        infoNotification.putExtra("SHARE_KEY", share);
        infoNotification.putExtra("TOTAL_COMMENT", totcom);
        infoNotification.putExtra("TOTAL_LIKE", totlike);

        //OPEN ACTIVITY
        TaskStackBuilder taskStackBuilder = TaskStackBuilder
                .create(DetailActivity.this);
        taskStackBuilder.addParentStack(DetailActivity.class);
        taskStackBuilder.addNextIntent(infoNotification);
        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pendingIntent);
        Notification notification = mBuilder.build();
        notification.flags = Notification.DEFAULT_LIGHTS | Notification.FLAG_AUTO_CANCEL;
        NotificationManagerCompat.from(DetailActivity.this).notify(0, notification);
    }*/
    //--------------------------------------------------------------------------------------------//

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                DetailActivity.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onRefresh() {
        mList.clear();
        session = new SessionManager(DetailActivity.this);
        HashMap<String, String> post_detail = session.getIdPost();
        final String id_post = post_detail.get(SessionManager.KEY_ID_POST);
        kosong_detai();
        callVolley(id_post);
    }

    private void kosong_detai() {
        nameTxt.setText("");
        timeTxt.setText("");
        title.setText("");
        kej.setImageResource(R.drawable.image_placeholder);
        img.setImageResource(R.drawable.image_placeholder);
        likeimg.setImageResource(R.drawable.like);
        comimg.setImageResource(R.drawable.comment);
        shaimg.setImageResource(R.drawable.share);
    }
}
