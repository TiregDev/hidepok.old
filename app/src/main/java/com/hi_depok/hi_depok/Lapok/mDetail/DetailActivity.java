package com.hi_depok.hi_depok.Lapok.mDetail;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.Lapok.lapok_content;
import com.hi_depok.hi_depok.R;

public class DetailActivity extends BaseActivity {

    TextView nameTxt, timeTxt, title, jml_like, jml_com, jml_share;
    ImageView img, kej, likeimg, comimg, shaimg;
    /*String[] pilihan = {
            "Merbabu", "Merapi", "Lawu", "Rinjani",
            "Sumbing","Sindoro", "Krakatau", "Selat Sunda",
            "Selat Bali","Selat Malaka","Kalimantan",
            "Sulawesi", "Jawa" };
    ArrayList mArrayList;*/
    EditText comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lapok_detail_komentar);
        super.onCreateDrawer();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //INITIALIZE VIEWS
        nameTxt = (TextView) findViewById(R.id.nameTxtdetail);
        timeTxt = (TextView) findViewById(R.id.timeTxtdetail);
        title = (TextView) findViewById(R.id.card_text_detail);
        jml_like = (TextView) findViewById(R.id.jumlah_like_detail);
        jml_com = (TextView) findViewById(R.id.jumlah_comment_detail);
        jml_share = (TextView) findViewById(R.id.jumlah_share_detail);
        img = (ImageView) findViewById(R.id.imageArtist_detail);
        kej = (ImageView) findViewById(R.id.card_image_detail);
        likeimg = (ImageView) findViewById(R.id.like_detail);
        comimg = (ImageView) findViewById(R.id.comment_button_detail);
        shaimg = (ImageView) findViewById(R.id.share_button_detail);
        comment = (EditText) findViewById(R.id.isiKomentar);


        //RECEIVE DATA
        Intent i = this.getIntent();
        String name = i.getExtras().getString("NAME_KEY");
        String time = i.getExtras().getString("TIME_KEY");
        String judul = i.getExtras().getString("TITLE_KEY");
        String jumlah_suka = i.getExtras().getString("TOTAL_LIKE");
        String jumlah_komentar = i.getExtras().getString("TOTAL_COMMENT");
        String jumlah_bagikan = i.getExtras().getString("TOTAL_SHARE");
        int image = i.getExtras().getInt("IMAGE_KEY");
        int kejadian = i.getExtras().getInt("KEJADIAN_KEY");
        int like = i.getExtras().getInt("LIKE_KEY");
        int comment = i.getExtras().getInt("COMMENT_KEY");
        int share = i.getExtras().getInt("SHARE_KEY");

        //BIND DATA
        nameTxt.setText(name);
        timeTxt.setText(time);
        title.setText(judul);
        jml_like.setText(jumlah_suka);
        jml_com.setText(jumlah_komentar);
        jml_share.setText(jumlah_bagikan);
        img.setImageResource(image);
        kej.setImageResource(kejadian);
        likeimg.setImageResource(like);
        comimg.setImageResource(comment);
        shaimg.setImageResource(share);
        jml_like.setText("0");
        jml_com.setText("0");
        jml_share.setText("0");

        likeimg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                jml_like.setText("1");
                likeimg.setImageResource(R.drawable.favorite);
                Toast.makeText(view.getContext(), "Anda menyukai konten ini",
                        Toast.LENGTH_SHORT).show();
            }
        });

        comimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Klik Gambar Untuk Memberikan Komentar",
                        Toast.LENGTH_SHORT).show();
            }
        });

        shaimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Yuk memberi dampak perubahan positif" +
                        "untuk kota Depok ;) ";
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                view.getContext().startActivity(Intent.createChooser(sharingIntent, "Share via"));

                jml_share.setText("1");
                Toast.makeText(view.getContext(), "Sebarkan berita ini",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                DetailActivity.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
