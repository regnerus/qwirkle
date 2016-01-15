package qwirkle.server.controllers;

// java
import java.io.IOException;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

// junit
import static org.junit.Assert.*;

// shared
import qwirkle.shared.Protocol;
import qwirkle.shared.ProtocolParser;

// server
import qwirkle.server.QwirkleServer;

/**
 * ClientController
 * Handles clients that are connecting to the game server
 */
public class ClientController extends Thread {

    private QwirkleServer server;

    private BufferedReader in;
    private BufferedWriter out;

    private int playerId;


    public ClientController(QwirkleServer _server, Socket _socket, int _playerId) throws IOException {
        playerId = _playerId;
        server = _server;

        // create in- and output stream readers
        in = new BufferedReader(
                new InputStreamReader(
                        _socket.getInputStream()));

        out = new BufferedWriter(
                new OutputStreamWriter(
                        _socket.getOutputStream()));
    }

    /**
     * A seperate thread handles all the messages to and from a client
     */
    public void run() {
        try {
            handleMessages();
        } catch (IOException e) {
            // TODO: use CLI output
            System.out.println(e);
        }
    }

    /**
     * Listen to all incoming messages from client and parse them
     * @throws IOException
     */
    public void handleMessages() throws IOException {

        // split incoming message string to find message type
        ArrayList incomingData = ProtocolParser.parse(in.readLine());

        switch ((String) incomingData.get(0)) {

            case Protocol.Client.HALLO:
                assertNotNull(incomingData.get(1));
                handleHandshake((String) incomingData.get(1));
                break;

            case Protocol.Client.QUIT:

                break;

            case Protocol.Client.INVITE:

                break;

            case Protocol.Client.ACCEPTINVITE:

                break;

            case Protocol.Client.DECLINEINVITE:

                break;

            case Protocol.Client.MAKEMOVE:

                break;

            case Protocol.Client.CHAT:

                break;

            case Protocol.Client.REQUESTGAME:

                break;

            case Protocol.Client.CHANGESTONE:

                break;

            case Protocol.Client.GETLEADERBOARD:

                break;

            case Protocol.Client.GETSTONESINBAG:

                break;

            case Protocol.Client.ERROR:
                // client is failing hard
                break;
        }
    }

    /**
     * Get the playerId of the client
     * @return int playerId
     */
    public int getPlayerId() {
        return playerId;
    }

    public void handleQuit() {

    }

    public void handleHandshake(String handshakeData) {

    }

    public void handleChat() {

    }

    public void handleLeaderboard() {

    }

    public void handleError() {

    }

    /**
     * Send a message to the client
     */
    public void emit(String data) {
        // try catch around sending a message over socket
        try {
            out.write(data);
            out.newLine();
            out.flush();
        }
        catch (IOException e) {
            // TODO: handle error in CLI
            e.printStackTrace();
        }
    }
}