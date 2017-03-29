package com.hi_depok.hi_depok.Kapok;


import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 3/17/2017.
 */

public class activity_view_ulasan extends BaseActivity {

    private LinearLayoutManager lLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kapok_view_ulasan);
        super.onCreateDrawer();
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        List<ItemObjectViewUlasan> rowListItem = getAllItemList();
        lLayout = new LinearLayoutManager(activity_view_ulasan.this);

        RecyclerView rView = (RecyclerView)findViewById(R.id.recycler_view);
        rView.setLayoutManager(lLayout);

        CustomAdapterViewUlasan rcAdapter = new CustomAdapterViewUlasan(activity_view_ulasan.this, rowListItem);
        rView.setAdapter(rcAdapter);


    }

    private List<ItemObjectViewUlasan> getAllItemList(){
        List<ItemObjectViewUlasan> allItems = new ArrayList<>();
        allItems.add(new ItemObjectViewUlasan("Kadek Pandu", "12-01-2016 20:01:56", "Makanannya enak dan murah", R.drawable.profile));
        allItems.add(new ItemObjectViewUlasan("Fajar Zakaria", "12-01-2016 21:01:56", "Tempatnya nyaman dan bersih", R.drawable.profile));
        allItems.add(new ItemObjectViewUlasan("Tegar Hidayat", "13-01-2016 08:02:00", "makannya muuaannntebbb!!!!", R.drawable.profile));
        allItems.add(new ItemObjectViewUlasan("Jihada Amalia ", "14-02-2016 10:01:47", "Fasilitasnya lengkap.....", R.drawable.profile));
        allItems.add(new ItemObjectViewUlasan("Retno Widianti", "15-01-2016 20:34:15", "Topmarkotop gan, lanjutkan!!", R.drawable.profile));
        allItems.add(new ItemObjectViewUlasan("Fatimah Rosmaningsih", "16-01-2016 20:56:23", "Pelayanannya terbaik!!", R.drawable.profile));
        allItems.add(new ItemObjectViewUlasan("Ilham Al Fajri", "17-01-2016 20:45:33", "Parkirannya luas", R.drawable.profile));
        allItems.add(new ItemObjectViewUlasan("Toriq Wahid", "18-01-2016 20:22:44", "harga minumannya lumayan tapi enak", R.drawable.profile));
        allItems.add(new ItemObjectViewUlasan("Alian Rakhul", "19-01-2016 15:55:34", "Berkelas rasa makannya", R.drawable.profile));
        allItems.add(new ItemObjectViewUlasan("Hadi Nur Salam", "19-01-2016 20:39:55", "Harga kaki 5 rasa bintang 5", R.drawable.profile));

        return allItems;
    }

}
