package com.hi_depok.hi_depok.Sikepok_Panic.adapter;

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

import java.util.List;

import com.bumptech.glide.Glide;
import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.Sikepok_Panic.activity.DetailActivity;


public class JSONAdapter extends RecyclerView.Adapter<JSONAdapter.ViewHolder> {

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

    public JSONAdapter(List<GetDataAdapter> adapter, Context context){

        super();

        this.adapter = adapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sikepokpanic_item_list, parent, false);

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
        if(!foto.equals("null") && foto.contains(",")){
            String[] listFoto = foto.split(",");
            urlPhoto = "http://hidepok.id/assets/images/photos/sikepok/sikepok3/" + listFoto[0];
            Glide.with(context).load(urlPhoto).placeholder(R.drawable.image_placeholder).thumbnail(0.3f).into(holder.personPhoto);
        }
        else if(!foto.equals("null")){
            urlPhoto = "http://hidepok.id/assets/images/photos/sikepok/sikepok3/" + foto;
            Glide.with(context).load(urlPhoto).placeholder(R.drawable.image_placeholder).thumbnail(0.3f).into(holder.personPhoto);
        }
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
                    Log.d("JSONAdapter", "get id: " + pos);
                    intent.putExtra(DetailActivity.EXTRA_POSITION, pos);

                    context.startActivity(intent);
                }
            });


        }
    }
}
