package qwirkle;

// game

import qwirkle.game.Game;
import qwirkle.shared.CliController;
import qwirkle.shared.Input;

// shared
// java

/**
 * The Qwirkle Main
 *
 * Created by Chris ter Beke and Bouke Regnerus
 */
public class Qwirkle {

    public static void main(String[] args) {
        // TODO: implement gameplay

        Input input = new Input();
        CliController cli = new CliController();
        Game game = new Game(input);

        game.startGame();

//        while(true){

            cli.logSimple(game.toString());

//            if(input.get().equals("exit")){
//                break;
//            }
//
//            break;
//        }

        input.close();
    }
}