package lelang.database.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import lelang.app.model.Kategori;
import lelang.database.DBConnection;
import lelang.database.MainDAO;

public class KategoriDAO implements MainDAO<Kategori> {

    @Override
    public Kategori findById(long id) {
        Kategori kategori = null;
        String sql = "SELECT * FROM kategori WHERE id = ?";
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setLong(1, id);

                ResultSet rs = statement.executeQuery();

                if (rs.next()) {
                    kategori = new Kategori(
                            rs.getLong("id"),
                            rs.getString("nama_kategori"));
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

        return kategori;
    }

    @Override
    public LinkedHashMap<Integer, List<Kategori>> findAll() {
        LinkedHashMap<Integer, List<Kategori>> kategoriList = new LinkedHashMap<>();
        String query = "SELECT * FROM kategori";
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {
                PreparedStatement statement = conn.prepareStatement(query);
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    Kategori kategori = new Kategori(
                            rs.getLong("id"),
                            rs.getString("nama_kategori"));

                    kategoriList.putIfAbsent((int) kategori.getId(), new ArrayList<>());
                    kategoriList.get((int) kategori.getId()).add(kategori);
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

        return kategoriList;
    }

    @Override
    public void create(Kategori kategori) {
        String query = "INSERT INTO kategori (nama_kategori) VALUES (?)";
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {
                PreparedStatement statement = conn.prepareStatement(query);

                statement.setString(1, kategori.getNamaKategori());

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
    public void update(Kategori kategori) {
        String query = "UPDATE kategori SET nama_kategori = ? WHERE id = ?";
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {
                PreparedStatement statement = conn.prepareStatement(query);

                statement.setLong(1, kategori.getId());
                statement.setString(2, kategori.getNamaKategori());

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
        String query = "DELETE FROM kategori  WHERE id = ?";
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

}