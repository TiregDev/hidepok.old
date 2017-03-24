package com.hi_depok.hi_depok.Sikepok_Diagnosa;

/**
 * Created by User on 19/03/17.
 */

public class itemObject_diagnosa {
    private String nama_bagian;
    private int avatar_bagian;

    public itemObject_diagnosa(String nama_bagian, int avatar_bagian) {
        this.nama_bagian = nama_bagian;
        this.avatar_bagian = avatar_bagian;
    }

    public String getNama_bagian() {
        return nama_bagian;
    }

    public void setNama_bagian(String nama_bagian) {
        this.nama_bagian = nama_bagian;
    }

    public int getAvatar_bagian() {
        return avatar_bagian;
    }

    public void setAvatar_bagian(int avatar_bagian) {
        this.avatar_bagian = avatar_bagian;
    }
}
