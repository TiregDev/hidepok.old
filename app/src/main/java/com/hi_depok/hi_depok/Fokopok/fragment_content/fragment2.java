package com.hi_depok.hi_depok.Fokopok.fragment_content;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ceylonlabs.imageviewpopup.ImagePopup;
import com.hi_depok.hi_depok.Activity_Main.fokopok;
import com.hi_depok.hi_depok.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by User on 19/03/17.
 */

public class fragment2 extends Fragment {
    private LinearLayoutManager lLayout;

    public static fragment2 newInstance(){
        Bundle args = new Bundle();

        fragment2 fragment = new fragment2();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.fokopok_fragment2_content, container, false);
        com.hi_depok.hi_depok.Fokopok.fragment_content.fragment2.ContentAdapter adapter = new com.hi_depok.hi_depok.Fokopok.fragment_content.fragment2.ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView picture, like;
        public ImageView avatar;
        public TextView username;
        public TextView time;
        public TextView title;
        public TextView description, jumlah_like;
        public CircleImageView imageView;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.fokopok_item_list, parent, false));
            final ImagePopup imagePopup = new ImagePopup(getContext());
            imagePopup.setBackgroundColor(Color.TRANSPARENT);
            imagePopup.setWindowWidth(800);
            imagePopup.setWindowHeight(800);
            imagePopup.setHideCloseIcon(true);
            imagePopup.setImageOnClickClose(true);

            imageView = (CircleImageView) itemView.findViewById(R.id.avatar);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /** Initiate Popup view **/
                    imagePopup.initiatePopup(imageView.getDrawable());
                }
            });

            picture = (ImageView) itemView.findViewById(R.id.card_image);
            like = (ImageButton) itemView.findViewById(R.id.like);
            avatar = (ImageView) itemView.findViewById(R.id.avatar);
            username = (TextView) itemView.findViewById(R.id.username);
            time = (TextView) itemView.findViewById(R.id.time);
            title    = (TextView) itemView.findViewById(R.id.card_title);
            description = (TextView) itemView.findViewById(R.id.card_text);
            ImageButton favoriteImageButton = (ImageButton) itemView.findViewById(R.id.like);
            jumlah_like = (TextView) itemView.findViewById(R.id.jumlah_like);
            favoriteImageButton.setOnClickListener(new View.OnClickListener() {
                Drawable myDrawable = getResources().getDrawable(R.drawable.favorite);

                @Override
                public void onClick(View v) {
                    jumlah_like.setText("294");
                    like.setImageDrawable(myDrawable);
                    Toast.makeText(v.getContext(), ("Anda telah menyukai content ini"),
                            Toast.LENGTH_LONG).show();
                }
            });

            favoriteImageButton = (ImageButton) itemView.findViewById(R.id.comment_button);
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
            mPlacePictures = resources.getDrawable(R.drawable.image_placeholder);
//            for (int i = 0; i < mPlacePictures.length; i++) {
//                mPlacePictures[i] = a.getDrawable(i);
//            }
//            a.recycle();
        }

        @Override
        public com.hi_depok.hi_depok.Fokopok.fragment_content.fragment2.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new com.hi_depok.hi_depok.Fokopok.fragment_content.fragment2.ViewHolder(LayoutInflater.from(parent.getContext()), parent);
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

}

