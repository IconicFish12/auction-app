package lelang.app.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lelang.app.model.User;
import lelang.database.DAO.UserDAO;

interface UserHandler<T> {
    void getData();
    void createData(Map<String, Object> request, T user);
    void updateData(Map<String, Object> request, T user);
    void deleteData(long id);
}

public class UserController implements UserHandler<User> {

    private UserDAO userDAO = new UserDAO();

    @Override
    public void getData() {
        LinkedHashMap<Integer, List<User>> dataUser = userDAO.findAllUser();
        for (List<User> userList : dataUser.values()) {
            for (User user : userList) {
                System.out.println("ID: " + user.getId());
                System.out.println("Nama Lengkap: " + user.getNama_lengkap());
                System.out.println("Username: " + user.getUsername());
                System.out.println("Email: " + user.getEmail());
                 System.out.println("Alamat: " + user.getAlamat());
                System.out.println("Tanggal Lahir: " + user.getTanggal_lahir());
                System.out.println("--------------------");
            }
        }
    }

    @Override
    public void createData(Map<String, Object> request, User user) {
        try {
            userDAO.createUser(user);
            System.out.println("User berhasil ditambahkan.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void updateData(Map<String, Object> request, User user) {
        try {
            userDAO.updateUser(user);
            System.out.println("User berhasil diupdate.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteData(long id) {
        try {
            userDAO.delete(id);
            System.out.println("User berhasil dihapus.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public User getUserById(long id) {
        User user = userDAO.findUserById(id);
        if (user == null) {
            return null;
        }
        return user;
    }

    public List<User> getAllUser() {
        LinkedHashMap<Integer, List<User>> dataUser = userDAO.findAllUser();
        List<User> allUser = new ArrayList<>();
        for (List<User> userList : dataUser.values()) {
            allUser.addAll(userList);
        }
        return allUser;
    }
}
