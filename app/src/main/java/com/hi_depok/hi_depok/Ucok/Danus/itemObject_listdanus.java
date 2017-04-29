package com.hi_depok.hi_depok.Ucok.Danus;

/**
 * Created by User on 18/03/17.
 */

public class itemObject_listdanus {
    private String title;
    private String desc;
    private String price;
    private int avatar;

    public itemObject_listdanus(String title, String desc, String price, int avatar) {
        this.title = title;
        this.desc = desc;
        this.price = price;
        this.avatar = avatar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
}
