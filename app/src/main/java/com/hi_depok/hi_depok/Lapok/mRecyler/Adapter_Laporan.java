package com.hi_depok.hi_depok.Lapok.mRecyler;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.Lapok.mData.Laporan;
import com.hi_depok.hi_depok.Lapok.mDetail.DetailActivity;

import java.util.ArrayList;

/**
 * Created by Azmi Muhammad on 3/17/2017.
 */

public class Adapter_Laporan extends RecyclerView.Adapter<Holder_Laporan> {

    Context c;
    ArrayList<Laporan> mLaporan;

    public Adapter_Laporan(Context c, ArrayList<Laporan> laporan) {
        this.c = c;
        this.mLaporan = laporan;
    }

    @Override
    public Holder_Laporan onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lapok_isi_forum, parent, false);
        return new Holder_Laporan(v);
    }

    @Override
    public void onBindViewHolder(Holder_Laporan holder, int position) {
        final String name = mLaporan.get(position).getName();
        final String time = mLaporan.get(position).getWaktu();
        final String title = mLaporan.get(position).getJudul();
        final String total_like = mLaporan.get(position).getJml_like();
        final String total_comment = mLaporan.get(position).getJml_com();
        final int image = mLaporan.get(position).getImage();
        final int kejadian = mLaporan.get(position).getKejadian();
        final int suka = mLaporan.get(position).getLike_imgbtn();
        final int komentar = mLaporan.get(position).getComment_imgbtn();
        final int bagikan = mLaporan.get(position).getShare_imgbtn();

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


        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                openDetailActivity(name, time, title, total_like, total_comment,
                        image, kejadian, suka, komentar, bagikan);
                Toast.makeText(c, name, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLaporan.size();
    }

    private void openDetailActivity(String name, String time, String title, String totlike,
                                    String totcom, int image, int kejadian, int like, int comment,
                                    int share){
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
        //OPEN ACTIVITY
        c.startActivity(i);
    }
}
