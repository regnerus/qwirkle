package qwirkle.player;

// game
import qwirkle.game.*;

/**
 * Created by Chris on 23/01/16.
 */
public interface Player {

    public String getName();

    public boolean isHuman();

    public void getStone(Stone stone);

    public void playStone(Stone stone);

    public void tradeStone(Stone stone);

    public Move nextMove(Board board);

    public Move firstMove(Board board);

    public Hand getHand();

    public void joinGame(Game game);

    public void leaveGame();
}
