package qwirkle.server;

// needed packages for networking
import java.io.IOException;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

// shared
import qwirkle.shared.Protocol;

// controllers
import qwirkle.server.controllers.ClientController;

/**
 * The Qwirkle Server
 */
public class Server {

    // port to run server on
    private int port;

    public Server(int _port) {
        port = _port;


    }

    /**
     * Starts the game server on selected port
     */
    public void start() {
        // use try catch around setting up server, port may be in use!
        try {
            ServerSocket serverSock = new ServerSocket(port);

            while(true) {
                // start accepting socket connections
                Socket sock = serverSock.accept();

                // TODO: populate ClientController
                ClientController client = new ClientController();

                // client.start();
            }
        }
        catch (IOException e) {
            // TODO: use CLI output
            System.out.println(e);
        }
    }

    public void handleClientInput(String msg, ClientController client) {

        // the protocol states we should split the incoming string
        String input = msg;
        String[] split = msg.split(" ");

        switch (split[0]) {
            case Protocol.HALLO:
                // do something with HALLO
                break;
        }
    }
}