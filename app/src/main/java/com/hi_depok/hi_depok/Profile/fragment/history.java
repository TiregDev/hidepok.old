package com.hi_depok.hi_depok.Profile.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.Profile.fragment.*;
import com.hi_depok.hi_depok.Profile.fragment.RecyclerViewAdapter;
import com.hi_depok.hi_depok.Profile.fragment.RecyclerViewHolders;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Muhammad63 on 3/4/2017.
 */

public class history extends Fragment{
    private LinearLayoutManager lLayout;

    public static history newInstance() {

        Bundle args = new Bundle();

        history fragment = new history();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rView = inflater.inflate(
                R.layout.profile_fragment2_history, container, false);
//        List<ItemObject> rowListItem = getAllItemList();
//        lLayout = new LinearLayoutManager(getActivity());
//        rView.setLayoutManager(lLayout);
//
//        RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(getContext(), rowListItem);
//        rView.setAdapter(rcAdapter);
        return rView;
    }

    private List<ItemObject> getAllItemList(){
        List<ItemObject> allItems = new ArrayList<>();
        allItems.add(new ItemObject("Bakti Sosial","Panti Asuhan Darul Ilmi. 22/07/2017"));
        allItems.add(new ItemObject("Pameran","Panti Asuhan Darul Ilmi. 22/07/2017"));
        allItems.add(new ItemObject("Lomba Makan","Panti Asuhan Darul Ilmi. 22/07/2017"));
        allItems.add(new ItemObject("Gathering Komika","Panti Asuhan Darul Ilmi. 22/07/2017"));
        allItems.add(new ItemObject("Nobar", "Panti Asuhan Darul Ilmi. 22/07/2017"));
        return allItems;
    }
}
