package com.hi_depok.hi_depok.fokopok.fragment_content;

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

public class RecyclerViewHolder_komunitas extends RecyclerView.ViewHolder {
    public TextView nama_komunitas;
    public ImageView avatar_komunitas;
    public RecyclerViewHolder_komunitas(View itemView) {
        super(itemView);

        nama_komunitas = (TextView)itemView.findViewById(R.id.nama_komunitas);
        avatar_komunitas = (ImageView)itemView.findViewById(R.id.avatar_komunitas);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
//                Intent intent = new Intent(context, UcokDetailActivity.class);
//                context.startActivity(intent);
                Toast.makeText(context,"Masuk Halaman Chatting Komunitas",Toast.LENGTH_SHORT).show();
            }
        });
    }
    
}
