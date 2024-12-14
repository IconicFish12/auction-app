package lelang.app.model;

import java.sql.Date;

import lelang.database.DAO.BarangDAO;
import lelang.database.DAO.LelangDAO;

public class Order {
    private long id, lelangId;
    private Date orderDate, deliveryDate;
    private String status, shippingAddress, shippingStatus, paymentStatus, paymentMethod;
    private int harga_akhir;

    // Lazy Load // Relation Table
    private Lelang lelang;
    private Barang barang; // to access data from barang

    // DAO // Database Access
    private static LelangDAO dataLelang = new LelangDAO();
    private static BarangDAO dataBarang = new BarangDAO(); // to access data from barang

    public Order(long id, long lelangId, Date orderDate, Date deliveryDate, String status, String shippingAddress,
            String shippingStatus, String paymentStatus, String paymentMethod, int harga_akhir) {
        this.id = id;
        this.lelangId = lelangId;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.shippingAddress = shippingAddress;
        this.shippingStatus = shippingStatus;
        this.paymentStatus = paymentStatus;
        this.paymentMethod = paymentMethod;
        this.harga_akhir = harga_akhir;
    }

    // Getter & Setter

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLelangId() {
        return lelangId;
    }

    public void setLelangId(long lelangId) {
        this.lelangId = lelangId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(String shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getHarga_akhir() {
        return harga_akhir;
    }

    public void setHarga_akhir(int harga_akhir) {
        this.harga_akhir = harga_akhir;
    }

    public void setLelang(Lelang lelang) {
        this.lelang = lelang;
    }

    public static LelangDAO getDataTawar() {
        return dataLelang;
    }

    public static void setDataTawar(LelangDAO dataLelang) {
        Order.dataLelang = dataLelang;
    }

    public Lelang getLelang() {
        if (lelang == null) {
            this.lelang = dataLelang.findById(this.getLelangId());
        }

        return lelang;
    }

    public void displayData() {
        System.out.println(" =========== Data Pemesanan Barang Lelang ============");
        this.getLelang().getBarangs().forEach((id, action) -> {
            barang = dataBarang.findById(id);
            System.out.println("Harga Akhir: " + harga_akhir);
            System.out.println("Tanggal Order: " + orderDate);
            System.out.println("Nama Barang: " + barang.getNama_barang());
            System.out.println("Harga Barang: " + barang.getHarga_barang());
            System.out.println("Tanggal Pemesanan: " + this.getShippingAddress());
            System.out.println("Metode Pembayaran: " + this.getPaymentMethod());
            System.out.println("Status Pembayaran: " + ("sudah".equals(this.getPaymentStatus()) ? "Sudah Dibayar" : "Belum Dibayar"));

        });
    }
}
