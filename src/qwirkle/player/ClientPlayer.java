package qwirkle.player;

import qwirkle.game.Game;
import qwirkle.game.Hand;

/**
 * Created by chris on 24/01/16.
 */
public class ClientPlayer implements Player  {

    private String name;
    private Hand hand;
    private Game game;

    public ClientPlayer(Game game, String name) {
        this.game = game;
        this.name = name;
        this.hand = new Hand (game.getBag());
    }

    public String getName() {
        return this.name;
    }

    public boolean isHuman() {
        return true; //TODO
    }

//    public Stone getStone(Stone stone) {
//        return stone;
//    }
//
//    public void playStone(Stone stone) {
//        //TODO
//    }
//
//    public void tradeStone(Stone stone) {
//        //TODO
//    }
//
//    public Move nextMove(Board board) {
//        //TODO
//    }
//
//    public Move firstMove(Board board) {
//        //TODO
//    }
//
    public Hand getHand() {
        return this.hand;
    }
//
//    public void joinGame(Game game) {
//        //TODO
//    }
//
//    public void leaveGame() {
//        //TODO
//    }
}
