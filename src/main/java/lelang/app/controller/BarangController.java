package lelang.app.controller;

// import lelang.app.model.Barang;
import lelang.database.DAO.BarangDAO;

public class BarangController extends Controller {

    private BarangDAO barang;


    @Override
    public void getData() {
        System.out.println("Getting data From tabel barang");
    }

    @Override
    public void createData() {
        System.out.println("Creating data barang tabel barang");
    }

    @Override
    public void updateData() {
        System.out.println("Updating data barang From tabel barang");
    }

    @Override
    public void deleteData() {
        System.out.println("Deleting data barang From tabel barang");
    }

}
