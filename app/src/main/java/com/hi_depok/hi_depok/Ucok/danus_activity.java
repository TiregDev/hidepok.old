package com.hi_depok.hi_depok.Ucok;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.Activity_Main.ucok;
import com.hi_depok.hi_depok.R;

import java.util.ArrayList;
import java.util.List;

public class danus_activity extends BaseActivity {
    ProgressDialog dialog;
    private LinearLayoutManager lLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ucok_danus_activity);
        super.onCreateDrawer();
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        List<itemObject_listusaha> rowListItem = getAllItemList();
        lLayout = new LinearLayoutManager(getBaseContext());

        RecyclerView rView = (RecyclerView)findViewById(R.id.list_usaha);
        rView.setLayoutManager(lLayout);

        RecyclerViewAdapter_listusaha rcAdapter = new RecyclerViewAdapter_listusaha(getBaseContext(), rowListItem);
        rView.setAdapter(rcAdapter);
    }

    public List<itemObject_listusaha> getAllItemList() {
        List<itemObject_listusaha> allItems = new ArrayList<>();
        allItems.add(new itemObject_listusaha("ITechno Cup", R.string.item_desc));
        allItems.add(new itemObject_listusaha("Studi Banding", R.string.item_desc));
        allItems.add(new itemObject_listusaha("Kabir", R.string.item_desc));
        return allItems;
    }

    private void progresscalculate(){
        dialog = new ProgressDialog(danus_activity.this);
        dialog.show();
        dialog.setMessage("Progress Kalkulasi");
    }

    private void showMessageDialog (){
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout= inflater.inflate(R.layout.ucok_danus_messagedialog, null);
        final EditText formMessage1 = (EditText) alertLayout.findViewById(R.id.target_rupiah);
        final EditText formMessage2 = (EditText) alertLayout.findViewById(R.id.target_waktu);

        AlertDialog.Builder alert = new AlertDialog.Builder(danus_activity.this);
        alert.setTitle("Silahkan Masukkan");
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

        alert.setPositiveButton("Kalkulasi", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
                progresscalculate();
                Intent intent = new Intent(danus_activity.this, hasil_calculate.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
        Button buttonPositive = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        buttonPositive.setTextColor(ContextCompat.getColor(this, R.color.abuAbu));
        Button buttonNegative = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        buttonNegative.setTextColor(ContextCompat.getColor(this, R.color.abuAbu));
    }

    public void danus_calculate(View v){
        showMessageDialog();
    }
}
