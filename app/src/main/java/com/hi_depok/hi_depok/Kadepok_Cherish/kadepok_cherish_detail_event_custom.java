package com.hi_depok.hi_depok.Kadepok_Cherish;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hi_depok.hi_depok.R;

/**
 * Created by Farhan Fadhli on 3/19/2017.
 */

public class kadepok_cherish_detail_event_custom extends PagerAdapter {
    private int [] imageResource = {R.drawable.doctor, R.drawable.book, R.drawable.camera, R.drawable.apotek};
    private Context ctx;
    private LayoutInflater layoutInflater;

    public kadepok_cherish_detail_event_custom(Context c) {
        ctx = c;
    }

    @Override
    public int getCount() {
        return imageResource.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.kadepok_cherish_detail_event_swipe,container,false);
        ImageView img =(ImageView)v.findViewById(R.id.dp_detail_event);
        TextView tv_nama  = (TextView)v.findViewById(R.id.nama);
        TextView tv_tgl  = (TextView)v.findViewById(R.id.tgl);
        TextView tv_tmpt  = (TextView)v.findViewById(R.id.tmpt);
        TextView tv_detail  = (TextView)v.findViewById(R.id.detail);
        img.setImageResource(imageResource[position]);
        tv_nama.setText("Nama Acara    : ACX0" + (position + 3));
        tv_tgl.setText("TTL           : "+ (position + 5) + " Agustus 2015");
        tv_tmpt.setText("Tempat        : Darul Ilmi");
        tv_detail.setText("Detail        : \n" +
                "Panti Asuhan Darul Ilmi melakukan perjalanan manuju Jogjakarta selama 7 hari 6 malam.");
        container.addView(v);
        return v;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view ==(LinearLayout)object);
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        container.refreshDrawableState();
    }
}
