package qwirkle.server;

import qwirkle.player.Player;
import qwirkle.shared.Protocol;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by Bouke on 26/01/16.
 */
public class ClientHandler extends Thread {

    private Server server;

    private Player player;

    private BufferedReader in;
    private BufferedWriter out;
    private Socket socket;

    private UUID clientId;

    private String username;

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
                if (message != null) {
                    handleMessages(message);
                } else {
                    this.stopClientConnection();
                    break;
                }
            } catch (IOException e) {
                //TODO error logs
                System.out.println(e.getMessage());
                this.stopClientConnection();
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
        System.out.println("Message:" + message);

        String[] messageArray = message.split(String.valueOf(Protocol.Server.Settings.DELIMITER));
        String command = messageArray[0];
        final String[] params = Arrays.copyOfRange(messageArray, 1, messageArray.length);

        switch (command) {

            case Protocol.Client.HALLO:
                if (ServerController.getInstance().isUniqueUsername(params[0])) {
                    System.out.println("Hello " + params[0]);
                    handleHandshake();
                    this.username = params[0];
                } else {
                    handleError("1");
                }
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
                handleRequestGame(ServerController.getInstance().joinWaitingRoom(Integer.parseInt(params[0]), this));
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
//    public GameController getGame() {
////        return this.getPlayer().getGame();
//    }

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
//    public ServerPlayer getPlayer() {
//        return this.player;
//    }

    public String getUsername() {
        return this.username;
    }

    /**
     * Handle client quitting from game.
     */
    //@ ensures player != null;
    public void handleQuit() {
//        player.leaveGame();
        // TODO: delete player
    }

    public void handleRequestGame(int players) {
        String cmd = Protocol.Server.OKWAITFOR + Protocol.Server.Settings.DELIMITER + players;
        this.emit(cmd);
    }

    public void handleHandshake() {
        String cmd = Protocol.Server.HALLO + Protocol.Server.Settings.DELIMITER + ServerController.getInstance().getServerName();
        this.emit(cmd);
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

    public void handleError(String errorCode) {
        String cmd = Protocol.Server.ERROR + Protocol.Server.Settings.DELIMITER + errorCode;
        emit(cmd);
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
            System.out.println(e.getMessage());

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
