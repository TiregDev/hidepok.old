package com.hi_depok.hi_depok.Sikepok_RS;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
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

public class dokter extends Fragment {

    public static final String PATOKAN = "patokan";
    String GET_JSON_DATA_HTTP_URL;
    String JSON_ID_DOKTER = "id_dokter";
    String JSON_NAMA_DOKTER = "nama_dokter";
    String JSON_ALAMAT_DOKTER = "alamat_dokter";
    String JSON_NO_TELP_DOKTER = "no_telp_dokter";
    String JSON_EMAIL_DOKTER = "email_dokter";
    String JSON_SPESIALISASI = "spesialisasi";
    String JSON_FOTO_DOKTER = "foto_dokter";
    String JSON_DESKRIPSI_DOKTER = "deskripsi_dokter";
    String JSON_ID_RS ="id_rs";

    String idRs;

    JsonArrayRequest jsonArrayRequest ;
    List<GetDataAdapter> dataAdapter;
    RequestQueue requestQueue ;
    RecyclerView.Adapter recyclerViewadapter;
    RecyclerView rView;
    ProgressDialog dialog;

    //tambahan untuk fragment
    public static dokter newInstance(String title) {
        dokter fragment = new dokter();

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

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  v = inflater.inflate(R.layout.sikepokrs_dokter, null);

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

        GET_JSON_DATA_HTTP_URL = "http://hidepok.id/android/sikepok/1.2/sikepokrs_menurs_json.php?id_rs2="+ idRs;

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

                dataFromJSON.setId_dokter(json.getString(JSON_ID_DOKTER));
                dataFromJSON.setNama_dokter(json.getString(JSON_NAMA_DOKTER));
                dataFromJSON.setAlamat_dokter(json.getString(JSON_ALAMAT_DOKTER));
                dataFromJSON.setNo_telp_dokter(json.getString(JSON_NO_TELP_DOKTER));
                dataFromJSON.setEmail_dokter(json.getString(JSON_EMAIL_DOKTER));
                dataFromJSON.setSpesialisasi(json.getString(JSON_SPESIALISASI));
                dataFromJSON.setFoto_dokter(json.getString(JSON_FOTO_DOKTER));
                dataFromJSON.setDeskripsi_dokter(json.getString(JSON_DESKRIPSI_DOKTER));
                dataFromJSON.setId_rs(json.getString(JSON_ID_RS));

            } catch (JSONException e) {

                e.printStackTrace();
            }
            dataAdapter.add(dataFromJSON);
        }

        recyclerViewadapter = new RecyclerViewAdapterDOKTER(dataAdapter, getContext());
        rView.setAdapter(recyclerViewadapter);
    }


//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        public ImageView fotoDokter;
//        public TextView namaDokter;
//        public TextView spesialisasi;
//        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
//            super(inflater.inflate(R.layout.sikepokrs_fragment_dokter, parent, false));
//            fotoDokter = (ImageView) itemView.findViewById(R.id.fotoDokter);
//            namaDokter = (TextView) itemView.findViewById(R.id.namaDokter);
//            spesialisasi = (TextView) itemView.findViewById(R.id.spesialisasiDokter);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Context context = v.getContext();
//                    Intent intent = new Intent(context, info_dokter.class);
////                   intent.putExtra(DetailActivity.EXTRA_POSITION, getAdapterPosition());
//                    context.startActivity(intent);
////                    Toast.makeText(context,"Dokter telah dipilih",Toast.LENGTH_SHORT).show();
//                }
//            });
//
//        }
//    }



//    /**
//     * Adapter to display recycler view.
//     */
//    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
//        // Set numbers of Card in RecyclerView.
//        private static final int LENGTH = 18;
//
//        private final String[] NAMADOKTER;
//        private final String[] SPESIALISASI;
//        private final Drawable[] FOTODOKTER;
//
//        public ContentAdapter(Context context) {
//            Resources resources = context.getResources();
//            NAMADOKTER = resources.getStringArray(R.array.NAMADOKTER);
//            SPESIALISASI = resources.getStringArray(R.array.SPESIALIS);
//            TypedArray a = resources.obtainTypedArray(R.array.FOTODOKTER);
//            FOTODOKTER = new Drawable[a.length()];
//            for (int i = 0; i < FOTODOKTER.length; i++) {
//                FOTODOKTER[i] = a.getDrawable(i);
//            }
//            a.recycle();
//        }
//
//        @Override
//        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
//        }
//
//        @Override
//        public void onBindViewHolder(ViewHolder holder, int position) {
//            holder.fotoDokter.setImageDrawable(FOTODOKTER[position % FOTODOKTER.length]);
//            holder.namaDokter.setText(NAMADOKTER[position % NAMADOKTER.length]);
//            holder.spesialisasi.setText(SPESIALISASI[position % SPESIALISASI.length]);
//        }
//
//        @Override
//        public int getItemCount() {
//            return LENGTH;
//        }
//    }
}

