package com.hi_depok.hi_depok.Profile.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.Profile.fragment.*;
import com.hi_depok.hi_depok.Profile.fragment.RecyclerViewAdapter;
import com.hi_depok.hi_depok.Profile.fragment.RecyclerViewHolders;

import java.util.List;

/**
 * Created by Muhammad63 on 4/6/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<com.hi_depok.hi_depok.Profile.fragment.RecyclerViewHolders> {

    private List<com.hi_depok.hi_depok.Profile.fragment.ItemObject> itemList;
    private Context context;

    public RecyclerViewAdapter(Context context, List<com.hi_depok.hi_depok.Profile.fragment.ItemObject> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public com.hi_depok.hi_depok.Profile.fragment.RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_profile_history_list, null);
        com.hi_depok.hi_depok.Profile.fragment.RecyclerViewHolders rcv = new com.hi_depok.hi_depok.Profile.fragment.RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.list_title.setText(itemList.get(position).getTitle());
        holder.list_time.setText(itemList.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}