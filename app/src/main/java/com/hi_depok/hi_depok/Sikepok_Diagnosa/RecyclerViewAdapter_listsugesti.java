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

public class RecyclerViewAdapter_listsugesti extends RecyclerView.Adapter<RecyclerViewHolders_listsugesti>{
    private List<itemObject_listsugesti> itemListsugesti;

    public RecyclerViewAdapter_listsugesti(Context contextsugesti, List<itemObject_listsugesti> itemListsugesti) {
        this.itemListsugesti = itemListsugesti;
    }

    @Override
    public RecyclerViewHolders_listsugesti onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sikepokdiagnosa_sugesti_list, null);
        RecyclerViewHolders_listsugesti rcv = new RecyclerViewHolders_listsugesti(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders_listsugesti holder, int position) {
        holder.title_sugesti.setText(itemListsugesti.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return this.itemListsugesti.size();
    }
}