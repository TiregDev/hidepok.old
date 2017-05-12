package com.hi_depok.hi_depok.Profile;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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
import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.Akses;
import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class setprofile extends BaseActivity {

    CircleImageView image;
    TextView changepass;
    EditText etBio, etNama, etEmail, etNomer, etAlamat, etUsername;
    Button btnSimpan;
    SessionManager session;
    String url_update_profile = "http://hidepok.id/android/hidepok/updateProfile.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_set_profile);
        super.onCreateDrawer();
        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        image = (CircleImageView) findViewById(R.id.profpict);
        etUsername = (EditText) findViewById(R.id.setUsername);
        etBio = (EditText) findViewById(R.id.setBio);
        etNama = (EditText) findViewById(R.id.setNama);
        etEmail = (EditText) findViewById(R.id.setEmail);
        etNomer = (EditText) findViewById(R.id.setNomer);
        etAlamat = (EditText) findViewById(R.id.setAlamat);
        btnSimpan = (Button) findViewById(R.id.btnSimpanProfil);

        session = new SessionManager(this);
        HashMap<String, String> user = session.getUserDetails();
        String username = user.get(SessionManager.KEY_USERNAME);
        String name = user.get(SessionManager.KEY_NAME);
        String email = user.get(SessionManager.KEY_EMAIL);
        final String id_user = user.get(SessionManager.KEY_ID_USER);

        etUsername.setText(username);
        etNama.setText(name);
        etEmail.setText(email);

        etUsername.setEnabled(false);
        etNama.setEnabled(false);
        etEmail.setEnabled(false);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(setprofile.this, ambil_gambar.class));
            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String bio = etBio.getText().toString();
                final String nama = etNama.getText().toString();
                final String email = etEmail.getText().toString();
                final String no_telp = etNomer.getText().toString();
                if (bio.equals("")) {
                    etBio.setError("Bio harus diisi");
                } else if (no_telp.equals("")) {
                    etNomer.setError("Nomor telpon harus diisi");
                } else {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url_update_profile,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONArray jsonArray = new JSONArray(response);
                                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                                        Toast.makeText(setprofile.this, jsonObject.getString("message"),
                                                Toast.LENGTH_SHORT).show();
                                        etBio.setText("");
                                        etNomer.setText("");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                                Toast.makeText(setprofile.this, "Koneksi gagal! Cek koneksi anda!",
                                        Toast.LENGTH_SHORT).show();
                            } else if (error instanceof AuthFailureError) {
                                Toast.makeText(setprofile.this, "Gagal terhubung! Cek koneksi anda!",
                                        Toast.LENGTH_SHORT).show();
                            } else if (error instanceof ServerError) {
                                Toast.makeText(setprofile.this, "Gagal terhubung dengan server!",
                                        Toast.LENGTH_SHORT).show();
                            } else if (error instanceof NetworkError) {
                                Toast.makeText(setprofile.this, "Gagal terhubung! Cek koneksi anda!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> params = new HashMap<String, String>();
                            params.put("bio", bio);
                            params.put("nama", nama);
                            params.put("email", email);
                            params.put("no_telp", no_telp);
                            params.put("id_user", id_user);
                            return params;
                        }
                    };
                    Akses.getInstance(setprofile.this).addtoRequestQueue(stringRequest);
                }
            }
        });

        changepass = (TextView) findViewById(R.id.changepass);
        changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(setprofile.this, changepass.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                setprofile.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
