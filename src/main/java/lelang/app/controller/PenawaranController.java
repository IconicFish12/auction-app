package lelang.app.controller;


public class PenawaranController  extends Controller{

    @Override
    public void getData() {
        System.out.println("Getting data From tabel Penawaran");
    }

    @Override
    public <Penawaran> void createData(Penawaran penawaran) {
        System.out.println("Creating data Penawaran tabel Penawaran");
    }

    @Override
    public <Penawaran> void updateData(Penawaran penawaran) {
        System.out.println("Updating data Penawaran From tabel Penawaran");
    }

    @Override
    public void deleteData(long id) {
        System.out.println("Deleting data Penawaran From tabel Penawaran");
    }

}
