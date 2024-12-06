package lelang.app.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Masyarakat extends User {
    private int nik;

    // Relation Barang
    private LinkedHashMap<Integer, List<Barang>> barangs = new LinkedHashMap<>();
    private LinkedHashMap<Integer, List<Lelang>> lelangs = new LinkedHashMap<>();

    public Masyarakat(long id, int nik, String nama_lengkap, String username, String email, String password,
            String alamat, Date tanggal_lahir) {
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

    // behavior

    public void displayData() {
        System.out.println(" =========== Data Masyarakat ============");
        System.out.println("Data ke -" + id);
        System.out.println("NIK : " + nik);
        System.out.println("Nama Lengkap : " + nama_lengkap);
        System.out.println("username : " + username);
        System.out.println("email : " + email);
        System.out.println("Alamat Pengguna : " + alamat);
        System.out.println("Tanggal Lahir : " + tanggal_lahir);
    }


}
