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

    // port to run server on
    private static int port;
    private static String host;

    private ArrayList<ClientController> clients;

    public QwirkleServer(int _port) {
        port = _port;
    }

    /**
     * Starts the game server on selected port
     */
    public void start() {

        this.clients = new ArrayList<>();

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
            // TODO: use CLI output
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