package com.hi_depok.hi_depok.Sikepok_Diagnosa;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hi_depok.hi_depok.R;


/**
 * Created by User on 18/03/17.
 */

public class RecyclerViewHolders_listsugesti extends RecyclerView.ViewHolder {
    public TextView title_sugesti;

    public RecyclerViewHolders_listsugesti(View itemViewsugesti) {
        super(itemViewsugesti);

        title_sugesti = (TextView)itemViewsugesti.findViewById(R.id.title_sugesti);
        itemViewsugesti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, Deskripsi.class);
                //intent.putExtra(detail.EXTRA_POSITION, getAdapterPosition());
                context.startActivity(intent);
            }
        });
    }
}

