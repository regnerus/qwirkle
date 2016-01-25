package qwirkle;

// game

import qwirkle.shared.Game;
import qwirkle.shared.GameController;
import qwirkle.shared.GameView;
// shared
// java

/**
 * The Qwirkle Main.
 * <p>
 * Created by Chris ter Beke and Bouke Regnerus
 */
public class Qwirkle {

    public static void main(String[] args) {
        Game game = new Game();
        GameView view = new GameView();
        GameController controller = new GameController(game, view);

        controller.startGame();
        controller.updateView();
    }
}