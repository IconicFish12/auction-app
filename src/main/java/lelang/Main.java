package lelang;

import java.sql.Connection;

// import javax.swing.SwingUtilities;

import lelang.database.DBConnection;

public class Main {
    public static void main(String[] args) {

        Connection connect = DBConnection.getConnection();

        if (connect != null) {
            try {
                System.out.println("Database Is Connect");

                
                connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }
        }
    }
}