package com.hi_depok.hi_depok.Sikepok_RS;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.R;

public class rs_terdekat extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sikepokrs_rs_terdekat);
        super.onCreateDrawer();
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyView);
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
//                R.layout.recycler_view, container, false);
//        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
//        recyclerView.setAdapter(adapter);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView gambarRS;
        public TextView namaRS;
        public TextView alamat;
        public TextView jarak1;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.sikepokrs_fragment_daftar_rs, parent, false));
            gambarRS = (ImageView) itemView.findViewById(R.id.gambarRS);
            namaRS = (TextView) itemView.findViewById(R.id.namaRS);
            alamat = (TextView) itemView.findViewById(R.id.alamatRS);
            jarak1 = (TextView) itemView.findViewById(R.id.jarak1);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Context context = v.getContext();
//                    Intent intent = new Intent(context, DetailActivity.class);
//                    intent.putExtra(DetailActivity.EXTRA_POSITION, getAdapterPosition());
//                    context.startActivity(intent);
                }
            });
        }
    }

    /**
     * Adapter to display recycler view.
     */
    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of List in RecyclerView.
        private static final int LENGTH = 18;

        private final String[] ALAMATRS;
        private final String[] JARAKRS;
        private final String[] NAMARS;
        private final Drawable[] FOTORS;

        public ContentAdapter(Context context) {
            Resources resources = context.getResources();
            NAMARS = resources.getStringArray(R.array.NAMARS);
            ALAMATRS = resources.getStringArray(R.array.ALAMATRS);
            JARAKRS = resources.getStringArray(R.array.JARAKRS);
            TypedArray a = resources.obtainTypedArray(R.array.FOTORS);
            FOTORS = new Drawable[a.length()];
            for (int i = 0; i < FOTORS.length; i++) {
                FOTORS[i] = a.getDrawable(i);
            }
            a.recycle();
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.gambarRS.setImageDrawable(FOTORS[position % FOTORS.length]);
            holder.namaRS.setText(NAMARS[position % NAMARS.length]);
            holder.alamat.setText(ALAMATRS[position % ALAMATRS.length]);
            holder.jarak1.setText(JARAKRS[position % JARAKRS.length]);
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }



    public void ke_daftar_rs(View view){
        Intent next = new Intent(rs_terdekat.this, daftar_rs.class);
        startActivity(next);
    }

    public void ke_menu_rs(View view){
        Intent next = new Intent(rs_terdekat.this, menu_rs.class);
        startActivity(next);
    }

}
