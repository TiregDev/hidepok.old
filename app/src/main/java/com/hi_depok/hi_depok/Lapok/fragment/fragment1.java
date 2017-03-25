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

    public static fragment1 newInstance() {
        Bundle args = new Bundle();

        fragment1 fragment = new fragment1();
        fragment.setArguments(args);
        return fragment;
    }

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View  v = inflater.inflate(R.layout.fragment1_lapok_content, null);
//        return v;
//    }

}
