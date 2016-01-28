package qwirkle.player;

import qwirkle.client.Client;
import qwirkle.client.ClientController;
import qwirkle.game.Board;
import qwirkle.game.Stone;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 24/01/16.
 */
public abstract class ClientPlayer extends Player {

    public ClientController clientController;

    public ClientPlayer(ClientController clientController) {
        super(clientController.getUsername());
        this.clientController = clientController;
    }

    public ClientPlayer(ClientController clientController, String username) {
        super(username);
        this.clientController = clientController;
    }

    public abstract ArrayList<Stone> determineFirstMove();

    public abstract void determineMove(Board board, Client client);

    public void addToHand(Stone stone) {
        super.getHand().addStone(stone);
    }

    public void addToHand(List<Stone> stones) {
        super.getHand().addStone(stones);
    }
}
