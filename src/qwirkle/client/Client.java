package qwirkle.client;

import qwirkle.game.Players;
import qwirkle.game.Stone;
import qwirkle.player.Player;
import qwirkle.shared.Protocol;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Bouke Regnerus
 * @version 1.0
 * @since 2016-01-29
 */
public class Client extends Thread {

    private InetAddress host;
    private int port;

    private BufferedReader in;
    private BufferedWriter out;
    private Socket socket;

    /**
     * Start new game client based on host and port.
     *
     * @param host
     * @param port
     */
    public Client(InetAddress host, int port) {
        try {
            socket = new Socket(host, port);

            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream(), Protocol.Server.Settings.ENCODING));
            out = new BufferedWriter(new OutputStreamWriter(
                    socket.getOutputStream(), Protocol.Server.Settings.ENCODING));

            System.out.println("Client started on: " + port);
        } catch (IOException e) {
            //TODO Handle Errors
        }
    }

    /**
     * Start new game client on default port.
     */
    public Client(InetAddress host) {
        this(host, Protocol.Server.Settings.DEFAULT_PORT);
    }

    /**
     * Start new game client on default host.
     */
    public Client(int port) {
        this(getLocalHost(), port);
    }

    /**
     * Start new game client on default port.
     */
    public Client() {
        this(getLocalHost(), Protocol.Server.Settings.DEFAULT_PORT);
    }

    /**
     * @return Return the localhost of the client.
     */
    private static final InetAddress getLocalHost() {
        InetAddress host = null;
        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            //TODO: Add error handling.
            System.out.println(e.getMessage());
        }

        return host;
    }

    private void startNewThread(Runnable r) {
        Thread t = new Thread(r);
        t.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                String message = in.readLine();
                if (message != null) {
                    handleMessages(message);
                } else {
                    this.stopClient();
                    break;
                }
            } catch (IOException e) {
                //TODO: Add error handling.
                this.stopClient();
                break;
            }
        }
    }

    /**
     * Listen to all incoming messages from server and parse them.
     *
     * @param message Incoming message.
     */
    public void handleMessages(String message) {
        //DEBUG: Print out the incoming message
        //System.out.println("Message:" + message);

        String[] messageArray = message.split(String.valueOf(Protocol.Server.Settings.DELIMITER));
        String command = messageArray[0];
        final String[] params = Arrays.copyOfRange(messageArray, 1, messageArray.length);

        Runnable runnable;

        switch (command) {

            case Protocol.Server.HALLO:
                runnable = () -> {
                    ClientController.getInstance().enterWaitingRoom();
                };

                this.startNewThread(runnable);
                break;

            case Protocol.Server.INVITE:
                //TODO: Handle Invite
                break;

            case Protocol.Server.DECLINEINVITE:
                //TODO: Handle Decline Invite
                break;

            case Protocol.Server.OKWAITFOR:
                ClientController.getInstance().getView().logSimple("Waiting for " +
                        params[0] + " players!");
                break;

            case Protocol.Server.STARTGAME:
                runnable = () -> {
                    List<String> opponentsList = new ArrayList<>();

                    for (String player : params) {
                        opponentsList.add(player);
                    }

                    String[] opponents = opponentsList.toArray(new String[opponentsList.size()]);

                    ClientController.getInstance().startGame(opponents);
                };

                this.startNewThread(runnable);
                break;

            case Protocol.Server.STONESINBAG:
                runnable = () -> {
                    ClientController.getInstance().setBagSize(Integer.getInteger(params[0]));
                };

                this.startNewThread(runnable);
                break;

            case Protocol.Server.MOVE:
                runnable = () -> {
                    String current = params[0];
                    String next = params[1];

                    List<Stone> move = new ArrayList<>();
                    Players players = ClientController.getInstance().getGame().getPlayers();

                    String[] stones = Arrays.copyOfRange(params, 2, params.length);
                    for (int i = 0; i < stones.length; i++) {
                        move.add(Stone.fromMove(stones[i]));
                    }

                    int bagSize = ClientController.getInstance().getBagSize();

                    ClientController.getInstance().setBagSize(bagSize - stones.length);

                    for (Player player : players.getPlayers()) {
                        if (current.equals(player.getUsername())) {
                            players.setCurrentPlayer(player);
                            ClientController.getInstance().getGame().placeStones(player, move);
                            break;
                        }
                    }

                    if (current.equals(ClientController.getInstance().getUsername())) {
                        ClientController.getInstance().getPlayer().getHand().removeStone(move);
                    }

                    ClientController.getInstance().logGame();

                    if (next.equals(ClientController.getInstance().getUsername())) {
                        ClientController.getInstance().getView().logSimple("Our Turn!");
                        ClientController.getInstance().getMove();
                    }
                };

                startNewThread(runnable);
                break;

            case Protocol.Server.ADDTOHAND:
                for (String stone : params) {
                    ClientController.getInstance().getPlayer().addToHand(Stone.fromChars(stone));

                    System.out.println(ClientController.getInstance().getPlayer()
                            .getHand().toString()); //TODO debug
                }
                break;

            case Protocol.Client.CHAT:
//              //TODO: Handle Chat
                break;

            case Protocol.Client.REQUESTGAME:
//              //TODO: Handle Request Game
                break;

            case Protocol.Server.LEADERBOARD:
                //TODO: Handle Leaderboard
                break;

            case Protocol.Server.GAME_END:
                //TODO: Handle Game End
                break;

            case Protocol.Server.ERROR:
                //TODO Handle all error messages.
                switch (params[0]) {
                    case "1":
                        //Not our turn
                        ClientController.getInstance().getView().logSimple("It wasn't our turn!");
                        break;
                    case "2":
                        //Not our Stone
                        ClientController.getInstance().getView().logSimple("It wasn't our stone!");
                        break;
                    case "3":
                        //Not enough stones
                        ClientController.getInstance().getView().logSimple("Not enough stones!");
                        break;
                    case "4":
                        //Username already taken
                        ClientController.getInstance().getView()
                                .logSimple("Username already taken!");
                        break;
                    case "5":
                        //Not Challenge
                        break;
                    case "6":
                        //Challenge Refused
                        break;
                    case "7":
                        //Invalid Move
                        ClientController.getInstance().getView().
                                logSimple("The move we tried to make was invalid!");

                        runnable = () -> {
                            ClientController.getInstance().getMove();
                        };

                        startNewThread(runnable);
                        break;
                    case "8":
                        ClientController.getInstance().getView()
                                .logSimple("An unknown error occurd!");
                        break;
                }
                break;
        }
    }

    /**
     * Handle handshake.
     *
     * @param username
     */
    public void handleHandshake(String username) {
        String cmd = Protocol.Client.HALLO + Protocol.Server.Settings.DELIMITER + username;
        emit(cmd);
    }

    /**
     * Handle request game.
     *
     * @param opponents
     */
    public void handleGameRequest(int opponents) {
        String cmd = Protocol.Client.REQUESTGAME + Protocol.Server.Settings.DELIMITER + opponents;
        emit(cmd);
    }

    /**
     * Handle change stones.
     *
     * @param stones
     */
    public void handleChangeStones(List<Stone> stones) {
        String ms = "";

        for (Stone stone : stones) {
            ms += stone.toChars() + Protocol.Server.Settings.DELIMITER;
        }

        if (ms.length() > 1) {
            ms = ms.substring(0, ms.length() - 1);
        }

        String cmd = Protocol.Client.CHANGESTONE + Protocol.Server.Settings.DELIMITER + ms;

        emit(cmd);

        this.handleMakeMove();
    }

    /**
     * Handle make move.
     *
     * @param moves
     */
    public void handleMakeMove(List<Stone> moves) {
        String ms = "";

        for (Stone stone : moves) {
            ms += stone.toMove() + Protocol.Server.Settings.DELIMITER;
        }

        if (ms.length() > 1) {
            ms = ms.substring(0, ms.length() - 1);
        }

        String cmd = Protocol.Client.MAKEMOVE + Protocol.Server.Settings.DELIMITER + ms;

        emit(cmd);
    }

    /**
     * Handle skip turn (empty make move).
     */
    public void handleMakeMove() {
        String cmd = Protocol.Client.MAKEMOVE;

        emit(cmd);
    }

    /**
     * Send message to server.
     *
     * @param message message to emit to server.
     */
    public void emit(String message) {
        try {
            out.write(message);
            out.newLine();
            out.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());

            stopClient();
        }
    }

    /**
     * Stop the server connection.
     */
    public void stopClient() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
