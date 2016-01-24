package qwirkle;

import qwirkle.game.Bag;
import qwirkle.game.Board;
import qwirkle.game.Hand;
import qwirkle.game.Stone;
import qwirkle.shared.CliController;

// shared

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
        Stone stone2 = bag.getStone();
        Stone stone3 = new Stone(Stone.Color.GREEN, Stone.Shape.STAR);
        Stone stone4 = bag.getStone();
        Stone stone5 = new Stone(Stone.Color.BLUE, Stone.Shape.STAR);
        Stone stone6 = bag.getStone();
        Stone stone7 = bag.getStone();

        board.placeStone(stone1, 0, 3);
        board.placeStone(stone2, -4, 0);
        board.placeStone(stone3, 0, 2);
        board.placeStone(stone4, 1, 1);
        board.placeStone(stone5, 0, 4);
        board.placeStone(stone6, 2, 5);
        board.placeStone(stone7, 0, 6);
        
        cli.logSimple(board.toString());
        cli.logBold("Bag " + bag.bagSize());
        cli.logBold("Hand " + hand.toString());
    }
}