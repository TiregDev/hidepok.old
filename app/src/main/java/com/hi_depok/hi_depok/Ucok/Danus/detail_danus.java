package com.hi_depok.hi_depok.Ucok.Danus;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.Ucok.SIUMKM.GetDataAdapter_siumkm;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class detail_danus extends BaseActivity {
    public static final String PATOKAN = "patokan";
    String GET_JSON_DATA_HTTP_URL;
    String JSON_ID_BARANG = "id_barang";
    String JSON_NAMA_UKM = "nama_ukm";
    String JSON_NAMA_BARANG = "nama_barang";
    String JSON_HARGA_BARANG = "harga_barang";
    String JSON_FOTO_BARANG = "foto_barang";
    String JSON_NO_TLP = "notelp";
    String JSON_KORDINAT_1 = "koordinat1";
    String JSON_KORDINAT_2 = "koordinat2";
    String JSON_TARGET_WAKTU= "target_waktu";
    String JSON_TARGET_UANG = "target_harga";
    String JSON_HARGA_JUAL = "harga_jual";
    String JSON_DESC_UKM = "deskripsi_ukm";
    String JSON_ALAMAT_UKM = "alamat_ukm";
    String JSON_OWNER_UKM = "nama_owner_ukm";
    String JSON_KECAMATAN;
    CircleImageView imageView;
    TextView list_title, deskripsi_ukm, alamat_ukm, barang, owner;
    Double kordinat1, kordinat2;
    String no_tlp, namaukm;
    String urlPhoto;
    JsonArrayRequest jsonArrayRequest;
    List<GetDataAdapter_siumkm> dataAdapter;
    RequestQueue requestQueue ;
    RecyclerView.Adapter recyclerViewadapter;
    RecyclerView rView;

    Date date = Calendar.getInstance().getTime();
    DateFormat formatdate = new SimpleDateFormat("dd/MM/yyyy");
    TextView dateAndTimeLabel;
    Calendar dateAndTime = Calendar.getInstance();
    Button mulai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ucok_detail_danus);
        super.onCreateDrawer();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //---------------- Image Single Popup
        final CircleImageView imageView = (CircleImageView) findViewById(R.id.list_avatar);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog settingsDialog = new Dialog(detail_danus.this);

                LayoutInflater inflater = getLayoutInflater();
                View newView = inflater.inflate(R.layout.activity_image, null);

                settingsDialog.setContentView(newView);
                settingsDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                settingsDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.alpha(0)));

                ImageView iv= (ImageView) newView.findViewById(R.id.profile_img_popup);
                Bitmap bm=((BitmapDrawable)imageView.getDrawable()).getBitmap();
                iv.setImageBitmap(bm);

                settingsDialog.show();


            }
        });
        Intent i = this.getIntent();
        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/android/ucok/ucok_dss.php?id_barang=" + i.getExtras().getString(PATOKAN);
        JSON_DATA_WEB_CALL();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ucok, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                detail_danus.this.finish();
                return true;
//            case R.id.action_share:
            case R.id.add:
                showMessageDialog_danus();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    DatePickerDialog.OnDateSetListener d =
            new DatePickerDialog.OnDateSetListener(){
                @Override
                public void onDateSet(DatePicker view, int year, int month,
                                      int day){
                    dateAndTime.set(Calendar.YEAR, year);
                    dateAndTime.set(Calendar.MONTH, month);
                    dateAndTime.set(Calendar.DAY_OF_MONTH, day);
                    updateLabel();
                }
            };

    private void showMessageDialog_danus (){
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout= inflater.inflate(R.layout.ucok_add_danus_dialog, null);
        final EditText formMessage1 = (EditText) alertLayout.findViewById(R.id.nama_target);
        final EditText formMessage2 = (EditText) alertLayout.findViewById(R.id.deskripsi);

        dateAndTimeLabel = (TextView) alertLayout.findViewById(R.id.gantitanggal);
        updateLabel();

        mulai = (Button) alertLayout.findViewById(R.id.date);
        mulai.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                settingTanggal();
            }
        });

        AlertDialog.Builder alert = new AlertDialog.Builder(detail_danus.this);
        alert.setTitle("Silahkan Masukkan Data Target");
        alert.setView(alertLayout);
        alert.setCancelable(true);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getBaseContext(), "Batal", Toast.LENGTH_SHORT).show();
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

        alert.setPositiveButton("Tambahkan", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(detail_danus.this, detail_target.class);
                startActivity(intent);
//                Toast.makeText(getApplicationContext(),"Bisa",Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
        Button buttonPositive = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        buttonPositive.setTextColor(ContextCompat.getColor(this, R.color.abuAbu));
        Button buttonNegative = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        buttonNegative.setTextColor(ContextCompat.getColor(this, R.color.abuAbu));
    }
//    public void link_addusaha (View v){
//        showMessageDialog_danus();
//    }

    private void updateLabel(){
        dateAndTimeLabel.setText(
                formatdate.format(date)
        );
    }

    public void JSON_DATA_WEB_CALL(){
        jsonArrayRequest = new JsonArrayRequest(getIntent().getExtras().getString("urlJSON"),
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
                urlPhoto = "http://hidepok.id/assets/images/photos/ucok/"+json.getString(JSON_FOTO_BARANG);
                Picasso.with(this).load(urlPhoto).resize(300, 300).placeholder(R.drawable.image_placeholder).into(imageView);
                String desc_owner = "Nama owner : "+json.getString(JSON_OWNER_UKM);
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

    private void settingTanggal(){
        new DatePickerDialog(detail_danus.this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
    }
    public void toMaps(View v){
        Double myLatitude = -6.3656374;
        Double myLongitude = 106.8237375;
        String labelLocation = "Perpus UI";

        String urlAddress = "http://maps.google.com/maps?q=" + myLatitude + "," + myLongitude + "(" + labelLocation + ")&iwloc=A&hl=es";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlAddress));
        startActivity(intent);
    }

    public void toCall(View v){
        String PhoneNo="085695454139";
        Intent dial = new Intent();
        dial.setAction("android.intent.action.DIAL");
        dial.setData(Uri.parse("tel:" + PhoneNo));
        startActivity(dial);
    }
}
