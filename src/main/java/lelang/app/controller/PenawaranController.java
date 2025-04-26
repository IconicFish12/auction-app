package lelang.app.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lelang.app.model.Barang;
import lelang.app.model.Penawaran;
import lelang.database.DAO.BarangDAO;
import lelang.database.DAO.PenawaranDAO;

public class PenawaranController extends Controller {

    private PenawaranDAO penawaranDAO = new PenawaranDAO();
    private BarangDAO barangDAO = new BarangDAO();

    @Override
    public void getData() {
        LinkedHashMap<Integer, List<Penawaran>> dataPenawaran = penawaranDAO.findAll();
        for (List<Penawaran> penawarans : dataPenawaran.values()) {
            for (Penawaran penawaran : penawarans) {
                penawaran.displayData();
            }
        }
    }

    public void createPenawaran(Penawaran penawaran) {
        try {
            // Validasi penawaran (contoh: cek harga penawaran lebih tinggi dari penawaran sebelumnya
            BarangController barang = new BarangController();
            int hargaBarang = barang.getBarangByIdBarang(penawaran.getBarangId()).getHarga_barang();
            if (penawaran.getHarga_penawaran() <= hargaBarang) {
                System.out.println("Penawaran gagal ditambahkan. Harga penawaran harus lebih tinggi dari harga barang.");
                return;
            }
            Penawaran penawaranTertinggi = getPenawaranTertinggiByBarangId(penawaran.getBarangId());
            if (penawaranTertinggi != null && penawaran.getHarga_penawaran() <= penawaranTertinggi.getHarga_penawaran()) {
                System.out.println("Penawaran gagal ditambahkan. Harga penawaran harus lebih tinggi dari penawaran sebelumnya.");
                return;
            }
            penawaranDAO.create(penawaran);
            System.out.println("Penawaran berhasil ditambahkan.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updatePenawaran(Penawaran penawaran) {
        try {
            penawaranDAO.update(penawaran);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deletePenawaran(long id) {
        try {
            penawaranDAO.delete(id);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Penawaran getPenawaranById(long id) {
        Penawaran penawaran = penawaranDAO.findById(id);
        if (penawaran == null) {
            return null;
        }
        Barang barang = barangDAO.findById(penawaran.getBarangId());
        penawaran.setBarangId(barang.getId());
        return penawaran;
    }

    public Penawaran getPenawaranTertinggiByBarangId(long barangId) {
        LinkedHashMap<Integer, List<Penawaran>> dataPenawaran = penawaranDAO.findAll();
        Penawaran penawaranTertinggi = null;
        for (List<Penawaran> penawarans : dataPenawaran.values()) {
            for (Penawaran penawaran : penawarans) {
                if (penawaran.getBarangId() == barangId) {
                    if (penawaranTertinggi == null || penawaran.getHarga_penawaran() > penawaranTertinggi.getHarga_penawaran()) {
                        penawaranTertinggi = penawaran;
                    }
                }
            }
        }
        return penawaranTertinggi;
    }

    public List<Penawaran> getAllPenawaran() {
        LinkedHashMap<Integer, List<Penawaran>> dataPenawaran = penawaranDAO.findAll();
        List<Penawaran> allPenawaran = new ArrayList<>();
        for (List<Penawaran> penawarans : dataPenawaran.values()) {
            for(Penawaran penawaran : penawarans){
                Barang barang = barangDAO.findById(penawaran.getBarangId());
                penawaran.setBarangId(barang.getId());
                allPenawaran.add(penawaran);
            }
        }
        return allPenawaran;
    }

    @Override
    public <T> void createData(Map<String, Object> request, T entity) {
        if (entity instanceof Penawaran) {
            Penawaran penawaran = (Penawaran) entity;
            createPenawaran(penawaran);
        }
    }

    @Override
    public <T> void updateData(Map<String, Object> request, T entity) {
        if (entity instanceof Penawaran) {
            Penawaran penawaran = (Penawaran) entity;
            updatePenawaran(penawaran);
        }
    }

    @Override
    public void deleteData(long id) {
        deletePenawaran(id);
    }
}
