package com.hi_depok.hi_depok.Sikepok_Diagnosa;

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

public class RecyclerViewAdapter_listapotik extends RecyclerView.Adapter<RecyclerViewHolders_listapotik> {
    private List<itemObject_listapotik> itemList;
    private Context context;

    public RecyclerViewAdapter_listapotik(Context context, List<itemObject_listapotik> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders_listapotik onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_lokasi_list, null);
        RecyclerViewHolders_listapotik rcv = new RecyclerViewHolders_listapotik(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders_listapotik holder, int position) {
        holder.list_title.setText(itemList.get(position).getTitle());
        holder.list_desc.setText(itemList.get(position).getDesc());
        holder.list_avatar.setImageResource(itemList.get(position).getAvatar());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
