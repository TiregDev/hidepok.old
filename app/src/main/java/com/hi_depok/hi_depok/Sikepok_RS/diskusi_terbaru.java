package com.hi_depok.hi_depok.Sikepok_RS;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hi_depok.hi_depok.R;

/**
 * Created by Hadi on 13/03/2017.
 */

public class diskusi_terbaru extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sikepokrs_diskusi_terbaru, null);

//        LinearLayout asal = (LinearLayout) view.findViewById(R.id.link_ke_sana);
//
//        asal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Context context = v.getContext();
//                Intent intent = new Intent(context, detail_post.class);
////                    intent.putExtra(DetailActivity.EXTRA_POSITION, getAdapterPosition());
//                context.startActivity(intent);
//            }
//
//        });

//        public void ke_detail_diskusi(View view){
//            Intent next = new Intent(MainActivity.this, rs_terdekat.class);
//            startActivity(next);
//        }
        return view;
    }

}