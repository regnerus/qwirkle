package qwirkle.player;

// game

import qwirkle.game.Game;
import qwirkle.game.Hand;
import qwirkle.game.Points;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Chris on 23/01/16.
 */
public abstract class Player implements Observer {

    private Hand hand;
    private Points points;
    private String username;
    private Game game;

    public Player() {
        this.hand = new Hand();
    }

    public Player(String username) {
        this();
        this.username = username;
    }

    public void setGame(Game game) {
        this.game = game;
        this.points = new Points();

        hand.init(game.getBag());

        if(this instanceof ServerPlayer) {
            ((ServerPlayer) this).getClient().handleInitHand(this.hand);
        }
    }

    public Game getGame() {
        return this.game;
    }


    //TODO: Do something with the Observable data
    public void update(Observable o, Object cmd) {
        ((ServerPlayer) this).getClient().handleEmitToAllPlayers("" + cmd);
    }

//    public Hand getHand() {
//        return this.hand;
//    }

    public void addPoints(int p) {
        this.points.addPoints(p);
    }

    public int getPoints() {
        return this.points.getPoints();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }


    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public Hand getHand() {
        return this.hand;
    }

    @Override
    public String toString() {
        return this.username;
    }
}
