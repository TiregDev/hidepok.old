package com.hi_depok.hi_depok.Kadepok_Donasi;

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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

import com.hi_depok.hi_depok.Activity_Main.kadepok;
import com.hi_depok.hi_depok.Kadepok_Cherish.kadepok_cherish;
import com.hi_depok.hi_depok.Kadepok_Volunteer.kadepok_volunteer;
import com.hi_depok.hi_depok.R;

import java.util.ArrayList;
import java.util.List;

public class kadepok_donasi extends AppCompatActivity {
/*    public void init() {
        btn_donasi2 = (Button)findViewById(R.id.btn_donasi2);
        btn_donasi2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent next = new Intent(kadepok_donasi.this, kadepok_donasi_pilihpanti.class);
                startActivity(next);
            }
        });
    }*/
    private ImageButton btn_kadepok_notifikasi;
    private ImageView btn_cherish, btn_volunteer;
    private PopupWindow popup_notifikasi;
    public Button close;

    public void gotocherish() {
        btn_cherish = (ImageView)findViewById(R.id.btn_cherish);
        btn_cherish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent cherish = new Intent(kadepok_donasi.this, kadepok_cherish.class);
                startActivity(cherish);
            }
        });
    }

    public void gotovolunteer() {
        btn_volunteer = (ImageView)findViewById(R.id.btn_volunteer);
        btn_volunteer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent volunteer = new Intent(kadepok_donasi.this, kadepok_volunteer.class);
                startActivity(volunteer);
            }
        });
    }

    private void initiatepopup() {
        try {
            LayoutInflater layoutInflater = (LayoutInflater)kadepok_donasi.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = layoutInflater.inflate(R.layout.kadepok_notifikasi, (ViewGroup)findViewById(R.id.kadepok_notifikasi));

            popup_notifikasi = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
            popup_notifikasi.showAtLocation(layout, Gravity.CENTER, 0, 0);

            close = (Button)layout.findViewById(R.id.close);
            close.setOnClickListener(cancel_button_click_listener);

            Button notif = (Button)layout.findViewById(R.id.notif);
            notif.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent notif = new Intent(kadepok_donasi.this, kadepok_donasi_upload.class);
                    startActivity(notif);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View.OnClickListener cancel_button_click_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            popup_notifikasi.dismiss();
        }
    };

    public void back_profile(View view) {
        Intent intent = new Intent(kadepok_donasi.this, kadepok.class);
        startActivity(intent);
    }

    public class ImageAdapter extends BaseAdapter {
        private Context mContext;

        public ImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return mThumbIds.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(23, 8, 23, 8);
            } else {
                imageView = (ImageView) convertView;
            }

            imageView.setImageResource(mThumbIds[position]);
            return imageView;
        }

        // references to our images
        private Integer[] mThumbIds = {
                R.drawable.ic_action_home4, R.drawable.ic_action_home4,
                R.drawable.ic_action_home4, R.drawable.ic_action_home4,
                R.drawable.ic_action_home4, R.drawable.ic_action_home4,
                R.drawable.ic_action_home4, R.drawable.ic_action_home4,
                R.drawable.ic_action_home4, R.drawable.ic_action_home4,
                R.drawable.ic_action_home4, R.drawable.ic_action_home4,
                R.drawable.ic_action_home4, R.drawable.ic_action_home4,
                R.drawable.ic_action_home4, R.drawable.ic_action_home4,
                R.drawable.ic_action_home4, R.drawable.ic_action_home4,
                R.drawable.ic_action_home4, R.drawable.ic_action_home4,
                R.drawable.ic_action_home4, R.drawable.ic_action_home4,
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kadepok_donasi_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //init();
        gotocherish();
        gotovolunteer();

        btn_kadepok_notifikasi = (ImageButton)findViewById(R.id.btn_kadepok_notifikasi);
        btn_kadepok_notifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initiatepopup();
            }
        });

        /*TextView tv = (TextView) findViewById(R.id.textContent);
        Typeface tf = Typeface.createFromAsset(getAssets(), "font/Blogger_Sans-Bold.otf");
        tv.setTypeface(tf);*/

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                /*Toast.makeText(kadepok_donasi.this, "" + position,
                        Toast.LENGTH_SHORT).show();*/
                Intent next = new Intent(kadepok_donasi.this, kadepok_donasi_panti.class);
                startActivity(next);
            }
        });

        Spinner spinner = (Spinner)findViewById(R.id.spinner);

        List<String> kecamatan = new ArrayList<String>();
        kecamatan.add("Beji");
        kecamatan.add("Bojongsari");
        kecamatan.add("Cilodong");
        kecamatan.add("Cimanggis");
        kecamatan.add("Cinere");
        kecamatan.add("Cipayung");
        kecamatan.add("Limo");
        kecamatan.add("Pancoran Mas");
        kecamatan.add("Sawangan");
        kecamatan.add("Sukmajaya");
        kecamatan.add("Tapos");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, kecamatan);
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
