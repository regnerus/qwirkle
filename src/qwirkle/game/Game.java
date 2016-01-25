package qwirkle.game;

// java

import qwirkle.player.HumanPlayer;
import qwirkle.player.Player;
import qwirkle.shared.CliController;

import java.util.ArrayList;
import java.util.Observable;
import java.util.UUID;

//import qwirkle.shared.Input;

// game

/**
 * Main game class
 */
public class Game extends Observable {

    public static final int MAX_HANDSIZE = 6;
    public static final int TILES_OF_EACH = 3; //As defined in the game rules.

    private ArrayList<Player> players;

    private Bag bag;
    private Board board;
//    private Input input;

    private Player testPlayer;

    private boolean finished = false;

    /**
     * Start a new game with players and a new bag
     */
    public Game() {
        this.players = new ArrayList();
        this.bag = new Bag();
        this.board = new Board();
//        this.input = input;
    }

    public void startGame() {
        this.board = new Board();

        testPlayer = new HumanPlayer(this, "Bouke");
        this.addObserver(testPlayer);

        this.players.add(testPlayer);

        Stone stone1 = new Stone(Stone.Color.RED, Stone.Shape.STAR);
        Stone stone2 = new Stone(Stone.Color.GREEN, Stone.Shape.STAR);
        Stone stone3 = new Stone(Stone.Color.BLUE, Stone.Shape.STAR);
        Stone stone4 = new Stone(Stone.Color.YELLOW, Stone.Shape.STAR);
        Stone stone5 = new Stone(Stone.Color.PURPLE, Stone.Shape.STAR);
        Stone stone6 = new Stone(Stone.Color.ORANGE, Stone.Shape.STAR);

        Stone stone7 = new Stone(Stone.Color.GREEN, Stone.Shape.SQUARE);
        Stone stone8 = new Stone(Stone.Color.GREEN, Stone.Shape.CIRCLE);
        Stone stone9 = new Stone(Stone.Color.GREEN, Stone.Shape.HEART);

        Stone stone10 = new Stone(Stone.Color.RED, Stone.Shape.HEART);
        Stone stone11 = new Stone(Stone.Color.RED, Stone.Shape.SQUARE);
        Stone stone12 = new Stone(Stone.Color.RED, Stone.Shape.STAR);
        Stone stone13 = new Stone(Stone.Color.RED, Stone.Shape.CIRCLE);
        Stone stone14 = new Stone(Stone.Color.RED, Stone.Shape.CROSS);
        Stone stone15 = new Stone(Stone.Color.RED, Stone.Shape.DIAMOND);

        Stone stone16 = new Stone(Stone.Color.BLUE, Stone.Shape.HEART);
        Stone stone17 = new Stone(Stone.Color.BLUE, Stone.Shape.SQUARE);
        Stone stone18 = new Stone(Stone.Color.BLUE, Stone.Shape.CROSS);
        Stone stone19 = new Stone(Stone.Color.BLUE, Stone.Shape.CIRCLE);
        Stone stone20 = new Stone(Stone.Color.BLUE, Stone.Shape.DIAMOND);


        stone1.setLocation(0, -1);
        stone2.setLocation(0, 0);
        stone3.setLocation(0, 1);
        stone4.setLocation(0, 2);
        stone5.setLocation(0, 3);
        stone6.setLocation(0, 4);

        stone7.setLocation(-1, 0);
        stone8.setLocation(1, 0);
        stone9.setLocation(2, 0);

        stone10.setLocation(1, -1);
        stone11.setLocation(1, -2);
        stone12.setLocation(1, -3);
        stone13.setLocation(1, -4);
        stone14.setLocation(1, -5);
        stone15.setLocation(1, -6);

        stone16.setLocation(-1, 1);
        stone17.setLocation(-2, 1);
        stone18.setLocation(-3, 1);
        stone19.setLocation(1, 1);
        stone20.setLocation(2, 1);

        ArrayList<Stone> addStones = new ArrayList<>();
        addStones.add(stone1);
        addStones.add(stone2);
        addStones.add(stone3);
        addStones.add(stone4);
        addStones.add(stone5);
        addStones.add(stone6);

        System.out.println("points: " + board.placeStones(addStones));

//        addStones = new ArrayList<>();
//        addStones.add(stone7);
//        addStones.add(stone8);
//        addStones.add(stone9);
//
//        System.out.println("points: " + board.placeStones(addStones));

        addStones = new ArrayList<>();
        addStones.add(stone10);
        addStones.add(stone11);
        addStones.add(stone12);
        addStones.add(stone13);
        addStones.add(stone14);
        addStones.add(stone15);

        System.out.println("points: " + board.placeStones(addStones));

        addStones = new ArrayList<>();
        addStones.add(stone16);
        addStones.add(stone17);
        addStones.add(stone18);
        addStones.add(stone19);
        addStones.add(stone20);

        System.out.println("points: " + board.placeStones(addStones));

        System.out.println("Stone Board: " + board.coordinateToStone("a4", "a6"));

        System.out.println("Stone Hand: " + testPlayer.getHand().coordinateToStone("4"));



        this.emitToAllPlayers("game started");
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

        //TODO: notify observers with specific message.
//        for (Player player : players) {
//            if (player.getClient().getClientId() == clientId)
//                player.getClient().emit(message);
//        }
    }

    /**
     * Emit chat message to all human players
     * @param message
     */
    public void emitToAllPlayers(String message) {
        setChanged();
        notifyObservers(message);
    }

    public boolean isFinished() {
        return finished;
    }

    @Override
    public String toString() {
        String s;
        s = board.toString();
        s = s + "-----------------" + CliController.RETURN;
        s = s + "     0 1 2 3 4 5" + CliController.RETURN;
        s = s + "Hand " + testPlayer.getHand().toString() + CliController.RETURN;
        s = s + "Bag " + bag.bagSize() + CliController.RETURN;
        s = s + "-----------------" + CliController.RETURN;

        return s;
    }
}
