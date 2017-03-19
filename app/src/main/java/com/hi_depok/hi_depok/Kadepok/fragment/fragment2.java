package com.hi_depok.hi_depok.Kadepok.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;

import com.hi_depok.hi_depok.Kadepok_Cherish.kadepok_cherish_panti;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  v = inflater.inflate(R.layout.fragment2_kadepok_content, null);

        GridView gridView = (GridView) v.findViewById(R.id.gridview);
        gridView.setAdapter(new ImageAdapter(v.getContext()));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent next = new Intent(getActivity(), kadepok_cherish_panti.class);
                startActivity(next);
            }
        });

        String [] values =
                {"Pilih Kecamatan", "Beji", "Bojongsari", "Cilodong", "Cimanggis", " +" +
                        "Cinere", "Cipayung", "Limo", "Pancoran Mas", "Sawangan", "Sukmajaya", "Tapos"};
        Spinner spinner = (Spinner) v.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        return v;
    }
    }
