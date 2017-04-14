package com.hi_depok.hi_depok.Kapok;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hi_depok.hi_depok.R;

/**
 * Created by User on 3/23/2017.
 */

public class RecycleViewHolderMenuKapok extends RecyclerView.ViewHolder {
    ImageView image;
    TextView name;

    public RecycleViewHolderMenuKapok(View itemView){
        super(itemView);
        image = (ImageView)itemView.findViewById(R.id.image);
        name = (TextView)itemView.findViewById(R.id.name);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, kapok_detail.class);
                context.startActivity(intent);
            }
        });
    }


}