package qwirkle.player;

// game

import qwirkle.game.*;
import qwirkle.shared.GameController;

/**
 * Created by Bouke on 23/01/16.
 */
public class ComputerPlayer extends ClientPlayer {

    private int thinkTime;

    /**
     * Constructor of computerPlayer.
     * A computer player is a player that is not controlled by a human
     * but has an AI to determine the next moves in order to win the game.
     *
     * @param name
     */
    public ComputerPlayer(GameController game, String name) {
        super(game, name);
    }

    /**
     * Ask if player is human.
     *
     * @return
     */
    public boolean isHuman() {
        return false;
    }

    /**
     * Calculate the next best move based on a board.
     *
     * @param board
     * @return
     */
    public Move calculateNextMove(Board board) {
        return new Move();
    }

    /**
     * Set the time that an AI player can think before retuning a move.
     *
     * @param thinkTime
     */
    public void setThinkTime(int thinkTime) {
        this.thinkTime = thinkTime;
    }
}
