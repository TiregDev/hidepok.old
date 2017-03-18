package com.hi_depok.hi_depok.Sikepok_Diagnosa;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.hi_depok.hi_depok.R;

public class Frag3 extends Fragment {

    public static Frag3 newInstance(){
        Bundle args = new Bundle();

        Frag3 fragment = new Frag3();
        fragment.setArguments(args);
        return  fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_frag3, null);

        CardView iv = (CardView) v.findViewById(R.id.cardview3);
        iv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v2) {
                pindah();
            }
        });
        return  v;
    }
    public void pindah (){
        Intent contoh = new Intent(getActivity(), Apotik1.class);
        startActivity(contoh);
    }
}