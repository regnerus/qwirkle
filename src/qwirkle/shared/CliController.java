package qwirkle.shared;

/**
 * Handles generic CLI output across server and clients
 */
public class CliController {

    final static String ANSI_CLS = "\u001b[2J";
    final static String ANSI_HOME = "\u001b[H";

    public CliController() {

    }

    public static void clearScreen() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            }
            else {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
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

    }

    public void logBoard() {

    }

    public void logServerError() {

    }

    public void logClientError() {

    }
}