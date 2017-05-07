package com.hi_depok.hi_depok.Lapok.mAdapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hi_depok.hi_depok.Lapok.mData.Komentar;
import com.hi_depok.hi_depok.R;

import java.util.List;

/**
 * Created by Azmi Muhammad on 4/24/2017.
 */

public class Adapter_Komentar extends ArrayAdapter<Komentar>{
    Context mContext;
    int layoutResourcesId;
    List<Komentar> mList = null;

    public Adapter_Komentar(Context context, int resource, List<Komentar> objects) {
        super(context, resource, objects);

        this.layoutResourcesId = resource;
        this.mContext = context;
        this.mList = objects;
    }

    public class Holder_Komentar{
        TextView nama, isi_komentar;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder_Komentar holder_komentar = null;

        if (convertView == null) {
            LayoutInflater layoutInflater = ((Activity)mContext).getLayoutInflater();

            convertView = layoutInflater.inflate(layoutResourcesId, parent, false);

            holder_komentar = new Holder_Komentar();
            holder_komentar.nama = (TextView)convertView.findViewById(R.id.namaUser_Post);
            holder_komentar.isi_komentar = (TextView)convertView.findViewById(R.id.isiKomentarUser);

            convertView.setTag(holder_komentar);
        }else{
            holder_komentar = (Holder_Komentar)convertView.getTag();
        }

        Komentar komentar = mList.get(position);
        holder_komentar.nama.setText(komentar.getNama());
        holder_komentar.isi_komentar.setText(komentar.getIsi_komentar());

        return convertView;
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
