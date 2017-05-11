package com.hi_depok.hi_depok.Sikepok_RS;


/**
 * Created by SONY-VAIO on 3/15/2017.
 */

public class GetDataAdapter {

    //adapter table RS
    String id;
    String nama;
    String foto;
    String deskripsi;
    String alamat;
    String no_telp;
    String kecamatan;
    String kordinat_lat;
    String kordinat_long;
    String website;
    String email;
    String id_partner;
    int foto_drawable;


    public String getName() {
        return nama;
    }
    public void setName(String name) {
        this.nama = name;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    public String getDeskripsi() {
        return deskripsi;
    }
    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
    public String getAlamat() {
        return alamat;
    }
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    public String getNoTelp() {
        return no_telp;
    }
    public void setNoTelp(String no_telp) {
        this.no_telp = no_telp;
    }

    public String getKecamatan() {
        return kecamatan;
    }
    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }
    public String getKordinatLat() {
        return kordinat_lat;
    }
    public void setKordinatLat(String kordinat) {
        this.kordinat_lat = kordinat;
    }

    public String getKordinatLong() {
        return kordinat_long;
    }
    public void setKordinatLong(String kordinat) {
        this.kordinat_long = kordinat;
    }

    public String getWebsite() {
        return website;
    }
    public void setWebsite(String kordinat) {
        this.website = kordinat;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String kordinat) {
        this.email = kordinat;
    }

    public String getId_partner() {
        return id_partner;
    }
    public void setId_partner(String kordinat) {
        this.id_partner = kordinat;
    }

    public int getFotoDrawable() {
        return foto_drawable;
    }
    public void setFotoDrawable(int foto_drawable) {
        this.foto_drawable = foto_drawable;
    }

    //adapter table post
    String id_post;
    String judul_post;
    String isi_post;
    String tanggal_post;
    String waktu_post;
    String kategori_post;
    String foto_post;
    String deskripsi_post;
    String lokasi_post;
    String no_identitas_post;
    String status_post;
    String rating_post;
    String id_user;
    String id_modul;

    public String getId_post() {
        return id_post;
    }
    public void setId_post(String id) {
        this.id_post = id;
    }

    public String getJudul_post() {
        return judul_post;
    }
    public void setJudul_post(String judul) {
        this.judul_post = judul;
    }

    public String getIsi_post() {
        return isi_post;
    }
    public void setIsi_post(String isi_post) {
        this.isi_post = isi_post;
    }

    public String getTanggal_post() {
        return tanggal_post;
    }
    public void setTanggal_post(String tanggal) {
        this.tanggal_post = tanggal;
    }

    public String getWaktu_post() {
        return waktu_post;
    }
    public void setWaktu_post(String waktu) {
        this.waktu_post = waktu;
    }

    public String getKategori_post() {
        return kategori_post;
    }
    public void setKategori_post(String kategori) {
        this.kategori_post = kategori;
    }

    public String getFoto_post() {
        return foto_post;
    }
    public void setFoto_post(String foto_post) {
        this.foto_post = foto_post;
    }

    public String getDeskripsi_post() {
        return deskripsi_post;
    }
    public void setDeskripsi_post(String deskripsi) {
        this.deskripsi_post = deskripsi;
    }

    public String getLokasi_post() {
        return lokasi_post;
    }
    public void setLokasi_post(String lokasi_post) {
        this.lokasi_post = lokasi_post;
    }

    public String getNo_identitas_post() {
        return no_identitas_post;
    }
    public void setNo_identitas_post(String identitas) {
        this.no_identitas_post = identitas;
    }

    public String getStatus_post() {
        return status_post;
    }
    public void setStatus_post(String status) {
        this.status_post = status;
    }

    public String getRating_post() {
        return rating_post;
    }
    public void setRating_post(String rating) {
        this.rating_post = rating;
    }

    public String getId_user() {
        return id_user;
    }
    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getId_modul() {
        return id_modul;
    }
    public void setId_modul(String id_modul) {
        this.id_modul = id_modul;
    }

    //adapter table dokter
    String id_dokter;
    String nama_dokter;
    String alamat_dokter;
    String no_telp_dokter;
    String email_dokter;
    String spesialisasi;
    String foto_dokter;
    String deskripsi_dokter;
    String id_rs;

    public String getId_dokter() {
        return id_dokter;
    }
    public void setId_dokter(String id_dokter) {
        this.id_dokter = id_dokter;
    }

    public String getNama_dokter() {
        return nama_dokter;
    }
    public void setNama_dokter(String nama_dokter) {
        this.nama_dokter = nama_dokter;
    }

    public String getAlamat_dokter() {
        return alamat_dokter;
    }
    public void setAlamat_dokter(String alamat_dokter) {
        this.alamat_dokter = alamat_dokter;
    }

    public String getNo_telp_dokter() {
        return no_telp_dokter;
    }
    public void setNo_telp_dokter(String no_telp_dokter) {
        this.no_telp_dokter = no_telp_dokter;
    }

    public String getEmail_dokter() {
        return email_dokter;
    }
    public void setEmail_dokter(String email_dokter) {
        this.email_dokter = email_dokter;
    }

    public String getSpesialisasi() {
        return spesialisasi;
    }
    public void setSpesialisasi(String spesialisasi) {
        this.spesialisasi = spesialisasi;
    }

    public String getFoto_dokter() {
        return foto_dokter;
    }
    public void setFoto_dokter(String foto_dokter) {
        this.foto_dokter = foto_dokter;
    }

    public String getDeskripsi_dokter() {
        return deskripsi_dokter;
    }
    public void setDeskripsi_dokter(String deskripsi_dokter) {
        this.deskripsi_dokter = deskripsi_dokter;
    }

    public String getId_rs() {
        return id_rs;
    }
    public void setId_rs(String id_rs) {
        this.id_rs = id_rs;
    }


    //adapter table jadwal
    String id_jadwal;
    String senin;
    String selasa;
    String rabu;
    String kamis;
    String jumat;
    String sabtu;
    String minggu;

    public String getId_jadwal() {
        return id_jadwal;
    }
    public void setId_jadwal(String id_jadwal) {
        this.id_jadwal = id_jadwal;
    }

    public String getSenin() {
        return senin;
    }
    public void setSenin(String senin) {
        this.senin = senin;
    }

    public String getSelasa() {
        return selasa;
    }
    public void setSelasa(String selasa) {
        this.selasa = selasa;
    }

    public String getRabu() {
        return rabu;
    }
    public void setRabu(String rabu) {
        this.rabu = rabu;
    }

    public String getKamis() {
        return kamis;
    }
    public void setKamis(String kamis) {
        this.kamis = kamis;
    }

    public String getJumat() {
        return jumat;
    }
    public void setJumat(String jumat) {
        this.jumat = jumat;
    }

    public String getSabtu() {
        return sabtu;
    }
    public void setSabtu(String sabtu) {
        this.sabtu = sabtu;
    }

    public String getMinggu() {
        return minggu;
    }
    public void setMinggu(String minggu) {
        this.minggu = minggu;
    }

    //adapter table fasilitas
    String id_fasilitas;
    String nama_fasilitas;
    String foto_fasilitas;
    String deskripsi_fasilitas;

    public String getId_fasilitas() {
        return id_fasilitas;
    }
    public void setId_fasilitas(String id_fasilitas) {
        this.id_fasilitas = id_fasilitas;
    }

    public String getNama_fasilitas() {
        return nama_fasilitas;
    }
    public void setNama_fasilitas(String nama_fasilitas) {
        this.nama_fasilitas = nama_fasilitas;
    }

    public String getFoto_fasilitas() {
        return foto_fasilitas;
    }
    public void setFoto_fasilitas(String foto_fasilitas) {
        this.foto_fasilitas = foto_fasilitas;
    }

    public String getDeskripsi_fasilitas() {
        return deskripsi_fasilitas;
    }
    public void setDeskripsi_fasilitas(String deskripsi_fasilitas) {
        this.deskripsi_fasilitas = deskripsi_fasilitas;
    }

    //adapter table user
    String nama_user;

    public String getNama_user() {
        return nama_user;
    }
    public void setNama_user(String nama_user) {
        this.nama_user = nama_user;
    }

    //adapter table komentar
    String id_komentar;
    String isi_komentar;
    String tanggal_komentar;
    String waktu_komentar;

    public String getId_komentar() {
        return id_komentar;
    }
    public void setId_komentar(String id_komentar) {
        this.id_komentar = id_komentar;
    }

    public String getIsi_komentar() {
        return isi_komentar;
    }
    public void setIsi_komentar(String isi_komentar) {
        this.isi_komentar = isi_komentar;
    }

    public String getTanggal_komentar() {
        return tanggal_komentar;
    }
    public void setTanggal_komentar(String tanggal_komentar) {
        this.tanggal_komentar = tanggal_komentar;
    }

    public String getWaktu_komentar() {
        return waktu_komentar;
    }
    public void setWaktu_komentar(String waktu_komentar) {
        this.waktu_komentar = waktu_komentar;
    }
}