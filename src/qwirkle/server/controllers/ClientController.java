package qwirkle.server.controllers;

// java
import java.io.IOException;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// shared
import qwirkle.shared.Protocol;

// server
import qwirkle.server.QwirkleServer;

/**
 * ClientController
 * Handles clients that are connecting to the game server
 */
public class ClientController extends Thread {

    private BufferedReader in;
    private BufferedWriter out;

    private int playerId;


    public ClientController(QwirkleServer server, Socket _socket, int _playerId) throws IOException {
        this.playerId = _playerId;

        in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(_socket.getOutputStream()));
    }

    /**
     * Do this when successfully connected to server
     */
    public void handleConnect() {

    }

    public void handleMessage(String msg, ClientController client) {

        // the protocol states we should split the incoming string
        String[] input = msg.split(" ");

        switch (input[0]) {

            case Protocol.Client.HALLO:
                // do something with HALLO
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

    /**
     * Handles a disconnect of the client
     */
    public void handleDisconnect() {

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