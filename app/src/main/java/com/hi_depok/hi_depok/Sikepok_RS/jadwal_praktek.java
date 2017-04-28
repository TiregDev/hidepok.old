package com.hi_depok.hi_depok.Sikepok_RS;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hi_depok.hi_depok.R;

/**
 * Created by Hadi on 13/03/2017.
 */

public class jadwal_praktek extends Fragment {

    //tambahan untuk fragment
    public static jadwal_praktek newInstance(String title) {
        jadwal_praktek fragment = new jadwal_praktek();

        Bundle args = new Bundle();
        args.putCharSequence("title", title);
        fragment.setArguments(args);

        return fragment;
    }

    public String getTitle() {
        Bundle args = getArguments();
        return args.getString("title", "NO TITLE FOUND");
    }
    //tambahan fragment sampai sini

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sikepokrs_jadwal_praktek, container, false);

    }

}
