package lelang.app.model;

import java.util.Date;

public class Masyarakat extends User{
    private int nik;

    public Masyarakat(long id, String nama_lengkap, String username, String email, String password, String alamat,
            Date tanggal_lahir, int nik) {
        super(id, nama_lengkap, username, email, password, alamat, tanggal_lahir);
        this.nik = nik;
    }

    public int getNik() {
        return nik;
    }

    public void setNik(int nik) {
        this.nik = nik;
    }

    // behavior 

}
