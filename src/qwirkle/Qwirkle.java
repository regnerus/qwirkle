package qwirkle;

// game
import qwirkle.game.*;

// shared
import qwirkle.shared.CliController;

// java
import java.util.Map;

/**
 * The Qwirkle Main
 *
 * Created by Chris ter Beke and Bouke Regnerus
 */
public class Qwirkle {

    public static void main(String[] args) {
        // TODO: implement gameplay

        CliController cli = new CliController();
        Board board = new Board();
        Bag bag = new Bag();

        System.out.println(bag.bagSize());

        Stone stone1 = new Stone(Stone.Color.RED, Stone.Shape.STAR);
        Stone stone2 = bag.getStone();
        Stone stone3 = new Stone(Stone.Color.GREEN, Stone.Shape.STAR);
        Stone stone4 = bag.getStone();
        Stone stone5 = new Stone(Stone.Color.BLUE, Stone.Shape.STAR);
        Stone stone6 = bag.getStone();
        Stone stone7 = bag.getStone();

        System.out.println(bag.bagSize());

        board.placeStone(stone1, 0, 3);
        board.placeStone(stone2, 4, 0);
        board.placeStone(stone3, 0, 2);
        board.placeStone(stone4, 1, 1);
        board.placeStone(stone5, 0, 4);
        board.placeStone(stone6, 2, 5);
        board.placeStone(stone7, 0, 6);

        System.out.println("ValidMove: " + board.validMove(stone1));

        Map<Position, Stone> map = board.getBoard();

        // log to cli
        cli.logSimple(board.toString());
    }
}