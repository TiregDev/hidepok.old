package com.hi_depok.hi_depok.Lapok.mData;

/**
 * Created by Azmi Muhammad on 3/17/2017.
 */

public class Laporan {
    String name, waktu, judul, jml_like, jml_com, jml_share;
    int image, kejadian, like_imgbtn, comment_imgbtn, share_imgbtn;

    public Laporan(String name, String waktu, String judul, String jml_like, String jml_com,
                   String jml_share, int image, int kejadian, int like_imgbtn, int comment_imgbtn,
                   int share_imgbtn) {
        this.name = name;
        this.waktu = waktu;
        this.judul = judul;
        this.jml_like = jml_like;
        this.jml_com = jml_com;
        this.jml_share = jml_share;
        this.image = image;
        this.kejadian = kejadian;
        this.like_imgbtn = like_imgbtn;
        this.comment_imgbtn = comment_imgbtn;
        this.share_imgbtn = share_imgbtn;
    }

    public String getJml_like() {
        return jml_like;
    }

    public void setJml_like(String jml_like) {
        this.jml_like = jml_like;
    }

    public String getJml_com() {
        return jml_com;
    }

    public void setJml_com(String jml_com) {
        this.jml_com = jml_com;
    }

    public String getJml_share() {
        return jml_share;
    }

    public void setJml_share(String jml_share) {
        this.jml_share = jml_share;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public int getLike_imgbtn() {
        return like_imgbtn;
    }

    public void setLike_imgbtn(int like_imgbtn) {
        this.like_imgbtn = like_imgbtn;
    }

    public int getComment_imgbtn() {
        return comment_imgbtn;
    }

    public void setComment_imgbtn(int comment_imgbtn) {
        this.comment_imgbtn = comment_imgbtn;
    }

    public int getShare_imgbtn() {
        return share_imgbtn;
    }

    public void setShare_imgbtn(int share_imgbtn) {
        this.share_imgbtn = share_imgbtn;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public int getKejadian() {
        return kejadian;
    }

    public void setKejadian(int kejadian) {
        this.kejadian = kejadian;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
