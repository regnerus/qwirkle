package qwirkle.server;

import qwirkle.player.ServerPlayer;
import qwirkle.shared.Cli;
import qwirkle.shared.GameController;
import qwirkle.shared.Protocol;

import java.io.*;
import java.net.Socket;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Bouke on 26/01/16.
 */
public class ClientHandler extends Thread {

    private Server server;

    private ServerPlayer player;

    private BufferedReader in;
    private BufferedWriter out;
    private Socket socket;

    private UUID clientId;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        clientId = UUID.randomUUID();

        try {
            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream(), Protocol.Server.Settings.ENCODING));
            out = new BufferedWriter(new OutputStreamWriter(
                    socket.getOutputStream(), Protocol.Server.Settings.ENCODING));
        } catch (IOException e) {
            //TODO error logs
            System.out.println(e.getMessage());
            stopClientConnection();
        }

        System.out.println("New client connected");
    }

    /**
     * A seperate thread handles all the messages to and from a client.
     */
    @Override
    public void run() {
        while (true) {
            try {
                String message = in.readLine();
                if(message != null) {
                    handleMessages(message);
                } else {
                    stopClientConnection();
                    break;
                }
            } catch (IOException e) {
                //TODO error logs
                System.out.println(e.getMessage());
                stopClientConnection();
                break;
            }
        }
    }

    /**
     * Listen to all incoming messages from client and parse them..
     *
     * @throws IOException
     */
    public void handleMessages(String message) {
//
//        // split incoming message string to find message type
//        ArrayList incomingData = ProtocolParser.parse(in.readLine());
//        assertNotNull(incomingData.get(0));

        switch (message) {

            case Protocol.Client.HALLO:
//                assertNotNull(incomingData.get(1));
//                handleHandshake((String) incomingData.get(1)); // called it handShake since HALLO is a stupid name
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
//                assertNotNull(incomingData.get(1));
//                handleChat((String) incomingData.get(1));
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
     * Get the playerId of the client.
     *
     * @return int playerId
     */
    public UUID getClientId() {
        return clientId;
    }

    /**
     * Get the current game this client is playing in.
     *
     * @return Game game
     */
    //@ pure
    public GameController getGame() {
        return this.getPlayer().getGame();
    }

    /**
     * Join a game.
     *
     * @param gameId Id of game to join
     */
    public void joinGame(String gameId) {
        // TODO: create a new ServerPlayer for this client and let it join a game


    }

    /**
     * Get player that is playing with this connection.
     *
     * @return Player player
     */
    public ServerPlayer getPlayer() {
        return this.player;
    }

    /**
     * Handle client quitting from game.
     */
    //@ ensures player != null;
    public void handleQuit() {
        assertNotNull(player);
        player.leaveGame();
        // TODO: delete player
    }

    public void handleHandshake(String handshakeData) {

    }

    /**
     * Send message to all clients in same game.
     *
     * @param message the message to send to all other clients in current game
     */
    public void handleChat(String message) {
//        getPlayer().getGame().emitToAllPlayers(message);
    }

    /**
     * Send message to a specific client.
     *
     * @param message        the message to send to the other client
     * @param targetPlayerId the UUID of the target client to send the message to
     */
    public void handleChat(String message, String targetPlayerId) {
//        getPlayer().getGame().emitToPlayer(UUID.fromString(targetPlayerId), message);
    }

    /**
     * Send current leaderboard to client.
     */
    public void handleLeaderboard() {

    }

    public void handleError() {

    }

    /**
     * Send message to client.
     *
     * @param message message to emit to client
     */
    public void emit(String message) {
        try {
            out.write(message);
            out.newLine();
            out.flush();
        } catch (IOException e) {
            Cli.logServerError(e);

            // disconnect client since data can be corrupted from now on
            stopClientConnection();
        }
    }

    private void stopClientConnection() {
        try {
            in.close();
            out.close();
            socket.close();
//            ServerController.getInstance().removeHandler(this);
        } catch (IOException e) {
            //TODO errors
            System.out.println(e.getMessage());
        }
    }
}
