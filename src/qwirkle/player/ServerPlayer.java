package qwirkle.player;

// game

// server

import qwirkle.server.ClientHandler;

/**
 * Created by chris on 24/01/16.
 */
public class ServerPlayer extends Player {

    private ClientHandler client;

    public ServerPlayer(ClientHandler client) {
        super();
        this.client = client;

        client.setPlayer(this);
    }

    public ServerPlayer(ClientHandler client, String username) {
        super(username);
        this.client = client;

        client.setPlayer(this);
    }

    public ClientHandler getClient() {
        return this.client;
    }
}
