package qwirkle.game;

// java
import java.util.ArrayList;
import java.util.UUID;

// game
import qwirkle.player.HumanPlayer;

/**
 * Main game class
 */
public class Game {

    public static final int MAX_HANDSIZE = 6;
    public static final int TILES_OF_EACH = 3; //As defined in the game rules.

    private ArrayList<HumanPlayer> humanPlayers;

    public Game() {
        humanPlayers = new ArrayList();
    }

    public void startGame() {

    }

    public void stopGame() {

    }

    public void getScore() {

    }

    /**
     * Emit chat message to specific human player
     * @param clientId UUID of the target client
     * @param message message to send to client
     */
    public void emitToPlayer(UUID clientId, String message) {
        for (int i = 0; i < humanPlayers.size(); i++) {
            if (humanPlayers.get(i).getClient().getClientId() == clientId)
                humanPlayers.get(i).getClient().emit(message);
        }
    }

    /**
     * Emit chat message to all human players
     * @param message
     */
    public void emitToAllPlayers(String message) {
        for (int i = 0; i < humanPlayers.size(); i++) {
            humanPlayers.get(i).getClient().emit(message);
        }
    }
}
