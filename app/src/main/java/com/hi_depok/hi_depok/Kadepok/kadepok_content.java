package com.hi_depok.hi_depok.Kadepok;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.R;

import java.util.ArrayList;
import java.util.List;

public class kadepok_content extends BaseActivity {
    private LinearLayoutManager lLayout;
    private Spinner kecamatan;
    public Button close;

    private static final String[]camat = {"Pilih Kecamatan", "Beji", "Bojongsari", "Cilodong", "Cimanggis",
            "Cinere", "Cipayung", "Limo", "Pancoran Mas", "Sawangan", "Sukmajaya", "Tapos"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kadepok_recycler_view);
        super.onCreateDrawer();
        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        List<ItemObject> rowListItem = getAllItemList();
        lLayout = new LinearLayoutManager(kadepok_content.this);

        RecyclerView rView = (RecyclerView)findViewById(R.id.recycler_view);
        rView.setLayoutManager(lLayout);

        RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(kadepok_content.this, rowListItem);
        rView.setAdapter(rcAdapter);

        /*-------------------------- START SPINER CATEGORY --------------------------------------- */
        kecamatan = (Spinner)findViewById(R.id.kecamatan);
        ArrayAdapter<String> adapter_camat = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,camat);

        adapter_camat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kecamatan.setAdapter(adapter_camat);
        kecamatan.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        Toast.makeText(getApplicationContext(), ("Anda telah memilih Beji"),
                                Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Toast.makeText(getApplicationContext(), ("Anda telah memilih Bojongsari"),
                                Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        Toast.makeText(getApplicationContext(), ("Anda telah memilih Cilodong"),
                                Toast.LENGTH_LONG).show();
                        break;
                    case 4:
                        Toast.makeText(getApplicationContext(), ("Anda telah memilih Cimanggis"),
                                Toast.LENGTH_LONG).show();
                        break;
                    case 5:
                        Toast.makeText(getApplicationContext(), ("Anda telah memilih Cinere"),
                                Toast.LENGTH_LONG).show();
                        break;
                    case 6:
                        Toast.makeText(getApplicationContext(), ("Anda telah memilih Cipayung"),
                                Toast.LENGTH_LONG).show();
                        break;
                    case 7:
                        Toast.makeText(getApplicationContext(), ("Anda telah memilih Limo"),
                                Toast.LENGTH_LONG).show();
                        break;
                    case 8:
                        Toast.makeText(getApplicationContext(), ("Anda telah memilih Pancoran Mas"),
                                Toast.LENGTH_LONG).show();
                        break;
                    case 9:
                        Toast.makeText(getApplicationContext(), ("Anda telah memilih Sawangan"),
                                Toast.LENGTH_LONG).show();
                        break;
                    case 10:
                        Toast.makeText(getApplicationContext(), ("Anda telah memilih Sukmajaya"),
                                Toast.LENGTH_LONG).show();
                        break;
                    case 11:
                        Toast.makeText(getApplicationContext(), ("Anda telah memilih Tapos"),
                                Toast.LENGTH_LONG).show();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //NULL
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                kadepok_content.this.finish();
                return true;
//            case R.id.action_share:
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private List<ItemObject> getAllItemList(){
        List<ItemObject> allItems = new ArrayList<>();
        allItems.add(new ItemObject("Amal Mulia", "Jl. Pekapuran Raya, Cimanggis, Depok, Jawa Barat","021-8744898", R.drawable.a));
        allItems.add(new ItemObject("Asuain Timor", "Jl. Cilodong, Cilodong, Depok, Jawa Barat","021-87782190", R.drawable.b));
        allItems.add(new ItemObject("Darul Ilmi", "Jl. Beji, Beji, Depok, Jawa Barat", "021-8766765", R.drawable.c));
        allItems.add(new ItemObject("Huswatun Khasanah", "Jl. Sukmajaya, Sukmajaya, Depok, Jawa Barat","021-8798321", R.drawable.d));
        allItems.add(new ItemObject("Muhammadiyah", "Jl. H. Ahmad, Sawangan, Depok, Jawa Barat","021-8799876", R.drawable.e));
        allItems.add(new ItemObject("Amal Mulia", "Jl. Pekapuran Raya, Cimanggis, Depok, Jawa Barat","021-8744898", R.drawable.a));
        allItems.add(new ItemObject("Asuain Timor", "Jl. Cilodong, Cilodong, Depok, Jawa Barat","021-87782190", R.drawable.b));
        allItems.add(new ItemObject("Darul Ilmi", "Jl. Beji, Beji, Depok, Jawa Barat", "021-8766765", R.drawable.c));
        allItems.add(new ItemObject("Huswatun Khasanah", "Jl. Sukmajaya, Sukmajaya, Depok, Jawa Barat","021-8798321", R.drawable.d));
        allItems.add(new ItemObject("Muhammadiyah", "Jl. H. Ahmad, Sawangan, Depok, Jawa Barat","021-8799876", R.drawable.e));

        return allItems;
    }
}
