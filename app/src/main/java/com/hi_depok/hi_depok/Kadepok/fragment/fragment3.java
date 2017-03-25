package com.hi_depok.hi_depok.Kadepok.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hi_depok.hi_depok.Kadepok_Cherish.kadepok_cherish_detail_anak;
import com.hi_depok.hi_depok.Kadepok_Cherish.kadepok_cherish_detail_event;
import com.hi_depok.hi_depok.R;

/**
 * Created by Muhammad63 on 3/16/2017.
 */

public class fragment3 extends Fragment {
    private Button btn_daftar_anak, btn_daftar_event, close;

    public static fragment3 newInstance() {
        Bundle args = new Bundle();

        fragment3 fragment = new fragment3();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  v = inflater.inflate(R.layout.kadepok_fragment3_kadepok_content, null);

        btn_daftar_anak = (Button)v.findViewById(R.id.btn_daftar_anak);
        btn_daftar_anak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent daftar_anak = new Intent(getContext(), kadepok_cherish_detail_anak.class);
                startActivity(daftar_anak);
            }
        });

        btn_daftar_event = (Button)v.findViewById(R.id.btn_daftar_event);
        btn_daftar_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent daftar_anak = new Intent(getContext(), kadepok_cherish_detail_event.class);
                startActivity(daftar_anak);
            }
        });

        return v;
    }
}
