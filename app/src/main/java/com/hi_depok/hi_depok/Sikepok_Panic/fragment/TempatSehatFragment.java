package com.hi_depok.hi_depok.Sikepok_Panic.fragment;


import android.app.SearchManager;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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
import android.widget.Toast;;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.Sikepok_Panic.util.Utils;
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
    JsonArrayRequest jsonArrayRequest, distanceRequest;
    List<GetDataAdapter> dataAdapter;
    RequestQueue requestQueue;
    RecyclerView.Adapter recyclerViewadapter;
    RecyclerView rView;
    SearchView searchView;
    Utils util = new Utils();

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(new LinearLayoutManager(getContext()));
        dataAdapter = new ArrayList<>();

        return rView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/android/sikepok/1.3/sikepokpanic_json.php?kategori=" + getTitle();
        JSON_DATA_WEB_CALL(GET_JSON_DATA_HTTP_URL);
        super.onActivityCreated(savedInstanceState);
    }

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
                    GET_JSON_DATA_HTTP_URL = "http://hidepok.id/android/sikepok/1.3/sikepokpanic_json.php?kategori=" + getTitle();
                    JSON_DATA_WEB_CALL(GET_JSON_DATA_HTTP_URL);
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
                    GET_JSON_DATA_HTTP_URL = "http://hidepok.id/android/sikepok/1.3/sikepokpanic_json.php?cari=" + query + "&kategori=" + getTitle();
                    JSON_DATA_WEB_CALL(GET_JSON_DATA_HTTP_URL);
                    Toast.makeText(getContext(), "Hasil pencarian untuk: " + query, Toast.LENGTH_SHORT).show();
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    // use this method for auto complete search process
                    dataAdapter.clear();
                    GET_JSON_DATA_HTTP_URL = "http://hidepok.id/android/sikepok/1.3/sikepokpanic_json.php?cari=" + newText + "&kategori=" + getTitle();
                    JSON_DATA_WEB_CALL(GET_JSON_DATA_HTTP_URL);
                    return false;
                }
            });
            SearchManager searchManager = (SearchManager)getActivity().getSystemService(Context.SEARCH_SERVICE);
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

        }
    }


    public void JSON_DATA_WEB_CALL(String url) {
        jsonArrayRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            GetDataAdapter dataFromJSON = new GetDataAdapter();
                            JSONObject json = null;
                            try {
                                json = response.getJSONObject(i);
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

                                Double jarak = BigDecimal.valueOf(util.CalculationByDistance(latitudeB, longitudeB, latitudeA, longitudeA))
                                        .setScale(3, RoundingMode.HALF_UP)
                                        .doubleValue();
                                dataFromJSON.setJarak(String.valueOf(jarak));

                            } catch (JSONException e) {

                                e.printStackTrace();
                            }
                            dataAdapter.add(dataFromJSON);
                        }

                        Collections.sort(dataAdapter, new Comparator<GetDataAdapter>() {
                            @Override
                            public int compare(GetDataAdapter data1, GetDataAdapter data2) {
                                Double f1 = Double.parseDouble(data1.getJarak());
                                Double f2 = Double.parseDouble(data2.getJarak());
                                return f1.compareTo(f2);
                            }
                        });
                        recyclerViewadapter = new JSONAdapter(dataAdapter, getContext());
                        rView.setAdapter(recyclerViewadapter);
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

}


