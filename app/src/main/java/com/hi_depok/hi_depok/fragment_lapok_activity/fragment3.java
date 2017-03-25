package com.hi_depok.hi_depok.fragment_lapok_activity;

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

public class fragment3 extends Fragment{

    public static fragment3 newInstance() {

        Bundle args = new Bundle();

        fragment3 fragment = new fragment3();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  v = inflater.inflate(R.layout.lapok_fragment3_activity, null);
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
        text+= "Video ini akan memberikan anda tutorial dalam cara menggunakan modul Lapok ini secara singkat dan menarik dengan gaya motion graphics";
        text+= "</p>" +
                "</body>" +
                "</html>";
        view.loadData(text, "text/html", "utf-8");
        return v;
    }
}
