package qwirkle.player;

// game
import qwirkle.game.*;

// java
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

        // loop over stones in hand
        for (Stone stone : hand.getStones()) {

            // get xth possible move

            // add to move map so it can be placed on board
            //move.put(stone.getLocation(), stone);
        }

        return move;
    }
}
