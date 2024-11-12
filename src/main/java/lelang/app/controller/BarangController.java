package lelang.app.controller;

public class BarangController extends Controller {

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
