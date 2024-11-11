package lelang.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL =  "jdbc:postgresql://localhost:5432/lelang-application";
    private static final String USER =  "postgres";
    private static final String password =  "isyawal161104";

    public static Connection getConnection() {
        Connection conn = null;
        try {conn = DriverManager.getConnection(URL, USER, password);
            
            System.out.println("Database is already Connect");
        } catch (SQLException e) {
            // TODO: handle exception
            System.out.println("Ada Kesalahan dalam koneksi database");
            e.printStackTrace();
        }
        return conn;
    }
}
