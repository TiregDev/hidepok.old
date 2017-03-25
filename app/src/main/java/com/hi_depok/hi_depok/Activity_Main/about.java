package com.hi_depok.hi_depok.Activity_Main;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.hi_depok.hi_depok.R;

public class about extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

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
        text+= "Hi-Depok merupakan wujud peningkatan pelayanan pemerintah Kota Depok kepada warganya. " +
                "Aplikasi ini dapat membantu warga Kota Depok dalam berbagai aspek pelayanan seperti pelayanan " +
                "kesehatan, keamanan, sosial dan juga kebutuhan akan informasi.";
        text+= "</p>" +
                "</body>" +
                "</html>";
        view.loadData(text, "text/html", "utf-8");
    }

    public void about_home(View view){
        Intent intent = new Intent(about.this, MainActivity.class);
        startActivity(intent);
    }

}
