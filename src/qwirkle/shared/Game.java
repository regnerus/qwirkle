package qwirkle.shared;

// java

import qwirkle.game.Bag;
import qwirkle.game.Board;
import qwirkle.game.Players;
import qwirkle.player.Player;

import java.util.Observable;
import java.util.UUID;

//import qwirkle.shared.Input;

// game

/**
 * Main game class.
 */
public class Game extends Observable {

    public static final int MAX_HANDSIZE = 6;
    public static final int TILES_OF_EACH = 3; //As defined in the game rules.

    private Players players;
    private Bag bag;
    private Board board;
//    private Input input;

    private boolean finished = false;

    /**
     * Start a new game with players and a new bag.
     */
    public Game() {
        this.players = new Players();
        this.bag = new Bag();
        this.board = new Board();
//        this.input = input;
    }

    public Bag getBag() {
        return bag;
    }

    public Board getBoard() {
        return board;
    }

    public void addPlayer(Player player) {
        this.addObserver(player);
        this.players.addPlayer(player);
    }

    /**
     * Emit chat message to specific human player.
     *
     * @param clientId UUID of the target client
     * @param message  message to send to client
     */
    public void emitToPlayer(UUID clientId, String message) {

        //TODO: notify observers with specific message
        // (use observer patterns is a must described in the project description).
//        for (Player player : players) {
//            if (player.getClient().getClientId() == clientId)
//                player.getClient().emit(message);
//        }
    }

    /**
     * Emit chat message to all human players.
     *
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
        String s = "";
        s += "Players: " + players.toString() + Cli.RETURN;
        s += "Score: " + Cli.RETURN;
        s += Cli.RETURN;
        s += board.toString();
        s += Cli.RETURN;
        s += "     0 1 2 3 4 5" + Cli.RETURN;
        s += "Hand " + Cli.RETURN;
        s += "Bag " + bag.bagSize() + Cli.RETURN;
        s += Cli.RETURN;

        return s;
    }
}
