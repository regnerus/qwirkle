package qwirkle.player;

// game
import qwirkle.game.Game;
import qwirkle.game.Hand;
import qwirkle.game.Stone;

// server
import qwirkle.server.controllers.ClientController;

/**
 * Created by chris on 24/01/16.
 */
public abstract class ServerPlayer implements Player {

    private Hand hand;
    private Game game;
    private ClientController client;

    /**
     * The ServerPlayer is used to keep track of players on the server side
     */
    public ServerPlayer(ClientController client) {
        this.client = client;
    }

    /**
     * Let player receive a stone in their hand
     * @param stone stone to receive
     */
    public void receiveStone(Stone stone) {
        hand.addStone(stone);
    }

    public void playStone(Stone stone) {
        // TODO: select stone from hand
        // TODO: select position to place stone
        hand.removeStone(stone);
        game.getBoard().placeStone(stone);
    }

    public void tradeStone(Stone stone) {
        // TODO: select stone from hand
        hand.removeStone(stone);
        hand.addStone(game.getBag().switchStone(stone));
    }

    /**
     * Get player's hand
     * @return
     */
    public Hand getHand() {
        return hand;
    }

    /**
     * Join a game and receive a random hand from bag
     * @param game game to join
     */
    public void joinGame(Game game) {
        this.game = game;
        this.hand = new Hand(game.getBag());
    }

    /**
     * Get game currently playing in
     * @return
     */
    public Game getGame() {
        return game;
    }

    /**
     * Leave game currently in
     */
    public void leaveGame() {
        this.game = null;
    }

    /**
     * Get client that player is connected to
     * @return
     */
    public ClientController getClient() {
        return client;
    }
}
