package lelang;

import java.sql.Connection;
// import java.sql.Date;
import java.util.List;
import java.util.LinkedHashMap;

// import javax.swing.SwingUtilities;

import lelang.app.model.Barang;
import lelang.app.model.Kategori;
import lelang.app.model.Masyarakat;
import lelang.app.model.Petugas;
import lelang.database.DBConnection;
import lelang.database.MainDAO;
import lelang.database.DAO.BarangDAO;
import lelang.database.DAO.KategoriDAO;
import lelang.database.DAO.MasyarakatDAO;
import lelang.database.DAO.PetugasDAO;
// import lelang.resources.interfaces.users.HomeScreens;

public class Main {
    public static void main(String[] args) {

        Connection connect = DBConnection.getConnection();

        if (connect != null) {
            try {
                System.out.println("Database Is Connect");
                // Try Crud
                @SuppressWarnings("rawtypes")
                MainDAO dataKategori = new KategoriDAO();
                @SuppressWarnings("rawtypes")
                MainDAO dataPetugas = new PetugasDAO();
                @SuppressWarnings("rawtypes")
                MainDAO dataMasyarakat = new MasyarakatDAO();
                @SuppressWarnings("rawtypes")
                MainDAO dataBarang = new BarangDAO();

                // Masyarakat inputUser = new Masyarakat(4, 10927, "Muhammad Rifki Anindita",
                // "iki1611",
                // "yeayeay@gmail.com", "password", "djashdjhgadvadqadwq",
                // Date.valueOf("2004-10-19"));
                // Kategori inputKategori = new Kategori(4, "Elektronik");
                // Petugas inputPetugas = new Petugas(2, 8911823, "Sherly Mulivia", "sherAway",
                // "sherly8890@gmail.com", "password", "asasasasa" , Date.valueOf("1999-05-24"),
                // "petugas");
                // Barang inputBarang = new Barang(1, 4, 4, "Laptop ROG", "jasdjadnkads",
                // 16000000, "kadamsdasmdas", "ditutup",
                // "belum", dataKategori.findById(4), dataMasyarakat.findById(4));

                // Kategori kategori = new Kategori(1, "Elektronik" );

                // Create Data
                // System.out.println("=== Testing Create ===");
                // dataKategori.create(inputKategori);
                // dataPetugas.create(inputPetugas);
                // dataMasyarakat.create(inputUser);
                // dataBarang.create(inputBarang);
                // System.out.println("Create new Data: " + data);

                // 2. Test FindById
                // System.out.println("=== Testing FindById ===");
                // Kategori foundData = dataKategori.findById(4);
                // System.out.println("Found Data by ID: " + foundData.getNamaKategori());

                // 3. Test FindAll
                System.out.println("=== Testing FindAll ===");
                @SuppressWarnings("unchecked")
                LinkedHashMap<Integer, List<Kategori>> kategoriList = dataKategori.findAll();
                if (!kategoriList.isEmpty()) {
                    System.out.println("All Kategori: ");

                    kategoriList.forEach((id, kategori) -> {
                        kategori.forEach(record -> {
                            record.displayData();
                        });
                        System.out.println("");
                    });
                } else {
                    System.out.println("Data kategori yang diambil kosong");
                }
                
                @SuppressWarnings("unchecked")
                LinkedHashMap<Integer, List<Masyarakat>> masyarakatList = dataMasyarakat.findAll();
                if (!masyarakatList.isEmpty()) {
                    System.out.println("All masyarakat: ");
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

                @SuppressWarnings("unchecked")
                LinkedHashMap<Integer, List<Petugas>> petugasList = dataPetugas.findAll();
                if (!petugasList.isEmpty()) {
                    System.out.println("All Petugas: ");
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

                @SuppressWarnings("unchecked")
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

                // SwingUtilities.invokeLater(() -> {
                // new HomeScreens().setVisible(true);
                // });
                connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}