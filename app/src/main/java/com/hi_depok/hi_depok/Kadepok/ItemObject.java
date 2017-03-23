package com.hi_depok.hi_depok.Kadepok;

/**
 * Created by Farhan Fadhli on 3/22/2017.
 */

public class ItemObject {
    private String title;
    private String alamat;
    private String telepon;
    private int avatar;

    public ItemObject(String title, String alamat, String telepon, int avatar) {
        this.title = title;
        this.alamat = alamat;
        this.telepon = telepon;
        this.avatar = avatar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String desc) {
        this.alamat = desc;
    }

    public String getTelepon() {
        return telepon;
    }

    public int getAvatar() {
        return avatar;
    }
}
