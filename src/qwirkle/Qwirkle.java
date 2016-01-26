package qwirkle;

// server
import qwirkle.server.QwirkleServer;

// client
import qwirkle.client.QwirkleClient;

// java
import java.io.IOException;

/**
 * The Qwirkle Main.
 * <p>
 * Created by Chris ter Beke and Bouke Regnerus
 */
public class Qwirkle {

    public static void main(String[] args) {

        // TODO: ask to start client or server when not running either one directly

        // test server
        try {
            // create new server on default port
            QwirkleServer server = new QwirkleServer();

            // start a new server manually to test
            // server.run();

            // test starting new game
            server.startNewGame();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // test client
        try {
            // create a new client connect to default port
            QwirkleClient client = new QwirkleClient("127.0.0.1");

            // start a new client to manually test
            // client.run();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}