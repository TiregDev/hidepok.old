package com.hi_depok.hi_depok.Kapok;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import com.hi_depok.hi_depok.R;

public class join_kapok extends Activity implements AdapterView.OnItemSelectedListener {

    private Vibrator vib;
    Animation animShake;
    private EditText nama, alamat, pj, email,telepon ;
    private TextInputLayout layout_nama, layout_alamat, layout_pj,layout_email, layout_telp;
    private Button selesai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_kapok);

        layout_nama = (TextInputLayout) findViewById(R.id.layout_nama);
        layout_alamat = (TextInputLayout) findViewById(R.id.layout_alamat);
        layout_pj = (TextInputLayout) findViewById(R.id.layout_pj);
        layout_email = (TextInputLayout) findViewById(R.id.layout_email);
        layout_telp = (TextInputLayout) findViewById(R.id.layout_telp);
        nama = (EditText) findViewById(R.id.nama);
        alamat = (EditText) findViewById(R.id.alamat);
        pj = (EditText) findViewById(R.id.pj);
        email = (EditText) findViewById(R.id.input_email);
        telepon = (EditText) findViewById(R.id.telepon);
        selesai = (Button) findViewById(R.id.selesai);

        animShake = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.design_fab_out);
        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });

        Spinner pilihan = (Spinner) findViewById(R.id.pilih);

        pilihan.setOnItemSelectedListener(this);
        List<String> pilih = new ArrayList<String>();
        pilih.add("Pilihan");
        pilih.add("Kuliner");
        pilih.add("Wisata");
        pilih.add("GOR");

        ArrayAdapter<String> pilihAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pilih);
        pilihAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        pilihan.setAdapter(pilihAdapter);

        //kecamatan
        Spinner camat = (Spinner) findViewById(R.id.camat);
        pilihan.setAdapter(pilihAdapter);

        camat.setOnItemSelectedListener(this);
        List<String> wilayah = new ArrayList<String>();
        wilayah.add("Kecamatan");
        wilayah.add("Beji");
        wilayah.add("Cilodong");
        wilayah.add("Cimanggis");
        wilayah.add("Cinere");
        wilayah.add("Cipayung");
        wilayah.add("Limo");
        wilayah.add("Pancoran Mas");
        wilayah.add("Sawangan");
        wilayah.add("Tapos");
        wilayah.add("Bojongsari");
        wilayah.add("Sukmajaya");

        ArrayAdapter<String> camatAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, wilayah);
        camatAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        camat.setAdapter(camatAdapter);

        ImageView foto = (ImageView) findViewById(R.id.foto);

        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initiatepopup();
            }
        });

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        // Showing selected spinner item

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }
    /**
     * Validating form
     */
    private void submitForm() {

        if (!ceknama()) {
            nama.setAnimation(animShake);
            nama.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if (!cekalamat()) {
            alamat.setAnimation(animShake);
            alamat.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if (!cekpj()) {
            pj.setAnimation(animShake);
            pj.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if (!cekemail()) {
            email.setAnimation(animShake);
            email.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if (!cektelepon()) {
            telepon.setAnimation(animShake);
            telepon.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        layout_nama.setErrorEnabled(false);
        layout_alamat.setErrorEnabled(false);
        layout_pj.setErrorEnabled(false);
        layout_email.setErrorEnabled(false);
        layout_telp.setErrorEnabled(false);

    }

    private boolean ceknama() {
        if (nama.getText().toString().trim().isEmpty()) {

            layout_nama.setErrorEnabled(true);
            layout_nama.setError(getString(R.string.err_msg_name));
            nama.setError(getString(R.string.err_msg_required));
            return false;
        }
        layout_nama.setErrorEnabled(false);
        return true;
    }

    private boolean cekalamat() {
        if (alamat.getText().toString().trim().isEmpty()) {

            layout_alamat.setErrorEnabled(true);
            layout_alamat.setError(getString(R.string.err_msg_alamat));
            alamat.setError(getString(R.string.err_msg_required));
            return false;
        }
        layout_alamat.setErrorEnabled(false);
        return true;
    }

    private boolean cekemail() {
        if (email.getText().toString().trim().isEmpty()) {

            layout_email.setErrorEnabled(true);
            layout_email.setError(getString(R.string.err_msg_email));
            nama.setError(getString(R.string.err_msg_required));
            return false;
        }
        layout_email.setErrorEnabled(false);
        return true;
    }


    private boolean cekpj() {
        if (pj.getText().toString().trim().isEmpty()) {

            layout_pj.setErrorEnabled(true);
            layout_pj.setError(getString(R.string.err_msg_pj));
            nama.setError(getString(R.string.err_msg_required));
            return false;
        }
        layout_pj.setErrorEnabled(false);
        return true;
    }
    private boolean cektelepon() {
        if (telepon.getText().toString().trim().isEmpty()) {

            layout_telp.setErrorEnabled(true);
            layout_telp.setError(getString(R.string.err_msg_telp));
            telepon.setError(getString(R.string.err_msg_required));
            return false;
        }
        layout_pj.setErrorEnabled(false);
        return true;
    }

    private PopupWindow pwindo;

    private void initiatepopup() {
        try {
            LayoutInflater inflater = (LayoutInflater) join_kapok.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.popup_upload_image, (ViewGroup) findViewById(R.id.popup_element));
            pwindo = new PopupWindow(layout, 300,200, true);
            pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);


            Button close = (Button) layout.findViewById(R.id.tutup);
            close.setOnClickListener(cancel_button_click_listener);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private View.OnClickListener cancel_button_click_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            pwindo.dismiss();
        }
    };


}
