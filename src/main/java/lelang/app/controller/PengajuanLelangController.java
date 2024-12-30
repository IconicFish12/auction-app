package lelang.app.controller;

import java.util.ArrayList;
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

    public List<PengajuanLelang> getAllPengajuanLelang() {
        LinkedHashMap<Integer, List<PengajuanLelang>> dataPengajuan = pengajuanLelangDAO.findAll();
        List<PengajuanLelang> pengajuanLelangs = new ArrayList<>();
        for (List<PengajuanLelang> pengajuanLelangsList : dataPengajuan.values()) {
            pengajuanLelangs.addAll(pengajuanLelangsList);
        }
        return pengajuanLelangs;
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
        if (entity instanceof PengajuanLelang) {
            createPengajuanLelang((PengajuanLelang) entity);
        }
    }

    @Override
    public <T> void updateData(Map<String, Object> request, T entity) {
        if (entity instanceof PengajuanLelang) {
            updatePengajuanLelang((PengajuanLelang) entity);
        }
    }

    @Override
    public void deleteData(long id) {
        deletePengajuanLelang(id);
    }
}
