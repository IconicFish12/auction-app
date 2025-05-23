package lelang.database.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import lelang.app.model.Barang;
import lelang.app.model.Lelang;
import lelang.app.model.Masyarakat;
import lelang.app.model.Petugas;
import lelang.database.DBConnection;
import lelang.database.MainDAO;

public class LelangDAO implements MainDAO<Lelang> {

    @Override
    public Lelang findById(long id) {
        Lelang lelang = null;
        String sql = "SELECT * FROM lelang WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    Masyarakat masyarakat = new MasyarakatDAO().findById(rs.getLong("userId"));
                    Petugas petugas = new PetugasDAO().findById(rs.getLong("petugasId"));
                    lelang = new Lelang(
                            rs.getLong("id"),
                            rs.getLong("barangId"),
                            rs.getLong("userId"),
                            rs.getLong("petugasId"),
                            rs.getDate("tgl_mulai"),
                            rs.getDate("tgl_selesai"),
                            rs.getDate("tgl_lelang"),
                            rs.getInt("harga_awal"),
                            rs.getInt("harga_lelang"),
                            masyarakat,
                            petugas);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lelang;
    }

    @Override
    public LinkedHashMap<Integer, List<Lelang>> findAll() {

        LinkedHashMap<Integer, List<Lelang>> lelangList = new LinkedHashMap<>();
        String query = "SELECT * FROM lelang";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(query);
                ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Barang barang = new BarangDAO().findById(rs.getLong("barangId"));
                Masyarakat masyarakat = new MasyarakatDAO().findById(rs.getLong("userId"));
                Petugas petugas = new PetugasDAO().findById(rs.getLong("petugasId"));
                Lelang lelang = new Lelang(
                        rs.getLong("id"),
                        rs.getLong("barangId"),
                        rs.getLong("userId"),
                        rs.getLong("petugasId"),
                        rs.getDate("tgl_mulai"),
                        rs.getDate("tgl_selesai"),
                        rs.getDate("tgl_lelang"),
                        rs.getInt("harga_awal"),
                        rs.getInt("harga_lelang"),
                        masyarakat,
                        petugas);

                lelangList.putIfAbsent((int) lelang.getId(), new ArrayList<>());
                lelangList.get((int) lelang.getId()).add(lelang);
                lelang.addBarangs(new BarangDAO().findById(rs.getLong("barangId")));
                
                barang.addLelangs(lelang);
                masyarakat.addLelangs(lelang);
                petugas.addLelangs(lelang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lelangList;
    }

    @Override
    public void create(Lelang lelang) {
        String query = "INSERT INTO lelang (\"barangId\", \"userId\", \"petugasId\", tgl_mulai, tgl_selesai, tgl_lelang,harga_awal, harga_lelang) VALUES (?,?,?,?,?,?,?,?)";
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {
                PreparedStatement statement = conn.prepareStatement(query);

                statement.setLong(1, lelang.getBarangId());
                statement.setLong(2, lelang.getUserId());
                statement.setLong(3, lelang.getPetugasId());
                statement.setDate(4, new java.sql.Date(lelang.getTgl_mulai().getTime()));
                statement.setDate(5, new java.sql.Date(lelang.getTgl_selesai().getTime()));
                statement.setDate(6, new java.sql.Date(lelang.getTgl_lelang().getTime()));
                statement.setInt(7, lelang.getHarga_awal());
                statement.setInt(8, lelang.getHarga_lelang());

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
    public void update(Lelang lelang) {
String query = "UPDATE lelang SET \"barangId\"= ?, \"userId\" = ?, \"petugasId\" = ?, tgl_mulai = ?, tgl_selesai =?, tgl_lelang =?, harga_awal =?, harga_lelang =? WHERE id = ?";
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {
                PreparedStatement statement = conn.prepareStatement(query);

                statement.setLong(1, lelang.getBarangId());
                statement.setLong(2, lelang.getUserId());
                statement.setLong(3, lelang.getPetugasId());
                statement.setDate(4, new java.sql.Date(lelang.getTgl_mulai().getTime()));
                statement.setDate(5, new java.sql.Date(lelang.getTgl_selesai().getTime()));
                statement.setDate(6, new java.sql.Date(lelang.getTgl_lelang().getTime()));
                statement.setInt(7, lelang.getHarga_awal());
                statement.setInt(8, lelang.getHarga_lelang());
                statement.setLong(9, lelang.getId());

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
        String sql = "DELETE FROM lelang WHERE id = ?";
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {
                PreparedStatement statement = conn.prepareStatement(sql);
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
