package lelang.database.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import lelang.app.model.Masyarakat;
import lelang.database.DBConnection;
import lelang.database.MainDAO;

public class MasyarakatDAO implements MainDAO<Masyarakat> {

    @Override
    public Masyarakat findById(long id) {
        Masyarakat masyarakat = null;
        String sql = "SELECT * FROM masyarakat WHERE id = ?";
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setLong(1, id);

                ResultSet rs = statement.executeQuery();

                if (rs.next()) {
                    masyarakat = new Masyarakat(
                            rs.getLong("id"),
                            rs.getInt("nik"),
                            rs.getString("nama_lengkap"),
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("alamat"),
                            rs.getDate("tanggal_lahir"));
                }

                System.out.println("Berhasil Melakukan Pengambilan Data");
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

        return masyarakat;
    }

    @Override
    public LinkedHashMap<Integer, List<Masyarakat>> findAll() {
        LinkedHashMap<Integer, List<Masyarakat>> masyarakatList = new LinkedHashMap<>();
        String sql = "SELECT * FROM masyarakat";
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {
                PreparedStatement statement = conn.prepareStatement(sql);
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    Masyarakat masyarakat = new Masyarakat(
                            rs.getLong("id"),
                            rs.getInt("nik"),
                            rs.getString("nama_lengkap"),
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("alamat"),
                            rs.getDate("tanggal_lahir"));
                    masyarakatList.putIfAbsent((int) masyarakat.getId(), new ArrayList<>());
                    masyarakatList.get((int) masyarakat.getId()).add(masyarakat);

                }
                
                System.out.println("Berhasil Melakukan Pengambilan Data");
            } catch (Exception e) {
                System.out.println("Gagal Melakukan Pengambilan Data");
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

        return masyarakatList;
    }

    @Override
    public void create(Masyarakat masyarakat) {
        String sql = "INSERT INTO masyarakat (nama_lengkap, nik, username, email, password, alamat, tanggal_lahir) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, masyarakat.getNama_lengkap());
                statement.setInt(2, masyarakat.getNik());
                statement.setString(3, masyarakat.getUsername());
                statement.setString(4, masyarakat.getEmail());
                statement.setString(5, masyarakat.getPassword());
                statement.setString(6, masyarakat.getAlamat());
                statement.setDate(7, new java.sql.Date(masyarakat.getTanggal_lahir().getTime()));

                statement.executeUpdate();
                System.out.println("Data berhasil dimasukkan");
            } catch (Exception e) {
                System.out.println("Data gagal dimasukkan");
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
    public void update(Masyarakat masyarakat) {
        String sql = "UPDATE masyarakat SET nama_lengkap = ?, username = ?, email = ?, password = ?, alamat = ?, tanggal_lahir = ?, nik = ? WHERE id = ?";
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setLong(1, masyarakat.getId());
                statement.setInt(2, masyarakat.getNik());
                statement.setString(3, masyarakat.getNama_lengkap());
                statement.setString(4, masyarakat.getUsername());
                statement.setString(5, masyarakat.getEmail());
                statement.setString(6, masyarakat.getPassword());
                statement.setString(7, masyarakat.getAlamat());
                statement.setDate(8, new java.sql.Date(masyarakat.getTanggal_lahir().getTime()));

                statement.executeUpdate();
                System.out.println("Data berhasil diubah");

            } catch (Exception e) {
                System.out.println("Data gagal diubah");
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
        String sql = "DELETE FROM masyarakat WHERE id = ?";
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setLong(1, id);
                statement.executeUpdate();

                System.out.println("Data berhasil dihapus");
            } catch (Exception e) {
                System.out.println("Data gagal dihapus");

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

}
