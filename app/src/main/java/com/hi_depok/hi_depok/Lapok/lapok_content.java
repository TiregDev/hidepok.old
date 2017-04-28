package com.hi_depok.hi_depok.Lapok;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.Lapok.mData.Laporan;
import com.hi_depok.hi_depok.Lapok.mData.List_Laporan;
import com.hi_depok.hi_depok.Lapok.mRecyler.Adapter_Laporan;
import com.hi_depok.hi_depok.R;

import java.util.ArrayList;

public class lapok_content extends BaseActivity implements List_Laporan.listLaporan_interface {

    private Spinner category, sortby;
    String kategori, status;
    Adapter_Laporan mAdapter;
    ArrayList<Laporan> mList = new ArrayList<>();
    RecyclerView rv;

    private static final String[] cate = {"", "Sampah", "Kebakaran", "Kemacetan",
            "Bencana Alam", "Pelanggaran", "Jalan Rusak", "Tindak Kriminal", "Terorisme", "Narkoba"};
    private static final String[] stats = {"", "Menunggu", "Proses", "Selesai"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lapok_content);
        super.onCreateDrawer();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView cameraBtn = (ImageView) findViewById(R.id.cameraBtn);
        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(lapok_content.this, lapok_ambil_kejadian.class));
            }
        });

        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);
        rv.setAdapter(new Adapter_Laporan(lapok_content.this, new ArrayList<Laporan>()));

        /*-------------------------- START SPINER CATEGORY --------------------------------------- */
        category = (Spinner) findViewById(R.id.category);
        ArrayAdapter<String> adapter_cate = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, cate);

        adapter_cate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter_cate);
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                kategori = adapterView.getItemAtPosition(position).toString();
                if (!kategori.equals("")) {
                    Toast.makeText(lapok_content.this, kategori + " selected", Toast.LENGTH_LONG).show();
                }
                List_Laporan listLaporan = new List_Laporan(lapok_content.this);
                listLaporan.registerForListener(lapok_content.this);
                listLaporan.getLaporan(kategori, status);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
         /*-------------------------- END SPINER CATEGORY ---------------------------------------- */

        /*-------------------------- START SPINER SORTBY --------------------------------------- */
        sortby = (Spinner) findViewById(R.id.sortby);
        ArrayAdapter<String> adapter_sort = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, stats);

        adapter_sort.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortby.setAdapter(adapter_sort);
        sortby.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                status = adapterView.getItemAtPosition(position).toString();
                if (!status.equals("")) {
                    Toast.makeText(lapok_content.this, status + " selected", Toast.LENGTH_LONG).show();
                }
                List_Laporan listLaporan = new List_Laporan(lapok_content.this);
                listLaporan.registerForListener(lapok_content.this);
                listLaporan.getLaporan(kategori, status);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public void onGetList(ArrayList<Laporan> laporanArrayList) {
        mAdapter = new Adapter_Laporan(lapok_content.this, laporanArrayList);
        rv.setAdapter(null);
        rv.setAdapter(mAdapter);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                lapok_content.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
