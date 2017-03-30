package com.hi_depok.hi_depok.Kapok;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.Kadepok.KadepokDetailActivity;
import com.hi_depok.hi_depok.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KapokActivity extends BaseActivity implements AdapterView.OnItemSelectedListener  {
    private Button temukan;
    private RadioGroup sort;
    private RadioButton urut;
    ImageView join;
    //array recycleview menu sort
    ArrayList<String> namalogo = new ArrayList<>(Arrays.asList(
            "Wisata", "Warung Pancong Pak Kumis & Mang Dadang",
            "Bebek & Ayam Goreng Pak Endut", "Wisata",
            "Talaga Seafood Restaurant", "Kolam Renang Tirta Sari",
            "Waroeng SS Spesial Sambal", "Wisata",
            "Kuliner", "Wisata"));
    ArrayList<Integer> gambarlogo = new ArrayList<>(Arrays.asList(
            R.drawable.wisata, R.drawable.ucok_image_4,
            R.drawable.ucok_image_4, R.drawable.wisata,
            R.drawable.ucok_image_4, R.drawable.wisata,
            R.drawable.ucok_image_4, R.drawable.wisata,
            R.drawable.ucok_image_4, R.drawable.wisata));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kapok_user_kapok);
        super.onCreateDrawer();
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner pilihan = (Spinner) findViewById(R.id.pilihan);
        Spinner camat = (Spinner) findViewById(R.id.camat);
        sort=(RadioGroup)findViewById(R.id.sort);
        temukan=(Button)findViewById(R.id.temukan);

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


        temukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId= sort.getCheckedRadioButtonId();
                urut=(RadioButton)findViewById(selectedId);
            }
        });

        //join


        //recycleview
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // set a GridLayoutManager with 2 number of columns , horizontal gravity and false value for reverseLayout to show the items from start to end
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        CustomAdapterMenuSort customAdapter = new CustomAdapterMenuSort(KapokActivity.this, namalogo,gambarlogo);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
}

    //popup
    private PopupWindow pwindo;

    public void initiatepopup() {
        try {
            LayoutInflater inflater = (LayoutInflater) KapokActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.kapok_popup_layout, (ViewGroup) findViewById(R.id.popup_element));

            pwindo = new PopupWindow(layout, 450, 750, true);
            pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);
            pwindo.setOutsideTouchable(true);
            pwindo.setFocusable(true);
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
    @Override
    public void onBackPressed() {
        if (pwindo != null) {
            if (pwindo.isShowing()) {
                pwindo.dismiss();
            }
        } else {
            finish();
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_kapok, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_join:
                // todo: goto back activity from here

                startActivity(new Intent(KapokActivity.this, join_kapok.class));
                return true;

            case android.R.id.home:
                // todo: goto back activity from here

                KapokActivity.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}