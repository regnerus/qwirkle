package qwirkle.player;

// game
import qwirkle.game.Game;

/**
 * Created by Bouke on 23/01/16.
 */
public class HumanPlayer extends ClientPlayer {

    private String name;

    public HumanPlayer(Game game, String name) {
        super(game, name);
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
