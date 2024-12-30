package lelang.resources.interfaces.admin.barang;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;

import lelang.app.model.Barang;
import lelang.app.controller.BarangController;
import lelang.mission.util.InputUtil;
import lelang.app.model.Kategori;
import lelang.app.model.Masyarakat;
import lelang.app.controller.KategoriController;
import lelang.database.DAO.MasyarakatDAO;

public class BarangLelang {
    private static BarangController barangController = new BarangController();
    private static KategoriController kategoriController = new KategoriController();

    public static void showBarangLelangByIdBarang(int idBarang) {
        try {
            Barang barang = barangController.getBarangByIdBarang(idBarang);
            if (barang != null) {
                barang.displayData();
            } else {
                System.out.println("Barang dengan ID " + idBarang + " tidak ditemukan.");
            }
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menampilkan barang: " + e.getMessage());
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
            System.out.println("Terjadi kesalahan saat menampilkan barang: Barang dengan harga " + harga + " tidak ditemukan.");
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menampilkan barang: " + e.getMessage());
        }
    }

    public static void tambahDataBarangLelang() {
        try {
            System.out.println("============= Tambah Data Barang Lelang =============");
            
            // Generate ID Barang Otomatis
            long idBarang = 1;
            LinkedHashMap<Integer, List<Barang>> allBarang = barangController.getAllBarangMap();
            if (!allBarang.isEmpty()) {
                for (List<Barang> barangs : allBarang.values()) {
                    for (Barang barang : barangs) {
                        if (barang.getId() >= idBarang) {
                            idBarang = barang.getId() + 1;
                        }
                    }
                }
            }
            
            System.out.println("ID Barang: " + idBarang);
            System.out.print("Masukkan Nama Barang: ");
            String namaBarang = InputUtil.getStrInput();
            System.out.print("Masukkan Harga Barang: ");
            int hargaBarang = InputUtil.getIntInput();
            System.out.print("Masukkan Deskripsi Barang: ");
            String deskripsiBarang = InputUtil.getStrInput();

            LinkedHashMap<Integer, List<Kategori>> kategoriMap = kategoriController.getAllKategori();
            if (kategoriMap.isEmpty()) {
                System.out.println("Tidak ada kategori yang tersedia.");
                return;
            }
            List<Kategori> listKategori = new ArrayList<>();
            for (List<Kategori> kategoriList : kategoriMap.values()) {
                listKategori.addAll(kategoriList);
            }
            System.out.println("Pilih Kategori:");
            for (int i = 0; i < listKategori.size(); i++) {
                System.out.println((i + 1) + ". " + listKategori.get(i).getNamaKategori());
            }
            System.out.print("Masukkan Pilihan >> ");
            int pilihanKategori = InputUtil.getIntInput();
            if (pilihanKategori < 1 || pilihanKategori > listKategori.size()) {
                System.out.println("Pilihan tidak valid.");
                return;
            }
            Kategori kategori = listKategori.get(pilihanKategori - 1);
            long kategoriId = kategori.getId();

            System.out.print("Masukkan Foto Barang: ");
            String foto = InputUtil.getStrInput();
            System.out.print("Masukkan Status Lelang: ");
            String status_lelang = InputUtil.getStrInput();
            System.out.print("Masukkan Proses Lelang: ");
            String proses_lelang = InputUtil.getStrInput();
            System.out.print("Masukkan ID User: ");
            long userId = InputUtil.getLongInput();

            Masyarakat user = new MasyarakatDAO().findById(userId);
            if (user == null) {
                System.out.println("User dengan ID " + userId + " tidak ditemukan.");
                return;
            }

            // Validasi input foto
            if (foto == null || foto.trim().isEmpty()) {
                System.out.println("Foto barang harus diisi");
                return;
            }

            // Validasi harga
            if (hargaBarang <= 0) {
                System.out.println("Harga barang harus lebih dari 0");
                return;
            }

            // Validasi status
            if (!status_lelang.equalsIgnoreCase("belum") && 
                !status_lelang.equalsIgnoreCase("berlangsung") && 
                !status_lelang.equalsIgnoreCase("selesai")) {
                System.out.println("Status lelang tidak valid");
                return;
            }

            Barang barang = new Barang(idBarang, userId, kategoriId, namaBarang, deskripsiBarang, hargaBarang, foto, status_lelang, proses_lelang, kategori, user);
            
            try {
                barangController.createBarang(barang);
            } catch (Exception e) {
                System.out.println("Error saat menambah barang: " + e.getMessage());
            }
            System.out.println("Data barang berhasil ditambahkan.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void updateDataBarangLelang() {
        try {
            System.out.println("============= Update Data Barang Lelang =============");
            List<Barang> listBarang = barangController.getAllBarang();
            if (listBarang.isEmpty()) {
                System.out.println("Tidak ada barang yang tersedia untuk diupdate.");
                return;
            }
            System.out.println("List Barang:");
            for (int i = 0; i < listBarang.size(); i++) {
                System.out.println((i + 1) + ". " + listBarang.get(i).getNama_barang());
            }
            System.out.print("Masukkan Pilihan >> ");
            int pilihan = InputUtil.getIntInput();
            if (pilihan < 1 || pilihan > listBarang.size()) {
                System.out.println("Pilihan tidak valid.");
                return;
            }
            Barang barang = listBarang.get(pilihan - 1);
            System.out.println("Akan Mengedit Barang: " + barang.getNama_barang());
            System.out.print("Masukkan Nama Barang Baru (default: - untuk tidak mengubah): ");
            String namaBarang = InputUtil.getStrInput();
            if (!namaBarang.equals("-")) {
                barang.setNama_barang(namaBarang);
            }
            System.out.print("Masukkan Harga Barang Baru (default: -1 untuk tidak mengubah) : ");
            int hargaBarang = InputUtil.getIntInput();
            if (hargaBarang != -1) {
                barang.setHarga_barang(hargaBarang);
            }
            System.out.print("Masukkan Deskripsi Barang Baru (default: - untuk tidak mengubah): ");
            String deskripsiBarang = InputUtil.getStrInput();
            if (!deskripsiBarang.equals("-")) {
                 barang.setDeskripsiBarang(deskripsiBarang);
            }
            barangController.updateBarang(barang);
            System.out.println("Data barang berhasil diupdate.");
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat mengupdate data barang: " + e.getMessage());
        }
    }

    public static void hapusDataBarangLelang() {
        try {
            System.out.println("============= Hapus Data Barang Lelang =============");
            List<Barang> listBarang = barangController.getAllBarang();
            if (listBarang.isEmpty()) {
                System.out.println("Tidak ada barang yang tersedia untuk dihapus.");
                return;
            }
            System.out.println("List Barang:");
            for (int i = 0; i < listBarang.size(); i++) {
                System.out.println((i + 1) + ". " + listBarang.get(i).getNama_barang());
            }
            System.out.print("Masukkan Pilihan >> ");
            int pilihan = InputUtil.getIntInput();
            if (pilihan < 1 || pilihan > listBarang.size()) {
                System.out.println("Pilihan tidak valid.");
                return;
            }
            Barang barang = listBarang.get(pilihan - 1);
            barangController.deleteBarang(barang.getId());
            System.out.println("Data barang berhasil dihapus.");
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menghapus data barang: " + e.getMessage());
        }
    }


    public static void showMenu() {
        System.out.println("============= Menu Barang Lelang =============");
        System.out.println("1. Tampilkan Barang Lelang By Harga Barang.");
        System.out.println("2. Tampilkan Barang Lelang By ID Barang.");
        System.out.println("3. Tambah Data Barang Lelang.");
        System.out.println("4. Update Data Barang Lelang.");
        System.out.println("5. Hapus Data Barang Lelang.");
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
                        System.out.print("Masukkan Harga Barang: ");
                        int harga = InputUtil.getIntInput();
                        showBarangLelangByHargaBarang(harga);
                        break;
                    case 2:
                        System.out.print("Masukkan ID Barang: ");
                        int idBarang = InputUtil.getIntInput();
                        showBarangLelangByIdBarang(idBarang);
                        break;
                    case 3:
                        tambahDataBarangLelang();
                        break;
                    case 4:
                        updateDataBarangLelang();
                        break;
                    case 5:
                        hapusDataBarangLelang();
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
