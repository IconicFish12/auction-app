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
import lelang.database.DBConnection;
import lelang.database.MainDAO;

public class BarangDAO implements MainDAO<Barang> {

    @Override
    public Barang findById(long id) {
        Barang barang = null;
        String sql = "SELECT * FROM barang WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    Kategori kategori = new KategoriDAO().findById(rs.getLong("kategoriId"));
                    Masyarakat masyarakat = new MasyarakatDAO().findById(rs.getLong("userId"));
                    barang = new Barang(
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
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return barang;
    }

    @Override
    public LinkedHashMap<Integer, List<Barang>> findAll() {
        LinkedHashMap<Integer, List<Barang>> barangList = new LinkedHashMap<>();
        String sql = " SELECT * FROM barang LEFT JOIN masyarakat ON barang.\"userId\" = masyarakat.id  LEFT JOIN kategori ON barang.\"kategoriId\" = kategori.id";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
                ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
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
                barangList.putIfAbsent((int) barang.getId(), new ArrayList<>());
                barangList.get((int) barang.getId()).add(barang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return barangList;
    }

    @Override
    public void create(Barang barang) {
        String sql = "INSERT INTO barang (\"userId\", \"kategoriId\", nama_barang, \"deskripsiBarang\", \"hargaBarang\", foto, status_lelang, proses) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setLong(1, barang.getUserId());
                statement.setLong(2, barang.getKategoriId());
                statement.setString(3, barang.getNama_barang());
                statement.setString(4, barang.getDeskripsiBarang());
                statement.setInt(5, barang.getHarga_barang());
                statement.setString(6, barang.getFoto());
                statement.setString(7, barang.getStatus_lelang());
                statement.setString(8, barang.getproses_lelang());

                statement.executeUpdate();
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
    }

    @Override
    public void update(Barang barang) {
        String sql = "UPDATE barang SET userId = ?, kategoriId = ?, nama_barang = ?, deskripsiBarang = ?, hargaBarang = ?, foto = ?, status_lelang = ?, proses = ? WHERE id = ?";
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {

                if ("ditutup".equalsIgnoreCase(barang.getStatus_lelang())) {
                    barang.setStatus_lelang("belum");
                    deleteLelangByBarangId(barang.getId());
                }

                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setLong(1, barang.getUserId());
                statement.setLong(2, barang.getKategoriId());
                statement.setString(3, barang.getNama_barang());
                statement.setString(4, barang.getDeskripsiBarang());
                statement.setInt(5, barang.getHarga_barang());
                statement.setString(6, barang.getFoto());
                statement.setString(7, barang.getStatus_lelang());
                statement.setString(8, barang.getproses_lelang());

                statement.executeUpdate();
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
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM barang WHERE id = ?";
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

    private void deleteLelangByBarangId(long barangId) {
        String sql = "DELETE FROM lelang WHERE barang_id = ?";
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setLong(1, barangId);
                statement.executeUpdate();
                System.out.println("Data lelang terkait berhasil dihapus.");
            } catch (Exception e) {
                System.out.println("Error saat menghapus data lelang: " + e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (Exception e) {
                    System.out.println("Error saat menutup koneksi: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }

}
