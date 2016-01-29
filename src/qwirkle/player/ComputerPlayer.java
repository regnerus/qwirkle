package qwirkle.player;

import qwirkle.client.Client;
import qwirkle.client.ClientController;
import qwirkle.game.*;

import java.util.ArrayList;

/**
 * @author Bouke Regnerus
 * @version 1.0
 * @since 2016-01-29
 */
public class ComputerPlayer extends ClientPlayer {

    private int thinkTime;
    private Strategy strategy = new DumbStrategy();

    /**
     * Constructor of computerPlayer.
     * <p>
     * A computer player is a player that is not controlled by a human
     * but has an AI to determine the next moves in order to win the game.
     *
     * @param clientController
     */
    public ComputerPlayer(ClientController clientController) {
        super(clientController);
    }

    public ComputerPlayer(ClientController clientController, String username) {
        super(clientController, username);
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
     * @return Return the first move.
     */
    @Override
    public ArrayList<Stone> determineFirstMove() {
        ArrayList<Stone> move = new ArrayList<>();
        Stone stone = ClientController.getInstance().getPlayer().getHand().getStones().get(0);
        stone.setLocation(0, 0);
        move.add(stone);

        return move;
    }

    /**
     * Calculate the next best move based on a board.
     *
     * @param board
     * @param client
     * @return
     */
    @Override
    public void determineMove(Board board, Client client) {
        ArrayList<Stone> move = strategy.calculateMove(board,
                ClientController.getInstance().getPlayer().getHand());
        if (move.size() > 1) {
            client.handleMakeMove(move);

            for (Stone stone : move) {
                ClientController.getInstance().getPlayer().getHand().removeStone(stone);
            }
        } else {
            ArrayList<Stone> hand =
                    ClientController.getInstance().getPlayer().getHand().getStones();
            ArrayList<Stone> stones = new ArrayList<>();
            stones.add(hand.get(0));

            for (Stone stone : stones) {
                ClientController.getInstance().getPlayer().getHand().removeStone(stone);
            }

            client.handleChangeStones(stones);
        }
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
