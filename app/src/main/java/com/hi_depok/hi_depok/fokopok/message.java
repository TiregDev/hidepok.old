package com.hi_depok.hi_depok.fokopok;

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
import com.hi_depok.hi_depok.Ucok.ItemObject;
import com.hi_depok.hi_depok.Ucok.RecyclerViewAdapter;
import com.hi_depok.hi_depok.Ucok.ucok_content;

import java.util.ArrayList;
import java.util.List;

public class message extends BaseActivity {
    private LinearLayoutManager lLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fokopok_message);
        super.onCreateDrawer();
        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        List<itemObject_message> rowListItem = getAllItemList();
        lLayout = new LinearLayoutManager(message.this);

        RecyclerView rView = (RecyclerView)findViewById(R.id.fokopok_pesan);
        rView.setLayoutManager(lLayout);

        RecyclerViewAdapter_message rcAdapter = new RecyclerViewAdapter_message(message.this, rowListItem);
        rView.setAdapter(rcAdapter);
    }

    private List<itemObject_message> getAllItemList(){
        List<itemObject_message> allItems = new ArrayList<>();
        allItems.add(new itemObject_message("Alian Rakhul Yama (20)", R.drawable.profile));
        allItems.add(new itemObject_message("Ninda Komala (75)", R.drawable.ucok_image_4));

        return allItems;
    }

    public void link_home_content(View v){
        Intent intent = new Intent(message.this,fokopok_content.class);
        startActivity(intent);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                // todo: goto back activity from here

                message.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
