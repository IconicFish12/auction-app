package lelang.database.DAO;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lelang.app.model.Masyarakat;
import lelang.app.model.User;

public class UserDAO extends MasyarakatDAO {

    @Override
    public Masyarakat findById(long id) {
        return super.findById(id);
    }

    public User findUserById(long id) {
        Masyarakat masyarakat = super.findById(id);
        if (masyarakat == null) {
            return null;
        }
        return new User(
            masyarakat.getId(),
            masyarakat.getNama_lengkap(),
            masyarakat.getUsername(),
            masyarakat.getEmail(),
            masyarakat.getPassword(),
            masyarakat.getAlamat(),
            masyarakat.getTanggal_lahir()
        );
    }


    @Override
    public LinkedHashMap<Integer, List<Masyarakat>> findAll() {
        return super.findAll();
    }

    public LinkedHashMap<Integer, List<User>> findAllUser() {
        LinkedHashMap<Integer, List<Masyarakat>> masyarakatList = super.findAll();
        LinkedHashMap<Integer, List<User>> userList = new LinkedHashMap<>();
        for (Map.Entry<Integer, List<Masyarakat>> entry : masyarakatList.entrySet()) {
            List<User> users = new ArrayList<>();
            for (Masyarakat masyarakat : entry.getValue()) {
                users.add(new User(
                    masyarakat.getId(),
                    masyarakat.getNama_lengkap(),
                    masyarakat.getUsername(),
                    masyarakat.getEmail(),
                    masyarakat.getPassword(),
                    masyarakat.getAlamat(),
                    masyarakat.getTanggal_lahir()
                ));
            }
            userList.put(entry.getKey(), users);
        }
        return userList;
    }

    @Override
    public void create(Masyarakat masyarakat) {
        super.create(masyarakat);
    }

    public void createUser(User user) {
         Masyarakat masyarakat = new Masyarakat(
            user.getId(),
            0, // nik tidak digunakan
            user.getNama_lengkap(),
            user.getUsername(),
            user.getEmail(),
            user.getPassword(),
            user.getAlamat(),
            user.getTanggal_lahir()
        );
        super.create(masyarakat);
    }

    @Override
    public void update(Masyarakat masyarakat) {
        super.update(masyarakat);
    }

    public void updateUser(User user) {
        Masyarakat masyarakat = new Masyarakat(
            user.getId(),
            0, // nik tidak digunakan
            user.getNama_lengkap(),
            user.getUsername(),
            user.getEmail(),
            user.getPassword(),
            user.getAlamat(),
            user.getTanggal_lahir()
        );
        super.update(masyarakat);
    }
}
