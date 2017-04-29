package com.hi_depok.hi_depok.Kadepok.adapter;

/**
 * Created by Farhan Fadhli on 3/22/2017.
 */

public class ItemObject {
    private String id;
    private String name;
    private String alamat;
    private String telepon;
    private String photo;
    private String kecamatan;
    private String email;
    private String jumlahanak;
    private String tahun;
    private String rekening;

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJumlahanak() {
        return jumlahanak;
    }

    public void setJumlahanak(String jumlahanak) {
        this.jumlahanak = jumlahanak;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getRekening() {
        return rekening;
    }

    public void setRekening(String rekening) {
        this.rekening = rekening;
    }


    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon){
        this.telepon = telepon;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo){
        this.photo = photo;
    }
}
