package qwirkle.shared;

// java

import qwirkle.game.*;
import qwirkle.player.Player;

import java.util.*;

//import qwirkle.shared.Input;

// game

/**
 * Main game class.
 */
public class Game extends Observable {

    public static final int MAX_HANDSIZE = 6;
    public static final int TILES_OF_EACH = 3; //As defined in the game rules.

    public Players players;
    private Bag bag;
    private Board board;

    private Map<Player, List<Stone>> firstMoves;

//    private Input input;

    private boolean finished = false;

    /**
     * Start a new game with players and a new bag.
     */
    public Game() {
        this.players = new Players();
        this.bag = new Bag();
        this.board = new Board();
        this.firstMoves = new HashMap<>();
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

    public Player nextTurn() {
        return this.players.nextTurn();
    }

    public List<Stone> firstMove(Player player, List<Stone> stones) {
        List<Stone> row = new ArrayList<>();

        for(int i = 0; i < stones.size(); i++) {
            stones.get(i).setLocation(i, 0);

            if(board.validateList(row, stones.get(i))) {
                row.add(stones.get(i));
            }
            else {
                this.firstMoves.put(player, new ArrayList<>());
                return new ArrayList<>();
            }
        }

        this.firstMoves.put(player, row);
        return row;
    }

    public Player longestRow() {
        Player player = null;
        List<Stone> row = new ArrayList<>();

        for (Map.Entry<Player, List<Stone>> entry : this.firstMoves.entrySet()) {
            if(entry.getValue().size() > row.size()) {
                row = entry.getValue();
                player = entry.getKey();
            }
        }

        placeStones(player, row);
        this.players.setCurrentPlayer(player);

        return player;
    }

    public int placeStones(Player player, List<Stone> stones) {
        int points = this.board.placeStones(stones);
        if (points > 0) {
            for(Stone stone : stones) {
                player.getHand().removeStone(stone);
            }
            player.addPoints(points);
        }

        return points;
    }

    public void switchStones(Player player, List<Stone> stones) {
        for(Stone stone : stones) {
            player.getHand().switchStone(stone);
        }
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
