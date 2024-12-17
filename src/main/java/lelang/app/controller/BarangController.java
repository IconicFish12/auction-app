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
            System.out.println("Barang berhasil ditambahkan.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updateBarang(Barang barang) {
        try {
            barangDAO.update(barang);
            System.out.println("Barang berhasil diupdate.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteBarang(long id) {
        try {
            barangDAO.delete(id);
            System.out.println("Barang berhasil dihapus.");
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
    
    public List<Barang> getAllBarang() {
        LinkedHashMap<Integer, List<Barang>> dataBarang = barangDAO.findAll();
        List<Barang> allBarang = new ArrayList<>();
        for (List<Barang> barangs : dataBarang.values()) {
            allBarang.addAll(barangs);
        }
        return allBarang;
    }

    @Override
    public <T> void createData(Map<String, Object> request, T entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <T> void updateData(Map<String, Object> request, T entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteData(long id) {
        // TODO Auto-generated method stub
        
    }
}
