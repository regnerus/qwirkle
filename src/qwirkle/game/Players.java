package qwirkle.game;

import qwirkle.player.Player;

import java.util.ArrayList;

/**
 * @author Bouke Regnerus
 * @version 1.0
 * @since 2016-01-29
 */
public class Players {
    private ArrayList<Player> players;

    private Player currentPlayer;

    /**
     * Initialize an empty list of players.
     */
    public Players() {
        this.players = new ArrayList<>();
        this.currentPlayer = null;
    }

    /**
     * @return Return a list of all players.
     */
    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    /**
     * @return Return the amount of players.
     */
    public int getSize() {
        return this.players.size();
    }

    /**
     * Set the game for these players.
     *
     * @param game
     */
    public void setGame(Game game) {
        for (Player player : this.players) {
            player.setGame(game);
        }
    }

    /**
     * @param player Player to add.
     */
    public void addPlayer(Player player) {
        this.players.add(player);
    }

    /**
     * @return Return the player who's move it currently is.
     */
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    /**
     * Set the current move to this player.
     *
     * @param player
     */
    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }

    /**
     * Set the player who is up next as the current player.
     *
     * @return Return the player who's move is up next.
     */
    public Player nextTurn() {
        for (int i = 0; i < this.players.size(); i++) {
            if (this.currentPlayer == this.players.get(i)) {
                this.setCurrentPlayer(this.players.get((i + 1) % this.players.size()));
                return this.players.get((i + 1) % this.players.size());
            }
        }

        return this.currentPlayer;
    }

    /**
     * @return Return a textual list of players and their points.
     */
    @Override
    public String toString() {
        String s = "";

        for (int i = 0; i < this.players.size(); i++) {
            s += this.players.get(i).toString() + " (" + this.players.get(i).getPoints() + ") ";
        }

        return s;
    }
}
