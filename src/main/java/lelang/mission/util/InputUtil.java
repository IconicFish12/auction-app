package lelang.mission.util;
import java.util.InputMismatchException;
import java.util.Scanner;
public class InputUtil {
    private static final Scanner scanner = new Scanner(System.in);
    public static String getStrInput() {
        return scanner.nextLine();
    }
    public static int getIntInput() {
        while (true) {
            try {
                return scanner.nextInt(); 
            } catch (InputMismatchException e) {
                System.out.println("Input tidak valid. Masukkan angka!");
                scanner.nextLine(); // Bersihkan buffer input
            }
        }
    }
}