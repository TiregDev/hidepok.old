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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hi_depok.hi_depok.R;

import java.util.List;


public class SugestiAdapter extends RecyclerView.Adapter<SugestiAdapter.ViewHolder> {

    Context context;

    List<DataModel> adapter;
    String id;
    String nama;
    String halaman;
    Double persen;
    Integer ppersen;

    public SugestiAdapter(List<DataModel> adapter, Context context){

        super();

        this.adapter = adapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sikepokdiagnosa_sugesti_list, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        id = adapter.get(position).getId();
        nama = adapter.get(position).getNama();
        halaman = adapter.get(position).getHalaman();
        persen = adapter.get(position).getPersen();
        persen = persen * 10000;
        ppersen = persen.intValue();
        holder.title.setText(nama);
        holder.pb.setProgress(ppersen);
        holder.itemView.setTag(halaman);

    }

    @Override
    public int getItemCount() {

        return adapter.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView title;
        public ProgressBar pb;


        public ViewHolder(final View itemView) {

            super(itemView);

            title = (TextView)itemView.findViewById(R.id.title_sugesti);
            pb = (ProgressBar) itemView.findViewById(R.id.pb);
            pb.setMax(10000);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, Deskripsi.class);
                    String hal = (String) itemView.getTag();
                    intent.putExtra("getPage", hal);
                    Log.d("JSONAdapter", "get page: " + hal);

                    context.startActivity(intent);
                }
            });


        }
    }
}
