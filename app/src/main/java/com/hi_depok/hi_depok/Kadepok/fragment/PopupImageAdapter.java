package com.hi_depok.hi_depok.Kadepok.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hi_depok.hi_depok.Activity_Main.TouchImageView;

import java.util.ArrayList;


/**
 * Created by Aulia on 10/03/2017.
 */

public class PopupImageAdapter extends PagerAdapter {
    Context context;
    ArrayList<Bitmap> bitmaps = new ArrayList<>();

    public PopupImageAdapter(Context context, ArrayList<Bitmap> bitmaps) {
        this.context = context;
        this.bitmaps = bitmaps;
    }

    @Override
    public int getCount() {
        return bitmaps.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        TouchImageView tiv = new TouchImageView(context);
        tiv.setImageBitmap(bitmaps.get(position));
        container.addView(tiv, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        return tiv;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
