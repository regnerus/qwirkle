package qwirkle.server;

// server
import qwirkle.server.controllers.ClientController;

// shared
import qwirkle.shared.*;

// java
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * The Qwirkle Server.
 */
public class QwirkleServer extends Thread {

    private static int port;    // port
    private static String host; // hostname

    private ServerSocket serverSock;

    private ArrayList<ClientController> clients;
    private ArrayList<Game> games;

    /**
     * Start new game server on default port.
     */
    public QwirkleServer() throws IOException {
        port = Protocol.Server.Settings.DEFAULT_PORT; // use leed port :P
    }

    /**
     * Start new game server on chosen port.
     *
     * @param port port to start server on
     */
    public QwirkleServer(int port) throws IOException {
        this.port = port;
    }

    /**
     * Starts the game server on selected port.
     */

    //@ ensures clients != null;
    public void run() {

        // gracefully stop server and it's games
        Runtime.getRuntime().addShutdownHook(new ShutdownHook(this));

        clients = new ArrayList<ClientController>();

        // use try catch around setting up server, port may be in use!
        try {
            serverSock = new ServerSocket(port);
            host = InetAddress.getLocalHost().getHostAddress();

            while (true) {
                // start accepting socket connections
                Socket socket = serverSock.accept();

                ClientController client = new ClientController(this, socket);

                // handle the new client connection
                handleClientConnection(client);
            }
        } catch (IOException e) {
            Cli.logServerError(e);
        }
    }

    /**
     * Handle a connecting client.
     *
     * @param client
     */
    public void handleClientConnection(ClientController client) {
        this.clients.add(client);

        // start the client (in a new thread)
        client.start();
    }

    /**
     * Handle a disconnecting client.
     *
     * @param client
     */
    public void handleClientDisconnect(ClientController client) {
        // remove from clients list
        if (clients.contains(client)) {
            clients.remove(client);
        }

        // TODO: send message to other players in same game and end game
    }

    public void startNewGame() {

        // TODO: make dynamic
        Game game = new Game();
        GameView view = new GameView();
        GameController controller = new GameController(game, view);

        controller.startGame();
        controller.updateView();
    }

    public void handleGameMove() {

    }

    public void handleGameEnded() {

    }

    public void handleGameTurn() {

    }

    public void stopServer() {
        try {
            // TODO: gracefully stop running games
            // TODO: gracefully notify and close connected clients
            serverSock.close();
        } catch (Exception e) {
            Cli.logServerError(e);
        }
    }

    /**
     * New server program
     * @param args
     */
    public static void main(String[] args) {
        try {
            QwirkleServer server = new QwirkleServer();
        } catch (IOException e) {
            Cli.logServerError(e);
        }
    }
}