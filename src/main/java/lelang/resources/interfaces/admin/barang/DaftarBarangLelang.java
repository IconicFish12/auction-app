package lelang.resources.interfaces.admin.barang;

import java.util.LinkedHashMap;
import java.util.List;
import lelang.app.model.Barang;
import lelang.database.DAO.BarangDAO;

public class DaftarBarangLelang {
    public static void showDaftarBarangLelangByHarga(int hargaMin, int hargaMax) {
        BarangDAO barangDAO = new BarangDAO();
        LinkedHashMap<Integer, List<Barang>> dataBarang = barangDAO.findAll();
        for (Integer id : dataBarang.keySet()) {
            List<Barang> barangs = dataBarang.get(id);
            for (Barang barang : barangs) {
                if (barang.getHarga_barang() >= hargaMin && barang.getHarga_barang() <= hargaMax) {
                    barang.displayData();
                }
            }
        }
    }
    public static void showDaftarBarangLelangByStatus(String statusLelang) {
        BarangDAO barangDAO = new BarangDAO();
        LinkedHashMap<Integer, List<Barang>> dataBarang = barangDAO.findAll();
        for (Integer id : dataBarang.keySet()) {
            List<Barang> barangs = dataBarang.get(id);
            for (Barang barang : barangs) {
                if (barang.getStatus_lelang().equalsIgnoreCase(statusLelang)) {
                    barang.displayData();
                }
            }
        }
    }
    public static void showAllDaftarBarangLelang(){
        BarangDAO barangDAO = new BarangDAO();
        LinkedHashMap<Integer, List<Barang>> dataBarang = barangDAO.findAll();
        for (Integer id : dataBarang.keySet()) {
            List<Barang> barangs = dataBarang.get(id);
            for (Barang barang : barangs) {
                barang.displayData();
            }
        }
    }
    public static void showMenu(){
        System.out.println("============= Menu Daftar Barang Lelang =============");
        System.out.println("1. Tampilkan Seluruh Daftar Barang Lelang.");
        System.out.println("2. Tampilkan Daftar Barang Lelang By Status.");
        System.out.println("3. Tampilkan Daftar Barang Lelang By Rentag Harga.");
    }
    public static void Menu(String[] args) {
        int pilihan = 
    }
}
