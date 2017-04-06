package com.hi_depok.hi_depok.Sikepok_Panic;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.hi_depok.hi_depok.R;

/**
 * Provides UI for the view with List.
 */
public class ApotekFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView avator;
        public TextView name;
        public TextView jarak;
        public TextView description;
        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_list, parent, false));
            avator = (ImageView) itemView.findViewById(R.id.list_avatar);
            name = (TextView) itemView.findViewById(R.id.list_title);
            description = (TextView) itemView.findViewById(R.id.list_desc);
            jarak = (TextView) itemView.findViewById(R.id.list_jarak);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra(DetailActivity.EXTRA_POSITION, getAdapterPosition());
                    context.startActivity(intent);
                }
            });
        }
    }

    /**
     * Adapter to display recycler view.
     */
    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of List in RecyclerView.
        private static final int LENGTH = 10;

        private final String[] arrNama;
        private final String[] arrJarak;
        private final String[] arrDeskripsi;
        private final Drawable[] arrFoto;

        public ContentAdapter(Context context) {
            Resources resources = context.getResources();
            arrNama = resources.getStringArray(R.array.panic_nama);
            arrJarak = resources.getStringArray(R.array.panic_jarak);
            arrDeskripsi = resources.getStringArray(R.array.panic_deskripsi);
            TypedArray a = resources.obtainTypedArray(R.array.place_avator);
            arrFoto = new Drawable[a.length()];
            for (int i = 0; i < arrFoto.length; i++) {
                arrFoto[i] = a.getDrawable(i);
            }
            a.recycle();
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.avator.setImageDrawable(arrFoto[position % arrFoto.length]);
            holder.name.setText(arrNama[position % arrNama.length]);
            holder.jarak.setText(arrJarak[position % arrJarak.length]);
            holder.description.setText(arrDeskripsi[position % arrDeskripsi.length]);
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }

}

