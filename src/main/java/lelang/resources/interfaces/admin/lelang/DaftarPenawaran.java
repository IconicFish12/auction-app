package lelang.resources.interfaces.admin.lelang;

import java.util.List;

import lelang.app.controller.BarangController;
import lelang.app.controller.PenawaranController;
import lelang.app.model.Barang;
import lelang.app.model.Penawaran;
import lelang.mission.util.InputUtil;

public class DaftarPenawaran {
    private static PenawaranController penawaranController = new PenawaranController();
    private static BarangController barangController = new BarangController();

    public static void showMenu() {
        int pilihan;
        do {
            System.out.println("\n===== Menu Daftar Penawaran =====");
            System.out.println("1. Lihat Daftar Penawaran");
            System.out.println("2. Tambah Penawaran");
            System.out.println("3. Update Penawaran");
            System.out.println("4. Hapus Penawaran");
            System.out.println("0. Kembali");
            System.out.print("Pilih menu: ");
            pilihan = InputUtil.getIntInput();

            switch (pilihan) {
                case 1:
                    showDaftarPenawaran();
                    break;
                case 2:
                    createPenawaran();
                    break;
                case 3:
                    updatePenawaran();
                    break;
                case 4:
                    deletePenawaran();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 0);
    }

    private static void showDaftarPenawaran() {
        System.out.println("\n===== Daftar Penawaran =====");
        List<Penawaran> penawarans = penawaranController.getAllPenawaran();
        if (penawarans.isEmpty()) {
            System.out.println("Belum ada penawaran.");
        } else {
            for (Penawaran penawaran : penawarans) {
                penawaran.displayData();
            }
        }
    }

    private static void createPenawaran() {
        System.out.println("\n===== Tambah Penawaran =====");
        
        // Pilih barang
        List<Barang> barangs = barangController.getAllBarang();
        if (barangs.isEmpty()) {
            System.out.println("Tidak ada barang yang tersedia untuk penawaran.");
            return;
        }
        System.out.println("Pilih barang untuk penawaran:");
        for (int i = 0; i < barangs.size(); i++) {
            System.out.println((i + 1) + ". " + barangs.get(i).getNama_barang());
        }
        System.out.print("Pilih nomor barang: ");
        int selectedBarangIndex;
        while (true) {
            selectedBarangIndex = InputUtil.getIntInput() - 1;
             if (selectedBarangIndex >= 0 && selectedBarangIndex < barangs.size()) {
                break;
            }
            System.out.println("Pilihan tidak valid. Silakan masukkan nomor barang yang benar.");
        }
       
        Barang selectedBarang = barangs.get(selectedBarangIndex);

        // Cek status lelang
        if (!selectedBarang.getStatus_lelang().equalsIgnoreCase("berlangsung")) {
            System.out.println("Barang tidak dalam masa lelang");
            return;
        }

        System.out.print("Masukkan ID User: ");
        long userId = InputUtil.getLongInput();
        System.out.print("Masukkan Harga Penawaran: ");
        int hargaPenawaran = InputUtil.getIntInput();

        // Validasi harga penawaran
        Penawaran penawaranTertinggi = penawaranController.getPenawaranTertinggiByBarangId(selectedBarang.getId());
        if (penawaranTertinggi != null && hargaPenawaran <= penawaranTertinggi.getHarga_penawaran()) {
            System.out.println("Harga penawaran harus lebih tinggi dari penawaran sebelumnya");
            return;
        }

        Penawaran penawaran = new Penawaran(0, selectedBarang.getId(), userId, hargaPenawaran);
        penawaranController.createPenawaran(penawaran);
    }

    private static void updatePenawaran() {
        System.out.println("\n===== Update Penawaran =====");
        List<Penawaran> penawarans = penawaranController.getAllPenawaran();
        if (penawarans.isEmpty()) {
            System.out.println("Belum ada penawaran untuk diupdate.");
            return;
        }
        System.out.println("Pilih penawaran yang akan diupdate:");
        for (int i = 0; i < penawarans.size(); i++) {
            System.out.println((i + 1) + ". " + penawarans.get(i).getBarang().getNama_barang() + " - " + penawarans.get(i).getHarga_penawaran());
        }
        System.out.print("Pilih nomor penawaran: ");
         int selectedPenawaranIndex;
        while (true) {
            selectedPenawaranIndex = InputUtil.getIntInput() - 1;
             if (selectedPenawaranIndex >= 0 && selectedPenawaranIndex < penawarans.size()) {
                break;
            }
            System.out.println("Pilihan tidak valid. Silakan masukkan nomor penawaran yang benar.");
        }
        Penawaran selectedPenawaran = penawarans.get(selectedPenawaranIndex);
        System.out.print("Masukkan Harga Penawaran baru: ");
        int hargaPenawaranBaru = InputUtil.getIntInput();

        selectedPenawaran.setHarga_penawaran(hargaPenawaranBaru);
        penawaranController.updatePenawaran(selectedPenawaran);
        
        // Fetch updated penawaran with Barang data
        penawarans = penawaranController.getAllPenawaran();
    }

   private static void deletePenawaran() {
        System.out.println("\n===== Hapus Penawaran =====");
        List<Penawaran> penawarans = penawaranController.getAllPenawaran();
        if (penawarans.isEmpty()) {
            System.out.println("Belum ada penawaran untuk dihapus.");
            return;
        }
        System.out.println("Pilih penawaran yang akan dihapus:");
        for (int i = 0; i < penawarans.size(); i++) {
            System.out.println((i + 1) + ". " + penawarans.get(i).getBarang().getNama_barang() + " - " + penawarans.get(i).getHarga_penawaran());
        }
        System.out.print("Pilih nomor penawaran: ");
        int selectedPenawaranIndex;
        while (true) {
            selectedPenawaranIndex = InputUtil.getIntInput() - 1;
             if (selectedPenawaranIndex >= 0 && selectedPenawaranIndex < penawarans.size()) {
                break;
            }
            System.out.println("Pilihan tidak valid. Silakan masukkan nomor penawaran yang benar.");
        }
        Penawaran selectedPenawaran = penawarans.get(selectedPenawaranIndex);

        penawaranController.deletePenawaran(selectedPenawaran.getId());
    }
    public static void main(String[] args) {
        showMenu();
    }
}
