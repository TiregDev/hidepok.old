package com.hi_depok.hi_depok.Kapok.adapter;

/**
 * Created by SONY-VAIO on 3/15/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hi_depok.hi_depok.Kapok.activity.kapok_detail;
import com.hi_depok.hi_depok.R;

import java.util.List;


public class RecyclerViewAdapterJSON extends RecyclerView.Adapter<RecyclerViewAdapterJSON.ViewHolder> {

    Context context;

    List<GetDataAdapter> adapter;
    String id;
    String nama;
    String foto;
    String urlPhoto;

    public RecyclerViewAdapterJSON(List<GetDataAdapter> getDataAdapter, Context context){

        super();

        this.adapter = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.kapok_rowlayout, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        id = adapter.get(position).getId();
        nama = adapter.get(position).getName();
        foto = adapter.get(position).getFoto();

        if(!foto.equals("null") && foto.contains(",")){
            String[] listFoto = foto.split(",");
            urlPhoto = "http://hidepok.id/assets/images/photos/kapok/" + listFoto[0];
            Glide.with(context).load(urlPhoto).placeholder(R.drawable.image_placeholder).thumbnail(0.3f).into(holder.personPhoto);
        }
        else if(!foto.equals("null")){
            urlPhoto = "http://hidepok.id/assets/images/photos/kapok/" + foto;
            Glide.with(context).load(urlPhoto).placeholder(R.drawable.image_placeholder).thumbnail(0.3f).into(holder.personPhoto);
        }
        holder.personName.setText(nama);
        holder.itemView.setTag(id);

    }

    @Override
    public int getItemCount() {

        return adapter.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView personName;
        public ImageView personPhoto;


        public ViewHolder(final View itemView) {

            super(itemView);

            personName = (TextView)itemView.findViewById(R.id.name);
            personPhoto = (ImageView)itemView.findViewById(R.id.image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    String pos = (String) itemView.getTag();
                    Intent intent = new Intent(context, kapok_detail.class);
                    intent.putExtra("getID", pos);
                    context.startActivity(intent);
                }
            });


        }
    }
}
