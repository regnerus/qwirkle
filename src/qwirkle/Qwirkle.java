package qwirkle;

// game
import qwirkle.game.Bag;
import qwirkle.game.Board;
import qwirkle.game.Hand;
import qwirkle.game.Stone;
import qwirkle.shared.CliController;

// shared
// java

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
        Hand hand = new Hand(bag);

        Stone stone1 = new Stone(Stone.Color.RED, Stone.Shape.STAR);
        Stone stone2 = new Stone(Stone.Color.GREEN, Stone.Shape.STAR);
        Stone stone3 = new Stone(Stone.Color.BLUE, Stone.Shape.STAR);
        Stone stone4 = new Stone(Stone.Color.GREEN, Stone.Shape.SQUARE);
        Stone stone5 = new Stone(Stone.Color.GREEN, Stone.Shape.CIRCLE);
        Stone stone6 = new Stone(Stone.Color.GREEN, Stone.Shape.HEART);

        board.placeStone(stone1, 0, 0);
        board.placeStone(stone2, 1, 0);
        board.placeStone(stone3, 2, 0);
        board.placeStone(stone4, 1, 1);
        board.placeStone(stone5, 1, -1);
        board.placeStone(stone6, 1, -2);

        // log to cli
        cli.logSimple(board.toString());
        cli.logBold("Bag " + bag.bagSize());
        cli.logBold("Hand " + hand.toString());
    }
}