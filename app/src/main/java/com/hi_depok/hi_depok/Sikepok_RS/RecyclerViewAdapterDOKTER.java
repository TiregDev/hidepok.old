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


public class RecyclerViewAdapterDOKTER extends RecyclerView.Adapter<RecyclerViewAdapterDOKTER.ViewHolder> {

    Context context;

    List<GetDataAdapter> adapter;
    String id_dokter;
    String nama_dokter;
    String alamat_dokter;
    String no_telp_dokter;
    String email_dokter;
    String spesialisasi;
    String foto_dokter;
    String deskripsi_dokter;
    String id_rs;
    String urlPhoto;

    public RecyclerViewAdapterDOKTER(List<GetDataAdapter> adapter, Context context){

        super();

        this.adapter = adapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sikepokrs_fragment_dokter, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        id_dokter = adapter.get(position).getId_dokter();
        nama_dokter = adapter.get(position).getNama_dokter();
        deskripsi_dokter = adapter.get(position).getDeskripsi_dokter();
        no_telp_dokter = adapter.get(position).getNo_telp_dokter();
        alamat_dokter = adapter.get(position).getAlamat_dokter();
        foto_dokter = adapter.get(position).getFoto_dokter();
        email_dokter = adapter.get(position).getEmail_dokter();
        spesialisasi = adapter.get(position).getSpesialisasi();
        id_rs = adapter.get(position).getId_rs();

        holder.namaDokter.setText(nama_dokter);
        holder.spesialisasi.setText(spesialisasi);
        holder.itemView.setTag(id_dokter);
        String encodeUrl = URLEncoder.encode(foto_dokter);
        urlPhoto = "http://hidepok.id/assets/images/photos/sikepok/sikepok2/" + encodeUrl;

//        switch (jenis){
//            case "Apotek":
//                urlPhoto = "http://hidepok.id/images/sikepok/sikepok3/APOTEK/" + encodeUrl + ".jpg";
//                break;
//            case "Bidan":
//                urlPhoto = "http://hidepok.id/images/sikepok/sikepok3/BIDAN/" + encodeUrl + ".jpg";
//                break;
//            case "Tukang Urut":
//                urlPhoto = "http://hidepok.id/images/sikepok/sikepok3/PIJAT/" + encodeUrl + ".jpg";
//                break;
//            case "Khitan":
//                urlPhoto = "http://hidepok.id/images/sikepok/sikepok3/KHITAN/" + encodeUrl + ".jpg";
//                break;
//            case "Klinik":
//                urlPhoto = "http://hidepok.id/images/sikepok/sikepok3/KLINIK/" + encodeUrl + ".jpg";
//                break;
//            case "Puskesmas":
//                urlPhoto = "http://hidepok.id/images/sikepok/sikepok3/PUSKESMAS/" + encodeUrl + ".jpg";
//                break;
//            default:
//                break;
//        }
//        Toast.makeText(context, holder.itemView.getTag().toString(),Toast.LENGTH_SHORT).show();
        Picasso.with(context).load(urlPhoto).into(holder.fotoDokter);
    }

    @Override
    public int getItemCount() {

        return adapter.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView namaDokter;
        public TextView spesialisasi;
        public ImageView fotoDokter;

        String angka = "4";

        public ViewHolder(final View itemView) {

            super(itemView);

            namaDokter = (TextView)itemView.findViewById(R.id.namaDokter);
            spesialisasi = (TextView)itemView.findViewById(R.id.spesialisasiDokter);
            fotoDokter = (ImageView)itemView.findViewById(R.id.fotoDokter);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, info_dokter.class);
                    String pos = (String) itemView.getTag();
//                    Toast.makeText(context,pos,Toast.LENGTH_SHORT).show();
                    Log.d("JSONAdapter", "get id: " + pos);
                    intent.putExtra("EXTRA_ID", pos.toString());

                    SharedPreferences sharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("id_rs", angka);
                    editor.commit();
                    editor.apply();

                    context.startActivity(intent);
                }
            });


        }
    }
}