package com.hi_depok.hi_depok.Lapok.mRecyler;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hi_depok.hi_depok.R;

/**
 * Created by Azmi Muhammad on 3/17/2017.
 */

public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView nameTxt, timeTxt, title, jml_like, jml_com, jml_share;
    ImageView img, kej, likeimg, comimg, shaimg;
    ItemClickListener ItemClickListener;

    public MyHolder(View itemView) {
        super(itemView);

        nameTxt = (TextView) itemView.findViewById(R.id.nameTxt);
        timeTxt = (TextView) itemView.findViewById(R.id.timeTxt);
        title = (TextView) itemView.findViewById(R.id.card_text);
        jml_like = (TextView) itemView.findViewById(R.id.jumlah_like);
        jml_com = (TextView) itemView.findViewById(R.id.jumlah_comment);
        jml_share = (TextView) itemView.findViewById(R.id.jumlah_share);
        img = (ImageView) itemView.findViewById(R.id.imageArtist);
        kej = (ImageView) itemView.findViewById(R.id.card_image);
        likeimg = (ImageView) itemView.findViewById(R.id.like);
        comimg = (ImageView) itemView.findViewById(R.id.comment_button);
        shaimg = (ImageView) itemView.findViewById(R.id.share_button);

        likeimg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                jml_like.setText("1");
                likeimg.setImageResource(R.drawable.favorite);
                Toast.makeText(view.getContext(), "Anda menyukai konten ini",
                        Toast.LENGTH_SHORT).show();
            }
        });

        comimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Klik Gambar Untuk Memberikan Komentar",
                        Toast.LENGTH_SHORT).show();
            }
        });

        shaimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Yuk memberi dampak perubahan positif" +
                        "untuk kota Depok ;) ";
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                view.getContext().startActivity(Intent.createChooser(sharingIntent, "Share via"));

                jml_share.setText("1");
                Toast.makeText(view.getContext(), "Sebarkan berita ini",
                        Toast.LENGTH_SHORT).show();
            }
        });


        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.ItemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        this.ItemClickListener.onItemClick(this.getLayoutPosition());
    }
}
