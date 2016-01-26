package qwirkle.server;

// server

// shared
import qwirkle.shared.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

// java

/**
 * The Server.
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
//        clients = new ArrayList<ClientController>();

        while(true) {
            ServerController controller = ServerController.getInstance();
//            int max_connections = controller.maxConnections();

            try {
                Socket s = server.accept();
                ClientHandler client = new ClientHandler(s);
                controller.handleClientConnection(client);
                client.start();
            } catch (IOException e) {
                //TODO error logs
                System.out.println(e.getMessage());
            }

//            if(max_connections != -1 || max_connections < controller.getHandlers().size()) {
//                try {
//                    Socket socket = server.accept();
//
//                    ClientHandler client = new ClientHandler(socket);
//                    handleClientConnection(client);
////                    controller.addHandler(client);
//                    client.start();
//                } catch (IOException e) {
//                    //TODO error logs
//                    System.out.println(e.getMessage());
//                }
//            }
        }
    }
}