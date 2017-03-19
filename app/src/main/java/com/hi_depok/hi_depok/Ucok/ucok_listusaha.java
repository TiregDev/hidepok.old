package com.hi_depok.hi_depok.Ucok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hi_depok.hi_depok.R;

import java.util.ArrayList;
import java.util.List;

public class ucok_listusaha extends AppCompatActivity {
    private LinearLayoutManager lLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ucok_list_usaha);
        List<itemObject_listusaha> rowListItem = getAllItemList();
        lLayout = new LinearLayoutManager(ucok_listusaha.this);

        RecyclerView rView = (RecyclerView)findViewById(R.id.list_usaha);
        rView.setLayoutManager(lLayout);

        RecyclerViewAdapter_listusaha rcAdapter = new RecyclerViewAdapter_listusaha(ucok_listusaha.this, rowListItem);
        rView.setAdapter(rcAdapter);
    }

    public List<itemObject_listusaha> getAllItemList() {
        List<itemObject_listusaha> allItems = new ArrayList<>();
        allItems.add(new itemObject_listusaha("ITechno Cup", R.string.item_desc));
        allItems.add(new itemObject_listusaha("Studi Banding", R.string.item_desc));
        allItems.add(new itemObject_listusaha("Kabir", R.string.item_desc));
        return allItems;
    }
}
