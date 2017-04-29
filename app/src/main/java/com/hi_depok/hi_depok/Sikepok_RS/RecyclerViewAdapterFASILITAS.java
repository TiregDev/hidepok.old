package com.hi_depok.hi_depok.Sikepok_RS;

/**
 * Created by SONY-VAIO on 3/15/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hi_depok.hi_depok.R;
import com.squareup.picasso.Picasso;

import java.net.URLEncoder;
import java.util.List;


public class RecyclerViewAdapterFASILITAS extends RecyclerView.Adapter<RecyclerViewAdapterFASILITAS.ViewHolder> {

    Context context;

    List<GetDataAdapter> adapter;
    String id_fasilitas;
    String nama_fasilitas;
    String foto_fasilitas;
    String deskripsi_fasilitas;
    String id_rs;
    String urlPhoto;

    public RecyclerViewAdapterFASILITAS(List<GetDataAdapter> adapter, Context context){

        super();

        this.adapter = adapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sikepokrs_fragment_fasilitas, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        id_fasilitas = adapter.get(position).getId_fasilitas();
        nama_fasilitas = adapter.get(position).getNama_fasilitas();
        deskripsi_fasilitas = adapter.get(position).getDeskripsi_fasilitas();
        foto_fasilitas = adapter.get(position).getFoto_fasilitas();
        id_rs = adapter.get(position).getId_rs();

        holder.namaFasilitas.setText(nama_fasilitas);
        holder.deskripsiFasilitas.setText(deskripsi_fasilitas);
        holder.itemView.setTag(id_rs);
        String encodeUrl = URLEncoder.encode(foto_fasilitas);
        urlPhoto = "http://hidepok.id/assets/images/photos/sikepok/sikepok2/" + encodeUrl;

        Picasso.with(context).load(urlPhoto).into(holder.fotoFasilitas);
    }

    @Override
    public int getItemCount() {

        return adapter.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView namaFasilitas;
        public TextView deskripsiFasilitas;
        public ImageView fotoFasilitas;


        public ViewHolder(final View itemView) {

            super(itemView);

            namaFasilitas = (TextView)itemView.findViewById(R.id.namaFasilitas);
            deskripsiFasilitas = (TextView)itemView.findViewById(R.id.deskripsiFasilitas);
            fotoFasilitas = (ImageView)itemView.findViewById(R.id.image_slide);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Context context = v.getContext();
//                    Intent intent = new Intent(context, info_dokter.class);
//                    String pos = (String) itemView.getTag();
////                    Toast.makeText(context,pos,Toast.LENGTH_SHORT).show();
//                    Log.d("JSONAdapter", "get id: " + pos);
//                    intent.putExtra("EXTRA_ID", pos.toString());
//
//                    SharedPreferences sharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor=sharedPreferences.edit();
//                    editor.putString("id_rs", angka);
//                    editor.commit();
//                    editor.apply();
//
//                    context.startActivity(intent);
//                }
//            });


        }
    }
}