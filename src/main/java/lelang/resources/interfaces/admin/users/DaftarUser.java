package lelang.resources.interfaces.admin.users;

import lelang.app.controller.UserController;
import lelang.app.model.User;
import lelang.mission.util.InputUtil;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DaftarUser {
    private static UserController userController = new UserController();
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static void view() {
        System.out.println("=== Daftar User ===");
        userController.getData();
        System.out.println("=== End Daftar User ===");
    }

    public static void create() {
        System.out.println("============= Tambah User =============");
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
         if (tanggalLahirStr.isEmpty()) {
            System.out.println("Tanggal lahir tidak boleh kosong.");
            return;
        }
        try {
            tanggalLahir = new Date(DATE_FORMAT.parse(tanggalLahirStr).getTime());
        } catch (ParseException e) {
            System.out.println("Format tanggal tidak valid. Gunakan format yyyy-MM-dd.");
            return;
        }

        User user = new User(0, namaLengkap, username, email, password, alamat, tanggalLahir);
        userController.createData(null, user);
        System.out.println("User berhasil ditambahkan.");
    }

    public static void update() {
        System.out.println("============= Update User =============");
        List<User> userList = userController.getAllUser();
        if (userList.isEmpty()) {
            System.out.println("Tidak ada user yang terdaftar.");
            return;
        }
        System.out.println("Pilih user yang ingin diupdate:");
        for (int i = 0; i < userList.size(); i++) {
            System.out.println((i + 1) + ". " + userList.get(i).getNama_lengkap());
        }
        System.out.print(">> Masukkan pilihan: ");
        int pilihan = InputUtil.getIntInput();
        if (pilihan < 1 || pilihan > userList.size()) {
            System.out.println("Pilihan tidak valid.");
            return;
        }
        User user = userList.get(pilihan - 1);

        System.out.print("Nama Lengkap (" + user.getNama_lengkap() + "): ");
        String namaLengkap = InputUtil.getStrInput();
        if (namaLengkap.isEmpty()) {
            namaLengkap = user.getNama_lengkap();
        }
        System.out.print("Username (" + user.getUsername() + "): ");
        String username = InputUtil.getStrInput();
        if (username.isEmpty()) {
            username = user.getUsername();
        }
        System.out.print("Email (" + user.getEmail() + "): ");
        String email = InputUtil.getStrInput();
         if (email.isEmpty()) {
            email = user.getEmail();
        }
        System.out.print("Password (" + user.getPassword() + "): ");
        String password = InputUtil.getStrInput();
         if (password.isEmpty()) {
            password = user.getPassword();
        }
        System.out.print("Alamat (" + user.getAlamat() + "): ");
        String alamat = InputUtil.getStrInput();
         if (alamat.isEmpty()) {
            alamat = user.getAlamat();
        }
        System.out.print("Tanggal Lahir (" + user.getTanggal_lahir() + "): ");
        String tanggalLahirStr = InputUtil.getStrInput();
        Date tanggalLahir = user.getTanggal_lahir();
         if (!tanggalLahirStr.isEmpty()) {
            try {
                tanggalLahir = new Date(DATE_FORMAT.parse(tanggalLahirStr).getTime());
            } catch (ParseException e) {
                System.out.println("Format tanggal tidak valid. Gunakan format yyyy-MM-dd.");
                return;
            }
        } else {
             System.out.println("Tanggal lahir tidak boleh kosong.");
             return;
        }

        user.setNama_lengkap(namaLengkap);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setAlamat(alamat);
        user.setTanggal_lahir(tanggalLahir);
        
        Map<String, Object> request = new HashMap<>();
        userController.updateData(request, user);
        System.out.println("User berhasil diupdate.");
    }

    public static void delete() {
        System.out.println("============= Hapus User =============");
        List<User> userList = userController.getAllUser();
        if (userList.isEmpty()) {
            System.out.println("Tidak ada user yang terdaftar.");
            return;
        }
        System.out.println("Pilih user yang ingin dihapus:");
        for (int i = 0; i < userList.size(); i++) {
            System.out.println((i + 1) + ". " + userList.get(i).getNama_lengkap());
        }
        System.out.print(">> Masukkan pilihan: ");
        int pilihan = InputUtil.getIntInput();
        if (pilihan < 1 || pilihan > userList.size()) {
            System.out.println("Pilihan tidak valid.");
            return;
        }
        User user = userList.get(pilihan - 1);
        userController.deleteData(user.getId());
        System.out.println("User berhasil dihapus.");
    }


    public static void showMenu() {
        System.out.println("============= Menu Daftar User =============");
        System.out.println("1. Tampilkan Seluruh Daftar User.");
        System.out.println("2. Tambah User.");
        System.out.println("3. Update User.");
        System.out.println("4. Hapus User.");
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
                        view();
                        break;
                    case 2:
                        create();
                        break;
                    case 3:
                        update();
                        break;
                    case 4:
                        delete();
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
