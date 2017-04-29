package com.hi_depok.hi_depok.Sikepok_Panic.util;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.net.URLEncoder;
import java.text.DecimalFormat;

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
    public double CalculationByDistance(LatLng StartP, LatLng EndP) {
        int Radius = 6371;// radius of earth in Km
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);

        return Radius * c;
    }
}
