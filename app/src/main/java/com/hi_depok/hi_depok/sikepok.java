package com.hi_depok.hi_depok;

import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

public class sikepok extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sikepok);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        TextView tv = (TextView)findViewById(R.id.sikepok);
        Typeface tf = Typeface.createFromAsset(getAssets(), "font/Blogger_Sans-Bold.otf");
        tv.setTypeface(tf);

        TextView tv2 = (TextView)findViewById(R.id.diagnosa);
        Typeface tf2 = Typeface.createFromAsset(getAssets(), "font/Blogger_Sans-Bold.otf");
        tv2.setTypeface(tf2);

        WebView view = (WebView) findViewById(R.id.textContent);
        view.setBackgroundColor(0);
        view.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
        WebSettings webSettings = view.getSettings();
        webSettings.setDefaultFontSize(12);
        String text;
        text = "<html>" +
                "<head>" +
                "<style type=\"text/css\"> .text{color:#7f8c8d;background-color:transparent;}" +
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
    }
}
