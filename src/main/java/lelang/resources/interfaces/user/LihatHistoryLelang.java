package lelang.resources.interfaces.user;

import lelang.app.controller.BarangController;
import lelang.app.controller.LelangController;
import lelang.app.model.Barang;
import lelang.app.model.Penawaran;

import java.util.List;
import lelang.mission.util.InputUtil;
import lelang.Main;

public class LihatHistoryLelang {
    private static BarangController barangController = new BarangController();
    private static LelangController lelangController = new LelangController();
    public List<Penawaran> lihatHistoryLelangBarang(int idBarang) {
        return lelangController.getHistoryLelangByBarangId(idBarang);
    }
    public List<Penawaran> lihatHistoryLelangBarangYangDiTawar(int idUser) {
        return lelangController.getHistoryLelangByUserId(idUser);
    }

    public static void menu() {
        boolean keluar = false;
        LihatHistoryLelang lihatHistoryLelang = new LihatHistoryLelang();
        while (!keluar) {
            System.out.println("=========== Menu History Lelang ===========");
            System.out.println("1. Lihat history lelang barang.");
            System.out.println("2. Lihat history lelang barang yang ditawar.");
            System.out.println("0. Keluar");
            System.out.print(">> Masukkan Pilihan: ");
            int pilihan = InputUtil.getIntInput();

            switch (pilihan) {
                case 1:
                    // Tampilkan daftar barang
                    System.out.println("Daftar Barang Lelang:");
                    List<Barang> barangList = barangController.getAllBarang();
                    if (barangList != null) {
                        for (int i = 0; i < barangList.size(); i++) {
                            Barang barang = barangList.get(i);
                            System.out.println((i + 1) + ". " + barang.getNama_barang());
                        }
                    }
                    // Meminta user memilih barang
                    System.out.print("Pilih nomor barang yang ingin dilihat history lelangnya: ");
                    int pilihanBarang = InputUtil.getIntInput();
                    if (barangList != null && pilihanBarang > 0 && pilihanBarang <= barangList.size()) {
                        Barang barangPilihan = barangList.get(pilihanBarang - 1);
                        List<Penawaran> historyLelang = lihatHistoryLelang.lihatHistoryLelangBarang((int) barangPilihan.getId());
                        if (historyLelang == null || historyLelang.isEmpty()) {
                            System.out.println("Tidak ada history lelang yang ditemukan.");
                            break;
                        }
                        for (Penawaran lelang : historyLelang) {
                            lelang.displayData();;
                        }
                    } else {
                        System.out.println("Pilihan tidak valid!");
                    }
                    break;
                case 2:
                    List<Penawaran> historyLelang = lihatHistoryLelang.lihatHistoryLelangBarangYangDiTawar((int) Main.getLoggedInUserId());
                    if (historyLelang == null || historyLelang.isEmpty()) {
                        System.out.println("Tidak ada history lelang yang ditemukan.");
                        break;
                    }
                    for (Penawaran lelang : historyLelang) {
                        lelang.displayData();
                    }
                    break;
                case 0:
                    keluar = true;
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }
}
