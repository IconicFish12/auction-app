package lelang.app.controller;

import java.util.Map;

import lelang.database.DAO.KategoriDAO;
import lelang.resources.interfaces.admin.barang.KategoriBarang;

public class KategoriController extends Controller{

    // Database / DAO
    private KategoriDAO dataKategori = new KategoriDAO();

    // view
    private KategoriBarang kb = new KategoriBarang();

    @Override
    public void getData() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getData'");
    }

    @Override
    public <Kategori> void createData(Map<String, Object> request, Kategori kategori) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createData'");
    }

    @Override
    public <Kategori> void updateData(Map<String, Object> request, Kategori kategori) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateData'");
    }

    @Override
    public void deleteData(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteData'");
    }
    
}
