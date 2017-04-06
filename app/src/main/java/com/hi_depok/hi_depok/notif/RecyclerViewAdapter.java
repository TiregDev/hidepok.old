package com.hi_depok.hi_depok.notif;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.notif.*;

import java.util.List;

/**
 * Created by Muhammad63 on 4/6/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {

    private List<com.hi_depok.hi_depok.notif.ItemObject> itemList;
    private Context context;

    public RecyclerViewAdapter(Context context, List<com.hi_depok.hi_depok.notif.ItemObject> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_event_list, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.list_title.setText(itemList.get(position).getTitle());
        holder.list_desc.setText(itemList.get(position).getDesc());
        holder.list_time.setText(itemList.get(position).getTime());
        holder.list_avatar.setImageResource(itemList.get(position).getAvatar());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
