

package com.hi_depok.hi_depok.Lapok.fragment;

import android.app.Application;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.hi_depok.hi_depok.Lapok.lapok_content;
import com.hi_depok.hi_depok.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Muhammad63 on 3/16/2017.
 */

public class fragment2 extends Fragment{


    public static fragment2 newInstance() {
        Bundle args = new Bundle();

        fragment2 fragment = new fragment2();
        fragment.setArguments(args);
        return fragment;
    }
    public ArrayList<String> wordList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.fragment2_lapok_content, container, false);
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView picture, avatar, like;
        public Button send;
        public EditText comment;
        public TextView username, time, title, description, jumlah_like, komentar;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.lapok_item_list, parent, false));

            send = (Button) itemView.findViewById(R.id.send);
            comment = (EditText) itemView.findViewById(R.id.comment);

            like = (ImageButton) itemView.findViewById(R.id.like);
            picture = (ImageView) itemView.findViewById(R.id.card_image);
            avatar = (ImageView) itemView.findViewById(R.id.avatar);

            username = (TextView) itemView.findViewById(R.id.username);
            time = (TextView) itemView.findViewById(R.id.time);
            title = (TextView) itemView.findViewById(R.id.card_title);
            description = (TextView) itemView.findViewById(R.id.card_text);
            komentar = (TextView) itemView.findViewById(R.id.labelKomentar);

            final RelativeLayout rl = (RelativeLayout) itemView.findViewById(R.id.rl);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Context context = v.getContext();
//                    Intent intent = new Intent(context, DetailActivity.class);
//                    intent.putExtra(DetailActivity.EXTRA_POSITION, getAdapterPosition());
//                    context.startActivity(intent);
//                }
//            });

            // Adding Snackbar to Action Button inside card
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

            send.setOnClickListener(new View.OnClickListener(){

                public void addString(){
                    EditText editText = (EditText) itemView.findViewById(R.id.comment);
                    String message = editText.getText().toString();
                    wordList.add(message);
                }


                @Override
                public void onClick(View view) {
                    addString();
                    String word = "";
                    for (String addWord : wordList)
                        if ("".equals(word))
                            word  = addWord;
                        else
                            word  = addWord + "\n" + word;

                    komentar.setText(word);
                    comment.setText("");
                    Toast.makeText(getContext(),
                            "Komentar dimasukkan", Toast.LENGTH_SHORT).show();
                }
            });

            ImageButton commentImageButton =
                    (ImageButton) itemView.findViewById(R.id.comment_button);

            ImageButton shareImageButton = (ImageButton) itemView.findViewById(R.id.share_button);
            shareImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    shareIt();
                    Toast.makeText(v.getContext(), ("Share Article"),
                            Toast.LENGTH_LONG).show();
                }
            });
        }
    }


    private void shareIt() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Here is the share content body";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    /**
     * Adapter to display recycler view.
     */
    public class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of Card in RecyclerView.
        private static final int LENGTH = 18;

        private final String[] mPlaces;
        private final String[] mPlaceDesc;
        private final Drawable[] mPlacePictures;
        private final Drawable mAvatar;
        private final String mUsername;
        private final String mTime;


        public ContentAdapter(Context context) {
            Resources resources = context.getResources();
            mPlaces = resources.getStringArray(R.array.places);
            mPlaceDesc = resources.getStringArray(R.array.place_desc);
            mAvatar = resources.getDrawable(R.drawable.profile);
            mUsername = "Fajar Zakaria";
            mTime = "Feb 8, 2017 at 17.00 WIB";
            TypedArray a = resources.obtainTypedArray(R.array.places_picture);
            mPlacePictures = new Drawable[a.length()];
            for (int i = 0; i < mPlacePictures.length; i++) {
                mPlacePictures[i] = a.getDrawable(i);
            }
            a.recycle();
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
            holder.picture.setImageDrawable(mPlacePictures[position % mPlacePictures.length]);
            holder.title.setText(mPlaces[position % mPlaces.length]);
            holder.description.setText(mPlaceDesc[position % mPlaceDesc.length]);
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }
}