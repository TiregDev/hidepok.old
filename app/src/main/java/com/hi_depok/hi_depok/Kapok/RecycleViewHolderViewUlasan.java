package com.hi_depok.hi_depok.Kapok;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hi_depok.hi_depok.R;

/**
 * Created by User on 3/24/2017.
 */

public class RecycleViewHolderViewUlasan extends RecyclerView.ViewHolder {

    public TextView title;
    public TextView waktu;
    public TextView komentar;
    public ImageView avatar;
    public String position;

    public RecycleViewHolderViewUlasan(View itemView) {
        super(itemView);

        title = (TextView)itemView.findViewById(R.id.title);
        waktu = (TextView)itemView.findViewById(R.id.waktu);
        komentar = (TextView)itemView.findViewById(R.id.komentar);
        setAvatar((ImageView)itemView.findViewById(R.id.avatar));
        itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
              //  Context context = v.getContext();

               // Toast.makeText(context, "Menu ini berada di posisi " + position, Toast.LENGTH_LONG).show();
            }
        });
    }

    public ImageView getAvatar() {
        return avatar;
    }

    public void setAvatar(ImageView avatar) {
        this.avatar = avatar;
    }
}
