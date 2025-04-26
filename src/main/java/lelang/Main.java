package lelang;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import lelang.app.controller.PetugasController;
import lelang.app.controller.UserController;
import lelang.app.model.Petugas;
import lelang.app.model.User;
import lelang.database.DBConnection;
import lelang.mission.util.InputUtil;
import lelang.resources.interfaces.admin.users.DaftarPetugas;
import lelang.resources.interfaces.admin.users.DaftarUser;
import lelang.resources.interfaces.user.BuatPenawaran;
import lelang.resources.interfaces.user.BuatPengajuan;
import lelang.resources.interfaces.user.DaftarBarangLelang;
import lelang.resources.interfaces.user.LihatHistoryLelang;
import lelang.resources.view.admin.barang.BarangLelang;
import lelang.resources.view.admin.barang.KategoriBarang;
import lelang.resources.view.admin.lelang.DaftarLelang;
import lelang.resources.view.admin.lelang.DaftarPenawaran;
import lelang.resources.view.admin.lelang.DaftarPengajuan;

public class Main {
    private static long loggedInUserId = 0;
    private static String loggedInUserRole = "";
    private static UserController userController = new UserController();
    private static PetugasController petugasController = new PetugasController();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Connection connect = DBConnection.getConnection();

        if (connect != null) {
            try {
                System.out.println("Database Is Connect");
                loginMenu();
                connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                scanner.close();
            }
        }
    }

    private static void loginMenu() {
        boolean keluar = false;
        while (!keluar) {
            System.out.println("=========== Menu Login ===========");
            System.out.println("1. Login sebagai User");
            System.out.println("2. Login sebagai Admin");
            System.out.println("0. Keluar");
            System.out.print(">> Masukkan Pilihan: ");
            int pilihan = InputUtil.getIntInput();

            switch (pilihan) {
                case 1:
                    login("user");
                    if (loggedInUserId != 0) {
                        userMenu();
                    }
                    break;
                case 2:
                    login("admin");
                    if (loggedInUserId != 0) {
                        adminMenu();
                    }
                    break;
                case 0:
                    keluar = true;
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private static void login(String role) {
        System.out.print("Masukkan Username: ");
        String username = InputUtil.getStrInput();
        System.out.print("Masukkan Password: ");
        String password = InputUtil.getStrInput();

        if (role.equals("user")) {
            List<User> users = userController.getAllUser();
            for (User user : users) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    loggedInUserId = user.getId();
                    loggedInUserRole = "user";
                    System.out.println("Login berhasil sebagai user!");
                    return;
                }
            }
            System.out.println("Login gagal. Username atau password salah.");
        } else if (role.equals("admin")) {
            List<Petugas> petugass = petugasController.getAllPetugas();
            for (Petugas petugas : petugass) {
                 if (petugas.getUsername().equals(username) && petugas.getPassword().equals(password)) {
                    loggedInUserId = petugas.getId();
                    loggedInUserRole = "admin";
                    System.out.println("Login berhasil sebagai admin!");
                    return;
                }
            }
            System.out.println("Login gagal. Username atau password salah.");
        }
    }

    private static void userMenu() {
        boolean keluar = false;
        while (!keluar) {
            System.out.println("=========== Menu User ===========");
            System.out.println("1. Lihat daftar barang lelang (yang dibuka saja).");
            System.out.println("2. Buat penawaran.");
            System.out.println("3. Buat pengajuan.");
            System.out.println("4. Lihat history pelelangan suatu barang.");
            System.out.println("0. Logout");
            System.out.print(">> Masukkan Pilihan: ");
            int pilihan = InputUtil.getIntInput();

            switch (pilihan) {
                case 1:
                    DaftarBarangLelang.showDaftarBarangLelangByStatus("berlangsung");
                    break;
                case 2:
                    BuatPenawaran.buatPenawaran();
                    break;
                case 3:
                    BuatPengajuan.buatPengajuan();
                    break;
                case 4:
                    LihatHistoryLelang.menu();
                    break;
                case 0:
                    loggedInUserId = 0;
                    loggedInUserRole = "";
                    keluar = true;
                    System.out.println("Logout Berhasil!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private static void adminMenu() {
        boolean keluar = false;
        while (!keluar) {
            System.out.println("=========== Menu Admin ===========");
            // ini di package lelang.resources.interfaces.admin.barang
            System.out.println("1. Menu Barang Lelang");
            System.out.println("2. Menu Daftar Barang Lelang");
            System.out.println("3. Menu Kategori Barang");
            // ini di package lelang.resources.interfaces.admin.lelang
            System.out.println("4. Menu Daftar Lelang");
            System.out.println("5. Menu Daftar Penawaran");
            System.out.println("6. Menu Daftar Pengajuan");
            // ini di package lelang.resources.interfaces.admin.user
            System.out.println("7. Menu Daftar Petugas");
            System.out.println("8. Menu Daftar User");
            System.out.println("0. Logout");
            System.out.print(">> Masukkan Pilihan: ");
            int pilihan = InputUtil.getIntInput();

            switch (pilihan) {
                case 1:
                    BarangLelang.menu();
                    break;
                case 2:
                    DaftarBarangLelang.menu();
                    break;
                case 3:
                    KategoriBarang.menu();
                    break;
                case 4:
                    DaftarLelang.menu();
                    break;
                case 5:
                    DaftarPenawaran.showMenu();
                    break;
                case 6:
                    DaftarPengajuan.menu();
                    break;
                case 7:
                    DaftarPetugas.menu();
                    break;
                case 8:
                    DaftarUser.menu();
                    break;
                case 0:
                    loggedInUserId = 0;
                    loggedInUserRole = "";
                    keluar = true;
                    System.out.println("Logout Berhasil!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }
    public static long getLoggedInUserId() {
        return loggedInUserId;
    }
}
