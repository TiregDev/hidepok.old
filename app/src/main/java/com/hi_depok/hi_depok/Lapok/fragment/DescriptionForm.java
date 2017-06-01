package com.hi_depok.hi_depok.Lapok.fragment;

/**
 * Created by Muhammad63 on 3/18/2017.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hi_depok.hi_depok.Akses;
import com.hi_depok.hi_depok.Lapok.lapok_content;
import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.SessionManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class DescriptionForm extends AppCompatActivity {

    EditText etJudul, etDeskripsi, etAlamat;
    TextView Tanggal, Waktu;
    Spinner spinner;
    Button btnSubmit;
    String kategori;
    private static final String[]cate = {"", "Sampah", "Kebakaran", "Kemacetan",
            "Bencana Alam", "Pelanggaran", "Jalan Rusak", "Tindak Kriminal", "Terorisme", "Narkoba"};
    Calendar dateAndTime = Calendar.getInstance();
    SimpleDateFormat sdf_tanggal = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdf_waktu = new SimpleDateFormat("HH:mm:ss");
    String judul, deskripsi, tanggal, waktu, alamat;
    String deskripsi_url = "http://hidepok.id/android/lapok/lapok_form_pelaporan.php";

    private static final int REQUEST_TIMEOUT = 30000;
    private static final int REQUEST_MAX_RETRY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lapok_deskripsi);

        etJudul = (EditText) findViewById(R.id.entry);
        etDeskripsi = (EditText) findViewById(R.id.etIsiKejadian);
        etAlamat = (EditText) findViewById(R.id.etAlamat);
        btnSubmit = (Button) findViewById(R.id.btnSubmitLaporan);

        Intent intent = getIntent();
        final String image = intent.getExtras().getString("image");

        spinner = (Spinner) findViewById(R.id.spinKategori);
        ArrayAdapter<String> category = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,cate);
        spinner.setAdapter(category);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                kategori = adapterView.getItemAtPosition(position).toString();
                if(!kategori.equals("")) {
                    Toast.makeText(DescriptionForm.this, kategori + " selected", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Tanggal = (TextView) findViewById(R.id.Tanggal);
        Waktu = (TextView) findViewById(R.id.Waktu);
        updateLabel();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                judul = etJudul.getText().toString();
                deskripsi = etDeskripsi.getText().toString();
                alamat = etAlamat.getText().toString();
                tanggal = Tanggal.getText().toString();
                waktu = Waktu.getText().toString();

                if (judul.equals("")){
                    etJudul.setError("Judul wajib diisi");
                }else if(deskripsi.equals("")){
                    etDeskripsi.setError("Deskripsikan kejadian");
                }else if(kategori.equals("")){
                    Toast.makeText(DescriptionForm.this, "Pilih kategori sesuai kejadian terlebih dahulu",
                            Toast.LENGTH_SHORT).show();
                }
                else if(alamat.equals("")){
                    etAlamat.setError("Tempat kejadian wajib diisi");
                }else{
                    final ProgressDialog loading = ProgressDialog.show(DescriptionForm.this,
                            "Proses...", "Tunggu sebentar...", false, false);
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, deskripsi_url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    loading.dismiss();
                                    Toast.makeText(DescriptionForm.this, response, Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(DescriptionForm.this, lapok_content.class));
                                    finish();

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(DescriptionForm.this, error.getMessage().toString(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            SessionManager session = new SessionManager(DescriptionForm.this);

                            HashMap<String, String> user = session.getUserDetails();
                            String id = user.get(SessionManager.KEY_ID_USER);

                            HashMap<String, String> params = new HashMap<String, String>();

                            params.put("id_user", id);
                            params.put("judul", judul);
                            params.put("tanggal", tanggal);
                            params.put("waktu", waktu);
                            params.put("isi_post", deskripsi);
                            params.put("kategori", kategori);
                            params.put("lokasi_post", alamat);
                            params.put("image", image);

                            return params;
                        }
                    };
                    stringRequest.setRetryPolicy(new DefaultRetryPolicy(REQUEST_TIMEOUT, REQUEST_MAX_RETRY, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    Akses.getInstance(DescriptionForm.this).addtoRequestQueue(stringRequest);
                }
            }
        });

    }
    private void updateLabel(){
        Tanggal.setText(
                sdf_tanggal.format(dateAndTime.getTime())
        );
        Waktu.setText(
                sdf_waktu.format(dateAndTime.getTime())
        );
    }
}
