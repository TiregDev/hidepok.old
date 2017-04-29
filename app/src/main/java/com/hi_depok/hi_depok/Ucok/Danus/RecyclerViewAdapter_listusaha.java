package com.hi_depok.hi_depok.Ucok.Danus;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hi_depok.hi_depok.R;

import java.util.List;

/**
 * Created by User on 18/03/17.
 */

public class RecyclerViewAdapter_listusaha extends RecyclerView.Adapter<RecyclerViewHolders_listusaha>{
    private List<itemObject_listusaha> itemListusaha;

    public RecyclerViewAdapter_listusaha(Context contextusaha, List<itemObject_listusaha> itemListusaha) {
        this.itemListusaha = itemListusaha;
    }

    @Override
    public RecyclerViewHolders_listusaha onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ucok_list_usaha, null);
        RecyclerViewHolders_listusaha rcv = new RecyclerViewHolders_listusaha(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders_listusaha holder, int position) {
        holder.title_usaha.setText(itemListusaha.get(position).getTitle());
        holder.desc.setText(itemListusaha.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return this.itemListusaha.size();
    }
}