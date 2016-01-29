package qwirkle.player;

import qwirkle.server.ClientHandler;

/**
 * @author Bouke Regnerus
 * @version 1.0
 * @since 2016-01-29
 */
public class ServerPlayer extends Player {

    private ClientHandler client;

    /**
     * Initialize a new server player.
     *
     * @param client
     */
    public ServerPlayer(ClientHandler client) {
        super();
        this.client = client;

        client.setPlayer(this);
    }

    /**
     * Initialize a new server player with a username.
     *
     * @param client
     * @param username
     */
    public ServerPlayer(ClientHandler client, String username) {
        super(username);
        this.client = client;

        client.setPlayer(this);
    }

    /**
     * @return Return the clientHandler of this player.
     */
    public ClientHandler getClient() {
        return this.client;
    }
}
