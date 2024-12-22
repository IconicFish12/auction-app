package lelang.mission.util;
import java.util.Scanner;

public class InputUtil {
    private static final Scanner scanner = new Scanner(System.in);

    public static String getStrInput() {
        String input;
        while (true) {
            input = scanner.nextLine();
            if (input != null && !input.trim().isEmpty()) {
                break;
            }
            System.out.println("Input tidak valid. Silakan masukkan teks.");
        }
        return input;
    }

    public static int getIntInput() {
        int input;
        while (true) {
            try {
                input = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Silakan masukkan angka integer.");
            }
        }
        return input;
    }

    public static long getLongInput() {
        long input;
        while (true) {
            try {
                input = Long.parseLong(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Silakan masukkan angka long.");
            }
        }
        return input;
    }
}