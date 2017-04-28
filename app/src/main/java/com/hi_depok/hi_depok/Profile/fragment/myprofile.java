package com.hi_depok.hi_depok.Profile.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.Akses;
import com.hi_depok.hi_depok.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Muhammad63 on 3/4/2017.
 */

public class myprofile extends Fragment {
    private TextView nama, tgl_lahir, tgl_join, email, no_telp, alamat;
    String detail_url = "http://hidepok.id/getUserDetail.php";
    SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("d MMM yyyy");
    Date gabung, kelahiran;

    public static myprofile newInstance() {

        Bundle args = new Bundle();

        myprofile fragment = new myprofile();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.profile_fragment1_myprofile, container, false);
        nama = (TextView) v.findViewById(R.id.namauser);
        tgl_lahir = (TextView) v.findViewById(R.id.ttl);
        tgl_join = (TextView) v.findViewById(R.id.join);
        email = (TextView) v.findViewById(R.id.emailuser);
        no_telp = (TextView) v.findViewById(R.id.notelpuser);
        alamat = (TextView) v.findViewById(R.id.alamatuser);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, detail_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);

                            //ambil nilai dari db

                            nama.setText(jsonObject.getString("nama"));
                            email.setText(jsonObject.getString("email"));

                            gabung = df1.parse(jsonObject.getString("gabung"));
                            tgl_join.setText("Bergabung dengan Hi-Depok sejak " +
                                    mSimpleDateFormat.format(gabung));

                            if(!jsonObject.getString("kelahiran").equals("null") &&
                                    !jsonObject.getString("tempat_kelahiran").equals("null")){
                                kelahiran = df1.parse(jsonObject.getString("kelahiran"));
                                tgl_lahir.setText(jsonObject.getString("tempat_kelahiran") + ", "
                                        + mSimpleDateFormat.format(kelahiran));
                            }else{
                                tgl_lahir.setText("Tempat dan tanggal lahir kamu");
                            }

                            if(!jsonObject.getString("no_telp").equals("null")){
                                no_telp.setText(jsonObject.getString("no_telp"));
                            } else{
                                no_telp.setText("Nomor telepon kamu");
                            }

                            if(!jsonObject.getString("alamat").equals("null")){
                                alamat.setText(jsonObject.getString("alamat"));
                            }else{
                                alamat.setText("Alamat rumah kamu");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "An error occured" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SessionManager session = new SessionManager(getContext());
                //session username untuk diambil dan dimasukkan ke dalam skrip php
                HashMap<String, String> user = session.getUserDetails();
                String username_session = user.get(SessionManager.KEY_USERNAME);
                //username ada di skrip getUserDetail.php dengan paramater name
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username_session);
                return params;
            }
        };
        Akses.getInstance(getContext()).addtoRequestQueue(stringRequest);

        return v;
    }
}
