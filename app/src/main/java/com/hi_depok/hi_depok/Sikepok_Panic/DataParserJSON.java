package com.hi_depok.hi_depok.Sikepok_Panic;

import com.hi_depok.hi_depok.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by SONY-VAIO on 4/14/2017.
 */

public class DataParserJSON {

    public void parsingData(JSONArray array, ArrayList<GetDataAdapter> dataAdapter, String name, String deskripsi, String alamat, String no_telp) {
        for (int i = 0; i < array.length(); i++) {
            GetDataAdapter dataFromJSON = new GetDataAdapter();
            JSONObject json = null;
            try {
                json = array.getJSONObject(i);
                dataFromJSON.setName(json.getString(name));
                dataFromJSON.setDeskripsi(json.getString(deskripsi));
                dataFromJSON.setAlamat(json.getString(alamat));
                dataFromJSON.setNoTelp(json.getString(no_telp));
                dataFromJSON.setFoto(R.drawable.a_avator);

            } catch (JSONException e) {

                e.printStackTrace();
            }
            dataAdapter.add(dataFromJSON);
        }
    }
}
