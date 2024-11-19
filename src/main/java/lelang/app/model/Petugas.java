package lelang.app.model;

import java.sql.Date;

public class Petugas extends User{
    private String role;
    private int nip;

    public Petugas(long id, String nama_lengkap, String username, String email, String password, String alamat,
            Date tanggal_lahir, int nip, String role) {
        super(id, nama_lengkap, username, email, password, alamat, tanggal_lahir);
        this.nip = nip;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getNip() {
        return nip;
    }

    public void setNip(int nip) {
        this.nip = nip;
    }

    // behavior

    
}
