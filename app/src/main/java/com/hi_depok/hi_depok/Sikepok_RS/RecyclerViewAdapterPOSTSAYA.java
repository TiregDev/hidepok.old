package com.hi_depok.hi_depok.Sikepok_RS;

/**
 * Created by SONY-VAIO on 3/15/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hi_depok.hi_depok.Akses;
import com.hi_depok.hi_depok.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RecyclerViewAdapterPOSTSAYA extends RecyclerView.Adapter<RecyclerViewAdapterPOSTSAYA.ViewHolder> {

    Context context;
    Button btnHapus;
    String DELETE_URL = "http://hidepok.id/android/sikepok/1.2/sikepokrs_delete_json.php";

    List<GetDataAdapter> adapter;

    String id_post;
    String judul_post;
    String isi_post;
    String angka_komen;
    String angka_suka;
    String tanggal_post;
    String waktu_post;
    String kategori_post;
    String foto_post;
    String deskripsi_post;
    String lokasi_post;
    String no_identitas_post;
    String status_post;
    String rating_post;
    String id_user;
    String id_modul;
    String urlPhoto;

    public RecyclerViewAdapterPOSTSAYA(List<GetDataAdapter> adapter, Context context){

        super();

        this.adapter = adapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sikepokrs_fragment_post_saya, parent, false);

        final ViewHolder viewHolder = new ViewHolder(v);

        btnHapus = (Button) v.findViewById(R.id.hapus);

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.deletePost();
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        id_post = adapter.get(position).getId_post();
        judul_post = adapter.get(position).getJudul_post();
        isi_post = adapter.get(position).getIsi_post();
        angka_komen = adapter.get(position).getAngka_komen();
        angka_suka = adapter.get(position).getAngka_suka();

        tanggal_post = adapter.get(position).getTanggal_post();
        waktu_post = adapter.get(position).getWaktu_post();
        kategori_post = adapter.get(position).getKategori_post();
        deskripsi_post = adapter.get(position).getDeskripsi_post();
        lokasi_post = adapter.get(position).getLokasi_post();
        no_identitas_post = adapter.get(position).getNo_identitas_post();
        status_post = adapter.get(position).getStatus_post();
        rating_post = adapter.get(position).getRating_post();
        id_user = adapter.get(position).getId_user();
        id_modul = adapter.get(position).getId_modul();
        foto_post = adapter.get(position).getFoto_post();


        holder.judulPost.setText(judul_post);
        holder.isiPost.setText(isi_post);
        holder.angkaKomen.setText(angka_komen);
        holder.angkaSuka.setText(angka_suka);
        holder.itemView.setTag(id_post);

    }

    @Override
    public int getItemCount() {

        return adapter.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView judulPost;
        public TextView isiPost;
        public TextView angkaKomen;
        public TextView angkaSuka;

        public ViewHolder(final View itemView) {

            super(itemView);

            judulPost = (TextView)itemView.findViewById(R.id.judul_post);
            isiPost = (TextView)itemView.findViewById(R.id.isi_post);
            angkaKomen = (TextView)itemView.findViewById(R.id.angka_komen);
            angkaSuka = (TextView)itemView.findViewById(R.id.angka_like);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("id_post_saya",itemView.getTag().toString());
                    editor.commit();

                    Context context = v.getContext();
                    Intent intent = new Intent(context, detail_post_saya.class);
                    context.startActivity(intent);
                }
            });
        }

        public void deletePost() {
            StringRequest delete = new StringRequest(Request.Method.POST, DELETE_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(RecyclerViewAdapterPOSTSAYA.this.context, response, Toast.LENGTH_SHORT).show();
                            context.startActivity(new Intent(RecyclerViewAdapterPOSTSAYA.this.context, post_saya.class));
//                            finish();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> param = new HashMap<>();
                    param.put("id_post", itemView.getTag().toString());
                    return param;
                }
            };
            Akses.getInstance(RecyclerViewAdapterPOSTSAYA.this.context).addtoRequestQueue(delete);
        }

    }

}