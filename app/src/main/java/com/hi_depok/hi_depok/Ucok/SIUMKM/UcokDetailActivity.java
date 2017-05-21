package com.hi_depok.hi_depok.Ucok.SIUMKM;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

public class UcokDetailActivity extends BaseActivity {
    public static final String PATOKAN = "patokan";
    String GET_JSON_DATA_HTTP_URL;
    String JSON_ID_UKM = "id_ukm";
    String JSON_ID_BARANG = "id_barang";
    String JSON_NAMA_UKM = "nama_ukm";
    String JSON_NAMA_OWNER_UKM = "nama_owner_ukm";
    String JSON_NAMA_BARANG = "daftar_barang";
    String JSON_KATEGORI = "daftar_kategori";
    String JSON_HARGA_BARANG = "daftar_harga";
    String JSON_ALAMAT_UKM = "alamat_ukm";
    String JSON_NO_TLP = "notelp";
    String JSON_KECAMATAN = "kecamatan";
    String JSON_DESC_UKM = "deskripsi_ukm";
    String JSON_KORDINAT_1 = "koordinat1";
    String JSON_KORDINAT_2 = "koordinat2";
    String JSON_FOTO_BARANG = "foto_barang";
    String JSON_FOTO_UKM = "foto_ukm";
    String urlPhoto, no_tlp, namaukm;
    Double kordinat1, kordinat2;
    CircleImageView imageView;
    TextView list_title, deskripsi_ukm, alamat_ukm, barang, owner;
    JsonArrayRequest jsonArrayRequest;
    List<GetDataAdapter_siumkm> dataAdapter;
    RequestQueue requestQueue;
    RecyclerView.Adapter recyclerViewadapter;
    RecyclerView rView;
    ImageView image_slide;
    LinearLayout gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ucok_detail_siumkm);
        super.onCreateDrawer();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list_title = (TextView) findViewById(R.id.list_title);
        deskripsi_ukm = (TextView) findViewById(R.id.deskripsi_ukm);
        alamat_ukm = (TextView) findViewById(R.id.alamat_ukm);
        barang = (TextView) findViewById(R.id.barang);
        owner = (TextView) findViewById(R.id.owner);
        //---------------- Image Single Popup --------------------------------------------------
        imageView = (CircleImageView) findViewById(R.id.list_avatar);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog settingsDialog = new Dialog(UcokDetailActivity.this);

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
        Intent i = this.getIntent();
        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/android/ucok/ucok_json.php?id_ukm=" + i.getExtras().getString(PATOKAN);
        JSON_DATA_WEB_CALL();

        gambar = (LinearLayout) findViewById(R.id.gambar);
        final ArrayList<Bitmap> bitmaps = new ArrayList<>();
        bitmaps.add(BitmapFactory.decodeResource(getBaseContext().getResources(),R.drawable.image_placeholder));
        bitmaps.add(BitmapFactory.decodeResource(getBaseContext().getResources(),R.drawable.image_placeholder));
        bitmaps.add(BitmapFactory.decodeResource(getBaseContext().getResources(),R.drawable.image_placeholder));
        bitmaps.add(BitmapFactory.decodeResource(getBaseContext().getResources(),R.drawable.image_placeholder));
        bitmaps.add(BitmapFactory.decodeResource(getBaseContext().getResources(),R.drawable.image_placeholder));
        image_slide = (ImageView) findViewById(R.id.image_slide);
        insertimage(bitmaps);
    }

    void insertimage(final ArrayList<Bitmap> bitmaps){
        for (int i=0; i<bitmaps.size(); i++){
            final ImageView imageView = new ImageView(getBaseContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(PixelUtil.dp2px(80f,getBaseContext()), ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(layoutParams);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setAdjustViewBounds(true);
            imageView.setImageBitmap(bitmaps.get(i));
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupImage popupImage = PopupImage.newInstance(bitmaps,gambar.indexOfChild(imageView));
                    popupImage.show(getSupportFragmentManager(),"");
                }
            });
            gambar.addView(imageView);
        }
    }

    public void JSON_DATA_WEB_CALL(){
        jsonArrayRequest = new JsonArrayRequest(GET_JSON_DATA_HTTP_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSON_PARSE_DATA_AFTER_WEBCALL(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    public void JSON_PARSE_DATA_AFTER_WEBCALL(JSONArray array){
        for(int i = 0; i<array.length(); i++) {
            JSONObject json = null;
            try {
                json = array.getJSONObject(i);
                urlPhoto = "http://hidepok.id/assets/images/photos/ucok/"+json.getString(JSON_FOTO_UKM);
                Picasso.with(this).load(urlPhoto).resize(300, 300).placeholder(R.drawable.image_placeholder).into(imageView);
                String desc_owner = "Nama owner : "+json.getString(JSON_NAMA_OWNER_UKM);
                String desc_alamat = json.getString(JSON_ALAMAT_UKM)+", "+json.getString(JSON_KECAMATAN)+", Depok.";
                String desc_barang = json.getString(JSON_NAMA_UKM)+" menjual "+json.getString(JSON_NAMA_BARANG);
                list_title.setText(json.getString(JSON_NAMA_UKM));
                deskripsi_ukm.setText(json.getString(JSON_DESC_UKM));
                alamat_ukm.setText(desc_alamat);
                barang.setText(desc_barang);
                owner.setText(desc_owner);
                no_tlp = json.getString(JSON_NO_TLP);
                kordinat1 = json.getDouble(JSON_KORDINAT_1);
                kordinat2 = json.getDouble(JSON_KORDINAT_2);
                namaukm = json.getString(JSON_NAMA_UKM);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                UcokDetailActivity.this.finish();
                return true;
//            case R.id.action_share:
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void toMaps(View v) {
        Double myLatitude = kordinat1;
        Double myLongitude = kordinat2;
        String labelLocation = namaukm;

        String urlAddress = "http://maps.google.com/maps?q=" + myLatitude + "," + myLongitude + "(" + labelLocation + ")&iwloc=A&hl=es";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlAddress));
        startActivity(intent);
    }

    public void toCall(View v) {
        String PhoneNo = no_tlp;
        Intent dial = new Intent();
        dial.setAction("android.intent.action.DIAL");
        dial.setData(Uri.parse("tel:" + PhoneNo));
        startActivity(dial);
    }
}