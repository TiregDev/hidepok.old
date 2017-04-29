package com.hi_depok.hi_depok.Kapok.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hi_depok.hi_depok.R;

/**
 * Created by User on 4/20/2017.
 */

public class ViewPagerAdapter extends PagerAdapter {

    String [] image;
    LayoutInflater inflater;
    Context context;

    public ViewPagerAdapter(Context context, String[] img) {
        this.context = context;
        this.image = img;
    }

    @Override
    public int getCount() {
        return image.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imgs;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemview = inflater.inflate(R.layout.kapok_detail, container, false);
        imgs = (ImageView) itemview.findViewById(R.id.image);
        Glide.with(context).load(image[position]).thumbnail(0.3f).placeholder(R.drawable.image_placeholder).into(imgs);

        //add item
        ((ViewPager) container).addView(itemview);
        return itemview;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }
}
