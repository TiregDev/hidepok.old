package com.hi_depok.hi_depok.Sikepok_Panic.fragment;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.Sikepok_Panic.adapter.GetDataAdapter;
import com.hi_depok.hi_depok.Sikepok_Panic.adapter.JSONAdapter;

public class TempatSehatFragment extends Fragment {
    private LinearLayoutManager lLayout;
    String GET_JSON_DATA_HTTP_URL;
    String JSON_ID = "id_tempat_sehat";
    String JSON_ALAMAT = "alamat_tempat_sehat";
    String JSON_JENIS = "nama_jenis";
    String JSON_KORDINAT = "koordinat_tempat_sehat";
    String JSON_KECAMATAN = "kecamatan_tempat_sehat";
    String JSON_NAME = "nama_tempat_sehat";
    String JSON_FOTO = "foto_tempat_sehat";
    String JSON_KETERANGAN = "keterangan_tempat_sehat";
    String JSON_OPERASIONAL = "operasional_tempat_sehat";
    String JSON_NOTLP = "no_telp_tempat_sehat";
    JsonArrayRequest jsonArrayRequest, distanceReq;
    List<GetDataAdapter> dataAdapter;
    RequestQueue requestQueue, req;
    RecyclerView.Adapter recyclerViewadapter;
    RecyclerView rView;
    StringBuilder stringBuilder;

    public static TempatSehatFragment newInstance(String title, String location) {
        TempatSehatFragment fragment = new TempatSehatFragment();

        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("location", location);
        fragment.setArguments(args);

        return fragment;
    }

    public String getTitle() {
        Bundle args = getArguments();
        return args.getString("title", "NO TITLE FOUND");
    }

    public String getLocation() {
        Bundle args = getArguments();
        return args.getString("location", "0.0");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);

        lLayout = new LinearLayoutManager(getContext());
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);
        dataAdapter = new ArrayList<>();
        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/include/sikepokpanic_json.php?kategori=" + getTitle();

        return rView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        JSON_DATA_WEB_CALL();
        super.onActivityCreated(savedInstanceState);
    }


    public void JSON_DATA_WEB_CALL() {
        jsonArrayRequest = new JsonArrayRequest(GET_JSON_DATA_HTTP_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
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

    public void JSON_PARSE_DATA_AFTER_WEBCALL(JSONArray array) {
        for (int i = 0; i < array.length(); i++) {
            GetDataAdapter dataFromJSON = new GetDataAdapter();
            JSONObject json = null;
            try {
                json = array.getJSONObject(i);
                dataFromJSON.setId(json.getString(JSON_ID));
                dataFromJSON.setName(json.getString(JSON_NAME));
                dataFromJSON.setDeskripsi(json.getString(JSON_KETERANGAN));
                dataFromJSON.setAlamat(json.getString(JSON_ALAMAT));
                dataFromJSON.setNoTelp(json.getString(JSON_NOTLP));
                dataFromJSON.setFoto(json.getString(JSON_FOTO));
                dataFromJSON.setKecamatan(json.getString(JSON_KECAMATAN));
                dataFromJSON.setKordinat(json.getString(JSON_KORDINAT));
                dataFromJSON.setJenis(json.getString(JSON_JENIS));
                dataFromJSON.setOperasional(json.getString(JSON_OPERASIONAL));

                String[] kordinatA = json.getString(JSON_KORDINAT).split(",");
                double latitudeA = Double.parseDouble(kordinatA[0]);
                double longitudeA = Double.parseDouble(kordinatA[1].trim());

                String[] kordinatB = getLocation().split(",");
                double latitudeB = Double.parseDouble(kordinatB[0]);
                double longitudeB = Double.parseDouble(kordinatB[1].trim());

                dataFromJSON.setJarak("5 km");

            } catch (JSONException e) {

                e.printStackTrace();
            }
            dataAdapter.add(dataFromJSON);
        }
        recyclerViewadapter = new JSONAdapter(dataAdapter, getContext());
        rView.setAdapter(recyclerViewadapter);
    }

//    public double getDistanceInfo(double latA, double longA, double latB, double longB) {
//        stringBuilder = new StringBuilder();
//        double dist = 0.0;
//        String url = "http://maps.googleapis.com/maps/api/directions/json?origin=" + latA + "," + longA + "&destination=" + latB + "," + longB + "&mode=driving&sensor=false";
//        distanceReq = new JsonArrayRequest()
//        JSONObject jsonObject = null;
//        try {
//
//            jsonObject = new JSONObject(stringBuilder.toString());
//
//            JSONArray array = jsonObject.getJSONArray("routes");
//
//            JSONObject routes = array.getJSONObject(0);
//
//            JSONArray legs = routes.getJSONArray("legs");
//
//            JSONObject steps = legs.getJSONObject(0);
//
//            JSONObject distance = steps.getJSONObject("distance");
//
//            Log.i("Distance", distance.toString());
//
//            dist = Double.parseDouble(distance.getString("text").replaceAll("[^\\.0123456789]","") );
//
//        } catch (JSONException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        return dist;
//    }
}


