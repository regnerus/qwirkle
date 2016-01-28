package qwirkle.server;

import qwirkle.game.Game;
import qwirkle.game.Players;
import qwirkle.player.Player;
import qwirkle.player.ServerPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bouke on 26/01/16.
 */
public class ServerController {
    private static ServerController instance = null;
    private final ArrayList<ClientHandler> clients;

    private static int MAX_PLAYERS = 4;

    List<Players> waitingRooms = new ArrayList<>(MAX_PLAYERS);

    private Server server;
    private String serverName;

    public ServerController() {
        //TODO: Add view
        this.clients = new ArrayList<>();
        this.serverName = "Server";

        for (int i = 0; i < MAX_PLAYERS; i++) {
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
        this.server = new Server();
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
        // remove from clients list
        if (this.clients.contains(client)) {
            this.clients.remove(client);
        }

        // TODO: send message to other players in same game and end game
    }

    public int joinWaitingRoom(int amount, ClientHandler client) {
        //Todo create this method
        Game game;
        int waitingFor = 0;
        Player player = new ServerPlayer(client, client.getUsername());

        if (amount > 0 && amount < 2) {
            //TODO: Computer Player

//            Players room = new Players();
//            room.addPlayer(player);


        }
        else if (amount >= 2 && amount <= MAX_PLAYERS) {
            Players room = waitingRooms.get(amount - 1);

            room.addPlayer(player);
            waitingFor = amount - room.getSize();

            if(waitingFor < 1) {
                game = new Game(room);
                game.start();
            }
        } else {
            //TODO: Error
        }

        return waitingFor;
    }

    public String getServerName() {
        return this.serverName;
    }

    public boolean isUniqueUsername(String username) {
        for (ClientHandler client : clients) {
            if (client.getUsername() != null && client.getUsername().equals(username)) {
                return false;
            }
        }

        return true;
    }
}
