package lelang.app.controller;

import lelang.app.model.Masyarakat;
import lelang.database.DAO.MasyarakatDAO;
import lelang.database.DAO.PetugasDAO;

public class UserController extends Controller {

    private MasyarakatDAO masyarakat;
    private PetugasDAO petugas;

    @Override
    public void getData() {
        System.out.println("Getting data From tabel masyrakat dan petugas");
    }

    @Override
    public void createData() {
        System.out.println("Creating data masyrakat dan petugas, tabel masyrakat dan petugas");
    }

    @Override
    public void updateData() {
        System.out.println("Updating data masyrakat dan petugas, From tabel masyrakat dan petugas");
    }

    @Override
    public void deleteData() {
        System.out.println("Deleting data masyrakat dan petugas, From tabel masyrakat dan petugas");
    }
    
}
