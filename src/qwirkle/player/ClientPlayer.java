package qwirkle.player;

import qwirkle.shared.GameController;

/**
 * Created by chris on 24/01/16.
 */
public class ClientPlayer extends Player {

    private String name;
    private GameController game;

    public ClientPlayer(GameController game, String name) {
        super(game, name);
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
//    public void joinGame(Game game) {
//        //TODO
//    }
//
//    public void leaveGame() {
//        //TODO
//    }
}
