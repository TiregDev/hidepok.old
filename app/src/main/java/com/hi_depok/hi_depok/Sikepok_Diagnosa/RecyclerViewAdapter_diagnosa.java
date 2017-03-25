package com.hi_depok.hi_depok.Sikepok_Diagnosa;

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

public class RecyclerViewAdapter_diagnosa extends RecyclerView.Adapter<RecyclerViewHolder_diagnosa> {
    private List<itemObject_diagnosa> itemList;
    private Context context;

    public RecyclerViewAdapter_diagnosa(Context context, List<itemObject_diagnosa> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder_diagnosa onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sikepokdiagnosa_diagnosa_list, null);
        RecyclerViewHolder_diagnosa rcv = new RecyclerViewHolder_diagnosa(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder_diagnosa holder, int position) {
        holder.nama_bagian.setText(itemList.get(position).getNama_bagian());
        holder.avatar_bagian.setImageResource(itemList.get(position).getAvatar_bagian());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
