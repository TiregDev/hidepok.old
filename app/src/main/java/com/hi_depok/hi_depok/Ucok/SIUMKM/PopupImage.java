package com.hi_depok.hi_depok.Ucok.SIUMKM;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.hi_depok.hi_depok.Activity_Main.ExtendedViewPager;
import com.hi_depok.hi_depok.R;

import java.util.ArrayList;

import static com.hi_depok.hi_depok.R.id.view_pager;

/**
 * Created by Muhammad63 on 4/24/2017.
 */

public class PopupImage extends DialogFragment implements View.OnClickListener {

    AppCompatDialog dialog;
    View view;
    ImageView options;
    ExtendedViewPager pager;
    ArrayList<Bitmap> bitmaps = new ArrayList<>();
    PopupWindow popupWindow;
    RelativeLayout layout;
    PopupImageAdapter adapter;
    int position;

    public static PopupImage newInstance(ArrayList<Bitmap> bitmaps, int position){
        Bundle args = new Bundle();
        args.putInt("position",position);
        args.putSerializable("bitmaps", bitmaps);
        PopupImage fasilitas_new = new PopupImage();
        fasilitas_new.setArguments(args);
        return fasilitas_new;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        position = getArguments().getInt("position");
        bitmaps = (ArrayList<Bitmap>) getArguments().getSerializable("bitmaps");
        view = LayoutInflater.from(getActivity()).inflate(R.layout.popup_image_thumbnail, null);
        pager = (ExtendedViewPager) view.findViewById(view_pager);
        layout = (RelativeLayout) view.findViewById(R.id.background);
        adapter = new PopupImageAdapter(getActivity(), bitmaps);
        pager.setAdapter(adapter);
        pager.setCurrentItem(position);

        dialog = new AppCompatDialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

        layout.setOnClickListener(this);

        return dialog;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.background:
                dismiss();
                break;
        }
    }
}
