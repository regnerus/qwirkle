package qwirkle.player;

// game

import qwirkle.server.ClientHandler;

/**
 * Created by Bouke on 23/01/16.
 */
public class HumanPlayer extends Player {
    private ClientHandler client;

    public HumanPlayer() {
        super();
    }

    public HumanPlayer(String username) {
        super(username);
    }

    public HumanPlayer(ClientHandler client) {
        super();
        this.client = client;
    }

    public HumanPlayer(ClientHandler client, String username) {
        super(username);
        this.client = client;
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
