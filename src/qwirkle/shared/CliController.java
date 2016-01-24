package qwirkle.shared;

// game
import qwirkle.game.Board;
import qwirkle.game.Position;

/**
 * Handles generic CLI output across server and clients
 */
public class CliController {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ERROR_COLOR = "\u001B[31m";

    public void logSimple(String message) {
        System.out.println(message);
    }

    public void logBoard(Board board) {
        for(int y = board.getBoundsY().getMin(); y <= board.getBoundsY().getMax(); y++) {
            for (int x = board.getBoundsX().getMin(); x <= board.getBoundsX().getMax(); x++) {
                System.out.print(board.getStone(new Position(x, y)).toString() + " ");
            }
            System.out.println();
        }
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