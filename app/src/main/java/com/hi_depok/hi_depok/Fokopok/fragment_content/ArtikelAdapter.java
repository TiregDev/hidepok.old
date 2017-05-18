package com.hi_depok.hi_depok.Fokopok.fragment_content;

/**
 * Created by SONY-VAIO on 3/15/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ceylonlabs.imageviewpopup.ImagePopup;
import com.hi_depok.hi_depok.Fokopok.detail_post_fokopok;
import com.hi_depok.hi_depok.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class ArtikelAdapter extends RecyclerView.Adapter<ArtikelAdapter.ViewHolder> {

    Context context;

    List<DataModel> adapter;
    String id, nama, foto, halaman, isi, waktu, judul, avatar, suka, komentar, hasil;

    public ArtikelAdapter(List<DataModel> adapter, Context context){

        super();

        this.adapter = adapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fokopok_item_list, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        id = adapter.get(position).getId();
        nama = adapter.get(position).getNama();
        foto = adapter.get(position).getFoto();
        waktu = adapter.get(position).getWaktu();
        judul = adapter.get(position).getJudul();
        avatar = adapter.get(position).getAvatar();
        komentar = adapter.get(position).getKomentar();
        suka = adapter.get(position).getSuka();
        hasil = adapter.get(position).getHasil();

        holder.favoriteImageButton.setImageResource(R.drawable.like);
        holder.commentButton.setImageResource(R.drawable.comment);

        /*if(hasil.equals("sudah"))
            holder.like.setImageResource(R.drawable.favorite);
        else
            holder.like.setImageResource(R.drawable.like);*/

        holder.username.setText(nama);
        holder.time.setText(waktu);
        holder.title.setText(judul);
        holder.jumlah_like.setText(suka);
        holder.jumlah_komentar.setText(komentar);

        Glide.with(context).load("http://hidepok.id/assets/images/photos/fokopok/" + avatar)
                .placeholder(R.drawable.image_placeholder).thumbnail(0.3f).into(holder.avatar);
        Glide.with(context).load("http://hidepok.id/assets/images/photos/fokopok/" + foto)
                .placeholder(R.drawable.image_placeholder).thumbnail(0.3f).into(holder.picture);
        holder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        return adapter.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView picture;
        public ImageView avatar;
        public TextView username;
        public TextView time;
        public TextView title;
        public TextView description, jumlah_like, jumlah_komentar;
        public ImageButton favoriteImageButton, commentButton;
        public CircleImageView imageView;


        public ViewHolder(final View itemView) {

            super(itemView);

            final ImagePopup imagePopup = new ImagePopup(itemView.getContext());
            imagePopup.setBackgroundColor(Color.TRANSPARENT);
            imagePopup.setWindowWidth(800);
            imagePopup.setWindowHeight(800);
            imagePopup.setHideCloseIcon(true);
            imagePopup.setImageOnClickClose(true);

            imageView = (CircleImageView) itemView.findViewById(R.id.avatar);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /** Initiate Popup view **/
                    imagePopup.initiatePopup(imageView.getDrawable());
                }
            });

            picture = (ImageView) itemView.findViewById(R.id.card_image);
            avatar = (ImageView) itemView.findViewById(R.id.avatar);
            username = (TextView) itemView.findViewById(R.id.username);
            time = (TextView) itemView.findViewById(R.id.time);
            title    = (TextView) itemView.findViewById(R.id.card_title);
            description = (TextView) itemView.findViewById(R.id.card_text);
            favoriteImageButton = (ImageButton) itemView.findViewById(R.id.like);
            commentButton = (ImageButton) itemView.findViewById(R.id.comment_button);
            jumlah_like = (TextView) itemView.findViewById(R.id.jumlah_like);
            jumlah_komentar = (TextView) itemView.findViewById(R.id.jumlah_comment);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, detail_post_fokopok.class);
                    String id_post_detail = (String) itemView.getTag();
                    intent.putExtra("id_artikel", id_post_detail);

                    context.startActivity(intent);
                }
            });


        }
    }
}
