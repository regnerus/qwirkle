package qwirkle;

// server
import qwirkle.server.QwirkleServer;
import qwirkle.server.ShutdownHook;

// client

/**
 * The Qwirkle Main.
 * <p>
 * Created by Chris ter Beke and Bouke Regnerus
 */
public class Qwirkle {

    public static void main(String[] args) {

        // start new server on default port
        QwirkleServer server = new QwirkleServer();

        // start a new game manually to test
        server.startNewGame();
    }
}