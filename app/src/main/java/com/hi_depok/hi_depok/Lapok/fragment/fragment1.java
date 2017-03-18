package com.hi_depok.hi_depok.Lapok.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hi_depok.hi_depok.R;

import java.io.File;

/**
 * Created by Muhammad63 on 3/16/2017.
 */

public class fragment1 extends Fragment {
    File imageFile;

    public static com.hi_depok.hi_depok.Lapok.fragment.fragment1 newInstance() {
        Bundle args = new Bundle();

        com.hi_depok.hi_depok.Lapok.fragment.fragment1 fragment = new com.hi_depok.hi_depok.Lapok.fragment.fragment1();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  v = inflater.inflate(R.layout.fragment1_lapok_content, null);
        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        imageFile = new File(Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "test.jpg");
        Uri tempuri = Uri.fromFile(imageFile);
        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, tempuri);
        intentCamera.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);

        startActivityForResult(intentCamera, 0);
        return v;
    }
//    public void process(View view) {
//
//        //set date as the name of picture
//
//        /*DatePickerDialog.OnDateSetListener d =
//                new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month,
//                                          int day) {
//                        dateAndTime.set(Calendar.YEAR, year);
//                        dateAndTime.set(Calendar.MONTH, month);
//                        dateAndTime.set(Calendar.DAY_OF_MONTH, day);
//                    }
//                };*/
//
//        //intent khusus untuk menangkap foto lewat kamera
//        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//        imageFile = new File(Environment
//                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
//                "test.jpg");
//        Uri tempuri = Uri.fromFile(imageFile);
//        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, tempuri);
//        intentCamera.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
//
//        startActivityForResult(intentCamera, 0);
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    if (imageFile.exists()) {
                        Toast.makeText(getContext(), "The file was save at " + imageFile.getAbsolutePath(),
                                Toast.LENGTH_LONG).show();
                        Intent intentDeskripsi = new Intent(getContext(), DescriptionForm.class);
                        startActivity(intentDeskripsi);
                    } else {
                        Toast.makeText(getContext(), "There was an error ", Toast.LENGTH_LONG).show();
                    }
                    break;
                case Activity.RESULT_CANCELED:
                    break;
                default:
                    break;
            }
        }
    }
}
