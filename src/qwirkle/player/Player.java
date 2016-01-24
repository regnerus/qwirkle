package qwirkle.player;

// server
import qwirkle.server.controllers.ClientController;

/**
 * Created by Bouke on 23/01/16.
 */
public abstract class Player {

    public static final int MAX_HAND = 6;

    private ClientController client;
    private String name;

    /**
     * Create a new local player
     * @param name
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Create a new networked player
     * @param name
     * @param client
     */
    public Player(String name, ClientController client) {

    }

    public abstract boolean isHuman();

    /**
     * Get the player name
     * @return
     */
    public String getName() {
        return name;
    }
}
