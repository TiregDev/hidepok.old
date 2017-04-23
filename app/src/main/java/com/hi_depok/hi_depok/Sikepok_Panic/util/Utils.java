package com.hi_depok.hi_depok.Sikepok_Panic.util;

import java.net.URLEncoder;

/**
 * Created by SONY-VAIO on 4/23/2017.
 */

public class Utils {

    public String UrlPhotoEncoder(String jenis, String foto, String result) {
        String encodeUrl = URLEncoder.encode(foto);
        switch (jenis){
            case "Apotek":
                result = "http://hidepok.id/assets/images/photos/sikepok/sikepok3/APOTEK/" + encodeUrl + ".jpg";
                break;
            case "Bidan":
                result = "http://hidepok.id/assets/images/photos/sikepok/sikepok3/BIDAN/" + encodeUrl + ".jpg";
                break;
            case "Tukang Urut":
                result = "http://hidepok.id/assets/images/photos/sikepok/sikepok3/PIJAT/" + encodeUrl + ".jpg";
                break;
            case "Khitan":
                result = "http://hidepok.id/assets/images/photos/sikepok/sikepok3/KHITAN/" + encodeUrl + ".jpg";
                break;
            case "Klinik":
                result = "http://hidepok.id/assets/images/photos/sikepok/sikepok3/KLINIK/" + encodeUrl + ".jpg";
                break;
            case "Puskesmas":
                result = "http://hidepok.id/assets/images/photos/sikepok/sikepok3/PUSKESMAS/" + encodeUrl + ".jpg";
                break;
            default:
                break;
        }
        return result;
    }
}
