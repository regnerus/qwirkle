package qwirkle.server;

// java

import qwirkle.server.controllers.ClientController;
import qwirkle.shared.Cli;
import qwirkle.shared.GameController;
import qwirkle.shared.Protocol;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

// controllers
// shared

/**
 * The Qwirkle Server.
 */
public class QwirkleServer {

    private static int port;    // port
    private static String host; // hostname

    private ArrayList<ClientController> clients;

    /**
     * Start new game server on default port.
     */
    public QwirkleServer() {
        port = Protocol.Server.Settings.DEFAULT_PORT; // use leed port :P
        start();
    }

    /**
     * Start new game server on chosen port.
     *
     * @param _port
     */

    public QwirkleServer(int p) {
        port = p;
        start();
    }

    /**
     * Starts the game server on selected port.
     */

    //@ ensures clients != null;
    public void start() {

        clients = new ArrayList<ClientController>();

        // use try catch around setting up server, port may be in use!
        try {
            ServerSocket serverSock = new ServerSocket(port);
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

    public void handleGameMove() {

    }

    public void handleGameEnded() {

    }

    public void handleGameTurn() {

    }

    public void stopServer() {
        try {
            // TODO: gracefully close connected clients
        } catch (Exception e) {
            Cli.logServerError(e);
        }
        System.exit(0);
    }
}