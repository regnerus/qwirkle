package qwirkle.player;

import qwirkle.game.Board;
import qwirkle.game.Hand;
import qwirkle.game.Stone;

import java.util.ArrayList;

/**
 * @author Bouke Regnerus
 * @version 1.0
 * @since 2016-01-29
 */

public interface Strategy {
    /**
     * Calculates the next move.
     *
     * @param board
     * @param hand
     * @return
     */
    ArrayList<Stone> calculateMove(Board board, Hand hand);
}