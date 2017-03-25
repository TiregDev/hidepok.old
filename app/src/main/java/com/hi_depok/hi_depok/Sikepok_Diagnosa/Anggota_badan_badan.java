package com.hi_depok.hi_depok.Sikepok_Diagnosa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.hi_depok.hi_depok.R;


/**
 * Created by HP on 4/28/2016.
 */
public class Anggota_badan_badan extends AppCompatActivity {

    ExpandableRelativeLayout expandableLayout1, expandableLayout2, expandableLayout3, expandableLayout4, expandableLayout5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anggota_badan);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                Anggota_badan_badan.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void expandableButton1(View view) {
        expandableLayout1 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout1);
        expandableLayout1.toggle(); // toggle expand and collapse
    }

    public void expandableButton2(View view) {
        expandableLayout2 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout2);
        expandableLayout2.toggle(); // toggle expand and collapse
    }

    public void expandableButton3(View view) {
        expandableLayout3 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout3);
        expandableLayout3.toggle(); // toggle expand and collapse
    }

    public void expandableButton4(View view) {
        expandableLayout4 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout4);
        expandableLayout4.toggle(); // toggle expand and collapse
    }

    public void androidCheckBoxClicked(View view) {
        // action for checkbox click
        switch (view.getId()) {
            case R.id.checkBox1:
                Toast.makeText(getApplicationContext(), "Bisa", Toast.LENGTH_LONG).show();
                break;
        }
    }
    public void sugesti (View view){
        Intent contoh = new Intent(Anggota_badan_badan.this, Sugesti.class);
        startActivity(contoh);
    }
}


