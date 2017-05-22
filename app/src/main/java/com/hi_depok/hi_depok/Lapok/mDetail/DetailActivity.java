package com.hi_depok.hi_depok.Lapok.mDetail;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.Adapter_Komentar;
import com.hi_depok.hi_depok.Akses;
import com.hi_depok.hi_depok.Komentar;
import com.hi_depok.hi_depok.Lapok.Utility;
import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.SessionManager;
import com.hi_depok.hi_depok.Ucok.SIUMKM.UcokDetailActivity;
import com.squareup.picasso.Picasso;

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

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    TextView nameTxt, timeTxt, title, jml_like, jml_com;
    ImageView img, kej, likeimg, comimg, shaimg, statimg, btnKirim;
    EditText etKomentar;
    SessionManager session;
    List<Komentar> mList = new ArrayList<Komentar>();
    SwipeRefreshLayout swipe;
    Adapter_Komentar adapter;
    String detail_url = "http://hidepok.id/android/lapok/lapok_getContent_detail.php";
    String url = "http://hidepok.id/android/lapok/lapok_komentar.php?id=";
    String insert = "http://hidepok.id/android/lapok/lapok_insert.php";
    NotificationCompat.Builder mBuilder;
    CircleImageView imageView;

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
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //---------------- Image Single Popup --------------------------------------------------
        imageView = (CircleImageView) findViewById(R.id.imageArtist_detail);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog settingsDialog = new Dialog(DetailActivity.this);

                LayoutInflater inflater = getLayoutInflater();
                View newView = inflater.inflate(R.layout.activity_image, null);

                settingsDialog.setContentView(newView);
                settingsDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                settingsDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.alpha(0)));

                ImageView iv = (ImageView) newView.findViewById(R.id.profile_img_popup);
                Bitmap bm = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                iv.setImageBitmap(bm);
                settingsDialog.show();

            }
        });

        Intent i = this.getIntent();
        final String id_post = (String) i.getExtras().get("ID_POST_DETAIL");

        ListView listView = (ListView) findViewById(R.id.listKomentar);
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout_komentar);
        adapter = new Adapter_Komentar(this, R.layout.lapok_isi_komentar, mList);

        swipe.setOnRefreshListener(this);

        listView.setAdapter(adapter);

        Utility.setListViewHeightBasedOnChildren(listView);

        content_detail(id_post);

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
        statimg = (ImageView) findViewById(R.id.status_post_detail);

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

        likeimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session = new SessionManager(DetailActivity.this);
                HashMap<String, String> user = session.getUserDetails();
                final String id_user = user.get(SessionManager.KEY_ID_USER);
                StringRequest likeRequest = new StringRequest(Request.Method.POST, insert,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.equals("belum")){
                                    likeimg.setImageResource(R.drawable.favorite);
                                    Toast.makeText(DetailActivity.this, "Anda menyukai konten ini",
                                            Toast.LENGTH_SHORT).show();
                                }else
                                    Toast.makeText(DetailActivity.this, "Anda telah menyukai konten ini",
                                            Toast.LENGTH_SHORT).show();
                                content_detail(id_post);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put("id_post", id_post);
                        params.put("id_user", id_user);
                        params.put("id", "2");
                        return params;
                    }
                };
                Akses.getInstance(DetailActivity.this).addtoRequestQueue(likeRequest);
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

                session = new SessionManager(DetailActivity.this);
                HashMap<String, String> user = session.getUserDetails();
                String nama_user_komentar = user.get(SessionManager.KEY_NAME);
                final String id_user_komentar = user.get(SessionManager.KEY_ID_USER);

                final String komentar_user = etKomentar.getText().toString();
                if (!komentar_user.equals("")) {
                    String text = nama_user_komentar + " mengomentari : " + komentar_user;

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, insert,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(DetailActivity.this, response, Toast.LENGTH_SHORT).show();
                                    content_detail(id_post);
                                    etKomentar.setText("");
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
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> params = new HashMap<String, String>();
                            params.put("id_post", id_post);
                            params.put("id_user", id_user_komentar);
                            params.put("isi_komentar", komentar_user);
                            params.put("id", "1");
                            return params;
                        }
                    };
                    Akses.getInstance(DetailActivity.this).addtoRequestQueue(stringRequest);
                } else
                    etKomentar.setError("Anda belum memberikan komentar anda");
            }
        });
    }

    private void content_detail(final String id_post) {
        String komentar_url = url + id_post;
        session = new SessionManager(DetailActivity.this);
        HashMap<String, String> user = session.getUserDetails();
        final String id_user = user.get(SessionManager.KEY_ID_USER);
        mList.clear();
        adapter.notifyDataSetChanged();
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
                            avatar = "http://hidepok.id/assets/images/avatar/" + objDetail.getString("avatar");
                            kejadian = "http://hidepok.id/assets/images/photos/lapok/" + objDetail.getString("foto");

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
                            nameTxt.setText(objDetail.getString("nama"));
                            jml_like.setText(objDetail.getString("suka"));
                            jml_com.setText(objDetail.getString("komentar"));
                            title.setText(objDetail.getString("isi"));

                            if (!objDetail.getString("user_like").equals("null")) {
                                String[] array = objDetail.getString("user_like").split(", ");
                                for (int j = 0; j < array.length; j++) {
                                    if (id_user.equals(array[j])) {
                                        likeimg.setImageResource(R.drawable.favorite);
                                    } else {
                                        likeimg.setImageResource(R.drawable.like);
                                    }
                                }
                            } else {
                                likeimg.setImageResource(R.drawable.like);
                            }

                            shaimg.setImageResource(R.drawable.share);
                            comimg.setImageResource(R.drawable.comment);

                            String status = objDetail.getString("status");
                            if (status.equals("Menunggu"))
                                statimg.setImageResource(R.drawable.circle_wait_list);
                            else if (status.equals("Proses"))
                                statimg.setImageResource(R.drawable.circle_process_list);
                            else if (status.equals("Selesai"))
                                statimg.setImageResource(R.drawable.circle_done_list);

                            Glide.with(DetailActivity.this).load(kejadian).thumbnail(0.3f)
                                    .placeholder(R.drawable.image_placeholder).into(kej);

                            if(objDetail.getString("avatar").equals("null")){
                                img.setImageResource(R.drawable.profile);
                            }else
                                Picasso.with(DetailActivity.this).load(avatar).resize(300,300).into(img);

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
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("id_post", id_post);
                return param;
            }
        };
        Akses.getInstance(DetailActivity.this).addtoRequestQueue(requestDetail);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, komentar_url, "",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject obj_komentar = response.getJSONObject(i);
                                Komentar komentar = new Komentar();
                                komentar.setNama(obj_komentar.getString("nama_user"));
                                komentar.setIsi_komentar(obj_komentar.getString("isi_komentar"));
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
        });
        Akses.getInstance(DetailActivity.this).addtoRequestQueue(jsonArrayRequest);
    }

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
        swipe.setRefreshing(false);
    }
}
