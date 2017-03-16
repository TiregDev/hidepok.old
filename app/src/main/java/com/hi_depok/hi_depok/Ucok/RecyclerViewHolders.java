package com.hi_depok.hi_depok.Ucok;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hi_depok.hi_depok.R;

/**
 * Created by Muhammad63 on 3/14/2017.
 */

public class RecyclerViewHolders extends RecyclerView.ViewHolder{

    public TextView list_title;
    public TextView list_desc;
    public TextView list_price;
    public ImageView list_avatar;

    public RecyclerViewHolders(View itemView) {
        super(itemView);

        list_title = (TextView)itemView.findViewById(R.id.list_title);
        list_desc = (TextView)itemView.findViewById(R.id.list_desc);
        list_price = (TextView)itemView.findViewById(R.id.list_price);
        list_avatar = (ImageView)itemView.findViewById(R.id.list_avatar);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, UcokDetailActivity.class);
                //intent.putExtra(detail.EXTRA_POSITION, getAdapterPosition());
                context.startActivity(intent);
            }
        });
    }
}
