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
import android.widget.ImageView;
import com.hi_depok.hi_depok.R;

public class Frag1 extends Fragment {

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

        CardView iv = (CardView) v.findViewById(R.id.cardview1);
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
