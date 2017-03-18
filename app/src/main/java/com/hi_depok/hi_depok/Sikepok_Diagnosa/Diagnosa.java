package com.hi_depok.hi_depok.Sikepok_Diagnosa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.hi_depok.hi_depok.R;

public class Diagnosa extends Activity {
    ListView list;
    String[] itemname = {
            "Kepala",
            "Tangan",
            "Kaki",
            "Dada",
            "Updomain",
            "Punggung",
            "Kaki",
            "Kepala"
    };
    Integer[] imgid = {
            R.drawable.capture1,
            R.drawable.capture2,
            R.drawable.capture3,
            R.drawable.capture4,
            R.drawable.capture5,
            R.drawable.capture6,
            R.drawable.capture3,
            R.drawable.capture1,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosa);

        CustomListAdapter adapter = new CustomListAdapter(this, itemname, imgid);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == 0) {
                    Intent contoh = new Intent(Diagnosa.this, Anggota_badan_badan.class);
                    startActivity(contoh);
                }
                if (position == 1) {
                    Intent contoh = new Intent(Diagnosa.this, Anggota_badan_badan.class);
                    startActivity(contoh);
                }
                if (position == 2) {
                    Intent contoh = new Intent(Diagnosa.this, Anggota_badan_badan.class);
                    startActivity(contoh);
                }
                if (position == 3) {
                    Intent contoh = new Intent(Diagnosa.this, Anggota_badan_badan.class);
                    startActivity(contoh);
                }
                if (position == 4) {
                    Intent contoh = new Intent(Diagnosa.this, Anggota_badan_badan.class);
                    startActivity(contoh);
                }
                if (position == 5) {
                    Intent contoh = new Intent(Diagnosa.this, Anggota_badan_badan.class);
                    startActivity(contoh);
                }
                if (position == 6) {
                    Intent contoh = new Intent(Diagnosa.this, Anggota_badan_badan.class);
                    startActivity(contoh);
                }
                if (position == 7) {
                    Intent contoh = new Intent(Diagnosa.this, Anggota_badan_badan.class);
                    startActivity(contoh);
                }
            }
        });

    }
    public void back (View view){
        Intent contoh = new Intent(Diagnosa.this, SikepokDiagnosa.class);
        startActivity(contoh);
    }
}
