package com.hi_depok.hi_depok.Kadepok;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hi_depok.hi_depok.R;

/**
 * Created by Farhan Fadhli on 3/22/2017.
 */

public class RecyclerViewHolders extends RecyclerView.ViewHolder{

    public TextView list_title;
    public TextView list_alamat;
    public TextView list_telepon;
    public ImageView list_avatar;

    public RecyclerViewHolders(View itemView) {
        super(itemView);

        list_title = (TextView)itemView.findViewById(R.id.list_title);
        list_alamat = (TextView)itemView.findViewById(R.id.list_alamat);
        list_telepon = (TextView)itemView.findViewById(R.id.list_telepon);
        list_avatar = (ImageView)itemView.findViewById(R.id.list_avatar);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, KadepokDetailActivity.class);
                context.startActivity(intent);
            }
        });
    }
}
