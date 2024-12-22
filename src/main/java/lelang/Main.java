package lelang;

import java.sql.Connection;
// import java.sql.Date;
// import java.sql.Date;
import java.util.List;
import java.util.LinkedHashMap;

// import javax.swing.SwingUtilities;

import lelang.app.model.Barang;
import lelang.app.model.Kategori;
import lelang.app.model.Lelang;
import lelang.app.model.Masyarakat;
import lelang.app.model.Order;
import lelang.app.model.Penawaran;
import lelang.app.model.PengajuanLelang;
import lelang.app.model.Petugas;
import lelang.database.DBConnection;
import lelang.database.DAO.BarangDAO;
import lelang.database.DAO.KategoriDAO;
import lelang.database.DAO.LelangDAO;
import lelang.database.DAO.MasyarakatDAO;
import lelang.database.DAO.OrderDAO;
import lelang.database.DAO.PenawaranDAO;
import lelang.database.DAO.PengajuanLelangDAO;
import lelang.database.DAO.PetugasDAO;
// import lelang.resources.interfaces.users.HomeScreens;
// import lelang.mission.util.InputUtil;
// import lelang.resources.view.admin.barang.BarangLelang;
// import lelang.resources.view.admin.barang.DaftarBarangLelang;
// import lelang.resources.view.admin.barang.KategoriBarang;

public class Main {
    public static void main(String[] args) {

        Connection connect = DBConnection.getConnection();

        if (connect != null) {
            try {
                System.out.println("Database Is Connect");

                // Try Crud
                BarangDAO dataBarang = new BarangDAO();
                KategoriDAO dataKategori = new KategoriDAO();
                LelangDAO dataLelang = new LelangDAO();
                MasyarakatDAO dataMasyarakat = new MasyarakatDAO();
                PetugasDAO dataPetugas = new PetugasDAO();
                PenawaranDAO dataTawar = new PenawaranDAO();
                OrderDAO dataOrder = new OrderDAO();
                PengajuanLelangDAO dataPengajuan = new PengajuanLelangDAO();

                // Model

                // Masyarakat inputUser = new Masyarakat(4, 10927, "Muhammad Rifki Anindita",
                // "iki1611",
                // "yeayeay@gmail.com", "password", "djashdjhgadvadqadwq",
                // Date.valueOf("2004-10-19"));

                // Kategori inputKategori = new Kategori(4, "Elektronik");

                // Petugas inputPetugas = new Petugas(2, 8911823, "Sherly Mulivia", "sherAway",
                // "sherly8890@gmail.com", "password", "asasasasa" , Date.valueOf("1999-05-24"),
                // "petugas");

                // Barang inputBarang = new Barang(5, 5, 4, "Mesin Cetak ", "jasdjadnkads",
                // 4500000, "kadamsdasmdas", "ditutup",
                // "belum", dataKategori.findById(4), dataMasyarakat.findById(5));

                // Lelang inputLelang =new Lelang(1, 2, 4, 1,
                // Date.valueOf("2024-05-24"), Date.valueOf("2024-09-24"), null,
                // 16000000, 0,
                // dataMasyarakat.findById(4),null);

                // Penawaran inputTawar = new Penawaran(3, 2, 5, 25000000);

                // Create Data

                // System.out.println("=== Testing Create ===");
                // dataKategori.create(inputKategori);
                // dataPetugas.create(inputPetugas);
                // dataMasyarakat.create(inputUser);
                // dataBarang.create(inputBarang);
                // dataLelang.create(inputLelang);
                // dataTawar.create(inputTawar);
                // System.out.println("Create new Data: " + data);

                // Updatae Data

                // dataKategori.update(inputKategori);
                // dataPetugas.update(inputPetugas);
                // dataMasyarakat.update(inputUser);
                // dataBarang.update(inputBarang);
                // dataLelang.update(inputLelang);
                // dataTawar.update(inputTawar);

                // 2. Test FindById / testing
                // System.out.println("=== Testing FindById ===");
                // Barang foundData = dataBarang.findById(2);
                // System.out.println("Found Data by ID: " + foundData.getHarga_barang());

                // 3. Test FindAll / testing

                LinkedHashMap<Integer, List<Kategori>> kategoriList = dataKategori.findAll();
                if (!kategoriList.isEmpty()) {
                    System.out.println("Semua Kategori: ");

                    kategoriList.forEach((id, kategori) -> {
                        kategori.forEach(record -> {
                            record.displayData();
                        });
                        System.out.println("");
                    });
                } else {
                    System.out.println("Data kategori yang diambil kosong");
                }

                LinkedHashMap<Integer, List<Masyarakat>> masyarakatList = dataMasyarakat.findAll();
                if (!masyarakatList.isEmpty()) {
                    System.out.println("Semua Masyarakat / User: ");
                    masyarakatList.forEach((id, masyarakat) -> {
                        masyarakat.forEach(record -> {
                            record.displayData();
                        });
                        System.out.println("");
                    });
                } else {
                    System.out.println("Data user yang diambil kosong");
                }

                System.out.println("");

                LinkedHashMap<Integer, List<Petugas>> petugasList = dataPetugas.findAll();
                if (!petugasList.isEmpty()) {
                    System.out.println("Semua Petugas Lelang: ");
                    petugasList.forEach((id, petugas) -> {
                        System.out.println("Id : " + id);
                        petugas.forEach(record -> {
                            record.displayData();
                        });
                        System.out.println("");
                    });
                } else {
                    System.out.println("Data petugas yang diambil kosong");
                }

                System.out.println("");

                LinkedHashMap<Integer, List<Barang>> barangList = dataBarang.findAll();
                if (!barangList.isEmpty()) {
                    System.out.println("All Barang: ");
                    barangList.forEach((id, barang) -> {
                        barang.forEach(record -> {
                            record.displayData();
                        });
                        System.out.println("");
                    });
                } else {
                    System.out.println("Data barang yang diambil kosong");
                }

                System.out.println("");

                LinkedHashMap<Integer, List<Lelang>> lelangList = dataLelang.findAll();
                if (!lelangList.isEmpty()) {
                    System.out.println("All Barang Lelang: ");
                    lelangList.forEach((id, lelang) -> {
                        lelang.forEach(record -> {
                            record.displayData();
                        });
                    });
                } else {
                    System.out.println("Data Lelang yang diambil kosong");
                }

                System.out.println("");

                LinkedHashMap<Integer, List<Penawaran>> listPenawaran = dataTawar.findAll();
                if (!listPenawaran.isEmpty()) {
                    System.out.println("Semua Penawaran :");
                    listPenawaran.forEach((id, tawar) -> {
                        tawar.forEach(action -> {
                            action.displayData();
                        });
                        System.out.println("");
                    });
                } else {
                    System.out.println("Data Tawaran yang di ambil kosong");
                }

                System.out.println("");

                LinkedHashMap<Integer, List<Order>> listOrder = dataOrder.findAll();
                if (!listOrder.isEmpty()) {
                    System.out.println("Semua Pesanan");
                    listOrder.forEach((id, order) -> {
                        order.forEach(action -> {
                            action.displayData();
                        });
                        System.out.println("");
                    });
                } else {
                    System.out.println("Data Pesanan yang di ambil kosong");
                }

                System.out.println("");

                LinkedHashMap<Integer, List<PengajuanLelang>> listPengajuan = dataPengajuan.findAll();
                if (!listPengajuan.isEmpty()) {
                    System.out.println("Semua pengajuan");
                    listPengajuan.forEach((id, pengajuan) -> {
                        pengajuan.forEach(action -> {
                            action.displayData();
                        });
                        System.out.println("");
                    });
                } else {
                    System.out.println("Data pengajuan yang diambil kosong");
                }

                // System.out.print(
                //         "Menu:\n1. Menu Barang Lelang\n2. Menu Daftar Barang Lelang\n3. Menu Kategori Barang Lelang.\n0. Exit\nMasukkan Inputan >> ");
                // int input = InputUtil.getIntInput();
                // while (input != 0) {
                //     if (input == 1) {
                //         BarangLelang.menu();
                //     } else if (input == 2) {
                //         DaftarBarangLelang.menu();
                //     } else if (input == 3) {
                //         KategoriBarang.menu();
                //     } else if (input == 0) {
                //         break;
                //     }
                //     System.out.print(
                //             "Menu:\n1. Menu Barang Lelang\n2. Menu Daftar Barang Lelang\n3. Menu Kategori Barang Lelang.\n0. Exit\nMasukkan Inputan >> ");
                //     input = InputUtil.getIntInput();
                // }
                //
                // SwingUtilities.invokeLater(() -> {
                // new HomeScreens().setVisible(true);
                // });
                connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }
        }
    }
}