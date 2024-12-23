package lelang.resources.interfaces.admin.users;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import lelang.app.controller.UserController;
import lelang.app.model.Petugas;
import lelang.mission.util.InputUtil;

public class DaftarPetugas {
    private static UserController userController = new UserController();
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static void showAllDaftarPetugas() {
        userController.getData();
    }

    public static void createPetugas() {
        System.out.println("============= Tambah Petugas =============");
        System.out.print("NIP: ");
        int nip = InputUtil.getIntInput();
        System.out.print("Nama Lengkap: ");
        String namaLengkap = InputUtil.getStrInput();
        System.out.print("Username: ");
        String username = InputUtil.getStrInput();
        System.out.print("Email: ");
        String email = InputUtil.getStrInput();
        System.out.print("Password: ");
        String password = InputUtil.getStrInput();
        System.out.print("Alamat: ");
        String alamat = InputUtil.getStrInput();
        System.out.print("Tanggal Lahir (yyyy-MM-dd): ");
        String tanggalLahirStr = InputUtil.getStrInput();
        Date tanggalLahir = null;
        try {
            tanggalLahir = new Date(DATE_FORMAT.parse(tanggalLahirStr).getTime());
        } catch (ParseException e) {
            System.out.println("Format tanggal tidak valid. Gunakan format yyyy-MM-dd.");
            return;
        }
        System.out.print("Role: ");
        String role = InputUtil.getStrInput();

        Petugas petugas = new Petugas(0, nip, namaLengkap, username, email, password, alamat, tanggalLahir, role);
        userController.createData(null, petugas);
    }

    public static void updatePetugas() {
        System.out.println("============= Update Petugas =============");
        List<Petugas> petugasList = userController.getAllPetugas();
        if (petugasList.isEmpty()) {
            System.out.println("Tidak ada petugas yang terdaftar.");
            return;
        }
        System.out.println("Pilih petugas yang ingin diupdate:");
        for (int i = 0; i < petugasList.size(); i++) {
            System.out.println((i + 1) + ". " + petugasList.get(i).getNama_lengkap());
        }
        System.out.print(">> Masukkan pilihan: ");
        int pilihan = InputUtil.getIntInput();
        if (pilihan < 1 || pilihan > petugasList.size()) {
            System.out.println("Pilihan tidak valid.");
            return;
        }
        Petugas petugas = petugasList.get(pilihan - 1);

        System.out.print("NIP (" + petugas.getNip() + "): ");
        String nipStr = InputUtil.getStrInput();
        int nip = nipStr.isEmpty() ? petugas.getNip() : Integer.parseInt(nipStr);
        System.out.print("Nama Lengkap (" + petugas.getNama_lengkap() + "): ");
        String namaLengkap = InputUtil.getStrInput();
        if (namaLengkap.isEmpty()) {
            namaLengkap = petugas.getNama_lengkap();
        }
        System.out.print("Username (" + petugas.getUsername() + "): ");
        String username = InputUtil.getStrInput();
        if (username.isEmpty()) {
            username = petugas.getUsername();
        }
        System.out.print("Email (" + petugas.getEmail() + "): ");
        String email = InputUtil.getStrInput();
         if (email.isEmpty()) {
            email = petugas.getEmail();
        }
        System.out.print("Password (" + petugas.getPassword() + "): ");
        String password = InputUtil.getStrInput();
         if (password.isEmpty()) {
            password = petugas.getPassword();
        }
        System.out.print("Alamat (" + petugas.getAlamat() + "): ");
        String alamat = InputUtil.getStrInput();
         if (alamat.isEmpty()) {
            alamat = petugas.getAlamat();
        }
        System.out.print("Tanggal Lahir (" + petugas.getTanggal_lahir() + "): ");
        String tanggalLahirStr = InputUtil.getStrInput();
        Date tanggalLahir = petugas.getTanggal_lahir();
        if (!tanggalLahirStr.isEmpty()) {
            try {
                tanggalLahir = new Date(DATE_FORMAT.parse(tanggalLahirStr).getTime());
            } catch (ParseException e) {
                System.out.println("Format tanggal tidak valid. Gunakan format yyyy-MM-dd.");
                return;
            }
        }
        System.out.print("Role (" + petugas.getRole() + "): ");
        String role = InputUtil.getStrInput();
        if (role.isEmpty()) {
            role = petugas.getRole();
        }

        petugas.setNip(nip);
        petugas.setNama_lengkap(namaLengkap);
        petugas.setUsername(username);
        petugas.setEmail(email);
        petugas.setPassword(password);
        petugas.setAlamat(alamat);
        petugas.setTanggal_lahir(tanggalLahir);
        petugas.setRole(role);
        
        Map<String, Object> request = new HashMap<>();
        userController.updateData(request, petugas);
    }

    public static void deletePetugas() {
        System.out.println("============= Hapus Petugas =============");
        List<Petugas> petugasList = userController.getAllPetugas();
        if (petugasList.isEmpty()) {
            System.out.println("Tidak ada petugas yang terdaftar.");
            return;
        }
        System.out.println("Pilih petugas yang ingin dihapus:");
        for (int i = 0; i < petugasList.size(); i++) {
            System.out.println((i + 1) + ". " + petugasList.get(i).getNama_lengkap());
        }
        System.out.print(">> Masukkan pilihan: ");
        int pilihan = InputUtil.getIntInput();
        if (pilihan < 1 || pilihan > petugasList.size()) {
            System.out.println("Pilihan tidak valid.");
            return;
        }
        Petugas petugas = petugasList.get(pilihan - 1);
        userController.deleteData(petugas.getId());
    }

    public static void showMenu() {
        System.out.println("============= Menu Daftar Petugas =============");
        System.out.println("1. Tampilkan Seluruh Daftar Petugas.");
        System.out.println("2. Tambah Petugas.");
        System.out.println("3. Update Petugas.");
        System.out.println("4. Hapus Petugas.");
        System.out.println("0. Keluar.");
    }

    public static void menu() {
        boolean keluar = false;
        while (!keluar) {
            showMenu();
            System.out.print(">> Masukkan Inputan: ");
            String input = InputUtil.getStrInput();
            try {
                int pilihan = Integer.parseInt(input);
                switch (pilihan) {
                    case 1:
                        showAllDaftarPetugas();
                        break;
                    case 2:
                        createPetugas();
                        break;
                    case 3:
                        updatePetugas();
                        break;
                    case 4:
                        deletePetugas();
                        break;
                    case 0:
                        keluar = true;
                        break;
                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Masukkan angka!");
            }
        }
    }
    public static void main(String[] args) {
        menu();
    }
}
