package com.hi_depok.hi_depok.Sikepok_Diagnosa;

/**
 * Created by User on 18/03/17.
 */

public class itemObject_listapotik {
    private String title;
    private String desc;
    private int avatar;

    public itemObject_listapotik(String title, String desc, int avatar) {
        this.title = title;
        this.desc = desc;
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

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
}
