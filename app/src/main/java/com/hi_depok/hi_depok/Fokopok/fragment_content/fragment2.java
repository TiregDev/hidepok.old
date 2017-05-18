package com.hi_depok.hi_depok.Fokopok.fragment_content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.hi_depok.hi_depok.Akses;
import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by User on 19/03/17.
 */

public class fragment2 extends Fragment {
    String JSON_URL;
    RecyclerView rView;
    List<DataModel> dataAdapter;
    DataModel data;
    RecyclerView.Adapter rViewAdapter;
    JsonArrayRequest req;
    SessionManager session;

    public static fragment2 newInstance() {
        Bundle args = new Bundle();

        fragment2 fragment = new fragment2();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fokopok_fragment2_content, null);
        rView = (RecyclerView) v.findViewById(R.id.recycler_view);
        rView.setLayoutManager(new LinearLayoutManager(getContext()));
        dataAdapter = new ArrayList<>();
        JSON_URL = "http://hidepok.id/android/fokopok/fokopok_artikel.php";
        session = new SessionManager(getContext());
        HashMap<String, String> user = session.getUserDetails();
        final String id_user_suka = user.get(SessionManager.KEY_ID_USER);
        req = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    data = new DataModel();
                    JSONObject json = null;
                    try {
                        json = response.getJSONObject(i);
                        String code = json.getString("code");
                        if (code.equals("available")) {
                            data.setId(json.getString("id_artikel"));
                            data.setNama(json.getString("nama_komunitas"));
                            data.setAvatar(json.getString("foto_komunitas"));
                            data.setFoto(json.getString("foto_artikel"));
                            data.setWaktu(json.getString("waktu_artikel"));
                            data.setJudul(json.getString("judul_artikel"));
                            data.setSuka(json.getString("suka"));
                            data.setKomentar(json.getString("komentar"));
                            /*if (!json.getString("user_like").equals("null")) {
                                String[] array = json.getString("user_like").split(", ");
                                for (int j = 0; j < array.length; j++) {
                                    if (id_user_suka.equals(array[j])) {
                                        data.setHasil("sudah");
                                    } else {
                                        data.setHasil("belum");
                                    }
                                }
                            } else {
                                data.setHasil("belum");
                            }*/
                        } else
                            Toast.makeText(getContext(), json.getString("message"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    dataAdapter.add(data);
                }
                rViewAdapter = new ArtikelAdapter(dataAdapter, getContext());
                rView.setAdapter(rViewAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Akses.getInstance(getContext()).addtoRequestQueue(req);
        return v;
    }
}

