package lelang.resources.view.admin.lelang;

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
        System.out.println("=========== Tambah Penawaran Lelang ===========");
        // Tampilkan daftar barang lelang yang dibuka
        System.out.println("Daftar Barang Lelang yang Dibuka:");
        List<Barang> barangList = barangController.getAllBarang();
        PenawaranController penawaranController = new PenawaranController();
        if (barangList == null) {
            System.out.println("Tidak ada barang lelang yang dibuka saat ini.");
            return;
        }
        boolean adaBarangDibuka = false;
        for (Barang barang : barangList) {
            if (barang.getStatus_lelang().equalsIgnoreCase("berlangsung")) {
                adaBarangDibuka = true;
                break;
            }
        }
        if (!adaBarangDibuka) {
            System.out.println("Tidak ada barang lelang yang dibuka saat ini.");
            return;
        }
        for (int i = 0; i < barangList.size(); i++) {
            Barang barang = barangList.get(i);
            if (barang.getStatus_lelang().equalsIgnoreCase("berlangsung")) {
                Penawaran penawaranTertinggi = penawaranController.getPenawaranTertinggiByBarangId(barang.getId());
                if (penawaranTertinggi != null) {
                    System.out.println((i + 1) + ". " + barang.getNama_barang() + " - Harga Awal: " + barang.getHarga_barang() + " - Harga Tertinggi: " + penawaranTertinggi.getHarga_penawaran());
                } else {
                    System.out.println((i + 1) + ". " + barang.getNama_barang() + " - Harga Awal: " + barang.getHarga_barang() + " - Harga Tertinggi: Tidak Ada Penawaran");
                }
            }
        }

        // memilih barang
        System.out.print("Pilih nomor barang yang ingin ditawar: ");
        int pilihanBarang = InputUtil.getIntInput();
        if (pilihanBarang <= 0 || pilihanBarang > barangList.size()) {
            System.out.println("Pilihan barang tidak valid.");
            return;
        }
        Barang barangPilihan = barangList.get(pilihanBarang - 1);

        // Menampilkan daftar user
        System.out.println("\n===== Daftar User =====");
        lelang.app.controller.UserController userController = new lelang.app.controller.UserController();
        List<lelang.app.model.User> users = userController.getAllUser();
        if (users.isEmpty()) {
            System.out.println("Belum ada user terdaftar.");
            return;
        }
        for (int i = 0; i < users.size(); i++) {
            System.out.println((i + 1) + ". " + users.get(i).getNama_lengkap());
        }

        // Memilih user
        System.out.print("Pilih nomor user: ");
        int pilihanUser = InputUtil.getIntInput();
        if (pilihanUser <= 0 || pilihanUser > users.size()) {
            System.out.println("Pilihan user tidak valid.");
            return;
        }
        long userId = users.get(pilihanUser - 1).getId();

        System.out.print("Masukkan harga penawaran Anda: ");
        int hargaPenawaran = InputUtil.getIntInput();


        // Validasi harga penawaran
        Penawaran penawaranTertinggi = penawaranController.getPenawaranTertinggiByBarangId(barangPilihan.getId());
        if (penawaranTertinggi != null && hargaPenawaran <= penawaranTertinggi.getHarga_penawaran()) {
            System.out.println("Harga penawaran harus lebih tinggi dari penawaran sebelumnya");
            return;
        }

        Penawaran penawaran = new Penawaran(0, barangPilihan.getId(), userId, hargaPenawaran);
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
        System.out.println("Penawaran berhasil dihapus.");
    }
}
