package qwirkle.shared;

import java.util.Scanner;

/**
 * Created by Bouke on 25/01/16.
 */
public class Cli {
    public static final String ANSI_CLS = "\u001b[2J";
    public static final String ANSI_HOME = "\u001b[H";
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
        // print this message in red
        System.out.println(ERROR_COLOR + e.getMessage() + ANSI_RESET);
    }

    public static void logClientError(Exception e) {
        // print this message in red
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

    public static void clearScreen() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            //TODO: Handle Exceptions
        }
    }

    public void printScreen(String message) {
        this.clearScreen();
        System.out.print(ANSI_CLS + ANSI_HOME);
        System.out.flush();
        System.out.println(message);
    }
}
