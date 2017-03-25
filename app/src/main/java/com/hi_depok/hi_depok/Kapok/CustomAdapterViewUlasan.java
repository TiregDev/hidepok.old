package com.hi_depok.hi_depok.Kapok;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hi_depok.hi_depok.R;

import java.util.List;

/**
 * Created by User on 3/24/2017.
 */

public class CustomAdapterViewUlasan extends RecyclerView.Adapter<RecycleViewHolderViewUlasan> {
    private List<ItemObjectViewUlasan> itemlist;
    private Context context;

    public CustomAdapterViewUlasan(Context context, List<ItemObjectViewUlasan> itemlist){
        this.itemlist = itemlist;
        this.context = context;
    }

    @Override
    public RecycleViewHolderViewUlasan onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.kapok_view_ulasan_isi, null);
        RecycleViewHolderViewUlasan rcv = new RecycleViewHolderViewUlasan(layoutView);
        return rcv;

    }

    @Override
    public void onBindViewHolder(RecycleViewHolderViewUlasan holder, int position) {
        holder.title.setText(itemlist.get(position).getTitle());
        holder.waktu.setText(itemlist.get(position).getTime());
        holder.komentar.setText(itemlist.get(position).getDesc());
        holder.avatar.setImageResource(itemlist.get(position).getAvatar());
    }

    @Override
    public int getItemCount() {
        return this.itemlist.size();
    }
}

