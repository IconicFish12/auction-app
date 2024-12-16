package lelang.app.controller;

import java.util.Map;
// import lelang.app.model.Masyarakat;
// import lelang.app.model.Petugas;
// import lelang.database.DAO.MasyarakatDAO;
// import lelang.database.DAO.PetugasDAO;

interface UserHandler<T> {
    void getData();
    void createData(Map<String, Object> request, T user);
    void updateData(Map<String, Object> request, T user);
    void deleteData(long id);
}

public class UserController implements UserHandler<Object> {

    // // Model
    // private Masyarakat masyarakat;
    // private Petugas petugas;

    // // DAO
    // private MasyarakatDAO dataMasyarakat = new MasyarakatDAO();
    // private PetugasDAO dataPetugas = new PetugasDAO();

    @Override
    public void getData() {
        
    }

    @Override
    public void createData(Map<String, Object> request, Object user) {
        
    }

    @Override
    public void updateData(Map<String, Object> request, Object user) {
        
    }

    @Override
    public void deleteData(long id) {
        
    }
}
