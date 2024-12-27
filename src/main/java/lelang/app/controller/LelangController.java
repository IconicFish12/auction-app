package lelang.app.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lelang.app.model.Lelang;
import lelang.app.model.Penawaran;
import lelang.database.DAO.LelangDAO;


public class LelangController extends Controller {
    private LelangDAO lelangDAO = new LelangDAO();
    private PenawaranController penawaranController = new PenawaranController();
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
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updateLelang(Lelang lelang) {
        try {
            lelangDAO.update(lelang);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteLelang(long id) {
        try {
            lelangDAO.delete(id);
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
    
    public List<Penawaran> getHistoryLelangByBarangId(int idBarang) {
        List<Penawaran> allPenawaran = penawaranController.getAllPenawaran();
        List<Penawaran> penawaranList = new ArrayList<>();
        for (Penawaran penawaran : allPenawaran) {
            if (penawaran.getBarangId() == idBarang) {
                penawaranList.add(penawaran);
            }
        }
        if (penawaranList.isEmpty()) {
            return null;
        }
        return penawaranList;
    }

    public List<Penawaran> getHistoryLelangByUserId(int idUser) {
        List<Penawaran> allPenawaran = penawaranController.getAllPenawaran();
        List<Penawaran> penawaranList = new ArrayList<>();
        for (Penawaran penawaran : allPenawaran) {
            if (penawaran.getUserId() == idUser) {
                penawaranList.add(penawaran);
            }
        }
        if (penawaranList.isEmpty()) {
            return null;
        }
        return penawaranList;
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
