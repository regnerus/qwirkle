package qwirkle.player;

// game
import qwirkle.game.*;

// java
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by chris on 24/01/16.
 */
public class DumbStrategy implements Strategy {

    /**
     * Calculates the next move in a really dumb way
     * @param game
     * @param hand
     * @return
     */
    public Map<Position, Stone> calculateMove(Game game, Hand hand) {

        Map<Position, Stone> move = new HashMap<Position, Stone>();

        for (Position pos : game.getBoard().getPossibleMoves()) {             // loop over empty fields
            for (Stone stone : hand.getStones()) {       // loop over stones in hand
                if (game.getBoard().validMove(stone)) {  // if valid move
                    // game.getBoard().placeStone(stone);   // put on board

                    move.
                }
            }
        }

        return move;
    }
}
