package com.hi_depok.hi_depok.Sikepok_RS;

import android.app.ProgressDialog;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.hi_depok.hi_depok.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muhammad on 13/03/2017.
 */

public class fasilitas extends Fragment {

    ImageView image_slide;
    LinearLayout gambar;
    View v;

    String GET_JSON_DATA_HTTP_URL = "http://hidepok.id/include/sikepokrs_menurs_json.php?nama_rs=RS%20Mitra%20Keluarga";
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
        v = inflater.inflate(R.layout.sikepokrs_fragment_fasilitas,null);
        gambar = (LinearLayout) v.findViewById(R.id.gambar);
        final ArrayList<Bitmap> bitmaps = new ArrayList<>();
        bitmaps.add(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.image_fokopok_1));
        bitmaps.add(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.image_fokopok_2));
        bitmaps.add(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.image_fokopok_3));
        bitmaps.add(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.ucok_image_1));
        bitmaps.add(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.ucok_image_2));
        image_slide = (ImageView) v.findViewById(R.id.image_slide);
        insertimage(bitmaps);

        //

        return v;
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
