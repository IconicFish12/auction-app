package lelang.resources.interfaces.user;

import lelang.app.controller.BarangController;
import lelang.app.controller.PenawaranController;
import lelang.app.model.Barang;
import lelang.app.model.Penawaran;
import lelang.mission.util.InputUtil;
import lelang.Main;

import java.util.List;

public class BuatPenawaran {
    private static PenawaranController penawaranController = new PenawaranController();
    private static BarangController barangController = new BarangController();

    public static void buatPenawaran() {
        System.out.println("=========== Buat Penawaran Lelang ===========");
        // Tampilkan daftar barang lelang yang dibuka
        System.out.println("Daftar Barang Lelang yang Dibuka:");
        List<Barang> barangList = barangController.getAllBarang();
        if (barangList == null) {
            System.out.println("Tidak ada barang lelang yang dibuka saat ini.");
            return;
        }
        boolean adaBarangDibuka = false;
        for (Barang barang : barangList) {
            if (barang.getStatus_lelang().equalsIgnoreCase("dibuka")) {
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
            if (barang.getStatus_lelang().equalsIgnoreCase("dibuka")) {
                System.out.println((i + 1) + ". " + barang.getNama_barang() + " - Harga Awal: " + barang.getHarga_barang());
            }
        }

        // Meminta user memilih barang
        System.out.print("Pilih nomor barang yang ingin ditawar: ");
        int pilihanBarang = InputUtil.getIntInput();
        Barang barangPilihan = barangList.get(pilihanBarang - 1);

        // Meminta user memasukkan harga penawaran
        System.out.print("Masukkan harga penawaran Anda: ");
        int hargaPenawaran = InputUtil.getIntInput();


        // Validasi harga penawaran
        Penawaran penawaranTertinggi = penawaranController.getPenawaranTertinggiByBarangId(barangPilihan.getId());
        if (penawaranTertinggi != null && hargaPenawaran <= penawaranTertinggi.getHarga_penawaran()) {
            System.out.println("Harga penawaran harus lebih tinggi dari penawaran sebelumnya");
            return;
        }

        Penawaran penawaran = new Penawaran(0, barangPilihan.getId(), Main.getLoggedInUserId(), hargaPenawaran);
        penawaranController.createPenawaran(penawaran);
        
        penawaranController.createPenawaran(penawaran);
        System.out.println("Penawaran berhasil dibuat!");
    }
}
