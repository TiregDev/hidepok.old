package com.hi_depok.hi_depok.Sikepok_Diagnosa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;
import com.hi_depok.hi_depok.R;

public class Sugesti extends AppCompatActivity {
    String[] items;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;
    ListView listView;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugesti);
        listView=(ListView)findViewById(R.id.list);
        initList();
    }

    public void initList(){
        items=new String[]{"Mata Katarak", "Jidat", "Hidung", "Mulut", "telinga"};
        listItems=new ArrayList<>(Arrays.asList(items));
        adapter=new ArrayAdapter<String>(this, R.layout.item_sugesti, R.id.textView16, listItems);
        listView.setAdapter(adapter);
    }
    public void back (View view){
        Intent contoh = new Intent(Sugesti.this, Anggota_badan_badan.class);
        startActivity(contoh);
    }
    public void deskripsi (View view){
        Intent contoh = new Intent(Sugesti.this, Deskripsi.class);
        startActivity(contoh);
    }
}

