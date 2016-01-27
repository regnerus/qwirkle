package qwirkle.client;

// shared

import qwirkle.game.Stone;
import qwirkle.player.ClientPlayer;
import qwirkle.shared.Protocol;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// java

/**
 * Created by chris on 26/01/16.
 */
public class Client extends Thread {

    private InetAddress host;
    private int port;

    private BufferedReader in;
    private BufferedWriter out;
    private BufferedReader clientInput;
    private Socket socket;

    public Client(InetAddress host, int port) {
//        running = true;

        try {
            socket = new Socket(host, port);

            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream(), Protocol.Server.Settings.ENCODING));
            out = new BufferedWriter(new OutputStreamWriter(
                    socket.getOutputStream(), Protocol.Server.Settings.ENCODING));

            System.out.println("Client started on: " + port);
        } catch (IOException e) {
            //TODO error logs
            System.out.println(e.getMessage());
//            shutdown();
        }
    }

    public Client() {
        this(getLocalHost(), Protocol.Server.Settings.DEFAULT_PORT);
    }

    public Client(InetAddress host) {
        this(host, Protocol.Server.Settings.DEFAULT_PORT);
    }

    private static final InetAddress getLocalHost() {
        InetAddress host = null;
        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
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
                //TODO error logs
                System.out.println(e.getMessage());
                this.stopClient();
                break;
            }
        }
    }

    public void handleMessages(String message) {
        System.out.println("Message:" + message);

        String[] messageArray = message.split(String.valueOf(Protocol.Server.Settings.DELIMITER));
        String command = messageArray[0];
        final String[] params = Arrays.copyOfRange(messageArray, 1, messageArray.length);

        Runnable runnable;

        switch (command) {

            case Protocol.Server.HALLO:
                runnable = () -> {
                    ClientPlayer player = new ClientPlayer(ClientController.getInstance().getUsername());
                    ClientController.getInstance().setPlayer(player);
                    ClientController.getInstance().enterWaitingRoom();
                };

                this.startNewThread(runnable);
                break;

            case Protocol.Server.INVITE:

                break;

            case Protocol.Server.DECLINEINVITE:

                break;

            case Protocol.Server.OKWAITFOR:
                System.out.println("Waiting for " + params[0] + " players!");
//                ClientController.getInstance().getUI().message("Waiting for " + params[0] + " players...");
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

                break;

            case Protocol.Server.MOVE:
                runnable = () -> {
                    String current = params[0];
                    String next = params[1];

                    //It is not our own move.
                    if (!current.equals(ClientController.getInstance().getUsername())) {
//                        String[] stones = params[2].split(Character.toString(Protocol.Server.Settings.DELIMITER));
//                         move = new ();
//
//                        for(String stone : stones) {
//                            String[] parts = stone.split(Character.toString(Protocol.Server.Settings.DELIMITER2));
//
//                            Tile t = Tile.fromChars(parts[0]);
//
//                            move.addTile(t, Utils.toInt(parts[1]), Utils.toInt(parts[2]));
//                        }

                        if (next.equals(ClientController.getInstance().getUsername())) { //It is our turn now
                            System.out.println("Our turn"); //TODO debug
                        }
                    }
                };

                startNewThread(runnable);
                break;

            case Protocol.Server.ADDTOHAND:
                for (String stone : params) {
                    ClientController.getInstance().getPlayer().addToHand(Stone.fromChars(stone));

                    System.out.println(ClientController.getInstance().getPlayer().getHand().toString()); //TODO debug
                }
                break;

            case Protocol.Client.CHAT:
//                assertNotNull(incomingData.get(1));
//                handleChat((String) incomingData.get(1));
                break;

            case Protocol.Client.REQUESTGAME:
//                handleRequestGame(ServerController.getInstance().joinLobby(params[0], this));
                break;

            case Protocol.Server.LEADERBOARD:

                break;

            case Protocol.Server.GAME_END:
                // client is failing hard
                break;

            case Protocol.Server.ERROR:
                // client is failing hard
                break;
        }
    }

    public void handleHandshake(String username) {
        String cmd = Protocol.Client.HALLO + Protocol.Server.Settings.DELIMITER + username;
        emit(cmd);
    }

    public void handleGameRequest(int opponents) {
        String cmd = Protocol.Client.REQUESTGAME + Protocol.Server.Settings.DELIMITER + opponents;
        emit(cmd);
    }

    public void handleMakeMove(List<Stone> moves) {
        String ms = "";

        for(Stone stone : moves) {
            ms += stone.toMove() + Protocol.Server.Settings.DELIMITER;
        }

        ms = ms.substring(0, ms.length() - 1);

        String cmd = Protocol.Client.MAKEMOVE + Protocol.Server.Settings.DELIMITER + ms;

        emit(cmd);
    }

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
