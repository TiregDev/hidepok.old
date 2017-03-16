package com.hi_depok.hi_depok.Ucok;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;
import android.widget.Toast;

import com.hi_depok.hi_depok.Activity_Main.ucok;
import com.hi_depok.hi_depok.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muhammad63 on 3/13/2017.
 */

public class ucok_content extends AppCompatActivity {
    private LinearLayoutManager lLayout;
    private Spinner category, sortby;


    private static final String[]cate = {"Category","Makanan", "Minuman", "Barang Custom"};
    private static final String[]sort = {"Sort by","Populer", "Harga", "A-Z"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ucok_recycler_view);
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

    public void ucok_home(View v){
        Intent intent = new Intent(ucok_content.this, ucok.class);
        startActivity(intent);
    }
}