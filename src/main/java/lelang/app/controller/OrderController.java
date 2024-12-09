package lelang.app.controller;

import java.util.Map;

public class OrderController extends Controller {

    @Override
    public void getData() {
        System.out.println("Getting data From tabel Order");
    }

    @Override
    public <Order> void createData(Map<String, Object> request, Order order) {
        System.out.println("Creating data Order tabel Order");
    }

    @Override
    public <Order> void updateData(Map<String, Object> request, Order order) {
        System.out.println("Updating data Order From tabel Order");
    }

    @Override
    public void deleteData(long id) {
        System.out.println("Deleting data Order From tabel Order");
    }

}
