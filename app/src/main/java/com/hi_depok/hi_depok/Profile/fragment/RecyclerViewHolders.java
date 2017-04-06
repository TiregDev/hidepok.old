package com.hi_depok.hi_depok.Profile.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hi_depok.hi_depok.R;

/**
 * Created by Muhammad63 on 4/6/2017.
 */

public class RecyclerViewHolders extends RecyclerView.ViewHolder{

    public TextView list_title;
    public TextView list_time;

    public RecyclerViewHolders(View itemView) {
        super(itemView);

        list_title = (TextView)itemView.findViewById(R.id.list_title);
        list_time = (TextView)itemView.findViewById(R.id.list_time);
//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Context context = v.getContext();
//                Intent intent = new Intent(context, UcokDetailActivity.class);
//                context.startActivity(intent);
//            }
//        });
    }
}
