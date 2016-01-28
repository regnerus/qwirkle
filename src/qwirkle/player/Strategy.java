package qwirkle.player;

// game

import qwirkle.game.Game;
import qwirkle.game.Hand;
import qwirkle.game.Stone;

import java.util.ArrayList;

// java

/**
 * Created by chris on 24/01/16.
 */
public interface Strategy {

    public ArrayList<Stone> calculateMove(Game game, Hand hand);
}