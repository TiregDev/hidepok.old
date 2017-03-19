package com.hi_depok.hi_depok.Kapok;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import com.hi_depok.hi_depok.R;
public class KapokActivity extends Activity implements AdapterView.OnItemSelectedListener  {
    private Button temukan;
    private RadioGroup sort;
    private RadioButton urut;
    ImageView join;


    GridView androidGridView;

    String[] gridViewString = {
            "Wisata", "Warung Pancong Pak Kumis & Mang Dadang",
            "Bebek & Ayam Goreng Pak Endut", "Wisata",
            "Talaga Seafood Restaurant", "Kolam Renang Tirta Sari",
            "Waroeng SS Spesial Sambal", "Wisata",
            "Kuliner", "Wisata"
    };
    int[] gridViewImageId = {
            R.drawable.wisata, R.drawable.kuliner,
            R.drawable.kuliner, R.drawable.wisata,
            R.drawable.kuliner, R.drawable.wisata,
            R.drawable.kuliner, R.drawable.wisata,
            R.drawable.kuliner, R.drawable.wisata
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_kapok);

        Spinner pilihan = (Spinner) findViewById(R.id.pilihan);
        Spinner camat = (Spinner) findViewById(R.id.camat);


        // Spinner click listener
        pilihan.setOnItemSelectedListener(this);
        List<String> pilih = new ArrayList<String>();
        pilih.add("Pilihan");
        pilih.add("Kuliner");
        pilih.add("Wisata");
        pilih.add("Pasar");
        pilih.add("Tempat Ibadah");
        pilih.add("GOR");

        ArrayAdapter<String> pilihAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pilih);
        pilihAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        pilihan.setAdapter(pilihAdapter);

        camat.setOnItemSelectedListener(this);
        List<String> wilayah = new ArrayList<String>();
        wilayah.add("Kecamatan");
        wilayah.add("Beji");
        wilayah.add("Cilodong");
        wilayah.add("Cimanggis");
        wilayah.add("Cinere");
        wilayah.add("Cipayung");
        wilayah.add("Limo");
        wilayah.add("Pancoran Mas");
        wilayah.add("Sawangan");
        wilayah.add("Tapos");
        wilayah.add("Bojongsari");
        wilayah.add("Sukmajaya");

        ArrayAdapter<String> camatAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, wilayah);
        camatAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        camat.setAdapter(camatAdapter);

        sort=(RadioGroup)findViewById(R.id.sort);

        temukan=(Button)findViewById(R.id.temukan);

        temukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId= sort.getCheckedRadioButtonId();
                urut=(RadioButton)findViewById(selectedId);
            }
        });



        grid_menu adapterViewAndroid = new grid_menu(KapokActivity.this, gridViewString, gridViewImageId);
        androidGridView=(GridView)findViewById(R.id.grid_view_image_text);
        androidGridView.setAdapter(adapterViewAndroid);
        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                initiatepopup();
            }
        });

        ImageView join = (ImageView) findViewById(R.id.join);

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KapokActivity.this, join_kapok.class));
            }
        });

    }

    private PopupWindow pwindo;

    private void initiatepopup() {
        try {
            LayoutInflater inflater = (LayoutInflater) KapokActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.popup_layout, (ViewGroup) findViewById(R.id.popup_element));

            pwindo = new PopupWindow(layout, 430, 660, true);
            pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);

            Button selengkapnya = (Button) layout.findViewById(R.id.next);
            selengkapnya.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(KapokActivity.this, activity_selengkapnya.class));
                }
            });
            Button close = (Button) layout.findViewById(R.id.close);
            close.setOnClickListener(cancel_button_click_listener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View.OnClickListener cancel_button_click_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            pwindo.dismiss();
        }
    };


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }
    //button join


}