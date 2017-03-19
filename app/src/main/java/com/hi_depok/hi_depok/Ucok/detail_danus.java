package com.hi_depok.hi_depok.Ucok;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.view.View.OnClickListener;

import com.hi_depok.hi_depok.Activity_Main.MainActivity;
import com.hi_depok.hi_depok.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class detail_danus extends AppCompatActivity {
    Date date = Calendar.getInstance().getTime();
    DateFormat formatdate = new SimpleDateFormat("dd/MM/yyyy");
    TextView dateAndTimeLabel;
    Calendar dateAndTime = Calendar.getInstance();
    Button mulai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ucok_detail_danus);

    }

    public void link_hasil_calculate(View v){
        Intent intent = new Intent(detail_danus.this, hasil_calculate.class);
        startActivity(intent);
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
        alert.setCancelable(false);
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
    }
    public void link_addusaha (View v){
        showMessageDialog_danus();
    }

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

}
