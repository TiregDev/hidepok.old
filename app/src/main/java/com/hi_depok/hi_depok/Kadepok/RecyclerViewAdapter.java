package com.hi_depok.hi_depok.Kadepok;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hi_depok.hi_depok.R;

import java.util.List;

/**
 * Created by Farhan Fadhli on 3/22/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {
    private List<ItemObject> itemList;
    private Context context;

    public RecyclerViewAdapter(Context context, List<ItemObject> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.kadepok_item_list, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.list_title.setText(itemList.get(position).getTitle());
        holder.list_alamat.setText(itemList.get(position).getAlamat());
        holder.list_telepon.setText(itemList.get(position).getTelepon());
        holder.list_avatar.setImageResource(itemList.get(position).getAvatar());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
