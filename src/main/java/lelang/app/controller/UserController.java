package lelang.app.controller;

public class UserController extends Controller  {

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
