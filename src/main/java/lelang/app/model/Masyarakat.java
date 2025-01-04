package lelang.app.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Masyarakat extends User {
    private int nik;

    // Relation to users
    private LinkedHashMap<Integer, List<Barang>> barangs = new LinkedHashMap<>();
    private LinkedHashMap<Integer, List<Lelang>> lelangs = new LinkedHashMap<>();
    private LinkedHashMap<Integer, List<Penawaran>> penawarans = new LinkedHashMap<>();
    private LinkedHashMap<Integer, List<PengajuanLelang>> pengajuans = new LinkedHashMap<>();


    public Masyarakat(long id, int nik, String nama_lengkap, String username, String email, String password, String alamat, Date tanggal_lahir) {
        super(id, nama_lengkap, username, email, password, alamat, tanggal_lahir);
        this.nik = nik;
    }

    public int getNik() {
        return nik;
    }

    public void setNik(int nik) {
        this.nik = nik;
    }

    // Relatiom Handler

    public void addBarangs(Barang barang) {
        this.barangs.putIfAbsent((int) barang.getUserId(), new ArrayList<>());
        this.barangs.get((int) barang.getUserId()).add(barang);
    }
    

    public LinkedHashMap<Integer, List<Barang>> getBarangs(){
        return barangs;
    }

    public void addLelangs(Lelang lelang){
        this.lelangs.putIfAbsent((int) lelang.getUserId(), new ArrayList<>());
        this.lelangs.get((int) lelang.getUserId()).add(lelang);
    }

    public LinkedHashMap<Integer, List<Lelang>> getLelang(){
        return lelangs;
    }

    public void addPenawaran(Penawaran penawaran) {
        this.penawarans.putIfAbsent((int) penawaran.getBarangId(), new ArrayList<>());
        this.penawarans.get((int) penawaran.getBarangId()).add(penawaran);
    }
    
    public LinkedHashMap<Integer, List<Penawaran>> getPenawarans(){
        return penawarans;
    }

    public void addPengajuan(PengajuanLelang pengajuanLelang){
        this.pengajuans.putIfAbsent((int) pengajuanLelang.getKategoriId(), new ArrayList<>());
        this.pengajuans.get((int) pengajuanLelang.getKategoriId()).add(pengajuanLelang);
    }

    public LinkedHashMap<Integer, List<PengajuanLelang>> getPengajuanLelangs(){
        return pengajuans;
    }

    // display data 

    @Override
    public void displayData() {
        System.out.println(" =========== Data Masyarakat ============");
        System.out.println("ID: " + id);
        System.out.println("NIK: " + nik);
        System.out.println("Nama Lengkap: " + nama_lengkap);
        System.out.println("Username: " + username);
        System.out.println("Email: " + email);
        System.out.println("Alamat: " + alamat);
        System.out.println("Tanggal Lahir: " + tanggal_lahir);
    }


}
