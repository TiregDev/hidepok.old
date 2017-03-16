package com.hi_depok.hi_depok.Lapok.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hi_depok.hi_depok.R;

/**
 * Created by Muhammad63 on 3/16/2017.
 */

public class fragment1 extends Fragment {

    public static com.hi_depok.hi_depok.Lapok.fragment.fragment1 newInstance() {
        Bundle args = new Bundle();

        com.hi_depok.hi_depok.Lapok.fragment.fragment1 fragment = new com.hi_depok.hi_depok.Lapok.fragment.fragment1();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  v = inflater.inflate(R.layout.fragment1_lapok_content, null);
        return v;
    }
}
