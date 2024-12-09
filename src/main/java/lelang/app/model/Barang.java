package lelang.app.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import lelang.database.DAO.KategoriDAO;
import lelang.database.DAO.MasyarakatDAO;

public class Barang {
    private long id, userId, kategoriId;
    private String nama_barang, deskripsiBarang, foto, proses_lelang, status_lelang;
    private int harga_barang;

    // Add Lazy Load
    private Kategori kategori;
    private Masyarakat user;

    // Adding DAO Into Barang
    private static KategoriDAO dataKategori = new KategoriDAO();
    private static MasyarakatDAO dataMasyarakat = new MasyarakatDAO();

    // Handling N to N Relation
    private LinkedHashMap<Integer, List<Lelang>> lelangs = new LinkedHashMap<>();

    public Barang(long id, long userId, long kategoriId, String nama_barang, String deskripsiBarang, int harga_barang,
            String foto, String status_lelang, String proses_lelang, Kategori kategori, Masyarakat user) {
        this.id = id;
        this.userId = userId;
        this.kategoriId = kategoriId;
        this.nama_barang = nama_barang;
        this.deskripsiBarang = deskripsiBarang;
        this.foto = foto;
        this.proses_lelang = proses_lelang;
        this.status_lelang = status_lelang;
        this.harga_barang = harga_barang;
        this.kategori = kategori;
        this.user = user;
    }
    
    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public long getKategoriId() {
        return kategoriId;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public String getDeskripsiBarang() {
        return deskripsiBarang;
    }

    public String getFoto() {
        return foto;
    }

    public String getproses_lelang() {
        return proses_lelang;
    }

    public String getStatus_lelang() {
        return status_lelang;
    }

    public int getHarga_barang() {
        return harga_barang;
    }

    public String getProses_lelang() {
        return proses_lelang;
    }

    public Kategori getKategori() {
        if (kategori == null) {
            this.kategori = dataKategori.findById(this.getKategoriId());
        }

        return kategori;
    }

    public Masyarakat getUser() {
        if (user == null) {
            this.user = dataMasyarakat.findById(this.getUserId());
        }

        return user;
    }

    public void addLelangs(Lelang lelang) {
        this.lelangs.putIfAbsent((int) lelang.getBarangId(), new ArrayList<>());
    }

    public LinkedHashMap<Integer, List<Lelang>> getLelangs() {
        return lelangs;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setKategoriId(long kategoriId) {
        this.kategoriId = kategoriId;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public void setDeskripsiBarang(String deskripsiBarang) {
        this.deskripsiBarang = deskripsiBarang;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setProses_lelang(String proses_lelang) {
        this.proses_lelang = proses_lelang;
    }

    public void setStatus_lelang(String status_lelang) {
        this.status_lelang = status_lelang;
    }

    public void setHarga_barang(int harga_barang) {
        this.harga_barang = harga_barang;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    public void setUser(Masyarakat user) {
        this.user = user;
    }

    public static void setDataKategori(KategoriDAO dataKategori) {
        Barang.dataKategori = dataKategori;
    }

    public static void setDataMasyarakat(MasyarakatDAO dataMasyarakat) {
        Barang.dataMasyarakat = dataMasyarakat;
    }

    public void setLelangs(LinkedHashMap<Integer, List<Lelang>> lelangs) {
        this.lelangs = lelangs;
    }

    // behavior

    public void displayData() {
        System.out.println(" =========== Data Barang ============");
        System.out.println("Data ke -" + id);
        System.out.println("Nama Barang : " + this.getNama_barang());
        System.out.println("Kategori Barang: " + (kategori != null
                ? this.getKategori().getNamaKategori()
                : "Kategori tidak ditemukan"));
        System.out.println("Pemilik Barang: " +
                (user != null
                        ? this.getUser().getNama_lengkap()
                        : "Pemilik tidak ditemukan"));
        System.out.println("Deskripsi Barang : " + this.getDeskripsiBarang());
        System.out.println("Foto Barang : " + this.getFoto());
        System.out.println("Harga Barang : " + this.getHarga_barang());
        System.out.println("Status Pelelangan : " + this.getStatus_lelang());
        System.out.println("Status Proses : " + this.getProses_lelang());
    }
}
