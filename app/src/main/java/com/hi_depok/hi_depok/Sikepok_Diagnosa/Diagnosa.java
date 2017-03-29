package com.hi_depok.hi_depok.Sikepok_Diagnosa;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.hi_depok.hi_depok.R;

import java.util.ArrayList;
import java.util.List;

public class Diagnosa extends AppCompatActivity {
    private LinearLayoutManager lLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sikepokdiagnosa_diagnosa);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        List<itemObject_diagnosa> rowListItem2 = getAllItemList();
        lLayout = new LinearLayoutManager(this);

        RecyclerView rView = (RecyclerView)findViewById(R.id.list_diagnosa);
        rView.setLayoutManager(lLayout);

        RecyclerViewAdapter_diagnosa rcAdapter = new RecyclerViewAdapter_diagnosa(this, rowListItem2);
        rView.setAdapter(rcAdapter);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                Diagnosa.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private List<itemObject_diagnosa> getAllItemList(){
        List<itemObject_diagnosa> allItems = new ArrayList<>();
        allItems.add(new itemObject_diagnosa("Kepala", R.drawable.kepala));
        allItems.add(new itemObject_diagnosa("Dada", R.drawable.dada));
        allItems.add(new itemObject_diagnosa("Punggung", R.drawable.punggung));
        allItems.add(new itemObject_diagnosa("Perut", R.drawable.perut));
        allItems.add(new itemObject_diagnosa("Tangan", R.drawable.tangan));
        allItems.add(new itemObject_diagnosa("Kaki", R.drawable.kaki));

        return allItems;
    }
}
