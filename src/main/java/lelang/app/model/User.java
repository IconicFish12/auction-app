package lelang.app.model;

import java.util.Date;

public class User {
    protected long id;
    protected String nama_lengkap, username, email, password, alamat;
    protected Date tanggal_lahir;

    public User(long id, String nama_lengkap, String username, String email, String password, String alamat,
            Date tanggal_lahir) {
        this.id = id;
        this.nama_lengkap = nama_lengkap;
        this.username = username;
        this.email = email;
        this.password = password;
        this.alamat = alamat;
        this.tanggal_lahir = tanggal_lahir;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Date getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(Date tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }


}
