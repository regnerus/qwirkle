package qwirkle.player;

import qwirkle.server.controllers.ClientController;

/**
 * Created by Bouke on 23/01/16.
 */
public class HumanPlayer extends Player {

    private ClientController client;

    public HumanPlayer() {
        super();
    }

    public boolean isHuman() {
        return true;
    }

    public ClientController getClient() {
        return client;
    }
}
