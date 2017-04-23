package com.hi_depok.hi_depok.Kadepok.fragment;

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

    public static fragment1 newInstance() {
        Bundle args = new Bundle();

        fragment1 fragment = new fragment1();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  v = inflater.inflate(R.layout.kadepok_fragment1_kadepok_content, null);

        return v;
    }
}
