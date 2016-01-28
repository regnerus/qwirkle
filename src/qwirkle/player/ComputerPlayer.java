package qwirkle.player;

// game

import qwirkle.client.Client;
import qwirkle.client.ClientController;
import qwirkle.game.*;
import qwirkle.shared.Cli;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bouke on 23/01/16.
 */
public class ComputerPlayer extends ClientPlayer {

    private int thinkTime;
    private DumbStrategy strategy;

    /**
     * Constructor of computerPlayer.
     * A computer player is a player that is not controlled by a human
     * but has an AI to determine the next moves in order to win the game.
     *
     * @param name
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

    @Override
    public ArrayList<Stone> determineFirstMove() {
        ArrayList<Stone> move = new ArrayList<>();
        Stone stone = ClientController.getInstance().getPlayer().getHand().getStones().get(0);
        stone.setLocation(0,0);
        move.add(stone);

        return move;
    }

    /**
     * Calculate the next best move based on a board.
     *
     * @param board
     * @return
     */
    @Override
    public void determineMove(Board board, Client client) {
        ArrayList<Stone> move = DumbStrategy.calculateMove(board, ClientController.getInstance().getPlayer().getHand());
        if(move.size() > 1) {
            client.handleMakeMove(move);

            for(Stone stone : move) {
                ClientController.getInstance().getPlayer().getHand().removeStone(stone);
            }
        }
        else {
            ArrayList<Stone> hand = ClientController.getInstance().getPlayer().getHand().getStones();

            for(Stone stone : hand) {
                ClientController.getInstance().getPlayer().getHand().removeStone(stone);
            }

            client.handleChangeStones(hand);

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
