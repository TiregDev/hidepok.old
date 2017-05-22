package com.hi_depok.hi_depok.Activity_Main;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hi_depok.hi_depok.Akses;
import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {

    EditText etNama, etSandi, etCekPass, etUsername, etEmail, etTempat;
    Button btnDaftar;
    TextView txtLogin, txtTanggal;
    SessionManager session;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String regist_url = "http://hidepok.id/android/hidepok/registrasi.php";
    String bio = "Pengguna Hi-Depok";
    String photo_default = "avatar_default.png";
    String alamat = "Alamat Anda belum tersedia";
    Calendar dateAndTime = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener d =
            new DatePickerDialog.OnDateSetListener(){
                @Override
                public void onDateSet(DatePicker view, int year, int month,
                                      int day){
                    dateAndTime.set(Calendar.YEAR, year);
                    dateAndTime.set(Calendar.MONTH, month);
                    dateAndTime.set(Calendar.DAY_OF_MONTH, day);
                    updateLabel();
                }
            };

    private void updateLabel(){
        txtTanggal.setText(
                sdf.format(dateAndTime.getTime()));
    }

    private void settingTanggal(){
        new DatePickerDialog(register.this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Intent getGmail = this.getIntent();
        String nama = (String) getGmail.getExtras().get("nama");
        String surel = (String) getGmail.getExtras().get("email");

        etNama = (EditText) findViewById(R.id.nama_user);
        etEmail = (EditText) findViewById(R.id.email_user);
        etUsername = (EditText) findViewById(R.id.username);
        etSandi = (EditText) findViewById(R.id.password);
        etCekPass = (EditText) findViewById(R.id.cekPass);
        etTempat = (EditText) findViewById(R.id.tempat_lahir);
        btnDaftar = (Button) findViewById(R.id.btnRegister);
        txtTanggal = (TextView) findViewById(R.id.tanggal_lahir);
        txtLogin = (TextView) findViewById(R.id.txtLogin);

        etNama.setText(nama);
        etEmail.setText(surel);

        etNama.setEnabled(false);
        etEmail.setEnabled(false);

        txtTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingTanggal();
            }
        });

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = etNama.getText().toString();
                final String email = etEmail.getText().toString();
                final String password = etSandi.getText().toString();
                final String cekPass = etCekPass.getText().toString();
                final String username = etUsername.getText().toString();
                final String tempat = etTempat.getText().toString();
                final String tanggal = txtTanggal.getText().toString();
                if (name.equals("")) {
                    etNama.setError("Nama harus diisi");
                } else if (email.equals("")) {
                    etEmail.setError("Email harus diiisi");
                }else if (username.contains(" ") || (username.equals(""))) {
                    etUsername.setError("Username tidak boleh ada spasi dan harus diisi");
                }else if (password.equals("")) {
                    etSandi.setError("Sandi harus diisi");
                }else if (!password.equals(cekPass)) {
                    etSandi.setError("Sandi tidak cocok");
                }else if (tempat.equals("")) {
                    etTempat.setError("Tempat lahir kamu harus diisi");
                }else if (tanggal.equals("Klik disini untuk mendapatkan tanggal lahir kamu")) {
                    txtTanggal.setText("Tanggal lahir kamu harus dipilih. Klik disini");
                }else{
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, regist_url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONArray jsonArray = new JSONArray(response);
                                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                                        String code = jsonObject.getString("code");
                                        if (code.equals("registration_failed")) {
                                            Toast.makeText(register.this, jsonObject.getString("message"),
                                                    Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(register.this, jsonObject.getString("message"),
                                                    Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(register.this,
                                                    MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(register.this, "Koneksi Gagal! Cek Koneksi Anda!", Toast.LENGTH_SHORT).show();
                            error.printStackTrace();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> params = new HashMap<String, String>();
                            params.put("nama", name);
                            params.put("email", email);
                            params.put("username", username);
                            params.put("password", password);
                            params.put("tempat_lahir", tempat);
                            params.put("tanggal_lahir", tanggal);
                            params.put("bio", bio);
                            params.put("photo", photo_default);
                            params.put("alamat", alamat);
                            return params;
                        }
                    };
                    Akses.getInstance(register.this).addtoRequestQueue(stringRequest);
                }

            }
        });

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(register.this, login.class));
            }
        });
    }
}
