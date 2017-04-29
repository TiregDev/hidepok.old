package com.hi_depok.hi_depok.Sikepok_RS;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.hi_depok.hi_depok.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muhammad on 13/03/2017.
 */

public class fasilitas extends Fragment {

    ImageView image_slide;
    LinearLayout gambar;
    View v;
    String idRs;

    String GET_JSON_DATA_HTTP_URL;
    String JSON_ID_RS = "id_rs";
    String JSON_ID_FASILITAS = "id_fasilitas";
    String JSON_NAMA_FASILITAS = "nama_fasilitas";
    String JSON_DESKRIPSI_FASILITAS = "deskripsi_fasilitas";
    String JSON_FOTO_FASILITAS = "foto_fasilitas";

    JsonArrayRequest jsonArrayRequest ;
    List<GetDataAdapter> dataAdapter;
    RequestQueue requestQueue ;
    RecyclerView.Adapter recyclerViewadapter;
    RecyclerView rView;
    ProgressDialog dialog;

    //tambahan untuk fragment
    public static fasilitas newInstance(String title) {
        fasilitas fragment = new fasilitas();

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
//        List<String> pilih = new ArrayList<String>();
//        pilih.add("");
            v = inflater.inflate(R.layout.sikepokrs_fasilitas,null);

//        gambar = (LinearLayout) v.findViewById(R.id.gambar);
//        final ArrayList<Bitmap> bitmaps = new ArrayList<>();
//        bitmaps.add(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.img_1));
//        bitmaps.add(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.img_2));
//        bitmaps.add(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.img_3));
//        bitmaps.add(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.img_4));
//        bitmaps.add(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.img_5));
//        image_slide = (ImageView) v.findViewById(R.id.image_slide);
//        insertimage(bitmaps);

        //r view db
        rView = (RecyclerView) v.findViewById(R.id.recyclerview);

        rView.setLayoutManager(new LinearLayoutManager(getContext()));

        dataAdapter = new ArrayList<>();
        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Loading");
        dialog.show();
        dialog.setCancelable(true);

        //shared preferences test
        final SharedPreferences prefsa = PreferenceManager.getDefaultSharedPreferences(getContext());
        idRs = prefsa.getString("id_rs","No data found");

        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/android/sikepok/1.2/sikepokrs_menurs_json.php?id_rs3=" + idRs;

        JSON_DATA_WEB_CALL();

        return v;
    }
//    //atur gambar slider
//    void insertimage(final ArrayList<Bitmap> bitmaps){
//        for (int i=0; i<bitmaps.size(); i++){
//            final ImageView imageView = new ImageView(getActivity());
//            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(PixelUtil.dp2px(80f,getActivity()), ViewGroup.LayoutParams.MATCH_PARENT);
//            imageView.setLayoutParams(layoutParams);
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setAdjustViewBounds(true);
//            imageView.setImageBitmap(bitmaps.get(i));
//            imageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    PopupImage popupImage = PopupImage.newInstance(bitmaps,gambar.indexOfChild(imageView));
//                    popupImage.show(getFragmentManager(),"");
//                }
//            });
//            gambar.addView(imageView);
//        }
//    }

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

                dataFromJSON.setNama_fasilitas(json.getString(JSON_NAMA_FASILITAS));
                dataFromJSON.setDeskripsi_fasilitas(json.getString(JSON_DESKRIPSI_FASILITAS));
                dataFromJSON.setFoto_fasilitas(json.getString(JSON_FOTO_FASILITAS));
                dataFromJSON.setId_rs(json.getString(JSON_ID_RS));
                dataFromJSON.setId_fasilitas(json.getString(JSON_ID_FASILITAS));

            } catch (JSONException e) {

                e.printStackTrace();
            }
            dataAdapter.add(dataFromJSON);
        }

        recyclerViewadapter = new RecyclerViewAdapterFASILITAS(dataAdapter, getContext());
        rView.setAdapter(recyclerViewadapter);
    }

}
