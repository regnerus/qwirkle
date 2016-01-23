package qwirkle.server;

// java
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

// controllers
import qwirkle.server.controllers.ClientController;

/**
 * The Qwirkle Server
 */
public class QwirkleServer {

    private static int port;    // port
    private static String host; // hostname

    private ArrayList<ClientController> clients;

    /**
     * Start new game server on default port
     */
    public QwirkleServer() {
        port = 1337; // use leed port :P
        start();
    }

    /**
     * Start new game server on chosen port
     * @param _port
     */

    public QwirkleServer(int _port) {
        port = _port;
        start();
    }

    /**
     * Starts the game server on selected port
     */

    //@ ensures clients != null;
    public void start() {

        clients = new ArrayList<ClientController>();

        // use try catch around setting up server, port may be in use!
        try {
            ServerSocket serverSock = new ServerSocket(port);
            host = InetAddress.getLocalHost().getHostAddress();

            while(true) {
                // start accepting socket connections
                Socket socket = serverSock.accept();

                ClientController client = new ClientController(this, socket, 0);
                handleClientConnection(client);

                // start the client (new thread)
                client.start();
            }
        }
        catch (IOException e) {
            // TODO: use CLI controller
            System.out.println(e);
        }
    }

    public void handleClientConnection(ClientController client) {
        this.clients.add(client);
    }

    /**
     * The Main Qwirkle server
     * @param args <port>
     */
    public static void main(String[] args) {

    }
}