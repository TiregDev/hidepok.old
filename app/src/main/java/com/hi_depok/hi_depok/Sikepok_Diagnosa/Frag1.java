package com.hi_depok.hi_depok.Sikepok_Diagnosa;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.Ucok.RecyclerViewAdapter_listdanus;
import com.hi_depok.hi_depok.Ucok.hasil_calculate;
import com.hi_depok.hi_depok.Ucok.itemObject_listdanus;

import java.util.ArrayList;
import java.util.List;

public class Frag1 extends Fragment {
    private LinearLayoutManager lLayout;

    public static Frag1 newInstance(){
        Bundle args = new Bundle();

        Frag1 fragment = new Frag1();
        fragment.setArguments(args);
        return  fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_frag1, null);

        List<itemObject_listapotik> rowListItem = getAllItemList();
        lLayout = new LinearLayoutManager(getContext());

        RecyclerView rView = (RecyclerView)v.findViewById(R.id.list_apotik);
        rView.setLayoutManager(lLayout);

        RecyclerViewAdapter_listapotik rcAdapter = new RecyclerViewAdapter_listapotik(getContext(), rowListItem);
        rView.setAdapter(rcAdapter);
        return  v;
    }

    private List<itemObject_listapotik> getAllItemList(){
        List<itemObject_listapotik> allItems = new ArrayList<>();
        allItems.add(new itemObject_listapotik("Apotik 1", "Vildansvagen 19, Lund Sweden", R.drawable.asia));
        allItems.add(new itemObject_listapotik("Apotik 2", "3 Villa Crescent London, UK", R.drawable.asia));
        allItems.add(new itemObject_listapotik("Apotik 3", "Victoria Island Lagos, Nigeria", R.drawable.asia));
        allItems.add(new itemObject_listapotik("Apotik 4", "New Heaven Enugu, Nigeria", R.drawable.asia));
        allItems.add(new itemObject_listapotik("Apotik 5", "Italion Gata, Padova, Italy", R.drawable.asia));

        return allItems;
    }
}
