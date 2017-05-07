package com.hi_depok.hi_depok.Lapok.mRecyler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hi_depok.hi_depok.R;

/**
 * Created by Azmi Muhammad on 3/17/2017.
 */

public class Holder_Laporan extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView nameTxt, timeTxt, title, jml_like, jml_com;
    ImageView img, kej, likeimg, comimg, shaimg, stsimg;
    ItemClickListener ItemClickListener;

    public Holder_Laporan(View itemView) {
        super(itemView);

        nameTxt = (TextView) itemView.findViewById(R.id.nameTxt);
        timeTxt = (TextView) itemView.findViewById(R.id.timeTxt);
        title = (TextView) itemView.findViewById(R.id.card_text);
        jml_like = (TextView) itemView.findViewById(R.id.jumlah_like);
        jml_com = (TextView) itemView.findViewById(R.id.jumlah_comment);
        img = (ImageView) itemView.findViewById(R.id.imageArtist);
        kej = (ImageView) itemView.findViewById(R.id.card_image);
        likeimg = (ImageView) itemView.findViewById(R.id.like);
        comimg = (ImageView) itemView.findViewById(R.id.comment_button);
        shaimg = (ImageView) itemView.findViewById(R.id.share_button);
        stsimg = (ImageView) itemView.findViewById(R.id.status_post);

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

