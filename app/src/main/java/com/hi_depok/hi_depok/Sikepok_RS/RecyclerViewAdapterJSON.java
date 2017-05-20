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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hi_depok.hi_depok.Activity_Main.MainActivity;
import com.hi_depok.hi_depok.Activity_Main.login;
import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.SessionManager;
import com.squareup.picasso.Picasso;

import java.net.URLEncoder;
import java.util.List;


public class RecyclerViewAdapterJSON extends RecyclerView.Adapter<RecyclerViewAdapterJSON.ViewHolder> {

    Context context;

    List<GetDataAdapter> adapter;
    String id;
    String nama;
    String foto;
    String deskripsi;
    String alamat;
    String no_telp;
    String kecamatan;
    String kordinat_lat;
    String kordinat_long;
    String website;
    String email;
    String id_partner;
    String urlPhoto;
    String jarak;
    String haha;

//    SessionManager session;

    public RecyclerViewAdapterJSON(List<GetDataAdapter> adapter, Context context){

        super();

        this.adapter = adapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sikepokrs_fragment_daftar_rs, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

//        session = new SessionManager(v.getContext());

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        id = adapter.get(position).getId();
        nama = adapter.get(position).getName();
        deskripsi = adapter.get(position).getDeskripsi();
        no_telp = adapter.get(position).getNoTelp();
        alamat = adapter.get(position).getAlamat();
        foto = adapter.get(position).getFoto();
        kecamatan = adapter.get(position).getKecamatan();
        kordinat_lat = adapter.get(position).getKordinatLat();
        kordinat_long = adapter.get(position).getKordinatLong();
        website = adapter.get(position).getWebsite();
        email = adapter.get(position).getEmail();
        id_partner = adapter.get(position).getId_partner();
        jarak = adapter.get(position).getJarak();

        holder.personName.setText(nama);
        holder.personAlamat.setText(alamat);
        holder.personDistance.setText(jarak + " km");
        holder.itemView.setTag(id);
        String encodeUrl = URLEncoder.encode(foto);
        urlPhoto = "http://hidepok.id/assets/images/photos/sikepok/sikepok2/" + encodeUrl;



//        switch (jenis){
//            case "Apotek":
//                urlPhoto = "http://hidepok.id/images/sikepok/sikepok3/APOTEK/" + encodeUrl + ".jpg";
//                break;
//            case "Bidan":
//                urlPhoto = "http://hidepok.id/images/sikepok/sikepok3/BIDAN/" + encodeUrl + ".jpg";
//                break;
//            case "Tukang Urut":
//                urlPhoto = "http://hidepok.id/images/sikepok/sikepok3/PIJAT/" + encodeUrl + ".jpg";
//                break;
//            case "Khitan":
//                urlPhoto = "http://hidepok.id/images/sikepok/sikepok3/KHITAN/" + encodeUrl + ".jpg";
//                break;
//            case "Klinik":
//                urlPhoto = "http://hidepok.id/images/sikepok/sikepok3/KLINIK/" + encodeUrl + ".jpg";
//                break;
//            case "Puskesmas":
//                urlPhoto = "http://hidepok.id/images/sikepok/sikepok3/PUSKESMAS/" + encodeUrl + ".jpg";
//                break;
//            default:
//                break;
//        }
//        Toast.makeText(context, holder.itemView.getTag().toString(),Toast.LENGTH_SHORT).show();
        Picasso.with(context).load(urlPhoto).resize(100, 100).into(holder.personPhoto);


    }

    @Override
    public int getItemCount() {

        return adapter.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView personName;
        public TextView personAlamat;
        public TextView personDistance;
        public ImageView personPhoto;

        public ViewHolder(final View itemView) {
            super(itemView);

            personName = (TextView)itemView.findViewById(R.id.namaRS);
            personAlamat = (TextView)itemView.findViewById(R.id.alamatRS);
            personDistance = (TextView)itemView.findViewById(R.id.jarakRS);
            personPhoto = (ImageView)itemView.findViewById(R.id.gambarRS);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("id_rs",itemView.getTag().toString());
                    editor.commit();

                    Context context = v.getContext();
                    Intent intent = new Intent(context, menu_rs.class);
                    context.startActivity(intent);
                }
            });

        }
    }
}