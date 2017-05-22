package com.hi_depok.hi_depok.Activity_Main;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hi_depok.hi_depok.Akses;
import com.hi_depok.hi_depok.Profile.ambil_gambar;
import com.hi_depok.hi_depok.Profile.fragment.history;
import com.hi_depok.hi_depok.Profile.fragment.myprofile;
import com.hi_depok.hi_depok.Profile.setprofile;
import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.SessionManager;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class profile extends BaseActivity implements View.OnClickListener {

    ViewPager pager;
    ProfilePager adapter;
    View strip;
    TextView profile, history, nama_profil, bio_profil;
    ImageView ham;
    SessionManager session;
    CircleImageView imageView;
    String detail_url = "http://hidepok.id/android/hidepok/getUserDetail.php";
    String foto, avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        super.onCreateDrawer();
        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, android.R.color.transparent));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        nama_profil = (TextView) findViewById(R.id.username);
        bio_profil = (TextView) findViewById(R.id.status_user);

        session = new SessionManager(this);
        HashMap<String, String> user = session.getUserDetails();
        String nama = user.get(SessionManager.KEY_NAME);
        String bio = user.get(SessionManager.KEY_BIO);
        String avatar = user.get(SessionManager.KEY_FOTO);

        nama_profil.setText(nama);
        bio_profil.setText(bio);

        //---------------- Image Single Popup --------------------------------------------------
        imageView = (CircleImageView) findViewById(R.id.pict_profile);
        Picasso.with(getBaseContext()).load(avatar).resize(300,300).into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(profile.this, ambil_gambar.class));
            }
        });

        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (null != imageView.getDrawable()) {
                    Dialog settingsDialog = new Dialog(profile.this);

                    LayoutInflater inflater = getLayoutInflater();
                    View newView = inflater.inflate(R.layout.activity_image, null);

                    settingsDialog.setContentView(newView);
                    settingsDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                    settingsDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.alpha(0)));

                    ImageView iv = (ImageView) newView.findViewById(R.id.profile_img_popup);
                    Bitmap bm = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                    iv.setImageBitmap(bm);

                    settingsDialog.show();
                    return false;
                } else {
                    Toast.makeText(activity, "Anda belum memiliki gambar", Toast.LENGTH_SHORT).show();
                    return true;
                }
            }
        });

        pager = (ViewPager) findViewById(R.id.pager);
        profile = (TextView) findViewById(R.id.profile);
        history = (TextView) findViewById(R.id.history);
        strip = findViewById(R.id.strip);
        ham = (ImageView) findViewById(R.id.ham);

        ham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.openDrawer();
            }
        });

        adapter = new ProfilePager(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position) {
                    case 0:
                        strip.setTranslationX(positionOffsetPixels / 2);
                        break;
                    case 1:
                        strip.setTranslationX(strip.getWidth() + positionOffsetPixels / 2);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        profile.setOnClickListener(this);
        history.setOnClickListener(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getProfile();
    }

    private void getProfile() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, detail_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);

                            foto = jsonObject.getString("foto");

//                            if (!foto.equals("null")) {
                                avatar ="http://hidepok.id/assets/images/avatar/" + foto;
                                Picasso.with(getBaseContext()).load(avatar).resize(300,300).into(imageView);
//                            } else {
//                                imageView.setImageResource(R.drawable.profile);
//                            }

                            //ambil nilai dari db
                            nama_profil.setText(jsonObject.getString("nama"));
//                            if (!jsonObject.getString("bio").equals("null")) {
                                bio_profil.setText(jsonObject.getString("bio"));
//                            } else {
//                                bio_profil.setText("Pengguna Hi Depok");
//                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(profile.this, "An error occured" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SessionManager session = new SessionManager(profile.this);
                //session email untuk diambil dan dimasukkan ke dalam skrip php
                HashMap<String, String> user = session.getUserDetails();
                String email_session = user.get(SessionManager.KEY_USERNAME);
                //username ada di skrip getUserDetail.php dengan paramater name
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", email_session);
                return params;
            }
        };
        Akses.getInstance(this).addtoRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profile:
                pager.setCurrentItem(0);
                break;
            case R.id.history:
                pager.setCurrentItem(1);
                break;
            default:
                break;
        }
    }

    class ProfilePager extends FragmentPagerAdapter {
        myprofile myprofile;
        history history;

        public ProfilePager(FragmentManager fm) {
            super(fm);
            myprofile = com.hi_depok.hi_depok.Profile.fragment.myprofile.newInstance();
            history = com.hi_depok.hi_depok.Profile.fragment.history.newInstance();
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return myprofile;
                case 1:
                    return history;
                default:
                    return myprofile;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    public void sett(View view) {
        Intent intent = new Intent(profile.this, setprofile.class);
        startActivity(intent);
    }
}
