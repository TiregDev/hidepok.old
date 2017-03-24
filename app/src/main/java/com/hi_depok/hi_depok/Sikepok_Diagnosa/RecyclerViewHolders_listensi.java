package com.hi_depok.hi_depok.Sikepok_Diagnosa;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.Ucok.detail_target;


/**
 * Created by User on 18/03/17.
 */

public class RecyclerViewHolders_listensi extends RecyclerView.ViewHolder {
    public TextView title_ensi;

    public RecyclerViewHolders_listensi(View itemViewensi) {
        super(itemViewensi);

        title_ensi = (TextView)itemViewensi.findViewById(R.id.title_ensiklopedia);
        itemViewensi.setOnClickListener(new View.OnClickListener() {
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

