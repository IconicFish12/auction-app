package lelang.resources.view.admin.lelang;

import lelang.Main;
import lelang.app.controller.LelangController;
import lelang.app.model.Barang;
import lelang.app.model.Lelang;
import lelang.app.model.Petugas;
import lelang.database.DAO.BarangDAO;
import lelang.database.DAO.PetugasDAO;
import lelang.mission.util.InputUtil;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DaftarLelang {
    private static LelangController lelangController = new LelangController();
    private static BarangDAO barangDAO = new BarangDAO();
    private static PetugasDAO petugasDAO = new PetugasDAO();

    public static void view() {
        System.out.println("=== Daftar Lelang ===");
        lelangController.getData();
        System.out.println("=== End Daftar Lelang ===");
    }

    public static void create() {
        System.out.println("=== Tambah Lelang ===");

        // Tampilkan barang yang status lelangnya "belum"
        System.out.println("Pilih Barang yang akan dilelang:");
        LinkedHashMap<Integer, List<Barang>> barangs = barangDAO.findAll();
        if (barangs.isEmpty()) {
            System.out.println("Tidak ada barang yang tersedia.");
            return;
        }
        int i = 1;
        for (List<Barang> barangList : barangs.values()) {
            for (Barang barang : barangList) {
                if (barang.getStatus_lelang().equalsIgnoreCase("belum")) {
                    System.out.println(i + ". " + barang.getNama_barang() + " (ID: " + barang.getId() + ")");
                    i++;
                }
            }
        }
         if (i == 1) {
            System.out.println("Tidak ada barang dengan status 'belum' yang tersedia.");
            return;
        }
        System.out.print("Pilih nomor barang: ");
        int pilihanBarang = InputUtil.getIntInput();
        if (pilihanBarang <= 0 || pilihanBarang >= i) {
            System.out.println("Pilihan tidak valid.");
            return;
        }
        
        Barang barang = null;
        int j = 1;
        for (List<Barang> barangList : barangs.values()) {
            for (Barang b : barangList) {
                 if (b.getStatus_lelang().equalsIgnoreCase("belum")) {
                    if (j == pilihanBarang) {
                        barang = b;
                        break;
                    }
                    j++;
                }
            }
             if (barang != null) {
                break;
            }
        }
        if (barang == null) {
            System.out.println("Barang tidak ditemukan.");
            return;
        }
        long barangId = barang.getId();
        long userId = barang.getUserId();
        long petugasId = Main.getLoggedInUserId();


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

        System.out.print("Harga Awal: ");
        int hargaAwal = InputUtil.getIntInput();
        if (hargaAwal <= 0) {
            System.out.println("Harga awal harus lebih dari 0.");
            return;
        }
        System.out.print("Harga Lelang: ");
        int hargaLelang = InputUtil.getIntInput();
         if (hargaLelang < 0) {
            System.out.println("Harga lelang harus lebih dari -1. 0 jika belum terlelang");
            return;
        }

        // Validasi tanggal
        if (tglSelesai.before(tglMulai)) {
            System.out.println("Tanggal selesai tidak boleh sebelum tanggal mulai");
            return;
        }

        // Validasi status barang
        if (!barang.getStatus_lelang().equalsIgnoreCase("belum")) {
            System.out.println("Barang ini tidak tersedia untuk dilelang");
            return;
        }
        
        Lelang lelang = new Lelang(0, barangId, userId, petugasId, tglMulai, tglSelesai, null, hargaAwal, hargaLelang, null, null);

        Map<String, Object> request = new HashMap<>();
        lelangController.createData(request, lelang);
        
        // Ubah status barang menjadi "berlangsung"
        barang.setStatus_lelang("berlangsung");
        barang.setProses_lelang("belum");
        barangDAO.update(barang);

        System.out.println("Lelang berhasil ditambahkan.");
        System.out.println("=== End Tambah Lelang ===");
    }

    public static void update() {
        System.out.println("=== Update Lelang ===");
        System.out.print("ID Lelang: ");
        long id = InputUtil.getLongInput();
        Lelang lelang = lelangController.getLelangById(id);
        if (lelang == null) {
            System.out.println("Lelang tidak ditemukan.");
            return;
        }

        System.out.println("Data Lelang yang akan diupdate:");
        lelang.displayData();

        // Input Barang
        Barang barang = chooseBarang();
        if (barang == null) return;
        long barangId = barang.getId();
        long userId = barang.getUserId();

        // Input Petugas
        Petugas petugas = choosePetugas();
        if (petugas == null) return;
        long petugasId = petugas.getId();

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
        System.out.print("Harga Awal: ");
        int hargaAwal = InputUtil.getIntInput();
         if (hargaAwal <= 0) {
            System.out.println("Harga awal harus lebih dari 0.");
            return;
        }
        System.out.print("Harga Lelang: ");
        int hargaLelang = InputUtil.getIntInput();
         if (hargaLelang < 0) {
            System.out.println("Harga lelang harus lebih dari -1. 0 jika belum terlelang");
            return;
        }

        Lelang updatedLelang = new Lelang(id, barangId, userId, petugasId, tglMulai, tglSelesai, null, hargaAwal, hargaLelang, null, null);
        Map<String, Object> request = new HashMap<>();
        lelangController.updateData(request, updatedLelang);

        // Ubah status barang menjadi "berlangsung"
        barang.setStatus_lelang("berlangsung");
        barang.setProses_lelang("belum");
        barangDAO.update(barang);

        System.out.println("Lelang berhasil diupdate.");
        System.out.println("=== End Update Lelang ===");
    }

    public static void delete() {
        System.out.println("=== Hapus Lelang ===");
        System.out.print("ID Lelang: ");
        long id = InputUtil.getLongInput();
        lelangController.deleteData(id);
        System.out.println("Lelang berhasil dihapus.");
        System.out.println("=== End Hapus Lelang ===");
    }

    public static void showMenu() {
        System.out.println("============= Menu Lelang =============");
        System.out.println("1. Tampilkan Daftar Lelang.");
        System.out.println("2. Tambah Data Lelang.");
        System.out.println("3. Update Data Lelang.");
        System.out.println("4. Hapus Data Lelang.");
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

    private static Barang chooseBarang() {
        System.out.println("Pilih Barang:");
        LinkedHashMap<Integer, List<Barang>> barangs = barangDAO.findAll();
        if (barangs.isEmpty()) {
            System.out.println("Tidak ada barang yang tersedia.");
            return null;
        }
        int i = 1;
        for (List<Barang> barangList : barangs.values()) {
            for (Barang barang : barangList) {
                System.out.println(i + ". " + barang.getNama_barang() + " (ID: " + barang.getId() + ")");
                i++;
            }
        }
        System.out.print("Pilih nomor barang: ");
        int pilihanBarang = InputUtil.getIntInput();
        if (pilihanBarang <= 0 || pilihanBarang > barangs.size()) {
            System.out.println("Pilihan tidak valid.");
            return null;
        }
        i = 1;
        for (List<Barang> barangList : barangs.values()) {
            for (Barang barang : barangList) {
                if (i == pilihanBarang) {
                    return barang;
                }
                i++;
            }
        }
        return null;
    }

    private static Petugas choosePetugas() {
        System.out.println("Pilih Petugas:");
        LinkedHashMap<Integer, List<Petugas>> petugass = petugasDAO.findAll();
        if (petugass.isEmpty()) {
            System.out.println("Tidak ada petugas yang tersedia.");
            return null;
        }
        int i = 1;
        for (List<Petugas> petugasList : petugass.values()) {
            for (Petugas petugas : petugasList) {
                 System.out.println(i + ". " + petugas.getNama_lengkap() + " (ID: " + petugas.getId() + ")");
                i++;
            }
        }
        System.out.print("Pilih nomor petugas: ");
        int pilihanPetugas = InputUtil.getIntInput();
        if (pilihanPetugas <= 0 || pilihanPetugas > petugass.size()) {
            System.out.println("Pilihan tidak valid.");
            return null;
        }
        i = 1;
        for (List<Petugas> petugasList : petugass.values()) {
            for (Petugas petugas : petugasList) {
                if (i == pilihanPetugas) {
                    return petugas;
                }
                i++;
            }
        }
        return null;
    }
}
