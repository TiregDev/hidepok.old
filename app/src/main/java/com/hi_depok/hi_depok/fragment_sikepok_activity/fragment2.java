package com.hi_depok.hi_depok.fragment_sikepok_activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.hi_depok.hi_depok.R;

/**
 * Created by Muhammad63 on 3/2/2017.
 */

public class fragment2 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment2_sikepok_activity, container, false);

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
        text+= "Pada modul ini anda dapat membeli sayur2an, buah2an, dll. Anda juga dapat memotong rambut " +
                "anda untuk terlihat lebih tampan, teruntuk anda kaum perempuan lebih baik anda menggunakan " +
                "kerudung jika tidak mau silahkan botakkan rambut anda, agar tidak ada laki2 yang melihat rambut anda. Thanks";
        text+= "</p>" +
                "</body>" +
                "</html>";
        view.loadData(text, "text/html", "utf-8");


        return v;
    }
}

