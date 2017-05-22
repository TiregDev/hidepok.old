
package com.hi_depok.hi_depok.Kadepok_Volunteer;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.Activity_Main.kadepok;
import com.hi_depok.hi_depok.Kadepok.activity.kadepok_content;
import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.Sikepok_RS.regis_pasien_baru;
import com.hi_depok.hi_depok.Sikepok_RS.registrasi;

import static java.security.AccessController.getContext;

public class kadepok_volunteer_form extends BaseActivity {
    public Button btn_volunteer;
//    private PopupWindow popupWindow;
//    public Button close;

    //inisialisasi sms
    private Button kirim;
    private EditText volounter, nama, telepon, keahlian;
    private Spinner panti;
    String panti_terpilih;

//    public void verify_volunteer() {
//        try {
//
//            LayoutInflater layoutInflater = (LayoutInflater)kadepok_volunteer_form.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View layout = layoutInflater.inflate(R.layout.kadepok_volunteer_popup, (ViewGroup)findViewById(R.id.rl_custom_layout));
//
//            popupWindow = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
//            popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);
//
//            close = (Button)layout.findViewById(R.id.close);
//            close.setOnClickListener(cancel_button_click_listener);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private View.OnClickListener cancel_button_click_listener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            popupWindow.dismiss();
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kadepok_volunteer_form);
        super.onCreateDrawer();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btn_volunteer = (Button)findViewById(R.id.btn_volunteer);
//        btn_volunteer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                verify_volunteer();
//            }
//        });

        //Pengiriman SMS
        panti = (Spinner) findViewById(R.id.pilih_panti);
        volounter = (EditText) findViewById(R.id.jenis_lain_volunteer);
        nama = (EditText) findViewById(R.id.nama_volunteer);
        telepon = (EditText) findViewById(R.id.tlp_volunteer);
        keahlian = (EditText) findViewById(R.id.keahlian_volunteer);

        kirim = (Button) findViewById(R.id.btn_volunteer);
        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS();
            }

        });
    }

    //mengirim sms
    protected void sendSMS()
    {
        if (panti.getSelectedItem().toString().equals("Panti Riyadush Solihun")){
            panti_terpilih = "085715720688";
        } else if (panti.getSelectedItem().toString().equals("Panti Tubagus Pangeling")){
            panti_terpilih = "085776410906";
        } else if (panti.getSelectedItem().toString().equals("Panti Uswatun Hasanah")){
            panti_terpilih = "085776410906";
        } else if (panti.getSelectedItem().toString().equals("Panti Madani")){
            panti_terpilih = "085776410906";
        } else if (panti.getSelectedItem().toString().equals("Panti Darussalam")){
            panti_terpilih = "085776410906";
        }

        String number=panti_terpilih;

        String part1 = volounter.getText().toString();
        String part2 = nama.getText().toString();
        String part3 = telepon.getText().toString();
        String part4 = keahlian.getText().toString();

        String message = "Ada volunteer yang mau daftar, datanya: \n" + part1 + "#" + part2 + "#" + part3 + "#" + part4 + "#";
        try {
            SmsManager manager = SmsManager.getDefault();
            manager.sendTextMessage(number, null, message,null,null);
            Toast.makeText(getApplicationContext(), "Permintaan volunteer Berhasil terkirim ke panti asuhan terpilih!", Toast.LENGTH_LONG).show();
            Intent next = new Intent(kadepok_volunteer_form.this, kadepok_content.class);
            startActivity(next);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),"Permintaan volunteer Gagal terkirim, silahkan coba lagi tahun depan!", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }

    //lain2
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                kadepok_volunteer_form.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

