package lelang.app.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lelang.app.model.Petugas;
import lelang.database.DAO.PetugasDAO;

interface PetugasHandler<T> {
    void getData();
    void createData(Map<String, Object> request, T petugas);
    void updateData(Map<String, Object> request, T petugas);
    void deleteData(long id);
}

public class PetugasController implements PetugasHandler<Petugas> {

    private PetugasDAO petugasDAO = new PetugasDAO();

    @Override
    public void getData() {
        LinkedHashMap<Integer, List<Petugas>> dataPetugas = petugasDAO.findAll();
        for (List<Petugas> petugasList : dataPetugas.values()) {
            for (Petugas petugas : petugasList) {
                petugas.displayData();
                System.out.println("--------------------");
            }
        }
    }

    @Override
    public void createData(Map<String, Object> request, Petugas petugas) {
        try {
            petugasDAO.create(petugas);
            System.out.println("Petugas berhasil ditambahkan.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void updateData(Map<String, Object> request, Petugas petugas) {
        try {
            petugasDAO.update(petugas);
            System.out.println("Petugas berhasil diupdate.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteData(long id) {
        try {
            petugasDAO.delete(id);
            System.out.println("Petugas berhasil dihapus.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Petugas getPetugasById(long id) {
        Petugas petugas = petugasDAO.findById(id);
        if (petugas == null) {
            return null;
        }
        return petugas;
    }

    public List<Petugas> getAllPetugas() {
        LinkedHashMap<Integer, List<Petugas>> dataPetugas = petugasDAO.findAll();
        List<Petugas> allPetugas = new ArrayList<>();
        for (List<Petugas> petugasList : dataPetugas.values()) {
            allPetugas.addAll(petugasList);
        }
        return allPetugas;
    }
}
