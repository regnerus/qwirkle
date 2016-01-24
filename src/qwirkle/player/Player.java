package qwirkle.player;

// game

import qwirkle.game.Hand;

/**
 * Created by Chris on 23/01/16.
 */
public interface Player {

    String getName();

    boolean isHuman();

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
     Hand getHand();
//
//    public void joinGame(Game game);
//
//    public void leaveGame();
}
