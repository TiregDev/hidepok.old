package com.hi_depok.hi_depok.Fokopok.fragment_content;

/**
 * Created by SONY-VAIO on 3/15/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ceylonlabs.imageviewpopup.ImagePopup;
import com.hi_depok.hi_depok.Fokopok.firebase.MainActivity;
import com.hi_depok.hi_depok.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class KomunitasAdapter extends RecyclerView.Adapter<KomunitasAdapter.ViewHolder> {

    Context context;

    List<DataModel> adapter;
    String id;
    String nama;
    String foto;

    public KomunitasAdapter(List<DataModel> adapter, Context context){

        super();

        this.adapter = adapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fokopok_list_komunitas, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        id = adapter.get(position).getId();
        nama = adapter.get(position).getNama();
        foto = adapter.get(position).getFoto();



        holder.name.setText(nama);
        Glide.with(context).load("http://hidepok.id/assets/images/photos/fokopok/" + foto).placeholder(R.drawable.image_placeholder).into(holder.avatar);
        holder.itemView.setTag(id);


    }

    @Override
    public int getItemCount() {

        return adapter.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public CircleImageView avatar;


        public ViewHolder(final View itemView) {

            super(itemView);

            name = (TextView)itemView.findViewById(R.id.nama_komunitas);
            avatar = (CircleImageView) itemView.findViewById(R.id.avatar_komunitas);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, MainActivity.class);
                    String hal = (String) itemView.getTag();
                    intent.putExtra("getRoom", hal);
                    Log.d("JSONAdapter", "get page: " + hal);

                    context.startActivity(intent);
                }
            });


        }
    }

}
