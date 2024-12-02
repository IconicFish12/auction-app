package lelang.app.model;

import java.sql.Date;

public class Petugas extends User{
    private String role;
    private int nip;

    public Petugas(long id, int nip, String nama_lengkap, String username, String email, String password, String alamat,
            Date tanggal_lahir, String role) {
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

    public void displayData(){
        System.out.println(" =========== Data Petugas Lelang ============");
        System.out.println("Data ke -" + id);
        System.out.println("NIP : " + nip);
        System.out.println("Nama Lengkap : " + nama_lengkap);
        System.out.println("username : " + username);
        System.out.println("email : " + email);
        System.out.println("Alamat Petugas : " + alamat);
        System.out.println("Tanggal Lahir : " + tanggal_lahir);
        System.out.println("Bertugas Sebagai " + role);
    }

}
