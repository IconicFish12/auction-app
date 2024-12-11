package lelang.app.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Kategori {
    private long id;
    private String namaKategori;

    // Relation to kategori
    private LinkedHashMap<Integer, List<Barang>> barangs = new LinkedHashMap<>();
    private LinkedHashMap<Integer, List<PengajuanLelang>> pengajuans = new LinkedHashMap<>();

    public Kategori(long id, String namaKategori) {
        this.namaKategori = namaKategori;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setID(long id) {
        this.id = id;
    }

    public String getNamaKategori() {
        return namaKategori;
    }

    public void setNamaKategori(String namaKategori) {
        this.namaKategori = namaKategori;
    }

    // Relation Handler

    public void addBarangs(Barang barang) {
        this.barangs.putIfAbsent((int) barang.getKategoriId(), new ArrayList<>());
        this.barangs.get((int) barang.getKategoriId()).add(barang);
    }
    
    public LinkedHashMap<Integer, List<Barang>> getBarangs(){
        return barangs;
    }

    public void addPengajuan(PengajuanLelang pengajuanLelang){
        this.pengajuans.putIfAbsent((int) pengajuanLelang.getKategoriId(), new ArrayList<>());
        this.pengajuans.get((int) pengajuanLelang.getKategoriId()).add(pengajuanLelang);
    }

    public LinkedHashMap<Integer, List<PengajuanLelang>> getPengajuanLelangs(){
        return pengajuans;
    }

    // display data 

    public void displayData(){
        System.out.println(" =========== Data Kategori ============");
        System.out.println("Data ke -" + id);
        System.out.println("Nama Kategori : " + namaKategori);
    }


}
