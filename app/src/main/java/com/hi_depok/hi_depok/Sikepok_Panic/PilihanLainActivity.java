package com.hi_depok.hi_depok.Sikepok_Panic;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.hi_depok.hi_depok.R;


public class PilihanLainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sikepokpanic_pilihan_lain);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public void menuBidanClick (View view) {
        Intent intent = new Intent(PilihanLainActivity.this, ListActivity.class);
        startActivity(intent);
    }//
    public void menuTukangUrutClick (View view) {
        Intent intent = new Intent(PilihanLainActivity.this, ListActivity.class);
        startActivity(intent);
    }
    public void menuKhitanClick (View view) {
        Intent intent = new Intent(PilihanLainActivity.this, ListActivity.class);
        startActivity(intent);
    }
    public void menuAmbulanClick (View view) {
        Intent intent = new Intent(PilihanLainActivity.this, ListActivity.class);
        startActivity(intent);
    }
}
