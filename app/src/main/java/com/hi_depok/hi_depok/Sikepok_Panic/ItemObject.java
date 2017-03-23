package com.hi_depok.hi_depok.Sikepok_Panic;

/**
 * Created by SONY-VAIO on 3/14/2017.
 */

public class ItemObject {

    private String name;
    private String jarak;
    private String address;
    private int photoId;

    public ItemObject(String name, String jarak, String address, int photoId) {
        this.name = name;
        this.address = address;
        this.photoId = photoId;
        this.jarak = jarak;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJarak(String jarak) {
        this.jarak = jarak;
    }

    public String getAddress() {
        return address;
    }

    public int getPhotoId() {
        return photoId;
    }

}
