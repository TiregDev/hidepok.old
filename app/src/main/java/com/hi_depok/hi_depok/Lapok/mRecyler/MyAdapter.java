package com.hi_depok.hi_depok.Lapok.mRecyler;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.Lapok.mData.Artist;
import com.hi_depok.hi_depok.Lapok.mDetail.DetailActivity;

import java.util.ArrayList;

/**
 * Created by Azmi Muhammad on 3/17/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    Context c;
    ArrayList<Artist> artists;

    public MyAdapter(Context c, ArrayList<Artist> artists) {
        this.c = c;
        this.artists = artists;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lapok_isi_forum, parent, false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        final String name = artists.get(position).getName();
        final String time = artists.get(position).getWaktu();
        final String title = artists.get(position).getJudul();
        final String total_like = artists.get(position).getJml_like();
        final String total_comment = artists.get(position).getJml_com();
        final String total_share = artists.get(position).getJml_share();
        final int image = artists.get(position).getImage();
        final int kejadian = artists.get(position).getKejadian();
        final int suka = artists.get(position).getLike_imgbtn();
        final int komentar = artists.get(position).getComment_imgbtn();
        final int bagikan = artists.get(position).getShare_imgbtn();

        //BIND DATA
        holder.nameTxt.setText(name);
        holder.img.setImageResource(image);
        holder.title.setText(title);
        holder.timeTxt.setText(time);
        holder.kej.setImageResource(kejadian);
        holder.likeimg.setImageResource(suka);
        holder.comimg.setImageResource(komentar);
        holder.shaimg.setImageResource(bagikan);
        holder.jml_like.setText(total_like);
        holder.jml_com.setText(total_comment);
        holder.jml_share.setText(total_share);


        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                openDetailActivity(name, time, title, total_like, total_comment, total_share,
                        image, kejadian, suka, komentar, bagikan);
                Toast.makeText(c, name, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    private void openDetailActivity(String name, String time, String title, String totlike,
                                    String totcom, String totshare, int image, int kejadian,
                                    int like, int comment, int share){
        Intent i = new Intent(c, DetailActivity.class);

        //PACK DATA TO SEND
        i.putExtra("NAME_KEY", name);
        i.putExtra("TIME_KEY", time);
        i.putExtra("TITLE_KEY", title);
        i.putExtra("IMAGE_KEY", image);
        i.putExtra("KEJADIAN_KEY", kejadian);
        i.putExtra("LIKE_KEY", like);
        i.putExtra("COMMENT_KEY", comment);
        i.putExtra("SHARE_KEY", share);
        i.putExtra("TOTAL_COMMENT", totcom);
        i.putExtra("TOTAL_LIKE", totlike);
        i.putExtra("TOTAL_SHARE", totshare);
        //OPEN ACTIVITY
        c.startActivity(i);
    }
}
