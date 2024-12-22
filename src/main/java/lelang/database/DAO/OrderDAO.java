package lelang.database.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import lelang.app.model.Order;
import lelang.database.DBConnection;
import lelang.database.MainDAO;

public class OrderDAO implements MainDAO<Order> {

    @Override
    public Order findById(long id) {
        Order order = null;
        String query = "SELECT * FROM \"order\" WHERE id = ?";
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setLong(1, id);

                ResultSet rs = statement.executeQuery();

                if (rs.next()) {
                    order = new Order(
                            rs.getLong("id"),
                            rs.getLong("lelangId"),
                            rs.getDate("tgl_pemesanan"),
                            rs.getDate("tgl_pengantaran"),
                            rs.getString("status_pemesanan"),
                            rs.getString("status_pengantaran"),
                            rs.getString("alamat_pengantaran"),
                            rs.getString("status_pembayaran"),
                            rs.getString("metode_pembayaran"),
                            rs.getInt("harga_akhir"));
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            } finally {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }
        }

        return order;
    }

    @Override
    public LinkedHashMap<Integer, List<Order>> findAll() {
        LinkedHashMap<Integer, List<Order>> orderList = new LinkedHashMap<>();
        String query = "SELECT * FROM \"order\" LEFT JOIN lelang ON \"order\".\"lelangId\" = lelang.id";
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {
                PreparedStatement statement = conn.prepareStatement(query);
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    Order order = new Order(
                            rs.getLong("id"),
                            rs.getLong("lelangId"),
                            rs.getDate("tgl_pemesanan"),
                            rs.getDate("tgl_pengantaran"),
                            rs.getString("status_pemesanan"),
                            rs.getString("status_pengantaran"),
                            rs.getString("alamat_pengantaran"),
                            rs.getString("status_pembayaran"),
                            rs.getString("metode_pembayaran"),
                            rs.getInt("harga_akhir"));

                    orderList.putIfAbsent((int) order.getId(), new ArrayList<>());
                    orderList.get((int) order.getId()).add(order);
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }

        }

        return orderList;
    }

    @Override
    public void create(Order order) {
        String query = "INSERT INTO \"order\" (\"lelangId\", tgl_pemesanan, tgl_pengantaran, status_pemesanan, status_pengantaran, alamat_pengantaran, status_pembayaran, metode_pembayaran, harga_akhir) VALUES (?,?,?,?,?,?,?,?,?)";
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {
                PreparedStatement statement = conn.prepareStatement(query);

                statement.setLong(1,order.getLelangId());
                statement.setDate(2, new java.sql.Date(order.getOrderDate().getTime()));
                statement.setDate(3,new java.sql.Date(order.getDeliveryDate().getTime()));
                statement.setString(4, order.getStatus());
                statement.setString(5, order.getShippingStatus());
                statement.setString(6,order.getShippingAddress());
                statement.setString(7,order.getPaymentStatus());
                statement.setString(8,order.getPaymentMethod());
                statement.setInt(9,order.getHarga_akhir());

                statement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            } finally {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    @Override
    public void update(Order order) {
        String query = "UPDATE \"order\" SET \"lelangId\" =?, tgl_pemesanan =?, tgl_pengantaran =?, status_pemesanan =?, status_pengantaran =?, alamat_pengantaran =?, status_pembayaran =?, metode_pembayaran =?, harga_akhir =? WHERE id =?";
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {
                PreparedStatement statement = conn.prepareStatement(query);

                statement.setLong(1,order.getId());
                statement.setLong(2,order.getLelangId());
                statement.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
                statement.setDate(4,new java.sql.Date(order.getDeliveryDate().getTime()));
                statement.setString(5, order.getStatus());
                statement.setString(6, order.getShippingStatus());
                statement.setString(7,order.getShippingAddress());
                statement.setString(8,order.getPaymentStatus());
                statement.setString(9,order.getPaymentMethod());
                statement.setInt(10,order.getHarga_akhir());

                statement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            } finally {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    @Override
    public void delete(long id) {
        String query = "DELETE FROM \"order\" WHERE id = ?";
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {
                PreparedStatement statement = conn.prepareStatement(query);

                statement.setLong(1, id);

                statement.executeUpdate();
            } catch (Exception e) { 
                e.printStackTrace();
                System.out.println(e.getMessage());
            } finally {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public Order findByLelangId(long lelangId) {
        Order order = null;
        String query = "SELECT * FROM \"order\" WHERE \"lelangId\" = ?";
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setLong(1, lelangId);

                ResultSet rs = statement.executeQuery();

                if (rs.next()) {
                    order = new Order(
                            rs.getLong("id"),
                            rs.getLong("lelangId"),
                            rs.getDate("tgl_pemesanan"),
                            rs.getDate("tgl_pengantaran"),
                            rs.getString("status_pemesanan"),
                            rs.getString("status_pengantaran"),
                            rs.getString("alamat_pengantaran"),
                            rs.getString("status_pembayaran"),
                            rs.getString("metode_pembayaran"),
                            rs.getInt("harga_akhir"));
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            } finally {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }
        }

        return order;
    }

}