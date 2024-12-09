package lelang.app.controller;

import java.util.Map;

// - Model / DB Representaion
// import lelang.app.model.Petugas;
// import lelang.app.model.Barang;

// - Database Import / DAO
import lelang.database.DAO.BarangDAO;
import lelang.database.DAO.KategoriDAO;
import lelang.database.DAO.LelangDAO;
import lelang.database.DAO.MasyarakatDAO;
import lelang.database.DAO.PetugasDAO;
import lelang.resources.interfaces.admin.barang.BarangLelang;
import lelang.resources.interfaces.admin.barang.DaftarBarangLelang;

public class BarangController extends Controller {

    // database 
    private BarangDAO barang = new BarangDAO();
    private LelangDAO lelang = new LelangDAO();
    private KategoriDAO kategori = new KategoriDAO();
    private MasyarakatDAO user = new MasyarakatDAO();
    private PetugasDAO petugas = new PetugasDAO();

    // view
    private BarangLelang bl = new BarangLelang();
    private DaftarBarangLelang dbl = new DaftarBarangLelang();

    @Override
    public void getData() {
        System.out.println("Getting data From tabel barang");
    }

    @Override
    public <Barang> void createData(Map<String, Object> request, Barang barang) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createData'");
    }

    @Override
    public <Barang> void updateData(Map<String, Object> request, Barang barang) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateData'");
    }

    @Override
    public void deleteData(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteData'");
    }

}
