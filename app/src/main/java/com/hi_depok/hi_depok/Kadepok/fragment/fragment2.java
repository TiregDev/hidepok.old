package com.hi_depok.hi_depok.Kadepok.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import com.hi_depok.hi_depok.R;

/**
 * Created by Muhammad63 on 3/16/2017.
 */

public class fragment2 extends DialogFragment {

    public static com.hi_depok.hi_depok.Kadepok.fragment.fragment2 newInstance() {
        Bundle args = new Bundle();

        com.hi_depok.hi_depok.Kadepok.fragment.fragment2 fragment = new com.hi_depok.hi_depok.Kadepok.fragment.fragment2();
        fragment.setArguments(args);
        return fragment;
    }

    public Button donate;
    private PopupWindow popupWindow;
    public Button close;
    View v;

    public void verify_volunteer() {
        try {

            LayoutInflater layoutInflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = layoutInflater.inflate(R.layout.kadepok_donasi_popup, (ViewGroup)v.findViewById(R.id.rl_custom_layout));

            popupWindow = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
            popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);

            close = (Button)layout.findViewById(R.id.close);
            close.setOnClickListener(cancel_button_click_listener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View.OnClickListener cancel_button_click_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            popupWindow.dismiss();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.kadepok_fragment2_kadepok_content, null);
//        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        donate = (Button)v.findViewById(R.id.donate);
        donate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                verify_volunteer();
            }
        });

        return v;
    }
    }
