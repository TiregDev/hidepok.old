package com.hi_depok.hi_depok.Kadepok.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Spinner;

import com.hi_depok.hi_depok.Activity_Main.kadepok;
import com.hi_depok.hi_depok.R;

/**
 * Created by Muhammad63 on 3/16/2017.
 */

public class fragment3 extends Fragment {
    private Button btn_volunteer;
    private PopupWindow popupWindow;
    public Button close;

    public static fragment3 newInstance() {
        Bundle args = new Bundle();

        fragment3 fragment = new fragment3();
        fragment.setArguments(args);
        return fragment;
    }

    private View.OnClickListener cancel_button_click_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            popupWindow.dismiss();
            Intent verify = new Intent(getContext(), kadepok.class);
            startActivity(verify);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  v = inflater.inflate(R.layout.fragment3_kadepok_content, null);

        btn_volunteer = (Button)v.findViewById(R.id.btn_volunteer);
        btn_volunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View layout = layoutInflater.inflate(R.layout.kadepok_volunteer_popup, (ViewGroup)v.findViewById(R.id.rl_custom_layout));

                    popupWindow = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
                    popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);

                    close = (Button)layout.findViewById(R.id.close);
                    close.setOnClickListener(cancel_button_click_listener);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        String [] values =
                {"Pilih Jenis Volunteer", "Guru Matematika", "Guru Fisika", "Guru Kimia", "Guru Biologi",
                        "Guru Bahasa Indonesia", "Guru Bahasa Inggris", "Guru Olahraga", "Guru Kesenian",
                        "Guru Musik", "Lain-Lain"};
        Spinner spinner = (Spinner) v.findViewById(R.id.jenis_volunter_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        return v;
    }
}
