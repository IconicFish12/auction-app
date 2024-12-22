package lelang.resources.view.admin.barang;

import java.util.List;
import java.util.LinkedHashMap;

import lelang.app.model.Barang;
import lelang.app.model.Kategori;
import lelang.app.controller.BarangController;
import lelang.app.controller.KategoriController;
import lelang.mission.util.InputUtil;

public class KategoriBarang {
    private static KategoriController kategoriController = new KategoriController();
    private static BarangController barangController = new BarangController();

    public static void showKategoriById(int idKategori) {
        try {
            Kategori kategori = kategoriController.getKategoriById(idKategori);
            if (kategori != null) {
                kategori.displayData();
            } else {
                System.out.println("Kategori dengan ID " + idKategori + " tidak ditemukan.");
            }
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menampilkan kategori: " + e.getMessage());
        }
    }

    public static void showAllKategori() {
        try {
            LinkedHashMap<Integer, List<Kategori>> dataKategori = kategoriController.getAllKategori();
            if (dataKategori.isEmpty()) {
                System.out.println("Tidak ada kategori yang ditemukan.");
            } else {
                for (List<Kategori> kategoris : dataKategori.values()) {
                    for (Kategori kategori : kategoris) {
                        kategori.displayData();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menampilkan kategori: " + e.getMessage());
        }
    }

   public static void tambahDataKategori() {
        try {
            System.out.println("============= Tambah Data Kategori =============");
            int idKategori = 0;
            while (true) {
                System.out.print("Masukkan ID Kategori: ");
                String idInput = InputUtil.getStrInput();
                try {
                    idKategori = Integer.parseInt(idInput);
                    Kategori kategoriExist = kategoriController.getKategoriById(idKategori);
                    if (kategoriExist != null) {
                        System.out.println("Kategori dengan ID " + idKategori + " sudah ada.");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("ID Kategori harus berupa angka.");
                }
            }
            System.out.print("Masukkan Nama Kategori: ");
            String namaKategori = InputUtil.getStrInput();
            if (namaKategori.isEmpty()) {
                System.out.println("Nama kategori tidak boleh kosong.");
                return;
            }
            
            Kategori kategori = new Kategori(idKategori, namaKategori);
            kategoriController.createKategori(kategori);
            System.out.println("Data kategori berhasil ditambahkan.");
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menambahkan data kategori: " + e.getMessage());
        }
    }

    public static void updateDataKategori() {
        try {
            System.out.println("============= Update Data Kategori =============");
            showAllKategori();
            System.out.print("Pilih ID Kategori yang akan diupdate: ");
            int idKategori = InputUtil.getIntInput();
            Kategori kategori = kategoriController.getKategoriById(idKategori);
            if (kategori == null) {
                System.out.println("Kategori dengan ID " + idKategori + " tidak ditemukan.");
                return;
            }
            System.out.print("Masukkan Nama Kategori Baru: ");
            String namaKategori = InputUtil.getStrInput();

            kategori.setNamaKategori(namaKategori);
            kategoriController.updateKategori(kategori);
            System.out.println("Data kategori berhasil diupdate.");
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat mengupdate data kategori: " + e.getMessage());
        }
    }

    public static void hapusDataKategori() {
        try {
            System.out.println("============= Hapus Data Kategori =============");
            showAllKategori();
            System.out.print("Pilih ID Kategori yang akan dihapus: ");
            int idKategori = InputUtil.getIntInput();
            Kategori kategori = kategoriController.getKategoriById(idKategori);
            if (kategori == null) {
                System.out.println("Kategori dengan ID " + idKategori + " tidak ditemukan.");
                return;
            }

            // Tambahkan konfirmasi sebelum hapus
            System.out.println("Apakah anda yakin ingin menghapus kategori ini? (y/n)");
            String konfirmasi = InputUtil.getStrInput();
            if (!konfirmasi.equalsIgnoreCase("y")) {
                System.out.println("Penghapusan dibatalkan");
                return;
            }

            // Cek apakah kategori masih memiliki barang
            List<Barang> barangList = barangController.getBarangByKategoriId(idKategori);
            if (!barangList.isEmpty()) {
                System.out.println("Kategori tidak dapat dihapus karena masih memiliki barang");
                return;
            }

            kategoriController.deleteKategori(idKategori);
            System.out.println("Data kategori berhasil dihapus.");
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menghapus data kategori: " + e.getMessage());
        }
    }

    public static void showMenu() {
        System.out.println("============= Menu Kategori Barang =============");
        System.out.println("1. Tampilkan Kategori By ID.");
        System.out.println("2. Tampilkan Semua Kategori.");
        System.out.println("3. Tambah Data Kategori.");
        System.out.println("4. Update Data Kategori.");
        System.out.println("5. Hapus Data Kategori.");
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
                        System.out.print("Masukkan ID Kategori: ");
                        int idKategori = InputUtil.getIntInput();
                        showKategoriById(idKategori);
                        break;
                    case 2:
                        showAllKategori();
                        break;
                    case 3:
                        tambahDataKategori();
                        break;
                    case 4:
                        updateDataKategori();
                        break;
                    case 5:
                        hapusDataKategori();
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
