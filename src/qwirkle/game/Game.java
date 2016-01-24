package qwirkle.game;

// java
import java.util.ArrayList;
import java.util.UUID;

// game
import qwirkle.player.ServerPlayer;

/**
 * Main game class
 */
public class Game {

    public static final int MAX_HANDSIZE = 6;
    public static final int TILES_OF_EACH = 3; //As defined in the game rules.

    private ArrayList<ServerPlayer> players;

    private Bag bag;
    private Board board;

    private boolean finished = false;

    /**
     * Start a new game with players and a new bag
     */
    public Game() {
        players = new ArrayList();
        bag = new Bag();
        board = new Board();
    }

    public void startGame() {

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
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getClient().getClientId() == clientId)
                players.get(i).getClient().emit(message);
        }
    }

    /**
     * Emit chat message to all human players
     * @param message
     */
    public void emitToAllPlayers(String message) {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).getClient().emit(message);
        }
    }

    public boolean isFinished() {
        return finished;
    }
}
