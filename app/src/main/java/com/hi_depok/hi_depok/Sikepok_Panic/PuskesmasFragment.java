package com.hi_depok.hi_depok.Sikepok_Panic;


import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import com.hi_depok.hi_depok.R;

public class PuskesmasFragment extends Fragment {
    private LinearLayoutManager lLayout;
    String GET_JSON_DATA_HTTP_URL = "http://hidepok.id/include/jsonData.php?kategori=puskesmas";
    String JSON_ID = "id_tempat";
    String JSON_ALAMAT = "alamat_tempat";
    String JSON_NAME = "nama_tempat";
    String JSON_DESKRIPSI = "deskripsi_tempat";
    String JSON_NOTLP = "no_telp_tempat";
    JsonArrayRequest jsonArrayRequest ;
    List<GetDataAdapter> dataAdapter;
    RequestQueue requestQueue ;
    RecyclerView.Adapter recyclerViewadapter;
    ProgressDialog progressDialog;
    RecyclerView rView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        lLayout = new LinearLayoutManager(getContext());
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);
        dataAdapter = new ArrayList<>();
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Sedang Mencari ...");
        progressDialog.show();
        JSON_DATA_WEB_CALL();
        return rView;
    }

    public void JSON_DATA_WEB_CALL(){
        jsonArrayRequest = new JsonArrayRequest(GET_JSON_DATA_HTTP_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        progressDialog.dismiss();
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
            GetDataAdapter dataFromJSON = new GetDataAdapter();
            JSONObject json = null;
            try {
                json = array.getJSONObject(i);
                dataFromJSON.setName(json.getString(JSON_NAME));
                dataFromJSON.setDeskripsi(json.getString(JSON_DESKRIPSI));
                dataFromJSON.setFoto(R.drawable.a_avator);

            } catch (JSONException e) {

                e.printStackTrace();
            }
            dataAdapter.add(dataFromJSON);
        }

        recyclerViewadapter = new RecyclerViewAdapterJSON(dataAdapter, getContext());
        rView.setAdapter(recyclerViewadapter);
    }
}


