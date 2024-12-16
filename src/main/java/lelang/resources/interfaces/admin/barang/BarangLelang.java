package lelang.resources.interfaces.admin.barang;

import java.util.LinkedHashMap;
import java.util.List;

import lelang.app.model.Barang;
import lelang.app.controller.BarangController;
import lelang.app.controller.KategoriController;
import lelang.database.DAO.MasyarakatDAO;

import lelang.mission.util.InputUtil;
import lelang.app.model.Kategori;
import lelang.app.model.Masyarakat;

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
            System.out.print("Masukkan Nama Barang: ");
            String namaBarang = InputUtil.getStrInput();
            System.out.print("Masukkan Harga Barang: ");
            int hargaBarang = InputUtil.getIntInput();
            System.out.print("Masukkan Deskripsi Barang: ");
            String deskripsiBarang = InputUtil.getStrInput();
            System.out.print("Masukkan Kategori Barang: ");
            long kategoriId = InputUtil.getLongInput();
            System.out.print("Masukkan Foto Barang: ");
            String foto = InputUtil.getStrInput();
            System.out.print("Masukkan Status Lelang: ");
            String status_lelang = InputUtil.getStrInput();
            System.out.print("Masukkan Proses Lelang: ");
            String proses_lelang = InputUtil.getStrInput();

            Kategori kategori = kategoriController.getKategoriById((int) kategoriId);            if (kategori == null) {
                System.out.println("Kategori dengan ID " + kategoriId + " tidak ditemukan.");
                return;
            }
            System.out.print("Masukkan ID User: ");
            long userId = InputUtil.getLongInput();
            Masyarakat user = new MasyarakatDAO().findById(userId);
            if (user == null) {
                System.out.println("User dengan ID " + userId + " tidak ditemukan.");
                return;
            }
            Barang barang = new Barang(0, userId, kategoriId, namaBarang, deskripsiBarang, hargaBarang, foto, status_lelang, proses_lelang, kategori, user);
            barangController.createBarang(barang);
            System.out.println("Data barang berhasil ditambahkan.");
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menambahkan data barang: " + e.getMessage());
        }
    }

    public static void updateDataBarangLelang() {
        try {
            System.out.println("============= Update Data Barang Lelang =============");
            System.out.print("Masukkan ID Barang yang akan diupdate: ");
            int idBarang = InputUtil.getIntInput();
            Barang barang = barangController.getBarangByIdBarang(idBarang);
            if (barang == null) {
                System.out.println("Barang dengan ID " + idBarang + " tidak ditemukan.");
                return;
            }
            System.out.print("Masukkan Nama Barang Baru: ");
            String namaBarang = InputUtil.getStrInput();
            System.out.print("Masukkan Harga Barang Baru: ");
            int hargaBarang = InputUtil.getIntInput();
            System.out.print("Masukkan Deskripsi Barang Baru: ");
            String deskripsiBarang = InputUtil.getStrInput();

            barang.setNama_barang(namaBarang);
            barang.setHarga_barang(hargaBarang);
            barang.setDeskripsiBarang(deskripsiBarang);
            barangController.updateBarang(barang);
            System.out.println("Data barang berhasil diupdate.");
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat mengupdate data barang: " + e.getMessage());
        }
    }

    public static void hapusDataBarangLelang() {
        try {
            System.out.println("============= Hapus Data Barang Lelang =============");
            System.out.print("Masukkan ID Barang yang akan dihapus: ");
            int idBarang = InputUtil.getIntInput();
            Barang barang = barangController.getBarangByIdBarang(idBarang);
             if (barang == null) {
                System.out.println("Barang dengan ID " + idBarang + " tidak ditemukan.");
                return;
            }
            barangController.deleteBarang(idBarang);
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
