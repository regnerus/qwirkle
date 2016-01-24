package qwirkle.game;

import qwirkle.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bouke on 24/01/16.
 */
public class Score{
    Map<Player, Points> players;

    public Score() {
        this.players = new HashMap<>();
    }

    public Score(ArrayList<Player> players) {
        this.players = new HashMap<>();

        for(Player player : players) {
            this.addPlayer(player);
        }
    }

    public void addPlayer (Player player) {
        this.players.put(player, new Points());
    }

    public Points getPlayer(Player player) {
        return this.players.get(player);
    }
}
