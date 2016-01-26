package qwirkle.server;

import java.util.ArrayList;

/**
 * Created by Bouke on 26/01/16.
 */
public class ServerController {
    private static ServerController instance = null;
    private final ArrayList<ClientHandler> clients;

    private int server_port, max_connections;

    private Server server;
    private String serverName;

    public ServerController() {
        //TODO: Add view
        this.clients = new ArrayList<>();
        this.serverName = "Server";
    }

    public static ServerController getInstance() {
        if(instance == null) {
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

    public void stopServer() {
//        try {
//            // TODO: gracefully stop running games
//            // TODO: gracefully notify and close connected clients
//            server.close();
//        } catch (Exception e) {
//            Cli.logServerError(e);
//        }
    }
}
