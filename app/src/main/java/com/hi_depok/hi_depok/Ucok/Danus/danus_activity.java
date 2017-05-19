package com.hi_depok.hi_depok.Ucok.Danus;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hi_depok.hi_depok.Activity_Main.BaseActivity;
import com.hi_depok.hi_depok.Activity_Main.MainActivity;
import com.hi_depok.hi_depok.Activity_Main.login;
import com.hi_depok.hi_depok.Akses;
import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class danus_activity extends BaseActivity {
    ProgressDialog dialog;
    private LinearLayoutManager lLayout;
    String JSON_URL;
    RecyclerView rView;
    List<DataModel> dataAdapter;
    DataModel data;
    SessionManager session;

    EditText formMessage1, formMessage2;
    String login_url = "http://hidepok.id/android/ucok/ucok_dss.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ucok_danus_activity);
        super.onCreateDrawer();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lLayout = new LinearLayoutManager(getBaseContext());
        rView = (RecyclerView)findViewById(R.id.list_usaha);
        rView.setLayoutManager(lLayout);
        rView.setHasFixedSize(true);
        dataAdapter = new ArrayList<>();
        session = new SessionManager(this);
        JSON_URL = "http://hidepok.id/android/ucok/ucok_riwayat_usaha.php?id_user=" + session.getUserDetails().get(SessionManager.KEY_ID_USER);
        getDataFromJSON(JSON_URL);

    }


    public void getDataFromJSON(String url){
        JsonArrayRequest req = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++){
                    data = new DataModel();
                    JSONObject json = null;
                    try{

                        json = response.getJSONObject(i);
                        data.setId(json.getString("id_event"));
                        data.setNama(json.getString("nama_event"));
                        data.setDeskripsi(json.getString("desc_event"));

                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                    dataAdapter.add(data);
                }
                RecyclerView.Adapter rViewAdapter = new DanusAdapter(dataAdapter, getBaseContext());
                rView.setAdapter(rViewAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue reqQueue = Volley.newRequestQueue(this);
        reqQueue.add(req);
    }



//    private void progresscalculate(){
//        dialog = new ProgressDialog(danus_activity.this);
//        dialog.show();
//        dialog.setMessage("Progress Kalkulasi");
//    }

    private void showMessageDialog (){
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout= inflater.inflate(R.layout.ucok_danus_messagedialog, null);
        formMessage1 = (EditText) alertLayout.findViewById(R.id.target_rupiah);
        formMessage2 = (EditText) alertLayout.findViewById(R.id.target_waktu);



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

                String urlJSON = "http://hidepok.id/android/ucok/ucok_dss.php?target_uang=" + formMessage1.getText() + "&target_hari=" + formMessage2.getText();
                Intent intent = new Intent(danus_activity.this, hasil_calculate.class);
                intent.putExtra("urlJSON", urlJSON);
                startActivity(intent);
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                danus_activity.this.finish();
                return true;
//            case R.id.action_share:
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
