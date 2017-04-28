package com.hi_depok.hi_depok.Sikepok_RS;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.hi_depok.hi_depok.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Hadi on 13/03/2017.
 */

public class deskripsi_rs extends Fragment {


    String GET_JSON_DATA_HTTP_URL;
    String JSON_ID_RS = "id_rs";
    String JSON_NAMA = "nama_rs";
    String JSON_DESKRIPSI = "deskripsi_rs";
    String JSON_NO_TELP = "no_telp_rs";
    String JSON_ALAMAT = "alamat_rs";
    String JSON_FOTO = "foto_rs";

    JsonArrayRequest jsonArrayRequest ;
    List<GetDataAdapter> dataAdapter;
    RequestQueue requestQueue ;
    ProgressDialog dialog;
    TextView nama, deskripsi, no_telp, alamat;
    ImageView image;

    //tambahan untuk fragment
    public static deskripsi_rs newInstance(String title) {
        deskripsi_rs fragment = new deskripsi_rs();

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
        View  v = inflater.inflate(R.layout.sikepokrs_deskripsi_rs, null);

        //inisialisasi adapter
        dataAdapter = new ArrayList<>();

        //inisialisasi tampilan di xml
        nama = (TextView) v.findViewById(R.id.namaRS);
        deskripsi = (TextView) v.findViewById(R.id.descRS);
        no_telp = (TextView) v.findViewById(R.id.telpRS);
        alamat = (TextView) v.findViewById(R.id.alamatRS);
        image = (ImageView) v.findViewById(R.id.imageRS);
        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Loading");
        dialog.show();
        dialog.setCancelable(true);

        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/include/sikepokrs_menurs_json.php?id_rs="+ getTitle();

        JSON_DATA_WEB_CALL();

//        menu_rs activity = (menu_rs) getActivity();
//        String myDataFromActivity = activity.getMyData();

//        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/include/sikepokrs_menurs_json.php?nama_rs=" + myDataFromActivity;

        return v;
    }

    //parsing JSON
    public void JSON_DATA_WEB_CALL(){
        jsonArrayRequest = new JsonArrayRequest(GET_JSON_DATA_HTTP_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.v("JsonResponse", "succses");
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
            JSONObject json = null;
            try {
                json = array.getJSONObject(i);
//                dataFromJSON.setNama(json.getString(JSON_NAMA));
//                dataFromJSON.setDesc(json.getString(JSON_DESKRIPSI));
//                dataFromJSON.setTelp(json.getString(JSON_NO_TELP));
//                dataFromJSON.setAlamat(json.getString(JSON_ALAMAT));
//                dataFromJSON.setFoto(R.drawable.rs_mitra_keluarga);

                nama.setText(json.getString(JSON_NAMA));
                deskripsi.setText(json.getString(JSON_DESKRIPSI));
                no_telp.setText(json.getString(JSON_NO_TELP));
                alamat.setText(json.getString(JSON_ALAMAT));

                String encodeUrl = URLEncoder.encode(json.getString(JSON_FOTO));

                String urlPhoto = "http://hidepok.id/assets/images/photos/sikepok/sikepok2/" + encodeUrl;

                Picasso.with(getContext()).load(urlPhoto).into(image);

            } catch (JSONException e) {

                e.printStackTrace();
            }
        }


    }
}

