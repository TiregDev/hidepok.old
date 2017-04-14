package com.hi_depok.hi_depok.Kapok;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 4/7/2017.
 */

public class kapok_detail extends BaseActivity {
    private LinearLayoutManager lLayout;
    public static final String EXTRA_POSITION = "position";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kapok_detail);
        super.onCreateDrawer();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, android.R.color.transparent));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Set Collapsing Toolbar layout to the screen
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        // Set title of Detail page

        int postion = getIntent().getIntExtra(EXTRA_POSITION, 0);
        Resources resources = getResources();
        String[] places = resources.getStringArray(R.array.panic_nama);
        collapsingToolbar.setTitle(places[postion % places.length]);
        collapsingToolbar.getExpandedTitleMarginBottom();

        String[] placeDetails = resources.getStringArray(R.array.place_details);
        TextView placeDetail = (TextView) findViewById(R.id.place_detail);
        placeDetail.setText(placeDetails[postion % placeDetails.length]);

        String[] placeLocations = resources.getStringArray(R.array.panic_deskripsi);
        TextView placeLocation = (TextView) findViewById(R.id.place_location);
        placeLocation.setText(placeLocations[postion % placeLocations.length]);

        TypedArray placePictures = resources.obtainTypedArray(R.array.places_picture2);
        ImageView placePicture = (ImageView) findViewById(R.id.image);
        placePicture.setImageDrawable(placePictures.getDrawable(postion % placePictures.length()));

        placePictures.recycle();

        TextView lengkap = (TextView) findViewById(R.id.ulasan);
        lengkap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(kapok_detail.this, activity_view_ulasan.class));
            }
        });
        //recycle ulasan
        List<ItemObjectViewUlasan> rowListItem = getAllItemList();
        lLayout = new LinearLayoutManager(kapok_detail.this);

        RecyclerView rView = (RecyclerView)findViewById(R.id.recycler_view);
        rView.setLayoutManager(lLayout);

        CustomAdapterViewUlasan rcAdapter = new CustomAdapterViewUlasan(kapok_detail.this, rowListItem);
        rView.setAdapter(rcAdapter);

        //buat popup
        ImageView tambah = (ImageView) findViewById(R.id.tambah);
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initiatepopup();
            }
        });


    }
    //ulasan
    private List<ItemObjectViewUlasan> getAllItemList(){
        List<ItemObjectViewUlasan> allItems = new ArrayList<>();
        allItems.add(new ItemObjectViewUlasan("Kadek Pandu", "12-01-2016 20:01:56", "Makanannya enak dan murah", R.drawable.profile));
        allItems.add(new ItemObjectViewUlasan("Fajar Zakaria", "12-01-2016 21:01:56", "Tempatnya nyaman dan bersih", R.drawable.profile));
        allItems.add(new ItemObjectViewUlasan("Tegar Hidayat", "13-01-2016 08:02:00", "makannya muuaannntebbb!!!!", R.drawable.profile));
        return allItems;
    }

    /*  @Override
      public boolean onOptionsItemSelected(MenuItem item) {
          switch (item.getItemId()) {
              case android.R.id.home:
                  // todo: goto back activity from here

                  DetailActivity.this.finish();
                  return true;

              default:
                  return super.onOptionsItemSelected(item);
          }
      }*/
    public void toMaps(View v){
        Intent intent = new Intent(getBaseContext(), com.hi_depok.hi_depok.Sikepok_Panic.MapsActivity.class);
        startActivity(intent);
    }
    //popup
    private PopupWindow pwindo;

    private void initiatepopup() {
        try {
            LayoutInflater inflater = (LayoutInflater) kapok_detail.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.kapok_popup_ulasan, (ViewGroup) findViewById(R.id.popup_element));
            pwindo = new PopupWindow(layout, 450,300, true);
            pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);
            pwindo.setOutsideTouchable(true);
            pwindo.setFocusable(true);

            ImageView close = (ImageView) layout.findViewById(R.id.close);
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
}


