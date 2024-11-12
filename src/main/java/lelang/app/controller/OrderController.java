package lelang.app.controller;

public class OrderController extends Controller{

    @Override
    public void getData() {
        System.out.println("Getting data From tabel Order");
    }

    @Override
    public void createData() {
        System.out.println("Creating data Order tabel Order");
    }

    @Override
    public void updateData() {
        System.out.println("Updating data Order From tabel Order");
    }

    @Override
    public void deleteData() {
        System.out.println("Deleting data Order From tabel Order");
    }

}
