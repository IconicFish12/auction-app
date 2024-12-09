package lelang.app.controller;

import java.util.Map;

import lelang.database.DAO.BarangDAO;
import lelang.database.DAO.LelangDAO;
import lelang.database.DAO.MasyarakatDAO;
import lelang.resources.interfaces.admin.lelang.DaftarLelang;
import lelang.resources.interfaces.admin.lelang.DaftarPenawaran;

public class LelangController extends Controller{

    // Database
    private LelangDAO lelang = new LelangDAO();
    private BarangDAO barang = new BarangDAO();
    private MasyarakatDAO user = new MasyarakatDAO();

    // view
    private DaftarLelang dl = new DaftarLelang();
    private DaftarPenawaran dp = new DaftarPenawaran();
    

    @Override
    public void getData() {
        System.out.println("Getting data From tabel Lelang");
    }

    @Override
    public <Lelang> void createData(Map<String, Object> request, Lelang lelang) {
        System.out.println("Creating data Lelang tabel Lelang");
    }

    @Override
    public <Lelang> void updateData(Map<String, Object> request, Lelang lelang) {
        System.out.println("Updating data Lelang From tabel Lelang");
    }

    @Override
    public void deleteData(long id) {
        System.out.println("Deleting data Lelang From tabel Lelang");
    }

}
