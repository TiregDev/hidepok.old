package com.hi_depok.hi_depok.Ucok;

/**
 * Created by Muhammad63 on 3/14/2017.
 */

public class ItemObject {

    private String title;
    private String desc;
    private String price;
    private int avatar;

    public ItemObject(String title, String desc, String price, int avatar) {
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
