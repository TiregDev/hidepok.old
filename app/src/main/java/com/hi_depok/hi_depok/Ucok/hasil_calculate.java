package com.hi_depok.hi_depok.Ucok;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.R;

import java.util.ArrayList;
import java.util.List;

public class hasil_calculate extends BaseActivity {
    private LinearLayoutManager lLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ucok_hasil_calculate);
        super.onCreateDrawer();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        List<itemObject_listdanus> rowListItem = getAllItemList();
        lLayout = new LinearLayoutManager(hasil_calculate.this);

        RecyclerView rView = (RecyclerView)findViewById(R.id.rekomen_danus);
        rView.setLayoutManager(lLayout);

        RecyclerViewAdapter_listdanus rcAdapter = new RecyclerViewAdapter_listdanus(hasil_calculate.this, rowListItem);
        rView.setAdapter(rcAdapter);
    }

    private List<itemObject_listdanus> getAllItemList(){
        List<itemObject_listdanus> allItems = new ArrayList<>();
        allItems.add(new itemObject_listdanus("Peter James", "Vildansvagen 19, Lund Sweden","IDR 1k/Item", R.drawable.image_placeholder));
        allItems.add(new itemObject_listdanus("Henry Jacobs", "3 Villa Crescent London, UK","IDR 1,5k/Item", R.drawable.image_placeholder));
        allItems.add(new itemObject_listdanus("Bola Olumide", "Victoria Island Lagos, Nigeria","IDR 5k/Item", R.drawable.image_placeholder));
        allItems.add(new itemObject_listdanus("Chidi Johnson", "New Heaven Enugu, Nigeria","IDR 7k/Item", R.drawable.image_placeholder));
        allItems.add(new itemObject_listdanus("DeGordio Puritio", "Italion Gata, Padova, Italy","IDR 3k/Item", R.drawable.image_placeholder));

        return allItems;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                hasil_calculate.this.finish();
                return true;
//            case R.id.action_share:
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
