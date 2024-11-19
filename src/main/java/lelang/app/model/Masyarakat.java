package lelang.app.model;

import java.sql.Date;

public class Masyarakat extends User {
    private int nik;

    public Masyarakat(long id, int nik, String nama_lengkap, String username, String email, String password,
            String alamat, Date tanggal_lahir) {
        super(id, nama_lengkap, username, email, password, alamat, tanggal_lahir);
        this.nik = nik;
    }

    public int getNik() {
        return nik;
    }

    public void setNik(int nik) {
        this.nik = nik;
    }

    @Override
    public String toString() {
        return "Masyarakat [id=" + id + ", nik=" + nik + ", nama_lengkap=" + nama_lengkap + ", username=" + username
                + ", email=" + email + ", password=" + password + ", alamat=" + alamat + ", tanggal_lahir="
                + tanggal_lahir + "]";
    }

    // behavior

}
