package qwirkle.server.controllers;

// java
import java.io.IOException;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.UUID;

// junit
import static org.junit.Assert.*;

// game
import qwirkle.game.Game;
import qwirkle.player.Player;

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

    private Game game;
    private Player player;

    private BufferedReader in;
    private BufferedWriter out;

    private UUID clientId;


    public ClientController(QwirkleServer _server, Socket _socket, int _playerId) throws IOException {
        server = _server;
        clientId = UUID.randomUUID();

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
            // TODO: use CLI controller
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
        assertNotNull(incomingData.get(0));

        switch ((String) incomingData.get(0)) {

            case Protocol.Client.HALLO:
                assertNotNull(incomingData.get(1));
                handleHandshake((String) incomingData.get(1)); // called it handShake since HALLO is a stupid name
                break;

            case Protocol.Client.QUIT:
                handleQuit();
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
                assertNotNull(incomingData.get(1));
                handleChat((String) incomingData.get(1));
                break;

            case Protocol.Client.REQUESTGAME:

                break;

            case Protocol.Client.CHANGESTONE:

                break;

            case Protocol.Client.GETLEADERBOARD:
                handleLeaderboard();
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
    public UUID getClientId() {
        return clientId;
    }

    /**
     * Get the current game this client is playing in
     * @return Game game
     */
    //@ pure
    public Game getGame() {
        return this.game;
    }

    /**
     * Join a game
     * @param game game to accept
     */
    public void setGame(Game game) {
        this.game = game;
        // TODO: send game joined message to other clients in game
    }

    /**
     * Get player that is playing with this connection
     * @return Player player
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Set player playing with this client
     * @param player the game player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Handle client quitting from game
     */
    //@ ensures game != null;
    public void handleQuit() {
        assertNotNull(this.game);
    }

    public void handleHandshake(String handshakeData) {

    }

    /**
     * Send message to all clients in same game
     * @param message the message to send to all other clients in current game
     */
    public void handleChat(String message) {
        game.emitToAllPlayers(message);
    }

    /**
     * Send message to a specific client
     * @param message the message to send to the other client
     * @param targetPlayerId the UUID of the target client to send the message to
     */
    public void handleChat(String message, String targetPlayerId) {
        game.emitToPlayer(UUID.fromString(targetPlayerId), message);
    }

    /**
     * Send current leaderboard to client
     */
    public void handleLeaderboard() {

    }

    public void handleError() {

    }

    /**
     * Send message to client
     * @param message message to emit to client
     */
    public void emit(String message) {
        try {
            out.write(message);
            out.newLine();
            out.flush();
        }
        catch (IOException e) {
            // disconnect client since data can be corrupted from now on
            // TODO: log error
            disconnect();
        }
    }

    /**
     * Handle client disconnect
     */
    public void disconnect() {
        // TODO: log disconnect reason
        server.handleClientDisconnect(this);
    }
}