package com.hi_depok.hi_depok.Ucok;

/**
 * Created by SONY-VAIO on 3/15/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.hi_depok.hi_depok.R;
import com.squareup.picasso.Picasso;
import java.net.URLEncoder;
import java.util.List;


public class RecyclerViewAdapterJSON extends RecyclerView.Adapter<RecyclerViewAdapterJSON.ViewHolder> {

    Context context;

    List<GetDataAdapter> adapter;
    String id_barang;
    String id_ukm;
    String nama_ukm;
    String nama_owner_ukm;
    String nama_barang;
    String kategori;
    String harga_barang;
    String alamat_ukm;
    String notelp;
    String kecamatan;
    String deskripsi_ukm;
    String koordinat_ukm;
    String urlPhoto;

    public RecyclerViewAdapterJSON(List<GetDataAdapter> adapter, Context context){
        super();
        this.adapter = adapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ucok_item_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        id_barang = adapter.get(position).getId_barang();
        id_ukm = adapter.get(position).getId_ukm();
        nama_ukm = adapter.get(position).getNama_ukm();
        nama_owner_ukm = adapter.get(position).getNama_owner_ukm();
        nama_barang = adapter.get(position).getNama_barang();
        kategori = adapter.get(position).getKategori();
        harga_barang = adapter.get(position).getHarga_barang();
        alamat_ukm = adapter.get(position).getAlamat_ukm();
        notelp = adapter.get(position).getNotelp();
        kecamatan = adapter.get(position).getKecamatan();
        deskripsi_ukm = adapter.get(position).getDeskripsi_ukm();
        koordinat_ukm = adapter.get(position).getKoordinat_ukm();

        holder.list_title.setText(nama_barang);
        holder.list_desc.setText(deskripsi_ukm);
        holder.list_price.setText(harga_barang);
//        holder.itemView.setTag(id);
//        String encodeUrl = URLEncoder.encode(foto);
//
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

//        Picasso.with(context).load(urlPhoto).resize(100, 100).into(holder.personPhoto);
    }

    @Override
    public int getItemCount() {
        return adapter.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView list_title;
        public TextView list_desc;
        public TextView list_price;
        public ImageView list_avatar;

        public ViewHolder(View itemView) {
            super(itemView);

            list_title = (TextView)itemView.findViewById(R.id.list_title);
            list_desc = (TextView)itemView.findViewById(R.id.list_desc);
            list_price = (TextView)itemView.findViewById(R.id.list_price);
            list_avatar = (ImageView)itemView.findViewById(R.id.list_avatar);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, UcokDetailActivity.class);
//                    String pos = (String) itemView.getTag();
//                    Log.d("RecyclerViewAdapterJSON", "get id: " + pos);
//                    intent.putExtra(DetailActivity.EXTRA_POSITION, pos);
//                    intent.putExtra(DetailActivity.NAMA_TEMPAT, nama);
//                    intent.putExtra(DetailActivity.DESKRIPSI_TEMPAT, deskripsi);
//                    intent.putExtra(DetailActivity.LOKASI_TEMPAT, alamat);
//                    intent.putExtra(DetailActivity.NOTLP_TEMPAT, no_telp);
//                    intent.putExtra(DetailActivity.FOTO_TEMPAT, urlPhoto);
//                    intent.putExtra(DetailActivity.OPERASIONAL_TEMPAT, operasional);
//                    intent.putExtra(DetailActivity.KORDINAT_TEMPAT, kordinat);

                    context.startActivity(intent);
                }
            });


        }
    }
}
