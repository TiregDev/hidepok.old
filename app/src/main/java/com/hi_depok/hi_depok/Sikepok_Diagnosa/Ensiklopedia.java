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

public class Ensiklopedia extends AppCompatActivity {
    String[] items;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;
    ListView listView;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ensiklopedia);
        listView=(ListView)findViewById(R.id.list_view);
        editText=(EditText)findViewById(R.id.editText);
        initList();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().equals("")) {
                    initList();
                }
                else{
                    searchItem(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    public void searchItem(String textToSearch){
        for(String item:items){
            if(!item.contains(textToSearch)){
                listItems.remove(item);
            }
        }
        adapter.notifyDataSetChanged();
    }
    public void initList(){
        items=new String[]{"Mata Katarak", "Rematik", "Encok", "Bau badan", "Tulang patah", "Kanker", "Mata minus", "Palsu"};
        listItems=new ArrayList<>(Arrays.asList(items));
        adapter=new ArrayAdapter<String>(this, R.layout.item_ensiklopedia, R.id.textView, listItems);
        listView.setAdapter(adapter);

    }
    public void back (View view){
        Intent contoh = new Intent(Ensiklopedia.this, SikepokDiagnosa.class);
        startActivity(contoh);
    }
    public void deskripsi (View view){
        Intent contoh = new Intent(Ensiklopedia.this, Deskripsi.class);
        startActivity(contoh);
    }
}

