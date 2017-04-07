package com.hi_depok.hi_depok.Fokopok.fragment_content;

/**
 * Created by User on 19/03/17.
 */

public class itemObject_komunitas {
    private String nama_komunitas;
    private int avatar_komunitas;

    public itemObject_komunitas(String nama_komunitas, int avatar_komunitas) {
        this.nama_komunitas = nama_komunitas;
        this.avatar_komunitas = avatar_komunitas;
    }

    public String getNama_komunitas() {
        return nama_komunitas;
    }

    public void setNama_komunitas(String nama_komunitas) {
        this.nama_komunitas = nama_komunitas;
    }

    public int getAvatar_komunitas() {
        return avatar_komunitas;
    }

    public void setAvatar_komunitas(int avatar_komunitas) {
        this.avatar_komunitas = avatar_komunitas;
    }
}
