package com.hi_depok.hi_depok.Lapok.mData;

/**
 * Created by Azmi Muhammad on 3/17/2017.
 */

public class Laporan {
    String id, name, waktu, judul, jml_like, jml_com, status, image, kejadian, isi, tanggal;
    int like_imgbtn, comment_imgbtn, share_imgbtn;

    public Laporan() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsi() {
        return isi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public void setIsi(String isi) {
        this.isi = isi;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getKejadian() {
        return kejadian;
    }

    public void setKejadian(String kejadian) {
        this.kejadian = kejadian;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
