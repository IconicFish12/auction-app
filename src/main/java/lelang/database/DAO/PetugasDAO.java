package lelang.database.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import lelang.app.model.Petugas;
import lelang.database.DBConnection;
import lelang.database.MainDAO;

public class PetugasDAO implements MainDAO<Petugas> {

    @Override
    public Petugas findById(long id) {
        Petugas petugas = null;
        String sql = "SELECT * FROM petugas WHERE id = ?";
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setLong(1, id);

                ResultSet rs = statement.executeQuery();

                if (rs.next()) {
                    petugas = new Petugas(
                            rs.getLong("id"),
                            rs.getInt("nip"),
                            rs.getString("nama_lengkap"),
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("alamat"),
                            rs.getDate("tanggal_lahir"),
                            rs.getString("role")
                            );
                }
                System.out.println("Berhasil Melakukan Pengambilan Data");
            } catch (Exception e) {
                System.out.println("Gagal melakukan pengambilan data");
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
        return petugas;
    }

    @Override
    public LinkedHashMap<Integer, List<Petugas>> findAll() {
        LinkedHashMap<Integer, List<Petugas>> petugasList = new LinkedHashMap<>();
        String sql = "SELECT * FROM petugas";
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {
                PreparedStatement statement = conn.prepareStatement(sql);
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    Petugas petugas = new Petugas(
                        rs.getLong("id"),
                        rs.getInt("nip"),
                        rs.getString("nama_lengkap"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("alamat"),
                        rs.getDate("tanggal_lahir"),
                        rs.getString("role")
                        );
                    petugasList.putIfAbsent((int) petugas.getId(), new ArrayList<>());
                    petugasList.get((int) petugas.getId()).add(petugas);
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

        return petugasList;
    }

    @Override
    public void create(Petugas petugas) {
        String sql = "INSERT INTO petugas (nama_lengkap, nip, username, email, password, alamat, tanggal_lahir, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, petugas.getNama_lengkap());
                statement.setInt(2, petugas.getNip());
                statement.setString(3, petugas.getUsername());
                statement.setString(4, petugas.getEmail());
                statement.setString(5, petugas.getPassword());
                statement.setString(6, petugas.getAlamat());
                statement.setDate(7, new java.sql.Date(petugas.getTanggal_lahir().getTime()));
                statement.setString(8, petugas.getRole());

                statement.executeUpdate();
                System.out.println("Data berhasil dimasukkan");
            } catch (Exception e) {
                System.out.println("Data gagal dimasukan");
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
    public void update(Petugas petugas) {
        String sql = "UPDATE petugas SET nama_lengkap = ?, nip = ?, username = ?, email = ?, password = ?, alamat = ?, tanggal_lahir = ?, role = ? WHERE id = ?";
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, petugas.getNama_lengkap());
                statement.setInt(2, petugas.getNip());
                statement.setString(3, petugas.getUsername());
                statement.setString(4, petugas.getEmail());
                statement.setString(5, petugas.getPassword());
                statement.setString(6, petugas.getAlamat());
                statement.setDate(7, new java.sql.Date(petugas.getTanggal_lahir().getTime()));
                statement.setString(8, petugas.getRole());
                statement.setLong(9, petugas.getId());

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
        String sql = "DELETE FROM petugas WHERE id = ?";
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
