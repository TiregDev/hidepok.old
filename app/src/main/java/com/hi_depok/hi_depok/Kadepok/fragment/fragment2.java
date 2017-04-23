package com.hi_depok.hi_depok.Kadepok.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.hi_depok.hi_depok.Kadepok.activity.KadepokDetailActivity;
import com.hi_depok.hi_depok.R;

import java.io.File;
import java.util.Date;

/**
 * Created by Muhammad63 on 3/16/2017.
 */

public class fragment2 extends DialogFragment {

    public static fragment2 newInstance() {
        Bundle args = new Bundle();

        fragment2 fragment = new fragment2();
        fragment.setArguments(args);
        return fragment;
    }

    private File imageFile;
    String filename;
    public Button donate, bukti;
    private PopupWindow popupWindow;
    public Button close;
    public TextView txtImage;
    View v;

    public void verify_volunteer() {
        try {

            LayoutInflater layoutInflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = layoutInflater.inflate(R.layout.kadepok_donasi_popup, (ViewGroup)v.findViewById(R.id.rl_custom_layout));

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
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.kadepok_fragment2_kadepok_content, null);
//        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        donate = (Button)v.findViewById(R.id.donate);
        bukti = (Button)v.findViewById(R.id.bukti);
        txtImage = (TextView)v.findViewById(R.id.txtBukti);

        donate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                verify_volunteer();
            }
        });

        bukti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                filename = "image_" + new Date().getTime() + ".jpg";

                imageFile = new File(Environment.getExternalStorageDirectory()
                        + "/DCIM/", filename);
                Uri tempuri = Uri.fromFile(imageFile);
                camera.putExtra(MediaStore.EXTRA_OUTPUT, tempuri);
                camera.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);

                startActivityForResult(camera, 0);
            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    if (imageFile.exists()) {
                        Toast.makeText(getActivity(), "The file was save at " + imageFile.getAbsolutePath(),
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "There was an error ", Toast.LENGTH_LONG).show();
                    }
                    txtImage.setText(filename);
                    /*imageFile = new File(Environment.getExternalStorageDirectory()
                            + "/DCIM/", filename);
                    Uri tempuri = Uri.fromFile(imageFile);
                    Bitmap capture = BitmapFactory.decodeFile(tempuri.getPath());*/
                    Intent deskripsi = new Intent(getActivity(), KadepokDetailActivity.class);
                    //deskripsi.putExtra("background", capture);
                    startActivity(deskripsi);

                    break;
                case Activity.RESULT_CANCELED:
                    break;
                default:
                    break;
            }
        }
    }
}
