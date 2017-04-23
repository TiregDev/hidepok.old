package com.hi_depok.hi_depok.Sikepok_Panic.fragment;

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
    JsonArrayRequest jsonArrayRequest ;
    List<GetDataAdapter> dataAdapter;
    RequestQueue requestQueue ;
    RecyclerView.Adapter recyclerViewadapter;
    RecyclerView rView;

    public static TempatSehatFragment newInstance(String title) {
        TempatSehatFragment fragment = new TempatSehatFragment();

        Bundle args = new Bundle();
        args.putCharSequence("title", title);
        fragment.setArguments(args);

        return fragment;
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
        JSON_DATA_WEB_CALL();
        return rView;
    }
    public String getTitle() {
        Bundle args = getArguments();
        return args.getString("title", "NO TITLE FOUND");
    }
    public void JSON_DATA_WEB_CALL(){
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

    public void JSON_PARSE_DATA_AFTER_WEBCALL(JSONArray array){
        for(int i = 0; i<array.length(); i++) {
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

            } catch (JSONException e) {

                e.printStackTrace();
            }
            dataAdapter.add(dataFromJSON);
        }
        recyclerViewadapter = new JSONAdapter(dataAdapter, getContext());
        rView.setAdapter(recyclerViewadapter);
    }
}


