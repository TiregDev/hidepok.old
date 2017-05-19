package com.hi_depok.hi_depok.Sikepok_RS;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.R;

public class regis_berobat extends BaseActivity {
    private Spinner poli, dokter;
    private Button kirim;
    String pilihan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sikepokrs_regis_berobat);
        super.onCreateDrawer();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar3));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Pengiriman SMS
        poli = (Spinner) findViewById(R.id.pilih_poli);
//        dokter = (Spinner) findViewById(R.id.pilih_dokter);

        kirim = (Button) findViewById(R.id.kirim);
        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS();
            }

        });
    }

    //mengirim sms
    protected void sendSMS()
    {
        String number="085811811833";

        if (poli.getSelectedItem().toString().equals("Poli Penyakit Dalam - Dr. Kahar")){
            pilihan = "DAL-KAHAR";
        } else if (poli.getSelectedItem().toString().equals("Poli Penyakit Dalam - Dr. Desi")){
            pilihan = "DAL-DESI";
        } else if (poli.getSelectedItem().toString().equals("Poli Anak - Dr. Dipari")){
            pilihan = "ANA-DIPARI";
        } else if (poli.getSelectedItem().toString().equals("Poli Anak - Dr. Harry")){
            pilihan = "ANA-HARRY";
        } else if (poli.getSelectedItem().toString().equals("Poli Anak - Dr. Indra")){
            pilihan = "ANA-INDRA";
        } else if (poli.getSelectedItem().toString().equals("Poli Anak - Dr. Siswanto")){
            pilihan = "ANA-SISWANTO";
        } else if (poli.getSelectedItem().toString().equals("Poli Psikiatri - Dr. Diana")){
            pilihan = "PSI-DIANA";
        } else if (poli.getSelectedItem().toString().equals("Poli Saraf/Neurologi - Dr. Agus")){
            pilihan = "SAR-AGUS";
        } else if (poli.getSelectedItem().toString().equals("Poli Mata - Dr. Lieska")){
            pilihan = "MAT-LIESKA";
        } else if (poli.getSelectedItem().toString().equals("Poli Paru - Dr. Sherly")){
            pilihan = "PAR-SHERLY";
        } else if (poli.getSelectedItem().toString().equals("Poli Anestesi - Dr. Amelia")){
            pilihan = "ANE-UJANG";
        } else if (poli.getSelectedItem().toString().equals("Poli Bedah - Dr. Abu")){
            pilihan = "BED-ABU";
        } else if (poli.getSelectedItem().toString().equals("Poli Bedah - Dr. Henru")){
            pilihan = "BED-HENRU";
        } else if (poli.getSelectedItem().toString().equals("Poli Kebidanan dan Kandungan - Dr. Bagus")){
            pilihan = "KAN-BAGUS";
        } else if (poli.getSelectedItem().toString().equals("Poli Kebidanan dan Kandungan - Dr. Patma")){
            pilihan = "KAN-PATMA";
        } else if (poli.getSelectedItem().toString().equals("Poli THT - Dr. Caroline")){
            pilihan = "THT-CAROLINE";
        } else if (poli.getSelectedItem().toString().equals("Poli Gigi dan Bedah Mulu - Dr. Sigit")){
            pilihan = "GIG-SIGIT";
        } else if (poli.getSelectedItem().toString().equals("Poli Gigi dan Bedah Mulu - Dr. Darma")){
            pilihan = "GIG-DARMA";
        } else if (poli.getSelectedItem().toString().equals("Poli CST - Dr. Kurnia")){
            pilihan = "CST-KURNIA";
        } else if (poli.getSelectedItem().toString().equals("Poli VCT - Dr. Kurnia")){
            pilihan = "VCT-KURNIA";
        }
        String part1 = pilihan;

        String message = "DAFPAS#" + part1;
        try {
            SmsManager manager = SmsManager.getDefault();
            manager.sendTextMessage(number, null, message,null,null);
            Toast.makeText(getApplicationContext(), "SMS Berhasil terkirim!", Toast.LENGTH_LONG).show();
            Intent next = new Intent(regis_berobat.this, registrasi.class);
            startActivity(next);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),"SMS Gagal terkirim, silahkan coba lagi!", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }

    //menambahkan menu

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                regis_berobat.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void kirim_data (View v) {
        Toast.makeText(getBaseContext(), "Data terkirim", Toast.LENGTH_SHORT).show();
    }
}
