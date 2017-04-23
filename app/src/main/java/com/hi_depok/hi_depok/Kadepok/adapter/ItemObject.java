package com.hi_depok.hi_depok.Kadepok.adapter;

/**
 * Created by Farhan Fadhli on 3/22/2017.
 */

public class ItemObject {
    private String name;
    private String alamat;
    private String telepon;
    private String photo;
    private String id;

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
