package lelang.app.model;

import java.util.Date;

public class Petugas extends User{
    private String role;

    public Petugas(long id, String nama_lengkap, String username, String email, String password, String alamat,
            Date tanggal_lahir, String role) {
        super(id, nama_lengkap, username, email, password, alamat, tanggal_lahir);
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // behavior

    
}
