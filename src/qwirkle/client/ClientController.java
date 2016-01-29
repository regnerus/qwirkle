package qwirkle.client;

import qwirkle.game.Board;
import qwirkle.game.Game;
import qwirkle.game.Players;
import qwirkle.game.Stone;
import qwirkle.player.ClientPlayer;
import qwirkle.player.ComputerPlayer;
import qwirkle.player.HumanPlayer;
import qwirkle.player.Player;
import qwirkle.shared.Cli;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * @author Bouke Regnerus
 * @version 1.0
 * @since 2016-01-29
 */
public class ClientController {
    private static ClientController instance = null;

    private String username;
//    private String server_ip, username;
//    private int server_port;

    private Cli view;
    private Client client;

    private ClientPlayer player;
    private int opponents;
    private Game game;
    private int bagSize;

    /**
     * Initialize new Client Controller with a new view.
     */
    public ClientController() {
        opponents = 0;
        view = new Cli();
        bagSize = (Stone.Shape.values().length - 1)
                * (Stone.Color.values().length - 1)
                * Game.TILES_OF_EACH;
    }

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }

        return instance;
    }

    /**
     * @return Return view.
     */
    public Cli getView() {
        return this.view;
    }

    public void run() {

        InetAddress host = null;

        String hostString = view.readString("At which IP do you want to run " +
                "the server? (0: For local IP)");

        if (hostString != "0") {

            try {
                host = InetAddress.getByName(hostString);
            } catch (UnknownHostException e) {
                host = null;
            }
        }

        int port = view.readInt("At which port do you want to run the server? " +
                "(0: For default port)");

        if (port > 0) {
            if (hostString == "0") {
                this.client = new Client(port);
            } else {
                this.client = new Client(host, port);
            }
        } else {
            if (hostString == "0") {
                this.client = new Client();
            } else {
                this.client = new Client(host);
            }
        }

        this.client.start();

        setUsername();
    }

    /**
     * Start game with a set amount of opponents.
     *
     * @param o Opponents
     */
    public void startGame(String[] o) {
        Players players = new Players();

        for (String opponent : o) {
            Player p = new HumanPlayer(this, opponent);

            players.addPlayer(p);
        }

        this.game = new Game(players);

        this.logGame();

        this.firstMove();
    }

    /**
     * Log game to the user.
     */
    public void logGame() {
        view.logSimple(this.game.toString());
        view.logSimple("Bag " + this.bagSize);

        view.logSimple("0 1 2 3 4 5");
        view.logSimple(this.player.getHand().toString());
    }

    /**
     * Get and submit the first move from the user.
     */
    public void firstMove() {
        ArrayList<Stone> move = this.player.determineFirstMove();

        this.client.handleMakeMove(move);
    }

    /**
     * Get and set the username from the user.
     */
    public void setUsername() {
        this.username = view.readString("What's your username?");
        this.setPlayerType();
    }

    /**
     * Get and set the requested player type by the user.
     */
    public void setPlayerType() {
        int playerType = view.readInt("Do you want to play as a user or computer player? " +
                "(1. User, 2. Computer)");
        if (playerType == 1) {
            this.setHumanPlayer(new HumanPlayer(this, this.username));
            this.client.handleHandshake(this.username);
        } else {
            this.setComputerPlayer(new ComputerPlayer(this, this.username));
            this.client.handleHandshake(this.username);
        }

    }

    /**
     * @return Return the current game.
     */
    public Game getGame() {
        return this.game;
    }

    /**
     * @return Return the username.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * @return Return the bag size.
     */
    public int getBagSize() {
        return this.bagSize;
    }

    /**
     * Set the bag size.
     *
     * @param bagSize
     */
    public void setBagSize(int bagSize) {
        this.bagSize = bagSize;
    }

    /**
     * Enter a waiting room based on the set amount of users.
     */
    public void enterWaitingRoom() {
        this.opponents = view.readInt("How many opponents do you want to play against?");

        this.bagSize = this.bagSize - (this.opponents * Game.MAX_HANDSIZE);

        this.client.handleGameRequest(this.opponents);
    }

    /**
     * Get and submit the move from the user.
     */
    public void getMove() {
        Board board = this.game.getBoard();

        player.determineMove(board, this.client);
    }

    /**
     * Set user as human player.
     *
     * @param p
     */
    public void setHumanPlayer(HumanPlayer p) {
        this.player = p;
    }

    /**
     * Set user as computer player.
     *
     * @param p
     */
    public void setComputerPlayer(ComputerPlayer p) {
        this.player = p;
    }

    /**
     * @return Return the current client player.
     */
    public ClientPlayer getPlayer() {
        return player;
    }
}