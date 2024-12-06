package lelang.app.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import lelang.database.DAO.MasyarakatDAO;
import lelang.database.DAO.PetugasDAO;

public class Lelang {
    private long id, barangId, userId, petugasId;
    private Date tgl_mulai, tgl_selesai, tgl_lelang;
    private int harga_awal, harga_lelang;

    // Relation Lazy Load
    // private Barang barang;
    private Masyarakat user;
    private Petugas petugas;

    // add DAO to Lelang model
    private static MasyarakatDAO dataUser = new MasyarakatDAO();
    private static PetugasDAO dataPetugas = new PetugasDAO();

    // handling N to N Relation 
    private LinkedHashMap<Integer, List<Barang>> barangs = new LinkedHashMap<>();
    
    public Lelang(long id, long barangId, long userId, long petugasId, Date tgl_mulai, Date tgl_selesai,
            Date tgl_lelang, int harga_awal, int harga_lelang, Masyarakat user, Petugas petugas) {
        this.id = id;
        this.barangId = barangId;
        this.userId = userId;
        this.petugasId = petugasId;
        this.tgl_mulai = tgl_mulai;
        this.tgl_selesai = tgl_selesai;
        this.tgl_lelang = tgl_lelang;
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
        this.barangs.putIfAbsent((int) barang.getUserId(), new ArrayList<>());
        this.barangs.get((int) barang.getUserId()).add(barang);
    }

    public LinkedHashMap<Integer, List<Barang>> getBarangs(){
        return barangs;
    }

    public Masyarakat getMasyarakat(){
        if(user == null){
            this.user =  dataUser.findById(this.getUserId());
        }

        return user;
    } 

    public Petugas getPetugas(){
        if(petugas == null){
            this.petugas = dataPetugas.findById(this.getPetugasId());
        }

        return petugas;
    }
    
}
