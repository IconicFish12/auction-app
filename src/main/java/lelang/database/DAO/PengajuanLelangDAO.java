package lelang.database.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import lelang.app.model.Kategori;
import lelang.app.model.Masyarakat;
import lelang.app.model.PengajuanLelang;
import lelang.database.DBConnection;
import lelang.database.MainDAO;

public class PengajuanLelangDAO implements MainDAO<PengajuanLelang> {

    @Override
    public PengajuanLelang findById(long id) {
        PengajuanLelang pengajuanLelang = null;

        String query = "SELECT * FROM penawaran WHERE id = ?";
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setLong(1, id);

                ResultSet rs = statement.executeQuery();

                pengajuanLelang = new PengajuanLelang(
                        rs.getLong("id"),
                        rs.getLong("userId"),
                        rs.getLong("kategoriId"),
                        rs.getString("nama_barang"),
                        rs.getString("status_pengajuan"),
                        rs.getInt("harga_lelang"),
                        rs.getInt("harga_barang"),
                        rs.getDate("mulai_lelang"),
                        rs.getDate("Date selesai_lelang"));

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

        return pengajuanLelang;
    }

    @Override
    public LinkedHashMap<Integer, List<PengajuanLelang>> findAll() {
        LinkedHashMap<Integer, List<PengajuanLelang>> pengajuanList = new LinkedHashMap<>();
        String query = "SELECT * FROM \"pengajuanLelang\" LEFT JOIN masyarakat ON \"pengajuanLelang\".\"userId\" = masyarakat.id  LEFT JOIN kategori ON \"pengajuanLelang\".\"kategoriId\" = kategori.id";
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {
                PreparedStatement statement = conn.prepareStatement(query);
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    Masyarakat masyarakat = new MasyarakatDAO().findById(rs.getLong("userId"));
                    Kategori kategori = new KategoriDAO().findById(rs.getLong("kategoriId"));
                    PengajuanLelang pengajuanLelang = new PengajuanLelang(
                            rs.getLong("id"),
                            rs.getLong("userId"),
                            rs.getLong("kategoriId"),
                            rs.getString("nama_barang"),
                            rs.getString("status_pengajuan"),
                            rs.getInt("harga_lelang"),
                            rs.getInt("harga_barang"),
                            rs.getDate("mulai_lelang"),
                            rs.getDate("Date selesai_lelang"));

                    pengajuanList.putIfAbsent((int) pengajuanLelang.getId(), new ArrayList<>());
                    pengajuanList.get((int) pengajuanLelang.getId()).add(pengajuanLelang);

                    masyarakat.addPengajuan(pengajuanLelang);
                    kategori.addPengajuan(pengajuanLelang);
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

        return pengajuanList;
    }

    @Override
    public void create(PengajuanLelang pengajuanLelang) {
        String query = "INSERT INTO \"pengajuanLelang\" (\"userId\", \"kategoriId\", nama_barang, harga_barang, harga_lelang, status_pengajuan, mulai_lelang, selesai_lelang) VALUES (?,?,?,?,?,?,?,?)";
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {
                PreparedStatement statement = conn.prepareStatement(query);

                statement.setLong(1, pengajuanLelang.getUserId());
                statement.setLong(2, pengajuanLelang.getKategoriId());
                statement.setString(3, pengajuanLelang.getNama_barang());
                statement.setString(4, pengajuanLelang.getStatus_pengajuan());
                statement.setInt(5, pengajuanLelang.getHarga_lelang());
                statement.setInt(6, pengajuanLelang.getHarga_barang());
                statement.setDate(7, new java.sql.Date(pengajuanLelang.getMulai_lelang().getTime()));
                statement.setDate(8, new java.sql.Date(pengajuanLelang.getSelesai_lelang().getTime()));

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
    public void update(PengajuanLelang pengajuanLelang) {
        String query = "UPDATE \"pengajuanLelang\" SET \"userId\"=?, \"kategoriId\" =?, nama_barang =?, harga_barang =?, harga_lelang =?, status_pengajuan =?, mulai_lelang =?, selesai_lelang =? WHERE id = ?";
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {
                PreparedStatement statement = conn.prepareStatement(query);

                statement.setLong(1, pengajuanLelang.getId());
                statement.setLong(2, pengajuanLelang.getUserId());
                statement.setLong(3, pengajuanLelang.getKategoriId());
                statement.setString(4, pengajuanLelang.getNama_barang());
                statement.setString(5, pengajuanLelang.getStatus_pengajuan());
                statement.setInt(6, pengajuanLelang.getHarga_lelang());
                statement.setInt(7, pengajuanLelang.getHarga_barang());
                statement.setDate(8, new java.sql.Date(pengajuanLelang.getMulai_lelang().getTime()));
                statement.setDate(9, new java.sql.Date(pengajuanLelang.getSelesai_lelang().getTime()));

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
        String query = "DELETE FROM  \"pengajuanLelang\" WHERE id = ?";
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
