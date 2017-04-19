package com.hi_depok.hi_depok.Lapok;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.Lapok.fragment.DescriptionForm;
import com.hi_depok.hi_depok.Lapok.mData.ArtistList;
import com.hi_depok.hi_depok.Lapok.mRecyler.MyAdapter;
import com.hi_depok.hi_depok.R;

import java.io.File;
import java.util.Date;

public class lapok_content extends BaseActivity {

    private File imageFile;
    String filename;
    private Spinner category, sortby;
    String kategori, status;

    private static final String[]cate = {"Kemacetan", "Bencana Alam", "Pelanggaran Peraturan"
            , "Jalan Rusak", "Tindak Kriminal", "Terorisme", "Narkoba"};
    private static final String[] stats = {"Belum Ada Tanggapan", "Proses", "Selesai"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lapok_content);
        super.onCreateDrawer();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView cameraBtn = (ImageView) findViewById(R.id.cameraBtn);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        MyAdapter adapter = new MyAdapter(this, ArtistList.getArtists());
        rv.setAdapter(adapter);

        /*-------------------------- START SPINER CATEGORY --------------------------------------- */
        category = (Spinner)findViewById(R.id.category);
        ArrayAdapter<String> adapter_cate = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,cate);

        adapter_cate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter_cate);
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                kategori = (String) adapterView.getItemAtPosition(position);
                Toast.makeText(getBaseContext(), kategori + " selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                kategori = "";
            }
        });

        /*-------------------------- START SPINER SORTBY --------------------------------------- */
        sortby = (Spinner)findViewById(R.id.sortby);
        ArrayAdapter<String> adapter_sort = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, stats);

        adapter_sort.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortby.setAdapter(adapter_sort);
        sortby.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                status = (String) adapterView.getItemAtPosition(position);
                Toast.makeText(getBaseContext(), status + " selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                status = "";
            }
        });

        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                filename = "image_" + new Date().getTime() + ".jpg";

                imageFile = new File(Environment.getExternalStorageDirectory()
                        + "/DCIM/", filename);
                Uri tempuri = Uri.fromFile(imageFile);
                camera.putExtra(MediaStore.EXTRA_OUTPUT, tempuri);
                camera.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);

                startActivityForResult(camera, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    if (imageFile.exists()) {
                        Toast.makeText(this, "The file was save at " + imageFile.getAbsolutePath(),
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "There was an error ", Toast.LENGTH_LONG).show();
                    }

                    /*imageFile = new File(Environment.getExternalStorageDirectory()
                            + "/DCIM/", filename);
                    Uri tempuri = Uri.fromFile(imageFile);
                    Bitmap capture = BitmapFactory.decodeFile(tempuri.getPath());*/
                    Intent deskripsi = new Intent(this, DescriptionForm.class);
                    //deskripsi.putExtra("background", capture);
                    startActivity(deskripsi);

                    break;
                case Activity.RESULT_CANCELED:
                    break;
                default:
                    break;
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                lapok_content.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
