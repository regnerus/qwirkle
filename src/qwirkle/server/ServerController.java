package qwirkle.server;

import qwirkle.game.Game;
import qwirkle.game.Players;
import qwirkle.player.Player;
import qwirkle.player.ServerPlayer;
import qwirkle.shared.Cli;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bouke Regnerus
 * @version 1.0
 * @since 2016-01-29
 */
public class ServerController {
    private static ServerController instance = null;
    private final ArrayList<ClientHandler> clients;

    private static int maxPlayers = 4;

    List<Players> waitingRooms = new ArrayList<>(maxPlayers);

    private Cli view;
    private Server server;
    private String serverName;

    /**
     * Initialize a new server controller.
     */
    public ServerController() {
        this.view = new Cli();
        this.clients = new ArrayList<>();
        this.serverName = "Server";

        for (int i = 0; i < maxPlayers; i++) {
            this.waitingRooms.add(new Players());
        }
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }

        return instance;
    }

    public void run() {
        int port = view.readInt("At which port do you want to run the server? (0: For default port)");

        if (port > 0) {
            this.server = new Server(port);
        } else {
            this.server = new Server();
        }

        this.server.start();
    }

    /**
     * Handle a connecting client.
     *
     * @param client
     */
    public void handleClientConnection(ClientHandler client) {
        this.clients.add(client);
    }

    /**
     * Handle a disconnecting client.
     *
     * @param client
     */
    public void handleClientDisconnect(ClientHandler client) {
        if (this.clients.contains(client)) {
            this.clients.remove(client);
        }
    }

    /**
     * Add a client to a waiting room based on the amount of players requested.
     *
     * @param amount
     * @param client
     * @return Return the amount of people still waiting for.
     */
    public int joinWaitingRoom(int amount, ClientHandler client) {
        //Todo create this method
        Game game;
        int waitingFor = 0;
        Player player = new ServerPlayer(client, client.getUsername());

        if (amount > 0 && amount < 2) {
            waitingFor = -1;
            //TODO: Start a game with a computer player or return an error.
        } else if (amount >= 2 && amount <= maxPlayers) {
            Players room = waitingRooms.get(amount - 1);

            room.addPlayer(player);
            waitingFor = amount - room.getSize();

            if (waitingFor < 1) {
                game = new Game(room);
                game.start();
            }
        } else {
            waitingFor = -1;
            //TODO: Return error
        }

        return waitingFor;
    }

    /**
     * @return Return the name of the server.
     */
    public String getServerName() {
        return this.serverName;
    }

    /**
     * Check if the username is unique.
     *
     * @param username
     * @return Return true if the username is unique.
     */
    public boolean isUniqueUsername(String username) {
        for (ClientHandler client : clients) {
            if (client.getUsername() != null && client.getUsername().equals(username)) {
                return false;
            }
        }

        return true;
    }
}
