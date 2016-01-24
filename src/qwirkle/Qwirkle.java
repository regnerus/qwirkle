package qwirkle;

// game

import qwirkle.game.Bag;
import qwirkle.game.Board;
import qwirkle.game.Hand;
import qwirkle.game.Stone;
import qwirkle.shared.CliController;

import java.util.ArrayList;

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
        Stone stone7 = new Stone(Stone.Color.RED, Stone.Shape.HEART);
        Stone stone8 = new Stone(Stone.Color.YELLOW, Stone.Shape.HEART);

        board.placeStone(stone1, 0, 0);
//        board.placeStone(stone2, 1, 0);
        board.placeStone(stone3, 2, 0);
        stone2.setLocation(1, 0);;
        board.placeStone(stone4, 1, 1);
        board.placeStone(stone7, 2, -1);
        stone5.setLocation(1, -1);
        stone8.setLocation(4, -9);
//        board.placeStone(stone5, 1, -1);
        board.placeStone(stone6, 1, -2);

        ArrayList<Stone> addStones = new ArrayList<>();
        addStones.add(stone2);
        addStones.add(stone5);
        addStones.add(stone8);

        System.out.println("points: " + board.placeStones(addStones));
//
//
//        System.out.println(board.getRow(stone2));
//
//        System.out.println(board.getColumn(stone2).equals(board.getColumn(stone4)));

        // log to cli
        cli.logSimple(board.toString());
        cli.logBold("Bag " + bag.bagSize());
        cli.logBold("Hand " + hand.toString());
    }
}