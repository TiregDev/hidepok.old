package com.hi_depok.hi_depok.Fokopok.fragment_content;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hi_depok.hi_depok.R;

import java.util.List;

/**
 * Created by User on 19/03/17.
 */

public class RecyclerViewAdapter_komunitas extends RecyclerView.Adapter<RecyclerViewHolder_komunitas> {
    private List<itemObject_komunitas> itemList;
    private Context context;

    public RecyclerViewAdapter_komunitas(Context context, List<itemObject_komunitas> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder_komunitas onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fokopok_list_komunitas, null);
        RecyclerViewHolder_komunitas rcv = new RecyclerViewHolder_komunitas(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder_komunitas holder, int position) {
        holder.nama_komunitas.setText(itemList.get(position).getNama_komunitas());
        holder.avatar_komunitas.setImageResource(itemList.get(position).getAvatar_komunitas());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
