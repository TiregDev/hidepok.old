package com.hi_depok.hi_depok.Ucok;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.Activity_Main.ucok;
import com.hi_depok.hi_depok.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muhammad63 on 3/13/2017.
 */

public class ucok_content extends BaseActivity {
    private LinearLayoutManager lLayout;
    private Spinner category, sortby;


    private static final String[]cate = {"Kategori","Makanan", "Minuman", "Barang Custom"};
    private static final String[]sort = {"Urutkan","Populer", "Harga", "A-Z"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ucok_recycler_view);
        super.onCreateDrawer();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        List<ItemObject> rowListItem = getAllItemList();
        lLayout = new LinearLayoutManager(ucok_content.this);

        RecyclerView rView = (RecyclerView)findViewById(R.id.recycler_view);
        rView.setLayoutManager(lLayout);

        RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(ucok_content.this, rowListItem);
        rView.setAdapter(rcAdapter);

        /*-------------------------- START SPINER CATEGORY --------------------------------------- */
        category = (Spinner)findViewById(R.id.category);
        ArrayAdapter<String> adapter_cate = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,cate);

        adapter_cate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter_cate);
        category.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        Toast.makeText(getApplicationContext(), ("Anda telah memilih makanan"),
                                Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Toast.makeText(getApplicationContext(), ("Anda telah memilih minuman"),
                                Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        Toast.makeText(getApplicationContext(), ("Anda telah memilih Barang Custom"),
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

        /*-------------------------- START SPINER SORTBY --------------------------------------- */
        sortby = (Spinner)findViewById(R.id.sortby);
        ArrayAdapter<String> adapter_sort = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,sort);

        adapter_sort.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortby.setAdapter(adapter_sort);
        sortby.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        Toast.makeText(getApplicationContext(), ("Anda telah memilih sesuai populer"),
                                Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Toast.makeText(getApplicationContext(), ("Anda telah memilih sesuai harga"),
                                Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        Toast.makeText(getApplicationContext(), ("Anda telah memilih sesuai urutan A-Z"),
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
                ucok_content.this.finish();
                return true;
//            case R.id.action_share:
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private List<ItemObject> getAllItemList(){
        List<ItemObject> allItems = new ArrayList<>();
        allItems.add(new ItemObject("Peter James", "Vildansvagen 19, Lund Sweden","IDR 1k/Item", R.drawable.ucok_image_1));
        allItems.add(new ItemObject("Henry Jacobs", "3 Villa Crescent London, UK","IDR 1,5k/Item", R.drawable.ucok_image_2));
        allItems.add(new ItemObject("Bola Olumide", "Victoria Island Lagos, Nigeria","IDR 5k/Item", R.drawable.ucok_image_3));
        allItems.add(new ItemObject("Chidi Johnson", "New Heaven Enugu, Nigeria","IDR 7k/Item", R.drawable.ucok_image_4));
        allItems.add(new ItemObject("DeGordio Puritio", "Italion Gata, Padova, Italy","IDR 3k/Item", R.drawable.ucok_image_5));
        allItems.add(new ItemObject("Peter James", "Vildansvagen 19, Lund Sweden","IDR 1k/Item", R.drawable.ucok_image_1));
        allItems.add(new ItemObject("Henry Jacobs", "3 Villa Crescent London, UK","IDR 1,5k/Item", R.drawable.ucok_image_2));
        allItems.add(new ItemObject("Bola Olumide", "Victoria Island Lagos, Nigeria","IDR 5k/Item", R.drawable.ucok_image_3));
        allItems.add(new ItemObject("Chidi Johnson", "New Heaven Enugu, Nigeria","IDR 7k/Item", R.drawable.ucok_image_4));
        allItems.add(new ItemObject("DeGordio Puritio", "Italion Gata, Padova, Italy","IDR 3k/Item", R.drawable.ucok_image_5));

        return allItems;
    }

}