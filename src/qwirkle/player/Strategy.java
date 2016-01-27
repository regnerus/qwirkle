package qwirkle.player;

// game

import qwirkle.game.*;
import qwirkle.game.Game;

// java
import java.util.Map;

/**
 * Created by chris on 24/01/16.
 */
public interface Strategy {

    public Map<Position, Stone> calculateMove(Game game, Hand hand);
}