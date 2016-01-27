package qwirkle.server;

import qwirkle.game.Players;
import qwirkle.player.HumanPlayer;
import qwirkle.player.Player;
import qwirkle.game.Game;

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

    private int server_port, max_connections;

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

    public void startNewGame() {
        // TODO: make dynamic
//        Game game = new Game();
//        GameView view = new GameView();
//        GameController controller = new GameController(game, view);
//
//        controller.startGame();
//        controller.updateView();
    }

    public void handleGameMove() {

    }

    public void handleGameEnded() {

    }

    public void handleGameTurn() {

    }

    public int joinWaitingRoom(int amount, ClientHandler client) {
        //Todo create this method
        Game game;
        Players players;
        int waitingFor = 0;
        Player player = new HumanPlayer(client, client.getUsername());

        if (amount > 0 && amount < 2) {
            //TODO: Computer Player
        } else if (amount >= 2 && amount <= MAX_PLAYERS) {
            Players room = waitingRooms.get(amount - 1);

            room.addPlayer(player);
            waitingFor = amount - room.getSize();

            if(waitingFor < 1) {
                game = new Game(room);
            }
        } else {
            //TODO: Error
        }

//
//        switch (amount) {
//            default:
//            case "0":
//            case "1":
//                //Todo start game with new computer player
//                break;
//            case "2":
//                List<Player> room = waitingRooms.get(0);
//
//                room.add(player);
//                waitingFor = 2 - room.size();
//
//                if (room.size() == 2) {
////                    g = new Game(lobby_2);
////                    games.add(g);
//                }
//                break;
//            case "3":
//                lobby_3.add(p);
//                waitingFor = 3 - lobby_3.size();
//
//                if (lobby_3.size() == 3) {
//                    g = new Game(lobby_3);
//                    games.add(g);
////                    g.start();
//                }
//                break;
//            case "4":
//                lobby_4.add(p);
//                waitingFor = 4 - lobby_4.size();
//
//                if (lobby_4.size() == 4) {
//                    g = new Game(lobby_4);
//                    games.add(g);
//                }
//                break;
//        }

        return waitingFor;
    }

    public void stopServer() {
//        try {
//            // TODO: gracefully stop running games
//            // TODO: gracefully notify and close connected clients
//            server.close();
//        } catch (Exception e) {
//            Cli.logServerError(e);
//        }
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
