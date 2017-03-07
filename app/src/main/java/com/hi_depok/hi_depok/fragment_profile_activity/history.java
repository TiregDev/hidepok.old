package com.hi_depok.hi_depok.fragment_profile_activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hi_depok.hi_depok.R;

/**
 * Created by Muhammad63 on 3/4/2017.
 */

public class history extends Fragment{

    public static history newInstance() {

        Bundle args = new Bundle();

        history fragment = new history();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  v = inflater.inflate(R.layout.profile_fragment2_history, null);
        return v;
    }
}
