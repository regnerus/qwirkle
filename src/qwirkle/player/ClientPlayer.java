package qwirkle.player;

import qwirkle.client.Client;
import qwirkle.client.ClientController;
import qwirkle.game.Board;
import qwirkle.game.Stone;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bouke Regnerus
 * @version 1.0
 * @since 2016-01-29
 */
public abstract class ClientPlayer extends Player {

    public ClientController clientController;

    /**
     * Initialize a new client player.
     *
     * @param clientController
     */
    public ClientPlayer(ClientController clientController) {
        super(clientController.getUsername());
        this.clientController = clientController;
    }

    /**
     * Initialize a new client playe with an username.
     *
     * @param clientController
     * @param username
     */
    public ClientPlayer(ClientController clientController, String username) {
        super(username);
        this.clientController = clientController;
    }

    /**
     * @return Return stones in the first move.
     */
    public abstract ArrayList<Stone> determineFirstMove();

    /**
     * Make a move.
     *
     * @param board
     * @param client
     */
    public abstract void determineMove(Board board, Client client);

    /**
     * Add a stone to the hand.
     *
     * @param stone
     */
    public void addToHand(Stone stone) {
        super.getHand().addStone(stone);
    }

    /**
     * Add stones to the hand.
     *
     * @param stones
     */
    public void addToHand(List<Stone> stones) {
        super.getHand().addStone(stones);
    }
}
