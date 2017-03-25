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

public class RecyclerViewAdapter_listensi extends RecyclerView.Adapter<RecyclerViewHolders_listensi>{

    private List<itemObject_listensi> itemListensi;

    public RecyclerViewAdapter_listensi(Context contextusaha, List<itemObject_listensi> itemListensi) {
        this.itemListensi = itemListensi;
    }

    @Override
    public RecyclerViewHolders_listensi onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sikepokdiagnosa_ensiklopedia_list, null);
        RecyclerViewHolders_listensi rcv = new RecyclerViewHolders_listensi(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders_listensi holder, int position) {
        holder.title_ensi.setText(itemListensi.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return this.itemListensi.size();
    }
}