package com.hi_depok.hi_depok.Sikepok_RS;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.hi_depok.hi_depok.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hadi on 13/03/2017.
 */

public class jadwal_praktek extends Fragment {

    String GET_JSON_DATA_HTTP_URL;
    String JSON_ID_DOKTER = "id_dokter";
    String JSON_NAMA_DOKTER = "nama_dokter";
    String JSON_SPESIALISASI = "spesialisasi";
    String JSON_ID_RS ="id_rs";
    String JSON_SENIN ="senin_jadwal";
    String JSON_SELASA ="selasa_jadwal";
    String JSON_RABU ="rabu_jadwal";
    String JSON_KAMIS ="kamis_jadwal";
    String JSON_JUMAT ="jumat_jadwal";
    String JSON_SABTU ="sabtu_jadwal";

    String idRs;

    JsonArrayRequest jsonArrayRequest ;
    List<GetDataAdapter> dataAdapter;
    RequestQueue requestQueue ;
    RecyclerView.Adapter recyclerViewadapter;
    RecyclerView rView;
    ProgressDialog dialog;

    //tambahan untuk fragment
    public static jadwal_praktek newInstance(String title) {
        jadwal_praktek fragment = new jadwal_praktek();

        Bundle args = new Bundle();
        args.putCharSequence("title", title);
        fragment.setArguments(args);

        return fragment;
    }

    public String getTitle() {
        Bundle args = getArguments();
        return args.getString("title", "NO TITLE FOUND");
    }
    //tambahan fragment sampai sini

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  v = inflater.inflate(R.layout.sikepokrs_jadwal_praktek, null);

        //r view db
        rView = (RecyclerView) v.findViewById(R.id.recyclerview);

        rView.setLayoutManager(new LinearLayoutManager(getContext()));

        dataAdapter = new ArrayList<>();
        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Loading");
        dialog.show();
        dialog.setCancelable(true);

        //shared preferences test
        final SharedPreferences prefsa = PreferenceManager.getDefaultSharedPreferences(getContext());
        idRs = prefsa.getString("id_rs","No data found");

        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/android/sikepok/1.2/sikepokrs_menurs_json.php?id_rs2="+ idRs;

        JSON_DATA_WEB_CALL();

        return v;
    }

    //recycler
    public void JSON_DATA_WEB_CALL(){
        jsonArrayRequest = new JsonArrayRequest(GET_JSON_DATA_HTTP_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        dialog.dismiss();
                        JSON_PARSE_DATA_AFTER_WEBCALL(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }

    public void JSON_PARSE_DATA_AFTER_WEBCALL(JSONArray array){
        for(int i = 0; i<array.length(); i++) {
            GetDataAdapter dataFromJSON = new GetDataAdapter();;
            JSONObject json = null;
            try {
                json = array.getJSONObject(i);
                dataFromJSON = new GetDataAdapter();

                dataFromJSON.setId_dokter(json.getString(JSON_ID_DOKTER));
                dataFromJSON.setNama_dokter(json.getString(JSON_NAMA_DOKTER));
                dataFromJSON.setSpesialisasi(json.getString(JSON_SPESIALISASI));
                dataFromJSON.setId_rs(json.getString(JSON_ID_RS));
                dataFromJSON.setSenin(json.getString(JSON_SENIN));
                dataFromJSON.setSelasa(json.getString(JSON_SELASA));
                dataFromJSON.setRabu(json.getString(JSON_RABU));
                dataFromJSON.setKamis(json.getString(JSON_KAMIS));
                dataFromJSON.setJumat(json.getString(JSON_JUMAT));
                dataFromJSON.setSabtu(json.getString(JSON_SABTU));

            } catch (JSONException e) {

                e.printStackTrace();
            }
            dataAdapter.add(dataFromJSON);
        }

        recyclerViewadapter = new RecyclerViewAdapterJADWAL(dataAdapter, getContext());
        rView.setAdapter(recyclerViewadapter);
    }

}
