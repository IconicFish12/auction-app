package lelang.app.model;

public class Barang {
    private long id, userId, kategoriId;
    private String nama_barang, deskripsiBarang, foto, prosesLelang, status;
    private int harga_barang;

    public Barang(long id, long userId, long kategoriId, String nama_barang, String deskripsiBarang, String foto,
            String prosesLelang, String status, int harga_barang) {
        this.id = id;
        this.userId = userId;
        this.kategoriId = kategoriId;
        this.nama_barang = nama_barang;
        this.deskripsiBarang = deskripsiBarang;
        this.foto = foto;
        this.prosesLelang = prosesLelang;
        this.status = status;
        this.harga_barang = harga_barang;
    }

    public long getId() {
        return id;
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

    public String getDeskripsiBarang() {
        return deskripsiBarang;
    }

    public void setDeskripsiBarang(String deskripsiBarang) {
        this.deskripsiBarang = deskripsiBarang;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getProsesLelang() {
        return prosesLelang;
    }

    public void setProsesLelang(String prosesLelang) {
        this.prosesLelang = prosesLelang;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getHarga_barang() {
        return harga_barang;
    }

    public void setHarga_barang(int harga_barang) {
        this.harga_barang = harga_barang;
    }

}
