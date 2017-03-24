package com.hi_depok.hi_depok.Kadepok.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hi_depok.hi_depok.Kadepok_Donasi.kadepok_donasi_verify;
import com.hi_depok.hi_depok.R;

/**
 * Created by Muhammad63 on 3/16/2017.
 */

public class fragment2 extends Fragment {

    public static com.hi_depok.hi_depok.Kadepok.fragment.fragment2 newInstance() {
        Bundle args = new Bundle();

        com.hi_depok.hi_depok.Kadepok.fragment.fragment2 fragment = new com.hi_depok.hi_depok.Kadepok.fragment.fragment2();
        fragment.setArguments(args);
        return fragment;
    }

    public Button donate;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  v = inflater.inflate(R.layout.fragment2_kadepok_content, null);

        donate = (Button)v.findViewById(R.id.donate);
        donate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent donate = new Intent(getContext(), kadepok_donasi_verify.class);
                startActivity(donate);
            }
        });

        return v;
    }
    }
