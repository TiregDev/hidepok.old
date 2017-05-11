package com.hi_depok.hi_depok.Sikepok_RS;

/**
 * Created by SONY-VAIO on 3/15/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hi_depok.hi_depok.R;

import java.util.List;


public class RecyclerViewAdapterKOMENTAR extends RecyclerView.Adapter<RecyclerViewAdapterKOMENTAR.ViewHolder> {

    Context context;

    List<GetDataAdapter> adapter;

    String id_komentar;
    String isi_komentar;
    String tanggal_komentar;
    String waktu_komentar;
    String id_post;
    String id_user;
    String nama_user;
//    String urlPhoto;

    public RecyclerViewAdapterKOMENTAR(List<GetDataAdapter> adapter, Context context){

        super();

        this.adapter = adapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sikepokrs_fragment_detail_post, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        id_post = adapter.get(position).getId_post();
        id_komentar = adapter.get(position).getId_komentar();
        isi_komentar = adapter.get(position).getIsi_komentar();
        tanggal_komentar = adapter.get(position).getTanggal_komentar();
        waktu_komentar = adapter.get(position).getWaktu_komentar();
        id_user = adapter.get(position).getId_user();
 //       foto_post = adapter.get(position).getFoto_post();

        nama_user = adapter.get(position).getNama_user();


        holder.isiKomentar.setText(isi_komentar);
        holder.Komentator.setText(nama_user);
        holder.itemView.setTag(id_post);

    }

    @Override
    public int getItemCount() {

        return adapter.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView isiKomentar;
        public TextView Komentator;

        public ViewHolder(final View itemView) {

            super(itemView);

            isiKomentar = (TextView)itemView.findViewById(R.id.isi_komentar);
            Komentator = (TextView)itemView.findViewById(R.id.komentator);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
//                    SharedPreferences.Editor editor=sharedPreferences.edit();
//                    editor.putString("id_post",itemView.getTag().toString());
//                    editor.commit();
//
//                    Context context = v.getContext();
//                    Intent intent = new Intent(context, detail_post.class);
//                    context.startActivity(intent);
//                }
//            });


        }
    }
}