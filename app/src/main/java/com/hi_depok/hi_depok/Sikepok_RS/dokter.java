package com.hi_depok.hi_depok.Sikepok_RS;

/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */




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
import android.widget.Toast;

import com.hi_depok.hi_depok.R;

/**
 * Provides UI for the view with Cards.
 */
public class dokter extends Fragment {



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
        public ImageView fotoDokter;
        public TextView namaDokter;
        public TextView spesialisasi;
        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.sikepokrs_fragment_dokter, parent, false));
            fotoDokter = (ImageView) itemView.findViewById(R.id.fotoDokter);
            namaDokter = (TextView) itemView.findViewById(R.id.namaDokter);
            spesialisasi = (TextView) itemView.findViewById(R.id.spesialisasiDokter);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, info_dokter.class);
//                   intent.putExtra(DetailActivity.EXTRA_POSITION, getAdapterPosition());
                    context.startActivity(intent);
//                    Toast.makeText(context,"Dokter telah dipilih",Toast.LENGTH_SHORT).show();
                }
            });

//            // Adding Snackbar to Action Button inside card
//            Button button = (Button)itemView.findViewById(R.id.action_button);
//            button.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View v) {
//                    Snackbar.make(v, "Action is pressed",
//                            Snackbar.LENGTH_LONG).show();
//                }
//            });
//
//            ImageButton favoriteImageButton =
//                    (ImageButton) itemView.findViewById(R.id.favorite_button);
//            favoriteImageButton.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View v) {
//                    Snackbar.make(v, "Added to Favorite",
//                            Snackbar.LENGTH_LONG).show();
//                }
//            });
//
//            ImageButton shareImageButton = (ImageButton) itemView.findViewById(R.id.share_button);
//            shareImageButton.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View v) {
//                    Snackbar.make(v, "Share article",
//                            Snackbar.LENGTH_LONG).show();
//                }
//            });
        }
    }

    /**
     * Adapter to display recycler view.
     */
    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of Card in RecyclerView.
        private static final int LENGTH = 18;

        private final String[] NAMADOKTER;
        private final String[] SPESIALISASI;
        private final Drawable[] FOTODOKTER;

        public ContentAdapter(Context context) {
            Resources resources = context.getResources();
            NAMADOKTER = resources.getStringArray(R.array.NAMADOKTER);
            SPESIALISASI = resources.getStringArray(R.array.SPESIALIS);
            TypedArray a = resources.obtainTypedArray(R.array.FOTODOKTER);
            FOTODOKTER = new Drawable[a.length()];
            for (int i = 0; i < FOTODOKTER.length; i++) {
                FOTODOKTER[i] = a.getDrawable(i);
            }
            a.recycle();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.fotoDokter.setImageDrawable(FOTODOKTER[position % FOTODOKTER.length]);
            holder.namaDokter.setText(NAMADOKTER[position % NAMADOKTER.length]);
            holder.spesialisasi.setText(SPESIALISASI[position % SPESIALISASI.length]);
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }
}

