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

import java.util.ArrayList;

/**
 * Created by Bouke on 26/01/16.
 */
public class ClientController {
    private static ClientController instance = null;

    private String username;
//    private String server_ip, username;
//    private int server_port;

    //    private TUI ui;
    private Cli view;
    private Client client;

    private ClientPlayer player;
    private int opponents;
    private Game game;
    private int bagSize;

    public ClientController() {
//        ui = new TUI();
        opponents = 0;
        view = new Cli();
        bagSize = (Stone.Shape.values().length - 1) * (Stone.Color.values().length - 1) * Game.TILES_OF_EACH;
    }

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }

        return instance;
    }

//    /**
//     * Returns the UI
//     *
//     * @return Userinterface
//     */
//    public TUI getUI() {
//        return ui;
//    }

//    public void run() {
////        getServerInfo();
////        getUsername();
//    }

    public Cli getView() {
        return this.view;
    }

    public void run() {
        this.client = new Client();
        this.client.start();

        setUsername();
    }

    public void startGame(String[] opponents) {
        Players players = new Players();

        for (String opponent : opponents) {
            Player p = new HumanPlayer(this, opponent);

            players.addPlayer(p);
        }

        this.game = new Game(players);

        this.logGame();

        this.firstMove();
    }

    public void logGame() {
        view.logSimple(this.game.toString());
        view.logSimple("Bag " + this.bagSize);

        view.logSimple("0 1 2 3 4 5");
        view.logSimple(this.player.getHand().toString());
    }

    public void firstMove() {
        ArrayList<Stone> move = this.player.determineFirstMove();

        this.client.handleMakeMove(move);
    }

    public void setUsername() {
        this.username = view.readString("What's your username?");
        this.setPlayerType();
    }

    public void setPlayerType() {
        int playerType = view.readInt("Do you want to play as a user or computer player? (1. User, 2. Computer)");
        if (playerType == 1) {
            this.setHumanPlayer(new HumanPlayer(this, this.username));
            this.client.handleHandshake(this.username);
        } else {
            this.setComputerPlayer(new ComputerPlayer(this, this.username));
            this.client.handleHandshake(this.username);
        }

    }

    public Game getGame() {
        return this.game;
    }

    public String getUsername() {
        return this.username;
    }

    public int getBagSize() {
        return this.bagSize;
    }

    public void setBagSize(int bagSize) {
        this.bagSize = bagSize;
    }

    public void enterWaitingRoom() {
        this.opponents = view.readInt("How many opponents?");

        this.bagSize = this.bagSize - (this.opponents * Game.MAX_HANDSIZE);

        this.client.handleGameRequest(this.opponents);
    }

    public void getMove() {
        Board board = this.game.getBoard();

        player.determineMove(board, this.client);
    }

    //
    public void setHumanPlayer(HumanPlayer player) {
        this.player = player;
    }

    public void setComputerPlayer(ComputerPlayer player) {
        this.player = player;
    }

    public ClientPlayer getPlayer() {
        return player;
    }
}