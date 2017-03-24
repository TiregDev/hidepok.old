package com.hi_depok.hi_depok.Sikepok_Diagnosa;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hi_depok.hi_depok.R;

/**
 * Created by User on 19/03/17.
 */

public class RecyclerViewHolder_diagnosa extends RecyclerView.ViewHolder {
    public TextView nama_bagian;
    public ImageView avatar_bagian;
    public RecyclerViewHolder_diagnosa(View itemView) {
        super(itemView);

        nama_bagian = (TextView)itemView.findViewById(R.id.nama_bagian);
        avatar_bagian = (ImageView)itemView.findViewById(R.id.avatar_bagian);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, Anggota_badan_badan.class);
                context.startActivity(intent);
//                Toast.makeText(context,"Masuk Halaman Chatting Komunitas",Toast.LENGTH_SHORT).show();
            }
        });
    }
    
}
