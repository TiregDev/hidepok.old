package com.hi_depok.hi_depok.Sikepok_Panic.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hi_depok.hi_depok.R;

import java.util.ArrayList;

/**
 * Created by SONY-VAIO on 4/23/2017.
 */

public class PanicButtonFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(
                R.layout.sikepokpanic_button, container, false);
        ImageView callbtn = (ImageView) v.findViewById(R.id.callbtn);
        callbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dial = new Intent();
                dial.setAction("android.intent.action.DIAL");
                dial.setData(Uri.parse("tel:" + 112));
                startActivity(dial);
            }
        });
        return v;
    }
}
