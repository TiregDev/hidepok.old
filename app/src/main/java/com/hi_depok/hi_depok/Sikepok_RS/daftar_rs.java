package com.hi_depok.hi_depok.Sikepok_RS;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.Sikepok_Panic.MenuActivity;

public class daftar_rs extends AppCompatActivity {
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_rs);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar3));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyView);
        rs_terdekat.ContentAdapter adapter = new rs_terdekat.ContentAdapter(recyclerView.getContext());
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                daftar_rs.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        // Retrieve the SearchView and plug it into SearchManager
        final MenuItem searchItem = menu.findItem(R.id.action_search);

        if (searchItem != null) {
            searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
            searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                @Override
                public boolean onClose() {
                    //some operation
                    return false;
                }
            });
            searchView.setOnSearchClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //some operation
                }
            });
            EditText searchPlate = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
            searchPlate.setHint("Search");
            View searchPlateView = searchView.findViewById(android.support.v7.appcompat.R.id.search_plate);
            searchPlateView.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
            // use this method for search process
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    // use this method when query submitted
                    Toast.makeText(daftar_rs.this, query, Toast.LENGTH_SHORT).show();
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    // use this method for auto complete search process
                    return false;
                }
            });
            SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        }
        return super.onCreateOptionsMenu(menu);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView gambarRS;
        public TextView namaRS;
        public TextView alamat;
        public TextView jarak1;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.fragment_daftar_rs, parent, false));
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
    public static class ContentAdapter extends RecyclerView.Adapter<rs_terdekat.ViewHolder> {
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
        public rs_terdekat.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new rs_terdekat.ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(rs_terdekat.ViewHolder holder, int position) {
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
        Intent next = new Intent(daftar_rs.this, daftar_rs.class);
        startActivity(next);
    }

    public void ke_menu_rs(View view){
        Intent next = new Intent(daftar_rs.this, menu_rs.class);
        startActivity(next);
    }

}