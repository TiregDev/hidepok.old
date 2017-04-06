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
        dokter = (Spinner) findViewById(R.id.pilih_dokter);

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
        String number="085776410906";

        String part1 = poli.getSelectedItem().toString().toUpperCase();
        String part2 = dokter.getSelectedItem().toString().toUpperCase();

        String message = "DAFPAS#" + part1 + "-" + part2;
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
