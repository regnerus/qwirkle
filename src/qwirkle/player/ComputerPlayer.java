package qwirkle.player;

// game
import qwirkle.game.*;

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

    public Move[] calculateNextMove(Board board) {
        Move[] move = new Move[1];


        return move;
    }
}
