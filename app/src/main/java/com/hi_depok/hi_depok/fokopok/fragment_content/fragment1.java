package com.hi_depok.hi_depok.fokopok.fragment_content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hi_depok.hi_depok.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 19/03/17.
 */

public class fragment1 extends Fragment {
    private LinearLayoutManager lLayout;

    public static fragment1 newInstance(){
        Bundle args = new Bundle();

        fragment1 fragment = new fragment1();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  v = inflater.inflate(R.layout.fokopok_fragment1_content, null);
        List<itemObject_komunitas> rowListItem = getAllItemList();
        lLayout = new LinearLayoutManager(getActivity());

        RecyclerView rView = (RecyclerView)v.findViewById(R.id.fokopok_komunitas);
        rView.setLayoutManager(lLayout);

        RecyclerViewAdapter_komunitas rcAdapter = new RecyclerViewAdapter_komunitas(getActivity(), rowListItem);
        rView.setAdapter(rcAdapter);
        return v;
    }

    private List<itemObject_komunitas> getAllItemList(){
        List<itemObject_komunitas> allItems = new ArrayList<>();
        allItems.add(new itemObject_komunitas("Komunitas Jalan Santai (20)", R.drawable.lapok_image_2));
        allItems.add(new itemObject_komunitas("Komunitas Fotografi (75)", R.drawable.lapok_image_3));

        return allItems;
    }
}
