package com.hi_depok.hi_depok.Sikepok_RS;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import com.hi_depok.hi_depok.R;

public class post_saya extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_saya);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar3));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void ke_detail_post_saya (View view){
        Intent next = new Intent(post_saya.this, detail_post_saya.class);
        startActivity(next);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                post_saya.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
