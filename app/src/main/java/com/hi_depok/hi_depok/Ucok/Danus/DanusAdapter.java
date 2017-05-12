package com.hi_depok.hi_depok.Ucok.Danus;

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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hi_depok.hi_depok.R;

import java.util.List;


public class DanusAdapter extends RecyclerView.Adapter<DanusAdapter.ViewHolder> {

    Context context;

    List<DataModel> adapter;
    String id;
    String nama;
    String deskripsi;

    public DanusAdapter(List<DataModel> adapter, Context context){

        super();

        this.adapter = adapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ucok_list_usaha, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        id = adapter.get(position).getId();
        nama = adapter.get(position).getNama();
        deskripsi = adapter.get(position).getDeskripsi();


        holder.title.setText(nama);
        holder.desc.setText(deskripsi);
        holder.itemView.setTag(id);

    }

    @Override
    public int getItemCount() {

        return adapter.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView title;
        public TextView desc;


        public ViewHolder(final View itemView) {

            super(itemView);

            title = (TextView)itemView.findViewById(R.id.title_usaha);
            desc = (TextView) itemView.findViewById(R.id.desc);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, detail_target.class);
                    String pos = (String) itemView.getTag();
                    intent.putExtra("getId", pos);
                    Log.d("JSONAdapter", "get id: " + pos);

                    context.startActivity(intent);
                    Toast.makeText(context, "Segera hadir setelah penyempurnaan aplikasi", Toast.LENGTH_LONG).show();
                }
            });


        }
    }
}

