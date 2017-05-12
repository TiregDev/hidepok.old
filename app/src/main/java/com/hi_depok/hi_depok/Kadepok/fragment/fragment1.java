package com.hi_depok.hi_depok.Kadepok.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.Sikepok_Diagnosa.DataModel;
import com.hi_depok.hi_depok.Sikepok_Diagnosa.EnsiklopediaAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Muhammad63 on 3/16/2017.
 */

public class fragment1 extends Fragment {
    ImageView image_slide;
    LinearLayout gambar;
    String URL;
    TextView pengurus, noTelp, jumAnak, thnBerdiri, alamat, email, rekening, deskripsi;
    ArrayList<Bitmap> bitmaps;

    public static fragment1 newInstance(String id) {

        fragment1 fragment = new fragment1();
        Bundle args = new Bundle();

        args.putString("id", id);
        fragment.setArguments(args);
        return fragment;
    }

    public String getID() {
        Bundle args = getArguments();
        return args.getString("id", "0");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.kadepok_fragment1_kadepok_content,null);
        gambar = (LinearLayout) v.findViewById(R.id.gambar);
        bitmaps = new ArrayList<>();
        bitmaps.add(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.image_placeholder));
        bitmaps.add(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.image_placeholder));
        bitmaps.add(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.image_placeholder));
        bitmaps.add(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.image_placeholder));
        bitmaps.add(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.image_placeholder));
        image_slide = (ImageView) v.findViewById(R.id.image_slide);

        pengurus = (TextView) v.findViewById(R.id.pengurus_panti);
        noTelp = (TextView) v.findViewById(R.id.tlpn_panti);
        jumAnak = (TextView) v.findViewById(R.id.jmlAnak);
        thnBerdiri = (TextView) v.findViewById(R.id.thn_berdiri);
        alamat = (TextView) v.findViewById(R.id.alamat_panti);
        email = (TextView) v.findViewById(R.id.email_panti);
        rekening = (TextView) v.findViewById(R.id.rekening_panti);
        deskripsi = (TextView) v.findViewById(R.id.desc_panti);
        URL = "http://hidepok.id/android/kadepok/kadepok_json.php?id=" + getID();
        getDataFromJSON(URL);
        insertimage(bitmaps);
        return v;
    }
    public void getDataFromJSON(String url){
        JsonArrayRequest req = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++){
                    JSONObject json = null;
                    try{

                        json = response.getJSONObject(i);
                        pengurus.setText(json.getString("nama_partner"));
                        noTelp.setText(json.getString("telpon_panti"));
                        jumAnak.setText(json.getString("jumlah_anak_panti"));
                        thnBerdiri.setText(json.getString("tahun_berdiri_panti"));
                        alamat.setText(json.getString("alamat_panti"));
                        email.setText(json.getString("email_panti"));
                        rekening.setText(json.getString("rekening_panti"));
                        deskripsi.setText(json.getString("deskripsi_panti"));

                        if(!json.getString("foto_detail_panti").equals("null") && json.getString("foto_detail_panti").contains(",")){
                            String[] listFoto = json.getString("foto_detail_panti").split(",");
                            String[] linkFoto = new String[listFoto.length];
                            for(int j=0;j<listFoto.length;j++){
                                linkFoto[j] = "http://hidepok.id/assets/images/photos/sikepok/sikepok3/" + listFoto[j];
                                bitmaps.add(j, BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.image_placeholder));
                                //Glide.with(getContext()).load(linkFoto[j]).thumbnail(0.3f).placeholder(R.drawable.image_placeholder).into(bitmaps);
                            }
                        }
                        else if(!json.getString("foto_detail_panti").equals("null")){
                            String urlPhoto  = "http://hidepok.id/assets/images/photos/sikepok/sikepok3/" + json.getString("foto_detail_panti");
                            bitmaps.add(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.image_placeholder));
                            //Glide.with(getContext()).load(urlPhoto).thumbnail(0.3f).placeholder(R.drawable.image_placeholder).into(image_slide);
                        }
                        else {
                            bitmaps.add(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.image_placeholder));
                        }


                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue reqQueue = Volley.newRequestQueue(getContext());
        reqQueue.add(req);
    }
    void insertimage(final ArrayList<Bitmap> bitmaps){
        for (int i=0; i<bitmaps.size(); i++){
            final ImageView imageView = new ImageView(getActivity());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(PixelUtil.dp2px(80f,getActivity()), ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(layoutParams);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setAdjustViewBounds(true);
            imageView.setImageBitmap(bitmaps.get(i));
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupImage popupImage = PopupImage.newInstance(bitmaps,gambar.indexOfChild(imageView));
                    popupImage.show(getFragmentManager(),"");
                }
            });
            gambar.addView(imageView);
        }
    }
}
