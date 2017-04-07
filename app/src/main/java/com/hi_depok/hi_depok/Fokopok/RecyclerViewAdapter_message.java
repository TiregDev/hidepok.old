package com.hi_depok.hi_depok.Fokopok;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.Ucok.ItemObject;
import com.hi_depok.hi_depok.Ucok.RecyclerViewHolders;

import java.util.List;

/**
 * Created by User on 19/03/17.
 */

public class RecyclerViewAdapter_message extends RecyclerView.Adapter<RecyclerViewHolder_message> {

    private List<itemObject_message> itemList;
    private Context context;

    public RecyclerViewAdapter_message(Context context, List<itemObject_message> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder_message onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fokopok_list_pesan, null);
        RecyclerViewHolder_message rcv = new RecyclerViewHolder_message(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder_message holder, int position) {
        holder.nama_komunitas.setText(itemList.get(position).getNama_komunitas());
        holder.avatar_komunitas.setImageResource(itemList.get(position).getAvatar_komunitas());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
