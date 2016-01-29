package qwirkle.game;

import qwirkle.player.Player;
import qwirkle.player.ServerPlayer;
import qwirkle.shared.Cli;
import qwirkle.shared.Protocol;

import java.util.*;

/**
 * @author Bouke Regnerus
 * @version 1.0
 * @since 2016-01-29
 */
public class Game extends Observable {

    public static final int MAX_HANDSIZE = 6;
    public static final int TILES_OF_EACH = 3; //As defined in the game rules.

    public Players players;
    private Bag bag;
    private Board board;

    private Map<Player, List<Stone>> firstMoves;

    private boolean finished = false;

    /**
     * Start a new game with players and an empty bag.
     */
    public Game(Players players) {
        this.bag = new Bag();
        this.board = new Board();
        this.firstMoves = new HashMap<>();

        this.players = players;
        this.players.setGame(this);

        players.getPlayers().forEach(this::addObserver);
    }

    /**
     * Start a new game with an empty list of players.
     */
    public Game() {
        this(new Players());
    }

    /**
     * @return Return the bag from the current game.
     */
    public Bag getBag() {
        return this.bag;
    }

    /**
     * @return Return the board of the current game.
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * @return Return the players of the current game.
     */
    public Players getPlayers() {
        return this.players;
    }

    /**
     * Add a first move to the list of first moves for a given player.
     *
     * @param player Player which first move it is.
     * @param stones List of stones in the move.
     */
    public void firstMove(Player player, List<Stone> stones) {
        List<Stone> row = new ArrayList<>();

        for (int i = 0; i < stones.size(); i++) {
            stones.get(i).setLocation(i, 0);

            if (board.validateList(row, stones.get(i))) {
                row.add(stones.get(i));
            } else {
                this.firstMoves.put(player, new ArrayList<>());
            }
        }

        this.firstMoves.put(player, row);

        if (this.firstMoves.size() == this.players.getSize()) {
            System.out.println(this.firstMoves);
            Player winnerFirstRow = this.longestRow();

            this.players.getPlayers().stream().filter(p -> p instanceof ServerPlayer).forEach(p -> {
                if (p == winnerFirstRow) {
                    this.move(p, this.firstMoves.get(p));
                } else {
                    ((ServerPlayer) p).getClient().handleError("1");
                }
            });
        }
    }

    /**
     * Handle a move from a player and emit this to all players connected to the game.
     *
     * @param player Player which move it is.
     * @param stones List of stones in the move.
     */
    public void move(Player player, List<Stone> stones) {
        this.placeStones(player, stones);

        emitToAllPlayers(this.getMoveCommand(player, this.players.nextTurn(), stones));

        ((ServerPlayer) player).getClient().handleAddToHand(this.bag, stones.size());

        player.getHand().removeStone(stones);
    }

    /**
     * A command in the given format described by the protocol.
     *
     * @param currentPlayer
     * @param nextPlayer
     * @param moves
     * @return Return command string.
     */
    public String getMoveCommand(Player currentPlayer, Player nextPlayer, List<Stone> moves) {
        String ms = "";

        for (Stone stone : moves) {
            ms += stone.toMove() + Protocol.Server.Settings.DELIMITER;
        }

        if (ms.length() > 1) {
            ms = ms.substring(0, ms.length() - 1);
        }

        String cmd = Protocol.Server.MOVE + Protocol.Server.Settings.DELIMITER
                + currentPlayer.getUsername() + Protocol.Server.Settings.DELIMITER
                + nextPlayer.getUsername() + Protocol.Server.Settings.DELIMITER + ms;

        return cmd;
    }


    /**
     * An empty move command for skip or switch turns.
     *
     * @param currentPlayer
     * @param nextPlayer
     * @return Return command string.
     */
    public String getMoveCommand(Player currentPlayer, Player nextPlayer) {
        String cmd = Protocol.Server.MOVE + Protocol.Server.Settings.DELIMITER
                + currentPlayer.getUsername() + Protocol.Server.Settings.DELIMITER
                + nextPlayer.getUsername();

        return cmd;
    }

    /**
     * @return Return the player which has the longest row in first moves.
     */
    public Player longestRow() {
        Player player = null;
        List<Stone> row = new ArrayList<>();

        for (Map.Entry<Player, List<Stone>> entry : this.firstMoves.entrySet()) {
            if (entry.getValue().size() > row.size()) {
                row = entry.getValue();
                player = entry.getKey();
            }
        }

        this.players.setCurrentPlayer(player);

        return player;
    }

    /**
     * Place stones on the board for a given player.
     * <p>
     * Place stones on the board for a given player, removes stones from the players hand
     * and also adds points for the  move to the given player.
     *
     * @param player
     * @param stones List of stones to be placed.
     * @return
     */
    public int placeStones(Player player, List<Stone> stones) {
        int points = this.board.placeStones(stones);
        if (points > 0) {
            for (Stone stone : stones) {
                player.getHand().removeStone(stone);
            }
            player.addPoints(points);
        }

        return points;
    }

    /**
     * Switch stones on the board for a given player.
     *
     * @param player
     * @param stones List of stones to be switched.
     * @return
     */
    public void switchStones(Player player, List<Stone> stones) {
        for (Stone stone : stones) {
            player.getHand().switchStone(stone);
        }

        ((ServerPlayer) player).getClient().handleAddToHand(this.bag, stones.size());

        player.getHand().removeStone(stones);
    }

    /**
     * Skip a turn for the given player.
     *
     * @param player
     */
    public void skipTurn(Player player) {
        emitToAllPlayers(this.getMoveCommand(player, this.players.nextTurn()));
    }


    /**
     * Emit chat message to all players connected to the game.
     *
     * @param message
     */
    public void emitToAllPlayers(String message) {
        setChanged();
        notifyObservers(message);
    }

    public void start() {
        players.getPlayers().stream().filter(player -> player instanceof ServerPlayer).forEach(player -> {
            ((ServerPlayer) player).getClient().handleStartGame(players.getPlayers());
        });
    }

    /**
     * @return Return is the game finished.
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * @return Return the game as ASCII output.
     */
    @Override
    public String toString() {
        String s = "";
        s += "Players: " + players.toString() + Cli.RETURN;
        s += Cli.RETURN;
        s += board.toString();
        s += Cli.RETURN;

        return s;
    }
}
