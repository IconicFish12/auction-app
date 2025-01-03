package lelang.app.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import lelang.database.DAO.BarangDAO;
import lelang.database.DAO.MasyarakatDAO;
import lelang.database.DAO.OrderDAO;
import lelang.database.DAO.PetugasDAO;

public class Lelang {
    private long id, barangId, userId, petugasId;
    private Date tgl_mulai, tgl_selesai, tgl_lelang;
    private int harga_awal, harga_lelang;

    // Relation Lazy Load
    private Barang barang;
    private Masyarakat user;
    private Petugas petugas;
    private Order order;

    // DAO // Database Access
    private static MasyarakatDAO dataUser = new MasyarakatDAO();
    private static PetugasDAO dataPetugas = new PetugasDAO();
    private static BarangDAO dataBarang = new BarangDAO();
    private static OrderDAO dataPesanan = new OrderDAO();

    // handling N to N Relation
    private LinkedHashMap<Integer, List<Barang>> barangs = new LinkedHashMap<>();

    public Lelang(long id, long barangId, long userId, long petugasId, Date tgl_mulai, Date tgl_selesai, Date tgl_lelang, int harga_awal, int harga_lelang, Masyarakat user, Petugas petugas) {
        this.id = id;
        this.barangId = barangId;
        this.userId = userId;
        this.petugasId = petugasId;
        this.tgl_mulai = tgl_mulai;
        this.tgl_selesai = tgl_selesai;
        this.tgl_lelang = (tgl_lelang != null) ? tgl_lelang : new Date(System.currentTimeMillis());
        this.harga_awal = harga_awal;
        this.harga_lelang = harga_lelang;
        this.user = user;
        this.petugas = petugas;
    }

    public long getId() {
        return id;
    }

    public long getBarangId() {
        return barangId;
    }

    public long getUserId() {
        return userId;
    }

    public long getPetugasId() {
        return petugasId;
    }

    public Date getTgl_mulai() {
        return tgl_mulai;
    }

    public Date getTgl_selesai() {
        return tgl_selesai;
    }

    public Date getTgl_lelang() {
        return tgl_lelang;
    }

    public int getHarga_awal() {
        return harga_awal;
    }

    public int getHarga_lelang() {
        return harga_lelang;
    }

    // Relation Handlers

    public void addBarangs(Barang barang) {
        this.barangs.putIfAbsent((int) barang.getId(), new ArrayList<>());
        this.barangs.get((int) barang.getId()).add(barang);
    }

    public LinkedHashMap<Integer, List<Barang>> getBarangs() {
        return barangs;
    }

    public Masyarakat getUser() {
        if (user == null) {
            this.user = dataUser.findById(this.getUserId());
        }

        return user;
    }

    public Petugas getPetugas() {
        if (petugas == null) {
            this.petugas = dataPetugas.findById(this.petugasId);
        }
        return petugas;
    }

    public Order getOrder() {
        if (order == null) {
            this.order = dataPesanan.findByLelangId(this.id);
        }
        return order;
    }

    // display data 

    public void displayData() {
        System.out.println(" =========== Data Barang Lelang ============");
        this.getBarangs().forEach((id, record) -> {
            barang = dataBarang.findById(id);
            System.out.println("Data ke -" + this.getId());
            System.out.println("Nama Barang : " + barang.getNama_barang());
            System.out.println("Petugas Pelelang: " + (petugas != null
                    ? this.getPetugas().getNama_lengkap()
                    : "Petugas tidak ditemukan"));
            System.out.println("Pemilik Barang: " +
                    (user != null
                            ? this.getUser().getNama_lengkap()
                            : "Pemilik tidak ditemukan"));
            System.out.println("Deskripsi Barang : " + barang.getDeskripsiBarang());
            System.out.println("Foto Barang : " + barang.getFoto());
            System.out.println("Harga Barang : " + barang.getHarga_barang());
            System.out.println("Status Pelelangan : " + barang.getStatus_lelang());
            System.out.println("Status Proses : " + barang.getProses_lelang());
            System.out.println("waktu Pelelangan dimulai dari : " + this.getTgl_mulai() + " Sampai Tanggal " +
                                this.getTgl_selesai());
            System.out.println("Terlelang dengan harga : " + (this.getHarga_lelang() != 0 ? this.getHarga_lelang() + " pada tanggal " + this.getTgl_lelang() :
                                " Barang Belum terlelang"));
        });
    }

}
