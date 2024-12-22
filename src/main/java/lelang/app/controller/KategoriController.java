package lelang.app.controller;

import java.util.LinkedHashMap;
import java.util.List;

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
        // TODO Auto-generated method stub
        
    }

    @Override
    public <T> void createData(T entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <T> void updateData(T entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteData(long id) {
        // TODO Auto-generated method stub
        
    }
}
