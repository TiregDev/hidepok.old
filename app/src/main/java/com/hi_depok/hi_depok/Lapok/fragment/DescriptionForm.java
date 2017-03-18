package com.hi_depok.hi_depok.Lapok.fragment;

/**
 * Created by Muhammad63 on 3/18/2017.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hi_depok.hi_depok.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.util.Calendar;


public class DescriptionForm extends AppCompatActivity {

    EditText entryJudul, entryDeskripsi;
    TextView perubahanWaktuLabel, coba;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    Calendar dateAndTime = Calendar.getInstance();
    DateFormat fmtDateandTime =
            DateFormat.getDateTimeInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lapok_deskripsi);

        entryJudul = (EditText) findViewById(R.id.entry);
        entryDeskripsi = (EditText) findViewById(R.id.entryDeskripsi);
        coba = (TextView) findViewById(R.id.labelCoba1);
        coba.setVisibility(View.GONE);

        spinner = (Spinner) findViewById(R.id.spinKategori);
        adapter = ArrayAdapter.createFromResource(this, R.array.places,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getBaseContext(), adapterView.getItemAtPosition(position)
                        + " selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        perubahanWaktuLabel = (TextView) findViewById(R.id.labelPerubahanWaktu);
        updateLabel();
    }

    private void updateLabel(){
        perubahanWaktuLabel.setText(
                fmtDateandTime.format(dateAndTime.getTime())
        );
    }

    public void writeMessage(View view){
        String Message = entryJudul.getText().toString();
        String filename = "hello_file";
        try {
            FileOutputStream fileOutputStream = openFileOutput(filename, MODE_PRIVATE);
            fileOutputStream.write(Message.getBytes());
            fileOutputStream.close();
            Toast.makeText(getApplicationContext(), "Pesan Disimpan", Toast.LENGTH_LONG).show();
            entryJudul.setText("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readMessage(View view){
        try {
            String Message;
            FileInputStream fileInputStream = openFileInput("hello_file");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            while ((Message = bufferedReader.readLine()) != null){
                stringBuffer.append(Message + "\n");
                coba.setText(stringBuffer.toString());
                coba.setVisibility(View.VISIBLE);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
