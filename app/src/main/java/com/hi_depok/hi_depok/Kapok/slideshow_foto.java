package com.hi_depok.hi_depok.Kapok;


import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.hi_depok.hi_depok.R;
public class slideshow_foto extends PagerAdapter {
    private Activity activity;
    private Integer[] imagesArray;

    public slideshow_foto(Activity activity, Integer[] imagesArray){
        this.activity = activity;
        this.imagesArray = imagesArray;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position){
        LayoutInflater inflater = ((Activity)activity).getLayoutInflater();
        View v = inflater.inflate(R.layout.kapok_swip, container, false);
        ImageView img = (ImageView)v.findViewById(R.id.imageView);
        img.setImageResource(imagesArray[position]);
        ((ViewPager)container).addView(v);
        return v;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((View)object);
    }

    @Override
    public int getCount() {
        return imagesArray.length;
    }

    public void destroyItem(ViewGroup container, int position, Object object){
        ((ViewPager)container).removeView((View)object);
    }
}