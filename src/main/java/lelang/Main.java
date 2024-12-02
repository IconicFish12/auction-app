package lelang;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;
import java.util.LinkedHashMap;

// import javax.swing.SwingUtilities;

import lelang.app.model.Barang;
import lelang.app.model.Kategori;
import lelang.app.model.Masyarakat;
import lelang.app.model.Petugas;
import lelang.database.DBConnection;
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
                connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Try Crud
        KategoriDAO dataKategori = new KategoriDAO();
        PetugasDAO dataPetugas = new PetugasDAO();
        MasyarakatDAO dataMasyarakat = new MasyarakatDAO();
        BarangDAO dataBarang = new BarangDAO();

        // Masyarakat data =new Masyarakat(5,19871,"Aslam Rizky Fadillah","password",
        //                 "isyawalaliefian@gmail.com","password","djashdjhgadvadqadwq",
        //                 Date.valueOf("2005-09-20"));
        // Kategori data = new Kategori(1, "Peralatan Rumah Tangga");
        // Petugas data =  new Petugas(1, 873128712, "", "", "", "", "" , Date.valueOf("2005-11-19"), "");

        // Kategori kategori = new Kategori(1, "Elektronik" );

        // Create Data
        // System.out.println("=== Testing Create ===");
        // dataKategori.create(data);
        // dataPetugas.create(data);
        // dataMasyarakat.create(data);
        // dataBarang.create(data);
        // System.out.println("Create new Data: " + data);

        // 2. Test FindById
        // System.out.println("=== Testing FindById ===");
        // Kategori foundData = data.findById(1);
        // System.out.println("Found Data by ID: " + foundData);

        // 3. Test FindAll
        System.out.println("=== Testing FindAll ===");
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

        LinkedHashMap<Integer, List<Barang>> barangList = dataBarang.findAll();
        System.out.println("All Barang: ");
        if(!petugasList.isEmpty()){
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
    }
}