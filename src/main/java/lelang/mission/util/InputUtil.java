package lelang.mission.util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputUtil {
    private static final Scanner scanner = new Scanner(System.in);

    public static String getStrInput() {
        return scanner.next();
    }

    public static int getIntInput() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.next();
            }
        }
    }

    public static long getLongInput() {
        while (true) {
            try {
                return scanner.nextLong();
            } catch (InputMismatchException e) {
                scanner.next();
            }
        }
    }
}