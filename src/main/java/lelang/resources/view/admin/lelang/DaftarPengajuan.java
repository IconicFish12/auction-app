package lelang.resources.view.admin.lelang;

import lelang.app.controller.PengajuanLelangController;
import lelang.app.model.Kategori;
import lelang.app.model.Masyarakat;
import lelang.app.model.PengajuanLelang;
import lelang.database.DAO.KategoriDAO;
import lelang.database.DAO.MasyarakatDAO;
import lelang.mission.util.InputUtil;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DaftarPengajuan {
    private static PengajuanLelangController pengajuanLelangController = new PengajuanLelangController();
    private static MasyarakatDAO masyarakatDAO = new MasyarakatDAO();
    private static KategoriDAO kategoriDAO = new KategoriDAO();

    public static void view() {
        System.out.println("=== Daftar Pengajuan Lelang ===");
        pengajuanLelangController.getData();
        System.out.println("=== End Daftar Pengajuan Lelang ===");
    }

    public static void create() {
        System.out.println("=== Tambah Pengajuan Lelang ===");

        // Input User
        Masyarakat user = chooseMasyarakat();
        if (user == null) return;
        long userId = user.getId();

        // Input Kategori
        Kategori kategori = chooseKategori();
        if (kategori == null) return;
        long kategoriId = kategori.getId();

        System.out.print("Nama Barang: ");
        String namaBarang = InputUtil.getStrInput();

        Date tglMulai = null;
        Date tglSelesai = null;

        while (tglMulai == null) {
            System.out.print("Tanggal Mulai (yyyy-MM-dd): ");
            String tglMulaiStr = InputUtil.getStrInput();
            try {
                tglMulai = parseDate(tglMulaiStr);
            } catch (ParseException e) {
                System.out.println("Format tanggal tidak valid. Gunakan format yyyy-MM-dd.");
            }
        }

        while (tglSelesai == null) {
            System.out.print("Tanggal Selesai (yyyy-MM-dd): ");
            String tglSelesaiStr = InputUtil.getStrInput();
            try {
                tglSelesai = parseDate(tglSelesaiStr);
                if (tglSelesai.before(tglMulai)) {
                    System.out.println("Tanggal selesai tidak boleh sebelum tanggal mulai.");
                    tglSelesai = null;
                }
            } catch (ParseException e) {
                System.out.println("Format tanggal tidak valid. Gunakan format yyyy-MM-dd.");
            }
        }

        System.out.print("Harga Barang: ");
        int hargaBarang = InputUtil.getIntInput();
        if (hargaBarang <= 0) {
            System.out.println("Harga barang harus lebih dari 0.");
            return;
        }
        System.out.print("Harga Lelang: ");
        int hargaLelang = InputUtil.getIntInput();
         if (hargaLelang <= 0) {
            System.out.println("Harga lelang harus lebih dari 0.");
            return;
        }

        // Validasi tanggal
        if (tglSelesai.before(tglMulai)) {
            System.out.println("Tanggal selesai tidak boleh sebelum tanggal mulai");
            return;
        }

        // Validasi harga
        if (hargaLelang <= hargaBarang) {
            System.out.println("Harga lelang harus lebih tinggi dari harga barang");
            return;
        }

        PengajuanLelang pengajuanLelang = new PengajuanLelang(0, userId, kategoriId, namaBarang, "diajukan", hargaLelang, hargaBarang, tglMulai, tglSelesai);

        Map<String, Object> request = new HashMap<>();
        pengajuanLelangController.createData(request, pengajuanLelang);
        System.out.println("Pengajuan lelang berhasil ditambahkan.");
        System.out.println("=== End Tambah Pengajuan Lelang ===");
    }

    public static void update() {
        System.out.println("=== Update Pengajuan Lelang ===");
        System.out.print("ID Pengajuan Lelang: ");
        long id = InputUtil.getLongInput();
        PengajuanLelang pengajuanLelang = pengajuanLelangController.getPengajuanLelangById(id);
        if (pengajuanLelang == null) {
            System.out.println("Pengajuan lelang tidak ditemukan.");
            return;
        }

        System.out.println("Data Pengajuan Lelang yang akan diupdate:");
        pengajuanLelang.displayData();

        // Input User
        Masyarakat user = chooseMasyarakat();
        if (user == null) return;
        long userId = user.getId();

        // Input Kategori
        Kategori kategori = chooseKategori();
        if (kategori == null) return;
        long kategoriId = kategori.getId();

        System.out.print("Nama Barang: ");
        String namaBarang = InputUtil.getStrInput();

        Date tglMulai = null;
        Date tglSelesai = null;

        while (tglMulai == null) {
            System.out.print("Tanggal Mulai (yyyy-MM-dd): ");
            String tglMulaiStr = InputUtil.getStrInput();
            try {
                tglMulai = parseDate(tglMulaiStr);
            } catch (ParseException e) {
                System.out.println("Format tanggal tidak valid. Gunakan format yyyy-MM-dd.");
            }
        }

        while (tglSelesai == null) {
            System.out.print("Tanggal Selesai (yyyy-MM-dd): ");
            String tglSelesaiStr = InputUtil.getStrInput();
            try {
                tglSelesai = parseDate(tglSelesaiStr);
                 if (tglSelesai.before(tglMulai)) {
                    System.out.println("Tanggal selesai tidak boleh sebelum tanggal mulai.");
                    tglSelesai = null;
                }
            } catch (ParseException e) {
                System.out.println("Format tanggal tidak valid. Gunakan format yyyy-MM-dd.");
            }
        }
        System.out.print("Harga Barang: ");
        int hargaBarang = InputUtil.getIntInput();
         if (hargaBarang <= 0) {
            System.out.println("Harga barang harus lebih dari 0.");
            return;
        }
        System.out.print("Harga Lelang: ");
        int hargaLelang = InputUtil.getIntInput();
         if (hargaLelang <= 0) {
            System.out.println("Harga lelang harus lebih dari 0.");
            return;
        }

        PengajuanLelang updatedPengajuanLelang = new PengajuanLelang(id, userId, kategoriId, namaBarang, "diajukan", hargaLelang, hargaBarang, tglMulai, tglSelesai);
        Map<String, Object> request = new HashMap<>();
        pengajuanLelangController.updateData(request, updatedPengajuanLelang);
        System.out.println("Pengajuan lelang berhasil diupdate.");
        System.out.println("=== End Update Pengajuan Lelang ===");
    }

    public static void delete() {
        System.out.println("=== Hapus Pengajuan Lelang ===");
        System.out.print("ID Pengajuan Lelang: ");
        long id = InputUtil.getLongInput();
        pengajuanLelangController.deleteData(id);
        System.out.println("Pengajuan lelang berhasil dihapus.");
        System.out.println("=== End Hapus Pengajuan Lelang ===");
    }

    public static void showMenu() {
        System.out.println("============= Menu Pengajuan Lelang =============");
        System.out.println("1. Tampilkan Daftar Pengajuan Lelang.");
        System.out.println("2. Tambah Data Pengajuan Lelang.");
        System.out.println("3. Update Data Pengajuan Lelang.");
        System.out.println("4. Hapus Data Pengajuan Lelang.");
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
                        view();
                        break;
                    case 2:
                        create();
                        break;
                    case 3:
                        update();
                        break;
                    case 4:
                        delete();
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
    private static Date parseDate(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return new Date(sdf.parse(dateStr).getTime());
    }

    private static Masyarakat chooseMasyarakat() {
         System.out.println("Pilih User:");
        LinkedHashMap<Integer, List<Masyarakat>> users = masyarakatDAO.findAll();
         if (users.isEmpty()) {
            System.out.println("Tidak ada user yang tersedia.");
            return null;
        }
        int i = 1;
        for (List<Masyarakat> masyarakatList : users.values()) {
            for (Masyarakat masyarakat : masyarakatList) {
                System.out.println(i + ". " + masyarakat.getNama_lengkap() + " (ID: " + masyarakat.getId() + ")");
                i++;
            }
        }
        System.out.print("Pilih nomor user: ");
        int pilihanUser = InputUtil.getIntInput();
         if (pilihanUser <= 0 || pilihanUser > users.size()) {
            System.out.println("Pilihan tidak valid.");
            return null;
        }
        i = 1;
        for (List<Masyarakat> masyarakatList : users.values()) {
            for (Masyarakat masyarakat : masyarakatList) {
                if (i == pilihanUser) {
                    return masyarakat;
                }
                i++;
            }
        }
        return null;
    }

    private static Kategori chooseKategori() {
        System.out.println("Pilih Kategori:");
        LinkedHashMap<Integer, List<Kategori>> kategoris = kategoriDAO.findAll();
        if (kategoris.isEmpty()) {
            System.out.println("Tidak ada kategori yang tersedia.");
            return null;
        }
        int i = 1;
        for (List<Kategori> kategoriList : kategoris.values()) {
            for (Kategori kategori : kategoriList) {
                 System.out.println(i + ". " + kategori.getNamaKategori() + " (ID: " + kategori.getId() + ")");
                i++;
            }
        }
        System.out.print("Pilih nomor kategori: ");
        int pilihanKategori = InputUtil.getIntInput();
        if (pilihanKategori <= 0 || pilihanKategori > kategoris.size()) {
            System.out.println("Pilihan tidak valid.");
            return null;
        }
        i = 1;
        for (List<Kategori> kategoriList : kategoris.values()) {
            for (Kategori kategori : kategoriList) {
                if (i == pilihanKategori) {
                    return kategori;
                }
                i++;
            }
        }
        return null;
    }
    public static void main(String[] args) {
        menu();
    }
}
