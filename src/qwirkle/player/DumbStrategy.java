package qwirkle.player;

// game

import qwirkle.game.Board;
import qwirkle.game.Hand;
import qwirkle.game.Position;
import qwirkle.game.Stone;

import java.util.ArrayList;

// java

/**
 * Created by chris on 24/01/16.
 */
public abstract class DumbStrategy implements Strategy {

    /**
     * Calculates the next move in a really dumb way.
     *
     * @param game
     * @param hand
     * @return
     */
    public static ArrayList<Stone> calculateMove(Board board, Hand hand) {
        ArrayList<Stone> move = new ArrayList<>();

        for (Stone stone : hand.getStones()) {
            for (Position position : board.getPossibleMoves()) {
                stone.setLocation(position);

                if (board.validMove(stone)) {
                    move.add(stone);
                    break;
                }
            }
        }

        return move;

    }
}
