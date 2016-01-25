package qwirkle.player;

// game
import qwirkle.shared.GameController;

/**
 * Created by Bouke on 23/01/16.
 */
public class HumanPlayer extends ClientPlayer {

    public HumanPlayer(GameController game, String name) {
        super(game, name);
    }


//    public Move nextMove(Board board) {
//
//    }
//
//    public Move firstMove(Board board) {
//
//    }

    public boolean isHuman() {
        return true;
    }

}
