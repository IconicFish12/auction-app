package lelang.app.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lelang.app.model.Barang;
import lelang.database.DAO.BarangDAO;

public class BarangController extends Controller {

    private BarangDAO barangDAO = new BarangDAO();

    @Override
    public void getData() {
        LinkedHashMap<Integer, List<Barang>> dataBarang = barangDAO.findAll();
        for (List<Barang> barangs : dataBarang.values()) {
            for (Barang barang : barangs) {
                barang.displayData();
            }
        }
    }

    public void createBarang(Barang barang) {
        try {
            barangDAO.create(barang);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updateBarang(Barang barang) {
        try {
            barangDAO.update(barang);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteBarang(long id) {
        try {
            barangDAO.delete(id);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Barang getBarangByIdBarang(long id) {
        Barang barang = barangDAO.findById(id);
        if (barang == null) {
            return null;
        }
        return barang;
    }

    public List<Barang> getBarangByKategoriId(int kategoriId) {

        List<Barang> barangList = new ArrayList<>();
        LinkedHashMap<Integer, List<Barang>> dataBarang = barangDAO.findAll();
        for (List<Barang> barangs : dataBarang.values()) {
            for (Barang barang : barangs) {
                if (barang.getKategoriId() == kategoriId) {
                    barangList.add(barang);
                }
            }
        }
        if (barangList.isEmpty()) {
            return null;
        }
        return barangList;

    }

    public LinkedHashMap<Integer, List<Barang>> getBarangByHargaBarang(int harga) {
        LinkedHashMap<Integer, List<Barang>> dataBarang = barangDAO.findAll();
        LinkedHashMap<Integer, List<Barang>> dataBarangNew = new LinkedHashMap<>();
        for (Integer id : dataBarang.keySet()) {
            List<Barang> barangs = dataBarang.get(id);
            List<Barang> filteredBarangs = new ArrayList<>();
            for (Barang barang : barangs) {
                if (barang.getHarga_barang() == harga) {
                    filteredBarangs.add(barang);
                }
            }
            if (!filteredBarangs.isEmpty()) {
                dataBarangNew.put(id, filteredBarangs);
            }
        }
        return dataBarangNew;
    }

    public void showBarangByRentangHarga(int hargaMin, int hargaMax) {
        LinkedHashMap<Integer, List<Barang>> dataBarang = barangDAO.findAll();
        for (Integer id : dataBarang.keySet()) {
            List<Barang> barangs = dataBarang.get(id);
            for (Barang barang : barangs) {
                if (barang.getHarga_barang() >= hargaMin && barang.getHarga_barang() <= hargaMax) {
                    barang.displayData();
                }
            }
        }
    }
    
    public List<Barang> getAllBarang() {
        LinkedHashMap<Integer, List<Barang>> dataBarang = barangDAO.findAll();
        List<Barang> allBarang = new ArrayList<>();
        for (List<Barang> barangs : dataBarang.values()) {
            allBarang.addAll(barangs);
        }
        return allBarang;
    }

    public LinkedHashMap<Integer, List<Barang>> getAllBarangMap() {
        return barangDAO.findAll();
    }

    @Override
    public <T> void createData(Map<String, Object> request, T entity) {
        if (entity instanceof Barang) {
            createBarang((Barang) entity);
        }
    }

    @Override
    public <T> void updateData(Map<String, Object> request, T entity) {
        if (entity instanceof Barang) {
            updateBarang((Barang) entity);
        }
    }

    @Override
    public void deleteData(long id) {
        deleteBarang(id);
    }
}
