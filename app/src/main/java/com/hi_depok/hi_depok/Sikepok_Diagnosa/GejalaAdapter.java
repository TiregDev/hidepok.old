package com.hi_depok.hi_depok.Sikepok_Diagnosa;

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
import android.widget.TextView;
import android.widget.Toast;

import com.hi_depok.hi_depok.R;

import java.util.List;


public class GejalaAdapter extends RecyclerView.Adapter<GejalaAdapter.ViewHolder> {

    Context context;

    List<DataModel> adapter;
    String id;
    String nama;
    String simpen;

    public GejalaAdapter(List<DataModel> adapter, Context context){

        super();

        this.adapter = adapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sikepokdiagnosa_ensiklopedia_list, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        id = adapter.get(position).getId();
        nama = adapter.get(position).getNama();

        holder.title.setText(nama);
        holder.itemView.setTag(id);

    }

    @Override
    public int getItemCount() {

        return adapter.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView title;


        public ViewHolder(final View itemView) {

            super(itemView);

            title = (TextView)itemView.findViewById(R.id.title_ensiklopedia);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
//                    adapter.clear();
//                    Intent intent = new Intent(context, Gejala.class);
//                    String idgejala = (String) itemView.getTag();
//                    SharedPreferences pref = context.getSharedPreferences("MyPref", 0);
//                    SharedPreferences.Editor editor = pref.edit();
//                    simpen = pref.getString("idgejala", "0");
//                    simpen = simpen.replace(idgejala,"");
//                    editor.putString("idgejala",simpen);
//                    editor.commit();
//                    Toast.makeText(context,simpen,Toast.LENGTH_SHORT).show();
//                    Log.d("JSONAdapter", "get page: " + idgejala);
//                    context.startActivity(intent);
                }
            });


        }
    }
}
