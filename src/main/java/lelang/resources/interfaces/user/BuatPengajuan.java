package lelang.resources.interfaces.user;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lelang.Main;
import lelang.app.controller.KategoriController;
import lelang.app.controller.PengajuanLelangController;
import lelang.app.model.Kategori;
import lelang.app.model.PengajuanLelang;
import lelang.mission.util.InputUtil;

public class BuatPengajuan {
    private static PengajuanLelangController pengajuanLelangController = new PengajuanLelangController();
    private static KategoriController kategoriController = new KategoriController();

    public static void buatPengajuan() {
        System.out.println("========== Buat Pengajuan Lelang ==========");

        // Input Kategori
        Kategori kategori = chooseKategori();
        if (kategori == null) return;
        long kategoriId = kategori.getId();

        System.out.print("Nama Barang: ");
        String namaBarang = InputUtil.getStrInput();

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

        Date mulaiLelang = null;
        Date selesaiLelang = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        while (mulaiLelang == null || selesaiLelang == null) {
            try {
                System.out.print("Mulai Lelang (yyyy-MM-dd): ");
                mulaiLelang = new Date(dateFormat.parse(InputUtil.getStrInput()).getTime());

                System.out.print("Selesai Lelang (yyyy-MM-dd): ");
                selesaiLelang = new Date(dateFormat.parse(InputUtil.getStrInput()).getTime());

                if (selesaiLelang.before(mulaiLelang)) {
                    System.out.println("Tanggal selesai tidak boleh sebelum tanggal mulai.");
                    mulaiLelang = null;
                    selesaiLelang = null;
                }
            } catch (ParseException e) {
                System.out.println("Format tanggal tidak valid. Gunakan format yyyy-MM-dd.");
            }
        }

        // Validasi harga
        if (hargaLelang <= hargaBarang) {
            System.out.println("Harga lelang harus lebih tinggi dari harga barang");
            return;
        }

        PengajuanLelang pengajuanLelang = new PengajuanLelang(
            0,
            Main.getLoggedInUserId(),
            kategoriId,
            namaBarang,
            "diajukan",
            hargaLelang,
            hargaBarang,
            mulaiLelang,
            selesaiLelang
        );

        Map<String, Object> request = new HashMap<>();
        pengajuanLelangController.createData(request, pengajuanLelang);

        System.out.println("Pengajuan lelang berhasil dibuat.");
    }

    private static Kategori chooseKategori() {
            LinkedHashMap<Integer, List<Kategori>> kategoriMap = kategoriController.getAllKategori();
            if (kategoriMap.isEmpty()) {
                System.out.println("Tidak ada kategori yang tersedia.");
                return null;
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
                return null;
            }
            return listKategori.get(pilihanKategori - 1);
    }
}