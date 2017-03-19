package com.hi_depok.hi_depok.Sikepok_Diagnosa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;

import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.fokopok.RecyclerViewAdapter_message;
import com.hi_depok.hi_depok.fokopok.itemObject_message;
import com.hi_depok.hi_depok.fokopok.message;

import java.util.List;

public class Diagnosa extends AppCompatActivity {
    private LinearLayoutManager lLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosa);
//        List<itemObject_message> rowListItem = getAllItemList();
//        lLayout = new LinearLayoutManager(Diagnosa.this);
//
//        RecyclerView rView = (RecyclerView)findViewById(R.id.list_diagnosa);
//        rView.setLayoutManager(lLayout);
//
//        RecyclerViewAdapter_message rcAdapter = new RecyclerViewAdapter_message(Diagnosa.this, rowListItem);
//        rView.setAdapter(rcAdapter);
    }
}
