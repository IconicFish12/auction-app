package lelang.app.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lelang.app.model.Lelang;
import lelang.database.DAO.LelangDAO;

public class LelangController extends Controller {
    private LelangDAO lelangDAO = new LelangDAO();
    @Override
    public void getData() {
        LinkedHashMap<Integer, List<Lelang>> dataLelang = lelangDAO.findAll();
        for (List<Lelang> lelangs : dataLelang.values()) {
            for (Lelang lelang : lelangs) {
                lelang.displayData();
            }
        }
    }

    public void createLelang(Lelang lelang) {
        try {
            lelangDAO.create(lelang);
            System.out.println("Lelang berhasil ditambahkan.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updateLelang(Lelang lelang) {
        try {
            lelangDAO.update(lelang);
            System.out.println("Lelang berhasil diupdate.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteLelang(long id) {
        try {
            lelangDAO.delete(id);
            System.out.println("Lelang berhasil dihapus.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Lelang getLelangById(long id) {
        Lelang lelang = lelangDAO.findById(id);
        if (lelang == null) {
            return null;
        }
        return lelang;
    }
    

    @Override
    public <T> void createData(Map<String, Object> request, T entity) {
        // Implementasi create data
        if (entity instanceof Lelang) {
            Lelang lelang = (Lelang) entity;
            createLelang(lelang);
        }
    }

    @Override
    public <T> void updateData(Map<String, Object> request, T entity) {
         // Implementasi update data
        if (entity instanceof Lelang) {
            Lelang lelang = (Lelang) entity;
            updateLelang(lelang);
        }
    }

    @Override
    public void deleteData(long id) {
        // Implementasi delete data
        deleteLelang(id);
    }
}
