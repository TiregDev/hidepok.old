package com.hi_depok.hi_depok.Kapok;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
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

import com.hi_depok.hi_depok.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class join_kapok extends Activity implements AdapterView.OnItemSelectedListener {
    private static int RESULT_LOAD_IMAGE = 1;
    private Vibrator vib;
    Animation animShake;
    private EditText nama, alamat, pj, email, telepon;
    private TextInputLayout layout_nama, layout_alamat, layout_pj, layout_email, layout_telp;
    private Button selesai;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    File imageFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kapok_join_kapok);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, android.R.color.transparent));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

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

        animShake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.design_fab_out);
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
            View layout = inflater.inflate(R.layout.kapok_popup_upload_image, (ViewGroup) findViewById(R.id.popup_element));
            pwindo = new PopupWindow(layout, 300, 200, true);
            pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);



            //ambil foto
           Button ambil = (Button) layout.findViewById(R.id.ambil);
            ambil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    imageFile = new File(Environment
                            .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                            "test.jpg");
                    Uri tempuri = Uri.fromFile(imageFile);
                    intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, tempuri);
                    intentCamera.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                    startActivityForResult(intentCamera, 0);
                }
            });
           Button pilih = (Button) layout.findViewById(R.id.pilih);
            pilih.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Create the Intent for Image Gallery.
                    Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    // Start new activity with the LOAD_IMAGE_RESULTS to handle back the results when image is picked from the Image Gallery.
                    startActivityForResult(i, RESULT_LOAD_IMAGE);
                }
            });

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



    //ambilgallery
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.foto);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }


    }
}
