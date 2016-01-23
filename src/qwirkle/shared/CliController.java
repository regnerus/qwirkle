package qwirkle.shared;

// game
import qwirkle.game.Board;
import qwirkle.game.Position;

/**
 * Handles generic CLI output across server and clients
 */
public class CliController {

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

    public void logServerError() {

    }

    public void logClientError() {

    }
}