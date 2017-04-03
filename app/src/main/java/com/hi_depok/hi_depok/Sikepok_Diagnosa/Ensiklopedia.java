package com.hi_depok.hi_depok.Sikepok_Diagnosa;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.R;

public class Ensiklopedia extends BaseActivity implements SearchView.OnQueryTextListener {
    private LinearLayoutManager lLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sikepokdiagnosa_ensiklopedia);
        super.onCreateDrawer();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        List<itemObject_listensi> rowListItem3 = getAllItemList();
        lLayout = new LinearLayoutManager(getApplicationContext());

        RecyclerView rView = (RecyclerView)findViewById(R.id.list_ensiklopedia);
        rView.setLayoutManager(lLayout);

        RecyclerViewAdapter_listensi rcAdapter = new RecyclerViewAdapter_listensi(getApplicationContext(), rowListItem3);
        rView.setAdapter(rcAdapter);

    }

    private List<itemObject_listensi> getAllItemList(){
        List<itemObject_listensi> allItems = new ArrayList<>();
        allItems.add(new itemObject_listensi("Kepala"));
        allItems.add(new itemObject_listensi("Dada"));
        allItems.add(new itemObject_listensi("Punggung"));
        allItems.add(new itemObject_listensi("Perut"));
        allItems.add(new itemObject_listensi("Tangan"));
        allItems.add(new itemObject_listensi("Kaki"));

        return allItems;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                Ensiklopedia.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onQueryTextChange(String query) {
        // Here is where we are going to implement the filter logic
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

}



