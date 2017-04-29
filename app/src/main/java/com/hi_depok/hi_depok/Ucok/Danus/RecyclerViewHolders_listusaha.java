package com.hi_depok.hi_depok.Ucok.Danus;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hi_depok.hi_depok.R;


/**
 * Created by User on 18/03/17.
 */

public class RecyclerViewHolders_listusaha extends RecyclerView.ViewHolder {
    public TextView title_usaha;
    public TextView desc;

    public RecyclerViewHolders_listusaha(View itemViewusaha) {
        super(itemViewusaha);

        title_usaha = (TextView)itemViewusaha.findViewById(R.id.title_usaha);
        desc = (TextView)itemViewusaha.findViewById(R.id.desc);
        itemViewusaha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, detail_target.class);
                //intent.putExtra(detail.EXTRA_POSITION, getAdapterPosition());
                context.startActivity(intent);
            }
        });
    }
}

