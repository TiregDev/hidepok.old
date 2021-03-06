package com.hi_depok.hi_depok.Sikepok_RS;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

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

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Hadi on 13/03/2017.
 */

public class diskusi_terbaru extends Fragment {

    String GET_JSON_DATA_HTTP_URL;
    String JSON_ID_POST = "id_post";
    String JSON_ID_MODUL = "id_modul";
    String JSON_ID_USER = "id_user";
    String JSON_JUDUL_POST = "judul_post";
    String JSON_ISI_POST = "isi_post";
    String JSON_TANGGAL_POST = "tanggal_post";
    String JSON_WAKTU_POST = "waktu_post";
    String JSON_KATEGORI_POST = "kategori_post";
    String JSON_FOTO_POST = "foto_post";
    String JSON_DESKRIPSI_POST = "deskripsi_post";
    String JSON_LOKASI_POST = "lokasi_post";
    String JSON_NO_IDENTITAS_POST = "no_identitas_post";
    String JSON_STATUS_POST = "status_post";
    String JSON_RATING_POST = "rating_post";
    String JSON_NAMA_USER = "nama_user";
    String JSON_ANGKA_KOMEN = "total_komentar";
    String JSON_ANGKA_SUKA = "total_suka";

    JsonArrayRequest jsonArrayRequest ;
    List<GetDataAdapter> dataAdapter;
    RequestQueue requestQueue ;
    RecyclerView.Adapter recyclerViewadapter;
    RecyclerView rView;
    SearchView searchView;
    ProgressDialog dialog;
//    String dicari;

    //tambahan untuk fragment
    public static diskusi_terbaru newInstance(String title, String cariin) {
        diskusi_terbaru fragment = new diskusi_terbaru();

        Bundle args = new Bundle();
        args.putCharSequence("title", title);
        args.putCharSequence("cariin", cariin);
        fragment.setArguments(args);

        return fragment;
    }

    public String getTitle() {
        Bundle args = getArguments();
        return args.getString("title", "NO TITLE FOUND");
    }

    public String getCari() {
        Bundle args = getArguments();
        return args.getString("cariin", "");
    }
    //tambahan fragment sampai sini

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  v = inflater.inflate(R.layout.sikepokrs_diskusi_terbaru, null);


//        //r view db
        rView = (RecyclerView) v.findViewById(R.id.recyclerviewpost);

        rView.setLayoutManager(new LinearLayoutManager(getContext()));

        dataAdapter = new ArrayList<>();
        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Loading");
        dialog.show();
        dialog.setCancelable(true);

        //shared preferences test
//        final SharedPreferences prefsa = PreferenceManager.getDefaultSharedPreferences(getContext());
//        idRs = prefsa.getString("id_rs","No data found");

//        //preference search dari forum
//        final SharedPreferences prefsa = PreferenceManager.getDefaultSharedPreferences(getContext());
//        dicari = prefsa.getString("dicari_diforum","");

        if (!getCari().equals("")) {
            GET_JSON_DATA_HTTP_URL = "http://hidepok.id/android/sikepok/1.2/sikepokrs_forum_json.php?cari=" + getCari();
        } else {
            GET_JSON_DATA_HTTP_URL = "http://hidepok.id/android/sikepok/1.2/sikepokrs_forum_json.php";
        }
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

                dataFromJSON.setId_post(json.getString(JSON_ID_POST));
//                dataFromJSON.setId_modul(json.getString(JSON_ID_MODUL));
                dataFromJSON.setId_user(json.getString(JSON_ID_USER));
                dataFromJSON.setJudul_post(json.getString(JSON_JUDUL_POST));
                dataFromJSON.setIsi_post(json.getString(JSON_ISI_POST));
                dataFromJSON.setTanggal_post(json.getString(JSON_TANGGAL_POST));
                dataFromJSON.setWaktu_post(json.getString(JSON_WAKTU_POST));
                dataFromJSON.setKategori_post(json.getString(JSON_KATEGORI_POST));
//                dataFromJSON.setFoto_post(json.getString(JSON_FOTO_POST));
//                dataFromJSON.setDeskripsi_post(json.getString(JSON_DESKRIPSI_POST));
//                dataFromJSON.setLokasi_post(json.getString(JSON_LOKASI_POST));
//                dataFromJSON.setNo_identitas_post(json.getString(JSON_NO_IDENTITAS_POST));
//                dataFromJSON.setStatus_post(json.getString(JSON_STATUS_POST));
//                dataFromJSON.setRating_post(json.getString(JSON_RATING_POST));
                dataFromJSON.setNama_user(json.getString(JSON_NAMA_USER));
                dataFromJSON.setAngka_komen(json.getString(JSON_ANGKA_KOMEN));
                dataFromJSON.setAngka_suka(json.getString(JSON_ANGKA_SUKA));

            } catch (JSONException e) {

                e.printStackTrace();
            }
            dataAdapter.add(dataFromJSON);
        }

        recyclerViewadapter = new RecyclerViewAdapterPOST(dataAdapter, getContext());
        rView.setAdapter(recyclerViewadapter);
    }

    //Search
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);

        if (searchItem != null) {
            searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
            searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                @Override
                public boolean onClose() {
                    //some operation
                    dataAdapter.clear();
                    GET_JSON_DATA_HTTP_URL = "http://hidepok.id/android/sikepok/1.2/sikepokrs_forum_json.php";
                    JSON_DATA_WEB_CALL();
                    return false;
                }
            });
            searchView.setOnSearchClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //some operation
                }
            });
            EditText searchPlate = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
            searchPlate.setHint("Search");
            View searchPlateView = searchView.findViewById(android.support.v7.appcompat.R.id.search_plate);
            searchPlateView.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
            // use this method for search process
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    // use this method when query submitted
                    dataAdapter.clear();
                    GET_JSON_DATA_HTTP_URL = "http://hidepok.id/android/sikepok/1.2/sikepokrs_forum_json.php?cari=" + query;
                    JSON_DATA_WEB_CALL();
                    Toast.makeText(getContext(), "Hasil pencarian untuk: " + query, Toast.LENGTH_SHORT).show();
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    // use this method for auto complete search process
                    dataAdapter.clear();
                    GET_JSON_DATA_HTTP_URL = "http://hidepok.id/android/sikepok/1.2/sikepokrs_forum_json.php?cari=" + newText;
                    JSON_DATA_WEB_CALL();
                    return false;
                }
            });
            SearchManager searchManager = (SearchManager)getActivity().getSystemService(Context.SEARCH_SERVICE);
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        dataAdapter.clear();
        JSON_DATA_WEB_CALL();
    }

}