package com.hi_depok.hi_depok.fragment_kadepok_activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.hi_depok.hi_depok.R;

/**
 * Created by Muhammad63 on 3/4/2017.
 */

public class fragment2 extends Fragment{

    public static fragment2 newInstance() {

        Bundle args = new Bundle();

        fragment2 fragment = new fragment2();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  v = inflater.inflate(R.layout.kadepok_fragment2_activity, null);
        WebView view = (WebView) v.findViewById(R.id.textContent);
        view.setBackgroundColor(0);
        view.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
        WebSettings webSettings = view.getSettings();
        webSettings.setDefaultFontSize(12);
        String text;
        text = "<html>" +
                "<head>" +
                "<style type=\"text/css\"> .text{color:#b7b7b7;background-color:transparent;}" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<p align=\"justify\" class=\"text\">";
        text+= "Pengguna dapat mendaftar sebagai relawan sesuai dengan skill yang dimiliki dan " +
                "diminati oleh pengguna baik secara perorangan maupun kelompok.";
        text+= "</p>" +
                "</body>" +
                "</html>";
        view.loadData(text, "text/html", "utf-8");
        return v;
    }
}
