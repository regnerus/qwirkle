package qwirkle.player;

import qwirkle.game.Stone;

import java.util.List;

/**
 * Created by chris on 24/01/16.
 */
public class ClientPlayer extends Player {

    public ClientPlayer() {
        super();
    }

    public ClientPlayer(String username) {
        super(username);
    }

    public boolean isHuman() {
        return true; //TODO
    }

    public void addToHand(Stone stone) {
        super.getHand().addStone(stone);
    }

    public void addToHand(List<Stone> stones) {
        super.getHand().addStone(stones);
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
//    public void joinGame(Game game) {
//        //TODO
//    }
//
//    public void leaveGame() {
//        //TODO
//    }
}
