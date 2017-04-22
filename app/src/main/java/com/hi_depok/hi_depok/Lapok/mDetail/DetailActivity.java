package com.hi_depok.hi_depok.Lapok.mDetail;

import android.app.Dialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.Ucok.UcokDetailActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailActivity extends BaseActivity {

    TextView nameTxt, timeTxt, title, jml_like, jml_com, jml_share;
    ImageView img, kej, likeimg, comimg, shaimg, btnKirim;
    EditText etKomentar;
    EditText comment;
    private NotificationCompat.Builder mBuilder;

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
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //---------------- Image Single Popup --------------------------------------------------
        final CircleImageView imageView = (CircleImageView) findViewById(R.id.imageArtist_detail);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog settingsDialog = new Dialog(DetailActivity.this);

                LayoutInflater inflater = getLayoutInflater();
                View newView = inflater.inflate(R.layout.activity_image, null);

                settingsDialog.setContentView(newView);
                settingsDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                settingsDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.alpha(0)));

                ImageView iv = (ImageView) newView.findViewById(R.id.profile_img_popup);
                Bitmap bm = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                iv.setImageBitmap(bm);

                settingsDialog.show();
            }
    });

        //INITIALIZE VIEWS
        btnKirim = (ImageView) findViewById(R.id.btnKirim);
        etKomentar = (EditText) findViewById(R.id.isiKomentar);
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
        final String name = i.getExtras().getString("NAME_KEY");
        final String time = i.getExtras().getString("TIME_KEY");
        final String judul = i.getExtras().getString("TITLE_KEY");
        final String jumlah_suka = i.getExtras().getString("TOTAL_LIKE");
        final String jumlah_komentar = i.getExtras().getString("TOTAL_COMMENT");
        final String jumlah_bagikan = i.getExtras().getString("TOTAL_SHARE");
        final int image = i.getExtras().getInt("IMAGE_KEY");
        final int kejadian = i.getExtras().getInt("KEJADIAN_KEY");
        final int like = i.getExtras().getInt("LIKE_KEY");
        final int comment = i.getExtras().getInt("COMMENT_KEY");
        final int share = i.getExtras().getInt("SHARE_KEY");

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

        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String isi = etKomentar.getText().toString();
                String text = name + " mengomentari : " + isi;
                mBuilder = new NotificationCompat.Builder(DetailActivity.this);
                mBuilder
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Lapok")
                        .setAutoCancel(true)
                        .setContentText(text);
                buildNotification(DetailActivity.this, name, time, judul, jumlah_suka, jumlah_komentar,
                        jumlah_bagikan, image, kejadian, like, comment, share);
            }
        });
    }

    //------------------------------------BUAT NOTIFIKASI-----------------------------------------//
    private void buildNotification(Context ctx, String name, String time, String title, String totlike,
                                   String totcom, String totshare, int image, int kejadian,
                                   int like, int comment, int share){

        Intent infoNotification = new Intent(ctx, DetailActivity.class);

        //PACK DATA TO SEND
        infoNotification.putExtra("NAME_KEY", name);
        infoNotification.putExtra("TIME_KEY", time);
        infoNotification.putExtra("TITLE_KEY", title);
        infoNotification.putExtra("IMAGE_KEY", image);
        infoNotification.putExtra("KEJADIAN_KEY", kejadian);
        infoNotification.putExtra("LIKE_KEY", like);
        infoNotification.putExtra("COMMENT_KEY", comment);
        infoNotification.putExtra("SHARE_KEY", share);
        infoNotification.putExtra("TOTAL_COMMENT", totcom);
        infoNotification.putExtra("TOTAL_LIKE", totlike);
        infoNotification.putExtra("TOTAL_SHARE", totshare);

        //OPEN ACTIVITY
        TaskStackBuilder taskStackBuilder = TaskStackBuilder
                .create(DetailActivity.this);
        taskStackBuilder.addParentStack(DetailActivity.class);
        taskStackBuilder.addNextIntent(infoNotification);
        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pendingIntent);
        Notification notification = mBuilder.build();
        notification.flags = Notification.DEFAULT_LIGHTS | Notification.FLAG_AUTO_CANCEL;
        NotificationManagerCompat.from(DetailActivity.this).notify(0, notification);
    }
    //--------------------------------------------------------------------------------------------//

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
