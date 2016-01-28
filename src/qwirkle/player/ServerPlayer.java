package qwirkle.player;

// game

// server

import qwirkle.server.ClientHandler;

/**
 * Created by chris on 24/01/16.
 */
public class ServerPlayer extends Player {

    private ClientHandler client;

    public ServerPlayer(ClientHandler client) {
        super();
        this.client = client;

        client.setPlayer(this);
    }

    public ServerPlayer(ClientHandler client, String username) {
        super(username);
        this.client = client;

        client.setPlayer(this);
    }

    public ClientHandler getClient() {
        return this.client;
    }


    public boolean isHuman() {
        return true;
    }

//    /**
//     * The ServerPlayer is used to keep track of players on the server side.
//     *
//     * @param client
//     * @param username
//     */
//    public ServerPlayer(ClientHandler client, GameController game, String username) {
//        super(game, username);
//        this.client = client;
//        this.username = username;
//    }
//
//    /**
//     * Let player receive a stone in their hand.
//     *
//     * @param stone stone to receive
//     */
//    public void getStone(Stone stone) {
//        hand.addStone(stone);
//    }
//
//    /**
//     * Lay a stone on the board.
//     *
//     * @param stone
//     */
//    public void playStone(Stone stone) {
//        // TODO: select stone from hand
//        // TODO: select position to place stone
//        hand.removeStone(stone);
//        game.getBoard().placeStone(stone);
//    }
//
//    /**
//     * Trade stone with random stone from game's bag.
//     *
//     * @param stone
//     */
//    public void tradeStone(Stone stone) {
//        // TODO: select stone from hand
//        hand.removeStone(stone);
//        hand.addStone(game.getBag().switchStone(stone));
//    }
//
//    /**
//     * Get player's hand.
//     *
//     * @return
//     */
//    public Hand getHand() {
//        return hand;
//    }
//
//    /**
//     * Join a game and receive a random hand from bag.
//     *
//     * @param game game to join
//     */
//    public void joinGame(GameController g) {
//        this.game = g;
//        this.hand = new Hand(g.getBag());
//    }
//
//    /**
//     * Get game currently playing in.
//     *
//     * @return
//     */
//    public GameController getGame() {
//        return game;
//    }
//
//    /**
//     * Leave game currently in.
//     */
//    public void leaveGame() {
//        // TODO: add score to leaderboard when game was finished
//        if (game.isFinished()) {
//            this.game = null;
//        }
//    }
//
//    /**
//     * Get client that player is connected to.
//     *
//     * @return
//     */
//    public ClientHandler getClient() {
//        return client;
//    }
//
//    /**
//     * Get player's username.
//     *
//     * @return
//     */
//    public String getUsername() {
//        return username;
//    }
}
