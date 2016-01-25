package qwirkle.shared;

// game
/**
 * Handles generic CLI output across server and clients.
 */
public class CliController {

    public static final String ANSI_CLS = "\u001b[2J";
    public static final String ANSI_HOME = "\u001b[H";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BOLD = "\u001B[1m";
    public static final String ERROR_COLOR = "\u001B[31m";
    public static final String RETURN = "\n\r";

    public CliController() {

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
}