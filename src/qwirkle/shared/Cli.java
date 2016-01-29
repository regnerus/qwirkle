package qwirkle.shared;

import java.util.Scanner;

/**
 * @author Bouke Regnerus
 * @version 1.0
 * @since 2016-01-29
 */
public class Cli {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BOLD = "\u001B[1m";
    public static final String ERROR_COLOR = "\u001B[31m";
    public static final String RETURN = "\n\r";

    public void logSimple(String message) {
        System.out.println(message + ANSI_RESET);
    }

    public void logBold(String message) {
        System.out.println(ANSI_BOLD + message + ANSI_RESET);
    }

    public static void logServerError(Exception e) {
        System.out.println(ERROR_COLOR + e.getMessage() + ANSI_RESET);
    }

    public static void logClientError(Exception e) {
        System.out.println(ERROR_COLOR + e.getMessage() + ANSI_RESET);
    }

    public int readInt(String message) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            this.logSimple(message);
            try (Scanner line = new Scanner(scanner.nextLine());) {
                if (line.hasNextInt()) {
                    return line.nextInt();
                }
            }
        }
    }

    public String readString(String message) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            this.logSimple(message);
            try (Scanner line = new Scanner(scanner.nextLine());) {
                if (line.hasNext()) {
                    return line.next();
                }
            }
        }
    }

    public String[] readArray(String message) {
        String[] input;

        Scanner scanner = new Scanner(System.in);

        this.logSimple(message);
        input = scanner.nextLine().split(" ", -1);

        return input;
    }
}
