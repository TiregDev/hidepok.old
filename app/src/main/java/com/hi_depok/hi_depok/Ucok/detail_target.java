package com.hi_depok.hi_depok.Ucok;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.fokopok.fokopok_content;

public class detail_target extends AppCompatActivity {

    public static final String EXTRA_POSITION = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ucok_detail_target);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.target_recycler);
        detail_target.ContentAdapter adapter = new detail_target.ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        /* ----------------------------- TOOLBAR ----------------------------------------------------------*/
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Set Collapsing Toolbar layout to the screen
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        int postion = getIntent().getIntExtra(EXTRA_POSITION, 0);
        Resources resources = getResources();
        String places = "ITechnoCup";
       collapsingToolbar.setTitle(places);
//
        String placeDetails = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys" +
                "standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make" +
                "a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting," +
                "remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing" +
                "Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum";
       TextView placeDetail = (TextView) findViewById(R.id.detail);
        placeDetail.setText(placeDetails);
//
        Drawable placePictures = resources.getDrawable(R.drawable.image_fokopok_2);
        ImageView placePicutre = (ImageView) findViewById(R.id.image_target);
        placePicutre.setImageDrawable(placePictures);

//        placePictures.recycle();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView harike;
        public EditText input_pendapatan;
//        public Button simpan;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.ucok_list_target, parent, false));

            harike = (TextView) itemView.findViewById(R.id.harike);
            input_pendapatan = (EditText) itemView.findViewById(R.id.input_pendapatan);
            Button button = (Button)itemView.findViewById(R.id.simpan);
            button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), ("Berhasil Menambahkan"),
                            Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of Card in RecyclerView.
        private static final int LENGTH = 7;

        private final String[] mHari;


        public ContentAdapter(Context context) {
            Resources resources = context.getResources();
            mHari = resources.getStringArray(R.array.harike);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.harike.setText(mHari[position % mHari.length]);
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }
}
