package com.hi_depok.hi_depok.Kapok;

/**
 * Created by User on 3/24/2017.
 */

public class ItemObjectViewUlasan {
    private String title;
    private String waktu;
    private String desc;
    private int avatar;

    public ItemObjectViewUlasan(String title, String waktu, String desc, int avatar){
        this.title = title;
        this.waktu = waktu;
        this.desc = desc;
        this.avatar = avatar;
    }

    public String getTitle(){return title; }
    public void setTitle(String title){this.title = title;}

    public String getTime(){return waktu; }
    public void setWaktu(String desc){this.waktu = waktu;}

    public String getDesc(){return desc; }
    public void setDesc(String desc){this.desc = desc; }

    public int getAvatar(){return avatar;}
}
