package com.hi_depok.hi_depok.Sikepok_RS;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hi_depok.hi_depok.R;

import java.util.ArrayList;

/**
 * Created by Muhammad on 13/03/2017.
 */

public class fasilitas extends Fragment {

    ImageView image_slide;
    LinearLayout gambar;
    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        List<String> pilih = new ArrayList<String>();
//        pilih.add("");
        v = inflater.inflate(R.layout.sikepokrs_fragment_fasilitas,null);
        gambar = (LinearLayout) v.findViewById(R.id.gambar);
        final ArrayList<Bitmap> bitmaps = new ArrayList<>();
        bitmaps.add(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.image_placeholder));
        bitmaps.add(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.image_placeholder));
        bitmaps.add(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.image_placeholder));
        bitmaps.add(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.image_placeholder));
        bitmaps.add(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.image_placeholder));
        image_slide = (ImageView) v.findViewById(R.id.image_slide);
        insertimage(bitmaps);
        return v;
    }

    void insertimage(final ArrayList<Bitmap> bitmaps){
        for (int i=0; i<bitmaps.size(); i++){
            final ImageView imageView = new ImageView(getActivity());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(PixelUtil.dp2px(80f,getActivity()), ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(layoutParams);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setAdjustViewBounds(true);
            imageView.setImageBitmap(bitmaps.get(i));
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupImage popupImage = PopupImage.newInstance(bitmaps,gambar.indexOfChild(imageView));
                    popupImage.show(getFragmentManager(),"");
                }
            });
            gambar.addView(imageView);
        }
    }
}
