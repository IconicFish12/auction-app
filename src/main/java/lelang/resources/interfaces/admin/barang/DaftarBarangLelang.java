package lelang.resources.interfaces.admin.barang;

import java.util.LinkedHashMap;
import java.util.List;
import lelang.app.model.Barang;
import lelang.database.DAO.BarangDAO;
import lelang.mission.util.InputUtil;


// Class untuk show daftar

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
        System.out.println("0. Keluar.");
    }
    public static void menu() {
        boolean keluar = false;
        while (!keluar) {
            showMenu();
            System.out.print(">> Masukkan Inputan: ");
            String input = InputUtil.getStrInput();
            try {
                int pilihan = Integer.parseInt(input);
                switch (pilihan) {
                    case 1:
                        showAllDaftarBarangLelang();
                        break;
                    case 2:
                        System.out.print("Masukkan status lelang: ");
                        String statusLelang = InputUtil.getStrInput();
                        showDaftarBarangLelangByStatus(statusLelang);
                        break;
                    case 3:
                        System.out.print("Masukkan harga minimum: ");
                        int hargaMin = InputUtil.getIntInput();
                        System.out.print("Masukkan harga maksimum: ");
                        int hargaMax = InputUtil.getIntInput();
                        showDaftarBarangLelangByHarga(hargaMin, hargaMax);
                        break;
                    case 0:
                        keluar = true; 
                        break;
                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Masukkan angka!");
            }
        }
    }
}
