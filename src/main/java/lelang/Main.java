package lelang;

import java.sql.Connection;
// import java.sql.Date;
// import java.util.List;

import javax.swing.SwingUtilities;

// import lelang.app.model.Masyarakat;
import lelang.database.DBConnection;
// import lelang.database.MainDAO;
// import lelang.database.DAO.MasyarakatDAO;
import lelang.resources.interfaces.users.HomeScreens;

public class Main {
    public static void main(String[] args) {

        Connection connect = DBConnection.getConnection();

        if (connect != null) {
            try {
                connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Date date = Date.valueOf("2004-10-19");

        // Try Crud
        // @SuppressWarnings("rawtypes")
        // MainDAO data = new MasyarakatDAO();
        // Masyarakat masyarakat = new Masyarakat(
        // 2,
        // 10927,
        // "Muhammad Rifki Anindita",
        // "iki1611",
        // "yeayeay@gmail.com",
        // "password",
        // "djashdjhgadvadqadwq",
        // date);

        // Create Data
        // System.out.println("=== Testing Create ===");
        // data.create(masyarakat);
        // System.out.println("Create new masyarakat: " + masyarakat);

        // 2. Test FindById
        // System.out.println("=== Testing FindById ===");
        // Masyarakat foundMasyarakat = (Masyarakat) data.findById(1);
        // System.out.println("Found masyarakat by ID: " + foundMasyarakat);

        // // 3. Test FindAll
        // System.out.println("=== Testing FindAll ===");
        // @SuppressWarnings("unchecked")
        // List<Masyarakat> masyarakatList = data.findAll();
        // System.out.println("All masyarakat: ");
        // masyarakatList.forEach(System.out::println);

        SwingUtilities.invokeLater(() -> {
            new HomeScreens().setVisible(true);
        });
    }
}