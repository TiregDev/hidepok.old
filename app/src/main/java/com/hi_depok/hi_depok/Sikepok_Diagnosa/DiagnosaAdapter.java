package com.hi_depok.hi_depok.Sikepok_Diagnosa;

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


public class DiagnosaAdapter extends RecyclerView.Adapter<DiagnosaAdapter.ViewHolder> {

    Context context;

    List<DataModel> adapter;
    String id;
    String nama;
    String foto;

    public DiagnosaAdapter(List<DataModel> adapter, Context context){

        super();

        this.adapter = adapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sikepokdiagnosa_listdiagnosa, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        id = adapter.get(position).getId();
        nama = adapter.get(position).getNama();
        foto = adapter.get(position).getFoto();


        holder.title.setText(nama);
        holder.itemView.setTag(id);

        Glide.with(context).load("http://hidepok.id/assets/images/photos/sikepok/sikepok1/bagian_tubuh/" + foto).placeholder(R.drawable.image_placeholder).thumbnail(0.3f).into(holder.image);

    }

    @Override
    public int getItemCount() {

        return adapter.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView title;
        public ImageView image;


        public ViewHolder(final View itemView) {

            super(itemView);

            title = (TextView)itemView.findViewById(R.id.nama_bagian);
            image = (ImageView)itemView.findViewById(R.id.bagian_tubuh);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
//                    Intent intent = new Intent(context, DiagnosaBagian.class);
//                    String pos = (String) itemView.getTag();
//                    Log.d("JSONAdapter", "get id: " + pos);
//
//                    context.startActivity(intent);
                    Toast.makeText(context, "Segera hadir setelah penyempurnaan aplikasi", Toast.LENGTH_LONG).show();
                }
            });


        }
    }
}

