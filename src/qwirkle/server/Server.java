package qwirkle.server;

import qwirkle.game.Game;
import qwirkle.shared.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @author Bouke Regnerus
 * @version 1.0
 * @since 2016-01-29
 */
public class Server extends Thread {

    private static int port;    // port
    private static String host; // hostname

    private ServerSocket server;

    private ArrayList<ClientHandler> clients;
    private ArrayList<Game> games;

    /**
     * Start new game server on default port.
     */
    public Server() {
        this(Protocol.Server.Settings.DEFAULT_PORT);
    }

    /**
     * Start new game server on chosen port.
     *
     * @param port port to start server on
     */
    public Server(int port) {
        this.port = port;

        try {
            this.server = new ServerSocket(port);
        } catch (IOException e) {
            //TODO error logs
            System.out.println(e.getMessage());
        }

        System.out.println("Server started on: " + port);
    }

    /**
     * Starts the game server on selected port.
     */

    //@ ensures clients != null;
    @Override
    public void run() {
        while (true) {
            ServerController controller = ServerController.getInstance();

            try {
                Socket s = server.accept();
                ClientHandler client = new ClientHandler(s);
                controller.handleClientConnection(client);
                client.start();
            } catch (IOException e) {
                //TODO handle error logs.
            }
        }
    }
}