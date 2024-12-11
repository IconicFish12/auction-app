package lelang.app.model;

import lelang.database.DAO.BarangDAO;
import lelang.database.DAO.MasyarakatDAO;
import lelang.database.DAO.OrderDAO;

public class Penawaran {
    private long id, barangId, userId;
    private int harga_penawaran;

    // Lazy Load // Relation Table
    private Masyarakat user;
    private Barang barang;
    private Order order;

    // DAO // Database Access
    private static MasyarakatDAO dataUser = new MasyarakatDAO();
    private static BarangDAO dataBarang = new BarangDAO();
    private static OrderDAO dataPesanan = new OrderDAO();

    public Penawaran(long id, long barangId, long userId, int harga_penawaran, 
                        Masyarakat user, Barang barang, Order order) {
        this.id = id;
        this.barangId = barangId;
        this.userId = userId;
        this.harga_penawaran = harga_penawaran;
        this.user = user;
        this.barang = barang;
        this.order = order;
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

    public void setUser(Masyarakat user) {
        this.user = user;
    }

    public void setBarang(Barang barang) {
        this.barang = barang;
    }

    public static void setDataUser(MasyarakatDAO dataUser) {
        Penawaran.dataUser = dataUser;
    }

    public static void setDataBarang(BarangDAO dataBarang) {
        Penawaran.dataBarang = dataBarang;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public static void setDataPesanan(OrderDAO dataPesanan) {
        Penawaran.dataPesanan = dataPesanan;
    }

    // Relation Handler

    public Masyarakat getUser() {
        if (user == null) {
            this.user = dataUser.findById(this.getUserId());
        }
        return user;
    }

    public Barang getBarang() {
        if (barang == null) {
            this.barang = dataBarang.findById(this.getBarangId());
        }

        return barang;
    }

    public Order getOrder(){
        if(order == null){
            this.order = dataPesanan.findByPenawaranId(this.getId());
        }

        return order;
    }

    // display data 

    public void displayData(){
        
    }
}
