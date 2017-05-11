package com.hi_depok.hi_depok.Lapok.mRecyler;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.hi_depok.hi_depok.Lapok.mData.Laporan;
import com.hi_depok.hi_depok.Lapok.mDetail.DetailActivity;
import com.hi_depok.hi_depok.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Azmi Muhammad on 3/17/2017.
 */

public class Adapter_Laporan extends RecyclerView.Adapter<Holder_Laporan> {

    Context mContext;
    ArrayList<Laporan> mLaporan;

    public Adapter_Laporan(Context c, ArrayList<Laporan> laporan) {
        this.mContext = c;
        this.mLaporan = laporan;
    }

    @Override
    public Holder_Laporan onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lapok_isi_forum, parent, false);
        return new Holder_Laporan(v);
    }

    @Override
    public void onBindViewHolder(Holder_Laporan holder, int position) {
        String name = mLaporan.get(position).getName();
        String tanggal = mLaporan.get(position).getTanggal();
        String waktu = mLaporan.get(position).getWaktu();
        String title = mLaporan.get(position).getJudul();
        String hasil = mLaporan.get(position).getHasil();
        String total_like = mLaporan.get(position).getJml_like();
        String total_comment = mLaporan.get(position).getJml_com();
        String image = mLaporan.get(position).getImage();
        String kejadian = mLaporan.get(position).getKejadian();
//        int suka = mLaporan.get(position).getLike_imgbtn();
        int komentar = mLaporan.get(position).getComment_imgbtn();
        int bagikan = mLaporan.get(position).getShare_imgbtn();

        final String id_post = mLaporan.get(position).getId();
        String status = mLaporan.get(position).getStatus();

        if(hasil.equals("sudah"))
            holder.likeimg.setImageResource(R.drawable.favorite);
        else
            holder.likeimg.setImageResource(R.drawable.like);

        if(status.equals("Menunggu"))
            holder.stsimg.setImageResource(R.drawable.circle_wait_list);
        else if(status.equals("Proses"))
            holder.stsimg.setImageResource(R.drawable.circle_process_list);
        else if(status.equals("Selesai"))
            holder.stsimg.setImageResource(R.drawable.circle_done_list);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("d MMM yyyy");
        DateFormat df1 = new SimpleDateFormat("HH:mm:ss");
        Date startDate, startTime;
        try {
            startDate = df.parse(tanggal);
            startTime = df1.parse(waktu);
            String newDateString = mSimpleDateFormat.format(startDate);
            String newTimeString = df1.format(startTime);
            String waktu_kejadian = newDateString + ", pada pukul " + newTimeString + " WIB";
            holder.timeTxt.setText(waktu_kejadian);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        final String urlAvatar = "http://hidepok.id/assets/images/photos/avatar/" + image;
        Glide.with(mContext).load(urlAvatar).thumbnail(0.3f).placeholder(R.drawable.image_placeholder)
                .into(holder.img);
        final String urlFotoPost = "http://hidepok.id/assets/images/photos/lapok/" + kejadian;
        Glide.with(mContext).load(urlFotoPost).thumbnail(0.3f).placeholder(R.drawable.image_placeholder)
                .into(holder.kej);


        holder.nameTxt.setText(name);
        holder.title.setText(title);
//        holder.likeimg.setImageResource(suka);
        holder.comimg.setImageResource(komentar);
        holder.shaimg.setImageResource(bagikan);
        holder.jml_like.setText(total_like);
        holder.jml_com.setText(total_comment);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                Intent i = new Intent(mContext, DetailActivity.class);
                i.putExtra("ID_POST_DETAIL", id_post);
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLaporan.size();
    }
}
