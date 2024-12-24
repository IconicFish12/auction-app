package lelang.app.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lelang.app.model.PengajuanLelang;
import lelang.database.DAO.PengajuanLelangDAO;

public class PengajuanLelangController extends Controller {

    private PengajuanLelangDAO pengajuanLelangDAO = new PengajuanLelangDAO();

    @Override
    public void getData() {
        LinkedHashMap<Integer, List<PengajuanLelang>> dataPengajuan = pengajuanLelangDAO.findAll();
        for (List<PengajuanLelang> pengajuanLelangs : dataPengajuan.values()) {
            for (PengajuanLelang pengajuanLelang : pengajuanLelangs) {
                pengajuanLelang.displayData();
            }
        }
    }

    public void createPengajuanLelang(PengajuanLelang pengajuanLelang) {
        try {
            pengajuanLelangDAO.create(pengajuanLelang);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updatePengajuanLelang(PengajuanLelang pengajuanLelang) {
        try {
            pengajuanLelangDAO.update(pengajuanLelang);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deletePengajuanLelang(long id) {
        try {
            pengajuanLelangDAO.delete(id);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public PengajuanLelang getPengajuanLelangById(long id) {
        PengajuanLelang pengajuanLelang = pengajuanLelangDAO.findById(id);
        if (pengajuanLelang == null) {
            return null;
        }
        return pengajuanLelang;
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
