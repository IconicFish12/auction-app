package lelang.resources.interfaces.admin.barang;

import java.util.LinkedHashMap;
import java.util.List;

import lelang.app.model.Barang; 
import lelang.app.controller.BarangController;
import lelang.mission.util.InputUtil;

// Class untuk show 1 item

public class BarangLelang {
    private static BarangController barangController = new BarangController();
    public static void showBarangLelangByIdBarang(int idBarang) {
        try {
            Barang barang = barangController.getBarangByIdBarang(idBarang);
            barang.displayData();
        } catch (Exception e) {
            System.out.println("Barang dengan ID " + idBarang + " tidak ditemukan.");
        }
    }
    public static void showBarangLelangByHargaBarang(int harga) {
        try {
            LinkedHashMap<Integer, List<Barang>> dataBarang = barangController.getBarangByHargaBarang(harga);
            if (dataBarang.isEmpty()) {
                System.out.println("Barang dengan harga " + harga + " tidak ditemukan.");
            } else {
                for (List<Barang> barangs : dataBarang.values()) {
                    for (Barang barang : barangs) {
                        barang.displayData();
                    }
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Barang dengan harga " + harga + " tidak ditemukan.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static void showMenu() {
        System.out.println("============= Menu Barang Lelang =============");
        System.out.println("1. Tampilkan Barang Lelang By Harga Barang.");
        System.out.println("2. Tampilkan Barang Lelang By ID Barang."); 
        System.out.println("0. Keluar.");
        System.out.println("==============================================");
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
                        //showAllDaftarBarangLelang();
                        break;
                    case 2:
                        System.out.print("Masukkan ID Barang: ");
                        int idBarang = InputUtil.getIntInput();
                        showBarangLelangByIdBarang(idBarang); 
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