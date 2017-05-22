package com.hi_depok.hi_depok.Kadepok.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hi_depok.hi_depok.Akses;
import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Muhammad63 on 3/16/2017.
 */

public class fragment2 extends DialogFragment {

    private File imageFile;
    String filename;
    public Button donate, bukti;
    private PopupWindow popupWindow;
    public Button close;
    public TextView txtImage;
    TextView jumlahDonasi, namaDonatur, telpDonatur;
    String url;
    SessionManager session;
    View v;

    public static fragment2 newInstance(String id) {

        fragment2 fragment = new fragment2();
        Bundle args = new Bundle();

        args.putString("id", id);
        fragment.setArguments(args);
        return fragment;
    }

    public String getID() {
        Bundle args = getArguments();
        return args.getString("id", "0");
    }

    public void verify_volunteer() {
        try {

            LayoutInflater layoutInflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = layoutInflater.inflate(R.layout.kadepok_donasi_popup, (ViewGroup)v.findViewById(R.id.rl_custom_layout));

            popupWindow = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
            popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);

            close = (Button)layout.findViewById(R.id.close);
            close.setOnClickListener(cancel_button_click_listener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View.OnClickListener cancel_button_click_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            popupWindow.dismiss();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.kadepok_fragment2_kadepok_content, null);
//        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        session = new SessionManager(getContext());
        donate = (Button)v.findViewById(R.id.donate);
        bukti = (Button)v.findViewById(R.id.bukti);
        txtImage = (TextView)v.findViewById(R.id.txtBukti);

        jumlahDonasi = (TextView)v.findViewById(R.id.donasi);
        namaDonatur = (TextView)v.findViewById(R.id.nama_donatur);
        telpDonatur = (TextView)v.findViewById(R.id.tlp_donatur);
        url = "http://hidepok.id/android/kadepok/kadepok_donasi_json.php";
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String donasi = jumlahDonasi.getText().toString();
                final String donatur = namaDonatur.getText().toString();
                final String telp = telpDonatur.getText().toString();
                final String txtImg = txtImage.getText().toString();

                if (donasi.equals("")) {
                    jumlahDonasi.setError("Jumlah harus diisi");
                } else if (donatur.equals("")) {
                    namaDonatur.setError("Nama harus diiisi");
                } else if (telp.equals("")) {
                    namaDonatur.setError("Telp harus diiisi");
                } else{
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONArray jsonArray = new JSONArray(response);
                                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                                        Toast.makeText(getContext(), jsonObject.getString("message"),
                                                    Toast.LENGTH_SHORT).show();
                                        verify_volunteer();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getContext(), "Koneksi Gagal! Cek Koneksi Anda!", Toast.LENGTH_SHORT).show();
                            error.printStackTrace();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> params = new HashMap<String, String>();
                            HashMap<String, String> user = session.getUserDetails();
                            params.put("nominal_donasi", donasi);
                            params.put("nama_donasi", donatur);
                            params.put("telepon_donasi", telp);
                            params.put("id_panti_donasi", getID());
                            params.put("id_user_donasi", user.get(SessionManager.KEY_ID_USER));
                            params.put("bukti_transfer_donasi", txtImg);
                            return params;
                        }
                    };
                    Akses.getInstance(getContext()).addtoRequestQueue(stringRequest);
                }

            }
        });

        bukti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                filename = "image_" + new Date().getTime() + ".jpg";

                imageFile = new File(Environment.getExternalStorageDirectory()
                        + "/DCIM/", filename);
                Uri tempuri = Uri.fromFile(imageFile);
                camera.putExtra(MediaStore.EXTRA_OUTPUT, tempuri);
                camera.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                startActivityForResult(camera, 0);
            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 0) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    if (imageFile.exists()) {
                        Toast.makeText(getActivity(), "The file was save at " + imageFile.getAbsolutePath(),
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "There was an error ", Toast.LENGTH_LONG).show();
                    }
                    txtImage.setText(filename);
                    super.onActivityResult(requestCode, resultCode, data);
                    Intent picIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    getActivity().startActivityForResult(picIntent,111);
//                    Intent deskripsi = new Intent(getActivity(), kadepok_content.class);
//                    startActivity(deskripsi);
                    break;
                case Activity.RESULT_CANCELED:
                    break;
                default:
                    break;
            }
        }
    }
}
