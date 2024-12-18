package lelang.app.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lelang.app.model.Kategori;
import lelang.database.DAO.KategoriDAO;

public class KategoriController extends Controller{

    // Database / DAO
    private KategoriDAO dataKategori = new KategoriDAO();

    public void createKategori(Kategori kategori) {
        try {
            dataKategori.create(kategori);
            System.out.println("Kategori berhasil ditambahkan.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updateKategori(Kategori kategori) {
        try {
            dataKategori.update(kategori);
            System.out.println("Kategori berhasil diupdate.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteKategori(long id) {
        try {
            dataKategori.delete(id);
            System.out.println("Kategori berhasil dihapus.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Kategori getKategoriById(long id) {
        Kategori kategori = dataKategori.findById(id);
        if (kategori == null) {
            return null;
        }
        return kategori;
    }

    public LinkedHashMap<Integer, List<Kategori>> getAllKategori() {
        return dataKategori.findAll();
    }

    @Override
    public void getData() {
        LinkedHashMap<Integer, List<Kategori>> allKategori = dataKategori.findAll();
        if (allKategori.isEmpty()) {
            System.out.println("Tidak ada kategori yang ditemukan.");
        } else {
            for (List<Kategori> kategoris : allKategori.values()) {
                for (Kategori kategori : kategoris) {
                    kategori.displayData();
                }
            }
        }
    }

    @Override
    public <T> void createData(Map<String, Object> request, T entity) {
        if (entity instanceof Kategori) {
            Kategori kategori = (Kategori) entity;
            try {
                dataKategori.create(kategori);
                System.out.println("Kategori berhasil ditambahkan melalui createData.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Error: Invalid entity type for createData.");
        }
    }

    @Override
    public <T> void updateData(Map<String, Object> request, T entity) {
         if (entity instanceof Kategori) {
            Kategori kategori = (Kategori) entity;
            try {
                dataKategori.update(kategori);
                 System.out.println("Kategori berhasil diupdate melalui updateData.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Error: Invalid entity type for updateData.");
        }
    }

    @Override
    public void deleteData(long id) {
        try {
            dataKategori.delete(id);
            System.out.println("Kategori berhasil dihapus melalui deleteData.");
        } catch (Exception e) {
             System.out.println("Error: " + e.getMessage());
        }
    }
}
