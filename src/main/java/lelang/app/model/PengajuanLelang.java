package lelang.app.model;

import java.sql.Date;

import lelang.database.DAO.KategoriDAO;
import lelang.database.DAO.MasyarakatDAO;

public class PengajuanLelang {
    private long id, userId, kategoriId;
    private String nama_barang, status_pengajuan;
    private int harga_lelang, harga_barang;
    private Date mulai_lelang, selesai_lelang;

    // Lazy Load //Table Relation 
    private Masyarakat user;
    private Kategori kategori;

    // DAO // Database Access
    private static MasyarakatDAO dataUser = new MasyarakatDAO();
    private static KategoriDAO dataKategori = new KategoriDAO();
    
    public PengajuanLelang(long id, long userId, long kategoriId, String nama_barang, String status_pengajuan, int harga_lelang, int harga_barang, Date mulai_lelang, Date selesai_lelang) {
        this.id = id;
        this.userId = userId;
        this.kategoriId = kategoriId;
        this.nama_barang = nama_barang;
        this.status_pengajuan = status_pengajuan;
        this.harga_lelang = harga_lelang;
        this.harga_barang = harga_barang;
        this.mulai_lelang = mulai_lelang;
        this.selesai_lelang = selesai_lelang;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getKategoriId() {
        return kategoriId;
    }

    public void setKategoriId(long kategoriId) {
        this.kategoriId = kategoriId;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public String getStatus_pengajuan() {
        return status_pengajuan;
    }

    public void setStatus_pengajuan(String status_pengajuan) {
        this.status_pengajuan = status_pengajuan;
    }

    public int getHarga_lelang() {
        return harga_lelang;
    }

    public void setHarga_lelang(int harga_lelang) {
        this.harga_lelang = harga_lelang;
    }

    public int getHarga_barang() {
        return harga_barang;
    }

    public void setHarga_barang(int harga_barang) {
        this.harga_barang = harga_barang;
    }

    public Date getMulai_lelang() {
        return mulai_lelang;
    }

    public void setMulai_lelang(Date mulai_lelang) {
        this.mulai_lelang = mulai_lelang;
    }

    public Date getSelesai_lelang() {
        return selesai_lelang;
    }

    public void setSelesai_lelang(Date selesai_lelang) {
        this.selesai_lelang = selesai_lelang;
    }

    // Relation Handler 

    public Masyarakat getUser(){
        if(user == null){
            this.user = dataUser.findById(this.getUserId());
        }

        return user;
    }

    public Kategori getKategori(){
        if(kategori == null){
            this.kategori = dataKategori.findById(this.getKategoriId());
        }

        return kategori;
    }

    // displayData

    public void displayData(){
        System.out.println("ID: " + this.getId());
        System.out.println("Nama Barang: " + this.getNama_barang());
        System.out.println("User: " + this.getUser().getNama_lengkap());
        System.out.println("Kategori: " + this.getKategori().getNamaKategori());
        System.out.println("Harga Barang: " + this.getHarga_barang());
        System.out.println("Harga Lelang: " + this.getHarga_lelang());
        System.out.println("Mulai Lelang: " + this.getMulai_lelang());
        System.out.println("Selesai Lelang: " + this.getSelesai_lelang());
        System.out.println("Status Pengajuan: " + this.getStatus_pengajuan());
    }

}
