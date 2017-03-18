package com.hi_depok.hi_depok.Kadepok_Volunteer;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

import com.hi_depok.hi_depok.Activity_Main.kadepok;
import com.hi_depok.hi_depok.R;

import java.util.ArrayList;
import java.util.List;

public class kadepok_volunteer extends AppCompatActivity {
    private Button btn_volunteer;
    private PopupWindow popupWindow;
    public Button close;

    private void initiatepopup() {
        try {


            LayoutInflater layoutInflater = (LayoutInflater)kadepok_volunteer.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = layoutInflater.inflate(R.layout.kadepok_volunteer_popup, (ViewGroup)findViewById(R.id.rl_custom_layout));

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
            Intent verify = new Intent(kadepok_volunteer.this, kadepok.class);
            startActivity(verify);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kadepok_volunteer_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btn_volunteer = (Button)findViewById(R.id.btn_volunteer);
        btn_volunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initiatepopup();
            }
        });

        Spinner spinner = (Spinner)findViewById(R.id.jenis_volunter_spinner);

        List<String> jenis_volunteer = new ArrayList<String>();
        jenis_volunteer.add("Pilih Jenis Volunteer");
        jenis_volunteer.add("Guru Kesenian");
        jenis_volunteer.add("Guru Olahraga");
        jenis_volunteer.add("Guru Renang");
        jenis_volunteer.add("Guru Musik");
        jenis_volunteer.add("Lain-Lain");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, jenis_volunteer);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
