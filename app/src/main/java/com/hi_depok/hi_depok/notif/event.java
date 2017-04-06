package com.hi_depok.hi_depok.notif;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.notif.*;
import com.hi_depok.hi_depok.notif.ItemObject;
import com.hi_depok.hi_depok.notif.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class event extends BaseActivity {
    private LinearLayoutManager lLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        super.onCreateDrawer();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, android.R.color.transparent));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        List<ItemObject> rowListItem = getAllItemList();
        lLayout = new LinearLayoutManager(event.this);

        RecyclerView rView = (RecyclerView)findViewById(R.id.view_event);
        rView.setLayoutManager(lLayout);

        com.hi_depok.hi_depok.notif.RecyclerViewAdapter rcAdapter = new com.hi_depok.hi_depok.notif.RecyclerViewAdapter(event.this, rowListItem);
        rView.setAdapter(rcAdapter);
    }

    private List<ItemObject> getAllItemList(){
        List<ItemObject> allItems = new ArrayList<>();
        allItems.add(new ItemObject("Bakti Sosial", "Vildansvagen 19, Lund Sweden","Panti Asuhan Darul Ilmi. 22/07/2017", R.drawable.image_fokopok_1));
        allItems.add(new ItemObject("Pameran", "3 Villa Crescent London, UK","Panti Asuhan Darul Ilmi. 22/07/2017", R.drawable.ucok_image_2));
        allItems.add(new ItemObject("Lomba Makan", "Victoria Island Lagos, Nigeria","Panti Asuhan Darul Ilmi. 22/07/2017", R.drawable.image_fokopok_2));
        allItems.add(new ItemObject("Gathering Komika", "New Heaven Enugu, Nigeria","Panti Asuhan Darul Ilmi. 22/07/2017", R.drawable.ucok_image_4));
        allItems.add(new ItemObject("Nobar", "Italion Gata, Padova, Italy","Panti Asuhan Darul Ilmi. 22/07/2017", R.drawable.ucok_image_5));
        allItems.add(new ItemObject("PHBD", "Vildansvagen 19, Lund Sweden","Panti Asuhan Darul Ilmi. 22/07/2017", R.drawable.image_fokopok_3));
        allItems.add(new ItemObject("Jalan Santai", "3 Villa Crescent London, UK","Panti Asuhan Darul Ilmi. 22/07/2017", R.drawable.ucok_image_2));
        allItems.add(new ItemObject("Balap Lari", "Victoria Island Lagos, Nigeria","Panti Asuhan Darul Ilmi. 22/07/2017", R.drawable.ucok_image_3));
        allItems.add(new ItemObject("Lomba Renang", "New Heaven Enugu, Nigeria","Panti Asuhan Darul Ilmi. 22/07/2017", R.drawable.ucok_image_4));
        allItems.add(new ItemObject("Syiar Ramadhan", "Italion Gata, Padova, Italy","Panti Asuhan Darul Ilmi. 22/07/2017", R.drawable.ucok_image_5));

        return allItems;
    }
}
