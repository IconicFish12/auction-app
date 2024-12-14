package lelang.mission.util;
import java.util.Scanner;
public class InputUtil {
    public static String getStrInput() {
        try (Scanner scanner = new Scanner(System.in)) {
            return scanner.nextLine();
        }
    }
    public static int getIntInput() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (!scanner.hasNextInt()) {
                System.out.println("Input tidak valid. Masukkan angka!");
                scanner.next();
            }
            return scanner.nextInt();
        }
    }
}
