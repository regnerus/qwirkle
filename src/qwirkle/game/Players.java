package qwirkle.game;

import qwirkle.player.Player;

import java.util.ArrayList;

/**
 * Created by Bouke on 25/01/16.
 */
public class Players {
    private ArrayList<Player> players;

    public Players () {
        this.players = new ArrayList<>();
    }

    public Players (ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public void addPlayer (Player player) {
        this.players.add(player);
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
