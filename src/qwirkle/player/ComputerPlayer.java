package qwirkle.player;

// game
import qwirkle.game.Board;
import qwirkle.game.PossibleMove;

/**
 * Created by Bouke on 23/01/16.
 */
public class ComputerPlayer extends Player {

    private int thinkTime;

    public ComputerPlayer(String name) {
        super(name);
    }

    public boolean isHuman() {
        return false;
    }

    public PossibleMove[] calculateNextMove(Board board) {
        PossibleMove[] move = new PossibleMove[1];

        for (Stone stone: hand) {

        }

        return move;
    }
}
