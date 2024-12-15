package lelang.app.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// - Model / DB Representaion
// import lelang.app.model.Petugas;
import lelang.app.model.Barang;
// - Database Import / DAO
import lelang.database.DAO.BarangDAO;
// import lelang.database.DAO.KategoriDAO;
// import lelang.database.DAO.LelangDAO;
// import lelang.database.DAO.MasyarakatDAO;
// import lelang.database.DAO.PetugasDAO;
// import lelang.resources.interfaces.admin.barang.BarangLelang;
// import lelang.resources.interfaces.admin.barang.DaftarBarangLelang;

public class BarangController extends Controller {

    // database
    private BarangDAO barangDAO = new BarangDAO();
    // private LelangDAO lelang = new LelangDAO();
    // private KategoriDAO kategori = new KategoriDAO();
    // private MasyarakatDAO user = new MasyarakatDAO();
    // private PetugasDAO petugas = new PetugasDAO();

    // // view
    // private BarangLelang bl = new BarangLelang();
    // private DaftarBarangLelang dbl = new DaftarBarangLelang();

    @Override
    public void getData() {
        LinkedHashMap<Integer, List<Barang>> dataBarang = barangDAO.findAll();
        for (List<Barang> barangs : dataBarang.values()) {
            for (Barang barang : barangs) {
                barang.displayData();
            }
        }
    }

    @Override
    public <T> void createData(Map<String, Object> request, T entity) {
        if (!validateRequest(request)) {
            throw new IllegalArgumentException("Input tidak valid.");
        }else if (entity instanceof Barang){
            try {
                long userId = Long.parseLong(request.get("userId").toString());
                long kategoriId = Long.parseLong(request.get("kategoriId").toString());
                String namaBarang = request.get("nama_barang").toString();
                String deskripsiBarang = request.get("deskripsiBarang").toString();
                int hargaBarang = Integer.parseInt(request.get("hargaBarang").toString());
                String foto = request.get("foto").toString();
                String statusLelang = request.get("status_lelang").toString();
                String proses = request.get("proses").toString();
    
                Barang newBarang = new Barang(0, userId, kategoriId, namaBarang, deskripsiBarang, hargaBarang, foto, statusLelang, proses, null, null);
                barangDAO.create(newBarang);
                System.out.println("Barang berhasil ditambahkan.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Format input tidak valid.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private boolean validateRequest(Map<String, Object> request) {
        return request.containsKey("userId") && request.containsKey("kategoriId") &&
               request.containsKey("nama_barang") && request.containsKey("deskripsiBarang") &&
               request.containsKey("hargaBarang") && request.containsKey("foto") &&
               request.containsKey("status_lelang") && request.containsKey("proses");
    }

    @Override
    public <T> void updateData(Map<String, Object> request, T entity) {
        if (!validateRequest(request)) {
            throw new IllegalArgumentException("Input tidak valid.");
        }else if (entity instanceof Barang){
            try {
                long id = Long.parseLong(request.get("id").toString());
                long userId = Long.parseLong(request.get("userId").toString());
                long kategoriId = Long.parseLong(request.get("kategoriId").toString());
                String namaBarang = request.get("nama_barang").toString();
                String deskripsiBarang = request.get("deskripsiBarang").toString();
                int hargaBarang = Integer.parseInt(request.get("hargaBarang").toString());
                String foto = request.get("foto").toString();
                String statusLelang = request.get("status_lelang").toString();
                String proses = request.get("proses").toString();

                Barang barang = (Barang) entity;
                // Update data barang
                barang.setId(id);
                barang.setUserId(userId);
                barang.setKategoriId(kategoriId);
                barang.setNama_barang(namaBarang);
                barang.setDeskripsiBarang(deskripsiBarang);
                barang.setHarga_barang(hargaBarang);
                barang.setFoto(foto);
                barang.setStatus_lelang(statusLelang);
                barang.setProses_lelang(proses);
                // Update barang di database
                barangDAO.update(barang);
                System.out.println("Barang berhasil diupdate.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Format input tidak valid.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    @Override
    public void deleteData(long id) {
        try {
            barangDAO.delete(id);
            System.out.println("Barang berhasil dihapus.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Barang getBarangByIdBarang(long id){
        return barangDAO.findById(id);
    }

    public LinkedHashMap<Integer, List<Barang>> getBarangByHargaBarang(int harga){
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

    // public static void main(String[] args) {
    //     Map<String, Object> requestCreate = new LinkedHashMap<>();
    //     requestCreate.put("userId", 4L);
    //     requestCreate.put("kategoriId", 4L);
    //     requestCreate.put("nama_barang", "Laptop Lenovo");
    //     requestCreate.put("deskripsiBarang", "Laptop Lenovo baru");
    //     requestCreate.put("hargaBarang", 10000000);
    //     requestCreate.put("foto", "lenovo.jpg");
    //     requestCreate.put("status_lelang", "ditutup");
    //     requestCreate.put("proses", "belum");

    //     // Panggil createData dengan objek Barang baru
    //     Barang barang = Barang newBarang = new Barang(0, userId, kategoriId, namaBarang, deskripsiBarang, hargaBarang, foto, statusLelang, proses, null, null);
    //     BarangController abc = new BarangController();
    //     abc.createData(requestCreate, null); 
    // }
}
