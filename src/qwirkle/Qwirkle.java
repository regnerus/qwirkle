package qwirkle;

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

        Board board = new Board();

        board.placeStone(stone1, 5, -1);
        board.placeStone(stone2, 0, 0);
        board.placeStone(stone3, -3, 5);
        board.placeStone(stone1, -10, 5);


//        for (Map.Entry<Position, Stone> entry : board.getBoard().entrySet()) {
//            System.out.println(entry.getKey() + "/" + entry.getValue());
//        }

//        System.out.println("ABS" + board.getBoundsX().getAbs());
//        System.out.println("ABS" + board.getBoundsY().getAbs());


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