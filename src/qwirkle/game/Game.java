package qwirkle.game;

// java

import qwirkle.player.HumanPlayer;
import qwirkle.player.Player;
import qwirkle.shared.CliController;
import qwirkle.shared.Input;

import java.util.ArrayList;
import java.util.UUID;

// game

/**
 * Main game class
 */
public class Game {

    public static final int MAX_HANDSIZE = 6;
    public static final int TILES_OF_EACH = 3; //As defined in the game rules.

    private ArrayList<Player> players;

    private Bag bag;
    private Board board;
    private Input input;

    private Player testPlayer;

    private boolean finished = false;

    /**
     * Start a new game with players and a new bag
     */
    public Game(Input input) {
        this.players = new ArrayList();
        this.bag = new Bag();
        this.board = new Board();
        this.input = input;
    }

    public void startGame() {
        this.board = new Board();

        testPlayer = new HumanPlayer(this, "Bouke");

        this.players.add(testPlayer);

        Stone stone1 = new Stone(Stone.Color.RED, Stone.Shape.STAR);
        Stone stone2 = new Stone(Stone.Color.GREEN, Stone.Shape.STAR);
        Stone stone3 = new Stone(Stone.Color.BLUE, Stone.Shape.STAR);
        Stone stone4 = new Stone(Stone.Color.GREEN, Stone.Shape.SQUARE);
        Stone stone5 = new Stone(Stone.Color.GREEN, Stone.Shape.CIRCLE);
        Stone stone6 = new Stone(Stone.Color.GREEN, Stone.Shape.HEART);


        stone1.setLocation(0, -1);
        stone2.setLocation(0, 0);
        stone3.setLocation(0, 1);

        stone4.setLocation(-1, 0);
        stone5.setLocation(1, 0);
        stone6.setLocation(2, 0);

        ArrayList<Stone> addStones = new ArrayList<>();
        addStones.add(stone1);
        addStones.add(stone2);
        addStones.add(stone3);

        System.out.println("points: " + board.placeStones(addStones));

        addStones = new ArrayList<>();
        addStones.add(stone4);
        addStones.add(stone5);
        addStones.add(stone6);

        System.out.println("points: " + board.placeStones(addStones));
    }

    public void stopGame() {

    }

    public void getScore() {

    }

    public Bag getBag() {
        return bag;
    }

    public Board getBoard() {
        return board;
    }

    /**
     * Emit chat message to specific human player
     * @param clientId UUID of the target client
     * @param message message to send to client
     */
    public void emitToPlayer(UUID clientId, String message) {
        for (Player player : players) {
//            if (player.getClient().getClientId() == clientId)
//                player.getClient().emit(message);
        }
    }

    /**
     * Emit chat message to all human players
     * @param message
     */
    public void emitToAllPlayers(String message) {
        for (Player player : players) {
//            player.getClient().emit(message);
        }
    }

    public boolean isFinished() {
        return finished;
    }

    @Override
    public String toString() {
        String s;
        s = board.toString();
        s = s + "-----------------" + CliController.RETURN;
        s = s + "     1 2 3 4 5 6" + CliController.RETURN;
        s = s + "Hand " + testPlayer.getHand().toString() + CliController.RETURN;
        s = s + "Bag " + bag.bagSize() + CliController.RETURN;
        s = s + "-----------------" + CliController.RETURN;

        return s;
    }
}
