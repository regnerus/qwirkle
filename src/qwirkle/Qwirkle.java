package qwirkle;

import qwirkle.game.Bag;
import qwirkle.game.Board;
import qwirkle.game.Position;
import qwirkle.game.Stone;

import java.util.Map;

/**
 * The Qwirkle Main
 *
 * Created by Chris ter Beke and Bouke Regnerus
 */
public class Qwirkle {

    public static void main(String[] args) {
        // TODO: implement gameplay

        Stone stone1 = new Stone(Stone.Color.RED, Stone.Shape.HEART);
        Stone stone2 = new Stone(Stone.Color.GREEN, Stone.Shape.CIRCLE);
        Stone stone3 = new Stone(Stone.Color.BLUE, Stone.Shape.SQUARE);
        Stone stone4 = new Stone(Stone.Color.PURPLE, Stone.Shape.DIAMOND);

        Board board = new Board();
        Bag bag = new Bag();

        System.out.println(bag.bagSize());
        System.out.println(bag.getStone());
        System.out.println(bag.bagSize());


        board.placeStone(stone1, 5, -1);
        board.placeStone(stone2, 0, 0);
        board.placeStone(stone3, -3, 5);
        board.placeStone(stone4, 0, 1);

        Map<Position, Stone> map = board.getBoard();

        for(int y = board.getBoundsY().getMin(); y <= board.getBoundsY().getMax(); y++)
        {
            for (int x = board.getBoundsX().getMin(); x <= board.getBoundsX().getMax(); x++)
            {

                System.out.print(board.getStone(new Position(x, y)).toString() + " ");
            }
            System.out.println();
        }

    }
}