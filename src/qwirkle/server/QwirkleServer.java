package qwirkle.server;

// java
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// controllers
import qwirkle.server.controllers.ClientController;

/**
 * The Qwirkle Server
 */
public class QwirkleServer {

    // port to run server on
    private int port;

    public QwirkleServer(int _port) {
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
                Socket socket = serverSock.accept();

                // TODO: populate ClientController
                ClientController client = new ClientController(this, socket, 0);

                // client.start();
            }
        }
        catch (IOException e) {
            // TODO: use CLI output
            System.out.println(e);
        }
    }
}