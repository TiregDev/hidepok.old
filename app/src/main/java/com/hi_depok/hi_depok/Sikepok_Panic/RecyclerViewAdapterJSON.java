package com.hi_depok.hi_depok.Sikepok_Panic;

/**
 * Created by SONY-VAIO on 3/15/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URLEncoder;
import java.util.List;
import com.hi_depok.hi_depok.R;
import com.squareup.picasso.Picasso;


public class RecyclerViewAdapterJSON extends RecyclerView.Adapter<RecyclerViewAdapterJSON.ViewHolder> {

    Context context;

    List<GetDataAdapter> adapter;
    String id;
    String nama;
    String foto;
    String deskripsi;
    String alamat;
    String operasional;
    String no_telp;
    String jenis;
    String kecamatan;
    String kordinat;
    String urlPhoto;

    public RecyclerViewAdapterJSON(List<GetDataAdapter> adapter, Context context){

        super();

        this.adapter = adapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        id = adapter.get(position).getId();
        nama = adapter.get(position).getName();
        operasional = adapter.get(position).getOperasional();
        deskripsi = adapter.get(position).getDeskripsi();
        no_telp = adapter.get(position).getNoTelp();
        alamat = adapter.get(position).getAlamat();
        foto = adapter.get(position).getFoto();
        jenis = adapter.get(position).getJenis();
        kecamatan = adapter.get(position).getKecamatan();
        kordinat = adapter.get(position).getKordinat();

        holder.personName.setText(nama);
        holder.personDesc.setText(alamat);
        holder.itemView.setTag(id);
        String encodeUrl = URLEncoder.encode(foto);

        switch (jenis){
            case "Apotek":
                urlPhoto = "http://hidepok.id/assets/images/photos/sikepok/sikepok3/APOTEK/" + encodeUrl + ".jpg";
                break;
            case "Bidan":
                urlPhoto = "http://hidepok.id/images/sikepok/sikepok3/BIDAN/" + encodeUrl + ".jpg";
                break;
            case "Tukang Urut":
                urlPhoto = "http://hidepok.id/images/sikepok/sikepok3/PIJAT/" + encodeUrl + ".jpg";
                break;
            case "Khitan":
                urlPhoto = "http://hidepok.id/images/sikepok/sikepok3/KHITAN/" + encodeUrl + ".jpg";
                break;
            case "Klinik":
                urlPhoto = "http://hidepok.id/images/sikepok/sikepok3/KLINIK/" + encodeUrl + ".jpg";
                break;
            case "Puskesmas":
                urlPhoto = "http://hidepok.id/images/sikepok/sikepok3/PUSKESMAS/" + encodeUrl + ".jpg";
                break;
            default:
                break;
        }

        Picasso.with(context).load(urlPhoto).resize(100, 100).into(holder.personPhoto);
    }

    @Override
    public int getItemCount() {

        return adapter.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView personName;
        public TextView personDesc;
        public ImageView personPhoto;


        public ViewHolder(final View itemView) {

            super(itemView);

            personName = (TextView)itemView.findViewById(R.id.list_title);
            personDesc = (TextView)itemView.findViewById(R.id.list_desc);
            personPhoto = (ImageView)itemView.findViewById(R.id.list_avatar);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, DetailActivity.class);
                    String pos = (String) itemView.getTag();
                    Log.d("RecyclerViewAdapterJSON_siumkm", "get id: " + pos);
                    intent.putExtra(DetailActivity.EXTRA_POSITION, pos);
//                    intent.putExtra(DetailActivity.NAMA_TEMPAT, nama);
//                    intent.putExtra(DetailActivity.DESKRIPSI_TEMPAT, deskripsi);
//                    intent.putExtra(DetailActivity.LOKASI_TEMPAT, alamat);
//                    intent.putExtra(DetailActivity.NOTLP_TEMPAT, no_telp);
//                    intent.putExtra(DetailActivity.FOTO_TEMPAT, urlPhoto);
//                    intent.putExtra(DetailActivity.OPERASIONAL_TEMPAT, operasional);
//                    intent.putExtra(DetailActivity.KORDINAT_TEMPAT, kordinat);

                    context.startActivity(intent);
                }
            });


        }
    }
}
