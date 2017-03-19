package com.hi_depok.hi_depok.Kapok;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.hi_depok.hi_depok.R;
/**
 * Created by User on 3/17/2017.
 */

public class activity_view_ulasan extends AppCompatActivity {

    ListView list;
    String [] titles;
    String [] description;
    int [] imgs = {R.drawable.favorite, R.drawable.like, R.drawable.like_list, R.drawable.common_google_signin_btn_icon_dark};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_view_ulasan);

        Resources res = getResources();

        titles = res.getStringArray(R.array.titles);
        description = res.getStringArray(R.array.description);

        list = (ListView) findViewById(R.id.list1);
        MyAdapter adapter = new MyAdapter(this, titles, imgs, description);
        list.setAdapter(adapter);
    }
    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        int [] imgs;
        String myTitles[];
        String myDescription[];

        MyAdapter(Context c,String []titles,int[] imgs,String[]description) {
            super(c, R.layout.row, R.id.text1, titles);
            this.context = c;
            this.imgs = imgs;
            this.myDescription = description;
            this.myTitles = titles;
        }

        @Override
        public View getView (int position, View convertView, ViewGroup parent){
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView myImages = (ImageView) row.findViewById(R.id.icon);
            TextView myTitle = (TextView) row.findViewById(R.id.text1);
            TextView myDescription = (TextView) row.findViewById(R.id.text2);
            myImages.setImageResource(imgs[position]);
            myTitle.setText(titles[position]);
            myDescription.setText(description[position]);
            return row;
        }

    }
}
