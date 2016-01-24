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


        stone1.setLocation(0, -1);
        stone2.setLocation(0, 0);
        stone3.setLocation(0, 1);

        stone4.setLocation(-1, 0);
        stone5.setLocation(1, 0);
        stone6.setLocation(2, 0);

        ArrayList<Stone> addStones = new ArrayList<>();
        addStones.add(stone1);
        addStones.add(stone2);
        addStones.add(stone3);

        System.out.println("points: " + board.placeStones(addStones));

        addStones = new ArrayList<>();
        addStones.add(stone4);
        addStones.add(stone5);
        addStones.add(stone6);

        System.out.println("points: " + board.placeStones(addStones));

        // log to cli
        cli.logSimple(board.toString());
        cli.logBold("Bag " + bag.bagSize());
        cli.logBold("Hand " + hand.toString());
    }
}