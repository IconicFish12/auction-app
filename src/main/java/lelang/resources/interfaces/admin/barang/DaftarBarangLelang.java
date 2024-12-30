package lelang.resources.interfaces.admin.barang;

import java.util.List;

import lelang.app.controller.BarangController;
import lelang.app.model.Barang;
import lelang.mission.util.InputUtil;


// Class untuk show daftar

public class DaftarBarangLelang {
    // Perbaiki akses data melalui controller
    private static BarangController barangController = new BarangController();

    public static void showDaftarBarangLelangByHarga(int hargaMin, int hargaMax) {
        // Validasi rentang harga
        if (hargaMin < 0 || hargaMax < 0) {
            System.out.println("Harga tidak boleh negatif");
            return;
        }
        if (hargaMin > hargaMax) {
            System.out.println("Harga minimum tidak boleh lebih besar dari harga maksimum");
            return;
        }
        barangController.showBarangByRentangHarga(hargaMin, hargaMax);
    }
    public static void showDaftarBarangLelangByStatus(String statusLelang) {
        List<Barang> barangList = barangController.getAllBarang();
        if (barangList != null) {
            for (Barang barang : barangList) {
                if (barang.getStatus_lelang().equalsIgnoreCase(statusLelang)) {
                    barang.displayData();
                }
            }
        }
    }
    public static void showAllDaftarBarangLelang(){
        barangController.getData();
    }
    public static void showMenu(){
        System.out.println("============= Menu Daftar Barang Lelang =============");
        System.out.println("1. Tampilkan Seluruh Daftar Barang Lelang.");
        System.out.println("2. Tampilkan Daftar Barang Lelang By Status.");
        System.out.println("3. Tampilkan Daftar Barang Lelang By Rentang Harga.");
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
