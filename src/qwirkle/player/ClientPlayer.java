package qwirkle.player;

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
