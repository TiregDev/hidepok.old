package com.hi_depok.hi_depok.Kadepok.adapter;

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
import com.hi_depok.hi_depok.Kadepok.activity.KadepokDetailActivity;
import com.hi_depok.hi_depok.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class JSONAdapter extends RecyclerView.Adapter<JSONAdapter.ViewHolder> {

    Context context;

    List<ItemObject> adapter;
    private String id;
    private String name;
    private String alamat;
    private String telepon;
    private String photo;
    private String kecamatan;
    private String email;
    private String jumlahanak;
    private String tahun;
    private String rekening;
    String urlPhoto;

    public JSONAdapter(List<ItemObject> adapter, Context context){

        super();

        this.adapter = adapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.kadepok_item_list, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        id = adapter.get(position).getId();
        name = adapter.get(position).getName();
        alamat = adapter.get(position).getAlamat();
        photo = adapter.get(position).getPhoto();
        telepon = adapter.get(position).getTelepon();
        kecamatan = adapter.get(position).getKecamatan();
        email = adapter.get(position).getEmail();
        jumlahanak = adapter.get(position).getJumlahanak();
        tahun = adapter.get(position).getTahun();
        rekening = adapter.get(position).getRekening();


        holder.itemView.setTag(id);
        holder.list_title.setText(name);
        holder.list_alamat.setText(alamat);
        urlPhoto = "http://hidepok.id/assets/images/photos/kadepok/" + photo;
        Glide.with(context).load(urlPhoto).placeholder(R.drawable.image_placeholder).thumbnail(0.3f).into(holder.list_avatar);
    }

    @Override
    public int getItemCount() {

        return adapter.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView list_title;
        public TextView list_alamat;
        public TextView list_telepon;
        public ImageView list_avatar;

        public ViewHolder(final View itemView) {
            super(itemView);

            list_title = (TextView)itemView.findViewById(R.id.list_title);
            list_alamat = (TextView)itemView.findViewById(R.id.list_alamat);
            list_telepon = (TextView)itemView.findViewById(R.id.list_telepon);
            list_avatar = (ImageView)itemView.findViewById(R.id.list_avatar);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    String pos = (String) itemView.getTag();
                    Intent intent = new Intent(context, KadepokDetailActivity.class);
                    intent.putExtra("getId", pos);
                    context.startActivity(intent);
                }
            });
        }
    }
}
