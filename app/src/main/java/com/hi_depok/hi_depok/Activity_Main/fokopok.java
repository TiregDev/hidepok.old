package com.hi_depok.hi_depok.Activity_Main;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.hi_depok.hi_depok.R;

public class fokopok extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fokopok);

        TextView tv = (TextView) findViewById(R.id.fokopok);
        Typeface tf = Typeface.createFromAsset(getAssets(), "font/Blogger_Sans-Bold.otf");
        tv.setTypeface(tf);

        WebView view = (WebView) findViewById(R.id.textContent);
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
                "kerudung jika tidak mau silahkan botakkan rambut anda, agar tidak ada laki2 yang melihat rambut anda. Thx";
        text+= "</p>" +
                "</body>" +
                "</html>";
        view.loadData(text, "text/html", "utf-8");
    }
}
