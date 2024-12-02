package lelang.app.model;

import java.sql.Date;

public class Masyarakat extends User  {
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

    public void displayData(){
        System.out.println(" =========== Data Masyarakat ============");
        System.out.println("Data ke -" + id);
        System.out.println("NIK : " + nik);
        System.out.println("Nama Lengkap : " + nama_lengkap);
        System.out.println("username : " + username);
        System.out.println("email : " + email);
        System.out.println("Alamat Pengguna : " + alamat);
        System.out.println("Tanggal Lahir : " + tanggal_lahir);
    }

    // behavior

}
