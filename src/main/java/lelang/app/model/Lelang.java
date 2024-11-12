package lelang.app.model;

import java.util.Date;

public class Lelang {
    private long id, barangId, userId, petugasId;
    private Date tgl_mulai, tgl_selesai, tgl_lelang;
    private int harga_awal, harga_lelang;
    
    public Lelang(long id, long barangId, long userId, long petugasId, Date tgl_mulai, Date tgl_selesai,
            Date tgl_lelang, int harga_awal, int harga_lelang) {
        this.id = id;
        this.barangId = barangId;
        this.userId = userId;
        this.petugasId = petugasId;
        this.tgl_mulai = tgl_mulai;
        this.tgl_selesai = tgl_selesai;
        this.tgl_lelang = tgl_lelang;
        this.harga_awal = harga_awal;
        this.harga_lelang = harga_lelang;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBarangId() {
        return barangId;
    }

    public void setBarangId(long barangId) {
        this.barangId = barangId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getPetugasId() {
        return petugasId;
    }

    public void setPetugasId(long petugasId) {
        this.petugasId = petugasId;
    }

    public Date getTgl_mulai() {
        return tgl_mulai;
    }

    public void setTgl_mulai(Date tgl_mulai) {
        this.tgl_mulai = tgl_mulai;
    }

    public Date getTgl_selesai() {
        return tgl_selesai;
    }

    public void setTgl_selesai(Date tgl_selesai) {
        this.tgl_selesai = tgl_selesai;
    }

    public Date getTgl_lelang() {
        return tgl_lelang;
    }

    public void setTgl_lelang(Date tgl_lelang) {
        this.tgl_lelang = tgl_lelang;
    }

    public int getHarga_awal() {
        return harga_awal;
    }

    public void setHarga_awal(int harga_awal) {
        this.harga_awal = harga_awal;
    }

    public int getHarga_lelang() {
        return harga_lelang;
    }

    public void setHarga_lelang(int harga_lelang) {
        this.harga_lelang = harga_lelang;
    }

    
}
