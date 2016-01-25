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

    Hand hand;
    Game game;
    Points points;
    String name;

    public Player(Game game, String name) {
        this.game = game;
        this.name = name;
        this.points = new Points();
        this.hand = new Hand(game.getBag());
    }

    //TODO: Do something with the Observable data
    public void update(Observable o, Object arg) {
        System.out.println("Game State: " + arg);
    }

    public Hand getHand() {
        return this.hand;
    }

    public void addPoints(int p) {
        this.points.addPoints(p);
    }

    public int getPoints() {
        return this.points.getPoints();
    }

//    abstract String getName();
//
//    abstract boolean isHuman();

//    public Stone getStone(Stone stone);
//
//    public void playStone(Stone stone);
//
//    public void tradeStone(Stone stone);
//
//    public Move nextMove(Board board);
//
//    public Move firstMove(Board board);
//
//    abstract Hand getHand();
//
//    public void joinGame(Game game);
//
//    public void leaveGame();

    @Override
    public String toString() {
        return this.name;
    }
}
