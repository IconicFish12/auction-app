package lelang.database.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import lelang.app.model.Barang;
import lelang.app.model.Kategori;
import lelang.app.model.Masyarakat;
import lelang.app.model.Order;
import lelang.app.model.Penawaran;
import lelang.database.DBConnection;
import lelang.database.MainDAO;

public class PenawaranDAO implements MainDAO<Penawaran> {

    @Override
    public Penawaran findById(long id) {
        Penawaran penawaran = null;
        String query = "SELECT * FROM penawaran WHERE id = ?";
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setLong(1, id);

                ResultSet rs = statement.executeQuery();

                if (rs.next()) {
                    Barang barang = new BarangDAO().findById(rs.getLong("barangId"));
                    Masyarakat user = new MasyarakatDAO().findById(rs.getLong("userId"));
                    Order order = new OrderDAO().findByPenawaranId(rs.getLong("id"));
                    penawaran = new Penawaran(
                            rs.getLong("id"),
                            rs.getLong("barangId"),
                            rs.getLong("userId"),
                            rs.getInt("harga_penawaran"),
                            user,
                            barang,
                            order);
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

        return penawaran;
    }

    @Override
    public LinkedHashMap<Integer, List<Penawaran>> findAll() {
        LinkedHashMap<Integer, List<Penawaran>> listPenawaran = new LinkedHashMap<>();
        String query = "SELECT * FROM penawaran LEFT JOIN masyarakat ON penawaran.\"userId\" = masyarakat.id  LEFT JOIN barang ON penawaran.\"barangId\" = barang.id";
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {
                PreparedStatement statement = conn.prepareStatement(query);
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    Order order = new OrderDAO().findById(rs.getLong("id"));
                    Kategori kategori = new Kategori(
                            rs.getLong("kategoriId"),
                            rs.getString("nama_kategori"));
                    Masyarakat masyarakat = new Masyarakat(
                            rs.getLong("id"),
                            rs.getInt("nik"),
                            rs.getString("nama_lengkap"),
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("alamat"),
                            rs.getDate("tanggal_lahir"));
                    Barang barang = new Barang(
                            rs.getLong("id"),
                            rs.getLong("userId"),
                            rs.getLong("kategoriId"),
                            rs.getString("nama_barang"),
                            rs.getString("deskripsiBarang"),
                            rs.getInt("hargaBarang"),
                            rs.getString("foto"),
                            rs.getString("status_lelang"),
                            rs.getString("proses"),
                            kategori,
                            masyarakat);
                    Penawaran penawaran = new Penawaran(
                            rs.getLong("id"),
                            rs.getLong("barangId"),
                            rs.getLong("userId"),
                            rs.getInt("harga_penawaran"),
                            masyarakat,
                            barang,
                            order);

                    listPenawaran.putIfAbsent((int) penawaran.getId(), new ArrayList<>());
                    listPenawaran.get((int) penawaran.getId()).add(penawaran);
                    barang.addPenawaran(penawaran);
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
        return listPenawaran;
    }

    @Override
    public void create(Penawaran penawaran) {
        String query = "INSERT INTO penawaran (\"barangId\", \"userId\", harga_penawaran) VALUES (?,?,?)";
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {
                PreparedStatement statement = conn.prepareStatement(query);

                statement.setLong(1, penawaran.getBarangId());
                statement.setLong(2, penawaran.getUserId());
                statement.setInt(3, penawaran.getHarga_penawaran());

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
    public void update(Penawaran penawaran) {
        String query = "UPDATE penawaran set \"barangId\" = ?, \"userId\" = ?, harga_penawaran = ? where id = ?";
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {
                PreparedStatement statement = conn.prepareStatement(query);

                statement.setLong(1, penawaran.getId());
                statement.setLong(2, penawaran.getBarangId());
                statement.setLong(3, penawaran.getUserId());
                statement.setInt(4, penawaran.getHarga_penawaran());

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
