package lelang.app.model;

import lelang.database.DAO.BarangDAO;
import lelang.database.DAO.MasyarakatDAO;

public class Penawaran {
    private long id, barangId, userId;
    private int harga_penawaran;

    // Lazy Load // Relation Table
    private Masyarakat user;
    private Barang barang;

    // DAO // Database Access
    private static MasyarakatDAO dataUser = new MasyarakatDAO();
    private static BarangDAO dataBarang = new BarangDAO();
    

    public Penawaran(long id, long barangId, long userId, int harga_penawaran) {
        this.id = id;
        this.barangId = barangId;
        this.userId = userId;
        this.harga_penawaran = harga_penawaran;
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

    public int getHarga_penawaran() {
        return harga_penawaran;
    }

    public void setHarga_penawaran(int harga_penawaran) {
        this.harga_penawaran = harga_penawaran;
    }

    public Masyarakat getUser() {
        if (user == null) {
            this.user = dataUser.findById(this.userId);
        }
        return user;
    }

    public Barang getBarang() {
        if (barang == null) {
            this.barang = dataBarang.findById(this.barangId);
        }
        return barang;
    }
    public void displayData() {
        System.out.println(" =========== Data Penawaran Barang Lelang ============");
        System.out.println("User: " + (getUser() != null ? getUser().getNama_lengkap() : "N/A"));
        System.out.println("Barang: " + (getBarang() != null ? getBarang().getNama_barang() : "N/A"));
        System.out.println("Harga Penawaran: " + harga_penawaran);
        System.out.println(" =====================================================");
    }
}
