package com.hi_depok.hi_depok.Ucok;

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

public class RecyclerViewAdapter_listdanus extends RecyclerView.Adapter<RecyclerViewHolders_listdanus> {
    private List<itemObject_listdanus> itemList;
    private Context context;

    public RecyclerViewAdapter_listdanus(Context context, List<itemObject_listdanus> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders_listdanus onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ucok_list_danus, null);
        RecyclerViewHolders_listdanus rcv = new RecyclerViewHolders_listdanus(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders_listdanus holder, int position) {
        holder.list_title.setText(itemList.get(position).getTitle());
        holder.list_desc.setText(itemList.get(position).getDesc());
        holder.list_price.setText(itemList.get(position).getPrice());
        holder.list_avatar.setImageResource(itemList.get(position).getAvatar());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
