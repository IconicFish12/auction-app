package lelang.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/lelang";
    private static final String USER = "postgres";
    private static final String password = "admin";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, password);
        } catch (SQLException e) {
            // TODO: handle exception
            System.out.println("Ada Kesalahan dalam koneksi database");
            e.printStackTrace();
        }
        return conn;
    }
}
