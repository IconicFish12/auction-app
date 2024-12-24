package lelang.app.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lelang.app.model.Order;
import lelang.database.DAO.OrderDAO;

public class OrderController extends Controller {
    private OrderDAO orderDAO = new OrderDAO();

    @Override
    public void getData() {
        LinkedHashMap<Integer, List<Order>> dataOrder = orderDAO.findAll();
        if (dataOrder.isEmpty()) {
            System.out.println("Tidak ada data order");
            return;
        }
        for (List<Order> orders : dataOrder.values()) {
            for (Order order : orders) {
                order.displayData();
            }
        }
    }

    public void createOrder(Order order) {
        try {
            orderDAO.create(order);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updateOrder(Order order) {
        try {
            orderDAO.update(order);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteOrder(long id) {
        try {
            orderDAO.delete(id);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Order getOrderById(long id) {
        return orderDAO.findById(id);
    }

    public Order getOrderByLelangId(long lelangId) {
        return orderDAO.findByLelangId(lelangId);
    }

    @Override
    public <T> void createData(Map<String, Object> request, T entity) {
        if (entity instanceof Order) {
            Order order = (Order) entity;
            createOrder(order);
        }
    }

    @Override
    public <T> void updateData(Map<String, Object> request, T entity) {
        if (entity instanceof Order) {
            Order order = (Order) entity;
            updateOrder(order);
        }
    }

    @Override
    public void deleteData(long id) {
        deleteOrder(id);
    }
}
