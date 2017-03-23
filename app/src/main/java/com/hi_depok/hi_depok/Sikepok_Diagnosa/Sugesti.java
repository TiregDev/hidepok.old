package com.hi_depok.hi_depok.Sikepok_Diagnosa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

import com.hi_depok.hi_depok.R;

public class Sugesti extends AppCompatActivity {
    private LinearLayoutManager lLayout;
    String[] items;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugesti);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        List<itemObject_listsugesti> rowListItem3 = getAllItemList();
        lLayout = new LinearLayoutManager(this);

        RecyclerView rView = (RecyclerView)findViewById(R.id.list_sugesti);
        rView.setLayoutManager(lLayout);

        RecyclerViewAdapter_listsugesti rcAdapter = new RecyclerViewAdapter_listsugesti(this, rowListItem3);
        rView.setAdapter(rcAdapter);
    }

    public List<itemObject_listsugesti> getAllItemList() {
        List<itemObject_listsugesti> allItems = new ArrayList<>();
        allItems.add(new itemObject_listsugesti("Mata Katarak"));
        allItems.add(new itemObject_listsugesti("Mata Merah"));
        allItems.add(new itemObject_listsugesti("Mata Kabur"));
        return allItems;
    }
}

