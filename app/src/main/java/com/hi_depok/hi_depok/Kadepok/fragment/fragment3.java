package com.hi_depok.hi_depok.Kadepok.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Muhammad63 on 3/16/2017.
 */

public class fragment3 extends Fragment {

    ImageView image_slide, image_slide_2;
    LinearLayout gambar, gambar_2;
    View v;
    String URL;
    TextView eventPanti, jumAnak;

    public static fragment3 newInstance(String id) {

        fragment3 fragment = new fragment3();
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
        v = inflater.inflate(R.layout.kadepok_fragment3_kadepok_content,null);
        gambar = (LinearLayout) v.findViewById(R.id.gambar);
        final ArrayList<Bitmap> bitmaps = new ArrayList<>();
        bitmaps.add(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.image_placeholder));
        bitmaps.add(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.image_placeholder));
        bitmaps.add(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.image_placeholder));
        bitmaps.add(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.image_placeholder));
        bitmaps.add(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.image_placeholder));
        image_slide = (ImageView) v.findViewById(R.id.image_slide);
        insertimage(bitmaps);

        //-------------------------------------- 2 ----------------------------------------------------
        gambar_2 = (LinearLayout) v.findViewById(R.id.gambar_2);
        final ArrayList<Bitmap> bitmaps2 = new ArrayList<>();
        bitmaps2.add(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.image_placeholder));
        bitmaps2.add(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.image_placeholder));
        bitmaps2.add(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.image_placeholder));
        bitmaps2.add(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.image_placeholder));
        bitmaps2.add(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.image_placeholder));
        image_slide_2 = (ImageView) v.findViewById(R.id.image_slide_2);
        insertimage_2(bitmaps2);
        eventPanti = (TextView) v.findViewById(R.id.event);
        jumAnak = (TextView) v.findViewById(R.id.jumlah_anak);
        URL = "http://hidepok.id/android/kadepok/kadepok_json.php?id=" + getID();
        getDataFromJSON(URL);
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
                        eventPanti.setText("Panti Asuhan " + json.getString("nama_panti") + " memiliki Beberapa Event");
                        jumAnak.setText("Panti Asuhan " + json.getString("nama_panti") + " memiliki " + json.getString("jumlah_anak_panti") + " anak.");



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

    void insertimage_2(final ArrayList<Bitmap> bitmaps2){
        for (int i=0; i<bitmaps2.size(); i++){
            final ImageView imageView = new ImageView(getActivity());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(PixelUtil.dp2px(80f,getActivity()), ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(layoutParams);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setAdjustViewBounds(true);
            imageView.setImageBitmap(bitmaps2.get(i));
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupImage popupImage = PopupImage.newInstance(bitmaps2,gambar_2.indexOfChild(imageView));
                    popupImage.show(getFragmentManager(),"");
                }
            });
            gambar_2.addView(imageView);
        }
    }
}
