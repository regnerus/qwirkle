package qwirkle.client;

import qwirkle.game.Board;
import qwirkle.game.Game;
import qwirkle.game.Players;
import qwirkle.game.Stone;
import qwirkle.player.ClientPlayer;
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

    private HumanPlayer player;
    private Game game;

    public ClientController() {
//        ui = new TUI();
        view = new Cli();

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
            Player p = new ClientPlayer(opponent);

            players.addPlayer(p);
        }

        this.game = new Game(players);

        this.logGame();

        this.firstMove();

    }

    public void logGame() {
        view.logSimple(this.game.toString());
        view.logSimple(this.player.getHand().toString());
    }

    public void firstMove() {
        ArrayList<Stone> move = this.player.determineFirstMove();

        this.client.handleMakeMove(move);
    }


    public void setUsername() {
        this.username = view.readString("What's your username?");
        this.client.handleHandshake(username);
    }

    public Game getGame() {
        return this.game;
    }

    public String getUsername() {
        return this.username;
    }
//
//    public void pause() {
//        try {
//            count = new CountDownLatch(1);
//            count.await();
//        } catch (InterruptedException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public void resume() {
//        count.countDown();
//    }

    public void enterWaitingRoom() {
        int opponents = view.readInt("How many opponents?");
        this.client.handleGameRequest(opponents);
    }

    //    public void startLobby() {
//        //TODO change if challenging has to be supported
//        Numeric num = new Numeric("The amount of players has to be numeric.");
//        InRange range = new InRange(0, 4, "Only games with up to 4 players are supported.");
//
//        String numOfPlayers = ui.getValidatedInput("Number of opponents? [0..4]", new Validator[] {num, range});
//        this.communication.requestGame(numOfPlayers);
//    }
//
//    /**
//     * Start a new game.
//     * @param opponents
//     */
//    public void startGame(String[] opponents) {
//        List<Player> players = new ArrayList<>();
//        for(String opponent : opponents) {
//            Player p = new HumanPlayer(null);
//            p.setUsername(opponent);
//
//            players.add(p);
//        }
//
//        this.game = new Game(players);
//
//        //We need to make a first move.
//         m = player.determineMove();
//    }
//
    public void getMove() {
        Board board = this.game.getBoard();

        ArrayList<Stone> move = player.determineMove(board);

        this.client.handleMakeMove(move);
    }
//
    public void setPlayer(HumanPlayer player) {
        this.player = player;
    }

    public HumanPlayer getPlayer() {
        return player;
    }
}