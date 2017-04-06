package com.hi_depok.hi_depok.notif;

/**
 * Created by Muhammad63 on 4/6/2017.
 */

public class ItemObject {

    private String title;
    private String desc;
    private String time;
    private int avatar;

    public ItemObject(String title, String desc, String time, int avatar) {
        this.title = title;
        this.desc = desc;
        this.time = time;
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

    public String getTime() {
        return time;
    }

    public int getAvatar() {
        return avatar;
    }

}

