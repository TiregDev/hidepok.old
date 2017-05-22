package com.hi_depok.hi_depok.Ucok.Danus;

/**
 * Created by SONY-VAIO on 3/15/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hi_depok.hi_depok.R;
import com.hi_depok.hi_depok.Ucok.SIUMKM.GetDataAdapter_siumkm;
import com.hi_depok.hi_depok.Ucok.SIUMKM.UcokDetailActivity;

import java.util.List;


public class KalkulasiAdapter extends RecyclerView.Adapter<KalkulasiAdapter.ViewHolder> {

    Context context;
    List<GetDataAdapter_siumkm> adapter;
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
    String koordinat_ukm_1;
    String koordinat_ukm_2;
    String foto_barang;
    String foto_ukm;
    String harga_jual;
    String persentase;
    String keuntungan;
    String target_waktu;
    String target_uang;
    String urlPhoto;

    public KalkulasiAdapter(List<GetDataAdapter_siumkm> adapter, Context context){
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
        koordinat_ukm_1 = adapter.get(position).getKoordinat_ukm_1();
        koordinat_ukm_2 = adapter.get(position).getKoordinat_ukm_2();
        foto_barang = adapter.get(position).getFoto_barang();
        foto_ukm = adapter.get(position).getFoto_ukm();
        harga_jual = adapter.get(position).getHarga_jual();
        persentase = adapter.get(position).getPersentase();
        keuntungan = adapter.get(position).getKeuntungan();
        target_uang = adapter.get(position).getTarget_uang();
        target_waktu = adapter.get(position).getTarget_waktu();
        String nama_ukm_2 = "Dari UKM "+nama_ukm;
        String harga_barang_2 = "Rp "+harga_jual;
        String nama_foto = foto_ukm;

        holder.list_title.setText(nama_barang);
        holder.list_desc.setText(nama_ukm_2);
        holder.list_price.setText(harga_barang_2);
        holder.itemView.setTag(id_barang);

        urlPhoto = "http://hidepok.id/assets/images/photos/ucok/"+foto_barang;
        Glide.with(context).load(urlPhoto).thumbnail(0.3f).placeholder(R.drawable.image_placeholder).into(holder.list_avatar);
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

        public ViewHolder(final View itemView) {
            super(itemView);

            list_title = (TextView)itemView.findViewById(R.id.list_title);
            list_desc = (TextView)itemView.findViewById(R.id.list_desc);
            list_price = (TextView)itemView.findViewById(R.id.list_price);
            list_avatar = (ImageView)itemView.findViewById(R.id.list_avatar);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, detail_danus.class);
                    String id = (String) itemView.getTag();
                    intent.putExtra(UcokDetailActivity.PATOKAN, id);
                    context.startActivity(intent);
                }
            });
        }
    }
}
