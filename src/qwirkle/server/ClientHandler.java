package qwirkle.server;

import qwirkle.game.Bag;
import qwirkle.game.Hand;
import qwirkle.game.Stone;
import qwirkle.player.Player;
import qwirkle.player.ServerPlayer;
import qwirkle.shared.Protocol;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author Bouke Regnerus
 * @version 1.0
 * @since 2016-01-29
 */
public class ClientHandler extends Thread {

    private ServerPlayer player;

    private BufferedReader in;
    private BufferedWriter out;
    private Socket socket;

    private UUID clientId;

    private String username;

    /**
     * Initialize ClientHandler.
     * <p>
     * Setup input and output stream.
     *
     * @param socket
     */
    public ClientHandler(Socket socket) {
        this.socket = socket;
        clientId = UUID.randomUUID();

        try {
            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream(), Protocol.Server.Settings.ENCODING));
            out = new BufferedWriter(new OutputStreamWriter(
                    socket.getOutputStream(), Protocol.Server.Settings.ENCODING));
        } catch (IOException e) {
            //TODO: handle error.
            stopClientConnection();
        }

        System.out.println("New client connected");
    }

    /**
     * A separate thread handles all the messages to and from a client.
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
                this.stopClientConnection();
                break;
            }
        }
    }

    /**
     * Listen to all incoming messages from client and parse them.
     *
     * @param message Incoming message.
     */
    public void handleMessages(String message) {
        //DEBUG: Print out the incoming message
        //System.out.println("Message:" + message);

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
                //TODO: Handle Invite
                break;

            case Protocol.Client.ACCEPTINVITE:
                //TODO: Handle AcceptInvite
                break;

            case Protocol.Client.DECLINEINVITE:
                //TODO: Handle DeclineInvite
                break;

            case Protocol.Client.MAKEMOVE:
                List<Stone> move = new ArrayList<>();

                boolean validMove = true;

                if (params.length < 1) {
                    player.getGame().skipTurn(player);
                } else {
                    for (int i = 0; i < params.length; i++) {
                        Stone stone = Stone.fromMove(params[i]);
                        if (player.getGame().getBoard().validMove(stone)) {
                            move.add(stone);
                        } else {
                            player.getClient().handleError("7");
                            validMove = false;
                            break;
                        }
                    }

                    if (validMove) {
                        if (player.getGame().getBoard().isEmpty()) {
                            player.getGame().firstMove(player, move);
                        } else {
                            player.getGame().move(player, move);
                        }
                    }
                }
                break;

            case Protocol.Client.CHAT:
                //TODO: Handle Chat
                break;

            case Protocol.Client.REQUESTGAME:
                handleRequestGame(ServerController.getInstance()
                        .joinWaitingRoom(Integer.parseInt(params[0]), this));
                break;

            case Protocol.Client.CHANGESTONE:
                List<Stone> changeStones = new ArrayList<>();

                for (int i = 0; i < params.length; i++) {
                    changeStones.add(Stone.fromChars(params[i]));
                }

                player.getGame().switchStones(player, changeStones);
                break;

            case Protocol.Client.GETLEADERBOARD:
                handleLeaderboard();
                break;

            case Protocol.Client.GETSTONESINBAG:
                handleStonesInBag();
                break;

            case Protocol.Client.ERROR:
                //TODO: Handle Client Error
                break;
        }
    }


    /**
     * @return Return the username of the connected player.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Handle client quitting from game.
     */
    //@ ensures player != null;
    public void handleQuit() {
        // TODO: delete player
    }

    /**
     * Handle the amount of stones in the bag.
     */
    public void handleStonesInBag() {
        int stonesInBag = this.player.getGame().getBag().bagSize();

        String cmd = Protocol.Server.STONESINBAG + Protocol.Server.Settings.DELIMITER + stonesInBag;
        this.emit(cmd);
    }

    /**
     * Handle the request of a new game based on an amount of players.
     *
     * @param players Amount of players.
     */
    public void handleRequestGame(int players) {
        String cmd = Protocol.Server.OKWAITFOR + Protocol.Server.Settings.DELIMITER + players;
        this.emit(cmd);
    }

    /**
     * Handle the start of a new game based on a list of players.
     *
     * @param players List of players.
     */
    public void handleStartGame(ArrayList<Player> players) {
        String ps = "";

        for (Player p : players) {
            ps += p.getUsername() + Protocol.Server.Settings.DELIMITER;
        }

        ps = ps.substring(0, ps.length() - 1);

        String cmd = Protocol.Server.STARTGAME + Protocol.Server.Settings.DELIMITER + ps;
        emit(cmd);
    }

    /**
     * Handle handshake.
     */
    public void handleHandshake() {
        String cmd = Protocol.Server.HALLO + Protocol.Server.Settings.DELIMITER +
                ServerController.getInstance().getServerName();
        this.emit(cmd);
    }

    /**
     * Send current leaderboard to client.
     */
    public void handleLeaderboard() {
        //TODO: Handle leaderboard.
    }

    /**
     * Handle initialization of a hand.
     *
     * @param hand
     */
    public void handleInitHand(Hand hand) {
        String cmd = Protocol.Server.ADDTOHAND + Protocol.Server.Settings.DELIMITER +
                hand.toChars();
        emit(cmd);
    }

    /**
     * Handle adding stones to a hand based on the bag and amount of stones.
     *
     * @param bag    Current bag
     * @param amount Amount of stones
     */
    public void handleAddToHand(Bag bag, int amount) {
        String ms = "";

        for (int i = 0; i < amount; i++) {
            Stone stone = bag.getStone();
            this.player.getHand().addStone(stone);
            ms += stone.toChars() + Protocol.Server.Settings.DELIMITER;
        }

        ms = ms.substring(0, ms.length() - 1);

        String cmd = Protocol.Server.ADDTOHAND + Protocol.Server.Settings.DELIMITER + ms;

        emit(cmd);
    }

    /**
     * Handle an error based on an error code.
     *
     * @param errorCode
     */
    public void handleError(String errorCode) {
        String cmd = Protocol.Server.ERROR + Protocol.Server.Settings.DELIMITER + errorCode;
        emit(cmd);
    }

    /**
     * Emit a message to all connected players.
     *
     * @param cmd
     */
    public void handleEmitToAllPlayers(String cmd) {
        emit(cmd);
    }

    public Player getPlayer() {
        return player;
    }

    /**
     * Set the player for the handler.
     *
     * @param player player.
     */
    public void setPlayer(ServerPlayer player) {
        this.player = player;
    }

    /**
     * Send message to client.
     *
     * @param message message to emit to client.
     */
    public void emit(String message) {
        try {
            out.write(message);
            out.newLine();
            out.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());

            stopClientConnection();
        }
    }

    /**
     * Stop the client connection.
     */
    private void stopClientConnection() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
