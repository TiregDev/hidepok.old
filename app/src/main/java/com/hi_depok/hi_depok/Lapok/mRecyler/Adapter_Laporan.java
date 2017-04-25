package com.hi_depok.hi_depok.Lapok.mRecyler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hi_depok.hi_depok.Lapok.mData.Laporan;
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
        String name = mLaporan.get(position).getName();
        String tanggal = mLaporan.get(position).getTanggal();
        String waktu = mLaporan.get(position).getWaktu();
        String title = mLaporan.get(position).getJudul();
        String total_like = mLaporan.get(position).getJml_like();
        String total_comment = mLaporan.get(position).getJml_com();
        String image = mLaporan.get(position).getImage();
        String kejadian = mLaporan.get(position).getKejadian();
        int suka = mLaporan.get(position).getLike_imgbtn();
        int komentar = mLaporan.get(position).getComment_imgbtn();
        int bagikan = mLaporan.get(position).getShare_imgbtn();

        DateFormat df = new SimpleDateFormat("d MMM yyyy");
        DateFormat df1 = new SimpleDateFormat("HH:mm:ss");
        Date startDate, startTime;
        try {
            startDate = df.parse(tanggal);
            startTime = df1.parse(waktu);
            String newDateString = df.format(startDate);
            String newTimeString = df1.format(startTime);
            String waktu_kejadian = newDateString + ", at" + newTimeString;
            holder.timeTxt.setText(waktu_kejadian);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String urlAvatar = "http://hidepok.id/assets/images/photos/ucok/"+image;

        holder.nameTxt.setText(name);
//        holder.img.setImageResource(image);
        holder.title.setText(title);
//        holder.kej.setImageResource(kejadian);
        holder.likeimg.setImageResource(suka);
        holder.comimg.setImageResource(komentar);
        holder.shaimg.setImageResource(bagikan);
        holder.jml_like.setText(total_like);
        holder.jml_com.setText(total_comment);
    }


//        holder.setItemClickListener(new ItemClickListener() {
//            @Override
//            public void onItemClick(int pos) {
//                openDetailActivity(name, time, title, total_like, total_comment,
//                        image, kejadian, suka, komentar, bagikan);
//                Toast.makeText(c, name, Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    @Override
    public int getItemCount() {
        return mLaporan.size();
    }

//    private void openDetailActivity(String name, String time, String title, String totlike,
//                                    String totcom, int image, int kejadian, int like, int comment,
//                                    int share){
//        Intent i = new Intent(c, DetailActivity.class);
//
//        //PACK DATA TO SEND
//        i.putExtra("NAME_KEY", name);
//        i.putExtra("TIME_KEY", time);
//        i.putExtra("TITLE_KEY", title);
//        i.putExtra("IMAGE_KEY", image);
//        i.putExtra("KEJADIAN_KEY", kejadian);
//        i.putExtra("LIKE_KEY", like);
//        i.putExtra("COMMENT_KEY", comment);
//        i.putExtra("SHARE_KEY", share);
//        i.putExtra("TOTAL_COMMENT", totcom);
//        i.putExtra("TOTAL_LIKE", totlike);
//        //OPEN ACTIVITY
//        c.startActivity(i);
//    }
}
