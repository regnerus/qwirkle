package qwirkle.player;

import qwirkle.game.Board;
import qwirkle.game.Hand;
import qwirkle.game.Position;
import qwirkle.game.Stone;

import java.util.ArrayList;

/**
 * @author Bouke Regnerus
 * @version 1.0
 * @since 2016-01-29
 */
public class DumbStrategy implements Strategy {

    /**
     * Calculates the next move in a really dumb way.
     *
     * @param board
     * @param hand
     * @return
     */
    public ArrayList<Stone> calculateMove(Board board, Hand hand) {
        ArrayList<Stone> move = new ArrayList<>();
        ArrayList<Position> positions = new ArrayList<>();

        for (Stone stone : hand.getStones()) {
            for (Position position : board.getPossibleMoves()) {
                if (!positions.contains(position)) {
                    stone.setLocation(position);

                    if (board.validMove(stone)) {
                        move.add(stone);
                        positions.add(stone.getLocation());
                        break;
                    }
                }

            }
        }

        return move;

    }
}
