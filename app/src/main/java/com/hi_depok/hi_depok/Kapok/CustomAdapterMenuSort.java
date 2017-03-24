package com.hi_depok.hi_depok.Kapok;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hi_depok.hi_depok.R;

import java.util.ArrayList;

/**
 * Created by User on 3/23/2017.
 */

public class CustomAdapterMenuSort extends RecyclerView.Adapter<RecycleViewHolderMenuKapok> {

    ArrayList<String> namalogo;
    ArrayList<Integer> gambarlogo;
    Context context;

    public CustomAdapterMenuSort(KapokActivity context, ArrayList<String> personNames, ArrayList<Integer> personImages) {
        this.context = context;
        this.namalogo = personNames;
        this.gambarlogo = personImages;
    }

    @Override
    public RecycleViewHolderMenuKapok onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.kapok_rowlayout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        RecycleViewHolderMenuKapok vh = new RecycleViewHolderMenuKapok(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(RecycleViewHolderMenuKapok holder, final int position) {
        // set the data in items
        holder.name.setText(namalogo.get(position));
        holder.image.setImageResource(gambarlogo.get(position));

    }



    @Override
    public int getItemCount() {
        return namalogo.size();
    }


    }



