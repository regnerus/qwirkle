package qwirkle.player;

// game

import qwirkle.client.ClientController;
import qwirkle.game.Stone;
import qwirkle.server.ClientHandler;
import qwirkle.shared.Cli;

import java.util.ArrayList;

/**
 * Created by Bouke on 23/01/16.
 */
public class HumanPlayer extends ClientPlayer {
    private ClientHandler client;

    public HumanPlayer() {
        super();
    }

    public HumanPlayer(String username) {
        super(username);
    }

//    public HumanPlayer(ClientHandler client) {
//        super();
//        this.client = client;
//    }
//
//    public HumanPlayer(ClientHandler client, String username) {
//        super(username);
//        this.client = client;
//    }

    public ClientHandler getClient() {
        return this.client;
    }

    public void determineMove() {
        Cli view = ClientController.getInstance().getView();

        int move = view.readInt("Make a move: (1. Place, 2. Switch, 3. Skip)");

        if (move == 1) {
            ArrayList<Stone> addStones = new ArrayList<>();

            while (true) {
                String[] input = view.readArray("Place, format: \"A0 A0 1\" (return empty to submit turn)");

                if (input[0].length() < 1) {
                    break;
                }

//                Stone stone = testPlayer1.getHand().coordinateToStone(input[2]);
//                stone.setLocation(board.coordinateToPosition(input[0], input[1]));
//
//                addStones.add(stone);

            }

//            int points = game.placeStones(this, addStones);
            int points = 0;

            if (points > 0) {
//                view.logSimple("Placed " + addStones.size() + " stones for " + points + " points!");
            } else {
                view.logSimple("Invalid move, try again! Tried to place: " + addStones.toString());
                this.determineMove();
            }
        } else if (move == 2) {
            ArrayList<Stone> addStones = new ArrayList<>();

            while (true) {
                String[] input = view.readArray("Switch, format: \"1\" (return empty to submit turn)");

                if (input[0].length() < 1) {
                    break;
                }

//                Stone stone = testPlayer1.getHand().coordinateToStone(input[0]);

//                addStones.add(stone);

            }

//            game.switchStones(testPlayer1, addStones);

            view.logSimple("Switched " + addStones.size() + " stones!");
//            view.logSimple("New Hand: " + testPlayer1.getHand().toString());

        } else if (move == 3) {
            view.logSimple("Skipped turn!");
        }


//        for (int i = 0; i < move.length; i++) {
//            move[i] = move[i].toUpperCase();
//        }

//        if (move[0] == "PLACE") {
//            for (int i = 1; i < move.length; i+3) {
//                move[i] = move[i].toUpperCase();
//                System.out.println("Hand", testPlayer1.getHand().coordinateToStone("4"));
//            }
//        } else if (move[0] == "SWITCH") {
//            //Todo: Switch stones with bag.
//        } else if (move[0] == "SKIP") {
//            //Todo: Implement turns.
//        }

    }



    public boolean isHuman() {
        return true;
    }

}
