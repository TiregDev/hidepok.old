package com.hi_depok.hi_depok.fokopok;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hi_depok.hi_depok.Activity_Main.fokopok;
import com.hi_depok.hi_depok.Lapok.fragment.fragment2;
import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.Ucok.ItemObject;
import com.hi_depok.hi_depok.Ucok.RecyclerViewHolders;
import com.hi_depok.hi_depok.Ucok.UcokDetailActivity;

import java.util.List;

public class fokopok_content extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fokopok_content);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView picture;
        public ImageView avatar;
        public TextView username;
        public TextView time;
        public TextView title;
        public TextView description;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.fokopok_item_list, parent, false));

            picture = (ImageView) itemView.findViewById(R.id.card_image);
            avatar = (ImageView) itemView.findViewById(R.id.avatar);
            username = (TextView) itemView.findViewById(R.id.username);
            time = (TextView) itemView.findViewById(R.id.time);
            title    = (TextView) itemView.findViewById(R.id.card_title);
            description = (TextView) itemView.findViewById(R.id.card_text);
            ImageButton button = (ImageButton)itemView.findViewById(R.id.like);
            button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), ("Anda telah menyukai content ini"),
                            Toast.LENGTH_LONG).show();
                }
            });

            ImageButton favoriteImageButton =
                    (ImageButton) itemView.findViewById(R.id.comment_button);
            favoriteImageButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), ("Anda telah memilih comment"),
                            Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    /**
     * Adapter to display recycler view.
     */
    public class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of Card in RecyclerView.
        private static final int LENGTH = 18;

        private final String mPlaces;
        private final String mPlaceDesc;
        private final Drawable mPlacePictures;
        private final Drawable mAvatar;
        private final String mUsername;
        private final String mTime;


        public ContentAdapter(Context context) {
            Resources resources = context.getResources();
            mPlaces = "Computer Student Club";
            mPlaceDesc = "Kompetisi Networking di PNJ";
            mAvatar = resources.getDrawable(R.drawable.profile);
            mUsername = "Fajar Zakaria";
            mTime = "Feb 8, 2017 at 17.00 WIB";
//            TypedArray a = resources.obtainTypedArray(R.array.places_picture);
            mPlacePictures = resources.getDrawable(R.drawable.image_fokopok_1);
//            for (int i = 0; i < mPlacePictures.length; i++) {
//                mPlacePictures[i] = a.getDrawable(i);
//            }
//            a.recycle();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.avatar.setImageDrawable(mAvatar);
            holder.username.setText(mUsername);
            holder.time.setText(mTime);
            holder.title.setText(mPlaces);
            holder.description.setText(mPlaceDesc);
            holder.picture.setImageDrawable(mPlacePictures);
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }

    public void fokopok_activity(View v){
        Intent intent = new Intent(fokopok_content.this, fokopok.class);
        startActivity(intent);
    }
}

