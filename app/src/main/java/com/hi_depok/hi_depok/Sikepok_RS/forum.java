package com.hi_depok.hi_depok.Sikepok_RS;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.hi_depok.hi_depok.R;

public class forum extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
    }

    public void ke_detail_post (View view){
        Intent next = new Intent(forum.this, detail_post.class);
        startActivity(next);
    }

    public void ke_detail_post_saya (View view){
        Intent next = new Intent(forum.this, detail_post_saya.class);
        startActivity(next);
    }

    public void ke_post_saya (View view){
        Intent next = new Intent(forum.this, post_saya.class);
        startActivity(next);
    }

    public void ke_post_terbaru (View view){
        Intent next = new Intent(forum.this, list_diskusi.class);
        startActivity(next);
    }
}
