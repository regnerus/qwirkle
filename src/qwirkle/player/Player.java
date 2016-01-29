package qwirkle.player;

import qwirkle.game.Game;
import qwirkle.game.Hand;
import qwirkle.game.Points;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Bouke Regnerus
 * @version 1.0
 * @since 2016-01-29
 */
public abstract class Player implements Observer {

    private Hand hand;
    private Points points;
    private String username;
    private Game game;

    /**
     * Initialize a new player with a new hand.
     */
    public Player() {
        this.hand = new Hand();
    }

    /**
     * Initialize a new player with a new hand and a username.
     *
     * @param username
     */
    public Player(String username) {
        this();
        this.username = username;
    }

    /**
     * Set game of this player.
     *
     * @param game
     */
    public void setGame(Game game) {
        this.game = game;
        this.points = new Points();

        hand.init(game.getBag());

        if (this instanceof ServerPlayer) {
            ((ServerPlayer) this).getClient().handleInitHand(this.hand);
        }
    }

    /**
     * @return Return the game of this player.
     */
    public Game getGame() {
        return this.game;
    }

    public void update(Observable o, Object cmd) {
        ((ServerPlayer) this).getClient().handleEmitToAllPlayers("" + cmd);
    }

    /**
     * Add points to this player.
     *
     * @param p points
     */
    public void addPoints(int p) {
        this.points.addPoints(p);
    }

    /**
     * @return Return points of this player.
     */
    public int getPoints() {
        return this.points.getPoints();
    }

    /**
     * Set the username of this player.
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return Return the username of this player.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Set the hand of this player.
     *
     * @param hand
     */
    public void setHand(Hand hand) {
        this.hand = hand;
    }

    /**
     * @return Return the hand of this player.
     */
    public Hand getHand() {
        return this.hand;
    }

    /**
     * @return Return the username of this player.
     */
    @Override
    public String toString() {
        return this.username;
    }
}
