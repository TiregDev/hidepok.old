package com.hi_depok.hi_depok.Ucok.Danus;

/**
 * Created by User on 18/03/17.
 */

public class itemObject_listusaha {
    private String title_usaha;
    private int desc;

    public itemObject_listusaha(String title_usaha, int desc) {
        this.title_usaha = title_usaha;
        this.desc = desc;
    }

    public String getTitle() {
        return title_usaha;
    }

    public void setTitle(String title_usaha) {
        this.title_usaha = title_usaha;
    }

    public int getDesc() {
        return desc;
    }

    public void setDesc(int desc) {
        this.desc = desc;
    }
}
