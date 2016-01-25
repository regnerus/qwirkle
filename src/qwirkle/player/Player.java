package qwirkle.player;

// game

import qwirkle.game.Game;
import qwirkle.game.Hand;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Chris on 23/01/16.
 */
public abstract class Player implements Observer {

    Hand hand;
    Game game;
    String name;

    public Player (Game game, String name) {
        this.game = game;
        this.name = name;
        this.hand = new Hand (game.getBag());
    }

    public void update(Observable o, Object arg) {
        System.out.println("Game changed: " + arg);
    }

    public Hand getHand () {
        return this.hand;
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
}
