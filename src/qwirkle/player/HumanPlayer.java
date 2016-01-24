package qwirkle.player;

// game
import qwirkle.game.Board;
import qwirkle.game.Game;
import qwirkle.game.Move;

/**
 * Created by Bouke on 23/01/16.
 */
public class HumanPlayer extends ClientPlayer {

    private String name;

    public HumanPlayer(String name) {
        super(name);
    }

    public String getName() {
        return name;
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
