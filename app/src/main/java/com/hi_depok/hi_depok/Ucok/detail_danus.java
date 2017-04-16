package com.hi_depok.hi_depok.Ucok;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import android.widget.TimePicker;
import android.widget.Toast;
import android.view.View.OnClickListener;

import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.Activity_Main.MainActivity;
import com.hi_depok.hi_depok.Kadepok.kadepok_content;
import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.Sikepok_Panic.MapsActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class detail_danus extends BaseActivity {
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

    private void settingTanggal(){
        new DatePickerDialog(detail_danus.this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
    }
    public void toMaps(View v){
        Intent intent = new Intent(getBaseContext(), MapsActivity.class);
        startActivity(intent);
    }

}
