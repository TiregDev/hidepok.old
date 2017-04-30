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
import com.hi_depok.hi_depok.SessionManager;

import java.util.List;


public class RecyclerViewAdapterPOST extends RecyclerView.Adapter<RecyclerViewAdapterPOST.ViewHolder> {

    Context context;

    List<GetDataAdapter> adapter;

    String id_post;
    String judul_post;
    String isi_post;
    String tanggal_post;
    String waktu_post;
    String kategori_post;
    String foto_post;
    String deskripsi_post;
    String lokasi_post;
    String no_identitas_post;
    String status_post;
    String rating_post;
    String id_user;
    String id_modul;
    String nama_user;
    String urlPhoto;

    public RecyclerViewAdapterPOST(List<GetDataAdapter> adapter, Context context){

        super();

        this.adapter = adapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sikepokrs_fragment_post, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        id_post = adapter.get(position).getId_post();
        judul_post = adapter.get(position).getJudul_post();
        isi_post = adapter.get(position).getIsi_post();

        tanggal_post = adapter.get(position).getTanggal_post();
        waktu_post = adapter.get(position).getWaktu_post();
        kategori_post = adapter.get(position).getKategori_post();
        deskripsi_post = adapter.get(position).getDeskripsi_post();
        lokasi_post = adapter.get(position).getLokasi_post();
        no_identitas_post = adapter.get(position).getNo_identitas_post();
        status_post = adapter.get(position).getStatus_post();
        rating_post = adapter.get(position).getRating_post();
        id_user = adapter.get(position).getId_user();
        id_modul = adapter.get(position).getId_modul();
        foto_post = adapter.get(position).getFoto_post();

        nama_user = adapter.get(position).getNama_user();


        holder.judulPost.setText(judul_post);
        holder.isiPost.setText(isi_post);
        holder.namaUser.setText(nama_user);
        holder.itemView.setTag(id_post);

    }

    @Override
    public int getItemCount() {

        return adapter.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView judulPost;
        public TextView isiPost;
        public TextView namaUser;

        public ViewHolder(final View itemView) {

            super(itemView);

            judulPost = (TextView)itemView.findViewById(R.id.judul_post);
            isiPost = (TextView)itemView.findViewById(R.id.isi_post);
            namaUser = (TextView)itemView.findViewById(R.id.pengirim_post);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("id_post",itemView.getTag().toString());
                    editor.commit();

                    Context context = v.getContext();
                    Intent intent = new Intent(context, detail_post.class);
                    context.startActivity(intent);
                }
            });


        }
    }
}