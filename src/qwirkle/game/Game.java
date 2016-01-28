package qwirkle.game;

// java

import qwirkle.player.Player;
import qwirkle.player.ServerPlayer;
import qwirkle.shared.Cli;
import qwirkle.shared.Protocol;

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
    public Game(Players players) {
        this.bag = new Bag();
        this.board = new Board();
        this.firstMoves = new HashMap<>();

        this.players = players;
        this.players.setGame(this);

        players.getPlayers().forEach(this::addObserver);
    }

    public Game() {
        this(new Players());
    }

    public Bag getBag() {
        return bag;
    }

    public Board getBoard() {
        return board;
    }

    public void addPlayer(Player player) {
        player.setGame(this);
        this.addObserver(player);
        this.players.addPlayer(player);
    }

    public Player nextTurn() {
        return this.players.nextTurn();
    }

    public void firstMove(Player player, List<Stone> stones) {
        List<Stone> row = new ArrayList<>();

        for(int i = 0; i < stones.size(); i++) {
            stones.get(i).setLocation(i, 0);

            if(board.validateList(row, stones.get(i))) {
                row.add(stones.get(i));
            }
            else {
                this.firstMoves.put(player, new ArrayList<>());
            }
        }

        this.firstMoves.put(player, row);

        if(this.firstMoves.size() == this.players.getSize()) {
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

    public void move(Player player, List<Stone> stones) {
        emitToAllPlayers(this.getMoveCommand(player, this.players.nextTurn(), stones));

        ((ServerPlayer) player).getClient().handleAddToHand(this.bag, stones.size());

        player.getHand().removeStone(stones);
    }

    public String getMoveCommand(Player currentPlayer, Player nextPlayer, List<Stone> moves) {
        String ms = "";

        for(Stone stone : moves) {
            ms += stone.toMove() + Protocol.Server.Settings.DELIMITER;
        }

        ms = ms.substring(0, ms.length() - 1);

        String cmd = Protocol.Server.MOVE + Protocol.Server.Settings.DELIMITER + currentPlayer.getUsername() + Protocol.Server.Settings.DELIMITER + nextPlayer.getUsername() + Protocol.Server.Settings.DELIMITER + ms;

        return cmd;
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
//        for(Stone stone : stones) {
//            player.getHand().switchStone(stone);
//        }
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

    public void start() {
        for(Player player : players.getPlayers()) {
            if(player instanceof ServerPlayer) {
                ((ServerPlayer) player).getClient().handleStartGame(players.getPlayers());
            } else {
                //TODO computer player
//                players.get(i).determineMove(getBoard().deepCopy());
            }
        }
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
        s += "Bag " + bag.bagSize() + Cli.RETURN;
        s += Cli.RETURN;

        return s;
    }
}
