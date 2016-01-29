package qwirkle.player;

import qwirkle.client.Client;
import qwirkle.client.ClientController;
import qwirkle.game.Board;
import qwirkle.game.Stone;
import qwirkle.shared.Cli;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bouke Regnerus
 * @version 1.0
 * @since 2016-01-29
 */
public class HumanPlayer extends ClientPlayer {
    public HumanPlayer(ClientController clientController) {
        super(clientController);
    }

    public HumanPlayer(ClientController clientController, String username) {
        super(clientController, username);
    }

    /**
     * Ask the human player to submit a row of stones for the first move.
     *
     * @return Return the first move.
     */
    @Override
    public ArrayList<Stone> determineFirstMove() {
        Cli view = ClientController.getInstance().getView();
        ArrayList<Stone> addStones = new ArrayList<>();
        ArrayList<Stone> row = new ArrayList<>();

        view.logSimple("Submit a first move!");
        while (true) {
            String[] input = view.readArray("Select a stone from hand, format: \"1\"" +
                    " (return empty to submit turn)");

            if (input[0].length() < 1) {
                break;
            }

            Stone stone = this.getHand().coordinateToStone(input[0]);
            addStones.add(stone);
        }

        for (int i = 0; i < addStones.size(); i++) {
            addStones.get(i).setLocation(i, 0);

            if (Board.validateList(row, addStones.get(i))) {
                row.add(addStones.get(i));
            } else {
                view.logSimple("Invalid move, try again! Tried to place: " + addStones.toString());
                this.determineFirstMove();
            }
        }

        return row;
    }

    /**
     * @param board
     * @return Return a list of stones to be placed on the board.
     */
    public ArrayList<Stone> determinePlaceStones(Board board) {
        Cli view = ClientController.getInstance().getView();
        ArrayList<Stone> addStones = new ArrayList<>();

        while (true) {
            String[] input = view.readArray("Place, format: \"A0 A0 1\" " +
                    "(return empty to submit turn)");

            if (input[0].length() < 1) {
                break;
            }

            Stone stone = this.getHand().coordinateToStone(input[2]);
            stone.setLocation(board.coordinateToPosition(input[0], input[1]));
            stone.setTemporary(true);

            if (board.validMove(stone)) {
                board.placeStone(stone);
                addStones.add(stone);

                view.logSimple(board.toString());
            } else {
                view.logSimple("You can not place that stone there!");
            }
        }

        return addStones;
    }

    /**
     * @param board
     * @return Return a list of stones to be switched with the bag.
     */
    public ArrayList<Stone> determineSwitchStones(Board board) {
        Cli view = ClientController.getInstance().getView();
        ArrayList<Stone> addStones = new ArrayList<>();

        while (true) {
            String[] input = view.readArray("Switch, format: \"1\" (return empty to submit turn)");

            if (input[0].length() < 1) {
                break;
            }

            Stone stone = this.getHand().coordinateToStone(input[0]);

            addStones.add(stone);

        }

        view.logSimple("Switched " + addStones.size() + " stones!");

        return addStones;
    }

    /**
     * Ask the human player to make a move.
     * <p>
     * This move can either be place stones on the board, switch stones with the bag
     * or skip a turn.
     *
     * @param board
     * @param client
     * @return
     */
    @Override
    public void determineMove(Board board, Client client) {
        Cli view = ClientController.getInstance().getView();

        int moveChoice = view.readInt("Make a move: (1. Place, 2. Switch, 3. Skip)");

        if (moveChoice == 1) {
            client.handleMakeMove(this.determinePlaceStones(board));
        } else if (moveChoice == 2) {
            List<Stone> stones = this.determineSwitchStones(board);
            for (Stone stone : stones) {
                ClientController.getInstance().getPlayer().getHand().removeStone(stone);
            }

            client.handleChangeStones(stones);
        } else if (moveChoice == 3) {
            client.handleMakeMove();
            view.logSimple("Turn Skipped!");
        }
    }


    public boolean isHuman() {
        return true;
    }

}
