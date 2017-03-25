package com.hi_depok.hi_depok.Sikepok_RS;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.hi_depok.hi_depok.R;

public class regis_pasien_lama extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sikepokrs_regis_pasien_lama);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar3));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                regis_pasien_lama.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void kirim_data (View v) {
        Toast.makeText(getBaseContext(), "Data terkirim", Toast.LENGTH_SHORT).show();
    }
}
