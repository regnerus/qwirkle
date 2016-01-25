package qwirkle.game;

import qwirkle.player.Player;

import java.util.ArrayList;

/**
 * Created by Bouke on 25/01/16.
 */
public class Players {
    private ArrayList<Player> players;

    private Player currentPlayer;

    public Players() {
        this.players = new ArrayList<>();
        this.currentPlayer = null;
    }

    public Players(ArrayList<Player> players) {
        this.players = players;
        this.currentPlayer = null;
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }

    public Player nextTurn() {
        for (int i = 0; i < this.players.size(); i++) {
            if(this.currentPlayer == this.players.get(i)) {
                this.setCurrentPlayer(this.players.get((i + 1) % this.players.size()));
                return this.players.get((i + 1) % this.players.size());
            }
        }

        return this.currentPlayer;
    }

    @Override
    public String toString() {
        String s = "";

        for (int i = 0; i < this.players.size(); i++) {
            s += this.players.get(i).toString() + " ";
        }

        return s;
    }
}
